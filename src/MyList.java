/**
 * Generic List Class for {@link QueueList}
 * <p>
 * Provides insertion/removal from the front/back of the list
 * Note: DO NOT EDIT THIS FILE.
 * ***********************************************************************
 * Computer Science 102: Data Structures
 * New York University, Fall 2013,
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
 * ***********************************************************************
 *
 * @author      Eric Koskinen       <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-09-01
 */

public class MyList<T> implements Collection<T> {
    protected MyListNode<T> firstNode = null;

    public MyList(MyListNode<T> n) { firstNode = n ; }
    public MyList() { firstNode = null; }

    public int size() {
	if (firstNode == null) return 0;
	else return firstNode.size();
    }
    public boolean isEmpty() {
	return ( firstNode == null ? true : false );
    }

    /**
     * insertFront(): simple. Link in the new element
     */
    public boolean insertFront(T el) {
	firstNode = new MyListNode<T>(el,firstNode);
	return true;
    }

    /**
     * insertBack(): if the list is empty, insertFront() will do.
     * Otherwise, need to make the recursive call to MyListNode
     */
    public boolean insertBack(T e) {
	if (firstNode == null) { return insertFront(e); }
	else { return firstNode.insertBack(e); }
    }

    /**
     * removeFront(): if the list is empty, throw exception.
     * Otherwise, remove the front element
     */
    public T removeFront() throws InvalidOperationException {
	if (firstNode == null)
	    throw new InvalidOperationException("removeFront: empty");

	T retVal = firstNode.value;
	firstNode = firstNode.next;
	return retVal;
    }

    /**
     * removeBack()
     */
    public T removeBack() throws InvalidOperationException {
	// if list is empty, throw exception
	if (firstNode == null)
	    throw new InvalidOperationException("removeBack: empty");

	T retVal;
	// if only one element, remove it and return it
	if (firstNode.next == null)  {
	    retVal = firstNode.value;
	    firstNode = firstNode.next;

	// otherwise, need to make the recursive call
	} else {
	    retVal = firstNode.removeBack(); 
	}
	// System.out.println("removed: "+retVal.toString());
	return retVal;
    }

    /**
     * Output operations
     */
    public String toString() {
	if(firstNode == null) {
	    return "(empty)";
	} else {
	    return "[" + firstNode.toString() + "]";
	}
    }
    public void PrettyPrint() {
	System.out.println(this.toString());
    }

}