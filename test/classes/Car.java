/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.List;
import java.util.UUID;

/**
 *
 * @author Manel
 */
public class Car {

    public Engine engine;
    public String manufacturerName;
    public String fuelType;
    public int numberOfDoors;
    public List<String> previousOwners;
    public List<Long> previousPrices;
    public UUID globalIdentifier;
}
