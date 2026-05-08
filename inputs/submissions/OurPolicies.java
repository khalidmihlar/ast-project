//comments: 26
//comment length: 3307
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	private T data[]; 
	private int size; 


	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		T newData[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}

	
	
	public boolean add(T arg0) {
		if (arg0 == null || !(arg0 instanceof Object) )
			return false;
		if (this.contains(arg0))
			return false;
		if (size == data.length)
			grow();
		data[size] = arg0;
		size = size + 1;
		
		return true;
	}
	
	
	
	public boolean addAll(Collection<? extends T> arg0) {
		if(arg0 == null) {
			return false;
		}
		
		boolean worked = false;
	
		for(T item : arg0) {
			if(this.add(item) && !worked) {
				worked = true;
			}
		}
		
		return worked;
	}

	
	@SuppressWarnings("unchecked")
	public void clear() {
		size = 0;
		
		data = (T[]) new Object[10]; 
	}
	
	
	public boolean contains(Object arg0) {
		if(arg0 == null) {
			return false;
		}
		
		for(int x = 0;x < size;x++) {
			if(data[x].equals(arg0)) {
				return true;
			}
		}
		return false;
		
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for(Object item: arg0) {
			if(!this.contains(item)) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		if (arg0 == null)
			return false;
		for(int x = 0;x < size;x++) {
			if(data[x].equals(arg0)) {
				for(int y = x;y < size - 1;y++) {
					data[y] = data[y + 1];
				}
				size--;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		if(arg0 == null) {
			return false;
		}
		
		boolean worked = false;
	
		for(Object item : arg0) {
			if(this.remove(item) && !worked) {
				worked = true;
			}
		}
		
		return worked;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<?> iter = this.iterator();
		
		boolean removed = false;
		
		while(iter.hasNext()) {
			T item = (T) iter.next();
			
			boolean contain = false;
			
			for(Object colItem : arg0) {
				if(colItem.equals(item)) {
					contain = true;
					arg0.remove(colItem);
					break;
				}
			}
			
			if(contain) {
				iter.remove();
				removed  = true;
			}
			
		}
		
		return removed;
	}

	
	public int size() {
		return size;
	}

	
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		
		Object items[] = (T[]) new Object[size];
		
		Iterator<?> iter = this.iterator();
		int val = 0;
		while(iter.hasNext()) {
			items[val] = iter.next();
			val++;
		}
		
		return items;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	@SuppressWarnings("unchecked")
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> list = new ArrayList<T>();
		list.addAll((Collection<? extends T>) Arrays.asList(this.toArray()));
		for (int i = 0; i <list.size() - 1; i++) {
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



	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int index;
		
		private boolean removable;
		
		public ArrayCollectionIterator()
		{
			index = 0;
			removable = false;
		}
		
		public boolean hasNext() {
			return index < size;
		}
		
		public T next() {
			if(hasNext()) {
				removable = true;
				return data[index++];
			}
			else {
				throw new NoSuchElementException("There are no more elements to iterate through");
			}
		}
		
		public void remove() {
			if(removable) {
				for(int y = index - 1;y < size - 1;y++) {
					data[y] = data[y + 1];
				}
				size--;
				index--;
				removable = false;
			}
			else {
				throw new IllegalStateException("Next has not been called since the last remove");
			}
		}

	}

}
