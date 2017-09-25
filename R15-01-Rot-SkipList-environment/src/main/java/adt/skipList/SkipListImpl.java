package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	
	@Override
	public void insert(int key, T newValue, int height) {
		
		SkipListNode[] update = new SkipListNode[maxHeight];
		SkipListNode<T> node = this.root;
		
		for (int i = maxHeight-1; i >= 0; i--) {
			while(node.getForward(i).getKey() < key){
				node = node.getForward(i);
			}
			update[i] = node;
		}
		node = node.getForward(0);
		if(node.getKey() == key){
			node.setValue(newValue);
		}else{
			node = new SkipListNode<T>(key,height,newValue);
			for (int i = 0; i < height; i++) {
				node.getForward()[i] = update[i].getForward(i);
				update[i].getForward()[i] = node;
			}
		}
	}

	@Override
	public void remove(int key) {
		
		SkipListNode[] update = new SkipListNode[maxHeight];
		SkipListNode<T> node = this.root;
		
		for (int i = maxHeight-1; i >= 0; i--) {
			while(node.getForward(i).getKey() < key){
				node = node.getForward(i);
			}
			update[i] = node;
		}
		node = node.getForward(0);
		if(node.getKey() == key){
			int i = 0;
			while(i < maxHeight && update[i].getForward(i) == node){
				update[i].getForward()[i] = node.getForward(i);
				i++;
			}
		}
	}

	@Override
	public int height() {
		
		int result = -1;
		int i = maxHeight -1;
		
		while(result == -1 && i >=0){
			if(this.root.getForward(i).getKey() != Integer.MAX_VALUE){
				return i;
			}
			i--;
		}
		return result;
	}

	@Override
	public SkipListNode<T> search(int key) {
		
		SkipListNode<T> node = this.root;
		
		for (int i = maxHeight-1; i >= 0; i--) {
			while(node.getForward(i).getKey() < key){
				node = node.getForward(i);
			}
		}
		if(node.getForward(0).getKey() == key){
			node = node.getForward(0);
		}else{
			node = null;
		}
		return node;
	}

	@Override
	public int size() {
		
		SkipListNode<T> node = this.root.getForward(0);
		int size = 0;
		
		while(node.getKey() != Integer.MAX_VALUE){
			size ++;
			node = node.getForward(0);
		}
		return size;
	}

	@Override
	public SkipListNode<T>[] toArray() {
		
		SkipListNode[] lista = new SkipListNode[this.size() + 2];
		SkipListNode<T> node = this.root;

		int i = 0;
		while(i < lista.length){
			lista[i] = node;
			node = node.getForward(0);
			i++;
		}
		return lista;
	}

}