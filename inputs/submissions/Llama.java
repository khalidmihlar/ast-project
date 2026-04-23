//comments: 30
//comment length: 4483
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
		T temp[] = (T[]) new Object[data.length * 2];
		for (int i = 0; i < size; i++)
			temp[i] = data[i];
		data = temp;
	}

	
	public boolean add(T arg0) {
		if (contains(arg0))
			return false;
		if (size == data.length)
			grow();
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean itemAdded = false;
		Iterator<? extends T> temp = arg0.iterator();
		while (temp.hasNext()) {
			if (add(temp.next()))
				itemAdded = true;
		}

		return itemAdded;
	}

	
	public void clear() {
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++)
			if (data[i] == arg0)
				return true;

		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		Iterator<?> temp = arg0.iterator();
		while (temp.hasNext()) {
			if (!contains(temp.next()))
				return false;
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
		for (int i = 0; i < size; i++)
			if (data[i].equals(arg0)) 
			{
				for (int j = i + 1; j < size; j++) {
					data[j - 1] = data[j];
				}
				size--;
				return true;
			}

		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean itemRemoved = false;
		Iterator<?> temp = arg0.iterator();
		while (temp.hasNext()) {
			if (remove(temp.next())) 
				itemRemoved = true;
		}
		return itemRemoved;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean itemRemoved = false;
		Iterator<?> temp = this.iterator();
		while (temp.hasNext()) {
			if (!arg0.contains(temp.next()))
			{
				temp.remove();
				itemRemoved = true;
			}
		}
		return itemRemoved;
	}

	
	public int size() {
		return size;
	}

	
	
	public Object[] toArray() {
		Object[] temp = new Object[size];
		for(int i = 0; i < size; i++)
			temp[i] = data[i];
		return temp;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		
		for(int i = 0; i<size;i++) {
			list.add(data[i]);
		}
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
		private boolean moved;

		public ArrayCollectionIterator() {
			index = 0;
			moved = false;
		}

		
		public boolean hasNext() {
			return index < size;
		}

		
		public T next() {
			if (index >= size)
				throw new NoSuchElementException();
			moved = true;
			return data[index++]; 
		}

		
		public void remove() {
			if (!moved)
				throw new IllegalStateException();
			for (int i = index; i < size; i++) {
				data[i - 1] = data[i];
			}
			index--;
			size--;
			moved = false;
			return;
		}

	}

}
