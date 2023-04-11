import java.util.ArrayList;
import java.util.Collections;

public class Defuzzifier {
    Variable out;
    
    public Defuzzifier()
    {
    	
    }
    public Defuzzifier(Variable out) {
        this.out = out;
    }

    public Variable getOut() {
        return out;
    }

    public void setOut(Variable out) {
        this.out = out;
    }

    /**
     * Function that returns predicted value using weighted-average method.
     */
    public String getPrediction(){

        ArrayList<FuzzySet> fuzzySets = out.getFuzzySets();
        System.out.println();
        double up = 0; // Numerator.
        double down = 0; // Denominator.

        // Calculating centroid values.
        for(int i = 0; i < fuzzySets.size(); i++){
            FuzzySet fs = fuzzySets.get(i);
            ArrayList<Pair<Integer, Integer>> points = fs.getPoints();

            double centroid = 0;
            for (Pair<Integer, Integer> point : points) centroid += point.first;
            centroid /= points.size();
            up += centroid * fs.memValue;
            down += fs.memValue;
        }

        double crisp = up / down; // (Z*)

        // Getting maximum membership value.
        double max = 0;
        String max_set = "";
        for(FuzzySet fs : fuzzySets){
            if (fs.memValue > max){
                max = fs.memValue;
                max_set = fs.getName();
            }
        }
        
        String res = "The predicted " + out.name + " is " + max_set + " (" + crisp + ")";
        System.out.println(res);
        
        return res;
    }

}
