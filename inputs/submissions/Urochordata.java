//comments: 66
//comment length: 5307
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
		T[] temp = (T[]) new Object[data.length * 2];

		
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}

		data = temp;
	}

	
	@Override
	public boolean add(T arg0) {

		
		
		
		for (T element : data) {
			if (element != null && element.equals(arg0)) {
				return false;
			}
		}

		
		if (size == data.length) {
			grow();
		}

		
		
		data[size] = arg0;
		size++;
		return true;

	}

	
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		
		T[] array = (T[]) arg0.toArray();

		if (array.length == 0) {
			return false;
		}

		boolean itemWasAdded = false;
		

		for (int i = 0; i < array.length; i++) {
			boolean containsItem = false;
			
			for (int j = 0; j < size(); j++) {
				if (array[i].equals(data[j])) {
					containsItem = true;
				}
			}
			
			
			if (containsItem != true) {
				add(array[i]);
				itemWasAdded = true;
			}
		}

		return itemWasAdded;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		data = (T[]) new Object[10];
	}

	
	@Override
	public boolean contains(Object arg0) {
		
		for (int i = 0; i < size(); i++) {
			if (data[i].equals(arg0)) {
				return true;
			}
		}
		
		return false;
		








	}

	
	@SuppressWarnings("unchecked")
	@Override
	public boolean containsAll(Collection<?> arg0) {
		
		T[] array = (T[]) arg0.toArray();

		for (int i = 0; i < array.length; i++) {
			if (!contains(array[i])) {
				return false;
			}
		}
		return true;
	}

	
	@Override
	public boolean isEmpty() {
		
		
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	
	@Override
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	@Override
	public boolean remove(Object arg0) {
		if (!contains(arg0)) {
			return false;
		} else {
			for (int i = 0; i < size(); i++) {
				int removeIndex;
				if (arg0.equals(data[i])) {
					
					
					removeIndex = i;
					data[i] = null;
					for (int j = removeIndex; j < size() - 1; j++) {
						data[j] = data[j + 1];
					}
					
				}
			}
			size--;
			return true;
		}
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean removeAll(Collection<?> arg0) {
		T[] array = (T[]) arg0.toArray();
		
		boolean dataArrayHasChanged = false;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < size(); j++) {
				if (array[i].equals(data[j])) {
					remove(data[j]);
					dataArrayHasChanged = true;
				}
			}
		}
		
		


















		
		return dataArrayHasChanged;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> arg0) {
		boolean dataArrayHasChanged = false;
		T[] array = (T[]) arg0.toArray();
		
		Iterator<T> iterator = iterator();

		for (int i = 0; i < array.length; i++) {
			while (iterator.hasNext()) {
				T nextItem = iterator.next();
				if (!nextItem.equals(array[i])) {
					iterator.remove();
					dataArrayHasChanged = true;
				}
			}
		}

		return dataArrayHasChanged;
	}

	
	@Override
	public int size() {
		return size;
	}

	
	@Override
	public Object[] toArray() {
		Object[] returnArray = new Object[size()];
		for (int i = 0; i < size(); i++) {
			returnArray[i] = data[i];
		}
		return returnArray;
	}

	
	@SuppressWarnings("hiding")
	@Override
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		T[] list = (T[]) this.toArray();
		ArrayList<T> test = new ArrayList<>();

		for (int i = 0; i < list.length - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.length; j++) {
				if (cmp.compare(list[j], list[minIndex]) < 0) {
					minIndex = j;
				}
			}
			T temp = list[i];
			list[i] = list[minIndex];
			list[minIndex] = temp;
		}

		for (int i = 0; i < list.length; i++) {
			test.add(list[i]);
		}

		return test;
	}

	private class ArrayCollectionIterator implements Iterator<T> {
		private int nextIndex;
		private boolean canRemove;

		public ArrayCollectionIterator() {
			nextIndex = 0;
			canRemove = false;
		}

		
		@Override
		public boolean hasNext() {
			return nextIndex < size();
		}

		
		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			canRemove = true;
			return data[nextIndex++];

		}

		
		@Override
		public void remove() {
			if (canRemove) {
				ArrayCollection.this.remove(data[nextIndex-1]);
				canRemove = false;
				
				nextIndex--;
			} else {
				throw new IllegalStateException();
			}

		}
		
		@Override
		public String toString() {
			return "next index: " + nextIndex + " can remove: " + canRemove;
		}

	}

}
