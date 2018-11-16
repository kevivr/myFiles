package decoder;

/**
 * An abstract Abstract Tree node that abstracts common features in LeafNode and IntermediateNode.
 */
public abstract class AbstractNode implements TreeNode {

  protected Character code;

  /**
   * Default Constructor to create an AbstractNode Object.
   */
  public AbstractNode() {
    this.code = Character.MIN_VALUE;
  }

  /**
   * Parameterized Constructor that given a Character assigns it to the code member of this
   * AbstractNode.
   *
   * @param code the character passed to the Constructor.
   */
  public AbstractNode(Character code) {
    this.code = code;
  }

  /**
   * Function to return Code of this node.
   *
   * @return Character that is the code that the node represents.
   */
  @Override
  public Character getCode() {
    return this.code;
  }
}
