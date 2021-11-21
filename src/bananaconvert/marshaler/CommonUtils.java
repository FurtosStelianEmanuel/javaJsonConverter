/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert.marshaler;

import bananaconvert.marshaler.exception.MarshallingFailed;
import java.lang.reflect.Field;

/**
 *
 * @author Manel
 */
public class CommonUtils {

    public static Object getValueFromField(Field field, Object instance) throws MarshallingFailed {
        try {
            return field.get(instance);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new MarshallingFailed(ex);
        }
    }

    public static Marshaler getMarshalerForType(Class type, Marshaler[] availableMarshalers) {
        for (Marshaler marshaler : availableMarshalers) {
            if (marshaler.canProcessType(type)) {
                return marshaler;
            }
        }

        return null;
    }
}
