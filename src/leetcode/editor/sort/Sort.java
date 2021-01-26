package leetcode.editor.sort;

import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 6, 1, 9, 16, 25, 94, 42, 77};
        Sort sort = new Sort();
//        sort.bubbleSort(nums);
//        sort.quickSort(nums);
        sort.mergeSort(nums, 0, nums.length-1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * @param nums 待排序数组
     * 比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 针对所有的元素重复以上的步骤，除了最后一个；
     * 重复步骤1~3，直到排序完成。
     */
    public void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                exchange(nums, j, j + 1);
            }
        }
        
    }

    /**
     * @param nums 待排序数组
     * n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。具体算法描述如下：
     *
     * 初始状态：无序区为R[1..n]，有序区为空；
     * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     * n-1趟结束，数组有序化了
     */
    public void selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            int tem = nums[i];
            nums[i] = nums[min];
            nums[min] = tem;
        }
    }

    /**
     * @param nums 待排序数组
     *
     * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
     *
     * 从第一个元素开始，该元素可以认为已经被排序；
     * 取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 将新元素插入到该位置后；
     * 重复步骤2~5。
     */
    public void insertSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {

        }
    }
    public void exchange(int[] nums, int i, int j) {
        if (nums[i] > nums[j]) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
    }

    public void mergeSort(int[] nums, int start, int end) {
        int mid = (start + end) / 2;
        if (start < end) {
            mergeSort(nums, start, mid);
            mergeSort(nums, mid+1, end);
            mergeSort(nums, start, mid, end);
        }
    }

    public void mergeSort(int[] nums, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int l = start;
        int r = mid + 1;
        int k = 0;
        while (l <= mid && r <= end) {
            if (nums[l] < nums[r]) {
                temp[k++] = nums[l++];
            } else {
                temp[k++] = nums[r++];
            }
        }
        while (l <= mid) {
            temp[k++] = nums[l++];
        }
        while (r <= end) {
            temp[k++] = nums[r++];
        }
        for (int i = 0; i < temp.length; i++) {
            nums[start + i] = temp[i];
        }
    }


    /**
     * 将数组的某一段元素进行划分，小的在左边，大的在右边
     * @param a
     * @param start
     * @param end
     * @return
     */
    public static int divide(int[] a, int start, int end){
        //每次都以最右边的元素作为基准值
        int base = a[end];
        //start一旦等于end，就说明左右两个指针合并到了同一位置，可以结束此轮循环。
        while(start < end){
            while(start < end && a[start] <= base)
                //从左边开始遍历，如果比基准值小，就继续向右走
                start++;
            //上面的while循环结束时，就说明当前的a[start]的值比基准值大，应与基准值进行交换
            if(start < end){
                //交换
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值右边)，因此右边也要同时向前移动一位
                end--;
            }
            while(start < end && a[end] >= base)
                //从右边开始遍历，如果比基准值大，就继续向左走
                end--;
            //上面的while循环结束时，就说明当前的a[end]的值比基准值小，应与基准值进行交换
            if(start < end){
                //交换
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
                start++;
            }

        }
        //这里返回start或者end皆可，此时的start和end都为基准值所在的位置
        return end;
    }

    /**
     * 排序
     * @param a
     * @param start
     * @param end
     */
    public static void sort(int[] a, int start, int end){
        if(start > end){
            //如果只有一个元素，就不用再排下去了
            return;
        }
        else{
            //如果不止一个元素，继续划分两边递归排序下去
            int partition = divide(a, start, end);
            sort(a, start, partition-1);
            sort(a, partition+1, end);
        }

    }

    /**
     * 归并
     * 递归左右数组排序，再合并两个有序数组，二叉树的后续便利。
     *
     * 快排
     * 先找基准点，再左右排序，二叉树的前序便利
     * @param nums
     */
    public void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int start, int end) {
        if (end > start) {
            int mid = quickSortDivide(nums, start, end);
            quickSort(nums, start, mid - 1);
            quickSort(nums, mid + 1, end);
        }
    }

    public int quickSortDivide(int[] a, int start, int end) {
        int base = a[start];
        while (start < end) {
            while (start < end && a[end] >= base) {
                end--;
            }
            if (start < end) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                start++;
            }
            while (start < end && a[start] <= base) {
                start++;
            }
            if (start < end) {
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                end--;
            }
        }
        return end;
    }
}
