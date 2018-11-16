package decoder;

/**
 * A LeafNode class that represents a node at the last level or the bottom level. It has all
 * operations depicted by the TreeNode interface.
 */
public class LeafNode extends AbstractNode {

  private Character symbol;

  /**
   * Constructor to create a LeafNode Object.
   *
   * @param symbol the symbol the leaf represents.
   * @param code   the code at the node.
   */
  public LeafNode(Character symbol, Character code) {
    super(code);
    this.symbol = symbol;
  }

  /**
   * Function to return symbol this leaf node represents.
   *
   * @return Character that is the symbol that the leaf node represents.
   */
  public Character getSymbol() {
    return this.symbol;
  }

  /**
   * Throws an IllegalStateException when attempting to add nodes to a leaf node as leaf node
   * already denotes code for a symbol.
   */
  @Override
  public TreeNode addChild(TreeNode tree) {
    throw new IllegalStateException("Cannot add any more branches to the sub tree");
  }

  /**
   * If the code of this Node is equal to the code of the node passed as a parameter, return this
   * else return the parameter itself.
   *
   * @param node the node with character to be checked for in the tree.
   * @return this node or the parameter as necessary.
   */
  @Override
  public TreeNode returnNode(TreeNode node) {
    if (this.code == node.getCode()) {
      return this;
    } else {
      return node;
    }
  }

  /**
   * Returns true by default as no leaf nodes have children.
   */
  @Override
  public boolean isCodeComplete(int size) {
    return true;
  }

}
