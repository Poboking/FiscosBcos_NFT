package org.sziit.infrastructure.common;

import org.sziit.infrastructure.dao.domain.MenuEntity;

import java.util.Comparator;
import java.util.List;

/**
 * @project: a20-nft-3_7
 * @author: poboking
 * @date: 2024/3/24 17:48
 */
public class SortUtil {

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }

    public static <T extends MenuEntity> void quickSort(List<T> menuList, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int pivot = partition(menuList, left, right, comparator);
            quickSort(menuList, left, pivot - 1, comparator);
            quickSort(menuList, pivot + 1, right, comparator);
        }
    }

    private static <T extends MenuEntity> int partition(List<T> menuList, int left, int right, Comparator<T> comparator) {
        MenuEntity pivot = menuList.get(left);
        while (left < right) {
            while (left < right && comparator.compare(menuList.get(right), (T) pivot) >= 0) {
                right--;
            }
            menuList.set(left, menuList.get(right));
            while (left < right && comparator.compare(menuList.get(left), (T) pivot) <= 0) {
                left++;
            }
            menuList.set(right, menuList.get(left));
        }
        menuList.set(left, (T) pivot);
        return left;
    }

    public static <T extends MenuEntity> void defaultQuickSort(List<T> menuList){
        quickSort(menuList, 0, menuList.size() - 1, Comparator.comparing(MenuEntity::getOrderNo));
    }
}
