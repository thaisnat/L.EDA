package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		
		if(isFull()){
			throw new StackOverflowException();
		}else{
			top.insertFirst(element);	
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		
		T element = null;
		
		if (top.isEmpty()) {
			throw new StackUnderflowException();
		}else{
			element = ((DoubleLinkedListImpl<T>) top).getHead().getData();
			top.removeFirst();
			return element;
		}
	}

	@Override
	public T top() {
		
		if(!isEmpty()){
			return ((DoubleLinkedListImpl<T>) top).getHead().getData();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		
		if(top.size() == 0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean isFull() {
		
		if(top.size() == size){
			return true;
		}else{
			return false;
		}
	}

}