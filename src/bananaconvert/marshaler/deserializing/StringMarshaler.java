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
public class StringMarshaler extends Marshaler<String> {

    @Override
    public void marshal(Object input, Field output, Object instance) throws DeserializationException {
        super.marshal((String) input, output, instance);
    }
}
