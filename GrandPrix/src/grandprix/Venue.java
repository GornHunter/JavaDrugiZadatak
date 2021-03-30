/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandprix;

/**
 *
 * @author GornHunter
 */
public class Venue {
    private int averageLapTime;
    private double chanceOfRain;
    private int numberOfLaps;
    private String venueName;
    
    public Venue(){}
    
    public Venue(String venueName, int numberOfLaps, int averageLapTime, double chanceOfRain){
        this.venueName = venueName;
        this.numberOfLaps = numberOfLaps;
        this.averageLapTime = averageLapTime;
        this.chanceOfRain = chanceOfRain;
    }
    
    public void setAverageLapTime(int averageLapTime){
        this.averageLapTime = averageLapTime;
    }
    
    public int getAverageLapTime(){
        return this.averageLapTime;
    }
    
    public void setChanceOfRain(double chanceOfRain){
        this.chanceOfRain = chanceOfRain;
    }
    
    public double getChanceOfRain(){
        return this.chanceOfRain;
    }
    
    public void setNumberOfLaps(int numberOfLaps){
        this.numberOfLaps = numberOfLaps;
    }
    
    public int getNumberOfLaps(){
        return this.numberOfLaps;
    }
    
    public void setVenueName(String venueName){
        this.venueName = venueName;
    }
    
    public String getVenueName(){
        return this.venueName;
    }
}
