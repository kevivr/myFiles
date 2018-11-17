package lookandsay;

import java.util.Iterator;

/**
 * Interface that represents reverse iterator operations. Has two functions, one that returns the
 * previous element of the collection that is being iterated and one that returns if there is such
 * an element. It also extends the Iterator interface, so any implementation of this interface must
 * override those methods.
 *
 * @param <T> the type of the iterator.
 */
public interface RIterator<T> extends Iterator<T> {
  /**
   * Returns the previous element in the iteration.
   *
   * @return the previous element in the iteration.
   */
  T prev();

  /**
   * Returns true if the iteration has previous elements. (In other words, returns true if prev()
   * would return an element rather than throwing an exception.)
   *
   * @return true if the iteration has elements prior to this element.
   */
  boolean hasPrevious();
}
