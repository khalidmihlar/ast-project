//comments: 29
//comment length: 4482
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class ArrayCollection<T> implements Collection<T> {

	T data[]; 
	int size; 
	T dataCopy[]; 
	T firstArray[]; 
	T secondArray[]; 
	int index; 


	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		index = 0;
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow()
	{

		dataCopy = (T[]) new Object[data.length * 2];
		System.arraycopy(data, 0, dataCopy, 0, data.length);
		data = dataCopy;
	}

	
	public boolean add(T arg0) {
		
		if(data.length == size) {
			this.grow();
		}
		
		if(!this.contains(arg0)) {
			data[size] = arg0;
			size++;
			return true;
		}
		else {
			return false;
		}
	}

	
	
	public boolean addAll(Collection<? extends T> arg0) {

		boolean returned = false;

		ArrayList<T> list = new ArrayList<T>();
		
		for(T j: arg0) {
			list.add(j);
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(!this.contains(list.get(i))) {
				this.add(list.get(i));
				returned = true;
			}
		}
		
		return returned;
	}

	
	public void clear() {
		for(int i = 0; i < data.length ; i++) {
			data[i] = null;
		}
		
		size = 0;
	}

	
	public boolean contains(Object arg0) {
		for(int i = 0; i < data.length ; i++) {
			if(arg0.equals(data[i])) {
				return true;
			}
		}
		
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {

		for(Object j: arg0) {
			if(!this.contains(j)) {
				return false;
			}
		}
		
		return true;
	}

	
	public boolean isEmpty() {

		if(data.length == 0) {
			return true;
		}
		
		for(int i = 0 ; i < data.length ; i++) {
			if(data[i] != null) {
				return false;
			}
		}
		return true;
	}

	
	
	public Iterator<T> iterator() {

		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {

		boolean returned = true;
		
		for(int i = 0; i < data.length ; i++) {
			if(arg0 != data[i]) {
				returned = false;
			}
		}
		
		for(int i = 0; i < data.length ; i++) {
			if(arg0 == data[i]) {
				int end = data.length - (i + 1);
				System.arraycopy(data, i + 1, data, i, end);
				returned = true;
				size--;
			}
		}
		
		return returned;
	}

	
	
	public boolean removeAll(Collection<?> arg0) {

		boolean returned = false;
		
		for(Object j: arg0) {
			if(this.contains(j)) {
				this.remove(j);
				returned = true;
			}
		}
		return returned;
	}

	
	
	public boolean retainAll(Collection<?> arg0) {

		boolean returned = false;
		
		for(Object j: this) {
			if(!arg0.contains(j)) {
				this.remove(j);
			}
		}
		
		return returned;
	}

	
	public int size() {

		return size;
	}

	
	
	public Object[] toArray() {

		dataCopy = (T[]) new Object[size];

		System.arraycopy(data, 0, dataCopy, 0, size);
		
		return dataCopy;
	}

	
	public <T> T[] toArray(T[] arg0) {
		T[] temp = null;
		
		for(int i = 0; i < arg0.length ; i++) {
			temp[i] = arg0[i];
		}
		return temp;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> sorted = new ArrayList<T>();
	  	ArrayList<T> temp = new ArrayList<T>(); 
		  for (int i = 0; i < data.length - 1; i++) {
			  int j, minIndex;
			  for (j = i + 1, minIndex = i; j < data.length; j++)
			  if (data[j] != null && data[minIndex] != null && cmp.compare(data[j], data[minIndex]) < 0)
				  minIndex = j;
			  	temp.add(data[i]);
			  	data[i] = data[minIndex];
			  	data[minIndex] = temp.get(0);
			  	temp.clear();
			  }
		  
		  for(int i = 0; i < data.length ; i++) {
			  if(data[i] != null) {
				  sorted.add(data[i]);
			  }
		  }
		return sorted;
	}
	



	private class ArrayCollectionIterator implements Iterator<T>
	{
		
		public ArrayCollectionIterator()
		{

		}

		public boolean hasNext() {

			return index < size;
		}

		public T next() {

			if(index == size) {
				throw new IndexOutOfBoundsException();
			}
			
			return data[index++];
		}

		public void remove() {
			
			int end = data.length - (index + 1);
			System.arraycopy(data, index, data, index - 1, end);
			index--;
			size--;
		}

	}

}
