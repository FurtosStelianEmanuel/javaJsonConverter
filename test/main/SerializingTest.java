/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import bananaconvert.BananaConvert;
import bananaconvert.marshaler.exception.SerializationException;
import classes.Car;
import classes.Engine;
import classes.Piston;
import classes.TemperatureCharacteristics;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

/**
 *
 * @author Manel
 */
public class SerializingTest {

    BananaConvert bananaConvert;

    public SerializingTest() {
        bananaConvert = new BananaConvert();
    }

    @Test
    public void primaryTest() throws ParseException, SerializationException {
        runSerializationTest(ModelBank.EXAMPLE_CAR);
    }

    @Test
    public void nullValues() throws SerializationException, ParseException {
        runSerializationTest(ModelBank.CAR_WITH_NULL_VALUES);
    }

    private void runSerializationTest(Car expectedCar) throws SerializationException, ParseException {
        String resultingString = bananaConvert.serializeToJson(expectedCar);

        JSONParser parser = new JSONParser();
        JSONObject parsed = (JSONObject) parser.parse(resultingString);

        Car actualCar = buildCarFromJson(parsed);

        CarAsserts.assertCar(expectedCar, actualCar);
    }

    private Car buildCarFromJson(JSONObject jsonObject) {
        Car toReturn = new Car();

        toReturn.fuelType = (String) jsonObject.get("fuelType");
        toReturn.manufacturerName = (String) jsonObject.get("manufacturerName");
        toReturn.numberOfDoors = (int) Long.parseLong(jsonObject.get("numberOfDoors").toString());
        toReturn.previousOwners = (List<String>) jsonObject.get("previousOwners");
        toReturn.previousPrices = (List<Long>) jsonObject.get("previousPrices");
        toReturn.globalIdentifier = buildCarGlobalIdentifier((String) jsonObject.get("globalIdentifier"));

        JSONObject engine = (JSONObject) jsonObject.get("engine");

        boolean hasPartId = (String) engine.get("partId") != null && (String) engine.get("partId") != "";

        toReturn.engine = new Engine() {
            {
                hasVvt = (boolean) engine.get("hasVvt");
                hasInterference = (boolean) engine.get("hasInterference");
                modelNumber = (int) Long.parseLong(engine.get("modelNumber").toString());
                timingMethod = (String) engine.get("timingMethod");
                partId = hasPartId ? UUID.fromString((String) engine.get("partId")) : null;
                pistons = buildPistons(engine);
            }
        };

        return toReturn;
    }

    private UUID buildCarGlobalIdentifier(String uuid) {
        if (uuid == null) {
            return null;
        }

        return UUID.fromString(uuid);
    }

    private List<Piston> buildPistons(JSONObject engine) {
        JSONArray pistonsJsonArray = (JSONArray) engine.get("pistons");
        List<Piston> pistons = new ArrayList();

        if (pistonsJsonArray == null) {
            return null;
        }

        for (int i = 0; i < pistonsJsonArray.size(); i++) {
            JSONObject object = (JSONObject) pistonsJsonArray.get(i);
            pistons.add(buildPiston(object));
        }

        return pistons;
    }

    private Piston buildPiston(JSONObject piston) {
        if (piston == null) {
            return null;
        }

        return new Piston() {
            {
                bore = (int) Long.parseLong(piston.get("bore").toString());
                stroke = (int) Long.parseLong(piston.get("stroke").toString());
                valveClearence = Double.parseDouble(piston.get("valveClearence").toString());
                temperatureCharacteristics = buildTemperatureCharacteristics((JSONObject) piston.get("temperatureCharacteristics"));
                index = (String) piston.get("index");
                partId = UUID.fromString((String) piston.get("partId"));
            }
        };
    }

    private TemperatureCharacteristics buildTemperatureCharacteristics(JSONObject temperatureCharacteristics) {
        if (temperatureCharacteristics == null) {
            return null;
        }

        return new TemperatureCharacteristics() {
            {
                maximumOperatingTemperature = Double.parseDouble(temperatureCharacteristics.get("maximumOperatingTemperature").toString());
                minimumOperatingTemperature = Double.parseDouble(temperatureCharacteristics.get("minimumOperatingTemperature").toString());
                optimalOperatingTemperature = Double.parseDouble(temperatureCharacteristics.get("optimalOperatingTemperature").toString());
                scale = (String) temperatureCharacteristics.get("scale");
            }
        };
    }
}
