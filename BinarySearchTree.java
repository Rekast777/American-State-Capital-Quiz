package Capitals;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *<pre>
 * Class        BinarySearchTree.java
 * Project      Binary Search Trees
 * Description  A definition for Tree class with multitude of methods for 
 *              operations on trees     
 * Platform     jdk 1.8.0_241; NetBeans IDE 11.3; Windows 10
 * @author	<i>Niko Culevski</i>
 * @version 	%1% %2%
 *</pre>
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class BinarySearchTree
{
    private BinarySearchTreeNode root;
    StringBuilder buf = new StringBuilder("");
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Constructor     BinarySearchTree()-- deafault constructor
     * Description     Construct a null Tree of Players.
     * @author         <i>Niko Culevski</i>
     *</pre>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTree()
    {
        root = null;
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           InsertNode()
     * Description      Insert a new node in the binary search tree. If the root 
     *                  node is null, create the root node here. Otherwise, call
     *                  the insert method from the from the class TreeNode
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void insertNode(Player player)
    {
        if (root == null)
            root = new BinarySearchTreeNode(player);
        else
            root.insert(player);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           remove()
     * Description      A method to remove a node with given name only. Calls
     *                  overloaded remove method withplayer name and the root
     *                  of the Tree
     * @author          <i>Niko Culevski</i>
     * @param           player Player to be removed
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void remove(Player player)
    {
	root = remove(player, root);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           remove()
     * Description      An overloaded recursive method to remove a node with 
     *                  given player and node. 
     * @author          <i>Niko Culevski</i>
     * @param           player Playerrtist to be removed
     * @param           node TreeNode
     * @return          node TreeNode--the new root
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode remove(Player player, BinarySearchTreeNode node)
    {
	if( node == null )
	    return node;                        // Item not found; do nothing
	if(player.compareTo(node.data) < 0)
	    node.left = remove(player, node.left);
	else if(player.compareTo(node.data) > 0)
	    node.right = remove(player, node.right);
	else if(node.left != null && node.right != null) // Two children
        {
	    node.data = findMin(node.right).data;
	    node.right = remove(node.data, node.right);
	}
	else    //Case 1 & case 2: remove leaf node & single child
	    node = (node.left != null) ? node.left : node.right;
	return node;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getRoot()
     * Description  Getter method to return the root of the tree
     * @author      <i>Niko Culevski</i>
     * @return      root TreeNode
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/ 
    public BinarySearchTreeNode getRoot()
    {
        return root;
    }
    
    public void saveTree(BinarySearchTreeNode node, String file){
//        try{
//            FileWriter filePointer = new FileWriter(file, false);
//            PrintWriter writeFile = new PrintWriter(filePointer);
//            if(node != null){
//                saveBuffer.append(node.data.toString() + '\n');
//                writeFile.write(saveBuffer.toString());
//                printTree(node.left);
//                printTree(node.right);
//            }
//            writeFile.close();
//            
//        }
//        catch(IOException exp){
//            exp.printStackTrace();
//        } 
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       buildBuffer()
     * Description  Build a StringBuilder that contains all data in preorder
     *              tranversal.
     * @param       node BinarySearchTree
     * @author      <i>Niko Culevski</i>
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public void buildBuffer (BinarySearchTreeNode node){
        if (node != null){
            buf.append(node.data.toString() + '\n');
            buildBuffer(node.left);
            buildBuffer(node.right);
        }
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       getBuffer()
     * Description  Return buffer
     * @author      <i>Niko Culevski</i>
     
     * @return      buf String
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public String getBuffer(){
        return buf.toString();
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *<pre>
     * Method       setBuffer()
     * Description  Set buffer
     * @author      <i>Niko Culevski</i>
     * @param       buf stringBuilder
     *</pre>
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/     
    public void setBuffer(StringBuilder buf){
        this.buf = buf;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           removeAll()
     * Description      A method to remove all nodes of the Tree
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void removeAll()
    {
        root = null;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           findMin()
     * Description      A method to find and return the player with the minimum
     *                  value as specified by the Player compareTo method. 
     *                  Calls an overloaded method to start the search from the 
     *                  root node that is passed to it.
     * @author          <i>Niko Culevski</i>
     * @return          player Player--with minimum name
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player findMin()
    {
        return findMin(root).data;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           findMin()
     * Description      A recursive overloaded method to find the node with the 
     *                  player containing the minimum value as specified by 
     *                  the Player compareTo method. 
     * @author          <i>Niko Culevski</i>
     * @param           node BinarySearchTreeNode--recursive node to search for minimum
     * @return          node BinarySearchTreeNode--node containing the smallest item
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode findMin(BinarySearchTreeNode node)
    {
	if(node == null)
	    return null;
	else if(node.left == null)
	    return node;
	return findMin(node.left);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           findMax()
     * Description      A method to find and return the player with the maximum
     *                  value as specified by the Player compareTo method. 
     *                  Calls an overloaded method to start the search from the 
     *                  root node that is passed to it.
     * @author          <i>Niko Culevski</i>
     * @return          player Player--with minimum name
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player findMax()
    {
        return findMax(root).data;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           findMax()
     * Description      A recursive overloaded method to find the node with the 
     *                  player containing the maximum value as specified by 
     *                  the Player compareTo method. 
     * @author          <i>Niko Culevski</i>
     * @param           node BinarySearchTreeNode--recursive node to search for minimum
     * @return          node BinarySearchTreeNode--node containing the largest item.
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode findMax(BinarySearchTreeNode node)
    {
	if( node != null )
        {
	    while( node.right != null )
		node = node.right;
        }	
	return node;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           elementAt()
     * Description      A method to return the player (data) given the node. 
     * @author          <i>Niko Culevski</i>
     * @param           node BinarySearchTreeNode
     * @return          player Player--the element field or null.
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player elementAt(BinarySearchTreeNode node)
    {
	return (node == null ? null : node.data);
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           contains()
     * Description      A boolean method to determine if a player is in the
     *                  BST 
     * @author          <i>Niko Culevski</i>
     * @param           player Player
     * @return          true if Player is in tree; false otherwise
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/    
    public boolean contains (Player player){
        if(nodeWith(player, root) == null)
            return false;
        else{
            BinarySearchTreeNode foundPlayer = nodeWith(player, root);
            return (foundPlayer.data).equals(player);
        }
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           nodeWith()
     * Description      A method to return the player (data) given the node. 
     * @author          <i>Niko Culevski</i>   
     * @param           data Player
     * @param           node BinarySearchTreeNode
     * @return          node TreeNode--the node with specified player or null
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private BinarySearchTreeNode nodeWith( Player data, BinarySearchTreeNode node)
    {
        if (node == null)
            return null;
        else
        {
            if(data.compareTo(node.data) == 0)
                return node;
            else
                if (data.compareTo(node.data) < 0)
                    return nodeWith(data, node.left);
                else
                    return nodeWith(data, node.right);                            
        }            
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           nodeWith()
     * Description      An overloaded method to return the node given the 
     *                  player name as a String
     * @author          <i>Niko Culevski</i>   
     * @param           playerName String
     * @param           node BinarySearchTreeNode
     * @return          node BinarySearchTreeNode--the node with specified player's name or null
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode nodeWith(String playerName, BinarySearchTreeNode node)
    {
        if (node == null)
            return null;
        else
        {
            if(playerName.compareTo(node.data.getName()) == 0)
                return node;
            else
                if (playerName.compareTo(node.data.getName()) < 0)
                    return nodeWith(playerName, node.left);
                else
                    return nodeWith(playerName, node.right);                            
        }            
    }
        
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           next()
     * Description      An unfinished method to return the next player according
     *                  to the sorting of the BST given a player. 
     * @author          <i>Niko Culevski</i>
     * @param           player Player
     * @return          player Player--the next player.
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player next(Player player)
    {
        BinarySearchTreeNode nextNode = nodeWith(player, root);
        if( nextNode.left != null && nextNode.right != null ) // Two children
            return findMin(nextNode.right).data;
        else
            return null;   //not so--needs work!! UNFINISHED
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           previous()
     * Description      An unfinished method to return the next player according
     *                  to the sorting of the BST given an player. 
     * @author          <i>Niko Culevski</i>
     * @param           player Player
     * @return          player Player--the next player.
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Player previous(Player player)
    {
        BinarySearchTreeNode nextNode = nodeWith(player, root);
        if( nextNode.left != null && nextNode.right != null ) // Two children
            return findMax(nextNode.left).data;
        else
            return null; //not so--needs work!! UNFINISHED
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           preorderTraversal()
     * Description      Display data of nodes in preorder: Node, Left, Right, Calls
     *                  recursive preorderHelper method to do the real display.
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void preorderTraversal()
    {
        preorderHelper( root );
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           preorderHelper()
     * Description      Display data of nodes id preorder: Node, Left, Right
     * @author          <i>Niko Culevski</i>
     * @param           node BinarySearchTreeNode
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void preorderHelper( BinarySearchTreeNode node )
    {
        if ( node == null )
            return;
        System.out.print( node.data + " " );
        preorderHelper( node.left );
        preorderHelper( node.right );
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           inorderTraversal()
     * Description      Display data of nodes in preorder: Left, Node, Right. Calls
     *                  recursive inorderHelper method to do the real display
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void inorderTraversal()
    {
        inorderHelper( root );
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           inorderHelper()
     * Description      Display data of nodes in preoredr: left, Node, Right.
     * @author          <i>Niko Culevski</i>
     * @param           node BinarySearchTreeNode
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void inorderHelper( BinarySearchTreeNode node )
    {
        if ( node == null )
            return;
        inorderHelper( node.left );
        System.out.print( node.data + " " );
        inorderHelper( node.right );
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           postorderTraversal()
     * Description      Display data of nodes in preorder: Left, Right, Node. Calls
     *                  recursive postorderHelper method to do the real display
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void postorderTraversal()
    {
        postorderHelper( root );
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           postorderHelper()
     * Description      Display data of nodes in preorder: left, Right, Node.
     * @author          <i>Niko Culevski</i>
     * @param           node BinarySearchTreeNode
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void postorderHelper( BinarySearchTreeNode node )
    {
        if ( node == null )
            return;
        postorderHelper( node.left );
        postorderHelper( node.right );
        System.out.print( node.data + " " );
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           toString()
     * Description      Outputs the tree contents in sorted order as a string.
     *                  It calls the printTree to print the tree inorder.
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString( )
    {
	if( isEmpty() )
	    return("");
	else
	    return(printTree( root ));
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           printTree()
     * Description      Outputs the tree contents in sorted order via inorder.
     * @parem           node BinarySearchTreeNode--the node that roots the tree
     * @return          tree String
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public String printTree(BinarySearchTreeNode node)
    {
	
	if(node != null)
        {
	    printTree(node.left);
	    buf.append(node.data + ",");
	    printTree(node.right);
	}
	return buf.toString();
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           isEmpty()
     * Description      Test if the tree is logically empty.
     * @return          true if empty, false otherwise
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean isEmpty()
    {
	return root == null;
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           size()
     * Description      Find number of nodes in a tree. Calls sizeHelper with 
     *                  the root of the tree passed to it
     * @return          number of nodes in a tree
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int size()
    {	
        return sizeHelper(root);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           sizeHelper()
     * Description      Recursive method to find number of nodes in a tree.
     * @param           node BinarySearchTreeNode
     * @return          number of nodes in the tree
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int sizeHelper (BinarySearchTreeNode node)
    {
	if (node == null)
	    return 0;
	else
	    return 1 + sizeHelper(node.left) + sizeHelper(node.right);
    }  

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           countLeaves()
     * Description      Find number of leaves in a tree. Calls countLeavesHelper  
     *                  with the root of the tree passed to it
     * @return          number of leaves in a tree to which node points
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int countLeaves() 
    {
        return countLeavesHelper(root);
    }
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           countLeavesHelper()
     * Description      Recursive method to find number of leaves in a tree.
     * @param           node BinarySearchTreeNode
     * @return          number of leaves in the tree
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int countLeavesHelper(BinarySearchTreeNode node)
    {
        if (node == null)
           return 0;
        else if (node.left == null && node.right == null)
            return 1;  // Node is a leaf.
        else
            return countLeavesHelper(node.left) + countLeavesHelper(node.right);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           height()
     * Description      Find height of a tree. Calls heighthHelper with the root 
     *                  of the tree passed to it.The height of a node is the 
     *                  number of edges on the longest path from the  node to a 
     *                  leaf. A leaf node will have a height of 0
     * @return          height of a node int
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int height() 
    {
        return heighthHelper(root);        
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           heighthHelper()
     * Description      Recursive method to find the height of a node.
     * @param           node BinarySearchTreeNode
     * @return          height of a node int
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int heighthHelper(BinarySearchTreeNode node)
    {
        if(node == null)
            return 0;
        else
        {
            return 1 + Math.max(heighthHelper(node.left),
                    heighthHelper(node.right));
        }
//        int left = heighthHelper(node.left);
//        int right = heighthHelper(node.right);
//
//        int x = (left > right) ? left + 1 : right + 1;
//        return x;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           findParent()
     * Description      Find height of a tree. Calls overloaded findParent with
     *                  player, root and parent (null)
     * @param           player Player
     * @return          parent node of player BinarySearchTreeNode
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findParent(Player player) 
    {
        return findParent(player, root, null);
    }

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           findParent()
     * Description      Recursive method to find the parent of a player.
     * @param           player Player
     * @param           node BinarySearchTreeNode
     * @param           parent BinartSearchTreeNode
     * @return          parent of a node or null--TreeNode
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findParent(Player player, BinarySearchTreeNode node, BinarySearchTreeNode parent) 
    {
        if (node == null) 
        {
            return null;
        } 
        else if (node.data != player) 
        {
            parent = findParent(player, node.left, node);
            if (parent == null) 
            {
                parent = findParent(player, node.right, node);
            }
        }
        return parent;
    }
        
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           findSuccessor()
     * Description      Recursive method to find the successor of an player 
     *                  given the data=Player
     * @param           data Player
     * @return          successor of an player or null--BinarySearchTreeNode
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findSuccessor(Player data) 
    {
        return findSuccessor(nodeWith(data,root));
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           findSuccessor()
     * Description      An overloaded recursive method to find the successor 
     *                  of an player given a node
     * @param           node BinarySearchTreeNode
     * @return          successor of an player or null--BinarySearchTreeNode
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findSuccessor(BinarySearchTreeNode node)
    {
	if (node == null)
		return null;
	
	if (node.right != null)
		return findMin(node.right);
	
	BinarySearchTreeNode y = findParent(node.data);
	BinarySearchTreeNode x = node;
	while (y != null && x == y.right)
	{
		x = y;
		y = findParent(y.data);
	}
	// Intuition: as we traverse left up the tree we traverse smaller values
	// The first node on the right is the next larger value
	return y;
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           findPredecessor()
     * Description      Recursive method to find the predecessor of an player 
     *                  given the data=Player
     * @param           data Player
     * @return          successor of an player or null--BinarySearchTreeNode
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findPredecessor(Player data) 
    {
        return findPredecessor(nodeWith(data,root));
    }
    
    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * <pre>
     * Method           findPredecessor()
     * Description      An overloaded recursive method to find the predecessor 
     *                  of a player given a node
     * @param           node BinarySearchTreeNode
     * @return          predecessor of a player or null--BinarySearchTreeNode
     * @author          <i>Niko Culevski</i>
     *</pre>   
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BinarySearchTreeNode findPredecessor(BinarySearchTreeNode node)
    {
	if (node == null)
            return null;
	
	if (node.left!= null)
            return findMax(node.left);
			
	BinarySearchTreeNode parent = findParent(node.data);

	BinarySearchTreeNode y = parent;
	BinarySearchTreeNode x = node;
	while (y != null && x == y.left)
	{
            x = y;
            y = findParent(y.data);
	}	
	return y;
    }    
}