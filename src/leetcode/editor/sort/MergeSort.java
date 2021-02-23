package leetcode.editor.sort;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 6, 1, 9, 16, 25, 94, 42, 77};
        MergeSort mergeSort = new MergeSort();
        mergeSort.merge(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

    }

    public void merge(int[] nums, int start, int end) {
        int mid = start + ((end - start) >> 1);
        if (start < end) {
            merge(nums, start, mid);
            merge(nums, mid + 1, end);
            mergeSort(nums, start, mid, end);
        }
    }

    public void mergeSort(int[] nums, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int left = start;
        int right = mid + 1;
        int tmpIndex = 0;
        while (left <= mid && right <= end) {
            if (nums[left] < nums[right]) {
                tmp[tmpIndex++] = nums[left++];
            } else {
                tmp[tmpIndex++] = nums[right++];
            }
        }
        while (left <= mid) {
            tmp[tmpIndex++] = nums[left++];
        }
        while (right <= end) {
            tmp[tmpIndex++] = nums[right++];
        }
        for (int i = 0; i < tmp.length; i++) {
            nums[start++] = tmp[i];
        }
    }
}
