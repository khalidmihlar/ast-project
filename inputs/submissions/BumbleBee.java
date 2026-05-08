//comments: 77
//comment length: 6587
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
		
		T temp[] = (T[]) new Object[this.size * 2];

		
		for (int i = 0; i < data.length; i++) {
			temp[i] = this.data[i];
		}

		
		this.data = temp;

	}

	
	public boolean add(T arg0) {
		
		if (this.contains(arg0))
			return false;

		
		if (size == this.data.length)
			this.grow();

		
		this.data[size] = arg0;
		this.size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		
		int count = 0;

		
		for (T item : arg0)

			
			if (this.add(item))
				count++;

		
		
		return count > 0;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		
		T temp[] = (T[]) new Object[10];

		
		this.data = temp;

		
		this.size = 0;

	}

	
	public boolean contains(Object arg0) {
		
		for(int i = 0; i < this.size; i++)
		{
			if(this.data[i].equals(arg0))
			{
				return true;
			}
		}

		return false;
	}

	
	@SuppressWarnings("unchecked")
	public boolean containsAll(Collection<?> arg0) {
		
		int count = 0;

		
		for (Object item : arg0)

			
			if (this.contains((T) item))
				count++;

		
		
		return count == arg0.size();

	}

	
	public boolean isEmpty() {

		return size == 0;
	}

	
	public Iterator<T> iterator() {

		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		
		
		int removeIndex = 0;
		boolean result = false;

		
		for (int i = 0; i < this.data.length; i++) {

			
			
			if (arg0.equals(this.data[i])) {
				this.data[i] = null;
				removeIndex = i;
				result = true;
				break;
			}
		}

		
		
		for (int j = removeIndex; j < size - 1; j++) {
			T temp = this.data[j + 1];
			this.data[j + 1] = null;
			this.data[j] = temp;
		}

		if (result)
			size--;

		return result;
	}

	
	@SuppressWarnings("unchecked")
	public boolean removeAll(Collection<?> arg0) {
		
		int count = 0;

		
		for (Object item : arg0)

			
			if (this.remove((T) item))
				count++;

		
		
		return count > 0;

	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		int count = 0;

		
		Iterator<T> itr = new ArrayCollectionIterator();

		
		while (itr.hasNext()) {
			
			T temp = itr.next();

			
			
			if (!arg0.contains(temp)) {
				count++;
				itr.remove();
			}
		}
		
		return count != 0;
	}

	
	public int size() {

		return this.size;
	}

	
	public Object[] toArray() {
		
		Object[] arr = new Object[this.size];

		
		for (int i = 0; i < this.size; i++)
			arr[i] = this.data[i];

		
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	@Override
	public String toString() {
		String result = "";

		for (T item : this.data) {
			result += " " + item;
		}

		return result;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();

		
		for (int idx = 0; idx < this.size; idx++)
			list.add(this.data[idx]);

		
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

		private int index;

		private boolean hasNextBeenCalled;

		
		public ArrayCollectionIterator() {
			this.index = 0;
			this.hasNextBeenCalled = false;
		}

		
		public boolean hasNext() {
			return this.index != size;
		}

		
		public T next() {
			
			hasNextBeenCalled = true;

			
			return data[index++];
		}

		
		public void remove() {
			
			
			if (hasNextBeenCalled) {
				hasNextBeenCalled = false;
				ArrayCollection.this.remove(data[--index]);
			}
			
			else {
				throw new IllegalStateException();
			}
		}

	}

}
