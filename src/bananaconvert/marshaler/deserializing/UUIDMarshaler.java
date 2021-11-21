/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler.deserializing;

import bananaconvert.marshaler.Marshaler;
import bananaconvert.marshaler.exception.DeserializationException;
import java.lang.reflect.Field;
import java.util.UUID;

/**
 *
 * @author Manel
 */
public class UUIDMarshaler extends Marshaler<UUID> {

    @Override
    public void marshal(Object input, Field output, Object instance) throws DeserializationException {
        if (input == null) {
            super.marshal(null, output, instance);
            return;
        }

        super.marshal(UUID.fromString(input.toString()), output, instance);
    }
}
