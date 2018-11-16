package listadt;

import java.util.function.Function;

/**
 * The type Immutable list adt.
 *
 * @param <T> the type parameter
 */
public final class ImmutableListADTImpl<T> extends AbstractListImpl<T> {

  /**
   * Instantiates a new Immutable list adt.
   *
   * @param listToMakeImmutable the list to make immutable
   * @throws IllegalArgumentException when list passed as parameter is null.
   */
  public ImmutableListADTImpl(ListADT<T> listToMakeImmutable) {
    if (listToMakeImmutable == null) {
      throw new IllegalArgumentException("List passed cannot be Null");
    }
    this.head = new GenericEmptyNode();
    for (int i = 0; i < listToMakeImmutable.getSize(); i++) {
      T value = listToMakeImmutable.get(i);
      this.head = this.head.addBack(value);
    }
  }

  //a private constructor that is used internally (see map)
  private ImmutableListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  /**
   * Guaranteed to throw an exception and leave the list unmodified.
   *
   * @throws UnsupportedOperationException when attempted to call the function.
   */
  @Override
  public void addFront(T b) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Operation not supported for Immutable Lists");
  }

  /**
   * Guaranteed to throw an exception and leave the list unmodified.
   *
   * @throws UnsupportedOperationException when attempted to call the function.
   */
  @Override
  public void addBack(T b) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Operation not supported for Immutable Lists");
  }

  /**
   * Guaranteed to throw an exception and leave the list unmodified.
   *
   * @throws UnsupportedOperationException when attempted to call the function.
   */
  @Override
  public void add(int index, T b) {
    throw new UnsupportedOperationException("Operation not supported for Immutable Lists");
  }

  /**
   * Return the number of objects currently in this list.
   *
   * @return the size of the list.
   */
  @Override
  public int getSize() {
    return this.head.count();
  }

  /**
   * Guaranteed to throw an exception and leave the list unmodified.
   *
   * @throws UnsupportedOperationException when attempted to call the function.
   */
  @Override
  public void remove(T b) {
    throw new UnsupportedOperationException("Operation not supported for Immutable Lists");
  }

  /**
   * Get the (index)th object in this list.
   *
   * @param index the index of the object to be returned.
   * @return the object at the given index.
   * @throws IllegalArgumentException if an invalid index is passed.
   */
  @Override
  public T get(int index) throws IllegalArgumentException {
    if ((index >= 0) && (index < getSize())) {
      return head.get(index);
    } else {
      throw new IllegalArgumentException("Invalid index");
    }
  }

  /**
   * Operation to return an immutable counterpart of this list.
   *
   * @return Immutable list that accumulates elements in encounter order of this list.
   */
  @Override
  public ListADT<T> toImmutable() {
    return new ImmutableListADTImpl<T>(this.head);
  }

  /**
   * Operation to return an Mutable counterpart of this list.
   *
   * @return Mutable list that accumulates elements in encounter order of this list.
   */
  @Override
  public ListADT<T> toMutable() {
    return new ListADTImpl(new ImmutableListADTImpl<T>(this.head));
  }

  /**
   * A general purpose map higher order function on this list, that returns the corresponding list
   * of type R.
   *
   * @param converter the function that converts T into R.
   * @param <R>       the type of data in the resulting list.
   * @return the resulting list that is identical in structure to this list, but has data of type R.
   */
  @Override
  public <R> ListADT<R> map(Function<T, R> converter) {
    return new ImmutableListADTImpl(head.map(converter));
  }

  /**
   * Operation to return  a String representation of the list. String representation constructed by
   * appending String representation of head's value to the String representaion of the rest of the
   * list.
   *
   * @return String representation of the List.
   */
  @Override
  public String toString() {
    return "(" + head.toString() + ")";
  }
}
