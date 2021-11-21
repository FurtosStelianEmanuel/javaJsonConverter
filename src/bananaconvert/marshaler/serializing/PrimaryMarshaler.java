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
import org.json.simple.JSONObject;

/**
 *
 * @author Manel
 * @param <K>
 */
public class PrimaryMarshaler<K> extends Marshaler<K> {

    public final Marshaler[] availableMarshalers;

    public PrimaryMarshaler() {
        availableMarshalers = new Marshaler[]{
            new PrimitiveMarshaler(),
            new ListMarshaler(this),
            new UUIDMarshaler(),
            this
        };
    }

    @Override
    public boolean canProcessType(Class type) {
        return false;
    }

    public String serialize(K object) throws SerializationException {
        return serializeObject(object).toJSONString();
    }

    protected JSONObject serializeObject(K object) throws SerializationException {
        if (object == null) {
            return null;
        }

        Field[] fields = object.getClass().getFields();
        JSONObject output = new JSONObject();

        for (Field field : fields) {
            Marshaler marshaler = CommonUtils.getMarshalerForType(field.getType(), availableMarshalers);

            if (marshaler != null) {
                marshaler.marshal(field, output, object);
                continue;
            }

            serializeObject(field, output, object);
        }

        return output;
    }

    private void serializeObject(Field field, JSONObject output, K object) throws SerializationException {
        try {
            output.put(field.getName(), serializeObject((K) CommonUtils.getValueFromField(field, object)));
        } catch (SerializationException | MarshallingFailed ex) {
            throw new SerializationException(ex);
        }
    }
}
