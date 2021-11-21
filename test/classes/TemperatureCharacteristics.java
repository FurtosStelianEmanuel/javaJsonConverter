/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Manel
 */
public class TemperatureCharacteristics {

    public double optimalOperatingTemperature;
    public double maximumOperatingTemperature;
    public double minimumOperatingTemperature;
    public String scale;

    public TemperatureCharacteristics() {
    }

    public TemperatureCharacteristics(double optimalOperatingTemperature, double maximumOperatingTemperature, double minimumOperatingTemperature, String scale) {
        this.optimalOperatingTemperature = optimalOperatingTemperature;
        this.maximumOperatingTemperature = maximumOperatingTemperature;
        this.minimumOperatingTemperature = minimumOperatingTemperature;
        this.scale = scale;
    }
}
