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
public class FloatingPointMarshaler extends Marshaler<Double> {

    @Override
    public boolean canProcessType(Class type) {
        return type == float.class || type == double.class || super.canProcessType(type);
    }

    @Override
    public void marshal(Object input, Field output, Object instance) throws DeserializationException {
        if (input == null) {
            return;
        }

        if (output.getType() == double.class) {
            double result = (double) Double.parseDouble(input.toString());
            super.marshal(result, output, instance);
        } else if (output.getType() == float.class) {
            float result = (float) Float.parseFloat(input.toString());
            super.marshal(result, output, instance);
        } else if (output.getType() == Double.class) {
            super.marshal(Double.parseDouble(input.toString()), output, instance);
        } else if (output.getType() == Float.class) {
            super.marshal(Float.parseFloat(input.toString()), output, instance);
        }
    }
}
