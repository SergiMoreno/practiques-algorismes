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
    
    public MinPairs(PointsPair a, PointsPair b, PointsPair c) {
        list = new PointsPair[3];
        n = 3;
        list[0] = a;
        list[1] = b;
        list[2] = c;
        Arrays.sort(list);
    }
    
    public void checkPoint(PointsPair p) {
        for (int i = 0; i < list.length; i++) {
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
