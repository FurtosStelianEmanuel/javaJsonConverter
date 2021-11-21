/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler;

import bananaconvert.marshaler.exception.DeserializationException;
import bananaconvert.marshaler.exception.SerializationException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import org.json.simple.JSONObject;

/**
 *
 * @author Manel
 * @param <K>
 */
public abstract class Marshaler<K> {

    public void marshal(Object input, Field output, Object instance) throws DeserializationException {
        try {
            output.set(instance, input);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new DeserializationException(ex);
        }
    }

    public void marshal(Field input, JSONObject output, Object instance) throws SerializationException {
        try {
            output.put(input.getName(), input.get(instance));
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new SerializationException(ex);
        }
    }

    public boolean canProcessType(Class type) {
        Class<K> marshalerType = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return marshalerType == type;
    }
}
