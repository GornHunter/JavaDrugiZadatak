/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grandprix;

import java.util.Comparator;

/**
 *
 * @author GornHunter
 */
public class DriverPointsComparator implements Comparator<Driver>{
    int direction = 1;
    
    public DriverPointsComparator(int direction){
        if(direction != 1 && direction != -1)
            direction = 1;
        
        this.direction = direction;
    }
    
    @Override
    public int compare(Driver d1, Driver d2){
        int retVal = 0;
        
        if(d1 != null && d2 != null)
            retVal = d2.getAccumulatedPoints() - d1.getAccumulatedPoints();
        
        return retVal * direction;
    }
}
