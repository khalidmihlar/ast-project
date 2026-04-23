//comments: 46
//comment length: 4471
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	T data[]; 
	int size; 


	
	@SuppressWarnings("unchecked")
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		
		T newData[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++)
			newData[i] = data[i];
		this.data = newData;
		
		
	}

	
	public boolean add(T arg0) {
		
		if (this.size == this.data.length)
			this.grow();
		for (T o : this.data)
			if (arg0.equals(o))
				return false;
		this.data[size++] = arg0;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		int sizeBeforeAddAll = this.size;
		Iterator<? extends T> itr = arg0.iterator();
		while (itr.hasNext()) {
			T current = itr.next();
			for (int i = 0; i < this.size; i++) {
				if (current.equals(this.data[i])) {
					break;
				}
				if (i == this.size - 1)
					this.add(current);
			}
		}

		if (this.size == sizeBeforeAddAll)
			return false;
		return true;
	}

	
	public void clear() {
		
		for (int i = 0; i < this.size; i++)
			data[i] = null;
		this.size = 0;
	}

	
	public boolean contains(Object arg0) {
		
		for (int i = 0; i < this.size; i++)
			if (arg0.equals(this.data[i]))
				return true;
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		
		Iterator<?> itr = arg0.iterator();
		while (itr.hasNext()) {
			if (!this.contains(itr.next()))
				return false;
		}
		return true;
	}

	
	public boolean isEmpty() {
		
		if (this.size == 0)
			return true;
		return false;
	}

	
	public Iterator<T> iterator() {
		
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		
		boolean contains = false;
		for (int i = 0; i < this.size; i++) {
			if (this.data[i].equals(arg0)) {
				contains = true;
				this.size--;
				for (int index = i; index < this.size; index++)
					data[index] = data[index + 1];
				data[this.size] = null;
				break;
			}
		}
		return contains;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		
		int sizeBeforeRemoved = this.size;
		Iterator<?> itr = arg0.iterator();
		while (itr.hasNext()) {
			this.remove(itr.next());
		}
		if (this.size == sizeBeforeRemoved)
			return false;
		return true;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		Iterator<T> thisItr = this.iterator();
		boolean equalsOrNot = false;
		int sizeBefore = this.size;

		while (thisItr.hasNext()) {
			Iterator<?> inputItr = arg0.iterator();
			equalsOrNot = false;
			T currentThis = thisItr.next();
			while (inputItr.hasNext()) {
				Object currentInput = inputItr.next();
				if (currentThis.equals(currentInput)) {
					equalsOrNot = true;
					break;
				}
			}
			if (equalsOrNot == false)
				thisItr.remove();
		}
		if (this.size == sizeBefore)
			return false;
		return true;
	}

	
	public int size() {
		
		return this.size;
	}

	
	public Object[] toArray() {
		
		T copy[] = (T[]) new Object[this.size];
		for (int i = 0; i < this.size; i++)
			copy[i] = this.data[i];
		return copy;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> sortedCollection = new ArrayList<T>();
		for (int i = 0; i < this.size; i++)
			sortedCollection.add(this.data[i]);
		for (int i = 0; i < sortedCollection.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < sortedCollection.size(); j++)
				if (cmp.compare(sortedCollection.get(j), sortedCollection.get(minIndex)) < 0)
					minIndex = j;
			T temp = sortedCollection.get(i);
			sortedCollection.set(i, sortedCollection.get(minIndex));
			sortedCollection.set(minIndex, temp);
		}
		return sortedCollection;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private int nextIdx;
		private boolean nextCalled = false;
		private T lastSeenItem;

		public ArrayCollectionIterator() {
			
		}

		
		public boolean hasNext() {
			
			return nextIdx < size;
		}

		
		public T next() {
			
			if (!this.hasNext())
				throw new NoSuchElementException();
			nextCalled = true;
			lastSeenItem = data[nextIdx++];
			return lastSeenItem;
		}

		
		public void remove() {
			if (nextCalled == false)
				throw new IllegalStateException();
			else {
				ArrayCollection.this.remove(lastSeenItem);
				nextCalled = false;
				nextIdx--;
			}
			
		}
	}
}
