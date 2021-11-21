/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler.deserializing;

import bananaconvert.marshaler.CommonUtils;
import bananaconvert.marshaler.Marshaler;
import bananaconvert.marshaler.exception.DeserializationException;
import bananaconvert.marshaler.exception.MarshallingFailed;
import bananaconvert.marshaler.exception.NoMatchingMarshalerFound;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Manel
 * @param <K>
 */
public class PrimaryMarshaler<K> extends Marshaler<K> {

    public final Marshaler[] availableMarshalers;

    private final JSONParser parser;

    public PrimaryMarshaler() {
        parser = new JSONParser();
        availableMarshalers = new Marshaler[]{
            new IntegerMarshaler(),
            new StringMarshaler(),
            new UUIDMarshaler(),
            new ListMarshaler(this),
            new BooleanMarshaler(),
            new FloatingPointMarshaler()
        };
    }

    public K deserialize(String input, Class<K> type) throws DeserializationException {
        try {
            return deserialize((JSONObject) parser.parse(input), type);
        } catch (ParseException | NoSuchFieldException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoMatchingMarshalerFound | MarshallingFailed ex) {
            throw new DeserializationException(ex);
        }
    }

    protected K deserialize(JSONObject input, Class<K> type) throws NoSuchFieldException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoMatchingMarshalerFound, MarshallingFailed, DeserializationException {
        if (input == null) {
            return null;
        }

        K objectToReturn = type.getConstructor().newInstance();
        Iterator iterator = input.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry pair = (Entry) iterator.next();
            String fieldName = (String) pair.getKey();
            Field field = objectToReturn.getClass().getField(fieldName);

            Marshaler marshaler = CommonUtils.getMarshalerForType(field.getType(), availableMarshalers);

            if (marshaler != null) {
                marshaler.marshal(pair.getValue(), field, objectToReturn);
                continue;
            }

            super.marshal(deserialize((JSONObject) pair.getValue(), (Class<K>) field.getType()), field, objectToReturn);
        }

        return objectToReturn;
    }
}
