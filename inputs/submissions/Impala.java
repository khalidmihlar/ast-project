//comments: 35
//comment length: 4156
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
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		T[] tempData = (T[]) new Object[this.data.length * 2];

		for (int i = 0; i < this.data.length; i++) {
			tempData[i] = this.data[i];
		}

		this.data = tempData;
	}

	
	public boolean add(T arg0) {
		if (this.contains(arg0))
			return false;
		if (size == this.data.length) {
			this.grow();
		}

		this.data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		Boolean somethingAdded = false;
		for (T var : arg0)
			if (this.add(var))
				somethingAdded = true;

		return somethingAdded;
	}

	
	public void clear() {
		this.size = 0;
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (this.data[i].equals(arg0)) {
				return true;
			}
		}

		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		Boolean doesntContain = true;
		for (Object var : arg0)
			if (!this.contains(var))
				doesntContain = false;
		return doesntContain;

	}

	
	public boolean isEmpty() {
		if (this.size < 1)
			return true;
		else
			return false;
		
	}

	public Iterator<T> iterator() {

		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		for (int i = 0; i < this.size; i++) { 
			if (this.data[i].equals(arg0)) {
				for (int j = i; j < this.size - 1; j++) { 
					this.data[j] = this.data[j + 1];
				}
				size--;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		Boolean somethingRemoved = false;
		for (Object var : arg0)
			if (this.remove(var))
				somethingRemoved = true;

		return somethingRemoved;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<T> itr = this.iterator();
		T next;
		Boolean hasRemoved = false; 

		while (itr.hasNext()) {
			next = itr.next();
			Boolean doesContain = false; 
											

			@SuppressWarnings("unchecked")
			Iterator<T> arg0itr = (Iterator<T>) arg0.iterator();
			T nextCollection;

			while (arg0itr.hasNext()) {
				nextCollection = arg0itr.next();

				if (next.equals(nextCollection)) {
					doesContain = true; 
				}

			}
			if (!doesContain) {
				
				
				
				itr.remove();
				hasRemoved = true;
			}
		}

		return hasRemoved;

	}

	
	public int size() {
		return this.size;
	}

	
	public Object[] toArray() {
		Object[] arr = new Object[size];
		for (int i = 0; i < this.size; i++) {
			arr[i] = this.data[i];
		}
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> sortedList = new ArrayList();
		for (int k = 0; k < this.size(); k++)
			sortedList.add(this.data[k]);
		for (int i = 0; i < this.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < this.size(); j++)
				if (cmp.compare(this.data[j], this.data[minIndex]) < 0)
					minIndex = j;
			T temp = sortedList.get(i);
			sortedList.set(i, this.data[minIndex]);
			sortedList.set(minIndex, temp);

		}
		return sortedList;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private int counter;
		@SuppressWarnings("unused")
		private Boolean canDelete; 
									
									

		public ArrayCollectionIterator() {
			super();
			counter = 0;
			canDelete = false;
		}

		public boolean hasNext() {
			if (counter < size)
				return true;
			return false;
		}

		public T next() {
			if (hasNext() == true) {
				this.canDelete = true;
				return data[counter++];
			}
			throw new NoSuchElementException();
		}

		public void remove() {
			if (canDelete = true) {
				ArrayCollection.this.remove(data[counter - 1]);
				this.canDelete = false;
			}

		}

	}

}
