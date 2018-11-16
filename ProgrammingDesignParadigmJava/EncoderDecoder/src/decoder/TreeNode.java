package decoder;

/**
 * An interface that represents a node in a tree. It has the following methods : getCode method that
 * returns the code at a node. addChild that adds a node to a tree. returnNode that returns node if
 * code is equal to the parameter passed. isCodeComplete that returns true or false according to if
 * the tree is Complete or not.
 */
public interface TreeNode {

  /**
   * Function to return Code of this node.
   *
   * @return Character that is the code that the node represents.
   */
  Character getCode();

  /**
   * Method to add a child to a Tree node. The method returns reference to the node that was just
   * added or the node with the entered code if it was already present.
   *
   * @param node a treenode with the code to be added to the intermediate node
   * @return an already existing tree node with given code or create a new tree node and return it.
   */
  TreeNode addChild(TreeNode node);

  /**
   * Given a node with a character code, return the node in the tree with the same code.
   *
   * @param node the node with character to be checked for in the tree.
   * @return the node with code equal to the code of the parameter passed.
   */
  TreeNode returnNode(TreeNode node);

  /**
   * Given a size, check if all nodes in the tree have number of children equal to the size passed.
   *
   * @param size the value to be checked against.
   * @return true or false according to if the check passes or fails.
   */
  boolean isCodeComplete(int size);
}