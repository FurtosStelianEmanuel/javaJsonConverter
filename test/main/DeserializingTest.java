/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import bananaconvert.BananaConvert;
import bananaconvert.marshaler.exception.DeserializationException;
import classes.Car;
import org.junit.Test;

/**
 *
 * @author Manel
 */
public class DeserializingTest {

    private final BananaConvert converter;

    public DeserializingTest() {
        converter = new BananaConvert();
    }

    @Test
    public void primaryTest() throws DeserializationException {
        runDeserializationTest("{\n"
                + "    \"engine\": {\n"
                + "        \"modelNumber\": 12344,\n"
                + "        \"hasVvt\": true,\n"
                + "        \"hasInterference\": false,\n"
                + "        \"timingMethod\": \"chain\",\n"
                + "        \"pistons\": [\n"
                + "            {\n"
                + "                \"bore\": 2,\n"
                + "                \"stroke\": 2,\n"
                + "                \"index\": \"PP01\",\n"
                + "                \"valveClearence\": 1.05,\n"
                + "                \"temperatureCharacteristics\": {\n"
                + "                    \"optimalOperatingTemperature\": 90.05,\n"
                + "                    \"maximumOperatingTemperature\": 120.55,\n"
                + "                    \"minimumOperatingTemperature\": -45.566,\n"
                + "                    \"scale\": \"Celsius\"\n"
                + "                }\n"
                + "            },\n"
                + "            {\n"
                + "                \"bore\": 2,\n"
                + "                \"stroke\": 2,\n"
                + "                \"index\": \"PP02\",\n"
                + "                \"valveClearence\": 1.05,\n"
                + "                \"temperatureCharacteristics\": {\n"
                + "                    \"optimalOperatingTemperature\": 90.05,\n"
                + "                    \"maximumOperatingTemperature\": 120.55,\n"
                + "                    \"minimumOperatingTemperature\": -45.566,\n"
                + "                    \"scale\": \"Celsius\"\n"
                + "                }\n"
                + "            }\n"
                + "        ]\n"
                + "    },\n"
                + "    \"manufacturerName\": \"Dacia\",\n"
                + "    \"globalIdentifier\": \"5f7b8de2-1af2-4331-b51c-768fd5f30896\",\n"
                + "    \"fuelType\": \"gasoline\",\n"
                + "    \"numberOfDoors\": 5,\n"
                + "    \"previousOwners\": [\n"
                + "        \"Emanoil Firtes\"\n"
                + "    ],\n"
                + "    \"previousPrices\": [\n"
                + "        100,\n"
                + "        50,\n"
                + "        20,\n"
                + "        5\n"
                + "    ]\n"
                + "}", ModelBank.EXAMPLE_CAR);
    }

    @Test
    public void nullValues() throws DeserializationException {
        runDeserializationTest("{\n"
                + "    \"fuelType\": null,\n"
                + "    \"engine\": {\n"
                + "        \"hasInterference\": false,\n"
                + "        \"modelNumber\": 12344,\n"
                + "        \"timingMethod\": null,\n"
                + "        \"pistons\": [\n"
                + "            {\n"
                + "                \"temperatureCharacteristics\": null,\n"
                + "                \"valveClearence\": 1.05,\n"
                + "                \"index\": null,\n"
                + "                \"stroke\": 2,\n"
                + "                \"bore\": 2\n"
                + "            },\n"
                + "            {\n"
                + "                \"temperatureCharacteristics\": {\n"
                + "                    \"optimalOperatingTemperature\": 90.05,\n"
                + "                    \"maximumOperatingTemperature\": 120.55,\n"
                + "                    \"scale\": null,\n"
                + "                    \"minimumOperatingTemperature\": -45.566\n"
                + "                },\n"
                + "                \"valveClearence\": 1.05,\n"
                + "                \"index\": \"PP02\",\n"
                + "                \"stroke\": 2,\n"
                + "                \"bore\": null\n"
                + "            },\n"
                + "            null\n"
                + "        ],\n"
                + "        \"hasVvt\": true\n"
                + "    },\n"
                + "    \"previousOwners\": [\n"
                + "        \"Emanoil Firtes\",\n"
                + "        null\n"
                + "    ],\n"
                + "    \"manufacturerName\": \"Dacia\",\n"
                + "    \"numberOfDoors\": 5,\n"
                + "    \"globalIdentifier\": null\n"
                + "}", ModelBank.CAR_WITH_NULL_VALUES);
    }

    private void runDeserializationTest(String stringToParse, Car expectedCar) throws DeserializationException {
        Car actualCar = converter.deserializeJson(stringToParse, Car.class);

        CarAsserts.assertCar(expectedCar, actualCar);
    }
}
