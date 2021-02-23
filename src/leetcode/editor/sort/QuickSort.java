package leetcode.editor.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 6, 1, 9, 16, 25, 94, 42, 77};
        QuickSort quickSort = new QuickSort();
        quickSort.quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    public void quickSort(int[] nums, int start, int end) {
        if (end > start) {
            int mid = quickSort2(nums, start, end);
            quickSort(nums, start, mid - 1);
            quickSort(nums, mid + 1, end);
        }
    }

    public int quickSort2(int[] nums, int start, int end) {
        int basic = nums[start];
        while (end > start) {
            while (end > start && nums[end] >= basic) {
                end--;
            }
            if (end > start) {
                int tmp = nums[end];
                nums[end] = nums[start];
                nums[start] = tmp;
                start++;
            }

            while (end > start && nums[start] <= basic) {
                start++;
            }
            if (end > start) {
                int tmp = nums[end];
                nums[end] = nums[start];
                nums[start] = tmp;
                end--;
            }
        }
        return end;
    }
}
