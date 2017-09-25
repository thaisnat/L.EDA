package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && leftIndex < rightIndex && leftIndex >= 0 && rightIndex < array.length
				&& array.length != 0) {
			int middle = ((rightIndex + leftIndex) / 2);

			sort(array, leftIndex, middle);
			sort(array, middle + 1, rightIndex);

			merge(array, leftIndex, middle, rightIndex);
		}
	}

	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {
		@SuppressWarnings("unchecked")
		T[] helper = (T[]) new Comparable[array.length];
		int c, i, j, k;

		for (c = leftIndex; c <= rightIndex; c++) {
			helper[c] = array[c];
		}

		i = leftIndex;
		j = middle + 1;
		k = leftIndex;

		while (i <= middle && j <= rightIndex) {
			if (helper[i].compareTo(helper[j]) < 0) {
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			array[k++] = helper[i++];
		}
		while (j <= rightIndex) {
			array[k++] = helper[j++];
		}
	}
}
