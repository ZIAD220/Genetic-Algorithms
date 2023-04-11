import java.util.ArrayList;
import java.util.HashMap;

public class FuzzyRule {

    double CalculateOperationValue(Double lvalue, Double rValue, String operation) {

        if (operation.equals("and")) {
            return Math.min(lvalue, rValue);
        } else if (operation.equals("and_not")) {
            return Math.min(lvalue, 1 - rValue);
        } else if (operation.equals("or")) {
            return Math.max(lvalue, rValue);
        } else if (operation.equals("or_not")) {
            return Math.max(lvalue, 1 - rValue);
        }
        return 0;
    }

    void setRuleValue(String rule, HashMap<String, Variable> map) {

        String[] tokens = rule.split(" ");
        String variableName;
        String fuzzySet;
        String operator;
        ArrayList<Double> membershipValue = new ArrayList<>();
        ArrayList<String> operations = new ArrayList<>();

        for (int i = 0; i < tokens.length; i += 3) {
            variableName = tokens[i];
            fuzzySet = tokens[i + 1];
            operator = tokens[i + 2];

            ArrayList<FuzzySet> fuzzySets = map.get(variableName).getFuzzySets();

            for (FuzzySet fs : fuzzySets) {
                if (fs.name.equals(fuzzySet)) {
                    membershipValue.add(fs.memValue);
                    break;
                }
            }

            if (operator.equals("=>"))
                break;
            operations.add(operator);
        }

        double cumm = CalculateOperationValue(membershipValue.get(0), membershipValue.get(1), operations.get(0));

        for (int i = 1; i < operations.size(); ++i) {
            System.out.println(cumm);
            cumm = CalculateOperationValue(cumm, membershipValue.get(i + 1), operations.get(i));
        }

        String consequentClass = tokens[tokens.length - 1];
        String consequentVariable = tokens[tokens.length - 2];

        ArrayList<FuzzySet> fuzzySets = map.get(consequentVariable).getFuzzySets();

        for (FuzzySet fs : fuzzySets) {
            if (fs.name.equals(consequentClass)) {
                fs.memValue = cumm;
                break;
            }

        }

    }

    void InputScan(String inputLines , HashMap<String, Variable> map){
        String[] lines = inputLines.split("\n");

        for (String line : lines){
            setRuleValue(line , map);
        }

    }
}
