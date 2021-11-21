/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler.deserializing;

import bananaconvert.marshaler.Marshaler;
import bananaconvert.marshaler.exception.DeserializationException;
import java.lang.reflect.Field;

/**
 *
 * @author Manel
 */
public class BooleanMarshaler extends Marshaler<Boolean> {

    @Override
    public void marshal(Object input, Field output, Object instance) throws DeserializationException {
        if (input == null) {
            return;
        }

        boolean result = input.toString().toLowerCase().equals("true") || input.toString().toLowerCase().equals("yes");

        super.marshal(result, output, instance);
    }

    @Override
    public boolean canProcessType(Class type) {
        return type == boolean.class || super.canProcessType(type);
    }
}
