//comments: 25
//comment length: 3777
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
		int newSize = this.data.length * 2;
		T temp[] = (T[]) new Object[newSize];

		Iterator<T> myItr = new ArrayCollectionIterator();

		int i = 0;

		while (myItr.hasNext()) {
			temp[i] = myItr.next();
			i++;
		}

		this.data = temp;
		
		
		
	}

	
	
	public boolean add(T arg0) {

		boolean added = false;

		if (this.contains(arg0)) {
			return false;
		}

		if (this.data.length == this.size) {
			this.grow();
			this.add(arg0);
		} else {
			this.data[this.size] = arg0;
			this.size++;
			added = true;
		}
		return added;
	}
	
	

	public boolean addAll(Collection<? extends T> arg0) {

		int added = 0;

		for (T arg : arg0) {
			if (this.add(arg)) {
				added++;
			}
		}

		return (added > 0);
	}
	
	

	public void clear() {

		for (int i = 0; i < this.size; i++) {
			this.data[i] = null;
		}
		
		this.size = 0;
	}
	
	

	public boolean contains(Object arg0) {

		Iterator<T> myItr = new ArrayCollectionIterator();

		while (myItr.hasNext()) {
			if (myItr.next() == (arg0))
				return true;
		}
		return false;
	}
	
	

	public boolean containsAll(Collection<?> arg0) {

		for (Object arg : arg0) {
			if (!this.contains(arg)) {
				return false;
			}
		}
		return true;
	}
	
	

	public boolean isEmpty() {

		return (this.data[0] == null);
	}

	public Iterator<T> iterator() {

		return new ArrayCollectionIterator();
	}
	
	

	public boolean remove(Object arg0) {

		int index = 0;
		while (index < this.data.length) {
			index++;
			if (arg0 == this.data[index-1]) {
				this.data[index-1] = null;
				break;
			} else if (index == this.data.length) {
				return false;
			}
		}

		int shift = this.size - index-1;
		for (int i = index-1; i < shift; i++) {
			this.data[i + 1] = this.data[i];
		}

		return true;
	}
	
	

	public boolean removeAll(Collection<?> arg0) {

		int removed = 0;

		for (Object arg : arg0) {
			if (this.remove(arg)) {
				removed++;
			}
		}

		return (removed > 0);
	}
	
	

	@SuppressWarnings("unchecked")
	public boolean retainAll(Collection<?> arg0) {

		boolean removed = false;
		
		ArrayCollection<T> temp = new ArrayCollection<T>();

		for (Object arg : arg0) {
			if (this.contains(arg)) {
				temp.add((T) arg);
			}
		}
		
		if(temp.size < this.size) 
			removed = true;
			
		this.clear();
		
		this.addAll(temp);
		
		return removed;
	}

	public int size() {

		return this.size;
	}
	
	

	public Object[] toArray() {

		Object[] objArr = new Object[this.size];

		for (int i = 0; i < this.size; i++) {
			objArr[i] = data[i];

		}
		return objArr;

	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		
		ArrayList<T> sortedList = new ArrayList<T>();
		sortedList.addAll(this);
		
		for (int i = 0; i < sortedList.size() - 1; i++) {
			  int j, minIndex;
			  for (j = i + 1, minIndex = i; j < sortedList.size(); j++)
			  if (cmp.compare(sortedList.get(j), sortedList.get(minIndex)) < 0)
			  minIndex = j;
			  T temp = sortedList.get(i);
			  sortedList.set(i, sortedList.get(minIndex));
			  sortedList.set(minIndex, temp);
		} 
		
		return sortedList;
	}

	private class ArrayCollectionIterator implements Iterator<T> {

		int index = 0;
		
		boolean nextCalled = false;

		public ArrayCollectionIterator() {

		}

		public boolean hasNext() {

			if (index < ArrayCollection.this.data.length) {
				nextCalled = false;
				return true;
			} else {
				return false;
			}
		}

		public T next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			nextCalled = true;

			return ArrayCollection.this.data[index++];
		}

		public void remove() {

			if (nextCalled) {
				ArrayCollection.this.remove(data[index - 1]);
				nextCalled = false;
			} else {
				throw new IllegalStateException();
			}
		}

	}

}
