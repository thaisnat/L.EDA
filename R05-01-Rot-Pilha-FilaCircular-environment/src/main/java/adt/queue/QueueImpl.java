package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int SIZE;
	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		if(size < 0){
			size = 0;
		}
		array = (T[]) new Object[size];
		tail = -1;
		SIZE = size;
	}

	@Override
	public T head() {
		T aux;
		if (isEmpty()) {
			aux = null;
		} else {
			aux = array[0];
		}
		return aux;
	}

	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	@Override
	public boolean isFull() {
		return this.tail == this.array.length - 1;
	}

	private void shiftLeft() {
		for (int i = 0; i <= this.tail; i++) {
			this.array[i] = this.array[i + 1];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}
		if (element != null) {
			this.tail++;
			array[this.tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T aux;
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		aux = head();
		this.tail--;
		shiftLeft();
		return aux;
	}

}