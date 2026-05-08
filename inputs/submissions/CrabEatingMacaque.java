//comments: 34
//comment length: 3578
package assignment3;

import java.util.ArrayList;
import java.util.Arrays;
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
		ArrayCollection<T> grownArray = new ArrayCollection<T>();
		
		grownArray.size = this.size();
		grownArray.data = (T[]) new Object[this.size()*2];
		
		for (int i = 0; i < this.size(); i++)
			grownArray.data[i] = this.data[i];
		
		this.data = grownArray.data;
		
		
		
		
		
		
	}


	
	public boolean add(T addee) {
		
		if (addee == null) {
			return true;
		}
		
		if (this.contains(addee)) {
			return false;
		}
	
		
		if (this.size() == this.data.length)
			this.grow();
		
		this.data[this.size()] = addee;
		this.size++;
		
		return true;
		
		
	}

	
	public boolean addAll(Collection<? extends T> e) {
		if (e == null) {
			return true;
		}
		Iterator<? extends T> itr = e.iterator();
	
		int doneWork = 0; 
		
		while (itr.hasNext()) {
			T element = (T) itr.next();
			
			boolean added = this.add(element);
			
			if (added == true) {
				doneWork++;
			}
		}
		
		if (doneWork > 0)
			return true;
		
		else return false;
				
	}

	
	public void clear() {
		for (int i = 0; i < this.size(); i++)
			this.data[i] = null;
		
		this.size = 0;
	}

	
	public boolean contains(Object arg0) {
		if (arg0 == null) {
			return true;
		}
		
		for (int i = 0; i < this.size(); i++) {
			if (this.data[i].equals(arg0))
				return true;
		}
			
		return false;
	}
	
	
	
	public boolean containsAll(Collection<?> arg0) {
		if (arg0 == null) {
			return true;
		}
		Iterator<?> itr = arg0.iterator();
		
		while (itr.hasNext()) {
			 Object token = itr.next();
		
			if (this.contains(token) == false)
				return false;
		
		}
		return true;
	}

	
	public boolean isEmpty() {
		if (this.size() == 0)
			return true;
		return false;
	}

	
	public Iterator<T> iterator() {
		
		return new ArrayCollectionIterator();
	}

	
	@SuppressWarnings("unchecked")
	public boolean remove(Object arg0) {
		
		int position = -1;
		T copyArray[] = (T[]) new Object[this.size() - 1];
		int oFound = 0; 

		for (int i = 0; i < this.size(); i++) {

			if (this.data[i].equals(arg0)) {
				position = i;
				oFound = 1;
			}
		}

		if (oFound == 1) {
			for (int j = 0; j < position; j++) { 
				copyArray[j] = this.data[j]; 
			}

			for (int k = position; k < this.size() - 1; k++) 
				copyArray[k] = this.data[k + 1];

			this.data = copyArray;
			this.size--;

			return true;
		}

		return false;
	}

	
	
	public boolean removeAll(Collection<?> arg0) {
		if (arg0 == null)
			return true;

		Iterator<?> itr = arg0.iterator();
		int itemsRemoved = 0;

		while (itr.hasNext()) {
			Object token = itr.next();
			boolean removed = this.remove(token);

			if (removed == true) {
				itemsRemoved++;
			}
		}

		if (itemsRemoved > 0) {
			return true;
		}

		return false;
	}

	public boolean retainAll(Collection<?> arg0) {
		
		if (this.size() == 0) { 
			return false;
		}
		
		
		if (arg0 == null) { 
			this.clear();
			return true;
		}

		Iterator<?> itrArrayCollection = this.iterator();
		int itemsRemoved = 0;

		while (itrArrayCollection.hasNext()) {

			Object token = itrArrayCollection.next();

			Iterator<?> itrInnerCollection = arg0.iterator(); 

			while (itrInnerCollection.hasNext()) {
				Object innerToken = itrInnerCollection.next();

				if (innerToken.equals(token))
					break;

				if (!(itrInnerCollection.hasNext())) {
					itrArrayCollection.remove();
					itemsRemoved++;
				}
			}
		}

		if (itemsRemoved > 0) {
			return true;
		}

		return false;
	}

	
	public int size() {
		return this.size;
		
	}

	
	public Object[] toArray() {
		
		Object[] arr = new Object[this.size()];
		for (int i = 0; i < this.size(); i++) {
			arr[i] = this.data[i];
		}
		
		return arr;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}




	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp) {
		ArrayList<T> arrList = new ArrayList<T>(this.size());

		for (int i = 0; i < this.size(); i++)
			arrList.add(this.data[i]);

		for (int i = 0; i < this.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < this.size(); j++)
				if (cmp.compare(arrList.get(j), arrList.get(minIndex)) < 0)
					minIndex = j;
			T temp = arrList.get(i);
			arrList.set(i, arrList.get(minIndex));
			arrList.set(minIndex, temp);

			
		}
		return arrList;
	}


	class ArrayCollectionIterator implements Iterator<T>
	{
		private int index;
		private T element;
		
		public ArrayCollectionIterator()
		{
			index = 0;
			
		}
		@Override
		public boolean hasNext() {
			return ((index < size) && (data[index] != null));
			
		}
		@Override
		public T next() {
			if (!(this.hasNext()))
				throw new NoSuchElementException();
			element = data[index];
			return data[index++];
		}
			
		@Override
		public void remove() {
			if (element == null) {
				throw new IllegalStateException();
			}
			
			T copyArray[] = (T[]) new Object[size - 1];
			
			if(index == size)
				index = size-1;
			
			for (int i = 0; i < index; i++)
				copyArray[i] = data[i];
			if (index > size) {
				data = copyArray;
				index--;
				size--;
				element = null;
				
			}
				
			else {
				for (int j = index; j < size - 1; j++)
					copyArray[j] = data[j - 1];

				data = copyArray;
				index--;
				size--;
				element = null;
			}
			}
		public T getElement() {
			return element;
		}
		
		public int getIndex() {
			return index;
		}
	}
	
	
	protected class OrderByInt implements Comparator<Integer> {

		@Override
		public int compare(Integer lhs, Integer rhs) {
			return (int) (lhs - rhs);
		}

		
	}

	protected class OrderByString implements Comparator<String> {

		public int compare(String lhs, String rhs) {
			return (lhs.compareTo(rhs));
		}
	}

	
	protected class OrderByLong implements Comparator<Long> {

		public int compare(Long lhs, Long rhs) {
			return (int) (lhs - rhs);
		}

	}
	
	protected class OrderByFloat implements Comparator<Float> {
		public int compare(Float lhs, Float rhs) {
			return (int) (lhs - rhs);
		}
	}

}
