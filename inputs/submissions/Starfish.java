//comments: 9
//comment length: 1463
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
		T tempdata[] = data.clone();
		data = (T[]) new Object[data.length*2];
		
		for( int i = 0; i < tempdata.length; i++ )
			data[i] = tempdata[i];
	
	}


	public boolean add(T arg0) {
		
		for( int i = 0; i < data.length; i++ )
			if ( data[i] == null ) {
				data[i] = arg0;
				size++;
				return true;
			} else if( data[i].equals(arg0) )
				return false;
		
		grow();
		add(arg0);
		
		return true;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		boolean added = false;
		
		for( T t: arg0  )
			if ( add(t) )
				added = true;
		
		return added;
	}

	@SuppressWarnings("unchecked")
	public void clear() {
		this.data = (T[]) new Object[10];
		this.size = 0;
	}

	public boolean contains(Object arg0) {
		for( T t: data) {
			if( t != null && t.equals(arg0) )
				return true;
		}
		return false;
	}

	public boolean containsAll(Collection	<?> arg0) {
		
		for( Object o : arg0 )
			if( contains(o) )
				continue;
			else
				return false;
		
		return true;
	}

	public boolean isEmpty() {
		return ( size == 0 );
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator( this );
	}
	
	private void delete( int index ) {
		for( int i = index; i < data.length-1; i++ ) {
			data[i] = data[i+1];
		}
		size--;
		data[data.length-1] = null;
	}

	public boolean remove(Object arg0) {
		for( int i = 0; i < data.length; i++ )
			if( data[i].equals(arg0) ) { 
				delete(i);
				i--;
				return true;
			}
		
		return false;
	}

	public boolean removeAll(Collection<?> arg0) {
		
		boolean removed = false;
		
		for( Object o : arg0 )
			if( remove(o) )
				removed = true;
		
		return removed;
	}

	public boolean retainAll(Collection<?> arg0) {
		
		boolean removed = false;
		
		for( int i = 0; i < data.length; i++ ) {
			
			if( data[i] == null )
				continue;
			
			boolean has = false;
			
			for( Object o : arg0 )
				if( data[i].equals(o) ) {
					has = true;
					break;
				}
			
			if( !has ) {
				delete(i);
				i--;
				removed = true;
			}
		}
		return removed;
	}

	public int size() {
		return this.size;
	}

	public Object[] toArray() {
		Object array[] = new Object[size];
		for( int i = 0; i < size; i++ )
			array[i] = data[i];
		return array;
	}

	
	@SuppressWarnings("hiding")
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> list = new ArrayList<T>();
		for( int i = 0; i < size; i++ ) {
			list.add(data[i]);
		}
		
		for (int i = 0; i < size; i++) {
			
			if( list.get(i) == null )
				continue;
			
			int j, minIndex;
			
			for (j = i + 1, minIndex = i; j < size; j++)
				if( list.get(j) == null )
					continue;
				else if (cmp.compare(list.get(j), list.get(minIndex)) < 0)
			  		minIndex = j;
			
			  	T temp = list.get(i);
			  	list.set(i, list.get(minIndex));
			  	list.set(minIndex, temp);
		 }
		
		return list;
	}

	private class ArrayCollectionIterator implements Iterator<T>
	{
		
		private ArrayCollection<T> parent;
		private T data[];
		private int index;
		
		public ArrayCollectionIterator( ArrayCollection<T> parent )
		{
			this.parent = parent;
			this.data = parent.data;
			this.index = 0;
		}

		public boolean hasNext() {
			return ( index >= data.length - 1 );
		}

		public T next() {
			index++;
			if( index > data.length )
				throw new NoSuchElementException();
			
			return data[index-1];
		}

		public void remove() {
			parent.delete(index);
		}

	}

}
