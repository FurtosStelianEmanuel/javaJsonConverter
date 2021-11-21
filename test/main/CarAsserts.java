/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import classes.Car;
import classes.Engine;
import classes.Piston;
import classes.TemperatureCharacteristics;
import java.util.List;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Manel
 */
public class CarAsserts {

    public static void assertCar(Car expected, Car actual) {
        assertEquals(expected.manufacturerName, actual.manufacturerName);
        assertEquals(expected.fuelType, actual.fuelType);
        assertEquals(expected.numberOfDoors, actual.numberOfDoors);

        assertEngine(expected.engine, actual.engine);
        assertPreviousOwners(expected.previousOwners, actual.previousOwners);
        assertPreviousPrices(expected.previousPrices, actual.previousPrices);
    }

    private static void assertEngine(Engine expected, Engine actual) {
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
            return;
        }

        assertEquals(expected.hasInterference, actual.hasInterference);
        assertEquals(expected.hasVvt, actual.hasVvt);
        assertEquals(expected.modelNumber, actual.modelNumber);
        assertEquals(expected.timingMethod, actual.timingMethod);

        assertPistons(expected.pistons, actual.pistons);
    }

    private static void assertPistons(List<Piston> expected, List<Piston> actual) {
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertPiston(expected.get(i), actual.get(i));
        }
    }

    private static void assertPiston(Piston expected, Piston actual) {
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
            return;
        }

        assertEquals(expected.bore, actual.bore);
        assertEquals(expected.stroke, actual.stroke);
        assertEquals(expected.index, actual.index);
        assertEquals(expected.valveClearence, actual.valveClearence, 0.01);
        assertTemperatureCharacteristics(expected.temperatureCharacteristics, actual.temperatureCharacteristics);
    }

    private static void assertTemperatureCharacteristics(TemperatureCharacteristics expected, TemperatureCharacteristics actual) {
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
            return;
        }

        assertEquals(expected.maximumOperatingTemperature, actual.maximumOperatingTemperature, 0.001);
        assertEquals(expected.optimalOperatingTemperature, actual.optimalOperatingTemperature, 0.001);
        assertEquals(expected.minimumOperatingTemperature, actual.minimumOperatingTemperature, 0.001);
        assertEquals(expected.scale, actual.scale);
    }

    private static void assertPreviousOwners(List<String> expected, List<String> actual) {
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }

    private static void assertPreviousPrices(List<Long> expected, List<Long> actual) {
        if (expected == null || actual == null) {
            assertEquals(expected, actual);
            return;
        }

        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), actual.get(i));
        }
    }
}
