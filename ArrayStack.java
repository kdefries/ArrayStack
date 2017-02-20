/* A class of stacks whose entries are stored in an array. */

import java.util.*;

public final class ArrayStack<T> implements StackInterface<T>
{
  private T[] stack; // Array of stack entries
  private int topIndex; // Index of Top entry
  private boolean initialized = false;
  private static final int DEFAULT_CAPACITY = 50;
  private static final int MAX_CAPACITY = 10000;
  
  public ArrayStack()
  {
    this(DEFAULT_CAPACITY);
  }
  
  public ArrayStack(int initialCapacity)
  {
    if (initialCapacity <= MAX_CAPACITY)
    {
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
      topIndex = -1;
      initialized = true;
    }
    else
      throw new IllegalStateException("Attempt to create stack whose capacity exceeds allowed maximum.");
  }
  
  private void checkInitialization()
  {
    if (!initialized)
      throw new SecurityException("ArrayStack is not initialized properly.");
  }
  
  private void checkCapacity(int capacity)
  {
    if (capacity > MAX_CAPACITY)
      throw new IllegalStateException("Attempt to create a stack whose capacity exeeds allowed "
                                        + "maximum of " + MAX_CAPACITY);
  }
  
  public void push(T newEntry)
  {
    checkInitialization();
    ensureCapacity();
    stack[topIndex + 1] = newEntry;
    topIndex++;
  }
  
  private void ensureCapacity()
  {
    if (topIndex == stack.length - 1)
    {
      int newLength = 2 * stack.length;
      checkCapacity(newLength);
      stack = Arrays.copyOf(stack, newLength);
    }
  }
  
  public T pop()
  {
    checkInitialization();
    if (isEmpty())
      throw new EmptyStackException();
    else
    {
      T top = stack[topIndex];
      stack[topIndex] = null;
      topIndex--;
      return top;
    }
  }
  
  public T peek()
  {
    checkInitialization();
    if (isEmpty())
      throw new EmptyStackException();
    else
      return stack[topIndex];
  }
  
  public boolean isEmpty()
  {
    return topIndex < 0;
  }
  
  public void clear()
  {
    while (topIndex > -1)
    {
      stack[topIndex] = null;
      topIndex--;
    }
  }
}
