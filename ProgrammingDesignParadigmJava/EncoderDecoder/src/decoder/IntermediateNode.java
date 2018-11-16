package decoder;

import java.util.LinkedList;
import java.util.List;

/**
 * A class that represents nodes in tree that have children. Have same operations as a TreeNode.
 */
public class IntermediateNode extends AbstractNode {

  private List<TreeNode> children;

  /**
   * Constructor to create an empty Intermediate Node Object.
   */
  public IntermediateNode() {
    super();
    this.children = new LinkedList<TreeNode>();
  }

  /**
   * Constructor to create an Intermediate Node Object. Creates an empty list of Treenode to keep
   * track of its Children.
   *
   * @param code the code at the node.
   */
  public IntermediateNode(Character code) {
    super(code);
    this.children = new LinkedList<TreeNode>();
  }

  /**
   * Method to add a child to an intermediate node. If a child treenode with the code is already
   * present, the treenode is returned else, the treenode with code is added as a child to the
   * Intermediate Node.
   *
   * @param tree a treenode with the code to be added to the intermediate node
   * @return an already existing tree node with given code or create a new tree node and return it.
   */
  public TreeNode addChild(TreeNode tree) {
    if (tree.getClass() == LeafNode.class && this.children.size() > 0) {
      if (iterateThroughNode(tree) != tree) {
        throw new IllegalStateException("Cannot add a root node here, tree branches further");
      }
      this.children.add(tree);
      return tree;
    } else {
      TreeNode t = iterateThroughNode(tree);
      if (t != tree) {
        if (t.getClass() == IntermediateNode.class) {
          return t;
        } else if (t.getClass() == LeafNode.class) {
          t.addChild(tree);
        }
      }
      this.children.add(tree);
      return tree;
    }
  }

  /**
   * Function to iterate through the whole tree that this node is a part of and then return the node
   * whose code matches the code of the node passed as an argument.
   */
  private TreeNode iterateThroughNode(TreeNode node) {
    for (TreeNode t : this.children) {
      if (t.getCode() == node.getCode()) {
        return t;
      }
    }
    return node;
  }

  /**
   * Given a node with a character code, returns this node if the character code in this node equals
   * the code of the node passed to the function. If not, it checks for the same with all its
   * children and if such a node doesn't exist even then, throws an IllegalStateException.
   *
   * @param tree the node with character to be checked for in the tree.
   * @return the node with code equal to the code of the parameter passed.
   */
  public TreeNode returnNode(TreeNode tree) {
    TreeNode t = iterateThroughNode(tree);
    if (t != tree) {
      if (t.getClass() == IntermediateNode.class) {
        return t;
      } else if (t.getClass() == LeafNode.class) {
        return t.returnNode(tree);
      }
    }
    throw new IllegalStateException("Node with the character code doesn't exist, " +
            "Message entered cannot be decoded using this encoding scheme");
  }

  /**
   * Given a size checks if this node has number of children equal to the size parameter passed and
   * checks if its children also have number of children equal to the size parameter passed, it
   * returns  false if check fails for any node, and if passes for every node, returns true.
   *
   * @param size value against which check is made.
   * @return boolean value according to if the check passes or fails.
   */
  @Override
  public boolean isCodeComplete(int size) {
    boolean result = false;
    if (this.children.size() != size) {
      return false;
    }
    for (TreeNode t : this.children) {
      result = t.isCodeComplete(size);
      if (!result) {
        break;
      }
    }
    return result;
  }

}