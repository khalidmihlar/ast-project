//comments: 28
//comment length: 2901
package assignment3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class ArrayCollection<T> implements Collection<T>
{

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
		T[] temp = (T[]) new Object[data.length * 2];
		for (int i = 0; i < data.length; i++)
		{
			temp[i] = data[i];
		}
		data = temp;
	}

	
	public boolean add(T arg0)
	{
		if (contains(arg0))
		{
			return false;
		}
		if (size >= data.length)
		{
			grow();
		}
		data[size] = arg0;
		size++;
		return true;
	}

	
	public boolean addAll(Collection<? extends T> arg0)
	{
		boolean success = false;

		for (T o : arg0)
		{
			if (add(o))
			{
				success = true;
			}
		}
		return success;
	}

	
	public void clear()
	{
		for (int i = 0; i < size; i++)
		{
			data[i] = null;
		}
		size = 0;
	}

	
	public boolean contains(Object arg0)
	{
		for (int i = 0; i<size; i++)
		{
			if (data[i].equals(arg0))
				return true;
		}
		return false;
	}

	
	public boolean containsAll(Collection<?> arg0)
	{
		for (Object o : arg0)
		{
			if (!contains(o))
			{
				return false;
			}
		}
		return true;
	}

	
	public boolean isEmpty()
	{
		return size == 0;
	}

	
	public Iterator<T> iterator()
	{
		return new ArrayCollectionIterator();
	}

	
	public boolean remove(Object arg0)
	{
		if (!contains(arg0))
		{
			return false;
		}
		int index = 0;
		for (int i = 0; i < size; i++)
		{
			if (data[i].equals(arg0))
			{
				index = i;
				break;
			}
		}
		for (int i = index; i < size - 1; i++)
		{
			data[i] = data[i + 1];
		}
		data[--size] = null;
		return true;
	}

	
	public boolean removeAll(Collection<?> arg0)
	{
		boolean success = false;
		for (Object o : arg0)
		{
			if (remove(o))
			{
				success = true;
			}
		}
		return success;
	}

	
	public boolean retainAll(Collection<?> arg0)
	{
		boolean success = false;
		ArrayCollectionIterator iter = new ArrayCollectionIterator();
		while (iter.hasNext())
		{
			if (!arg0.contains(iter.next()))
			{
				iter.remove();
				success = true;
			}
		}
		return success;
	}

	
	public int size()
	{
		return size;
	}

	
	public Object[] toArray()
	{
		Object[] temp = new Object[size];

		for (int i = 0; i < size; i++)
		{
			temp[i] = data[i];
		}

		return temp;
	}

	
	public <T> T[] toArray(T[] arg0)
	{
		return null;
	}

	
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> list = new ArrayList<T>(this);
		for (int i = 0; i < size - 1; i++)
		{
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
			{
				if (cmp.compare(list.get(j), list.get(minIndex)) < 0)
				{
					minIndex = j;
				}
			}
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
		return list;
	}

	
	private class ArrayCollectionIterator implements Iterator<T>
	{
		int position;
		boolean remove;

		
		public ArrayCollectionIterator()
		{
			position = 0;
			remove = false;
		}

		
		public boolean hasNext()
		{
			return position < size;
		}

		
		public T next() throws NoSuchElementException
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			remove = true;
			return data[position++];
		}

		
		public void remove() throws IllegalStateException
		{
			if (!remove)
			{
				throw new IllegalStateException();
			}
			ArrayCollection.this.remove(data[position - 1]);
			position--;
			remove = false;
		}

	}

}
