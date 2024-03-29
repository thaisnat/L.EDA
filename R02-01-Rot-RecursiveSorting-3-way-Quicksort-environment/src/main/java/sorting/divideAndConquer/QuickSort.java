package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length
				&& array.length != 0) {

			int pos_pivot = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, pos_pivot - 1);
			sort(array, pos_pivot + 1, rightIndex);
		}
	}

	public int partition(T[] array, int leftIndex, int rightIndex) {
		T pivot;
		int i, j;

		pivot = array[leftIndex];
		i = leftIndex + 1;
		j = rightIndex;

		while (i <= j) {
			if (array[i].compareTo(pivot) <= 0) {
				i++;
			} else if (pivot.compareTo(array[j]) < 0) {
				j--;
			} else {
				Util.swap(array, i, j);
				i++;
				j--;
			}
		}
		array[leftIndex] = array[j];
		array[j] = pivot;

		return j;
	}
}