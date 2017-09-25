package adt.bst;

import java.util.ArrayList;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

  	@Override
  	public int height() {
  		if (this.isEmpty())
  			return -1;
  		else {
  			return heightAux(root) - 1; 
  		}
  	}
  
  	private int heightAux(BTNode<T> node) {
  		if (node.isEmpty())
  			return 0;
  		else {
  			int leftLvl = heightAux(node.getLeft()) + 1;
  			int rightLvl = heightAux(node.getRight()) + 1;
  			if(leftLvl >= rightLvl){
  				return leftLvl;
  			}else{
  				return rightLvl;
  			}
  		}
  	}

  	@Override
  	public BSTNode<T> search(T element) {
  		return (BSTNode<T>)this.searchAux(root, element);
  	}
  	
  	private BTNode<T> searchAux (BTNode<T> node, T element){
  		if(node.isEmpty() || node.getData().equals(element)){
  			return node;
  		}else{
  			if(element.compareTo(node.getData()) >= 0){
  				return searchAux(node.getRight(), element);
  			}else{
  				return searchAux(node.getLeft(), element);
  			}
  		}
  	}

  	@Override
  	public void insert(T element) {
  		if (element != null) {
  			insertAux(root, element);
  		}
  	}
  
  	private void insertAux(BTNode<T> node, T element) {
  		if (node.isEmpty()) {
  			node.setData(element);
  
  			BSTNode<T> nodeLeft = new BSTNode<>();
  			nodeLeft.setData(null);
  			nodeLeft.setRight(null);
  			nodeLeft.setLeft(null);
  			nodeLeft.setParent(node);
  
  			BSTNode<T> nodeRight = new BSTNode<>();
  			nodeRight.setData(null);
  			nodeRight.setRight(null);
  			nodeRight.setLeft(null);
  			nodeRight.setParent(node);
  
  			node.setLeft(nodeLeft);
  			node.setRight(nodeRight);
  		} else {
  			if(element.compareTo(node.getData()) >= 0){
  				this.insertAux(node.getRight(), element);
  			}else{
  				this.insertAux(node.getLeft(), element);
  			}
  		}
  	}
  
  	@Override
  	public BSTNode<T> maximum() {
  		if(!this.isEmpty()){
 			return (BSTNode<T>)this.maximumAux(root);
 		}
 		return null;
 	}
 	
 	private BSTNode<T> maximumAux(BTNode<T> node){
 		if(node.getRight().isEmpty()){
 			return (BSTNode<T>) node;
 		}else{
 			return maximumAux(node.getRight());
 		}
 	}
 
 	@Override
 	public BSTNode<T> minimum(){
 		if(!this.isEmpty()){
 			return (BSTNode<T>)this.minimumAux(root);
 		}
 		return null;
 	}
 	
 	private BSTNode<T> minimumAux(BTNode<T> node){
 		if(node.getLeft().isEmpty()){
 			return (BSTNode<T>) node;
 		}else{
 			return minimumAux(node.getLeft());
 		}
 	}
 
 	@Override
 	public BSTNode<T> sucessor(T element) {
 		if(element != null){
 			BSTNode<T> nodeSearch = this.search(element);
 			if(!nodeSearch.isEmpty()){
 				return this.sucessorAux(nodeSearch);
 			}
 		}
 		return null;
 	}
 	
 	private BSTNode<T> sucessorAux(BTNode<T> node){
 		if(!node.getRight().isEmpty()){
 			return this.minimumAux(node.getRight());
 		}else{
 			BTNode<T> nodeParent = node.getParent();
 			while (nodeParent != null && node.equals(nodeParent.getRight())){
 				node = nodeParent;
 				nodeParent = nodeParent.getParent();
 			}
 			return (BSTNode<T>) nodeParent;
 		}
 	}
 
 	@Override
 	public BSTNode<T> predecessor(T element) {
 		if(element != null){
 			BSTNode<T> nodeSearch = this.search(element);
 			if(!nodeSearch.isEmpty()){
 				return this.predecessorAux(nodeSearch);
 			}
 		}
 		return null;
 	}
 
 	private BSTNode<T> predecessorAux(BTNode<T>node){
 		if(!node.getLeft().isEmpty()){
 			return this.maximumAux(node.getLeft());
 		}else{
 			BTNode<T> nodeParent = node.getParent();
 			while (nodeParent != null && node.equals(nodeParent.getLeft())){
 				node = nodeParent;
 				nodeParent = nodeParent.getParent();
 			}
 			return (BSTNode<T>) nodeParent;
 		}
 	}

 	@Override
 	public void remove(T element) {
 		if (this.isEmpty() || element == null) {
 			return;
 		} else {
 			BTNode<T> node = search(element);
 			this.removeAux(node);
 		}
 	}
 	
 	private void removeAux(BTNode<T> node){
 		if (node.isEmpty()){
 			return;
 		}else if (node.isLeaf()){
 			node.setData(null);
 			node.setLeft(null);
 			node.setRight(null);
 		}else {
 			BTNode<T> auxNode = null;
 			if (!node.getRight().isEmpty()) {
 				auxNode = minimumAux(node.getRight());
 				node.setData(auxNode.getData());
 				removeAux(auxNode);
 			} else {
 				auxNode = maximumAux(node.getLeft());
 				node.setData(auxNode.getData());
 				removeAux(auxNode);
 			}
 		}
 	}
 
 	@Override
 	public T[] preOrder() {
 		T[] retorno = (T[]) new Comparable[this.size()];
 		ArrayList<T> aux = new ArrayList<>();
 		this.preOrderAux(aux, root);
 		int index = 0;
 		for(T element : aux){
 			retorno[index] = element;
 			index ++;
 		}
 		return retorno;
 	}
 	
 	private void preOrderAux (ArrayList<T> lista, BTNode<T> node){
 		if(!node.isEmpty()){
 			lista.add(node.getData());
 			this.preOrderAux(lista, node.getLeft());
 			this.preOrderAux(lista, node.getRight());
 		}
 	}
 	@Override
 	public T[] order() {
 		T[] retorno = (T[]) new Comparable[this.size()];
 		ArrayList<T> aux = new ArrayList<>();
 		this.orderAux(aux, root);
 		int index = 0;
 		for(T element : aux){
 			retorno[index] = element;
 			index ++;
 		}
 		return retorno;
 	}
 	
 	private void orderAux (ArrayList<T> lista, BTNode<T> node){
 		if(!node.isEmpty()){
 			this.orderAux(lista, node.getLeft());
 			lista.add(node.getData());
 			this.orderAux(lista, node.getRight());
 		}
 	}
 
 	@Override
 	public T[] postOrder() {
 		T[] retorno = (T[]) new Comparable[this.size()];
 		ArrayList<T> aux = new ArrayList<>();
 		this.postOrderAux(aux, root);
 		int index = 0;
 		for(T element : aux){
 			retorno[index] = element;
 			index ++;
 		}
 		return retorno;
 	}
 	
 	private void postOrderAux (ArrayList<T> lista, BTNode<T> node){
 		if(!node.isEmpty()){
 			this.postOrderAux(lista, node.getLeft());
 			this.postOrderAux(lista, node.getRight());
 			lista.add(node.getData());
 		}
 	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
