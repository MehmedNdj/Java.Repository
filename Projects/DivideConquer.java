package Projects;
public class DivideConquer {

    public static int findMedian(int[] arr) {
        return findMedianHelper(arr, 0, arr.length - 1);
    }

    private static int findMedianHelper(int[] arr, int low, int high) {
        if (low == high) {
            return arr[low];
        }

        int pivot = arr[low];

        // Swap pivot to the beginning
        swap(arr, low, low + (high - low) / 2); // Choose a pivot in the middle to prevent worst-case scenarios

        int partitionIndex = partition(arr, low, high);

        if (partitionIndex - low == (high - low) / 2) {
            return arr[partitionIndex];
        } else if (partitionIndex - low < (high - low) / 2) {
            // Median is in the right sub-array
            return findMedianHelper(arr, partitionIndex + 1, high);
        } else {
            // Median is in the left sub-array
            return findMedianHelper(arr, low, partitionIndex - 1);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int left = low + 1; // Start from the element next to the pivot
        int right = high;

        while (left <= right) {
            while (left <= right && arr[left] <= pivot) {
                left++;
            }

            while (left <= right && arr[right] >= pivot) {
                right--;
            }

            if (left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        swap(arr, low, right);
        return right;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        int median = findMedian(arr);
        System.out.println("Median = " + median);
    }
}

