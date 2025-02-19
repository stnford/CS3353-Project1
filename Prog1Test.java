public class Prog1Test {

    public static void main(String[] args) {
        final int ARRAY_SIZE = 10;

        // Store the original array for resetting between sorts
        MyObj[] originalArr = new MyObj[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            originalArr[i] = new MyObj();
        }

        // Print original array
        System.out.println("\n----------------------------\nOriginal Array:");
        for (MyObj obj : originalArr) {
            obj.print();
        }
        System.out.println("----------------------------\n");

        // Test for each code (0-3)
        for (int code = 0; code <= 3; code++) {
            // Reset the array to the original unsorted version
            MyObj[] arr = new MyObj[ARRAY_SIZE];
            for (int i = 0; i < ARRAY_SIZE; i++) {
                int[] values = originalArr[i].a; // Assuming `a` is accessible or create a getter
                arr[i] = new MyObj(values[0], values[1], values[2]);
            }

            System.out.println("Sorting with code: " + code);
            arr[0].reset();  // Reset comparison count

            // Apply ShellSort
            int[] hlist = ShellSort.shellSort(arr, code);

            // Print sorted array
            for (MyObj obj : arr) {
                obj.print();

            }
            System.out.println();

            // Print hlist used
            System.out.print("hlist: ");
            for (int h : hlist) {
                System.out.print(h + " ");
            }
            System.out.println();

            // Print comparison count
            System.out.println("Comparisons: " + arr[0].getCount());
            System.out.println("----------------------------");
        }
    }
}