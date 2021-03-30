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
public class DriverTimeComparator implements Comparator<Driver>{
    int direction = 1;
    
    public DriverTimeComparator(int direction){
        if(direction != 1 && direction != -1)
            direction = 1;
        
        this.direction = direction;
    }
    
    @Override
    public int compare(Driver d1, Driver d2){
        int retVal = 0;
        
        if(d1 != null && d2 != null)
            retVal = d1.getAccumulatedTime() - d2.getAccumulatedTime();
        
        return retVal * direction;
    }
}
