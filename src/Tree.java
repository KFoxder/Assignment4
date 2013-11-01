/**
 * A basic generic tree
 * <p>
 * This implements a basic generic tree, with labels of type T,
 * pointer to the parent node, and a singly linked list of children nodes.
 * It provides iterators that loop over children, that loop up to the root,
 * and that traverse the tree in prefix and postfix order.
 * ***********************************************************************<br>
 * Computer Science 102: Data Structures<br>
 * New York University, Fall 2013,<br>
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne<br>
 * ***********************************************************************
 *
 * @author      Ernie Davis <davise@cs.nyu.edu> and Eric Koskinen <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-01-01
 */

import java.io.File;

public class Tree<T extends TreeNode> {
	private T label;
	private Tree<T> parent;
	/** 
	 * next node on the list of parent's children
	 */
	private Tree<T> nextSibling; 
	/**
	 * first in the linked list of children
	 */
	private Tree<T> firstChild;

	public Tree(T l) {
		label = l; parent = null; nextSibling = null; firstChild = null; 
	}
	public Tree() {
		label = null; parent = null; nextSibling = null; firstChild = null; 
	}

	/**
	 * getters and setters
	 */
	public T getLabel() { return label; }  
	public void setLabel(T v) { label = v; }
	public Tree<T> getParent() { return parent;}
	public Tree<T> getNextSibling() { return nextSibling;}
	public Tree<T> getFirstChild() { return firstChild;}

	/**
	 * findChild(): searches through the *immediate* children of the tree
	 * to see if there is a subtree N whose label is equal to {@link otherLabel}.
	 *
	 * @param otherLabel      The label to search for within the current Tree's
	 *                        children. Must not be a null value.
	 * @return 				  Returns a Tree<T> if it finds a child that has the 
	 * 						  same label as {@link otherLabel}. Returns null if it doesn't
	 * 						  find a child with the same label as {@link otherLabel}.
	 */
	public Tree<T> findChild(T otherLabel) {

		//check if input is null
		if(otherLabel==null){
			return null;
		}else{
			//Iterate through children by looking at the firstChild and then nextSiblings of the first child
			Tree<T> temp = this.firstChild;
			while(temp!=null){
				if(temp.label.equals(otherLabel)){
					return temp;
				}else{
					temp = temp.nextSibling;
				}
			}
			return null;
		}
	}

	/**
	 * Add C to the front of the children of this
	 */
	public void addChild(Tree<T> c) {
		c.parent = this;
		if (firstChild == null) 
			firstChild = c;
		else {
			c.nextSibling = firstChild;
			firstChild = c;
		}
	}
	/**
	 * Check if the node is a leaf
	 */
	public boolean Leaf() { return firstChild==null; }

	/**
	 * Convert the tree into a string. The structure is indicated by
	 * square brackets. Uses a preorder listing.
	 */
	public String toString() {
		String S = "[ " + label.toString();
		Tree<T> N = firstChild;
		while (N != null) {
			S = S + " " + N.toString();
			N = N.nextSibling;
		}
		return S+" ]";
	}

	/**
	 * displayXML: displays the tree in XML format. 
	 */
	public void displayXML() {

		//Check if it is root and print opening XML tag
		if(this.parent==null) System.out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");

		//Create Indent by checking to see how many parent it has and increasing the Indent 
		int Indent = 0;
		Tree<T> parentNode = this.parent;
		while(parentNode!=null){
			Indent+=3;
			parentNode = parentNode.parent;
		}

		//Print the preString() 
		for (int I = 0; I < Indent; I++) System.out.print(" ");
		System.out.println(this.label.preString());
		Tree<T> node = this.firstChild;
		
		//Recursively call .displayXML() on all children of the node
		while(node!=null){
			node.displayXML();			
			node = node.nextSibling;
		}

		//Print the postString()
		for (int I = 0; I < Indent; I++) System.out.print(" ");
		System.out.println(this.label.postString());


	}


}
