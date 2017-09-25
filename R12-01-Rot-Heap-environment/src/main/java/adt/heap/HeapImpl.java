package adt.heap;
   
   import java.util.Arrays;
   import java.util.Comparator;
   
   import util.Util;
   
   /**
   * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
  * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
  * topo. Essa comparação não é feita diretamente com os elementos armazenados,
  * mas sim usando um comparator. Dessa forma, dependendo do comparator, a heap
  * pode funcionar como uma max-heap ou min-heap.
  */
  public class HeapImpl<T extends Comparable<T>> implements Heap<T> {
  
  	protected T[] heap;
  	protected int index = -1;
  	/**
  	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
  	 * mudar apenas o comparator e mandar reordenar a heap usando esse
  	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
  	 * como max-heap ou min-heap.
  	 */
  	protected Comparator<T> comparator;
  
  	private static final int INITIAL_SIZE = 20;
  	private static final int INCREASING_FACTOR = 10;
  
  	/**
  	 * Construtor da classe. Note que de inicio a heap funciona como uma
  	 * min-heap.
  	 */
  	@SuppressWarnings("unchecked")
  	public HeapImpl(Comparator<T> comparator) {
  		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
  		this.comparator = comparator;
  	}
  
  	// /////////////////// METODOS IMPLEMENTADOS
  	private int parent(int i) {
  		return (i - 1) / 2;
  	}
  
  	/**
  	 * Deve retornar o indice que representa o filho a esquerda do elemento
  	 * indexado pela posicao i no vetor
  	 */
  	private int left(int i) {
  		return (i * 2 + 1);
  	}
  
  	/**
  	 * Deve retornar o indice que representa o filho a direita do elemento
  	 * indexado pela posicao i no vetor
  	 */
  	private int right(int i) {
  		return (i * 2 + 1) + 1;
  	}
  
  	@Override
  	public boolean isEmpty() {
  		return (index == -1);
  	}
  
  	@Override
  	public T[] toArray() {
  		@SuppressWarnings("unchecked")
  		T[] resp = (T[]) new Comparable[index + 1];
  		for (int i = 0; i <= index; i++) {
  			resp[i] = this.heap[i];
  		}
  		return resp;
  	}
  
  	// ///////////// METODOS A IMPLEMENTAR
  	/**
  	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
  	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
  	 * (comparados usando o comparator) elementos na parte de cima da heap.

  	 */
  	private void heapify(int position) {
  		if(position >= 0 && !isEmpty()) {
  			int max = getMax(left(position), right(position));
  			
  			if((max >= 0) && (comparator.compare(heap[position], heap[max]) < 0)) {
  				Util.swap(heap, position, max);
  				heapify(max);
  			}
  			
  		}
  	}
  
  	private int getMax(int leftIndex, int rightIndex) {
  		if(leftIndex > index) {
  			return -1;
  		} else if(rightIndex > index) {
  			return leftIndex;
  		} else if ((leftIndex < size()) && (rightIndex < size())) {
  			if(comparator.compare(heap[leftIndex], heap[rightIndex]) > 0) {
 				return leftIndex;

 			} else {
 				return rightIndex;
 			}
 		}
 		return -1;
 	}
 
 	@Override
 	public void insert(T element) {
 		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
 		if (index == heap.length - 1) {
 			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
 		}
 		// /////////////////////////////////////////////////////////////////
 		// TODO Implemente a insercao na heap aqui.
 		
 		heap[++index] = element;
 		int j = index;
 		
 		while((j >= 1) && (comparator.compare(heap[j], heap[parent(j)]) > 0)) {
 			Util.swap(heap, j, parent(j));
 			j = parent(j);
 		}
 	}
 
 	@Override
 	public void buildHeap(T[] array) {
 		if((array != null) && (array.length > 0)) {			
 			this.heap = Arrays.copyOf(array, array.length);
 			index = array.length - 1;
 			for(int i = array.length / 2; i >=0; i--) {
 				heapify(i);
 			}
 		}
 	} 
 	@Override
 	public T extractRootElement() {
 		if(isEmpty()) {
 			return null;
 		} else {			
 			T element = this.heap[0];
 			Util.swap(heap, 0, index);
 			index--;
 			this.heapify(0);
 			return element;
 		}
 	}
 
 	@Override
 	public T rootElement() {
 		return (isEmpty()) ? null : this.heap[0];
 	}
 
 	@Override
 	public T[] heapsort(T[] array) {
 		Comparator<T> newComparator = this.comparator;
 		this.comparator = (o1, o2) -> o1.compareTo(o2);		
 		this.buildHeap(array);
 		
 		T[] aux = (T[]) new Comparable[this.size()];
 		for(int i = index; i >= 0; i--) {
 			aux[i] = this.extractRootElement();
 		}
 		
 		this.comparator = newComparator;
 		return aux;
 	}
 
 	@Override
 	public int size() {
 		return index + 1;
 	}
 
	public Comparator<T> getComparator() {
 		return comparator;
 	}
 
 	public void setComparator(Comparator<T> comparator) {
 		this.comparator = comparator;
 	}
 	public T[] getHeap() {
 		return heap;
 	}
 
 }
