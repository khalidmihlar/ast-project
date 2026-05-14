//comments: 51
//comment length: 3612
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
	public void grow()
	{
		
		
		T tempData[] = (T[]) new Object[2 * data.length];
		
		for(int i = 0; i < data.length; i++) {
			tempData[i] = data[i];
		}


		data = tempData;

	}


	public boolean add(T arg0) {
		
		
		if (data.length == size()) {
			grow();
		}
		
		for(int i = 0; i < data.length; i++) {
			if(data[i] == arg0 && data[i] != null) {

				return false;
			}
			else if(data[i] == null) {

				data[i] = arg0;
				size++;
				return true;
			} 
		}
		return false;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		
		int added = 0;
		
		for (T x : arg0) {
			if (data.length == size()) {
				grow();
			}
			
			if(this.contains(x)) {
				continue;
			} else {
				this.add(x);
				added++;
			}
		}	
		
		return added > 0 ? true : false;
	}

	public void clear() {
		
		for(int i = 0; i < data.length; i++) {
			data[i] = null;
		}
		
	}

	public boolean contains(Object arg0) {
		
		for(T x : data) {

			if(x != null && x == arg0) {
				return x.equals(arg0);
			} 
			if(x == null) {
				break;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		
		Iterator<T> itr = iterator();
		T x;
		
		
		int containsCount = 0;
		for(int i = 0; i < this.size(); i++) {
			Iterator<?> itr2 = arg0.iterator();
			 x = itr.next();
			
			for(int j = 0; j < arg0.size(); j++) {
				if(itr2.next() == x) {
					containsCount++;
					break;
				}
			}
		}
		if(containsCount == arg0.size()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isEmpty() {
		
		if(size() == 0) {
			return true;
		}
		return false;
	}

	public Iterator<T> iterator() {
		
		Iterator<T> itr = this.new ArrayCollectionIterator();
		return itr;
	}

	public boolean remove(Object arg0) {
		
		Iterator<T> itr = this.iterator();
		while(itr.hasNext()) {
			T xyz = itr.next();

			if(arg0 == xyz) {
				itr.remove();
				size--;
				return true;
			}
		}
		
		return false;
	}

	public boolean removeAll(Collection<?> arg0) {
		
		Iterator itr = arg0.iterator();
		int removeCounter = 0;
		
		for(int i = 0; i < arg0.size(); i++) {
			Object x = itr.next();
			
			if(this.contains(x)) {
				this.remove(x);

				removeCounter++;
			}
		}
		
		if(removeCounter > 0) {
			for(Object o : data) {
				System.out.println(o);
			}
			return true;
		} else {
			return false;
		}
	}
	
	public boolean retainAll(Collection<?> arg0) {
		ArrayList<Object> removeList = new ArrayList<Object>();
		Iterator itr = this.iterator();
		Iterator itr2;
		int removedItems = 0;
		boolean hasMatch;
		Object x;
		Object y;
		
		for(int i = 0; i < this.size(); i++) {
			hasMatch = false;
			itr2 = arg0.iterator();
			x = itr.next();
			
			for(int j = 0; j < arg0.size(); j++) {
				y = itr2.next();

				if(x.equals(y)) {
					hasMatch = true;
				}
			}
			
			if(hasMatch == false) {

				removeList.add(x);
				removedItems++;
			} 
		}
		



		size -= removedItems;

		if(removedItems > 0) {
			this.removeAll(removeList);
			return true;
		} else {
			return false;
		}
	}
	
	
	public int size() {
		return size;
	}
	
	
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		
		T[] tempArray = (T[]) new Object[this.size];
		for(int i = 0; i < this.size(); i++) {
			tempArray[i] = this.data[i];
		}
		
		return tempArray;
	}
	
	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp){
		ArrayList<T> sortedArray = new ArrayList<T>();

		for(T t : data) {
			sortedArray.add(t);
		}

		for(int i = 0; i < this.size(); i++) {
			int min = i;

			for(int j = i + 1; j < this.size(); j++) {



				if(cmp.compare(sortedArray.get(min), sortedArray.get(j)) > 0) {
					min = j;
				}
			}

			swap(sortedArray, i, min);
		}

		return sortedArray;
	}
	
	public <T> void swap(ArrayList<T> list, int currentMin, int newMin) {
		T current = list.get(currentMin);
		list.set(currentMin, list.get(newMin));
		list.set(newMin, current);
	}
	

	public class OrderString implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			

			return o1.compareTo(o2);
		}
	}
	
	public class OrderInt implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			

			return o1.compareTo(o2);
		}
	}

	private class ArrayCollectionIterator implements Iterator<T>
	{	
		
		public int nextIndex = 0;
		private int nextSinceRemove = 0;
		
		public ArrayCollectionIterator()
		{
			
		}

		public boolean hasNext() {
			
			
			return nextIndex < size();
		}

		public T next() {
			

			nextSinceRemove++;
			return data[nextIndex++];
		}	

		public void remove() {
			int resumeIndex = (nextIndex - 1);
			
			
			nextIndex -= 1;



			
			data[nextIndex] = null;
			
			while(hasNext()) {

				
				data[nextIndex] = data[nextIndex + 1];
				
				nextIndex++;
			}
			
			nextIndex = resumeIndex;

			nextSinceRemove = 0;
		}
	}
}
