package adt.rbtree;

import java.util.LinkedList;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		int bh = 0;

		if (!isEmpty()) {
			bh = recursiveBH((RBNode<T>) this.root);
		}

		return bh;
	}

	private int recursiveBH(RBNode<T> actualNode) {
		int bh = -1;
		if (actualNode != null) {

			RBNode<T> left = (RBNode<T>) actualNode.getLeft();
			int leftBH = recursiveBH(left);

			RBNode<T> right = (RBNode<T>) actualNode.getRight();
			int rightBH = recursiveBH(right);

			bh = Math.max(leftBH, rightBH);
			if (isBlack(actualNode)) {
				bh += 1;
			}

		}
		return bh;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return recVerifyCofRedNodes((RBNode<T>) root);
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		boolean bh = recursiveBH((RBNode<T>) root.getLeft()) == recursiveBH((RBNode<T>) root.getRight());
		if (!bh) {
			throw new RuntimeException();
		}
		return bh;
	}

	@Override
	public void insert(T value) {
		if (value != null) {
			insert((RBNode<T>) root, value);
		}
	}

	private void insert(RBNode<T> actualNode, T value) {
		if (actualNode.isEmpty()) {
			actualNode.setData(value);
			actualNode.setRight(new RBNode<T>());
			actualNode.getRight().setParent(actualNode);
			actualNode.setLeft(new RBNode<T>());
			actualNode.getLeft().setParent(actualNode);
			actualNode.setColour(Colour.RED);
			fixUpCase1(actualNode);
		} else {
			if (actualNode.getData().compareTo(value) < 0) {
				actualNode = (RBNode<T>) actualNode.getRight();
				insert(actualNode, value);
			} else if (actualNode.getData().compareTo(value) > 0) {
				actualNode = (RBNode<T>) actualNode.getLeft();
				insert(actualNode, value);
			}
		}
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		@SuppressWarnings("unchecked")
		RBNode<T>[] arr = new RBNode[size()];
		if (!isEmpty()) {
			arr = recursivePreOrder((RBNode<T>) this.root, arr);
		}

		return arr;
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(root)) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		if (!isBlack(parent)) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> gParent = (RBNode<T>) parent.getParent();
		RBNode<T> uncle;

		if (parent.getData().compareTo(gParent.getData()) > 0) {
			uncle = (RBNode<T>) gParent.getLeft();
		} else {
			uncle = (RBNode<T>) gParent.getRight();
		}
		if (!isBlack(uncle)) {
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			gParent.setColour(Colour.RED);
			fixUpCase1(gParent);
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> gParent = (RBNode<T>) parent.getParent();

		if ((node.getData().compareTo(parent.getData()) > 0 && parent.getData().compareTo(gParent.getData()) < 0)) {
			leftRotation(parent);
			next = (RBNode<T>) node.getLeft();

		} else if ((node.getData().compareTo(parent.getData()) < 0
				&& parent.getData().compareTo(gParent.getData()) > 0)) {
			rightRotation(parent);
			next = (RBNode<T>) node.getRight();

		}

		fixUpCase5(next);
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> gParent = (RBNode<T>) parent.getParent();

		parent.setColour(Colour.BLACK);
		gParent.setColour(Colour.RED);

		if (node.getData().compareTo(parent.getData()) < 0) {
			rightRotation(gParent);

		} else {
			leftRotation(gParent);
		}
	}

	private boolean isBlack(RBNode<T> node) {
		return (node.getColour() == Colour.BLACK);
	}

	private RBNode<T>[] recursivePreOrder(RBNode<T> actualNode, RBNode<T>[] array) {
		if (actualNode.isLeaf()) {
			insertOnArray(array, (RBNode<T>) actualNode);
		} else {

			insertOnArray(array, (RBNode<T>) actualNode);

			if (!actualNode.getLeft().isEmpty()) {
				RBNode<T> left = (RBNode<T>) actualNode.getLeft();
				recursivePreOrder(left, array);
			}

			if (!actualNode.getRight().isEmpty()) {
				RBNode<T> right = (RBNode<T>) actualNode.getRight();
				recursivePreOrder(right, array);
			}

		}
		return array;
	}

	private boolean recVerifyCofRedNodes(RBNode<T> node) {
		boolean verify = false;
		if (node != null) {
			if (!node.isEmpty()) {
				if (!isBlack(node)) {
					verify = isBlack((RBNode<T>) node.getLeft()) && isBlack((RBNode<T>) node.getRight());
				} else {
					verify = recVerifyCofRedNodes((RBNode<T>) node.getLeft())
							&& recVerifyCofRedNodes((RBNode<T>) node.getRight());
				}
			} else {
				verify = true;
			}
		}
		return verify;
	}

	private void insertOnArray(RBNode<T>[] array, RBNode<T> node) {
		int i = 0;
		while (i < array.length) {
			if (array[i] == null) {
				array[i] = node;
				break;
			}
			i++;
		}
	}

	private void rightRotation(RBNode<T> node) {
		if (node != null) {
			RBNode<T> aux = (RBNode<T>) Util.rightRotation(node);
			if (root.equals(node)) {
				this.root = aux;
			}
		}
	}

	private void leftRotation(RBNode<T> node) {
		if (node != null) {
			RBNode<T> aux = (RBNode<T>) Util.leftRotation(node);
			if (root.equals(node)) {
				this.root = aux;
			}
		}
	}
}