//comments: 24
//comment length: 2876
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	T data[]; 
	int size; 


	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{
		T temp[] = (T[]) new Object[data.length*2];
		for(int i=0;i<this.size();i++) {
			temp[i] = data[i];
		}
		
		data = temp;
	
	}


	
	public boolean add(T arg0) {
		if(!this.contains(arg0)) {
			if(this.size()>=data.length) {
				this.grow();
			}
			data[size] = arg0;
			size++;
			return true;
		}
		else {
		return false;
		}
	}

	
	public boolean addAll(Collection<? extends T> arg0) {
		Iterator itr = arg0.iterator();
		int itemAdded = 0;
		while(itr.hasNext()) {
			Object obj = itr.next();
			if(!this.contains(obj)) {
				if(this.size()>=data.length) {
					this.grow();
				}
				data[size] = (T) obj;
				size++;
				itemAdded++;
			}
		}

		if (itemAdded <= 0) {
			return false;
		}
		else {
			return true;
		}
	}

	
	public void clear() {
		size = 0;
		data = (T[]) new Object[0];
	}

	
	public boolean contains(Object arg0) {
		for(int i=0;i<this.size();i++) {
			if(data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		Iterator itr = arg0.iterator();
		while(itr.hasNext()) {
			if(!this.contains(itr.next())) {
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty() {
		if(size==0)
			return true;
		else
		return false;
	}

	
	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
		if(!this.contains(arg0)) {
			return false;
		}
		else {
		for(int i=0;i<this.size();i++) {
			if(data[i].equals(arg0)) {
				for(int j=i;j<this.size()-1;j++) {
					data[j] = data[j+1];
				}
				data[this.size()-1] = null;
				size--;
				break;
			}
		}
		return true;
		}
	}

	
	public boolean removeAll(Collection<?> arg0) {
		Iterator itr = this.iterator();
		int itemRemoved = 0;
		while(itr.hasNext()) {
			Object obj = itr.next();
			if(arg0.contains(obj)) {
				itr.remove();
				itemRemoved++;
			}
		}
		
		if (itemRemoved <= 0) {
			return false;
		}
		else {
			return true;
		}
		
	}

	
	public boolean retainAll(Collection<?> arg0) {
		ArrayCollectionIterator arritr = (ArrayCollection<T>.ArrayCollectionIterator) this.iterator();
		int itemsRemoved = 0;
		while(arritr.hasNext()) {
			if(!arg0.contains(arritr.next())) {
				 arritr.remove();
				 itemsRemoved++;
			}
		}
		return itemsRemoved>0;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		Object[] arr = new Object[size];
	    for(int i=0;i<arr.length;i++) {
	    	arr[i] = data[i];
	    }
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		    ArrayList<T> list = new ArrayList<T>();
		    list.addAll(this);
		    
		    
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
		
		private int index = 0;
		private boolean next = false;

		public boolean hasNext() {
			next = index<size;
			return next;
		}
        
        
		
		public T next() {
			if(hasNext()) {
				index++;
				return data[index-1];
			}
			else {
			throw new NoSuchElementException();
			}
		}

		
		public void remove() { 
			if(next) {
				for(int i=index-1;i<size-1;i++) {
					data[i] = data[i+1];
				}
				data[size-1] = null;
				size--;
				index--;
				next = false;
			}
			else {
				throw new IllegalStateException();
			}
			
		}

	}

}
