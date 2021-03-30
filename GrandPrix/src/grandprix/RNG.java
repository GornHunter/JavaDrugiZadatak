/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandprix;

import java.util.Random;

/**
 *
 * @author GornHunter
 */
public class RNG {
    private int minimumValue;
    private int maximumValue;
    private Random rnd;
    
    public RNG(){}
    
    public RNG(int minimumValue, int maximumValue){
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }
    
    public void setMinimumValue(int minimumValue){
        this.minimumValue = minimumValue;
    }
    
    public int getMinimumValue(){
        return this.minimumValue;
    }
    
    public void setMaximumValue(int maximumValue){
        this.maximumValue = maximumValue;
    }
    
    public int getMaximumValue(){
        return this.maximumValue;
    }
    
    public int getRandomValue(){
        this.rnd = new Random();
        
        return this.rnd.nextInt((this.maximumValue - this.minimumValue) + 1) + this.minimumValue;
    }
}
