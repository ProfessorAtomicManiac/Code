package SortingAlgorithms;

public class QuickSort {

    // Implement quick-sort method.
    // begin points to start of what you want to sort. end points
    // to the element after what you want to sort

    // Originally I had 3 arraylists to store all the values less than, equal to, and greater than the pivot
    // but I found this https://www.geeksforgeeks.org/quick-sort/ so I remodeled my code after their explanation
    public static void quickSort(int[] arr, int begin, int end) {
        // base case
        if (begin < end) {
            // pivot set to end
            int pivot = end-1;
            // the index of the next position of the lowest number
            int low = begin;

            // iterate through all elements
            for (int i = begin; i < end; i++) {

                if (arr[i] < arr[pivot]) {

                    swap(arr, i, low);
                    low++;
                }
            }
            swap(arr, low, pivot);

            // Sort the low partition
            quickSort(arr, begin, low);
            // Sort the high partition
            quickSort(arr, low+1, end);
        }
    }

    public static void swap(int[] arr, int a, int b) {
        int tempValue = arr[a];
        arr[a] = arr[b];
        arr[b] = tempValue;
    }

    public static void main(String[] args) {
        //int[] arr = {34, 2, 1, 1, -1, 234, 284, -2349, 38, 28283};
        int[] arr = {8, 6, 4, 9, 7, 5, 6, 6, 6, 9};
        quickSort(arr, 0, arr.length);
        for (int j : arr) {
            System.out.print(j + ", ");
        }
    }
}
