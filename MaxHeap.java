/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxheap;
import java.util.Scanner;
import java.util.Random;

import java.util.Arrays;
public final class MaxHeap <T extends Comparable<? super T>>
        implements MaxHeapInterface<T>
{
    private T[] heap;
    private int lastIndex;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    private int swaps;
    
    public MaxHeap()
    {
        this(DEFAULT_CAPACITY);
    }
    
    public MaxHeap(int initialCapacity)
    {
        if(initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else
            checkCapacity(initialCapacity);
        
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
        swaps = 0;
    }
    
    public MaxHeap(T[] entries)
    {
        this(entries.length);
        assert initialized = true;
        for(int index = 0; index < entries.length; index++)
            heap[index + 1] = entries[index];
        for(int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
            reheap(rootIndex);
        System.out.println(heap[1]);
    }
    
    public void add(T newEntry)
    {
        checkInitialization();
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0)
        {       
            heap[newIndex] = heap[parentIndex];
            swaps++;
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        }
        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    }
    
    public T removeMax()
    {
        checkInitialization();
        T root = null;
        if(!isEmpty())
        {
            root = heap[1];
            heap[1] = heap[lastIndex];
            lastIndex--;
            reheap(1);
        }
        return root;
    }
    
    private void reheap(int rootIndex)
    {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;
        while(!done && (leftChildIndex <= lastIndex))
        {
            int largerChildIndex = leftChildIndex;
            int rightChildIndex = leftChildIndex + 1;
            if((rightChildIndex <= lastIndex) &&
                    heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0)
            {
                largerChildIndex = rightChildIndex;
            }
            if(orphan.compareTo(heap[largerChildIndex]) < 0)
            {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
                swaps++;
            }
            else
                done = true;
        }
        heap[rootIndex] = orphan;
    }
    
    public T getMax()
    {
        checkInitialization();
        T root = null;
        if(!isEmpty())
            root = heap[1];
        return root;
    }
    
    public boolean isEmpty()
    {
        return lastIndex < 1;
    }
    
    public int getSize()
    {
        return lastIndex;
    }
    
    public void clear()
    {
        checkInitialization();
        while(lastIndex > -1)
        {
            heap[lastIndex] = null;
            lastIndex--;
        }
        lastIndex = 0;
    }
    
    private void checkInitialization()
    {
        if(!initialized)
            throw new SecurityException("MaxHeap object is not initialized "
                                        + "properly.");
    }
    
    private void ensureCapacity()
    {
        if(lastIndex == heap.length)
        {
            int newLength = heap.length * 2;
            checkCapacity(newLength);
            heap = Arrays.copyOf(heap, newLength);
        }
    }
    
    private void checkCapacity(int capacity)
    {
        if(capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a heap whose "
                                            + "capacity is smaller than the "
                                            + "maximum of " + MAX_CAPACITY);
    }
    
    public void levelOrderTraversal()
    {
        for(int i = 1; i <= lastIndex; i++)
        {
            System.out.print(heap[i]);
            if(i != lastIndex)
                System.out.print(", ");           
        }
        System.out.println();
    }
    
    public int numberOfSwaps()
    {
        return swaps;
    }   
}
