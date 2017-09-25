package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends
		AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array.length > 0 && leftIndex >= 0 && rightIndex < array.length && leftIndex < rightIndex){
			
			int aux = leftIndex + (rightIndex - leftIndex) / 2;
			for (int j = leftIndex; j <= aux + 1; j++) {
				for (int i = leftIndex + 1, k = rightIndex; i <= rightIndex && k > leftIndex; i++, k--) {
					if (array[i - 1].compareTo(array[i]) > 0) {
						Util.swap(array, i - 1, i);
					}
					if (array[k - 1].compareTo(array[k]) > 0) {
						Util.swap(array, k - 1, k);
					}
				}
			}
		}
	}
}
