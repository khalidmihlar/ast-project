//comments: 31
//comment length: 5023
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
		T[] grownData = (T[]) new Object[data.length * 2];
		for (int i = 0; i < size; i++) {
			grownData[i] = data[i];
		}
		data = grownData;
	}

	
	public boolean add(T arg0) {

		if (this.contains(arg0)) 
			return false;

		if (size == data.length) { 
			grow();
		}

		data[size] = arg0;
		size++;

		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean flag = false;

		for (T item : arg0) {
			if (add(item)) {
				flag = true;
			}
		}

		return flag;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		data = (T[]) new Object[10];
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0))
				return true;
		}

		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		boolean flag = true;

		for (Object x : arg0) {
			if (!this.contains(x))
				flag = false;
		}
		return flag;
	}

	
	public boolean isEmpty() {
		return size == 0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				deleteAndShift(i);
				size--;
				return true;
			}
		}

		return false;
	}

	private void deleteAndShift(int index) {
		data[index] = null;
		for (int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
	}

	
	public boolean removeAll(Collection<?> arg0) {
		
		boolean flag = false;
		
		for (Object x : arg0) {
			if (remove(x))
				flag = true;
		}
		
		return flag;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		boolean flag = false;
		Iterator<T> iter = iterator();
		
		while(iter.hasNext()) {
			T x = iter.next();
			if(!arg0.contains(x)) {
					iter.remove();
					flag = true;
			}
		}
		
		return flag;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		
		Object[] returnData = new Object[size];
		
		for (int i = 0; i < size; i++) {
			returnData[i] = data[i];
		}
		
		return returnData;
	}

	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T[] toArray(T[] arg0) {
		
		if (arg0.length < size)
			return arg0;
		
		for (int i = 0; i < size; i++) {
			arg0[i] = (T) data[i];
		}
		
		return arg0;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		
		ArrayList<T> list = new ArrayList<T>();
		
		list.addAll(this); 
		
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

		int nextIndex;
		T lastItem;

		public ArrayCollectionIterator() {
			
			nextIndex = 0;
			lastItem = null;
			
		}

		
		public boolean hasNext() {
			
			if (nextIndex >= size)
				return false;
			
			return true;
			
		}

		
		public T next() {
			
			T item = null;

			if (nextIndex >= size)
				throw new NoSuchElementException();

			item = data[nextIndex];

			nextIndex++;
			lastItem = item;
			
			return item;
			
		}

		
		public void remove() {
			
			if (lastItem == null)
				throw new IllegalStateException();

			ArrayCollection.this.remove(lastItem);
			
			nextIndex--;
			
			lastItem = null;
			
		}

	}

}
