import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Main {

    public static double Pc = 0.7;
    public static double Pm = 0.1;

    public static int populationSize = 500;

    //Maximum number of generation
    public static int T = 50;

    public static int k = 50; // Mating pool size (Number of selected parents).

    public static ArrayList<Point> points;

    public static Scanner input;

    public static FileWriter fileWriter;

    public static void main(String[] args) throws IOException {
        File inputFile = new File("input.txt");
        fileWriter = new FileWriter("output.txt");
        input = new Scanner(inputFile);
        int tests = Integer.parseInt(input.nextLine()); // Test cases.

        for(int t = 1; t <= tests; t++) {
            String[] str = input.nextLine().split(" ");

            // Taking input.
            int n = Integer.parseInt(str[0]); // Number of points.
            int d = Integer.parseInt(str[1]); // Polynomial Degree.
            d++;
            points = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                String[] str2 = input.nextLine().split(" ");
                Double x = Double.parseDouble(str2[0]);
                Double y = Double.parseDouble(str2[1]);
                points.add(new Point(x, y));
            }

            // Generated population.
            ArrayList<ArrayList<Double>> chromosomes = getPopulation(d);

            // For Loop to the number of generations.
            for(int generationNumber = 0; generationNumber < T; generationNumber++)
            {
                ArrayList<ArrayList<Double>> matingPool = getMatingPool(chromosomes, points);

                // Crossover.
                ArrayList<ArrayList<Double>> offspring = new ArrayList<>();
                for(int i = 0; i < k; i += 2) {
                    ArrayList<Double> parent1 = matingPool.get(i);
                    ArrayList<Double> parent2 = matingPool.get(i + 1);
                    ArrayList<Double> o1 = parent1, o2 = parent2;
                    Random rand = new Random();
                    int Xc1 = rand.nextInt( max(d - 1, 1)); // Position of crossover.
                    int Xc2 = Xc1;
                    while (Xc2 == Xc1)
                        Xc2 = rand.nextInt(max(d - 1, 1));
                    double rc = rand.nextDouble();
                    if (rc <= Pc && n > 1) {
                        if (Xc1 > Xc2) {
                            int tmp = Xc1;
                            Xc1 = Xc2;
                            Xc2 = tmp;
                        }
                        for (int j = Xc1 + 1; j < Xc2; j++) {
                            o1.set(j, parent2.get(j));
                            o2.set(j, parent1.get(j));
                        }
                    }
                    offspring.add(o1);
                    offspring.add(o2);
                }
                ArrayList<ArrayList<Double>> mutatedOffspring = doMutation(offspring, generationNumber);
                replacement(chromosomes, matingPool, mutatedOffspring, points);
            }
            Collections.sort(chromosomes, new compareByFitness());
            double ans = getFitness(chromosomes.get(0), points);
            fileWriter.write("Case " + t + "\n");
            fileWriter.write("Mean Square Error: " + ans + "\n");
            for(int i = 0; i < chromosomes.get(0).size(); i++){
                fileWriter.write(String.valueOf(chromosomes.get(0).get(i)));
            }
            fileWriter.write("\n");
        }
        fileWriter.close();
        input.close();
    }

    /**
     * Function to initialize the population and generates random chromosomes.
     * @param d number of points.
     * @return Array of array of doubles containing all chromosomes.
     */
    public static ArrayList<ArrayList<Double>> getPopulation(int d){
        ArrayList<ArrayList<Double>> chromosomes = new ArrayList<>();
        Random rand = new Random();
        for(int i = 0; i < populationSize; i++){
            ArrayList<Double> chromosome = new ArrayList<>();
            for(int j = 0; j < d; j++){
                Double r = -10.0 + 20.0 * rand.nextDouble();
                chromosome.add(r);
            }
            chromosomes.add(chromosome);
        }
        return chromosomes;
    }

    /**
     * Function that performs tournament selection.
     * @return the mating pool after performing k-tournaments.
     */
    public static ArrayList<ArrayList<Double>> getMatingPool
            (ArrayList<ArrayList<Double>> chromosomes, ArrayList<Point> points)
    {
        ArrayList<ArrayList<Double>> matingPool = new ArrayList<>();

        ArrayList<Integer> notUsed = new ArrayList<>();
        for(int i = 0; i < chromosomes.size(); i++)
            notUsed.add(i);

        // Tournament Selection
        while(matingPool.size() < k)
        {
            Random rand = new Random();
            int pos = rand.nextInt(notUsed.size());
            ArrayList<Double> player1 = chromosomes.get(notUsed.get(pos));
            notUsed.remove(pos);

            pos = rand.nextInt(notUsed.size());
            ArrayList<Double> player2 = chromosomes.get(notUsed.get(pos));
            notUsed.remove(pos);

            Double fitness1 = getFitness(player1, points);
            Double fitness2 = getFitness(player2, points);
            if (fitness1 < fitness2)
                matingPool.add(player1);
            else
                matingPool.add(player2);
        }
        return matingPool;
    }

    /**
     * Gets the fitness of a solution.
     */
    public static Double getFitness(ArrayList<Double> chromosome, ArrayList<Point> points){
        double fitness = 0;
        for(int i = 0; i < points.size(); i++){
            Double x = points.get(i).x, y = points.get(i).y;
            Double tmpX = x;
            double Y_calc = chromosome.get(0);
            for(int j = 1; j < chromosome.size(); j++){
                Y_calc += chromosome.get(j) * tmpX;
                tmpX *= x;
            }
            fitness += (Y_calc - y) * (Y_calc - y);
        }
        return fitness / points.size();
    }


    public static ArrayList<Double> getNonUniFormMutation(ArrayList<Double> chromosome, double generationNumber){
        ArrayList<Double> modifiedChromosome = new ArrayList<>();
        Random random = new Random();
        // double lB = Collections.min(chromosome);
        // double uB = Collections.max(chromosome);

        double lB = -10.0;
        double uB = 10.0;

        for (int i = 0; i < chromosome.size(); i++) {
            double rm = random.nextDouble();
            double gene = chromosome.get(i);

            if(rm <= Pm)
            {
                double ri1 = random.nextDouble();
                double delta = getDelta(lB, uB, ri1, gene);
                // Calculate New gene Value
                // assume b = 1;
                double b =1;
                double r = random.nextDouble();
                double mutationValue = delta * r * (Math.pow(1 - (generationNumber / T), b));
                double modifiedGene = 0.0;

                if(ri1 <= 0.5){
                    modifiedGene = gene - mutationValue;
                }else {
                    modifiedGene = gene + mutationValue;
                }
                modifiedChromosome.add(modifiedGene);
            }
            else {
                modifiedChromosome.add(gene);
            }
        }


        return modifiedChromosome;
    }
    // utility function
    public static double getDelta (double lB, double uB, double ri1, double gene) {
        double delta = 0;
        if (ri1 <= 0.5) {
            delta = uB - gene;
        } else {
            delta = gene - lB;
        }
        return delta;
    }


    public static ArrayList<ArrayList<Double>> doMutation(ArrayList<ArrayList<Double>> offspring, int generationNum) {

        ArrayList<ArrayList<Double>> offspringAfterMutation = new ArrayList<ArrayList<Double>>();

        for (int i = 0; i < offspring.size(); i++) {
            offspringAfterMutation.add(getNonUniFormMutation(offspring.get(i), generationNum));
        }
        return offspringAfterMutation;
    }

    static class compareByFitness implements Comparator<ArrayList<Double>>{

        @Override
        public int compare(ArrayList<Double> o1, ArrayList<Double> o2) {

            double fitness1 = getFitness(o1, points);
            double fitness2 = getFitness(o2, points);
            if(fitness1 < fitness2){
                return -1;
            }
            else if (fitness1 > fitness2){
                return 1;
            }
            return 0;
        }
    }

    public static void replacement
            (ArrayList<ArrayList<Double>> individuals, ArrayList<ArrayList<Double>> parents,
             ArrayList<ArrayList<Double>> mutatedOffspring, ArrayList<Point> points){

        // Removing parents from individuals.
        for(int i = 0; i < k; i++){
            individuals.remove(parents.get(i));
        }

        // Merging parents and offspring in one arraylist and choosing the best k out of them.

        ArrayList<ArrayList<Double>> temp =  new ArrayList<ArrayList<Double>>();

        temp.addAll(parents);
        temp.addAll(mutatedOffspring);

        Collections.sort(temp, new compareByFitness());

        for(int i = 0; i < k; i++)
            individuals.add(temp.get(i));
    }

}
