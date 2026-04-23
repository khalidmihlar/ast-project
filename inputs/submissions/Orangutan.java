//comments: 26
//comment length: 3562
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
		
		int growSize = this.data.length;
		growSize *= 2;
		T newData[] = (T[])(new Object[growSize]);
		for(int index = 0; index < this.size; index++) {
			newData[index] = this.data[index];
		}
		this.data = newData;
	}

	
	public boolean add(T arg0) {
		if(arg0 == null) {
			return false;
		}
		
		if(this.contains(arg0)) {
			return false;
		}
		
		if((this.size + 1) > this.data.length) {
			this.grow();
		}
		
		this.data[this.size] = arg0;
		this.size++;
		
		return true;
	}

	
	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean wasChanged = false;
		for(T val : arg0) {
			if(this.add(val) || wasChanged) {
				wasChanged = true;
			}
		}
		return wasChanged;
	}

	
	public void clear() {
		this.size = 0;
		this.data = (T[])(new Object[10]);
	}

	
	public boolean contains(Object arg0) {
		if(arg0 == null) {
			return false;
		}
		for(int index = 0; index < this.size; index++) {
			if(this.data[index].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		for(Object val : arg0) {
			if(!this.contains(val)) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		return this.size == 0;
	}

	
	public Iterator<T> iterator() {
		ArrayCollectionIterator iter = new ArrayCollectionIterator();
		iter.collection = this;
		return iter;
	}

	
	public boolean remove(Object arg0) {
		int indexOf = -1;
		for(int index = 0; index < this.data.length; index++) {
			if(this.data[index].equals(arg0)) {
				indexOf = index;
				break;
			}
		}
		
		if(indexOf < 0) {
			return false;
		}
		
		for(int index = indexOf + 1; index < this.size; index++) {
			this.data[index - 1] = this.data[index];
		}
		
		this.size--;
		return true;
	}
	

	
	public boolean removeAll(Collection<?> arg0) {
		boolean wasChanged = false;
		for(Object val : arg0) {
			if(this.remove(val)) {
				wasChanged = true;
			}
		}
		return wasChanged;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean itemsRemoved = false;
		Iterator<T> self = this.iterator();
		while(self.hasNext()) {
			T ref = self.next();
			if(!arg0.contains(ref)) {
				this.remove(ref);
			}
		}
		return itemsRemoved;
	}

	
	public int size() {
		return this.size;
	}

	
	public Object[] toArray() {
		Object[] colArray = new Object[this.size];
		for(int index = 0; index < this.size; index++) {
			colArray[index] = this.data[index];
		}
		return colArray;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> list = new ArrayList<T>();
		for(int index = 0; index < this.size; index++) {
			list.add(this.data[index]);
		}
		
		for(int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for(j = i+1, minIndex = i; j < list.size(); j++){
				if(cmp.compare(list.get(i), list.get(minIndex)) < 0) {
					minIndex = j;
				}
			}
			T temp = list.get(i);
			list.set(minIndex,  list.get(minIndex));
			list.set(minIndex, temp);
		}
		return list;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		int cursor;
		boolean removeValid;
		ArrayCollection<T> collection;
		
		public ArrayCollectionIterator()
		{
			this.cursor = -1;
			this.removeValid = false;
			
			collection = null;
		}
		
		
		public boolean hasNext() {
			return this.cursor < this.collection.size - 1;
		}

		
		public T next() {
			if(this.hasNext()) {
				this.removeValid = true;
				cursor++;
				return (T)this.collection.data[cursor];
			}
			this.removeValid = true;
			throw new NoSuchElementException();
			
			
		}

		
		public void remove() {
			if(!removeValid) {
				throw new IllegalStateException();
			}
			this.collection.remove((T) this.collection.data[cursor]);
			this.removeValid = false;
			cursor--;
		}

	}

}
