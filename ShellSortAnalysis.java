import java.io.FileWriter;
import java.io.IOException;

public class ShellSortAnalysis {
    public static void main(String[] args) {

        int[] arraySizes = {1000, 3000, 5000, 7000, 9000, 11000, 13000, 15000};     

        try (FileWriter csv = new FileWriter("ShellSort_Results.csv")) {
            
            csv.append("Array Size,Code,Average Comparisons,Standard Deviation\n");     //column headers

            for (int n : arraySizes) {  // For each array size
                for (int code = 0; code <= 3; code++) {  // Run each hlist code 
                    
                    int[] comparisons = new int[100];   //Save the number of compatisons for each code

                    for (int i = 0; i < 100; i++) {    //And do it 100 times
                        MyObj[] arr = generateArray(n);     //generate an array of array size n (outer loop)
                        arr[0].reset();     //reset the number of compatisons to 0. compareCount in MyObj is static so arr[0] will work as well as arr[i] just not as misleading
                        ShellSort.shellSort(arr, code);
                        comparisons[i] = arr[0].getCount();
                    }

                    double avg = calcAvg(comparisons);
                    double stdev = calcStdev(comparisons, avg);

                    // output to cvs file
                    csv.append(n + "," + code + "," + String.format("%.3f", avg) + "," + String.format("%.3f", stdev) + "\n");
                }
            }

            System.out.println("Analysis complete. Results saved to ShellSort_Results.csv in the current project folder.");
        } catch (IOException e) {
            e.printStackTrace();    //if there is an error generating the cvs file it will be reported here for debugging
        }
    }

    public static MyObj[] generateArray(int size) {
        MyObj[] arr = new MyObj[size];
        for (int i = 0; i < size; i++) {
            arr[i] = new MyObj();
        }
        return arr;
    }

    public static double calcAvg(int[] data) {
        long sum = 0;
        for (int num : data) {
            sum += num;
        }
        return (double) sum / data.length;
    }
    
    public static double calcStdev(int[] data, double avg) {
        double sum = 0;
        for (int num : data) {
            sum += Math.pow(num - avg, 2);
        }
        return Math.sqrt(sum / (data.length - 1));  // sample stdev instead of population
    }
}