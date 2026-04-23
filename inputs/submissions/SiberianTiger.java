//comments: 25
//comment length: 2738
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

		size = data.length * 2; 

		T newData[] = (T[]) new Object[size]; 

		int i = 0;

		
		for (T item : data) {
			newData[i] = item;
			i++;
		}

		data = newData; 
	}

	public boolean add(T arg0) {
		for (int i = 0; i < data.length; i++) {
			if (arg0 == data[i]) {
				return false;
			} else {
				if (data[i] == null) {
					data[i] = arg0;
					break;
				} else if (i == data.length - 1) {
					grow();
				}
			}
		}
		return true;
	}

	

	public boolean addAll(Collection<? extends T> arg0) {
		boolean isTrue = false;

		Object[] newArray = arg0.toArray();
		for (Object item : newArray) {
			for (T thing : data) {
				if (item != thing) {
					add((T) item);
					isTrue = true;

				}
			}
		}
		return isTrue;
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		
		data = (T[]) new Object[0];
	}

	public boolean contains(Object arg0) {
		
		for (T item : data) {
			try {
				if (item.equals(arg0)) {
					return true;
				}	
			}catch(NullPointerException e) {
				
			}
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		

		Object[] newArray = arg0.toArray();

		int itemCount = 0;
		for (Object item : newArray) {
			if (item != null) {
				itemCount++;
			}
		}

		
		int x = 0;
		for (Object item : newArray) {
			for (T thing : data) {
				if (item != null && item.equals(thing)) {
					x++;
				}
			}
		}
		if (x == itemCount) {
			return true;
		}
		return false;
	}

	
	public boolean isEmpty() {
		for (T item : data) {
			if (item != null) {
				return false;
			}
		}
		return true;
	}

	public Iterator<T> iterator() {
		
		return new ArrayCollectionIterator();
	}

	public boolean remove(Object arg0) {
		boolean isIn = false;
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null && data[i].equals(arg0)) {
				data[i] = null;
				isIn = true;
				int j = i; 
				while (j < data.length) {
					if (j + 1 <= data.length-1) {
						data[j] = data[j + 1];
					}
					j++;
				}
			}
		}
		return isIn;
	}

	public boolean removeAll(Collection<?> arg0) {
		boolean isThere = false;
		Object[] newArray = arg0.toArray();
		
		for (Object thing: newArray) {
			for (T item: data) {
			if (item != null && item.equals(thing)) {
				remove(item);
				isThere = true;
			}
			}
		}
		return isThere;
		}
	

	public boolean retainAll(Collection<?> arg0) {		
		ArrayCollectionIterator iterator = new ArrayCollectionIterator();
		int i = 0;
		
		Object[] arg0Array = arg0.toArray();
		
		Boolean finalResult = false;
		try {
			while(i<data.length) {
				if(data[i].equals(arg0Array[i])) {
					iterator.next();
					arg0.iterator().next();
				}
				if(!data[i].equals(arg0Array[i])) {
					ArrayCollection.this.remove(iterator.next());
					finalResult = true;
					iterator.next();
					arg0.iterator().next();
				}
				if(i == arg0.size()) {
					for(int nullCount=arg0.size(); nullCount<data.length;nullCount++) {
						data[nullCount] = null;
					}
				}
			i++;
		}
		}catch(NullPointerException e) {
			
		}
		return finalResult;
	}

	public int size() {
		int finalSize = 0; 
		for (T item: data) { 
			if (item != null) {
				finalSize++; 
			}
		}
		return finalSize++; 
	}

	public Object[] toArray() {

		Object[] array = new Object[data.length];

		for (int i = 0; i < array.length; i++) {
			array[i] = data[i];
		}

		return array;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> finalArray = new ArrayList<T>();

		for(T item : this.data) {
			finalArray.add(item);
		}
		
		try {
			for(int i=0; i<finalArray.size()-1; i++) {
				int minIndex = i;
				for(int j= i+1; j<finalArray.size(); j++) {
					if(cmp.compare(this.data[minIndex], this.data[j])>0) {
						minIndex = j;
					}
					T temp = this.data[minIndex];
					finalArray.set(minIndex, this.data[i]);
					finalArray.set(i, temp);
				}
			}	
		}catch(NullPointerException e) {
			
		}
	System.out.println(finalArray.toString());
	return finalArray;
}
	
	private class ArrayCollectionIterator implements Iterator<T> {
		public int iterNum = 0;
		public int i = 0;
		public int j = 0;

		public ArrayCollectionIterator() {
			
		}

		
		public boolean hasNext() {
			while(iterNum<data.length-1) {
				if(data[iterNum+1] != null) {
					iterNum++;
					return true;
				}
				else {
				}
				
			}
			return false;
		}
		

		public T next() {
			T finalItem = null;
			if(hasNext() == true) {
				finalItem = data[iterNum-1];
				i = j;
			}
			else if(iterNum>data.length) {
				throw new NoSuchElementException("No Such Element Exception Occured");
			}
			return finalItem;
		}

		public void remove() {
			if (i == j) {
				if(hasNext() == true) {
					ArrayCollection.this.remove(next());
					j++;
				}
				
			} else {
				throw new IllegalStateException("Illegal State Exception Occured");
			}
		}
	}
}

