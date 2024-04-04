package Projects;

 public class insertSort {
     public static void main(String[] args){
         int[] arr = {1, 2, 5, 6, 7, 8, 4, 3, 9};

         InsertSort(arr);

         for (int i : arr) {
             System.out.println(i + " ");
         }
     }

     public static void InsertSort(int[] arr) {
         for (int i = 1; i < arr.length; i++) {
             int temp = arr[i];
             int j = i - 1;

             while (j >= 0 && arr[j] > temp) {
                 arr[j + 1] = arr[j];
                 j--;
             }

             arr[j + 1] = temp;
         }
     }
 }