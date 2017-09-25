package adt.btree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}


	private int height(BNode<T> node) {
		int altura = -1;
		if(node.isEmpty()){
			return altura;
		}
		int result = 0;
		for(int i = 0 ; i < node.getChildren().size(); i++){
			int alturas = heightAux(node.getChildren().get(i));
			if(alturas > result){
				result = alturas;
			}
		}
		return result ;
	}
	
	private int heightAux(BNode<T> node){

		if(node.isLeaf()){
			return 1;
		}
		int result = 0;
		for(int i = 0 ;i < node.getChildren().size(); i++){
			int alturas = heightAux(node.children.get(i));
			if(alturas > result){
				result = alturas;
			}
		}
		return result + 1;
	}
	
	
	@Override
  	public BNode<T>[] depthLeftOrder() {
  		
  		List<BNode<T>> array = new ArrayList<BNode<T>>();
  		depthLeftOrder(array, root);
  		BNode<T>[] resp = toArray(array);
  		return resp;
  	}
  	
  	private void depthLeftOrder(List<BNode<T>> array, BNode<T> node){
  		if (!node.isEmpty()){
  			array.add(node);
  			for (int i = 0; i < node.getChildren().size(); i++){
  				depthLeftOrder(array, node.getChildren().get(i));
  			}
  		}
  	}

  	private BNode<T>[] toArray(List<BNode<T>> list){
  		BNode<T>[] array = (BNode<T>[]) new BNode[list.size()];
  		for (int i = 0; i < list.size(); i++){
  			array[i] = list.get(i);
  		}
  		return array;
  	}
	
		
	@Override
	public int size() {
		int elements = size(root);
		return elements;
	}
	
	private int size (BNode node){
		int elementos = 0;
		elementos += node.getElements().size();
		
		for(int i = 0 ; i < node.getChildren().size(); i++){
			elementos += size((BNode) node.children.get(i));
		}
		return elementos;
	}

	@Override
	public BNodePosition<T> search(T element) {
		if(root.isEmpty()){
			return null;
		}
		return search(element, root);
	}
	
	private BNodePosition<T> search(T element, BNode<T> node){
		int i = 0;
		while(i < node.elements.size()){
			if(element.compareTo(node.getElementAt(i)) > 0){
				i++;
			}
		}
		if(i < node.elements.size()){
			if(element.compareTo(node.getElementAt(i)) == 0){
				return (BNodePosition<T>) node.getElementAt(i);
			}
		}
		if(node.isLeaf()){
			return null;
		}
		return search(element,node.children.get(i));
	}
	
	@Override
	public void insert(T element) {
		insert(element, root);

	}
	
	private void insert(T element,BNode<T> node){
		if(node.children.isEmpty()){
			node.elements.add(element);
			if(node.elements.size() >= order){
				split(node);
			}
			
		}else{
			int i = 0;
			while(i < node.elements.size()){
				if(element.compareTo(node.elements.get(i)) > 0){
					i++;
				}
			}
			insert(element, node.children.get(i));
			if(node.children.get(i).elements.size() >= order){
				split(node);
			}
		}
	}
	
	
	private void split(BNode<T> node) {
		if(node.getParent() == null){
			if(node.isLeaf()){
				splitRoot(node);
			}else{
				splitRootNotLeaf(node);	
			}
		}
		
	}
	
	private void splitRoot(BNode<T> node) {
		BNode<T> novoRoot = new BNode<T>(order);
		BNode<T> filho1 = new BNode<T>(order);
		BNode<T> filho2 = new BNode<T>(order);
		filho1.setParent(novoRoot);
		filho2.setParent(novoRoot);
		
		T upElement = node.getElementAt(order/2);
		
		for(int i = 0 ; i < order/2; i++){
			filho1.elements.add(node.elements.get(i));
		}
		
		for(int i = (order/2)+1 ; i < order; i++){
			filho2.elements.add(node.elements.get(i));
		}
		
		novoRoot.elements.add(upElement);
		novoRoot.children.add(filho1);
		novoRoot.children.add(filho2);
		
		root = novoRoot;
		
	}
	
	private void splitRootNotLeaf(BNode<T> node){
		
		BNode<T> novoRoot = new BNode<T>(order);
		BNode<T> filho1 = new BNode<T>(order);
		BNode<T> filho2 = new BNode<T>(order);
		filho1.setParent(novoRoot);
		filho2.setParent(novoRoot);
		
		T upElement = node.getElementAt(order/2);
		
		for(int i = 0 ; i < order/2; i++){
			filho1.elements.add(node.elements.get(i));
		}
		
		for(int i = (order/2)+1 ; i < order; i++){
			filho2.elements.add(node.elements.get(i));
		}
		
		novoRoot.elements = node.parent.elements;
		novoRoot.elements.add(upElement);
		
		for(int i=0; i < node.parent.children.size()-1; i++){
			novoRoot.children.add(node.parent.children.get(i));
			node.parent.children.get(i).setParent(novoRoot);
		}
		
		novoRoot.children.add(filho1);
		novoRoot.children.add(filho2);
		
		root = novoRoot;
	}

	private void promote(BNode<T> node) {
		int mediana = node.size() / 2;
		T element = node.getElementAt(mediana);
		BNode<T> right = new BNode<T>(order);

		for (int i = mediana + 1; i < node.size(); i++) {
			right.addElement(node.getElementAt(i));
		}
		while (mediana < node.size()) {
			node.removeElement(mediana);
		}

		if (!node.isLeaf()) {
			LinkedList<BNode<T>> rightChild = new LinkedList<BNode<T>>();
			for (int i = mediana + 1; i < node.getChildren().size(); i++) {
				node.getChildren().get(i).setParent(right);
				rightChild.add(node.getChildren().get(i));
			}
			while (mediana + 1 < node.getChildren().size()) {
				node.removeChild(node.getChildren().get(mediana + 1));
			}
			right.setChildren(rightChild);
		}

		if (node.getParent() == null) { // se for raiz
			BNode<T> newRoot = new BNode<T>(order);
			newRoot.addElement(element);
			newRoot.addChild(0, node);
			newRoot.addChild(1, right);
			root = newRoot;
		} else {
			int indexSon = node.getParent().indexOfChild(node);
			if (node.getParent().isFull()) {
				node.getParent().addElement(element);
				node.getParent().addChild(indexSon + 1, right);
				split(node.getParent());
			} else {
				node.getParent().addElement(element);
				node.getParent().addChild(indexSon + 1, right);
			}
		}
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}