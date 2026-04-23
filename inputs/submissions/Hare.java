//comments: 25
//comment length: 5391
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	private T data[]; 
	private int size; 


	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		T[] grownArray = (T[]) new Object[2*size];
		for (int i = 0; i < size; i++) {
			grownArray[i] = data[i];
		}
		data = grownArray;
	}


	
	public boolean add(T arg0) {
		if (!this.contains(arg0)) {
			if (size == data.length) {
				this.grow();
			}
			this.data[size] = arg0;
			size++;
			return true;
		}
		return false;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean itemAdded = false;
		for (T a : arg0) {
			if(this.add(a))
				itemAdded = true;
		}
		return itemAdded;

	}

	
	public void clear() {
		
		for(int i = 0; i < size; i++) {
			this.data[i] = null;
		}
		size = 0;	
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (arg0.equals(data[i])) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		boolean itemAdded = false;
		for (Object a : arg0) {
			if(this.contains(a))
				itemAdded = true;
		}
		return itemAdded;
	}

	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}
	
	
	public Iterator<T> iterator() {
		ArrayCollectionIterator iterator = new ArrayCollectionIterator();
		return iterator;
	}

	
	public boolean remove(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (arg0.equals(this.data[i])) {
				for (int j = i; j < size; j++) {
					this.data[j] = this.data[j+1];
				}
				this.data[size] = null;
				size--;
				return true;
			}
		}			
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean itemRemoved = false;
		for (Object a : arg0) {
			if(this.remove(a))
				itemRemoved = true;
		}
		return itemRemoved;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean itemRemoved = false;
		for(int i = 0; i < size; i++) {
			if(!arg0.contains(this.data[i])) {
				for (int j = i; j < size; j++) {
					this.data[j] = this.data[j+1];
				}
				this.data[size] = null;
				size--;
				itemRemoved = true;
				i--;
			}
		}
		return itemRemoved;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] objectArray = new Object[size];
		for (int i = 0; i < size; i++) {
			objectArray[i] = this.data[i];
		}
		return objectArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> list = new ArrayList<T>(this);
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
				if (cmp.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
		return list;
	}
	




	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int nextIdx;
		boolean canRemove = false;
		
		public ArrayCollectionIterator()
		{
			nextIdx = 0;
		}

		
		public boolean hasNext() {
			return nextIdx < size;
		}

		
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			canRemove = true;
			return data[nextIdx++];
		}

		
		public void remove() {
			if (!canRemove) {
				throw new IllegalStateException();
			}
			ArrayCollection.this.remove(data[nextIdx - 1]);
			nextIdx--;
			canRemove = false;
			
		}

	}

}