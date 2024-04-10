package Projects;
import java.util.Arrays;

public class MedianOfMedians {
    public static int findMedian(int[] arr) {
        return findMedianHelper(arr, 0, arr.length - 1, arr.length / 2);
    }

    private static int findMedianHelper(int[] arr, int low, int high, int medianIndex) {
        int pivotIndex = medianOfMedians(arr, low, high);

        if (pivotIndex == medianIndex) {
            return arr[pivotIndex];
        } else if (pivotIndex < medianIndex) {
            return findMedianHelper(arr, pivotIndex + 1, high, medianIndex);
        } else {
            return findMedianHelper(arr, low, pivotIndex - 1, medianIndex);
        }
    }

    private static int medianOfArray(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length / 2];
    }

    private static int medianOfMedians(int[] arr, int low, int high) {
        int n = high - low + 1;

        // Divide the array into groups of size 5
        int numGroups = (n + 4) / 5;
        int[] medians = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            int groupLow = low + i * 5;
            int groupHigh = Math.min(groupLow + 4, high);
            Arrays.sort(arr, groupLow, groupHigh + 1); // sort the group
            int medianIndex = groupLow + (groupHigh - groupLow) / 2;
            medians[i] = arr[medianIndex];
        }

        // Recursively find median of medians
        if (medians.length <= 5) {
            return medianOfArray(medians);
        } else {
            return medianOfMedians(medians, 0, medians.length - 1);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low;
        int j = high;
        while (i < j) {
            while (arr[i] < pivot) {
                i++;
            }
            while (arr[j] > pivot) {
                j--;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }
        return j; // Return the index of the pivot element
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = { 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99,
                30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59,
                70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
                80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 1, 2, 3, 4, 5, 6, 7, 8, 9, 100 };

        int median = findMedian(arr);
        System.out.println("Median = " + median);
    }
}