//comments: 31
//comment length: 3896
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;


public class ArrayCollection<T> implements Collection<T> {

	
	Object data[]; 
	private int size; 


	
	public ArrayCollection() {
		size = 0;
		
		
		data = new Object[10]; 
	}

	
	private void grow() {
		Object[] grown = new Object[data.length*2];
		for(int i=0; i<data.length; i++) grown[i] = data[i];
		data = grown;
	}

	
	public boolean add(T arg0) {
		if(contains(arg0)) return false;
		if(size == data.length) grow();

		data[size++] = arg0;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean change = false;
		for(T t : arg0) if(add(t)) change = true;
		return change;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		size = 0;
		data = (T[]) new Object[10];
	}

	
	public boolean contains(Object arg0) {
		if(arg0 == null) return false;
		
		for(int i=0; i<size; i++) if(data[i].equals(arg0)) return true;
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for(Object o : arg0) if(!contains(o)) return false;
		return true;
	}

	
	public boolean isEmpty() {
		return size == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		for(int i=0; i<size; i++) {
			if(data[i].equals(arg0)) {
				for(int j=i; j<(size-1); j++) data[j] = data[j+1];
				data[--size] = null;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean change = false;
		for(int i=0; i<size; i++) {
			if(arg0.contains(data[i])) {
				for(int j=i; j<(size-1); j++) data[j] = data[j+1];
				data[--size] = null;
				change = true;
			}
		}
		return change;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean change = false;
		for(int i = 0; i < size; i++) {
			if(!arg0.contains(data[i])) {
				for(int j=i; j<(size-1); j++) data[j] = data[j+1];
				data[--size] = null;
				change = true;
			}
		}
		return change;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] resized = new Object[size];
		for(int i=0; i<size; i++) resized[i] = data[i];
		return resized;
	}

	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		
		ArrayList<T> sorted = new ArrayList<T>();
		for(int i=0; i<size; i++)
			sorted.add((T) data[i]);

		for(int i=0; i<sorted.size()-1; i++) {
			int j, minIndex;
			for(j=i+1, minIndex=i; j<sorted.size(); j++)
				if(cmp.compare(sorted.get(j), sorted.get(minIndex)) < 0)
					minIndex = j;
			T temp = sorted.get(i);
			sorted.set(i, sorted.get(minIndex));
			sorted.set(minIndex, temp);
		}
		
		return sorted;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {
		
		private int curr;
		
		
		public ArrayCollectionIterator() {
			this.curr = -1;
		}

		
		public boolean hasNext() {
			return curr < (size-1);
		}

		
		@SuppressWarnings("unchecked")
		public T next() {
			return (T) data[++curr];
		}

		
		public void remove() {
			for(int j=curr; j<(size-1); j++) data[j] = data[j+1];
			data[--size] = null;
		}

	}

}
