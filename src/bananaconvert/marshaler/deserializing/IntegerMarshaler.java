/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler.deserializing;

import bananaconvert.marshaler.Marshaler;
import bananaconvert.marshaler.exception.MarshallingFailed;
import java.lang.reflect.Field;

/**
 *
 * @author Manel
 */
public class IntegerMarshaler extends Marshaler<Integer> {

    @Override
    public void marshal(Object input, Field output, Object instance) throws MarshallingFailed {
        if (input == null) {
            super.marshal(0, output, instance);
            return;
        }

        super.marshal((int) Long.parseLong(input.toString()), output, instance);
    }

    @Override
    public boolean canProcessType(Class type) {
        return type == int.class || super.canProcessType(type);
    }
}
