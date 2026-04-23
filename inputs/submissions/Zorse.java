//comments: 28
//comment length: 2758


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
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		T[] temp = (T[]) new Object[data.length*2];
		for(int i = 0; i<data.length; i++)
			temp[i] = data[i];
		data = temp;
	}


	
	public boolean add(T arg0) {
		if(contains(arg0))
			return false;
		if(size==data.length)
			grow();
		data[size++] = arg0;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		boolean allAdded = true;
		for(T t: arg0) {
			if(contains(t))
				allAdded = false;
			else
				add(t);
		}
		return allAdded;
	}

	
	public void clear() {
		for(int i = 0; i<data.length; i++)
			data[i] = null;
		size=0;
	}

	
	public boolean contains(Object arg0) {
		for(T t: data) {
			if(t != null && t.equals(arg0))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		compared:
		for(Object o: arg0){
			for(T t: data) {
				if(t != null && t.equals(o))
					continue compared;
			}
				return false;
		}
		return true;
	}

	
	public boolean isEmpty() {
		return size==0;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		
		boolean found = false;
		int i = 0;
		for(; i<size; i++){
			if(data[i].equals(arg0)) {
				found = true;
				break;
			}
		}
		if(!found)
			return false;
		
		
		for(; i<size-1; i++) {
			data[i]=data[i+1];
		}
		data[size-1]=null;
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean removed = false;
		boolean temp = true;
		
		for(Object o: arg0) {
			temp = remove(o);
		}
		
		
		if(!removed)
			removed = temp;
		
		return removed;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean removed = false;
		Iterator iterator = iterator();
		
		while(iterator.hasNext()) {
		if(!arg0.contains(iterator.next())) {
			iterator.remove();
			removed = true;
			}
		
		}
		
		return removed;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] temp = new Object[size];
		Iterator iterator = iterator();
		
		for(int i = 0; i<temp.length; i++)
		{
			temp[i] = data[i];
		}
		
		return temp;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		@SuppressWarnings("unchecked")
		ArrayList<T> list = new ArrayList<T>(Arrays.asList((T[]) toArray()));
		
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
		private int pointer = 0;
		private boolean canRemove = false;

		
		public boolean hasNext() {
			return pointer < size;
		}
		
		
		public T next() {
			if (this.hasNext()) {
				canRemove = true;
				return data[pointer++];
			}
			throw new NoSuchElementException();
		}
		
		
		public void remove() {
			if (!canRemove) {
				throw new IllegalStateException();
			}
			
			ArrayCollection.this.remove(data[--pointer]);
			canRemove=false;
		}

	}

}
