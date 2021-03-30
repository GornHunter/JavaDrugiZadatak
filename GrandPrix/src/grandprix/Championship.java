/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandprix;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author GornHunter
 */
public class Championship {
    private ArrayList<Driver> drivers;
    private ArrayList<Venue> venues;
    
    private final int MINOR_MECHANICAL_FAULT = 5;
    private final int MAJOR_MECHANICAL_FAULT = 3;
    private final int UNRECOVERABLE_MECHANICAL_FAULT = 1;
    
    public Championship(){
        this.drivers = loadDrivers("vozaci.txt");
        this.venues = loadVenues("staze.txt");
    }
    
    public ArrayList<Driver> getDrivers(){
        return this.drivers;
    }
    
    public ArrayList<Venue> getVenues(){
        return this.venues;
    }
    
    public int getMinorMechanicalFault(){
        return this.MINOR_MECHANICAL_FAULT;
    }
    
    public int getMajorMechanicalFault(){
        return this.MAJOR_MECHANICAL_FAULT;
    }
    
    public int getUnrecoverableMechanicalFault(){
        return this.UNRECOVERABLE_MECHANICAL_FAULT;
    }
    
    public void prepareForRace(){
        for(var item : this.drivers){
            if(item.getRanking() == 1)
                item.setAccumulatedTime(0);
            else if(item.getRanking() == 2)
                item.setAccumulatedTime(3);
            else if(item.getRanking() == 3)
                item.setAccumulatedTime(5);
            else if(item.getRanking() == 4)
                item.setAccumulatedTime(7);
            else
                item.setAccumulatedTime(10);
            
            item.setEligibleToRace(true);
            item.setPneumatics(Driver.PNEUMATICS.DRY);
        }
    }
    
    public void driveAverageLapTime(int venueTime){
        for(var item : this.drivers){
            if(item.getEligibleToRace() == true)
                item.setAccumulatedTime(item.getAccumulatedTime() + venueTime);
        }
    }
    
    public void applySpecialSkills(int currentLap){
        for(var item : this.drivers){
            if(item.getEligibleToRace() == true)
                item.useSpecialSkill(new RNG(), currentLap);
        }
    }
    
    public void checkMechanicalProblem(int currentLap){
        for(var item : this.drivers){
            if(item.getEligibleToRace() == true){

                RNG r = new RNG(0,100);
                int rng = r.getRandomValue();
                if(rng <= this.getMinorMechanicalFault()){
                    item.setAccumulatedTime(item.getAccumulatedTime() + 20);
                    System.out.println("Krug " + currentLap + " : Bolid koji je vozio " + item.getName() + " je zadesio manji mehanicki kvar sto mu rezultuje dodatkom od 20 sekundi na vreme voznje kruga.");
                    continue;
                }
                
                rng = r.getRandomValue();
                if(rng <= this.getMajorMechanicalFault()){
                    item.setAccumulatedTime(item.getAccumulatedTime() + 120);
                    System.out.println("Krug " + currentLap + " : Bolid koji je vozio " + item.getName() + " je zadesio ozbiljan mehanicki kvar sto mu rezultuje dodatkom od 120 sekundi na vreme voznje kruga.");
                    continue;
                }
                
                rng = r.getRandomValue();
                if(rng <= this.getUnrecoverableMechanicalFault()){
                    item.setEligibleToRace(false);
                    item.setAccumulatedTime(item.getAccumulatedTime() + 20000);
                    System.out.println("Krug " + currentLap + " : Bolid koji je vozio " + item.getName() + " je zadesio neotklonjiv mehanicki kvar tako da se vozac ne moze takmiciti u ovoj trci.");
                }
            }
        }
    }
    
    public void updateRanking(){
        int cnt = 1;
        for(var item : this.drivers){
            if(cnt <= 4)
                item.setRanking(cnt++);
            else
                item.setRanking(cnt);
        }         
    }
    
    public void sleep(long milis){
        try{
            Thread.sleep(milis);
        }
        catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }
    
    public void setPoints(){
        for(var item : this.drivers){
            if(item.getRanking() == 1)
                item.setAccumulatedPoints(item.getAccumulatedPoints() + 8);
            else if(item.getRanking() == 2)
                item.setAccumulatedPoints(item.getAccumulatedPoints() + 5);
            else if(item.getRanking() == 3)
                item.setAccumulatedPoints(item.getAccumulatedPoints() + 3);
            else if(item.getRanking() == 4)
                item.setAccumulatedPoints(item.getAccumulatedPoints() + 1);
        }
    }
    
    public void changePneumatics(int currentLap){
        if(currentLap == 2){
            for(var item : this.drivers){
                RNG rng = new RNG(1,100);
                
                if(rng.getRandomValue() <= 50){
                    if(item.getPneumatics() == Driver.PNEUMATICS.DRY){
                        item.setPneumatics(Driver.PNEUMATICS.RAIN);
                        item.setAccumulatedTime(item.getAccumulatedTime() + 10);
                        System.out.println("Krug " + currentLap + " : Bolidu koji vozi " + item.getName() + " stavljeni su pneumatici za vlazno vreme sto mu rezultuje dodatkom od 10 sekundi na vreme vozenja kruga.");
                    }
                }
            }
        }
    }
    
    public void checkRain(int currentLap, double venueChanceOfRain){
        Random rng = new Random();
        DecimalFormat df = new DecimalFormat("0.00");
        
        if(Double.parseDouble(df.format(rng.nextDouble())) <= venueChanceOfRain){
            System.out.println("Krug " + currentLap + " : Pocela je da pada kisa.");
            for(var item : this.drivers){
                if(item.getPneumatics() == Driver.PNEUMATICS.DRY){
                    item.setAccumulatedTime(item.getAccumulatedTime() + 5);
                    System.out.println("Krug " + currentLap + " : Posto bolid koji vozi " + item.getName() + " ima pneumatike za suvo vreme a pada kisa dodato mu je 5 sekundi na vreme vozenja kruga.");
                }
            }
        }
    }
    
    public void printLeader(int lap){
        System.out.println("Nakon kruga " + lap + " na prvom mestu je " + this.drivers.get(0).getName() + " sa vremenom od " + this.drivers.get(0).getAccumulatedTime() + " sekunde");
    }
    
    public void printWinnersAfterRace(String venueName){
        System.out.println("Na stazi " + venueName + " pobednici su:\n1." + this.drivers.get(0).getName() + "\n2." + this.drivers.get(1).getName() + "\n3." + this.drivers.get(2).getName() + "\n4." + this.drivers.get(3).getName());
    }
    
    public void printChampion(int numOfRaces){
        System.out.println("Sampion nakon " + numOfRaces + " odvozanih trka je " + this.drivers.get(0).getName());
    }
    
    private ArrayList<Driver> loadDrivers(String filename){
        ArrayList<Driver> d = new ArrayList<Driver>();
        
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                String[] data = sc.nextLine().split(",");
                d.add(new Driver(data[0], Integer.parseInt(data[1]), data[2]));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Fajl " + filename + " nije nadjen!");
            e.printStackTrace();
        }
        
        return d;
    }
    
    private ArrayList<Venue> loadVenues(String filename){
        ArrayList<Venue> v = new ArrayList<Venue>();
        
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()){
                String[] data = sc.nextLine().split(",");
                v.add(new Venue(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Double.parseDouble(data[3])));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Fajl " + filename + " nije nadjen!");
            e.printStackTrace();
        }
        
        return v;
    }
}