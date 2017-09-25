package adt.avltree;

import adt.bt.Util;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		fillWithoutRebalance(array, 0, array.length-1);
	}
	
	private void fillWithoutRebalance(T[] array, int leftIndex,int rightIndex){
		
		T element = null;
		
		if(array.length != 0){
			int pivot = calculateMediana(array,leftIndex, rightIndex);
			element = array[pivot];
			super.insert(element);
			array = this.remove(array, pivot);
			fillWithoutRebalance(array, leftIndex++, rightIndex--);
		}
	}
	
	private int calculateMediana(T[] array, int left, int right){
		
		int leftIndex = left +1;
		int rightIndex = right;
		T pivot = array[left];
		
		while(leftIndex <= rightIndex){
			if(array[leftIndex].compareTo(pivot) <= 0){
				leftIndex++;
			}else if(array[rightIndex].compareTo(pivot) > 0){
				rightIndex--;
			}else{
				Util.swap(array, leftIndex, rightIndex);
			}
		}
		Util.swap(array, left, rightIndex);
		return rightIndex;
	}
	
	private T[] remove(T[] array, int y) {
		int j = 0;
		for (int i = 0; i < array.length; i++) {
			if (!(array[i].equals(y))) {
				j++;
			}
		}
		
		T[] vetorAux = (T[]) new Comparable[this.size()];
		int index = 0;
		for (int i = 0; i < array.length; i++) {
			if (!(array[i].equals(y))){
				vetorAux[index] = array[i];
				index++;
			}
		}
		array = vetorAux;
		return array;
	}
}
