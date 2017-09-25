package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		
		int size = 0;
		SingleLinkedListNode<T> aux =  head;
		while(!aux.isNIL()){
			size++;
			aux = aux.next;
		}
		
		return size;
	}

	@Override
	public T search(T element) {
		
		SingleLinkedListNode<T> aux =  head;
		
		while(!aux.isNIL() && !aux.getData().equals(element)){
			aux = aux.next;
		}
		
		return aux.getData();
	}

	@Override
	public void insert(T element) {
		
		SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
		
		if(head.isNIL()){
			newNode.next = head;
			head = newNode;
		} else{
			
			SingleLinkedListNode<T> aux =  head;
			
			while(!aux.next.isNIL()){
				aux = aux.next;
			}
			
			newNode.next = aux.next;
			aux.next = newNode;
		}
	}

	@Override
	public void remove(T element) {
		
		if(head.getData() == element){
			head = head.next;
			
		}else{
			SingleLinkedListNode<T> aux =  head;
			SingleLinkedListNode<T> previous =  new SingleLinkedListNode<T>();
			
			while(!aux.isNIL() && (!aux.getData().equals(element))){
				previous = aux;
				aux = aux.next;
			}
			
			if(!aux.isNIL()){
				previous.next = aux.next;
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] newArray = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> aux =  head;
		int cont = 0;
		
		while(!aux.isNIL()){
			newArray[cont] = aux.getData();
			aux = aux.next;
			cont++;
		}
		
		return newArray;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
