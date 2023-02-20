import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Main {

    public static double Pc = 0.7;
    public static double Pm = 0.1;

    public static int populationSize = 10;

    public static int k = 1; // Steady-state constant.

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int tests = scan.nextInt(); // Test cases.

        for(int t = 1; t <= tests; t++) {
            // Taking input.
            int c = scan.nextInt(); // Capacity.
            int n = scan.nextInt(); // Number of items.
            ArrayList<Item> items = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                int w = scan.nextInt();
                int v = scan.nextInt();
                items.add(new Item(w, v));
            }

            ArrayList<String> chromosomes = getPopulation(n, c, items);
            for(int i = 0; i < k; i++){
                ArrayList<Integer> fitness = getFitness(chromosomes, items);
                makeOffspring(chromosomes, fitness);
            }

            int best = getBest(chromosomes, c, items);
            int ans = 0;
            for(int i = 0; i < n; i++){
                if (chromosomes.get(best).charAt(i) == '1')
                    ans += items.get(i).value;
            }
            System.out.println("Case " + t + ": " + ans);
            for(int i = 0; i < n; i++){
                if (chromosomes.get(best).charAt(i) == '1')
                    System.out.println(items.get(i).weight + " " + items.get(i).value);
            }
        }
    }

    /**
     * Function to initialize the population and generates random feasible chromosomes.
     * @param n number of items.
     * @param c capacity.
     * @return Array of strings containing all chromosomes.
     */
    public static ArrayList<String> getPopulation(int n, int c, ArrayList<Item> items){
        ArrayList<String> chromosomes = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < populationSize; i++){
            String chromosome = "";
            for(int j = 0; j < n; j++)
                chromosome += (rand.nextInt(2) == 1 ? "1" : "0");
            if (feasibility(chromosome, c, items) > -1)
                chromosomes.add(chromosome);
        }
        return chromosomes;
    }

    /**
     * Function to check if the given chromosome is feasible.
     * @param c capacity
     * @return the fitness of the solution. If unfeasible, it returns -1.
     */
    public static int feasibility(String chromosome, int c, ArrayList<Item> items){
        int n = chromosome.length();
        int totalWeight = 0, totalValue = 0;
        for(int i = 0; i < n; i++){
            if (chromosome.charAt(i) == '1'){
                totalWeight += items.get(i).weight;
                totalValue += items.get(i).value;
            }
            if (totalWeight > c)
                return -1;
        }
        return totalValue;
    }

    /**
     * Gets the cumulative fitness of all solutions.
     */
    public static ArrayList<Integer> getFitness(ArrayList<String> chromosomes, ArrayList<Item> items){
        ArrayList<Integer> fitness = new ArrayList<>();
        fitness.add(0);
        int cumulative = 0;
        for(int i = 0; i < chromosomes.size(); i++){
            String chromosome = chromosomes.get(i);
            int curFitness = 0;
            for(int j = 0; j < chromosome.length(); j++){
                if (chromosome.charAt(j) == '1')
                    curFitness += items.get(j).value;
            }
            cumulative += curFitness;
            fitness.add(cumulative);
        }
        return fitness;
    }

    /**
     * Function that uses mutation and crossover to generate offsprings.
     * These offsprings are replaced by their parents in the next generation.
     */
    public static void makeOffspring(ArrayList<String> chromosomes, ArrayList<Integer> fitness){
        // Choosing first parent.
        Random rand = new Random();
        int n = fitness.size();
        int r1 = rand.nextInt(fitness.get(n - 1));
        int p1 = -1;
        for(int i = 0; i < n - 1 && p1 == -1; i++){
            int l = fitness.get(i), r = fitness.get(i + 1);
            if (l <= r1 && r1 < r)
                p1 = i;
        }

        // Choosing second parent.
        int p2 = p1;
        while(p2 == p1){
            int r2 = rand.nextInt(fitness.get(n - 1));
            for(int i = 0; i < n - 1; i++){
                int l = fitness.get(i), r = fitness.get(i + 1);
                if (l <= r2 && r2 < r)
                    p2 = i;
            }
        }

        // Removing those parents because we're replacing them by their offspring.
        String parent1 = chromosomes.get(p1);
        String parent2 = chromosomes.get(p2);
        chromosomes.remove(parent1);
        chromosomes.remove(parent2);

        // Crossover.
        String o1 = parent1, o2 = parent2;
        n = chromosomes.get(0).length();
        int Xc = rand.nextInt(max(n - 1, 1)); // Position of crossover.
        double rc = rand.nextDouble();
        if (rc <= Pc && n > 1){
            o1 = parent1.substring(0, Xc + 1) + parent2.substring(Xc + 1, n);
            o2 = parent2.substring(0, Xc + 1) + parent1.substring(Xc + 1, n);
        }

        // Mutation.
        String newO1 = "", newO2 = "";
        for(int i = 0; i < o1.length(); i++){
            double r = rand.nextDouble();
            if (r <= Pm)
                newO1 += (o1.charAt(i) == '0' ? "1" : "0");
            else newO1 += o1.charAt(i);
            r = rand.nextDouble();
            if (r <= Pm)
                newO2 += (o2.charAt(i) == '0' ? "1" : "0");
            else newO2 += o2.charAt(i);
        }

        // Adding the new offspring to the next generation.
        chromosomes.add(newO1);
        chromosomes.add(newO2);
    }

    /**
     * Function that returns the index of the best solution.
     */
    public static int getBest(ArrayList<String> chromosomes, int c, ArrayList<Item> items){
        int mx = 0, best = 0;
        for(int i = 0; i < chromosomes.size(); i++){
            int value = feasibility(chromosomes.get(i), c, items);
            if (mx > value){
                mx = value;
                best = i;
            }
        }
        return best;
    }
}
