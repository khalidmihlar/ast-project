//comments: 37
//comment length: 4191
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

	
	@SuppressWarnings("unchecked") void grow()
	{

		T temp[] = (T[]) new Object[data.length * 2];
		Iterator<T> itr = this.iterator();
		while(itr.hasNext()) {
			for(int i = 0; i < size; i++) {
				temp[i] = itr.next();
			}
		}

		data = temp;
	}

	

	public boolean add(T newT) {
		if(size == data.length) {
			this.grow();           
		}

		if(!this.contains(newT)) {
			data[size] = newT;
			size ++;               
			return true;
		}

		return false;
	}

	
	public boolean addAll(Collection<? extends T> newCollection) {
		Iterator<? extends T> newItr = newCollection.iterator();

		if(this.containsAll(newCollection)){
			return false;                    
		}

		while(newItr.hasNext()) {
			this.add(newItr.next());
		}

		return true;
	}

	
	public void clear() {

		for(int i = 0; i < size; i++) {
			data[i] = null;

		}
		size = 0;       

	}

	
	public boolean contains(Object obj) {
		Iterator<? extends T> itr = this.iterator();
		while(itr.hasNext()) {
			if(obj.equals(itr.next())) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> newC) {

		@SuppressWarnings("unchecked")
		Iterator<? extends T> itr = (Iterator<? extends T>) newC.iterator();

		while(itr.hasNext()) {
			if(!this.contains(itr.next())) {
				return false;		
			}
		}
		return true;
	}

	
	public boolean isEmpty() {

		Iterator<? extends T> itr = this.iterator();

		boolean result = itr.hasNext();
		if(result)
			return false;
		return true;
	}

	
	public Iterator<T> iterator() {

		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object toRemove) {

		int i = 0;

		while(i < size) {
			if(data[i].equals(toRemove)) {
				data[i] = null;

				for(int j = i; j < size; j++) {
					data[j] = data[j+1];       
					data[j+1] = null;
				}
				size--;
				i++;
				return true;
			}
		}
		return false;
	}

	
	public boolean removeAll(Collection<?> newR) {
		Iterator<?> itr = newR.iterator();
		Iterator<T> thisItr = this.iterator();
		boolean didRemove = false;
		int newSize = newR.size();


		while(thisItr.hasNext()) {
			T toRemove = thisItr.next();

			if(newR.contains(toRemove)) {
				thisItr.remove();





				didRemove = true;           
			}

		}


		return didRemove;
	}

	
	public boolean retainAll(Collection<?> ret) {

		Iterator<?> itr = ret.iterator();
		Iterator<T> thisItr = this.iterator();

		boolean didRemove = false;

		int i = 0;


		while(thisItr.hasNext()) {
			T toRemove = thisItr.next();
			if(!ret.contains(toRemove)) {
				data[i] = null;
				didRemove = true;               

			}
			i++;
		}


		return didRemove;

	}
	
	public int size() {

		return size;
	}

	
	public Object[] toArray() {
		Object[] arr = new Object[size];

		for(int i = 0; i < size; i++) {
			arr[i] = data[i];
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
		private int current;
		private boolean canRemove;
		public ArrayCollectionIterator()
		{
			current = 0;                    
			canRemove = false;              
		}

		
		public boolean hasNext() {

			if(current < size) {
				return true;
			}
			return false;
		}

		
		public T next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			canRemove = true;

			return data[current++];         
		}

		
		public void remove() {
			if(!canRemove) {
				throw new IllegalStateException();
			}

			ArrayCollection.this.remove(data[current-1]);
			canRemove = false;             
			current--;                     
		}
	}

}


