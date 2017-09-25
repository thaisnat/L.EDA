package adt.hashtable.closed;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

   /**
    * A hash table with closed address works with a hash function with closed
    * address. Such a function can follow one of these methods: DIVISION or
    * MULTIPLICATION. In the DIVISION method, it is useful to change the size
    * of the table to an integer that is prime. This can be achieved by
    * producing such a prime number that is bigger and close to the desired
    * size.
    * 
    * For doing that, you have auxiliary methods: Util.isPrime and
    * getPrimeAbove as documented bellow.
    * 
    * The length of the internal table must be the immediate prime number
    * greater than the given size. For example, if size=10 then the length must
    * be 11. If size=20, the length must be 23. You must implement this idea in
    * the auxiliary method getPrimeAbove(int size) and use it.
    * 
    * @param desiredSize
    * @param method
    */

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
      int realSize = desiredSize;

      if (method == HashFunctionClosedAddressMethod.DIVISION) {
         realSize = this.getPrimeAbove(desiredSize); // real size must the
         // the immediate prime
         // above
      }
      initiateInternalTable(realSize);
      HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
      this.hashFunction = function;
   }

   // AUXILIARY
   /**
    * It returns the prime number that is closest (and greater) to the given
    * number. You can use the method Util.isPrime to check if a number is
    * prime.
    */
   int getPrimeAbove(int number) {
	   while(!Util.isPrime(number)){
		   number++;
	   }
	   return number;
   }

   @Override
   public void insert(T element) {
	   if(element == null){
		   return;
	   }
	   
	   int hash = getHash(element);
	   
	   if(this.table[hash] == null){
		   
		   LinkedList<T> auxList = new LinkedList<>();
		   auxList.add(element);
		   this.table[hash] = auxList;
		   this.elements++;
	   } else{
		   
		   LinkedList<T> elements = (LinkedList<T>) this.table[hash];
		   if(!elements.contains(element)){
			   elements.add(element);
			   this.COLLISIONS++;
			   this.elements++;
		   }
	   }
   }

   @Override
   public void remove(T element) {
	   if(element == null){
		   return;
	   }
	   
	   int hash = this.getHash(element);
	   
	   LinkedList<T> elements = (LinkedList<T>) this.table[hash];
	   
	   if(elements != null){
		   if(elements.size() >= 2){
			   elements.remove(element);
			   this.COLLISIONS--;
			   this.elements--;
		   } else{
			   elements.remove(element);
			   this.elements--;
		   }
	   }
   }

   @Override
   public T search(T element) {
	   if(element == null){
		   return null;
	   }
	   
	   int hash = this.getHash(element);;
	   
	   LinkedList<T> elements = (LinkedList<T>) this.table[hash];
	   
	   if(elements != null){
		   if(elements.contains(element)){
			   return elements.get(elements.indexOf(element));
		   }
	   }
	   
	   return null;
   }

   @Override
   public int indexOf(T element) {
	   if(element == null){
		   return -1;
	   }
	   
	   int hash = this.getHash(element);
	   
	   LinkedList<T> elements = (LinkedList<T>)this.table[hash];
	   
	   if(elements != null){
		   if(elements.contains(element)){
			   return hash;
		   }
		   
	   }
	   
	   return -1;
   }
   
   private int getHash(T element) {
	   HashFunctionClosedAddress<T> hash = (HashFunctionClosedAddress<T>) this.hashFunction;
	   
	   return hash.hash(element);
   }

}
