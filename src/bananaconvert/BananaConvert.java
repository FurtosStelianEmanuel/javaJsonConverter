/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert;

import bananaconvert.marshaler.exception.DeserializationException;
import bananaconvert.marshaler.exception.SerializationException;

/**
 *
 * @author Manel
 */
public class BananaConvert {

    public <K> String serializeToJson(K objectToSerialize) throws SerializationException {
        bananaconvert.marshaler.serializing.PrimaryMarshaler primaryMarshaler = new bananaconvert.marshaler.serializing.PrimaryMarshaler();
        return primaryMarshaler.serialize(objectToSerialize);
    }

    public <K> K deserializeJson(String s, Class<K> type) throws DeserializationException {
        bananaconvert.marshaler.deserializing.PrimaryMarshaler primaryMarshaler = new bananaconvert.marshaler.deserializing.PrimaryMarshaler();
        return (K) primaryMarshaler.deserialize(s, type);
    }
}
