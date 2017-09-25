package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (rightIndex > leftIndex && leftIndex >= 0 && rightIndex < array.length) {
			double gap = rightIndex - leftIndex + 1;
			boolean swapped = true;
			while (gap > 1 || swapped) {
				gap = gap / 1.25;
				swapped = false;
				int i = leftIndex;
				while (i + (int) gap <= rightIndex) {
					if (array[i].compareTo(array[i + (int) gap]) > 0) {
						util.Util.swap(array, i, i + (int) gap);
						swapped = true;
					}
					i++;
				}
			}
		}
	}
}