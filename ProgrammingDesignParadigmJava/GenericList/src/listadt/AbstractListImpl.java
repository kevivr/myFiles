package listadt;

public abstract class AbstractListImpl<T> implements ListADT<T> {
  protected GenericListADTNode<T> head;

  /**
   * Return the number of objects currently in this list.
   *
   * @return the size of the list.
   */
  @Override
  public int getSize() {
    return head.count();
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
