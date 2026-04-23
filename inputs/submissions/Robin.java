//comments: 32
//comment length: 2546
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
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

		T[] temp = (T[]) new Object[size * 2];

		for (int i = 0; i < size; i++) {

			temp[i] = data[i];

		}
		
		data = temp;

	}

	

	public boolean add(T arg0) {
		if (size == data.length)
			grow(); 
		for (int i = 0; i < size; i++) {
			if (this.contains(arg0))
				return false;
		}
		data[size++] = arg0;

		return true;
	}

	

	public boolean addAll(Collection<? extends T> arg0) {
		boolean placeHolder = false;

		for (T argument : arg0) { 
			if (add(argument))
				placeHolder = true;

		}
		return placeHolder;
	}

	

	public void clear() {
		for (int i = 0; i < data.length; i++) {
			data[i] = null;
			size = 0;

		}

	}

	

	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (arg0.equals(data[i]))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		boolean placeHolder = false;

		for (Object argument : arg0) { 

			if (!contains(argument))
				placeHolder = true;

		}
		return placeHolder;
	}

	public boolean isEmpty() {
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null) {
				return false;

			}

		}
		return true;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();

	}

	public boolean remove(Object arg0) {
		boolean result = false;
		for (int i = 0; i < data.length; i++) {
			if (arg0.equals(data[i])) {
				result = true;
				size--;
				for (int j = i; j < size; j++) {
					data[j] = data[j + 1];
				}

			}
		}

		return result;
	}

	public boolean removeAll(Collection<?> arg0) {
		boolean placeHolder = false;
		boolean result = true;
		for (Object argument : arg0) {
			placeHolder = remove(argument);
			if (placeHolder == true)
				result = true;
		}
		return result;
	}

	public boolean retainAll(Collection<?> arg0) {

		return false;
	}

	public int size() {
		return this.size;
	}

	public Object[] toArray() {
		Object[] result = new Object[this.size];
		for (int i = 0; i < size; i++) {
			result[i] = data[i];
		}
		return result;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		
		
		
		for (int i = 0; i < size; i++) {
			T min;
			min = data[i];
			for (int j = 0; j < size; j++) {
				if (cmp.compare(min, data[j]) > 0)

					min = data[j];
				data[i] = min;
			}
		}
		return new ArrayList<T>(Arrays.asList(data));
		 
	}













	private class ArrayCollectionIterator implements Iterator<T> {
		private int position;
		private boolean canRemove;

		public ArrayCollectionIterator() {

			this.position = 0;
			this.canRemove = false;

		}

		public boolean hasNext() {
			if (position < ArrayCollection.this.data.length)
				return true;
			else
				return false;
		}

		public T next() {
			
			return ArrayCollection.this.data[position++];
		}

		public void remove() {

			if (canRemove == false) {
				throw new IllegalStateException("");

			}

			ArrayCollection.this.remove(ArrayCollection.this.data[position]);
			position--;
		}

	}

}
