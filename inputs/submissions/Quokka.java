//comments: 45
//comment length: 5579
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class ArrayCollection<T> implements Collection<T> {

	T data[]; 
	int size; 
	Comparator<? super T> cmp;


	
	@SuppressWarnings("unchecked")
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		
		
		
		T newData[] = (T[]) new Object[size * 2];
		for (int x = 0; x < data.length; x++) {
			newData[x] = data[x];
		}
		data = newData;
	}

	
	public boolean add(T arg0) {
		
		if (this.contains(arg0)) {
			return false;
		}
		if (size == data.length)
			grow();

		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		

		boolean foundAddable = false;
		for (T x : arg0) {
			if (add(x)) {
				foundAddable = true;
			}
			return foundAddable;
		}
		for (T x : data)
			add(x);
		return false;
	}

	
	
	public void clear() {
		
		size = 0;
		data = (T[]) new Object[size];
	}
	
	
	public boolean contains(Object arg0) {
		
		if (data.length > 0)
			if (data[0] == null)
					return false;
		
		for (T t : data)
			if (t != null)
				if (t.equals(arg0))
					return true;
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		
		for (Object x : arg0) {
			if (!(arg0.contains(x))) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		
		if (data.length == 0) {
			return true;
		}
		return false;
	}

	
	public Iterator<T> iterator() {
		
		ArrayCollectionIterator newIterator = new ArrayCollectionIterator();
		return newIterator;
	}

	
	public boolean remove(Object arg0) {
		

		for (int x = 0; x < size; x++) {
			if (data[x] != null)
				if (data[x].equals(arg0)) {
					for (int j = x; j < size; j++)
						data[x] = data[x + 1];
					return true;
			}

		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {

		
		boolean foundRemovable = false;
		for (Object x : arg0) {
			if (remove(x))
				foundRemovable = true;
		}
		return foundRemovable;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		

		boolean foundRemovable = false;
		for (Object x : data) {
			if (!(arg0.contains(x)))
				if (remove(x))
					foundRemovable = true;
		}
		return foundRemovable;
	}
	
	
	public int size() {
		

		return size;
	}

	
	public Object[] toArray() {
		
		Object[] newData = new Object[size];
		for (int x = 0; x < size; x++) {
			newData[x] = this.data[x];
		}
		return newData;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {

		ArrayList<T> sorted = new ArrayList<T>();
		for (T t : data)
			sorted.add(t);

		for (int i = 0; i < size; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++)
				if (cmp.compare(sorted.get(j), sorted.get(minIndex)) < 0)
					minIndex = j;

			T temp = sorted.get(i);
			sorted.set(i, sorted.get(minIndex));
			sorted.set(minIndex, temp);
			
		}
		return sorted;
	}



		private class ArrayCollectionIterator implements Iterator<T> {
			private int posIndex;
			private boolean canRemove;

			public ArrayCollectionIterator() {
				
				posIndex = 0;
				canRemove = false;
			}
			
			
			public boolean hasNext() {
				
				if (posIndex < ArrayCollection.this.size()) {
					return true;
				}
				return false;
			}

			
			public T next() {
				
				if (hasNext()) {
					return data[posIndex++];
				}
				throw new NoSuchElementException();
			}

			
			public void remove() {
				
				if (canRemove) {
					posIndex--;
					canRemove = false;
					ArrayCollection.this.remove(data[posIndex]);
				} else
					throw new IllegalStateException();
			}
		}
	}

