//comments: 32
//comment length: 2682
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	T data[]; 
	int size; 
	private ArrayCollection<T> reference;


	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
		reference = this;
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		
		
		T[] tempArray = (T[]) new Object[data.length*2]; 
		for(int i = 0; i < data.length; i++) {	
			tempArray[i] = data[i];
		}
		data = tempArray;	
	}

	
	
	public boolean add(T addContent) {
		if(contains(addContent)) {
			return false;	
		}
		if(size == data.length - 1)
			grow();
		data[size] = addContent;
		size++;
		return true;	
	}
	
	
	public boolean addAll(Collection<? extends T> items) {
		boolean addedOrNot = false;
		for(T item : items) {
			if(!contains(item))
				addedOrNot = this.add(item);
		}
		return addedOrNot;
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		size = 0;
		
		data = (T[]) new Object[10]; 
	}
	
	
	public boolean contains(Object newItem) {
		for(int i = 0; i < size; i++) 
			if(data[i].equals(newItem))
				return true;
		return false;
	}
	
	
	public boolean containsAll(Collection<?> newItems) {
		for(Object item : newItems)
			if(!this.contains(item))
				return false;
		return true;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}
	
	
	public boolean remove(Object myItem) {
		int tempIndex = 0;
		if(!this.contains(myItem))
			return false;
		
		other:
		for (int i = 0; i < size; i++) {
			if(data[i].equals(myItem)) {
				data[i] = null;
				tempIndex = i;
				size -= 1;
				break other;
			}
		}
		for (int i = tempIndex; i < this.size - 1; i++) {
			data[i] = data[i + 1];
		}
		return true;
	}
	
	
	public boolean removeAll(Collection<?> myItems) {
		boolean removedOrNot = false;
		for(Object item : myItems) {
			if(this.contains((item))) {
				this.remove(item);
				removedOrNot = true;	
			}
		}
		return removedOrNot;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean retainAll(Collection<?> myItems) {
		ArrayCollection<T> tempArray = new ArrayCollection<T>();
		boolean removedOrNot = false;
		for(T item : (ArrayCollection<T>)myItems) {
			if(this.contains(item)) {
				tempArray.add(item);
				removedOrNot = true;
				
			}
		}
		this.clear();
		this.addAll(tempArray);
		return removedOrNot;
	}
	
	
	public int size() {
		return size;
	}

	public Object[] toArray() {
		
		Object [] toReturn = new Object[size];
		for(int i = 0; i < size; i++) {
			toReturn[i] = data[i];
		}
		return toReturn;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> tempList = new ArrayList<T>();
		tempList.addAll(this);
		for (int i = 0; i < tempList.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < tempList.size(); j++)
				if (cmp.compare(tempList.get(j), tempList.get(minIndex)) < 0)
					minIndex = j;
			T temp = tempList.get(i);
			tempList.set(i, tempList.get(minIndex));
			tempList.set(minIndex, temp);
		}
		return tempList;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int index;
		public ArrayCollectionIterator()
		{
			
			index = 0;
		}

		public boolean hasNext() {
			
			return index < size;
		}

		public T next() {
			return data[index++];
		}

		public void remove() {
			
			reference.remove(data[index]);
		}

	}

}
