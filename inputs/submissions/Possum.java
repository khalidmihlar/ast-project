//comments: 22
//comment length: 2221


package assignment3;

import java.util.*;


public class ArrayCollection<T> implements Collection<T>
{

    T       data[]; 
    int     size; 
    int     cursor;
    boolean canBeRemoved = false;


    @SuppressWarnings("unchecked")
    public ArrayCollection()
    {
        size = 0;
        
        data = (T[]) new Object[3]; 
    }

    public ArrayCollection(T[] data, int size)
    {
        this.data = data;
        this.size = size;
    }

    
    @SuppressWarnings("unchecked")
    private void grow()
    {
        
        int newArraySize = size() * 2;

        
        T tempData[] = (T[]) new Object[newArraySize];

        
        for (int i = 0; i < data.length; i++)
            tempData[i] = data[i];

        data = tempData;
    }

    public boolean add(T arg0)
    {
        
        if (isEmpty())
        {
            data[0] = arg0;
            size++;
            return true;
        }

        
        if (!contains(arg0))
        {
            
            ensureCapacity();

            Iterator iterator = this.iterator();
            while (iterator.hasNext())
                iterator.next();

            data[cursor] = arg0;
            size++;
            return true;
        }

        return false;
    }

    public boolean addAll(Collection<? extends T> arg0)
    {
        for (T object : arg0)
            if (!add(object))
                return false;
        return true;
    }

    public void clear()
    {
        data = (T[]) new Object[3];
        size = 0;
    }

    public boolean contains(Object arg0)
    {
        Iterator iterator = this.iterator();
        while (iterator.hasNext())
            if (iterator.next() == arg0)
                return true;

        return false;
    }

    public boolean containsAll(Collection<?> arg0)
    {
        for (Object object : arg0)
            if (!contains(object))
                return false;

        return true;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public Iterator<T> iterator()
    {
        return new ArrayCollectionIterator();
    }

    public boolean remove(Object arg0)
    {
        if (contains(arg0))
        {
            Iterator iterator = this.iterator();
            while (iterator.hasNext())
            {
                if (arg0 == iterator.next())
                {
                    iterator.remove();
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeAll(Collection<?> arg0)
    {
        boolean objectWasRemoved = false;

        for (Object object : arg0)
            if (remove(object))
                objectWasRemoved = true;

        return objectWasRemoved;
    }

    public boolean retainAll(Collection<?> arg0)
    {
        boolean retainedSome = false;
        T       temp[]       = (T[]) new Object[data.length]; 

        for (int i = 0, j = 0; i < size(); i++)
        {
            if (arg0.contains(data[i]))
            {
                temp[j++] = data[i];
                retainedSome = true;
            }
        }

        data = temp;

        return retainedSome;
    }

    
    public int size()
    {
        return size;
    }

    public Object[] toArray()
    {
        Object[] newArray = (T[]) new Object[size()];
        for (int i = 0; i < size(); i++)
            newArray[i] = data[i];
        return newArray;
    }

    
    public <T> T[] toArray(T[] arg0)
    {
        return null;
    }

    private void ensureCapacity()
    {
        
        if (this.size == data.length) grow();
    }

    
    public ArrayList<T> toSortedList(Comparator<? super T> cmp)
    {
        for (int i = 0; i < this.size() - 1; i++)
        {
            int j, minIndex;
            for (j = i + 1, minIndex = i; j < this.size(); j++)
                if (cmp.compare(this.data[j], this.data[minIndex]) < 0)
                    minIndex = j;

            T temp = this.data[i];
            this.data[i] = this.data[minIndex];
            this.data[minIndex] = temp;
        }

        return new ArrayList<>(Arrays.asList(this.data));
    }

    private class ArrayCollectionIterator implements Iterator<T>
    {
        public ArrayCollectionIterator()
        {
            cursor = 0;
        }

        public boolean hasNext()
        {
            
            return cursor < size();
        }

        public T next()
        {
            if (hasNext())
            {
                canBeRemoved = true;

                
                cursor++;

                
                return data[cursor - 1];
            }

            throw new NoSuchElementException("There are no more elements!");
        }

        public void remove()
        {
            if (!canBeRemoved)
                throw new IllegalStateException("Need to call next!");

            for (int i = cursor; i <= size(); i++)
                data[cursor - 1] = data[cursor];

            data[cursor] = null;

            canBeRemoved = false;
        }

    }

}
