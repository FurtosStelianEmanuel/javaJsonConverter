/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler.deserializing;

import bananaconvert.marshaler.Marshaler;
import bananaconvert.marshaler.exception.DeserializationException;
import bananaconvert.marshaler.exception.MarshallingFailed;
import bananaconvert.marshaler.exception.NoMatchingMarshalerFound;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Manel
 * @param <K>
 */
public class ListMarshaler<K> extends Marshaler<List<K>> {

    private final PrimaryMarshaler primaryMarshaler;

    public ListMarshaler(PrimaryMarshaler primaryMarshaler) {
        this.primaryMarshaler = primaryMarshaler;
    }

    @Override
    public void marshal(Object input, Field output, Object instance) throws DeserializationException {
        if (isListOfPrimitives(output)) {
            deserializeListOfPrimitives(input, output, instance);
            return;
        }

        deserializeObjects(input, output, instance);
    }

    @Override
    public boolean canProcessType(Class type) {
        return type.equals(List.class);
    }

    private void deserializeListOfPrimitives(Object input, Field output, Object instance) throws DeserializationException {
        JSONArray array = (JSONArray) input;
        List<K> outputArray = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            Object object = array.get(i);
            outputArray.add((K) object);
        }

        super.marshal(outputArray, output, instance);
    }

    private void deserializeObjects(Object input, Field output, Object instance) throws DeserializationException {
        JSONArray array = (JSONArray) input;
        List<K> outputArray = new ArrayList<>();

        for (int i = 0; i < array.size(); i++) {
            Object object = array.get(i);
            try {
                outputArray.add((K) primaryMarshaler.deserialize((JSONObject) object, getListType(output)));
            } catch (NoSuchFieldException | NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoMatchingMarshalerFound | MarshallingFailed ex) {
                throw new DeserializationException(ex);
            }
        }

        super.marshal(outputArray, output, instance);
    }

    private boolean isListOfPrimitives(Field field) {
        Class listType = getListType(field);

        return listType.equals(Long.class) || listType.equals(Integer.class) || listType.equals(String.class);
    }

    private Class getListType(Field field) {
        ParameterizedType listType = (ParameterizedType) field.getGenericType();

        return (Class<?>) listType.getActualTypeArguments()[0];
    }
}
