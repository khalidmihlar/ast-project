//comments: 67
//comment length: 5025
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	private T data[]; 
	private int size; 
	private int capacity; 


	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0; 
		data = (T[]) new Object[10]; 
		capacity = 10; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		capacity = capacity*2;
		T[] temp = (T[]) new Object[capacity];
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}
		data = temp;
 	}


	public boolean add(T arg0) {
		
		if(contains(arg0))		
			return false;
	
		
		
		
		if(size>capacity-1)
			grow();
		
		data[size]=arg0;
		size++;
		
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean wereAnyAdded = false;
		
		
		
		
		
		
		for (T item: arg0) {
			if(add(item))
				wereAnyAdded = true;
		}
		
		return wereAnyAdded;
	}

	
	public void clear() {
		
		capacity = 10;
		
		T[] temp = (T[]) new Object[capacity];
		
		data = temp; 
		
		size = 0; 
	}

	
	public boolean contains(Object arg0) {
		


		
		for (int i = 0; i < data.length; i++) {
			if (data[i]==null) {
				return false;
			}
			if(data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {

		if(arg0.size()==0)
			return false; 
		
		
		boolean arrayContainsAll = true;;
		
		
		for(Object collection: arg0) {
			if(!contains(collection))
				arrayContainsAll = false;
		}
		
		return arrayContainsAll;
	}

	
	
	public boolean isEmpty() {
		
		if(size==0)
			return true;
		
		return false;
	}

	
	public Iterator<T> iterator() {
		ArrayCollectionIterator iterator = new ArrayCollectionIterator();
		return iterator;
	}

	
	public boolean remove(Object arg0) {
		
		
		if(!contains(arg0))
			return false;
		
		
		T[] temp = (T[]) new Object[capacity];
		
		
		
		int counter = 0;
		for(int i = 0; i<=size; i++) {
			counter++;
			
			
			if(!(data[i]==arg0)){
				temp[i]=data[i];
			}
			
			else {
				break;}
		}	
		
		
		
		
		for(int i = counter-1; i <=size;i++) {
			
			
			temp[i]=data[i+1];
		}
		
		
		data=temp; 
		
		size--; 
		
		return true;
	}


	
	public boolean removeAll(Collection<?> arg0) {
		boolean wereAnyItemsRemoved = false;
		
		
		
		
		for(Object item: arg0)
			if(remove(item))
				wereAnyItemsRemoved = true;
				
		return wereAnyItemsRemoved;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		
		ArrayCollection tempArray = new ArrayCollection<T>();
		
		
		boolean wereAnyItemsRetained = false;
		
		for(Object item: arg0)
			if(contains(item)) {
				
				tempArray.add(item);
				
				wereAnyItemsRetained=true;
			}
			
		
		
		data=(T[]) tempArray.data;
		size = tempArray.size;
		capacity = tempArray.capacity;
		
		return wereAnyItemsRetained;
	}

	public int size() {
		return size;
		
	}

	
	public Object[] toArray() {
		T[] temp = (T[]) new Object[size];
		for(int i = 0; i<size; i++)
			temp[i]=data[i];
		return temp;
	}

	
	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp){

		 
		ArrayList <T> sortedList = new ArrayList<T>();
		for (int i = 0; i <size; i++) {
			sortedList.add(data[i]);
		}
		

		  for (int i = 0; i < sortedList.size() - 1; i++) {
			  int j, minIndex;
			  for (j = i + 1, minIndex = i; j < sortedList.size(); j++)
			  if (cmp.compare(sortedList.get(j), sortedList.get(minIndex)) < 0)
			  minIndex = j;
			  T temp = sortedList.get(i);
			  sortedList.set(i, sortedList.get(minIndex));
			  sortedList.set(minIndex, temp);
			  }
		  return sortedList;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		int index = 0;
		boolean nextCall = false;
	
		public ArrayCollectionIterator()
		{
			
		}

		public boolean hasNext() {
			if(index<=size)
				return true;
			return false;
		}

		public T next() {
			nextCall = true;
			if(hasNext())
				return data[index++];
			else 
				throw new NoSuchElementException(); 
		}

		public void remove() {
			if(nextCall==true) {
				nextCall=false;
				
				index=index-1;
				ArrayCollection.this.remove(data[index]);

			}
			else {
				throw new IllegalStateException();
			}
			
		}

	}

}
