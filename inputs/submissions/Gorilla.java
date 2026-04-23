//comments: 91
//comment length: 5962
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
	private void grow()
	{
		T temparr[] = (T[]) new Object[data.length * 2];
		
		for(int i = 0; i < data.length; i++)
			temparr[i] = data[i];
		
		data = temparr;
	}

	
	public boolean add(T newItem) {

		for(int i = 0; i < size; i++)
			if(data[i].equals(newItem))
				return false;
		
		if(size >= data.length)
			grow();
		
		data[size] = newItem;
		
		size++;
		return true;
	}

	
	@SuppressWarnings("unchecked")
	public boolean addAll(Collection<? extends T> allNew) {

		Iterator<? extends T> allItr = allNew.iterator();
		boolean isFalse = false;
		
		while(allItr.hasNext())
		{
			Object element = allItr.next();
			if(this.add((T)element))
				isFalse = true;
		}
		
		return isFalse;
	}

	
	@SuppressWarnings("unchecked")  
	public void clear() {

		data = (T[]) new Object[10];
		size = 0;
	}

	
	public boolean contains(Object findMe) {

		for(int i = 0; i < size; i++)
			if(data[i].equals(findMe))
				return true;
		
		return false;
	}

	
	public boolean containsAll(Collection<?> findCol) {

		Iterator<?> findItr = findCol.iterator();
		
		while(findItr.hasNext())
		{
			Object element = findItr.next();
			if(!this.contains(element))
				return false;
		}
		
		return true;
	}

	
	public boolean isEmpty() {

		if(size == 0)
			return true;
					
		return false;
	}

	
	public Iterator<T> iterator() {

		ArrayCollectionIterator finItr = new ArrayCollectionIterator();
		
		return finItr;
	}

	
	public boolean remove(Object deleteMe) {

		if(!this.contains(deleteMe))
			return false;
		
		int tempNum = 0;
		for(int i = 0; i < size; i++)
			if(data[i].equals(deleteMe))
			{
				tempNum = i;
				break;
			}
		
		for(int x = tempNum; x < size; x++)
			data[x] = data[x+1];
		
		size--;
		return true;
	}

	
	public boolean removeAll(Collection<?> delAll) {

		Iterator<?> delColItr = delAll.iterator();
		boolean isFalse = false;
		
		while(delColItr.hasNext())
		{
			Object element = delColItr.next();
			if(this.remove(element))
				isFalse = true;
		}
		
		return isFalse;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		
		Iterator<?> argItr = arg0.iterator();
		ArrayCollection<T> tempCol = new ArrayCollection<T>();
		
		while(argItr.hasNext())
		{
			@SuppressWarnings("unchecked")
			T thisObj = (T) argItr.next();
			if(this.contains(thisObj))
				tempCol.add(thisObj);
		}
		if(!tempCol.isEmpty())
		{
			this.data = tempCol.data;
			return true;
		}
		return false;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {

		Object arr[] = new Object[size];
		
		for(int i = 0; i < size; i++)
			arr[i] = data[i];
		
		return arr;
	}

	
	
	@SuppressWarnings({ "unchecked", "hiding" })
	public <T> T[] toArray(T[] arg0) {
		
		for(int i = 0; i < size; i++)
			arg0[i] = (T) data[i];
		
		return arg0;
	}

	
	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		@SuppressWarnings("unchecked")
		T startList[] = (T[]) new Object[size];
		T finList[] = this.toArray(startList);
		ArrayList<T> returnList = new ArrayList<T>();
		
		for (int i = 0; i < size - 1; i++) {
			  int j, minIndex;
			  
			  for (j = i + 1, minIndex = i; j < size; j++)
				  if (cmp.compare(finList[j], finList[minIndex]) < 0)
					  	minIndex = j;
			  
			  T temp = finList[i];
			  
			  finList[i]= finList[minIndex];
			  
			  finList[minIndex] = temp;
		  }
		  
		for(int temp = 0; temp < size; temp++)
			returnList.add(finList[temp]);
		
		return returnList;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		private int tempSize = 0;
		
		
		public boolean hasNext() {

			if(tempSize >= size)
				return false;
			
			return true;
		}

		
		public T next() {
		
			if(!hasNext())
				throw new NoSuchElementException();
			
			tempSize++;
			
			return data[tempSize-1];
		}

		
		public void remove() {
			
			if(tempSize == 0)
				throw new IllegalStateException();
			
			T tempItem = data[tempSize - 1];
			
			
			if(data[tempSize] == tempItem)
				throw new IllegalStateException();
			
			for(int x = tempSize - 1; x < size; x++)
				data[x] = data[x+1];
			
			size--;
			tempSize--;
			
		}

	}

}
