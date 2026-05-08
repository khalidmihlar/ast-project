//comments: 26
//comment length: 4698
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
		T[] growArray = (T[]) new Object[data.length*2];
		for(int i = 0; i<data.length;i++) {
			growArray[i]=data[i];
		}
		data=growArray;
	}

	
	public boolean add(T arg0) {
		if(contains(arg0)) {
			return false;
		}
		if(size==data.length) {
			grow();
		}
		data[size]=arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean isTrue = false;
		for(T current: arg0) {
			if(this.add(current)) {
				isTrue=true;
			}
		}
		return isTrue;
	}

	
	public void clear() {
		for(int i = 0; i< size; i++) {
			data[i]=null;
		}
		size=0;
	}
	
	
	public boolean contains(Object arg0) {
		for (int i = 0; i< size; i++) {
			if(data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean containsAll(Collection<?> arg0) {
		for(Object current: arg0) {
			if(contains(current)==false) {
				return false;
			}
		}
		return true;
	}
	
	
	public boolean isEmpty() {
		if(size==0) {
			return true;
		}
		return false;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		if(!this.contains(arg0)) {
			return false;
		}
		int positionOfRemoval = size;
		for (int i = 0; i<size; i++) {
			if(data[i].equals(arg0)) {
				positionOfRemoval = i;
			}
			if(positionOfRemoval!=size) {
				if(i<size-1) {
					data[i] = data[i+1];
				}	
			}
		}	
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean hadRemoval = false;
		for(Object current: arg0) {
			if(this.remove(current)) {
				hadRemoval=true;
			}
		}
		return hadRemoval;
	}
	
	
	public boolean retainAll(Collection<?> arg0) {
		boolean wasRemoved = false;
		int i = 0;
		
		while(i<this.size()) {
			if(!arg0.contains(this.data[i])) {
				this.remove(data[i]);
				wasRemoved=true;
			}
			else {
				i++;
			}
		}
		return wasRemoved;
	}
	
	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] arr = new Object[this.size()];
		for(int i = 0; i< this.size(); i++) {
			arr[i]=this.data[i];
		}
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> list = new ArrayList<T>(this);
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

	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int counter;
		private boolean canRemove;
		
		
		public ArrayCollectionIterator()
		{
			counter = 0;
			canRemove=false;
		}

		
		public boolean hasNext() {
			if(counter<size) {
				return true;
			}
			return false;
		}

		
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			counter++;
			canRemove=true;
			return data[counter-1];
		}
		
		
		public void remove() {
			if(!canRemove) {
				throw new IllegalStateException();
			}
			ArrayCollection.this.remove(data[counter-1]);
			counter--;
			canRemove=false;
		}
	}
}