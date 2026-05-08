//comments: 32
//comment length: 5273
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	T data[]; 
	private int size; 


	
	@SuppressWarnings("unchecked")
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		ArrayCollection<T> newCol = new ArrayCollection<T>();
		newCol.data = (T[]) new Object[this.size * 2];
		newCol.addAll(this);
		this.data = (T[]) newCol.data;
	}

	
	public boolean add(T arg0) {
		if (size == data.length) { 
			this.grow();
		}
		if (!this.contains(arg0)) {
			data[size] = arg0;
			size++;
			return true;
		}

		return false;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean retVal = false;
		for (T a : arg0) {
			if (this.add(a))
				retVal = true; 
		}
		return retVal;
	}

	
	public void clear() {
		T newData[] = (T[]) new Object[10];
		this.size = 0;
		this.data = newData;
	}

	
	public boolean contains(Object arg0) {
		if (this.isEmpty()) {
			return false;
		}

		for (int i = 0; i < this.size; i++) {
			if (this.data[i].equals(arg0)) {
				return true;
			}
		}

		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {

		for (Object a : arg0) {
			if (!this.contains(a)) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {

		return this.size() == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		if (!this.contains(arg0))
			return false;
		int idx = 0;
		for (Object a : this) {
			if (a.equals(arg0)) {
				break;
			}
			idx++;
		}
		for (int i = idx; i < size; i++) {
			this.data[i] = this.data[i + 1];

		}
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		if (arg0.size() == 0 || this.size == 0) {
			return false;
		}

		boolean retVal = false;
		for (Object o : arg0) {
			if (this.contains(o)) {
				this.remove(o);
				retVal = true;
			}
		}
		return retVal;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		if (arg0.isEmpty() || this.isEmpty()) {
			this.clear();
			return true;
		}

		ArrayCollection<Object> notCenter = new ArrayCollection<Object>();
		
		
		boolean retVal = false;
		for (Object o : this) {
			if (!arg0.contains(o)) {
				notCenter.add(o);
				retVal = true;
			}
		}

		for (Object b : notCenter)
			this.remove(b);
		return retVal;
	}

	public int size() {
		return this.size;
	}

	
	public Object[] toArray() {
		Object retA[] = new Object[this.size];
		for (int i = 0; i < this.size; i++) {
			retA[i] = this.data[i];
		}
		return retA;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>(); 
		Object[] arr = this.toArray(); 
		for (Object i : arr)
			list.add((T) i);

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

	private class ArrayCollectionIterator implements Iterator<T> {
		private int nextIdx;

		
		public ArrayCollectionIterator() {
			nextIdx = 0;
		}

		
		public boolean hasNext() {
			return nextIdx < size;
		}

		
		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			return data[nextIdx++];

		}

		
		public void remove() {
			if (nextIdx > 0) {
				ArrayCollection.this.remove(next());
			} else
				throw new IllegalStateException();

		}

	}

}
