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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Manel
 */
public class ModelBank {

    public static final Car EXAMPLE_CAR = new Car() {
        {
            engine = new Engine() {
                {
                    modelNumber = 12344;
                    hasVvt = true;
                    hasInterference = false;
                    timingMethod = "chain";
                    pistons = Stream.of(
                            new Piston() {
                        {
                            bore = stroke = 2;
                            index = "PP01";
                            valveClearence = 1.05;
                            temperatureCharacteristics = new TemperatureCharacteristics(90.05, 120.55, -45.566, "Celsius");
                        }
                    },
                            new Piston() {
                        {
                            bore = stroke = 2;
                            index = "PP02";
                            valveClearence = 1.05;
                            temperatureCharacteristics = new TemperatureCharacteristics(90.05, 120.55, -45.566, "Celsius");
                        }
                    }).collect(Collectors.toList());
                }
            };
            manufacturerName = "Dacia";
            fuelType = "gasoline";
            numberOfDoors = 5;
            previousOwners = Stream.of("Emanoil Firtes").collect(Collectors.toList());
            previousPrices = Stream.of(100l, 50l, 20l, 5l).collect(Collectors.toList());
        }
    };

    public static final Car CAR_WITH_NULL_VALUES = new Car() {
        {
            engine = new Engine() {
                {
                    modelNumber = 12344;
                    hasVvt = true;
                    hasInterference = false;
                    timingMethod = null;
                    pistons = Stream.of(
                            new Piston() {
                        {
                            bore = stroke = 2;
                            index = null;
                            valveClearence = 1.05;
                            temperatureCharacteristics = null;
                        }
                    },
                            new Piston() {
                        {
                            bore = 0;
                            stroke = 2;
                            index = "PP02";
                            valveClearence = 1.05;
                            temperatureCharacteristics = new TemperatureCharacteristics(90.05, 120.55, -45.566, null);
                        }
                    },
                            null
                    ).collect(Collectors.toList());
                }
            };
            manufacturerName = "Dacia";
            fuelType = null;
            numberOfDoors = 5;
            previousOwners = Stream.of("Emanoil Firtes", null).collect(Collectors.toList());
            previousPrices = null;
        }
    };
}
