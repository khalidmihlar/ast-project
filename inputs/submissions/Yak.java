//comments: 35
//comment length: 4794
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
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
		T[] holder = (T[]) new Object[data.length * 2]; 

		for (int i = 0; i < data.length; i++) {
			holder[i] = data[i];
		}

		data = holder; 
	}

	
	public boolean add(T arg0) {

		if (contains(arg0)) 
			return false;

		else if (data.length - 1 == size) { 
			grow();
		}
		data[size] = arg0; 
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean temp = false;
		
		
		for (T obj : arg0) {
			if (add(obj))
				temp = true;
		}
		return temp;
	}

	
	public void clear() {
		for (int i = 0; i < data.length; i++) {
			data[i] = null;
		}
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		ArrayCollectionIterator itr = new ArrayCollectionIterator();
		while (itr.hasNext()) {
			if (itr.next().equals(arg0))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for (Object obj : arg0) {
			if (!contains(obj)) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	
	public Iterator<T> iterator() {
		ArrayCollectionIterator itr = new ArrayCollectionIterator();
		return itr;
	}

	
	public boolean remove(Object arg0) {
		ArrayCollectionIterator itr = new ArrayCollectionIterator();
		while (itr.hasNext()) {
			if (itr.next().equals(arg0)) {
				itr.remove();
				size--;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean temp = false;
		for (Object object : arg0) {
			if (remove(object)) {

				temp = true;

			}

		}

		return temp;

	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean temp = false;

		ArrayCollectionIterator itr = new ArrayCollectionIterator();
		while (itr.hasNext()) {
			if (!arg0.contains(itr.next())) {
				itr.remove();
				temp = true;
			}
		}

		return temp;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] toreturn = new Object[size];
		for (int i = 0; i < size; i++)
			toreturn[i] = data[i];
		return toreturn;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> toReturn = new ArrayList<T>();
		ArrayCollectionIterator itr = new ArrayCollectionIterator();

		while (itr.hasNext()) {
			toReturn.add(itr.next());
		}

		for (int i = 0; i < toReturn.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < toReturn.size(); j++)
				if (cmp.compare(toReturn.get(j), toReturn.get(minIndex)) < 0)
					minIndex = j;
			T temp = toReturn.get(i);
			toReturn.set(i, toReturn.get(minIndex));
			toReturn.set(minIndex, temp);
		}

		return toReturn;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {
		int currentIndex;
		boolean removeableState;

		
		public ArrayCollectionIterator() {
			currentIndex = -1;
			removeableState = false;
		}

		
		public boolean hasNext() {
			if (data[currentIndex + 1] != null) {
				return true;
			}
			return false;
		}

		
		public T next() {
			if (currentIndex == data.length - 1) {
				throw new NoSuchElementException();
			}
			currentIndex++;
			removeableState = true;
			return data[currentIndex];
		}

		
		public void remove() {
			if (removeableState = false) {
				throw new IllegalStateException();
			}
			int removalIndex = currentIndex;
			while (data[removalIndex + 1] != null || removalIndex + 1 == data.length) {
				data[removalIndex] = data[removalIndex + 1];
				removalIndex++;
			}
			data[removalIndex] = null;
			currentIndex--;
			removeableState = false;
		}

	}

}
