package listadt;

import java.util.function.Function;

/**
 * This is the implementation of a generic list. Specifically it implements the listadt.ListADT
 * interface
 *
 * @param <T> the type parameter
 */
public class ListADTImpl<T> extends AbstractListImpl<T> {

  /**
   * Instantiates a new List adt.
   */
  public ListADTImpl() {
    head = new GenericEmptyNode();
  }

  /**
   * Instantiates a new List adt.
   *
   * @param listToMakeMutable the list to make mutable
   */
  public ListADTImpl(ImmutableListADTImpl<T> listToMakeMutable) {
    this.head = new GenericEmptyNode();
    for (int i = 0; i < listToMakeMutable.getSize(); i++) {
      T value = listToMakeMutable.get(i);
      this.head = this.head.addBack(value);
    }
  }

  //a private constructor that is used internally (see map)
  private ListADTImpl(GenericListADTNode<T> head) {
    this.head = head;
  }

  /**
   * Add an object to the front of this list.
   *
   * @param b the object to be added to the front of this list.
   */
  @Override
  public void addFront(T b) {
    head = head.addFront(b);
  }

  /**
   * Add an object to the back of this list (so it is the last object in the list).
   *
   * @param b the object to be added to the back of this list
   */
  @Override
  public void addBack(T b) {
    head = head.addBack(b);
  }

  /**
   * Add an object to this list so that it occupies the provided index. Index begins with 0.
   *
   * @param index the index to be occupied by this object, beginning at 0.
   * @param b     the object to be added to the list.
   */
  @Override
  public void add(int index, T b) {
    head = head.add(index, b);
  }

  /**
   * Remove the first instance of this object from this list.
   *
   * @param b the object to be removed.
   */
  @Override
  public void remove(T b) {
    head = head.remove(b);
  }

  /**
   * Operation to return an immutable counterpart of this list.
   *
   * @return Immutable list that accumulates elements in encounter order of this list.
   */
  @Override
  public ListADT<T> toImmutable() {
    return new ImmutableListADTImpl<T>(new ListADTImpl<T>(this.head));
  }

  /**
   * Operation to return an Mutable counterpart of this list.
   *
   * @return Mutable list that accumulates elements in encounter order of this list.
   */
  @Override
  public ListADT<T> toMutable() {
    return new ListADTImpl<T>(this.head);
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
    return new ListADTImpl(head.map(converter));
  }
}