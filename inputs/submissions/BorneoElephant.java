//comments: 60
//comment length: 4427
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T> {

	private T data[]; 
	private int size; 

	private T temp[]; 


	
	@SuppressWarnings("unchecked")
	public ArrayCollection() {
		size = 0;
		
		
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow() {

		temp = (T[]) new Object[data.length * 2];

		for (int i = 0; i < data.length; i++) {

			temp[i] = data[i];

		}

		this.data = temp;

	}

	
	public boolean add(T arg0) {

		
		for (int i = 0; i < data.length; i++) {

			if (this.contains(arg0)) {
				return false;
			}

		}

		
		if (data.length == size) {
			grow();
		}
		
		data[size++] = arg0;
		









		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0) {

		boolean change = false;

		for (T a : arg0) {

			change = add(a);
			
		}

		
		return change;
	}

	
	public T get(int index) {

		if (index < size() && index >= 0)
			return data[index];

		return null;

	}

	
	@SuppressWarnings("unchecked")
	public void clear() {

		

		temp = (T[]) new Object[0];

		data = temp;

	}

	
	public boolean contains(Object arg0) {
				
		for (int i = 0; i < size; i++) {
						
			if (data[i].equals(arg0)) {
				return true;
			}
			
		}

		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		
		for (Object each : arg0) {
			
			if (!this.contains(each)) {
				return false;
			}
		}

		return true;
	}

	
	public boolean isEmpty() {
		

		if (size() == 0)
			return true;

		return false;
	}

	
	public Iterator<T> iterator() {
		
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0) {
	

		for (int i = 0; i < size(); i++) {

			if (i >= 0 && i != size()-1) {

				if (arg0.equals(data[i])) {
					data[i] = data[i++];
				}

			}
			
			if (i == size()-1) {

				data[i] = null;
			}

		}

		return false;
	}

	
	public boolean removeAll(Collection<?> arg0) {

		boolean change = false;

		for (int i = 0; i < data.length; i++) {
			

			
			while (arg0.iterator().hasNext()) {
			
				if (arg0.contains(data[i])) {
					
					System.out.println(" match " + data[i]);
					
					remove(data[i]);
					change = true;
				}

			}
		}

		return change;
	}

	
	public boolean retainAll(Collection<?> arg0) {

		boolean toggle = false;

		while (this.iterator().hasNext()) {

			for (Object a : arg0) {

				if (!(this.iterator().next().equals(a))) {
					this.iterator().remove();
					toggle = true;
				}

			}

		}

		return toggle;
	}

	
	public int size() {

		int count = 0;

		for (int i = 0; i < data.length; i++) {

			if (data[i] != null) {
				count++;
			}

		}
		return count;
	}

	
	public Object[] toArray() {
		

		Object[] array = new Object[size()];

		
		for (int i = 0; i < size(); i++) {

			array[i] = data[i];

		}

		return array;
	}

	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {

		ArrayList<T> list = new ArrayList<T>();

		for (int i = 0; i < data.length - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < data.length; j++)
				if (cmp.compare(data[j], data[minIndex]) < 0)
					minIndex = j;
			ArrayList<T> temp = (ArrayList<T>) data[i];
			list.set(i, data[i]);
			list.set(minIndex, (T) temp);
		}

		return list;
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {

		
		int index;

		
		boolean removed;

		
		public ArrayCollectionIterator() {

			index = -1;
			removed = false;

		}

		
		public boolean hasNext() {
			

			if(data.length == 0 && data[index+1] == null) {
				System.out.println("Size is 0");
				return false;
			}







			
			return true;
		}

		
		public T next() {


						
			if (index >= size())
				throw new NoSuchElementException();
			
			
			removed = false;

			



			return data[++index];
		}

		
		public void remove() {

			if (removed == true) {
				throw new IllegalStateException();
			}

			ArrayCollection.this.remove(data[index-1]);

			index--;
			removed = true;

		}

	}

}
