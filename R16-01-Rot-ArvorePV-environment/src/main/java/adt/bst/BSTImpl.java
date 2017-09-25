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
  			return height(root) - 1; 
  		}
  	}
  
  	protected int height(BTNode<T> node) {
  		if (node.isEmpty())
  			return 0;
  		else {
  			int leftLvl = height(node.getLeft()) + 1;
  			int rightLvl = height(node.getRight()) + 1;
  			if(leftLvl >= rightLvl){
  				return leftLvl;
  			}else{
  				return rightLvl;
  			}
  		}
  	}

  	@Override
  	public BSTNode<T> search(T element) {
  		return (BSTNode<T>)this.search(root, element);
  	}
  	
  	private BTNode<T> search(BTNode<T> node, T element){
  		if(node.isEmpty() || node.getData().equals(element)){
  			return node;
  		}else{
  			if(element.compareTo(node.getData()) >= 0){
  				return search(node.getRight(), element);
  			}else{
  				return search(node.getLeft(), element);
  			}
  		}
  	}

  	@Override
  	public void insert(T element) {
  		if (element != null) {
  			insert(root, element);
  		}
  	}
  
  	private void insert(BTNode<T> node, T element) {
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
  				this.insert(node.getRight(), element);
  			}else{
  				this.insert(node.getLeft(), element);
  			}
  		}
  	}
  
  	@Override
  	public BSTNode<T> maximum() {
  		if(!this.isEmpty()){
 			return (BSTNode<T>)this.maximum(root);
 		}
 		return null;
 	}
 	
 	private BSTNode<T> maximum(BTNode<T> node){
 		if(node.getRight().isEmpty()){
 			return (BSTNode<T>) node;
 		}else{
 			return maximum(node.getRight());
 		}
 	}
 
 	@Override
 	public BSTNode<T> minimum(){
 		if(!this.isEmpty()){
 			return (BSTNode<T>)this.minimum(root);
 		}
 		return null;
 	}
 	
 	private BSTNode<T> minimum(BTNode<T> node){
 		if(node.getLeft().isEmpty()){
 			return (BSTNode<T>) node;
 		}else{
 			return minimum(node.getLeft());
 		}
 	}
 
 	@Override
 	public BSTNode<T> sucessor(T element) {
 		if(element != null){
 			BSTNode<T> nodeSearch = this.search(element);
 			if(!nodeSearch.isEmpty()){
 				return this.sucessor(nodeSearch);
 			}
 		}
 		return null;
 	}
 	
 	private BSTNode<T> sucessor(BTNode<T> node){
 		if(!node.getRight().isEmpty()){
 			return this.minimum(node.getRight());
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
 				return this.predecessor(nodeSearch);
 			}
 		}
 		return null;
 	}
 
 	private BSTNode<T> predecessor(BTNode<T>node){
 		if(!node.getLeft().isEmpty()){
 			return this.maximum(node.getLeft());
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
 			this.remove(node);
 		}
 	}
 	
 	private void remove(BTNode<T> node){
 		if (node.isEmpty()){
 			return;
 		}else if (node.isLeaf()){
 			node.setData(null);
 			node.setLeft(null);
 			node.setRight(null);
 		}else {
 			BTNode<T> auxNode = null;
 			if (!node.getRight().isEmpty()) {
 				auxNode = minimum(node.getRight());
 				node.setData(auxNode.getData());
 				remove(auxNode);
 			} else {
 				auxNode = maximum(node.getLeft());
 				node.setData(auxNode.getData());
 				remove(auxNode);
 			}
 		}
 	}
 
 	@Override
 	public T[] preOrder() {
 		T[] retorno = (T[]) new Comparable[this.size()];
 		ArrayList<T> aux = new ArrayList<>();
 		this.preOrder(aux, root);
 		int index = 0;
 		for(T element : aux){
 			retorno[index] = element;
 			index ++;
 		}
 		return retorno;
 	}
 	
 	private void preOrder(ArrayList<T> lista, BTNode<T> node){
 		if(!node.isEmpty()){
 			lista.add(node.getData());
 			this.preOrder(lista, node.getLeft());
 			this.preOrder(lista, node.getRight());
 		}
 	}
 	@Override
 	public T[] order() {
 		T[] retorno = (T[]) new Comparable[this.size()];
 		ArrayList<T> aux = new ArrayList<>();
 		this.order(aux, root);
 		int index = 0;
 		for(T element : aux){
 			retorno[index] = element;
 			index ++;
 		}
 		return retorno;
 	}
 	
 	private void order(ArrayList<T> lista, BTNode<T> node){
 		if(!node.isEmpty()){
 			this.order(lista, node.getLeft());
 			lista.add(node.getData());
 			this.order(lista, node.getRight());
 		}
 	}
 
 	@Override
 	public T[] postOrder() {
 		T[] retorno = (T[]) new Comparable[this.size()];
 		ArrayList<T> aux = new ArrayList<>();
 		this.postOrder(aux, root);
 		int index = 0;
 		for(T element : aux){
 			retorno[index] = element;
 			index ++;
 		}
 		return retorno;
 	}
 	
 	private void postOrder(ArrayList<T> lista, BTNode<T> node){
 		if(!node.isEmpty()){
 			this.postOrder(lista, node.getLeft());
 			this.postOrder(lista, node.getRight());
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
