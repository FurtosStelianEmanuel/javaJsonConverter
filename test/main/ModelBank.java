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
import java.util.UUID;
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
                            partId = UUID.fromString("9c90ef47-d7b9-402b-87e0-43a6b3462ca8");
                        }
                    },
                            new Piston() {
                        {
                            partId = UUID.fromString("793d1c18-787c-4520-ac44-97bb95b29f3f");
                            bore = stroke = 2;
                            index = "PP02";
                            valveClearence = 1.05;
                            temperatureCharacteristics = new TemperatureCharacteristics(90.05, 120.55, -45.566, "Celsius");
                        }
                    }).collect(Collectors.toList());
                    partId = UUID.fromString("b0a071d2-324f-453c-81f7-655a6aba90c1");
                }
            };
            manufacturerName = "Dacia";
            fuelType = "gasoline";
            numberOfDoors = 5;
            previousOwners = Stream.of("Emanoil Firtes").collect(Collectors.toList());
            previousPrices = Stream.of(100l, 50l, 20l, 5l).collect(Collectors.toList());
            globalIdentifier = UUID.fromString("5f7b8de2-1af2-4331-b51c-768fd5f30896");
        }
    };

    public static final Car CAR_WITH_NULL_VALUES = new Car() {
        {
            engine = new Engine() {
                {
                    partId = null; //intentional
                    modelNumber = 12344;
                    hasVvt = true;
                    hasInterference = false;
                    timingMethod = null;
                    pistons = Stream.of(
                            new Piston() {
                        {
                            partId = UUID.fromString("9c90ef47-d7b9-402b-87e0-43a6b3462ca8");
                            bore = stroke = 2;
                            index = null;
                            valveClearence = 1.05;
                            temperatureCharacteristics = null;
                        }
                    },
                            new Piston() {
                        {
                            partId = UUID.fromString("793d1c18-787c-4520-ac44-97bb95b29f3f");
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
            globalIdentifier = null;
        }
    };
}
