/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bananaconvert;

import bananaconvert.marshaler.deserializing.PrimaryMarshaler;
import bananaconvert.marshaler.exception.DeserializationFailed;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Manel
 */
public class BananaConvert {

    public <K> JSONObject serializeToJson(K objectToSerialize) throws IllegalArgumentException, IllegalAccessException {
        if (objectToSerialize == null) {
            return null;
        }

        Field[] fields = objectToSerialize.getClass().getFields();
        JSONObject object = new JSONObject();

        for (Field field : fields) {
            if (field.getType().isPrimitive()) {
                object.put(field.getName(), field.get(objectToSerialize));
            } else if (field.getType() == String.class) {
                object.put(field.getName(), field.get(objectToSerialize));
            } else if (field.getType() == UUID.class) {
                if (field.get(objectToSerialize) != null) {
                    object.put(field.getName(), ((UUID) field.get(objectToSerialize)).toString());
                } else {
                    object.put(field.getName(), null);
                }
            } else {
                object.put(field.getName(), serializeToJson(field.get(objectToSerialize)));
            }
        }

        return object;
    }

    public <K> K deserializeJson(String s, Class<K> type) throws ParseException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        JSONParser jsonParser = new JSONParser();
        K obj = type.getConstructor().newInstance();
        JSONObject object = (JSONObject) jsonParser.parse(s);
        Iterator it = object.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Field field = obj.getClass().getField((String) pair.getKey());
            if (field.getType() == int.class) {
                field.set(obj, (int) Long.parseLong(pair.getValue() + ""));
            } else if (field.getType() == String.class) {
                field.set(obj, pair.getValue());
            } else if (field.getType() == UUID.class) {
                field.set(obj, (UUID) UUID.fromString(pair.getValue() + ""));
            } else if (!field.getType().isPrimitive()) {
                field.set(obj, deserializeJson(((JSONObject) pair.getValue()).toJSONString(), field.getType()));
            }
        }

        return obj;
    }

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, ParseException, NoSuchMethodException, InstantiationException, InvocationTargetException, NoSuchFieldException, DeserializationFailed {
        BananaConvert bananaConvert = new BananaConvert();
//        RgbStripDetailProjection rgbStripDetailProjection = new RgbStripDetailProjection() {
//            {
//                redPin = 1;
//                greenPin = 2;
//                bluePin = 3;
//                sequence = 1;
//                id = UUID.randomUUID();
//            }
//        };
//
//        JSONObject result = bananaConvert.serializeToJson(rgbStripDetailProjection);
//        StringSelection stringSelection = new StringSelection(result.toJSONString());
//        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//        clipboard.setContents(stringSelection, null);
//        System.out.println(result);

        String input = "{\n"
                + "    \"sequence\": 1,\n"
                + "    \"redPin\": 1,\n"
                + "    \"bluePin\": 3,\n"
                + "    \"greenPin\": 2,\n"
                + "    \"id\": \"ee1d96dc-6bf4-49dd-bd62-6862d7f014e7\",\n"
                + "    \"extraBalonsticList\": [\n"
                + "        {\n"
                + "            \"extraBalonstic_string\": \"ugabuga\",\n"
                + "            \"extraBalonstic_int\": \"1\",\n"
                + "            \"extraBalonstic_integerList\": [\n"
                + "                1,\n"
                + "                2,\n"
                + "                3,\n"
                + "                4\n"
                + "            ],\n"
                + "            \"extraBalonstic_bombaSimpla\": {\n"
                + "                \"bomba\": \"bombaSimplaString\",\n"
                + "                \"bomba1\": \"bombaSimplaString1\",\n"
                + "                \"bomba2\": \"bombaSimplaString2\",\n"
                + "                \"bomba3\": \"bombaSimplaString3\"\n"
                + "            },\n"
                + "            \"extraBalonstic_bombaList\": [\n"
                + "                {\n"
                + "                    \"bomba\": \"bombaListString\",\n"
                + "                    \"bomba1\": \"bombaListString1\",\n"
                + "                    \"bomba2\": \"bombaListString2\",\n"
                + "                    \"bomba3\": \"bombaListString3\"\n"
                + "                },\n"
                + "                {\n"
                + "                    \"bomba\": \"bombaListStringCiocapik\",\n"
                + "                    \"bomba1\": \"bombaListStringCiocapik1\",\n"
                + "                    \"bomba2\": \"bombaListStringCiocapik2\",\n"
                + "                    \"bomba3\": \"bombaListStringCiocapik3\"\n"
                + "                }\n"
                + "            ]\n"
                + "        }\n"
                + "    ]\n"
                + "}";
        //RgbStripDetailProjection deserializeJson = bananaConvert.deserializeJson(input, RgbStripDetailProjection.class);

        PrimaryMarshaler marshaler = new PrimaryMarshaler();
        Object deserializeObject = marshaler.deserialize(input, RgbStripDetailProjection.class);
    }
}
