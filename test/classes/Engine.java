/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.List;

/**
 *
 * @author Manel
 */
public class Engine extends CarComponent {

    public int modelNumber;
    public boolean hasVvt;
    public boolean hasInterference;
    public String timingMethod;
    public List<Piston> pistons;
}
