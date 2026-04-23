//comments: 39
//comment length: 3145
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
	public ArrayCollection(){
		size = 0; 
		data = (T[]) new Object[10]; 
	}

	
	@SuppressWarnings("unchecked")
	private void grow(){
		T data2[]; 
		data2 = (T[]) new Object[size*2];

		for(int i = 0; i < size; i++) 
			data2[i] = data[i];

		data = (T[]) new Object[size*2]; 
		for(int i = 0; i < size; i++) 
			data[i] = data2[i];
	}

	
	public boolean add(T arg0) {
		for(T element : data) 
			if(arg0.equals(element))
				return false;

		if(size == data.length)
			grow();

		data[size] = arg0;
		size++;
		return true;
	}
	
	
	public boolean addAll(Collection<? extends T> arg0) {
		T data2[];
		data2= (T[]) new Object[arg0.size()]; 
		Object[] temp = arg0.toArray(); 
		int index = 0; 

		for(int i = 0; i < temp.length;i++) { 
			if(!ArrayCollection.this.contains(temp[i])) {
				data2[index]=(T) temp[i];
				index++;
			}
		}

		if(index==0)
			return false;
		
		for(T element : data2) { 
			if(element==null)
				break;
			if(size == data.length)
				grow();
			data[size] = element;
			size++;
		}

		return true;

	}
	
	
	public void clear() {
		data = (T[]) new Object[10];
		size =0;
	}

	
	public boolean contains(Object arg0) {
		for(T element : data)
			if(arg0.equals(element))
				return true;
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0) {
		if(arg0.size()>size) 
			return false;
		
		Object[] temp = arg0.toArray();
		for(Object element : temp) {
			if(!ArrayCollection.this.contains(element))
				return false;
		}

		return true;
	}

	
	public boolean isEmpty() {
		for(T element : data) {
			if(element!=null)
				return false;
		}

		return true;
	}

	
	public Iterator<T> iterator() {
		Iterator<T> temp = new ArrayCollectionIterator();
		return temp;
	}

	

	public boolean remove(Object arg0) {
		boolean result = false;
		int counter = -1;

		for(int i = 0; i < size; i ++) { 
			if(data[i].equals(arg0)) {
				result = true;
				counter = i;
				break;
			}
		}

		if(counter != -1 && counter < size) { 
			for(int i = counter; i < size-1; i ++) 
				data[i] = data[i+1];
			size--;
			data[size] = null;
		}

		return result;
	}

	
	public boolean removeAll(Collection<?> arg0) {
		boolean result = false;
		Object[] temp = arg0.toArray();
		
		for(Object element : temp) {
			if(contains(element)) {
				remove(element);
				result = true;
			}
		}
		
		return result;
	}

	
	public boolean retainAll(Collection<?> arg0) {
		boolean result = false;
		Object[] temp = arg0.toArray();
		ArrayCollectionIterator iter = new ArrayCollectionIterator();

		while(iter.hasNext()) {
			T element = iter.next();
			boolean isIn = false;
			for(Object obj: temp ) { 
				if(element.equals(obj))
					isIn = true;
			}
			
			if(!isIn) { 
 				iter.remove();
				result = true;
			}
		}
			
		return result;
	}

	
	public int size() {
		return size;
	}

	
	public Object[] toArray() {
		T[] temp =  (T[]) new Object[size];
		
		for(int i = 0; i < size; i++)
			temp[i] = data[i];

		return temp;
	}

	
	public <T> T[] toArray(T[] arg0) {
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp){
		ArrayList<T> data2;
		data2= new ArrayList<T>(size);
		
		for(int i = 0; i < data.length; i++) {
			data2.add(data[i]);
		}

		for (int i = 0; i < size - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < size; j++)
				if (cmp.compare(data2.get(j), data2.get(minIndex)) < 0)
					minIndex = j;
			Object temp = data2.get(i);
			data2.set(i, data2.get(minIndex));
			data2.set(minIndex, (T)temp);
		}
		return data2;
	}

	
	public String toString() {
		String rt = "";
		for(int i = 0; i<data.length;i++) {
			rt += data[i]+" ";
		}
		
		return rt+"\n";
	}

	private class ArrayCollectionIterator implements Iterator<T>{
		private boolean removable;
		private int current = 0;	
		Object temp;

		
		public ArrayCollectionIterator(){
			removable = false;
		}

		
		public boolean hasNext() {
			if(current < (data.length - 1) && data[current] != null) {
				removable = true;
			}
			else {
				removable = false;
			}

			return removable;
		}

		
		public T next() {
			if(hasNext()) {
				temp = data[current];
				current++;
				return data[current-1];
			}
			else {
				throw new NoSuchElementException();
			}
		}

		
		public void remove() {
			if(removable) {
				ArrayCollection.this.remove(temp);
				current--;
				removable = false;
			}
			else {
				throw new IllegalStateException();
			}
		}
	}
}