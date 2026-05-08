//comments: 33
//comment length: 5594
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

		int newSize = data.length * 2;

		T[] arr = (T[]) new Object[newSize];

		for (int j = 0; j < data.length; j++) {
			arr[j] = data[j];

		}
		data = arr;

	}

	

	public boolean add(T arg0) {

		if (contains(arg0)) {

			return false;

		}
		if (size == data.length) {
			this.grow();
		}
		data[size] = arg0;
		size++;
		return true;

	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean hasAdded = false;

		for (T s : arg0) {

			if (add(s)) {

				hasAdded = true;
			}

		}

		return hasAdded;
	}

	
	public void clear() {

		for (int i = 0; i <= size; i++) {
			data[i] = null;
		}

		size = 0;

	}

	
	public boolean contains(Object arg0) {

		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				return true;

			}

		}

		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {

		for (Object s : arg0) {

			if (!contains(s)) {
				return false;
			}
		}
		return true;

	}

	
	public boolean isEmpty() {
		if (size == 0) {
			return true;

		} else
			return false;
	}

	
	public Iterator<T> iterator() {

		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {

		
		int index = 0;

		if (!contains(arg0))
			return false;
		else

		{

			
			for (int i = 0; i < size; i++) {
				if (data[i].equals(arg0)) {
					index = i;
					data[i] = null;
				}
			}

			for (int j = index; j < size; j++) {
				data[j] = data[j + 1];

			}
			size = size - 1;

			return true;
		}

	}

	
	public boolean removeAll(Collection<?> arg0) {

		boolean ans = false;
		for (Object s : arg0) {

			{
				if (remove(s)) {
					ans = true;
				}
			}

		}

		return ans;
	}

	
	public boolean retainAll(Collection<?> arg0) {

		boolean ans = false;
		Iterator<?> itr = this.iterator();
		while (itr.hasNext()) {

			if (!arg0.contains(itr.next())) {

				itr.remove();
				ans = true;

			}
		}
		return ans;

	}

	
	public int size() {

		return this.size;
	}

	
	public Object[] toArray() {
		@SuppressWarnings("unchecked")

		T[] arr = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {

			arr[i] = data[i];

		}

		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {

		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {

		ArrayList<T> ans = new ArrayList<T>(size);

		for (int i = 0; i < size; i++) {

			int j;
			int minIndex = 0; 

			for (j = i + 1; j < size; j++) {
				if (cmp.compare(data[j], data[i]) < 0) {
					minIndex = j;

					T temp = data[i];
					data[i] = data[j];

					data[minIndex] = temp;

				}
			}
			ans.add(data[i]);

		}

		return ans;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {

		public ArrayCollectionIterator() {

		}

		int nextIndex = 0;
		boolean allowed = false;

		
		public boolean hasNext() {

			if (nextIndex < size)
				return true;
			else
				return false;
		}

		
		public T next() {

			if (hasNext() == false) {
				throw new NoSuchElementException("No more items to iterate through");
			}
			allowed = true;

			return data[nextIndex++];
		}

		
		public void remove() {

			if (allowed = true) {
				int newIndex = nextIndex - 1;

				
				data[newIndex] = null;

				
				for (int j = newIndex; j < size; j++) {
					data[j] = data[j + 1];

				}

				
				size = size - 1;

				
				nextIndex--;
			}

			else {
				throw new IllegalStateException(
						"Cannot call remove() twice in a row without calling next() in betwen remove() calls");
			}
			allowed = false;
		}

	}



}
