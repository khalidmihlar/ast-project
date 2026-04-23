//comments: 44
//comment length: 4697
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	T data[]; 
	int size; 
	int capacity = 10; 

	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() 
	{
		capacity = size*2; 
		T temp[] = (T[]) new Object[capacity];
		for(int i = 0; i < size; i++) {
			temp[i] = data[i];
		}

		data = temp;
	}

	
	public boolean add(T arg0) {
		boolean isFound = false;
		for(int i = 0; i < size; i++) {
			if(data[i].equals(arg0)) {
				isFound = true;
			}
		}
		if(isFound == false) {
			if(size == capacity) {
				grow();
			}
			data[size++] = arg0;
			return true;
		}
		return false;
	}

	
	public boolean addAll(Collection<? extends T> arg0) { 
		T temp[] = (T[]) arg0.toArray();
		int checkSize = size;
		for(int i = 0; i < temp.length; i++) {

			add(temp[i]);
		}
		if(checkSize < size) {
			return true;
		}
		return false;
	}

	
	public void clear() { 
		T temp[] = (T[]) new Object[10];
		data = temp;
		size = 0;
		
	}

	
	public boolean contains(Object arg0) { 
		for(int i = 0; i < size; i++) {
			if(data[i].equals(arg0)) {
				return true; 
			}
		}
		return false; 
	}

	
	public boolean containsAll(Collection<?> arg0) { 
		int count = 0;
		T temp[] = (T[]) arg0.toArray();
		int inputSize = temp.length;
		for(int i = 0; i < inputSize; i++) {
			for(int j = 0; j < size; j++) {
				if(temp[i].equals(data[j])) {
					count++;
				}
			}
		}
		if(count == inputSize) {
			return true;
		}
		return false;
	}

	
	public boolean isEmpty() { 
		if(size == 0) {
			return true;
		}
		return false;
	}

	
	public Iterator<T> iterator() {
		ArrayCollectionIterator itr = new ArrayCollectionIterator();
		return itr;
	}

	
	public boolean remove(Object arg0) { 
		boolean isFound = false;
		int storeI = 0;
		for(int i = 0; i < size; i++) {

			if(data[i].equals(arg0)) {
				isFound = true;
				storeI = i;
			}
			if(isFound) {
				if(storeI == size-1) {
					data[i] = null;
				}
				else {
					data[i] = data[i+1];

				}
			}
		}
		if(isFound) {
			size--;
		}
		return isFound;
	}

	
	public boolean removeAll(Collection<?> arg0) { 
		T temp[] = (T[]) arg0.toArray();
		int checkSize = size;
		for(int i = 0; i < temp.length; i++) {
			remove(temp[i]);
		}
		if(checkSize > size) {
			return true;
		}
		return false;
	}

	
	public boolean retainAll(Collection<?> arg0) { 
		T temp[] = (T[]) arg0.toArray();
		int checkSize = size;
		boolean keep = false;
		Iterator<T> itr = iterator();
		
		while(itr.hasNext()) {
			keep = false;
			T tempNext = itr.next();
			for(int i = 0; i < temp.length; i++) {
				T check = temp[i];
				if(check.equals(tempNext)) {
					keep = true;
				}
			}
			if(keep == false && tempNext != null) {
				itr.remove();
			}
		}

		if(checkSize > size) {
			return true;
		}
		return false;
	}

	
	public int size() { 
		return size;
	}

	
	public Object[] toArray() {
		Object temp[] = new Object[size];
		for(int i = 0; i < size; i++) {
			temp[i] = this.data[i];
		}
		return temp;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) 
	{
		ArrayList<T> output = new ArrayList<T>();
		for(int i = 0; i < size; i++) {
			output.add(data[i]);
		}
		for(int i = 0; i < output.size()-1; i++) {
			int j, minIndex;
			for(j = i+1, minIndex = i; j < output.size(); j++) {
				if(cmp.compare(output.get(j), output.get(minIndex)) < 0)
					minIndex = j;
				T temp = output.get(i);
				output.set(i,output.get(minIndex));
				output.set(minIndex, temp);
				
				
			}
		}
		return output;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		int index;
		boolean isCalled = false; 
		
		
		public ArrayCollectionIterator()
		{
			index = 0;
		}

		
		public boolean hasNext() {
			try {
				T checkError = data[index+1];
			}
			catch(Exception ex){
				return false;
			}
			return true;
		}

		
		public T next() {
			isCalled = true;
			return data[index++];
		}

		
		public void remove() {
			if(isCalled == false) {
				throw new IllegalStateException();
			}
			isCalled = false;
			int temp = index - 1;
			ArrayCollection.this.remove(ArrayCollection.this.data[temp]);
			index--;
			
			
		}

	}

}
