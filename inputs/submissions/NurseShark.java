//comments: 66
//comment length: 5991
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
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
	private void grow() {
		T temp[] = (T[]) new Object[data.length*2];
		for (int i = 0; i < size; i++) {
			temp[i] = data[i];
		}
		data = temp;
	}

	
	
	public boolean add(T arg0) {

		
	if (this.size +1 > data.length) {
			this.grow();
	}
		
		for (int i = 0; i < size; i++) {
			
			if (data[i].equals(arg0)) {
				return false;
			}
		}
		data[size] = arg0;
		size++;
		return true;	
	}
	
	

	
	@SuppressWarnings("unchecked")
	public boolean addAll(Collection<? extends T> arg0) {
		boolean out = false;
		Iterator<? extends T> iterator = arg0.iterator();
		T temp = (T) new Object();
		for (int i = 0; i < arg0.size(); i++) {
			temp = (T) iterator.next();
			boolean checker = true;
			
			for (int j = 0; j < size; j++) {	
				if (temp.equals(data[j])) {
					checker = false; 
				}
			}
			
			if (checker) { 
				this.add(temp);
				out = true; 
			}
		}
		
		return out;
		
	}
	
	
	
	
	public void clear() {
		for (int i = 0; i < size; i++) {
			data[i] = null;
		}
		size = 0;
	}
	
	

	
	public boolean contains(Object arg0) {
		
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				return true;
			}
		}
		
		return false;
	}
	
	

	
	@SuppressWarnings("unchecked")
	public boolean containsAll(Collection<?> arg0) {
		
		
		Iterator<?> iterator = arg0.iterator();
		T temp = (T) new Object();
		
		
		for (int i = 0; i < arg0.size(); i++) {
			
			temp = (T) iterator.next(); 
		
			boolean insideCheck = true; 	
			
			
			for (int j = 0; j < size; j++ ) { 
				if (data[j].equals(temp)) {
					insideCheck = false; 
					break;
				}
			}
			
			
			if (insideCheck) { 
				return false;
			}
		
		}
		return true;
	}

	

	
	public boolean isEmpty() {

		if (size == 0 ) {
			return true;
		} else return false;
	}
	
	
	
	public Iterator<T> iterator() {
		ArrayCollectionIterator itr = new ArrayCollectionIterator();
		return itr;
	}
	

	
	
	
	@SuppressWarnings("unchecked")
	public boolean remove(Object arg0) {
		int indexOfRemoval = 0;
		boolean argNotContained = true;
		
		
		for (int i = 0; i < size; i++) { 
			if (data[i].equals(arg0)) {
				argNotContained = false;
				indexOfRemoval = i;
			}
		}
		
		if (argNotContained) { 
			return false;
		} else {
		
			size--; 
		
			T temp[] = (T[]) new Object[data.length]; 
		
			for (int i = 0; i < indexOfRemoval; i++) { 
				temp[i] = data[i];
			}
		
			for (int i = indexOfRemoval+1; i < size + 1; i++) { 
				temp[i-1] = data[i];
			}
		
			this.data = temp; 
			return true; 
		
		}
	}
	

	
	
	
	@SuppressWarnings ("unchecked")
	public boolean removeAll(Collection<?> arg0) {
		boolean anythingRemoved = false;
		Iterator<?> itr = arg0.iterator();
		int howManyToBeRemoved = 0;
		int[] indecesToBeRemovedTemp = new int[size]; 
		
		
		while (itr.hasNext()) {
			T temp = (T) itr.next();
			
			
			for (int j = 0; j < size; j++) {
				if (temp.equals(data[j])) {
					indecesToBeRemovedTemp[howManyToBeRemoved] = j; 
					howManyToBeRemoved++;			
					anythingRemoved = true;
				}
			}
		}
		
		int[] indecesToBeRemoved = new int[howManyToBeRemoved];
		for (int i = 0; i < howManyToBeRemoved; i++) {
			indecesToBeRemoved[i] = indecesToBeRemovedTemp[i];
		}
		
		
		
		LinkedList<T> itemsToBeRemoved = new LinkedList<T>();
		
		for (int i = 0; i < indecesToBeRemoved.length; i++) {
			itemsToBeRemoved.add(data[indecesToBeRemoved[i]]);
		}
		
		Iterator<T> itr2 = itemsToBeRemoved.iterator();
		
		while (itr2.hasNext()) {
			this.remove(itr2.next());
		}
		
		return anythingRemoved;
	}
	
	

	@SuppressWarnings("unchecked")
	public boolean retainAll(Collection<?> arg0) {
		
		
		T[] newDataUnsized = (T[]) new Object[arg0.size()];
		int howManyItemsToRetain = 0; 
		boolean out = false; 
		
		
		Iterator<T> itrArrayCollection = new ArrayCollectionIterator(); 
		while (itrArrayCollection.hasNext()) {
			
			T arrayCollectionReturn = (T) new Object();
			arrayCollectionReturn = itrArrayCollection.next();
			
			
			Iterator<T> itrArgument = (Iterator<T>) arg0.iterator();
			while (itrArgument.hasNext()) {
				if (arrayCollectionReturn.equals(itrArgument.next())) {
					newDataUnsized[howManyItemsToRetain] = arrayCollectionReturn;
					howManyItemsToRetain++;
					out = true;
				}
			}
		}
		
		
		T[] newDataSized = (T[]) new Object[howManyItemsToRetain];
		for (int i = 0; i < newDataSized.length; i++) {
			newDataSized[i] = newDataUnsized[i];
		}
		
		size = newDataSized.length;
		data = newDataSized;
		return out;
		
	}

	
	
	public int size() {
		return size;
	}

	
	
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] out = (T[]) new Object[size];
		for (int i = 0; i < size; i ++) {
			out[i] = (T) data[i];
		}
		return out;
	}

	
	

	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> sortList = new ArrayList<T>();
		for (int i = 0; i < size; i++) {
			sortList.add(data[i]);
		}
		
		sort(sortList, cmp);
		
		return sortList;
	}

	
	
	private void sort(ArrayList<T> list, Comparator<? super T> c) {
		for (int i = 0; i < this.size() - 1; i++) {
				int j, minIndex;
				for (j = i + 1, minIndex = i; j < list.size(); j++)
					if (c.compare(list.get(j), list.get(minIndex)) < 0)
						minIndex = j;
				T temp = list.get(i);
				list.set(i, list.get(minIndex));
				list.set(minIndex, temp);
		}
	}

	
	private class ArrayCollectionIterator implements Iterator<T> {
		
		int howManyToIterateThrough = 0;
		int howManyHaveBeenIterated = 0;
		boolean legalToRemove = false;
		
		
		public ArrayCollectionIterator() {
			howManyToIterateThrough = size;
			howManyHaveBeenIterated = 0;
		}

		
		public boolean hasNext() {
			if (howManyHaveBeenIterated < howManyToIterateThrough) {
				return true;
			} else {
				return false;
			}
		}

		
		public T next() {
			legalToRemove = true;
			howManyHaveBeenIterated++;
			return data[howManyHaveBeenIterated-1];
		}

		
		public void remove() {
			if (legalToRemove) {
				ArrayCollection.this.remove(data[howManyHaveBeenIterated-1]);
				howManyToIterateThrough--;
				howManyHaveBeenIterated--;
				legalToRemove = false;
			} else {
				throw new IllegalStateException();
			}
			
			
			
		}

	}

}
