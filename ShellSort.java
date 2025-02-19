// import java.util.Scanner;
public class ShellSort {

    public static int[] shellSort(MyObj[] arr, int code) {

        int hlist[] = createHlist(arr.length, code);    //create hlist
        arr[0].reset();     //set the comparison count to zero
        
        //Shell sort
        for(int gap : hlist){
            for(int i = gap; i < arr.length; i++){
                MyObj temp = arr[i];
                int j = i;

                while(j >= gap && temp.lessThan(arr[j-gap])){
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
        return hlist;   //return the hlist for whatever reason
    }

    public static int[] createHlist(int n, int code) {
        int hlist[];
        int k, size;

        switch (code) {

            // plain insertion sort
            case 0:
                hlist = new int[]{1};
                break;

            // 2^k - 1 sequence
            case 1:
                //determining hlist size
                k = 1;
                size = 0;
                while ((int) Math.pow(2, k) - 1 < n) {
                    size++;
                    k++;
                }
                hlist = new int[size];

                //determing hlist values
                k = size;
                for (int i = 0; i < size; i++) {
                    hlist[i] = (int) Math.pow(2, k) - 1;
                    k--;
                }
                break;

            // (3^k - 1) / 2 sequence
            case 2:
                //determining hlist size
                size = 0;
                k = 1;
                while ((Math.pow(3, k) - 1) / 2 < n) {
                    size++;
                    k++;
                }
                hlist = new int[size];
                                
                //determing hlist values
                k = size;
                for (int i = 0; i < size; i++) {
                    hlist[i] = (int) ((Math.pow(3, k) - 1) / 2);
                    k--;
                }
                break;
            
            // 2-3-6 multiplication sequence
            case 3:
                int temp[] = new int[100];  //temp[] will store potential hlists until the largest one is found
                temp[0] = 1;
                size = 1;
                int lastValue = 1; //keeps track of the last generated number for multiplication

                //multiply the last value by 2,3 and 6 to create new gaps until the "smallest gap" is too large
                while (true) {
                    int next2 = lastValue * 2;
                    int next3 = lastValue * 3;
                    int next6 = lastValue * 6;

                    // add valid values to temp[]
                    if(next2 < n){
                        temp[size++] = next2;
                    }else{
                        break;   //stop if the smallest gap is too large
                    }
                    if (next3 < n){
                        temp[size++] = next3;
                    } 
                    if (next6 < n){
                        temp[size++] = next6;
                    } 

                    // pick the largest valid value for next iteration
                    if (next6 < n) {
                        lastValue = next6;
                    } else if (next3 < n) {
                        lastValue = next3;
                    } else {
                        lastValue = next2;
                    }
                }

                // reverse temp[] and store it in the final hlist[] in descending order
                hlist = new int[size];
                for (int i = 0; i < size; i++) {
                    hlist[i] = temp[size - 1 - i];
                }
                break;

            default:
                System.err.println("Code must be a number between 0-3.\nExiting program.");
                System.exit(1);
                return null;
        }

        return hlist;
    }
}
