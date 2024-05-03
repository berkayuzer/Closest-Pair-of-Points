import java.awt.geom.Point2D;

public class QuickSort {


    /**
     * Default Contructor
     */
    public QuickSort() {
        //Empty constructor --- do nothing
    }

    /**
     * The main function that implements QuickSort
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @param orderBy    --> compareX or compareY
     *                   compareX: sort minimum to maximum arr[] by X point
     *                   compareY: sort minimum to maximum arr[] by Y point
     */
    public void sort(Point2D.Double[] arr, int startIndex, int lastIndex, String orderBy) {
        if(startIndex < lastIndex) {
            int pivotIndex;
            if (orderBy.equals("compareX")) {
                pivotIndex = partitionX(arr, startIndex, lastIndex);
            }
            else {
                pivotIndex = partitionY(arr, startIndex, lastIndex);
            }

            sort(arr, startIndex, pivotIndex - 1, orderBy);
            sort(arr, pivotIndex + 1, lastIndex, orderBy);
        }

    }

    /**
     * A utility function to swap two elements
     *
     * @param arr --> Array to be sorted
     * @param i   --> first index
     * @param j   --> second index
     */
    private void swap(Point2D.Double[] arr, int i, int j) {
        Point2D.Double temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Get Median of 3 order by Point.X
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianX(Point2D.Double[] arr, int left, int right) {
        int centerX = (left + right) / 2;

        if(arr[left].getX() > arr[centerX].getX()) swap(arr, left, centerX);
        if(arr[left].getX() > arr[right].getX()) swap(arr, left, right);
        if(arr[centerX].getX() > arr[right].getX()) swap(arr, centerX, right);

        swap(arr, centerX, right - 1);

        return arr[right - 1];
    }

    /**
     * Get Median of 3 order by Point.Y
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianY(Point2D.Double[] arr, int left, int right) {
        int centerY = (left + right) / 2;

        if(arr[left].getY() > arr[centerY].getY()) {
            swap(arr, left, centerY);
        }
        if(arr[left].getY() > arr[right].getY()) {
            swap(arr, left, right);
        }
        if(arr[centerY].getY() > arr[right].getY()) {
            swap(arr, centerY, right);
        }

        swap(arr, centerY, right - 1);

        return arr[right - 1];
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.X
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionX(Point2D.Double[] arr, int startIndex, int lastIndex) {
        Point2D.Double pivotX = getMedianX(arr, startIndex, lastIndex);
        int left = startIndex;
        int right = lastIndex;

        while (left < right) {
            while (arr[left].getX() < pivotX.getX()) {
                left++;
            }
            while (arr[right].getX() > pivotX.getX()) {
                right--;
            }
            if (left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        swap(arr,right,lastIndex - 1);

        return left;
    }

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.Y
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionY(Point2D.Double[] arr, int startIndex, int lastIndex) {
        Point2D.Double pivotY = getMedianY(arr, startIndex, lastIndex);
        int left = startIndex;
        int right = lastIndex;

        while (left < right) {
            while (arr[left].getY() < pivotY.getY()) {
                left++;
            }
            while (arr[right].getY() > pivotY.getY()) {
                right--;
            }
            if (left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }

        swap(arr,right,lastIndex - 1);

        return left;
    }

}
