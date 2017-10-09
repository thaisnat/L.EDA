package adt.bt;

import adt.bst.BSTNode;

public class Util {


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
		
BSTNode<T> aux = null;
		
		if (node == null || node.isEmpty()) {
			return aux;
		}
		if (node.getParent() == null){
			node = (BSTNode<T>) node.getRight();
		}
		if(!node.getRight().isEmpty()){
			BSTNode<T> pivot = (BSTNode<T>) node.getRight();
			node.setRight(pivot.getLeft());
			node.getRight().setParent(node);
			pivot.setParent(node.getParent());	
			if (node.getParent().getLeft() == node){
				node.getParent().setLeft(pivot);
			} else {
				node.getParent().setRight(pivot);
			}
				pivot.setLeft(node);
				node.setParent(pivot);
				aux = pivot;
		}
		
		return aux;
		
	}
	
	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		
BSTNode<T> aux = null;
		
		if (node == null || node.isEmpty()) {
			return aux;
		}
		if (node.getParent() == null) {
			node = (BSTNode<T>) node.getLeft();
		}
		if(!node.getLeft().isEmpty()){
			BSTNode<T> pivot = (BSTNode<T>) node.getLeft();
			node.setLeft(pivot.getRight());
			node.getLeft().setParent(node);
			pivot.setParent(node.getParent());
			if (node.getParent().getLeft() == node) {
				node.getParent().setLeft(pivot);
			} else {
				node.getParent().setRight(pivot);
			}
			pivot.setRight(node);
			node.setParent(pivot);
			aux = pivot;
		}
		return aux;
	}
	
	/**
	 * @param size
	 * @return
	 */
	
	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}