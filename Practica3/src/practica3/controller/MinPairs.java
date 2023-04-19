package practica3.controller;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author usuario
 */
public class MinPairs {
    private PointsPair [] list;
    private int n;
    
    public MinPairs(int k) {
        list = new PointsPair[k];
        n = 0;
    }
    
    // Method to fill the list with max distances
    public void fill() {
        for (int i = n; i < list.length; i++) {
            list[i] = PointsPair.maxDistance();
        }
        n = list.length;
    }
    
    // Method to check if a point must be inserted
    public void checkPoint(PointsPair p) {
        // check if the point is not already in the object
        for (int i = 0; i < n; i++) {
            if (p.equals(list[i])) return;
        }
        
        if (n < list.length) {
            list[n] = p;
            n++;
            if (n == list.length) Arrays.sort(list);
        } else if (list[0].getDistance() > p.getDistance()) {
            list[0] = p;
            Arrays.sort(list);
        }
    }
    
    public PointsPair getPointPair(int index) {
        return this.list[index];
    }
    
    public ArrayList<Integer> getIndexs() {
        ArrayList<Integer> solution = new ArrayList<Integer>();
        for(int i = 0; i < n; i++) {
            PointsPair p = list[i];
            solution.add(p.refPoint1);
            solution.add(p.refPoint2);
        }
        
        return solution;
    }
    
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < n; i++) {
            result += "\nPair " + i + " : " + list[i].toString();
        }
        return result;
    }
}
