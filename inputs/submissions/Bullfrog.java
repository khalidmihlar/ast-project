//comments: 23
//comment length: 2924
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
		T[] temp = (T[]) new Object[this.data.length * 2];
		for (int i = 0; i < this.size; i++) {
			temp[i] = this.data[i];
		}
		this.data = temp;
	}

	
	public boolean add(T arg0) {
		if (this.contains(arg0)) {
			return false;
		}
		if (data.length == this.size) {
			this.grow();
		}
		for (int i = 0; i < this.data.length; i++) {
			if (data[i] == null) {
				data[i] = arg0;
				this.size++;
				return true;
			}
		}
		return false;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		Iterator<? extends T> itr = arg0.iterator();
		int falseCount = 0;
		while (itr.hasNext()) {
			if (!this.add(itr.next())) {
				falseCount++;
			}
		}
		if (falseCount != arg0.size()) {
			return true;
		}
		return false;
	}

	
	public void clear() {
		for (int i = 0; i < this.size; i++) {
			this.data[i] = null;
		}
		this.size = 0;
	}

	
	public boolean contains(Object arg0) {
		Iterator<? extends T> itr = this.iterator();
		while (itr.hasNext()) {
			if (itr.next().equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for (Object element : arg0) {
			if (!this.contains(element)) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		if (this.size == 0) {
			return true;
		}
		return false;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator(this);
	}

	
	public boolean remove(Object arg0) {
		if (!this.contains(arg0)) {
			return false;
		}
		for (int i = 0; i < this.size; i++) {
			if (data[i].equals(arg0)) {
				for (int j = i; j < this.size; j++) {
					data[j] = data[j + 1];
					if (j == this.size - 1) {
						data[j + 1] = null;
					}
				}
				this.size--;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		int falseCount = 0;
		for (Object element : arg0) {
			if (!this.remove(element)) {
				falseCount++;
			}
		}
		if (falseCount == arg0.size()) {
			return false;
		}
		return true;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<?> itr = this.iterator();
		T current;
		int zeroIfFalse = 0;
		while (itr.hasNext()) {
			current = (T) itr.next();
			if (!arg0.contains(current)) {
				this.remove(current);
				zeroIfFalse++;
			}
		}
		if (zeroIfFalse != 0) {
			return true;
		}
		return false;
	}

	
	public int size() {
		return this.size;
	}

	
	public Object[] toArray() {
		Object[] returnArray = new Object[this.size];
		for (int i = 0; i < this.size; i++)
			returnArray[i] = this.data[i];
		return returnArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public int dataSize() {
		return this.data.length;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		for (int i = 0; i < this.data.length - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < this.data.length; j++)
				if (cmp.compare(this.data[j], this.data[minIndex]) < 0)
					minIndex = j;
			T temp = this.data[i];
			data[i] = this.data[minIndex];
			data[minIndex] = temp;
		}
		return null;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private int cursor;
		private int size;
		private T data[];

		public ArrayCollectionIterator(Collection<?> arg0) {
			this.cursor = 0;
			this.size = arg0.size();
			data = (T[]) new Object[arg0.size()];
			for (int i = 0; i < arg0.size(); i++) {
				this.data[i] = (T) arg0.toArray()[i];
			}

		}

		public boolean hasNext() {
			if (this.cursor < this.size) {
				return true;
			}
			return false;
		}

		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			this.cursor++;
			return data[cursor - 1];
		}

		public void remove() {
			this.data[cursor - 1] = null;
		}

	}

}
