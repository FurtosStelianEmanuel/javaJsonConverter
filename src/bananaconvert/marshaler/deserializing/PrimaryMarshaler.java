/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler.deserializing;

import bananaconvert.marshaler.Marshaler;
import bananaconvert.marshaler.exception.DeserializationFailed;
import bananaconvert.marshaler.exception.MarshallingFailed;
import bananaconvert.marshaler.exception.NoMatchingMarshalerFound;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            new ListMarshaler(this)
        };
    }

    public K deserialize(String input, Class<K> type) throws DeserializationFailed {
        try {
            return deserialize((JSONObject) parser.parse(input), type);
        } catch (ParseException | NoSuchFieldException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoMatchingMarshalerFound | MarshallingFailed ex) {
            throw new DeserializationFailed(ex);
        }
    }

    protected K deserialize(JSONObject input, Class<K> type) throws NoSuchFieldException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoMatchingMarshalerFound, MarshallingFailed {
        K objectToReturn = type.getConstructor().newInstance();
        Iterator iterator = input.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry pair = (Entry) iterator.next();
            String fieldName = (String) pair.getKey();
            Field field = objectToReturn.getClass().getField(fieldName);

            Marshaler marshaler = getMarshalerForType(field.getType());

            if (marshaler != null) {
                marshaler.marshal(pair.getValue(), field, objectToReturn);
                continue;
            }

            super.marshal(deserialize((JSONObject) pair.getValue(), (Class<K>) field.getType()), field, objectToReturn);
        }

        return objectToReturn;
    }

    private Marshaler getMarshalerForType(Class type) {
        for (Marshaler marshaler : availableMarshalers) {
            if (marshaler.canProcessType(type)) {
                return marshaler;
            }
        }

        return null;
    }
}
