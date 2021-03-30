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
public class Driver {
    private String name;
    private int ranking;
    private String specialSkill;
    private boolean eligibleToRace;
    private int accumulatedTime;
    private int accumulatedPoints;
    private PNEUMATICS pneumatics;
    
    public enum PNEUMATICS{
        DRY, RAIN
    }
    
    public Driver(){}
    
    public Driver(String name, int ranking, String specialSkill){
        this.name = name;
        this.ranking = ranking;
        this.specialSkill = specialSkill;
        this.eligibleToRace = true;
        this.pneumatics = PNEUMATICS.DRY;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setRanking(int ranking){
        this.ranking = ranking;
    }
    
    public int getRanking(){
        return this.ranking;
    }
    
    public void setSpecialSkill(String specialSkill){
        this.specialSkill = specialSkill;
    }
    
    public String getSpecialSkill(){
        return this.specialSkill;
    }
    
    public void setEligibleToRace(boolean eligibleToRace){
        this.eligibleToRace = eligibleToRace;
    }
    
    public boolean getEligibleToRace(){
        return this.eligibleToRace;
    }
    
    public void setAccumulatedTime(int accumulatedTime){
        this.accumulatedTime = accumulatedTime;
    }
    
    public int getAccumulatedTime(){
        return this.accumulatedTime;
    }
    
    public void setAccumulatedPoints(int accumulatedPoints){
        this.accumulatedPoints = accumulatedPoints;
    }
    
    public int getAccumulatedPoints(){
        return this.accumulatedPoints;
    }
    
    public void setPneumatics(PNEUMATICS pneumatics){
        this.pneumatics = pneumatics;
    }
    
    public PNEUMATICS getPneumatics(){
        return this.pneumatics;
    }
    
    public void useSpecialSkill(RNG rng, int currentLap){
        if(this.getSpecialSkill().equals("Braking") || this.getSpecialSkill().equals("Cornering")){
            rng = new RNG(1,8);
            this.accumulatedTime -= rng.getRandomValue();
        }
        else{
            if(currentLap % 3 == 0){
                rng = new RNG(10,20);
                this.accumulatedTime -= rng.getRandomValue();
            }
        }
    }
}
