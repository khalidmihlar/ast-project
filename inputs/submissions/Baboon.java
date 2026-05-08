//comments: 26
//comment length: 2074
package assignment3;

import java.util.*;
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

	
	private void grow()
	{
		
		
		
		T copy[] = data.clone();
		data = (T[]) new Object[size*2];
		System.arraycopy(copy, 0, data, 0, size);

	}


	public boolean add(T arg0) {
		if(data.length==size)
			grow();
		if(contains(arg0))
			return false;
		data[size] = arg0;
		size++;
		return true;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		
		boolean added = false;
		for(T item :arg0){
			if(add(item))
				added=true;
		}
		return added;
	}

	public void clear() {
		
		size = 0;
		data = (T[]) new Object[10];
	}

	public boolean contains(Object arg0) {
		
		for(int i =0; i<size; i++){
			if(arg0.equals(data[i]))
				return true;
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		
		for(Object item :arg0){
			if(!contains((T)item))
				return false;
		}
		return true;
	}

	public boolean isEmpty() {
		
		return size==0;
	}

	public Iterator<T> iterator() {
		
		return new ArrayCollectionIterator();
	}

	public boolean remove(Object arg0) {
		

		if(!contains(arg0))
			return false;
		
		int index = 0;
		for(int i =0; i<size; i++){
			if(arg0.equals(data[i])) {
				index = i;
				break;
			}
		}

		T[] copy = data.clone();

		data = (T[]) new Object[size-1];
		T[] first =Arrays.copyOfRange(copy,0,index);
		System.arraycopy(first, 0, data, 0, first.length);

		T[] second =Arrays.copyOfRange(copy,index+1,size);
		System.arraycopy(second, 0, data, index, second.length);
		size--;
		return true;
	}

	public boolean removeAll(Collection<?> arg0) {
		boolean removed=false;
		for(Object item : arg0){
			if(remove(item))
				removed=true;
		}
		return removed;
	}

	public boolean retainAll(Collection<?> arg0) {
		
		boolean removed = false;
		Iterator<T>  it = iterator();
		while(it.hasNext()){
			T item = it.next();
			if(!arg0.contains(item)){
					it.remove();
					removed = true;
			}

		}

		return removed;
	}

	public int size() {
		
		return size;
	}

	public Object[] toArray() {
		
		T[]clone =data.clone();
		T[]copy = (T[])new Object[size];
		System.arraycopy(clone, 0, copy, 0, size);

		return copy;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		for (int i = 0; i < data.length - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++) {
				if (cmp.compare(data[j], data[minIndex]) < 0)
					minIndex = j;
			}
			T temp = data[i];
			data[i] = data[minIndex];
			data[minIndex] = temp;
		}
		ArrayList<T> list = new ArrayList<T>();
		for(int i = 0 ; i<size;i++)
			list.add(data[i]);
		return  list;
	}



	private class ArrayCollectionIterator implements Iterator<T>
	{
		int current;
		boolean nexted;
		public ArrayCollectionIterator()
		{
			current = -1;
			nexted = false;
			
		}

		public boolean hasNext() {
			
			try {
				if(data[current+1]==null)
					return false;
			}
			catch (ArrayIndexOutOfBoundsException e){
				return  false;
			}
			return true;
		}

		public T next() {
			
			T item;

			try {
				current++;
				item=data[current];
				if(item==null)
					throw  new NoSuchElementException();;
				nexted=true;


			}
			catch (ArrayIndexOutOfBoundsException e){
				throw  new NoSuchElementException();
			}
			return item;
		}

		public void remove() {
			
			if(!nexted)
				throw  new IllegalStateException();
			nexted = false;


			if(ArrayCollection.this.remove(data[current]))
				current--;

		}

	}

}
