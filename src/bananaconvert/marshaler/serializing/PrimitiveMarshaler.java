/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler.serializing;

import bananaconvert.marshaler.Marshaler;

/**
 *
 * @author Manel
 */
public class PrimitiveMarshaler extends Marshaler {

    @Override
    public boolean canProcessType(Class type) {
        return type.isPrimitive() || type == String.class || type == Double.class || type == Float.class || type == Integer.class || type == Long.class;
    }
}
