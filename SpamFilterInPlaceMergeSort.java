public class SpamFilterInPlaceMergeSort {

    static void inPlaceMerge(int[] arr, int left, int mid, int right) {
        int start2 = mid + 1;

        if (arr[mid] <= arr[start2]) {
            return;
        }

        while (left <= mid && start2 <= right) {
            if (arr[left] <= arr[start2]) {
                left++;
            } else {
                int value = arr[start2];
                int index = start2;

                while (index != left) {
                    arr[index] = arr[index - 1];
                    index--;
                }

                arr[left] = value;

                left++;
                mid++;
                start2++;
            }
        }
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            inPlaceMerge(arr, left, mid, right);
        }
    }

    static void printArray(int[] arr) {
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] featureVectors = {7, 3, 11, 5, 14, 2, 9, 6, 12, 1, 8, 4};

        System.out.println("Spam Filter Feature Vector Sort");
        System.out.println("--------------------------------");

        System.out.print("Input Feature Vectors: ");
        printArray(featureVectors);

        mergeSort(featureVectors, 0, featureVectors.length - 1);

        System.out.print("Sorted Feature Vectors: ");
        printArray(featureVectors);

        System.out.println();
        System.out.println("Algorithm Used: In-Place Merge Sort");
        System.out.println("Time Complexity: O(n log n)");
        System.out.println("Auxiliary Space: O(1)");
    }
}
