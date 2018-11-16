package decoder;

import java.util.HashSet;

/**
 * This is a simple implementation of the Decoder Interface. It offers a single constructor that
 * takes the coding symbols as a single string (e.g. 01 in the above example). This class builds a
 * coding tree and then uses it to decode a message using the above process.
 */
public class DecoderImpl implements Decoder {

  /**
   * Members of the class.
   * <ul>
   * <li> symbols : A Hashset to keep track of the valid characters acceptable in a code.</li>
   * <li> everyCode : A String that consists of all symbols that have
   * valid code in this tree.</li>
   * <li> root : A TreeNode Object that depicts the root of this tree.</li>
   * </ul>
   */
  private HashSet<Character> symbols = new HashSet<Character>();
  private HashSet<Character> addedSymbols = new HashSet<Character>();
  private String everyCode = "";
  private TreeNode root;

  /**
   * Constructor for Objects of this class. Takes in a String that contains the various valid
   * symbols for this encoding scheme. Pushes in the different symbols in the symbols hashset and if
   * there is a duplicate symbol within the string, an Illegal Argument Exception is thrown.
   *
   * @param symbols the String that contains the different allowed symbols for this encoding
   *                scheme.
   */
  public DecoderImpl(String symbols) {
    if (symbols == null || symbols.equals("")) {
      throw new IllegalArgumentException("null or empty String is not a " +
              "valid acceptable character");
    }
    for (char ch : symbols.toCharArray()) {
      if (this.symbols.contains(ch)) {
        throw new IllegalArgumentException("Pass a string consisting of non repeating characters");
      }
      this.symbols.add(ch);
    }
    this.root = new IntermediateNode();
  }

  /**
   * The addCode method takes a symbol and its code as a character and string respectively. This
   * method should add this code to the coding tree. Method throws an IllegalStateException if the
   * code contains symbols other than the coding symbols.
   *
   * @param symbol A character whose code is to be added to the coding tree.
   * @param code   A String that represents the code for the symbol.
   */
  @Override
  public void addCode(Character symbol, String code) {
    checkIsDuplicateSymbol(symbol);
    checkAreValidCharacter(code);
    TreeNode tree = this.root;
    for (int i = 0; i < code.length(); i++) {
      if (i == code.length() - 1) {
        tree = tree.addChild(new LeafNode(symbol, code.charAt(i)));
      } else {
        tree = tree.addChild(new IntermediateNode(code.charAt(i)));
      }
    }
    this.everyCode = this.everyCode.concat(symbol + ":" + code + "\n");
    this.addedSymbols.add(symbol);
  }

  /**
   * Method to check if symbol passed is already represented in the tree.
   *
   * @param symbol the character passed as a parameter to perform the check on.
   */
  private void checkIsDuplicateSymbol(Character symbol) {
    if (symbol == null) {
      throw new IllegalStateException("Symbol cannot be null");
    }
    if (this.addedSymbols.contains(symbol)) {
      throw new IllegalStateException("Symbol Already present");
    }
  }

  /**
   * Check if a given String has all valid characters according to this encoding scheme.
   *
   * @param message the String to check
   * @return true or false depending on if the check is true or false.
   */
  private void checkAreValidCharacter(String message) {
    if (message == null || message.equals("")) {
      throw new IllegalStateException("Code cannot contain null or be empty");
    }
    for (int i = 0; i < message.length(); i++) {
      if (!this.symbols.contains(message.charAt(i))) {
        throw new IllegalStateException("Method contains unacceptable characters");
      }
    }
  }

  /**
   * The decode method takes an encoded message as a string, and returns the decoded message. as a
   * string using the coding tree created thus far. This method throws an IllegalStateException if
   * the decoding fails.
   *
   * @param message A string that is to be decoded using the coding tree formed thus far.
   * @return The decoded String.
   */
  @Override
  public String decode(String message) {
    String result = "";
    checkAreValidCharacter(message);
    TreeNode tree = this.root;
    for (int i = 0; i < message.length(); i++) {
      tree = tree.returnNode(new IntermediateNode(message.charAt(i)));
      if (tree.getClass() == LeafNode.class || i == message.length() - 1) {
        if (tree.getClass() != LeafNode.class) {
          throw new IllegalStateException("Incomplete code");
        }
        LeafNode l = (LeafNode) tree;
        result = result.concat(Character.toString(l.getSymbol()));
        tree = this.root;
      }
    }
    return result;
  }

  /**
   * The allCodes method returns the codes entered thus far as a string. This string contains each
   * symbol x and its code yyy on a separate line, in the form x:yyy.
   *
   * @return the string that contains all codes entered thus far.
   */
  @Override
  public String allCodes() {
    return this.everyCode;
  }

  /**
   * The isCodeComplete method returns true if the code entered so far is complete, false otherwise.
   * A code is said to be complete if every valid encoded message can be successfully decoded.
   *
   * @return true if code entered so far is complete, else false.
   */
  @Override
  public boolean isCodeComplete() {
    return root.isCodeComplete(this.symbols.size());
  }
}
