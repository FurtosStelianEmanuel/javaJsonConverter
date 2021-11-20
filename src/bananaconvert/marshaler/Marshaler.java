/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler;

import bananaconvert.marshaler.exception.MarshallingFailed;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 *
 * @author Manel
 * @param <K>
 */
public abstract class Marshaler<K> {

    public void marshal(Object input, Field output, Object instance) throws MarshallingFailed {
        try {
            output.set(instance, input);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new MarshallingFailed(ex);
        }
    }

    public boolean canProcessType(Class type) {
        Class<K> marshalerType = (Class<K>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        return marshalerType == type;
    }
}
