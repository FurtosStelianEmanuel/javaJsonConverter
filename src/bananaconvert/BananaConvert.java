/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert;

import bananaconvert.marshaler.exception.DeserializationException;
import bananaconvert.marshaler.exception.SerializationException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public <K> K deserializeJson(Path path, Class<K> type) throws DeserializationException {
        bananaconvert.marshaler.deserializing.PrimaryMarshaler primaryMarshaler = new bananaconvert.marshaler.deserializing.PrimaryMarshaler();

        String serialized = readFile(path);

        return (K) primaryMarshaler.deserialize(serialized, type);
    }

    private static String readFile(Path filePath) {
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()))) {

            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                contentBuilder.append(currentLine).append("\n");
            }

        } catch (IOException ex) {
            Logger.getLogger(BananaConvert.class.getName()).log(Level.SEVERE, null, ex);
        }

        return contentBuilder.toString();
    }
}
