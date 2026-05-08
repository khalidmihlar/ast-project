//comments: 26
//comment length: 4231
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {
	
	int currentIndex = 0; 
	T data[]; 
	int size; 
	


	
	@SuppressWarnings("unchecked")
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {
		T temp[] = (T[]) new Object[size * 2];
		for (int i = 0; i < data.length; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	
	public boolean add(T arg0) {
		if (data.length == size) {
			grow();
		}
			for (int i = 0; i < size; i++) {
				if (data[i] == arg0) {
					return false;
				}
			}
			data[size] = arg0;
			size += 1;
			return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean added = false;
		for(T position: arg0) {
			if(add(position)) {
				added = true;
			}
		}
		return added;
	}

	
	public void clear() {
		for (int i = 0; i < size; i++) {
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
		for (int i = 0; i < arg0.size(); i++) {
			if (!this.contains(arg0.iterator().next())) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	
	public Iterator<T> iterator() {
		Iterator<T> temp = new ArrayCollectionIterator();
		return temp;
	}

	
	public boolean remove(Object arg0) {
		int position = 0;
		for (int i = 0; i < size; i++) {
			position = i;
			if (data[i].equals(arg0)) {
				data[position] = null;

				if (!(position + 1 == size)) {
					for (int j = position + 1; j < size; j++) {
						data[j - 1] = data[j];
					}
				}
				size -= 1;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean removed = false;
		while(arg0.iterator().hasNext()) {
			Object temp = arg0.iterator().next();
			if(this.contains(temp)) {
				this.remove(temp);
				removed = true;
			}
		}
		
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean removed = false;
		T[] temp = (T[]) new Object[size];
		int position = 0;
		while(arg0.iterator().hasNext()) {
			Object tempObject = arg0.iterator().next();
			if(this.contains(tempObject)) {
				temp[position] = (T) tempObject;
				position += 1; 
			}
		}
		if(position < size) {
			removed = true;
		}
		T[] finalTemp = (T[]) new Object[position];
		for(int i = 0; i < finalTemp.length; i++) {
			finalTemp[i] = temp[i]; 
		}
		data = finalTemp;
		size = finalTemp.length;
		return removed;
	}

	
	public int size() {
		return this.size;
	}

	
	public Object[] toArray() {
		Object[] finishedArray = new Object[size];
			for(int i = 0; i < size; i++) {
				finishedArray[i] = data[i];
			}
		return finishedArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> list = new ArrayList<T>();
		for(int i = 0; i < size; i++) {
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
		private boolean removable = false;
		
		
		public boolean hasNext() {
			if(data != null) {
				removable = true;
			}else {
				removable = false;
			}
			if(data[currentIndex] != null) {
				removable = true;
			}else {
				removable = false;
			}
			
			return removable;
		}

		
		
		public T next() {
			if (hasNext()) {
				currentIndex += 1;
				removable = true;
				return data[currentIndex - 1];
			}
			return null;
		}
		
		
		public void remove() {
			if(removable) {
				for(int i = currentIndex + 1; i < size; i++) {
					data[i-1] = data[i];
				}
				removable = false;
				currentIndex -= 1;
			}else {
				throw new IllegalStateException();
			}
		}

	}

}
