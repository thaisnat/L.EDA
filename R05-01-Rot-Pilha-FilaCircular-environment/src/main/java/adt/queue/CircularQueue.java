package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;
	private int capacity;

	public CircularQueue(int size) {
		if(size < 0){
			size = 0;
		}
		array = (T[]) new Object[size];
		capacity = size;
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		}
		if (isEmpty() && element != null) {
			this.head = 0;
			this.tail = 0;
			this.array[this.tail] = element;
			this.elements++;
		} else if (element != null) {
			this.tail = (this.tail + 1) % this.array.length;
			this.array[tail] = element;
			this.elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		}
		T aux = this.array[this.head];
		if (this.head == this.tail) {
			this.head = -1;
			this.tail = -1;
		} else {
			this.head = (this.head + 1) % this.array.length;
		}
		this.elements--;
		return aux;
	}

	@Override
	public T head() {
		T aux;
		if (isEmpty()) {
			aux = null;
		} else {
			aux = this.array[this.head];
		}
		return aux;
	}

	@Override
	public boolean isEmpty() {
		return this.elements == 0;
	}

	@Override
	public boolean isFull() {
		return this.elements == this.array.length;
	}

}