package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex > leftIndex && leftIndex >= 0 && rightIndex < array.length) {
			int pos = leftIndex + 1;
			while (pos <= rightIndex) {
				if (array[pos].compareTo(array[pos - 1]) >= 0) {
					pos++;
				} else {
					util.Util.swap(array, pos - 1, pos);
					if (pos > leftIndex + 1) {
						pos--;
					} else {
						pos++;
					}
				}
			}
		}
	}
}

