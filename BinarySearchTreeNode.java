package Capitals;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        BinarySearchTreeNode.java
 * Project      Binary Search Trees
 * Description  A self-refential class representing a binary tree node of Player.      
 * Platform     jdk 1.8.0_251; NetBeans IDE 11.3; MacBook Air
 * @author	<i>Niko Culevski</i>
 * @version 	%1% %2%
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
class BinarySearchTreeNode
{
    // package access members
    BinarySearchTreeNode left;  // left node
    Player data;    // data item
    BinarySearchTreeNode right; // right node
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor     BinarySearchTreeNode()-- constructor
     * Description     Initialize data to d and make this a leaf node.
     * @param          player Player
     * @author         <i>Niko Culevski</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode(Player player)
    {
        data = player;
        left = right = null; // this node has no children
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method       Insert()
     * Description  Recursive method to insert an Player into a Tree that
     *              contains nodes
     * @param       player Player
     * @author      <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public synchronized void insert(Player player)
    {
        if (player.compareTo(data) < 0)
        {
            if (left == null)
                left = new BinarySearchTreeNode(player);
            else
                left.insert(player);
        }
        else
            if (player.compareTo(data) >= 0)
            {
                if (right == null)
                    right = new BinarySearchTreeNode(player);
                else
                    right.insert(player);
            }
    }
}