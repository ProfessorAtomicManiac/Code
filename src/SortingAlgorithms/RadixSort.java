package SortingAlgorithms;

import java.util.LinkedList;

public class RadixSort {

    // Does consider negative numbers
    public static double[] bucketSort(double[] arr, int digit)
    {
        // Range is [-9, 9]
        @SuppressWarnings("unchecked")
        LinkedList<Double>[] buckets = new LinkedList[19];

        for (int i = 0; i < buckets.length; i++)
            buckets[i] = new LinkedList<>();

        for (double num : arr) {
            buckets[(int) (getDigit(num, digit) + 9)].add(num);
        }

        /* Technically, sorting isn't required if the digits are just the same as the bucket index
         * This would only be used if the number has decimals (which is why the input array is a double array)
         * There is also a step in bucket sort where you multiply each value by how many buckets there are, but
         * I did not code that part in because it is unnecessary in this case, like how insertion sort is
         * unnecessary if you only input integer values
         */
        for (LinkedList<Double> bucket : buckets) {
            insertionSort(bucket, digit);
        }

        int index = 0;
        for (LinkedList<Double> bucket : buckets) {
            for (Double aDouble : bucket) {
                arr[index] = aDouble;
                ++index;
            }
        }
        return arr;
    }

    public static void insertionSort(LinkedList<Double> arr, int digit)
    {
        for (int i = 1; i < arr.size(); i++) {
            for (int k = i-1; k >= 0; k--)
            {
                if (getDigit(arr.get(i), digit) < getDigit(arr.get(k), digit))
                {
                    double tempNum = arr.get(k);
                    arr.set(k, arr.get(i));
                    arr.set(i, tempNum);
                }
            }
        }
    }

    public static double[] radixSortBucket(double[] arr)
    {
        double maxValue = 0;
        for (double num : arr)
        {
            if (num > maxValue)
                maxValue = num;
        }

        // We assume that the digits are > 0 for doubles
        int maxDigit = 1;
        int digitValue = 10;
        while (maxValue / digitValue > 0)
        {
            digitValue*=10;
            ++maxDigit;
        }

        for (int i = 0; i < maxDigit; i++)
        {
            bucketSort(arr, i);
        }
        return arr;
    }

    // I also tried counting sort in replacement of bucket sort since it is another compatible stable sort
    // Since you are sorting integers I find it easier just to use counting sort than bucket sort
    public static int[] countingSort(int[] arr, int digit)
    {
        int[] result = new int[arr.length];

        // Range for a digit is [-9, 9]
        int[] count = new int[19];

        for (int num : arr) {
            count[getDigit(num, digit)+9]++;
        }

        for (int i = 1; i < count.length; i++)
        {
            count[i] += count[i-1];
        }
        for (int i = arr.length-1; i >= 0; i--) {
            int index = getDigit(arr[i], digit);
            result[count[index+9] - 1] = arr[i];
            --count[index+9];
        }
        return result;
    }

    public static int[] radixSortCounting(int[] arr)
    {
        // Finding max digit
        int maxValue = 0;
        for (int num : arr)
        {
            if (num > maxValue)
                maxValue = num;
        }
        int maxDigit = 1;
        int digitValue = 10;
        while (maxValue / digitValue > 0)
        {
            digitValue*=10;
            ++maxDigit;
        }

        // Iterate through each digit
        for (int i = 0; i < maxDigit; i++)
        {
            arr = countingSort(arr, i);
        }
        return arr;
    }

    // Misc. methods
    public static int getDigit(int num, int digit)
    {
        // value = (num / (10^digit)) % 10^(digit)
        // 0 is units digit, 1 is tens digit, etc

        if (digit == 0)
        {
            return num%(10*(digit+1));
        } else {
            int digitValue = 1;
            for (int i = 0; i < digit; i++)
                digitValue *= 10;
            return (num/(digitValue))%(10);
        }
    }

    public static double getDigit(double num, int digit)
    {
        // value = (num / (10^digit)) % 10^(digit)
        // 0 is units digit, 1 is tens digit, etc

        if (digit == 0)
        {
            return num%(10*(digit+1));
        } else {
            int digitValue = 1;
            for (int i = 0; i < digit; i++)
                digitValue *= 10;
            return (num/(digitValue))%(10);
        }
    }

    public static void printArray(int[] result)
    {
        System.out.print("[");
        for (int i = 0; i < result.length; i++)
        {
            System.out.print(result[i]);
            if (i != result.length-1){
                System.out.print(',');
            }
        }
        System.out.println("]");
    }

    public static void printArray(double[] result)
    {
        System.out.print("[");
        for (int i = 0; i < result.length; i++)
        {
            System.out.print(result[i]);
            if (i != result.length-1){
                System.out.print(',');
            }
        }
        System.out.println("]");
    }

    // Testing
    public static void main(String[] args)
    {
        int[] arr1 = {-234, -65546, 423, -5, 3, 48, -38, -23, 3};
        //arr1 = radixSortCounting(arr1);
        //printArray(arr1);

        double[] arr2 = {-234.34, -65546.329, 423.587, -5.234, 3.21, 48.432, -38.45, -23.234, 3.12};
        double[] arr3 = {1.2321, 1.235, -1.232, -1.8743, 1.9};
        //arr2 = radixSortBucket(arr2);
        //printArray(arr2);
        radixSortBucket(arr3);
        printArray(arr3);
    }
}
