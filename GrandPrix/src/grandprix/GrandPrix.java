/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandprix;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 *
 * @author GornHunter
 */
public class GrandPrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sc = new Scanner(System.in);
        Championship c = new Championship();
        int maxBrojStaza = 0;
        int staza;
        int trenutniBrojStaza = 1;
        
        //ArrayList<Driver> drivers = c.getDrivers();
        //ArrayList<Venue> venues = c.getVenues();
        
        //RNG r = new RNG(1, 50);
        
        //for(var d : c.getDrivers()){
        //    d.setAccumulatedTime(r.getRandomValue());
        //    d.setAccumulatedPoints(r.getRandomValue());
        //}
        //Collections.sort(c.getDrivers(), new DriverPointsComparator(1));
        //Collections.sort(c.getDrivers(), new DriverTimeComparator(1));
        
        //c.getDrivers().forEach(data -> System.out.println("Name: " + data.getName() + "\nRanking: " + data.getRanking() + "\nSpecial skill: " + data.getSpecialSkill() + "\nAccumulated time: " + data.getAccumulatedTime() + "\nAccumulated points: " + data.getAccumulatedPoints() + "\n----------------------------------"));
        
        do{
            System.out.println("Koliko trka zelite da odrzite?");
            maxBrojStaza = Integer.parseInt(sc.nextLine());
            if(maxBrojStaza >= 3 && maxBrojStaza <= 5)
                break;
            
            System.out.println("Broj trka mora biti izmedju 3 i 5!\n------------------------------");
        }while(maxBrojStaza < 3 || maxBrojStaza > 5);
        
        while(trenutniBrojStaza <= maxBrojStaza){
            System.out.println("\nIzaberite stazu na kojoj ce se takmiciti voazaci");
            c.getVenues().forEach(new Consumer<Venue>() {
                int cnt = 1;
                @Override
                public void accept(Venue data) {
                    System.out.println(cnt++ + "." + data.getVenueName());
                }
            });
            System.out.println("");
            staza = Integer.parseInt(sc.nextLine());
            Venue trenutnaStaza = c.getVenues().get(staza - 1);
            c.prepareForRace();
            
            int trenutniKrug = 1;
            
            while(trenutniKrug <= trenutnaStaza.getNumberOfLaps()){
                System.out.println(
                        "**************\n"
                        + "*   KRUG " + trenutniKrug + "   *\n"
                        + "**************");
                c.changePneumatics(trenutniKrug);
                c.checkRain(trenutniKrug, trenutnaStaza.getChanceOfRain());
                c.driveAverageLapTime(trenutnaStaza.getAverageLapTime());
                c.applySpecialSkills(trenutniKrug);
                c.checkMechanicalProblem(trenutniKrug);
                
                Collections.sort(c.getDrivers(), new DriverTimeComparator(1));
                
                System.out.println("------------------------------------------");
                c.printLeader(trenutniKrug);
                trenutniKrug++;
                c.sleep(1000);
                System.out.println("------------------------------------------");
            }
            
            System.out.println("------------------------------------------");
            Collections.sort(c.getDrivers(), new DriverTimeComparator(1));
            c.updateRanking();
            c.setPoints();
            c.printWinnersAfterRace(trenutnaStaza.getVenueName());
            System.out.println("");
            c.getVenues().remove(staza - 1);
            trenutniBrojStaza++;
        }
        
        Collections.sort(c.getDrivers(), new DriverPointsComparator(1));
        c.updateRanking();
        c.printChampion(maxBrojStaza);
    }
}