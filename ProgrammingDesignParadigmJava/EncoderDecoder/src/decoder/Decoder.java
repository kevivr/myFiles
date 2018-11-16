package decoder;

/**
 * This interface represents a Decoder. Decoding is the reverse of Encoding in that given a code for
 * a particular Encoding scheme, it should return the decoded sequence. This interface has the
 * following methods. An addCode method that takes a symbol and its code as a character and string
 * respectively. This method should add this code to the coding tree. It should not return anything.
 * A decode method that takes an encoded message as a string, and returns the decoded message as a
 * string using the coding tree created thus far. A allCodes method that returns the codes entered
 * thus far as a string. This string contains each symbol x and its code yyy on a separate line, in
 * the form x:yyy. A isCodeComplete method that returns true if the code entered so far is complete,
 * false otherwise. A code is said to be complete if every valid encoded message can be successfully
 * decoded. If the decoding is done by using a coding tree, then this condition is fulfilled if the
 * coding tree is full (i.e. every non-leaf node has exactly the same number of children, equal to
 * the number of coding symbols). In other words, a coding tree for coding symbols {0,1} is full if
 * each non-leaf node has exactly two children.
 */
public interface Decoder {
  /**
   * The addCode method takes a symbol and its code as a character and string respectively. This
   * method should add this code to the coding tree. Method throws an IllegalStateException if the
   * code contains symbols other than the coding symbols.
   *
   * @param symbol A character whose code is to be added to the coding tree.
   * @param code   A String that represents the code for the symbol.
   */
  void addCode(Character symbol, String code);

  /**
   * The decode method takes an encoded message as a string, and returns the decoded message. as a
   * string using the coding tree created thus far. This method should throw an
   * IllegalStateException if the decoding fails.
   *
   * @param message A string that is to be decoded using the coding tree formed thus far.
   * @return the result of decoding the given message.
   */
  String decode(String message);

  /**
   * The allCodes method returns the codes entered thus far as a string. This string contains each
   * symbol x and its code yyy on a separate line, in the form x:yyy.
   *
   * @return the string that contains all codes entered thus far.
   */
  String allCodes();

  /**
   * The isCodeComplete method returns true if the code entered so far is complete, false otherwise.
   * A code is said to be complete if every valid encoded message can be successfully decoded.
   *
   * @return true if code entered so far is complete, else false.
   */
  boolean isCodeComplete();
}
