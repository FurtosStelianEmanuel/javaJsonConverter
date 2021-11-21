/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler.serializing;

import bananaconvert.marshaler.CommonUtils;
import bananaconvert.marshaler.Marshaler;
import bananaconvert.marshaler.exception.MarshallingFailed;
import bananaconvert.marshaler.exception.SerializationException;
import java.lang.reflect.Field;
import java.util.UUID;
import org.json.simple.JSONObject;

/**
 *
 * @author Manel
 */
public class UUIDMarshaler extends Marshaler<UUID> {

    @Override
    public void marshal(Field input, JSONObject output, Object instance) throws SerializationException {
        try {
            Object serialized = CommonUtils.getValueFromField(input, instance);
            if (serialized == null) {
                output.put(input.getName(), null);
                return;
            }

            output.put(input.getName(), serialized.toString());
        } catch (MarshallingFailed ex) {
            throw new SerializationException(ex);
        }
    }

    @Override
    public boolean canProcessType(Class type) {
        return type == UUID.class;
    }
}
