//comments: 25
//comment length: 4356
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
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		T growth[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < size; i++) {
			growth[i] = data[i];
		}
		data = growth;
	}

	
	public boolean add(T arg0) {
		if (this.size == this.data.length) {
			grow();
		}
		for(int i = 0; i < size + 1; i++) {
			if(this.data[i] == arg0) {
				return false;
			}
		}
		this.data[size] = arg0;
		size += 1;
		return true;

	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean elementsAdded = false;
		Object[] arrayForm = arg0.toArray();
		for(int i = 0; i < arg0.size(); i++) {
			@SuppressWarnings("unchecked")
			T element = (T) arrayForm[i];
			this.add(element);
			elementsAdded = true;
		}
		return elementsAdded;
	}

	
	public void clear() {
		ArrayCollectionIterator clearer = new ArrayCollectionIterator();
		while(clearer.hasNext()) {
			clearer.next();
			clearer.remove();
		}
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		if(this.isEmpty()) {
			return false;
		}
		for(int i = 0; i < this.size; i++) {
			if(this.data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		if(this.isEmpty()) {
			return false;
		}
		boolean contains = false;
		for(Object arg1 : arg0) {
			this.contains(arg1);
			if(this.contains(arg1)) {
				contains = true;
			}
		}
		return contains;
	}

	
	public boolean isEmpty() {
		for(int i = 0; i < data.length; i++) {
			if(data[i] != null) {
				return false;
			}
		}
		return true;
	}

	
	public Iterator<T> iterator() {
		ArrayCollectionIterator iterator = new ArrayCollectionIterator();
		return iterator;
	}

	
	public boolean remove(Object arg0) {
		if(this.isEmpty()) {
			return false;
		}
		if(this.contains(arg0)) {
			for(int i = 0; i < data.length; i++) {
				if(data[i] == arg0) {
					for(int j = i+1; j < size; j++) {
						data[i] = data[j];
					}
					size -= 1;
					return true;
				}

			}
		}

		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		if(this.isEmpty()) {
			return false;
		}
		boolean removed = false;
		for(Object arg1 : arg0) {
			this.remove(arg1);
			if(this.remove(arg1)) {
				removed = true;
			}
		}
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		if(this.isEmpty()) {
			return false;
		}
		Iterator<T> retainer = this.iterator();
		boolean removed = false;

		while(retainer.hasNext()) {
			Object current = retainer.next();
			if(arg0.contains(current) == false) {
				retainer.remove();
				removed = true;
			}
		}


		return removed;
	}

	public int size() {
		return this.size;
	}

	
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		T fit[] = (T[]) new Object[size];
		for(int i = 0; i < size; i++) {
			fit[i] = data[i];
		}
		return fit;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> sorted = (ArrayList<T>) new ArrayList<T>();
		for (int i = 0; i < this.size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < this.size; j++)
				if (cmp.compare(this.data[j], this.data[minIndex]) < 0)
					minIndex = j;
			T temp =  data[i];
			sorted.add(data[minIndex]);
			data[minIndex] = temp;
		}

		return sorted;
	}


	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int currentPosition;
		private boolean calledNext;

		public ArrayCollectionIterator()
		{

		}

		
		public boolean hasNext() {
			try {
				this.next();
			}catch(NoSuchElementException e) {
				return false;
			}
			return true;
		}

		
		public T next() {
			if(data[currentPosition + 1] == null) {
				throw new NoSuchElementException();
			}
			else {
				currentPosition++;
				calledNext = true;
				return data[currentPosition];
			}
		}

		
		public void remove() {
			if(calledNext == false) {
				throw new IllegalStateException();
			}
			else {
				ArrayCollection.this.remove(data[currentPosition]);
				calledNext = false;
			}
		}

	}

}
