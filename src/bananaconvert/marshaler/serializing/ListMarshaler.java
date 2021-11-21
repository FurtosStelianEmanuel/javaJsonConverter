/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler.serializing;

import bananaconvert.marshaler.Marshaler;
import bananaconvert.marshaler.exception.SerializationException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
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
    public boolean canProcessType(Class type) {
        return type.equals(List.class);
    }

    @Override
    public void marshal(Field input, JSONObject output, Object instance) throws SerializationException {
        if (isListOfPrimitives(input)) {
            serializeListOfPrimitives(input, output, instance);
            return;
        }

        serializeObjects(input, output, instance);
    }

    private void serializeListOfPrimitives(Field input, JSONObject output, Object instance) throws SerializationException {
        List<K> listOfPrimitives = (List<K>) getValueFromField(input, instance);
        List<K> outputArray = new ArrayList<>();

        if (listOfPrimitives == null) {
            return;
        }

        for (K primitive : listOfPrimitives) {
            outputArray.add(primitive);
        }

        output.put(input.getName(), outputArray);
    }

    private void serializeObjects(Field input, JSONObject output, Object instance) throws SerializationException {
        List<Object> outputArray = new ArrayList<>();
        List<K> objects = (List<K>) getValueFromField(input, instance);

        for (K object : objects) {
            outputArray.add(primaryMarshaler.serializeObject(object));
        }

        output.put(input.getName(), outputArray);
    }

    private Object getValueFromField(Field field, Object instance) throws SerializationException {
        try {
            return field.get(instance);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new SerializationException(ex);
        }
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
