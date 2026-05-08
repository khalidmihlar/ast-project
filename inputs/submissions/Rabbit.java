//comments: 32
//comment length: 4664
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
		T[] arrayCopy = (T[]) new Object[data.length * 2]; 
		for(int i = 0; i < size; i++) 
			arrayCopy[i] = data[i];
		data = arrayCopy;
	}

	
	public boolean add(T arg0) {
		
		if(contains(arg0))
			return false;
		
		
		if(data[data.length - 1] != null)
			grow();
		
		
		data[size] = arg0;
		size++;
		return true;
	}
	
	
	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean isAdded = false;
		for(T el: arg0)
			if(add(el))
				isAdded = true;
		
		return isAdded;
	}
	
	
	public void clear() {
		for(int i = 0; i < size; i++)
			data[i] = null;
		
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for(int i = 0; i < size; i++)
			if(data[i].equals(arg0))
				return true;
		return false;
	}
	
	
	public boolean containsAll(Collection<?> arg0) {
		Iterator<?> itr = arg0.iterator();
		while(itr.hasNext())
			if(!contains(itr.next()))
				return false;

		return true;
	}
	
	
	public boolean isEmpty() {
		if(size == 0)
			return true;
		
		return false;
	}
	
	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}
	
	
	public boolean remove(Object arg0) {
		if(!contains(arg0))
			return false;
		
		int location = 0;
		for(int i = 0; i < size; i++) {
			if(arg0.equals(data[i])) {
				location = i;
				break;
		
			}
		}
		
		for(int i = location; i < size - 1; i++)
			data[i] = data[i + 1];
		
		data[size] = null;
		size--;
		return true;
	}
	
	
	public boolean removeAll(Collection<?> arg0) {
		boolean isRemoved = false;
		Iterator<?> itr = arg0.iterator();
		while(itr.hasNext())
			if(remove(itr.next()))
				isRemoved = true;

		return isRemoved;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		int initSize = size;
		ArrayCollection<T> arrayRemove = new ArrayCollection<T>();
		arrayRemove.addAll(this);
		Iterator<?> itr = arg0.iterator();
		Object placeholder = null;
		while(itr.hasNext()) {
			placeholder = itr.next();
			if(contains(placeholder))
				arrayRemove.remove((T)placeholder);
		}
		
		this.removeAll(arrayRemove);
		return !(initSize == size);
	}
	
	
	public int size() {
		return size;
	}
	
	
	public Object[] toArray() {
		T[] arrayCopy = (T[]) new Object[size];
		for(int i = 0; i < size; i++)
			arrayCopy[i] = data[i];
		
		return arrayCopy;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		
		ArrayList<T> sortedList = new ArrayList<T>();
		Iterator itr = this.iterator();
		while(itr.hasNext())
			sortedList.add((T)itr.next());
		
		for (int i = 0; i < this.size() - 1; i++) {
			  int j, minIndex;
			  for (j = i + 1, minIndex = i; j < this.size(); j++)
				if (cmp.compare(sortedList.get(j), sortedList.get(minIndex)) < 0)
					  minIndex = j;
		  		T temp = sortedList.get(i);
		  		sortedList.set(i, sortedList.get(minIndex));
		  		sortedList.set(minIndex, temp);
		  }
		return sortedList;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		int location;
		boolean canRemove;
		
		
		public ArrayCollectionIterator()
		{
			location = -1;
			canRemove = false;
		}
		
		
		public boolean hasNext() {
			if(location + 1 == size)
				return false;
			return true;
		}
		
		
		public T next() {
			if(!hasNext())
				throw new NoSuchElementException("Can't call next() when there are no more elements");
			location++;
			canRemove = true;
			return data[location];
		}
		
		
		public void remove() {
			if(!canRemove)
				throw new IllegalStateException("Can't call remove() without calling next() beforehand");
			
			canRemove = false;
			ArrayCollection.this.remove(data[location]);
			location--;
		}
		
	}
	
}
