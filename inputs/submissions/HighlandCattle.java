//comments: 31
//comment length: 4090
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
		T[] newData = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];
		}
		data = newData;

		
		
	}

	
	public boolean add(T arg0) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] == arg0) {
				return false;
			}
			if (data[i] == null) {
				data[i] = arg0;
				size++;
				return true;
			}
			if (i + 1 == data.length) {
				grow();
			}
		}

		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean wasAdded = false;
		for (T isInCollection : arg0) {

			wasAdded = add(isInCollection);

		}
		return wasAdded;
	}

	
	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
			size = 0;
		}
	}

	
	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i] == arg0)
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		Iterator<T> newIterate = (Iterator<T>) arg0.iterator();
		for (int i = 0; i < arg0.size(); i++) {
			if (data[i] != newIterate.next()) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {

		return size == 0; 
	}

	
	public Iterator<T> iterator() {
		ArrayCollectionIterator newIterator = new ArrayCollectionIterator();
		return newIterator;
	}

	
	public boolean remove(Object arg0) {
		boolean returns = false;
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				returns = true;
				data[i] = data[i + 1];

			} else if (returns) {
				data[i] = data[i + 1];
			}
		}
		size--;
		return returns;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean returns = false;
		Iterator<T> iterate = (Iterator<T>) arg0.iterator();
		for (int i = 0; i < size; i++) {
			Object temp = iterate.next();
			if (iterate.hasNext() && data[i] == temp) {
				remove(temp);
				returns = true;
			}
		}
		return returns;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		Iterator<T> iterate = (Iterator<T>) arg0.iterator();
		boolean track = false;
		for (int i = 0; i < size; i++) {
			Object temp = iterate.next();
			if (!data[i].equals(temp)) {
				remove(data[i]);
				track = true;
			}
		}
		return track;
	}

	
	public int size() {
		return size;
	}

	
	public int length() {
		return data.length;
	}

	
	public Object[] toArray() {
		Object newObjectCopy[] = new Object[size];
		for (int i = 0; i < size; i++) {
			newObjectCopy[i] = data[i];
		}
		return newObjectCopy;
	}

	
	public <T> T[] toArray(T[] arg0) {

		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> copy = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			copy.add(data[i]);

		}
		for (int i = 0; i < copy.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < copy.size(); j++)
				if (cmp.compare(copy.get(j), copy.get(minIndex)) < 0)
					minIndex = j;
			T temp = copy.get(i);
			copy.set(i, copy.get(minIndex));
			copy.set(minIndex, temp);
		}
		return copy;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {
		private int index;
		private int call;

		public ArrayCollectionIterator() {
			index = -1;
			call = 0;
		}

		
		public boolean hasNext() {
			if (data[++index] != null) {
				return true;
			}
			return false;
		}

		
		public T next() {
			if (hasNext() == true) {
				index = index++;
				call = 0;
				return data[index];
			} else {
				throw new NoSuchElementException("You dont have a next Index");
			}
		}

		
		public void remove() {
			if (call == 0) {
				ArrayCollection.this.remove(data[index]);
				index--;
				call++;
			} else {
				throw new IllegalStateException("You must call next before removing another item");
			}

		}

	}

}
