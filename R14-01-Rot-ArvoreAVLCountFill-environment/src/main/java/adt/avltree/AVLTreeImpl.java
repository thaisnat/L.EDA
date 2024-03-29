package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
//o certo
/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.
	
	@Override
	public void insert(T element) {
		if (element != null) {
			this.insert(element, this.getRoot(), new BSTNode<T>());
		}
	}

	private void insert(T element, BSTNode<T> node, BSTNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.setParent(parent);
		} else {
			if (element.compareTo(node.getData()) > 0) {
				this.insert(element, (BSTNode<T>) node.getRight(), node);
			} else if (element.compareTo(node.getData()) < 0) {
				this.insert(element, (BSTNode<T>) node.getLeft(), node);
			}
			rebalance(node);
		}
	}

	public void remove(T element) {
		if (element != null) {
			BSTNode<T> node = super.search(element);
			if (node != null && !node.isEmpty()) {
				super.remove(node.getData());
				if (node == this.root) {
					this.rebalanceUp(root);
				} else {
					this.rebalanceUp((BSTNode<T>) node.getParent());
				}
			}
		}
	}
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			return height((BSTNode<T>) node.getRight()) - height((BSTNode<T>) node.getLeft());
		}
		return 0;
	}
	
	
	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {

		if (node != null && !node.isEmpty()) {
			int balance = calculateBalance(node);
			if (Math.abs(balance) > 1) {
				if (balance > 0) {
					int sonBalance = calculateBalance((BSTNode<T>) node.getRight());
					if (sonBalance < 0) {
						Util.rightRotation((BSTNode<T>) node.getRight());
					}
					Util.leftRotation(node);
				} else {
					int sonBalance = calculateBalance((BSTNode<T>) node.getLeft());
					if (sonBalance > 0) {
						Util.leftRotation((BSTNode<T>) node.getLeft());
					}
					Util.rightRotation(node);
				}
			}
		}
	}
	
		
	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node == null || node.isEmpty()){
			return;
		}
		while(!node.isEmpty()){
			this.rebalance(node);
			node = (BSTNode<T>) node.getParent();
		}
	}
	
	public void setRoot(BSTNode<T> root){
		this.root = root;
	}
	
	public BSTNode<T> getRoot(){
		return this.root;
	}

}