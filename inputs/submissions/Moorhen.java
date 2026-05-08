//comments: 30
//comment length: 3475
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
		T newData[] = (T[]) new Object[data.length * 2];

		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}

		data = newData;

	}

	

	public boolean add(T arg0) {
		boolean added = false;
		if (size == data.length) {
			grow();
		}
		for (int i = 0; i < data.length; i++) {
			if (data[i] == null) {
				data[i] = arg0;
				size++;
				added = true;
				break;
			}
			if (arg0.equals(data[i])) {
				return added;
			}

		}
		return added;
	}
	
	public boolean addAll(Collection<? extends T> arg0) {
		for (T item : arg0) {
			if (add(item)) {
				return true;
			}
		}

		return false;
	}
	
	public void clear() {

		for (int i = 0; i < data.length; i++) {
			data[i] = null;
			size = 0;
		}
	}
	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size(); i++) {
			if (data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsAll(Collection<?> arg0) {
		boolean containsAll = false;
		for (Object item : arg0) {
			if (contains(item)) {
				containsAll = true;
			}
		}

		return containsAll;
	}
	
	public boolean isEmpty() 
	{
		boolean isEmpty = false;
		if (size == 0) {
			isEmpty = true;
		}
		return isEmpty;
	}
	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}
	
	public boolean remove(Object arg0) 
	{
		if (!contains(arg0)) {
			return false;
		}

		for (int i = 0; i < data.length; i++) {
			if (contains(arg0)) {
				data[i] = null;
				size--;
				break;
			}
		}

		T newArray[] = (T[]) new Object[data.length - 1];
		T finalArray[] = (T[]) new Object[data.length];
		for (int i = 0; i < size - 1; i++) 
		{
			newArray[i] = data[i+1];
		}
		for(int i = 0; i < newArray.length; i++)
		{
			finalArray[i] = newArray[i];
		}
		data = finalArray;
		return true;
	}
	
	public boolean removeAll(Collection<?> arg0) {
		for (Object item : data) {
			if(arg0.contains(item))
			{
				remove(item);
				return true;
			}
			if(item == null)
			{
				break;
			}
		}
		return false;
	}
	
	public boolean retainAll(Collection<?> arg0) { 
		if (arg0 == null) {
			throw new NullPointerException();
		}

		Iterator<T> itr = iterator();
		boolean foundObject = false;

		while (itr.hasNext()) {
			if (arg0.contains(itr.next())) {
				itr.remove();
				foundObject = true;
			}
		}
		return foundObject;
	}
	
	public int size() {
		return size;
	}
	
	public Object[] toArray() {
		T newArray[] = (T[]) new Object[size];

		for (int i = 0; i < size; i++) {
			newArray[i] = data[i];
		}

		data = newArray;

		return data;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> arrayList = new ArrayList<T>();

		for(int i = 0; i < this.size(); i++)
		{
			arrayList.add(data[i]);
		}
		
		sort(arrayList, cmp);
		return arrayList;
	}
	public static <T> void sort(ArrayList<T> list, Comparator<? super T> cmp)
	{
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++) {
				if (cmp.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;

				T temp = list.get(i);
				list.set(i, list.get(minIndex));
				list.set(minIndex, temp);
			}
		}

	}
	
	private class ArrayCollectionIterator implements Iterator<T> {
		private boolean lastItem;
		private int currentIndex;

		public ArrayCollectionIterator() {
			lastItem = false;
			currentIndex = 0;
		}
		
		public boolean hasNext() {
			return currentIndex < size;
		}
		
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			lastItem = true;
			return data[currentIndex++]; 
			
		}
		
		public void remove() {
			if (lastItem == false) {
				throw new IllegalStateException();
			}
			try {
				ArrayCollection.this.remove(data[currentIndex]);
				currentIndex--;
				lastItem = false;
			} catch (IndexOutOfBoundsException e) {
				throw new IndexOutOfBoundsException();
			}
		}

	}

}
