package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (array == null || leftIndex < 0 || rightIndex > array.length || leftIndex > rightIndex) {
			return;
		}

		for (int i = leftIndex; i < rightIndex + 1; i++) {
			for (int j = i; j < rightIndex + 1; j++) {
				if (array[i].compareTo(array[j]) >= 1) {
					Util.swap(array, i, j);
				}
			}
		}
	}
}