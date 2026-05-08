//comments: 28
//comment length: 4823
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
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
	}

	
    @SuppressWarnings("unchecked")
    private void grow()
    {
        T newData[] = (T[]) new Object[size * 2];
        for (int item = 0; item <data.length; item++ ) {
            newData[item] = data[item];
        }
        data = newData;
    }

	
	public String toString() {
		String out = "";
		for (int i = 0; i < size; i++) {
			out = out + data[i] + ",";
		}
		return out;
	}

	
	public boolean add(T arg0) {
		if(contains(arg0)) {
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
		boolean flag = false;
		for(T item : arg0) {
			if (item == null) {
				continue;
			}
			if(this.add(item)) {
				flag = true;
			}
		}
		return flag;
	}

	
	public void clear() {
		size = 0;
		for (int i = 0; i <data.length; i++ ) {
			data[i] = null;
		}
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
		for(Object item : arg0) {
			if (item == null) {
				continue;
			}
			if(!this.contains(item)) {
				flag = false;
			}
		}
		return flag;
	}

	
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();		
	}

	
	public boolean remove(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				for (int j = i; j < size - 1; j++) {
					data[j] = data[j+1];
				}
				data[size-1] = null;
				size = size - 1;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean flag = false;
		for(Object item : arg0) {
			if(this.remove(item)) {
				flag = true;
			}
		}
		return flag;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean flag = false;
		boolean check;
		ArrayCollectionIterator Itr = (ArrayCollectionIterator) this.iterator();
		for (int i = 0; i < this.size; i++) {
			check = arg0.contains(this.data[i]);
			if (check) {
			
			}else {
				Itr.next();
				Itr.remove();
				flag = true;
			}
		}
		return flag;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		T newData[] = (T[]) new Object[size];
		for (int item = 0; item < size; item++ ) {
			newData[item] = data[item];
		}
		return newData;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}


	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> newData = new ArrayList<T>(size);
		for (int i = 0; i < size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++) {
				if (cmp.compare(data[j], data[minIndex]) < 0) {
					minIndex = j;
				}
			}
			T tmp = data[i];
			data[i] = data[minIndex];
			newData.add(data[minIndex]);
			data[minIndex] = tmp;
		}
		newData.add(data[size-1]);
		return newData;
	}


	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int index;
		boolean canRemove;
		
		
		
		public ArrayCollectionIterator()
		{
			index = -1;
			canRemove = false;		
			}

		
		public boolean hasNext() {
			if (index + 1 < size) {
				return true;
			}
			return false;
		}

		
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();	
			}
			index++;
			canRemove = true;
			return data[index];
			
		}

		
		public void remove() {
			if (canRemove) {
				ArrayCollection.this.remove(index);
				index = index - 1;
				canRemove = false;
			} else {
				throw new IllegalStateException();
			}
		}
		
	}

}
