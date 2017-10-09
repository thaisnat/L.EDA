package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;
	private int SIZE;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		if(size < 0){
			size = 0;
		}
		array = (T[]) new Object[size];
		top = -1;
		SIZE = size;
	}

	@Override
	public T top() {
		T aux;
		if (isEmpty()) {
			aux = null;
		} else {
			aux = array[top];
		}
		return aux;
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == this.array.length - 1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}
		if (element != null) {
			top++;
			array[top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		T aux;
		if (isEmpty()) {
			throw new StackUnderflowException();
		}
		aux = array[top];
		top--;
		return aux;
	}

}