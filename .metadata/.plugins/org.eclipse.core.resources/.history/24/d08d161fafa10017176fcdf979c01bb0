package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl() {
		super.head = new DoubleLinkedListNode<T>();
		this.last = new DoubleLinkedListNode<T>();
		
	}
	
	@Override
	public void insert(T element) {
			
		if(isEmpty()){
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
			newNode.data = element;
			newNode.next = new DoubleLinkedListNode<>();
			newNode.previous = new DoubleLinkedListNode<>();
			
			super.head = newNode;
			this.last = newNode;
		}else if(super.head == this.last){
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
			newNode.data = element;
			newNode.previous = (DoubleLinkedListNode<T>) super.head;
			this.last = newNode;
			super.head.next = newNode;
			newNode.next = new DoubleLinkedListNode<>();
		}else{
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>();
			newNode.data = element;
			newNode.next = new DoubleLinkedListNode<>();
			newNode.previous = this.last;
			this.last.next = newNode;
			this.last = newNode;
		}
	}
	
	@Override
	public void remove(T element) {
		DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<>();
		aux = (DoubleLinkedListNode<T>) super.head;
		
		while(!aux.isNIL() && !aux.data.equals(element)){
			aux = (DoubleLinkedListNode<T>) aux.next;
		}
		
		if(!aux.isNIL()){
			aux.previous.next = aux.next;
			((DoubleLinkedListNode<T>)aux.next).previous = aux.previous;
		}
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>();
		newNode.data = element;
		newNode.next = super.head;
		((DoubleLinkedListNode<T>)head).previous = newNode;
		newNode.previous = new DoubleLinkedListNode<>();
		super.head = newNode;
	}
	
	@Override
	public void removeFirst() {
		
		if(!isEmpty()){
			if(super.head == this.last){
				super.head.data = null;
			}else{
				super.head = super.head.next;
				((DoubleLinkedListNode<T>)super.head).previous = new DoubleLinkedListNode<>();
			}
		}
	}
	
	@Override
	public void removeLast() {
		
		if(!isEmpty()){
			if(super.head == this.last){
				super.head = new DoubleLinkedListNode<>();
				this.last = (DoubleLinkedListNode<T>) super.head;
			}else{
				this.last = this.last.previous;
				this.last.next = new DoubleLinkedListNode<>();
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}