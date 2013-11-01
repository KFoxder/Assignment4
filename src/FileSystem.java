/**
 * A filesystem implemented as a tree of {@link FileNode}s.
 * <p>
 * ***********************************************************************
 * Computer Science 102: Data Structures
 * New York University, Fall 2013,
 * Lecturers: Eric Koskinen and Daniel Schwartz-Narbonne
 * ***********************************************************************
 *
 * @author      Eric Koskinen       <ejk@cs.nyu.edu>
 * @version     $Revision$
 * @since       2013-10-30
 */
import java.util.HashMap;
import java.io.File;

public class FileSystem {
	/**
	 * The {@link FileSystem} is implemented via a {@link Tree} of {@link FileNode}s
	 */
	Tree<FileNode> root = null;
	public Tree<FileNode> getTree() { return root; }

	/**
	 * insertPath(): Inserts the file given by filePathQueue into tree t.
	 *
	 * @param t               The current tree node into which the file is
	 *                        being inserted
	 * @param filePathQueue   A queue of strings, representing the
	 *                        relative path of the file from the current working
	 *                        directory (CWD). For example, the file
	 *                        "foo/bar/myFile" would be represented in 
	 *                        the queue as: 
	 *                        IN->["myFile","bar","foo","CWD"]->OUT
	 */
	public void insertPath(Tree<FileNode> t, QueueList<String> filePathQueue) {

		while(!filePathQueue.isEmpty()){
			String name;
			try {
				
				// Dequeue the name off the queue  and create a FileNode
				name = filePathQueue.dequeue();
				FileNode temp = new FileNode(name,true);

				//if the queue is empty we know its a file and thus change the FileNode.isDir to false
				if(filePathQueue.isEmpty()){
					temp.isDir = false;
				}

				//Create a Tree node with the FileNode
				Tree<FileNode> tempNode = new Tree<FileNode>(temp);
				
				//Search the current tree node to see if the dir/file exists
				Tree<FileNode> nodeFound = t.findChild(temp);
				
				//If the current tree node doesn't have the file/dir we add it as a child and recurse
				//otherwise we use the node that is found and continue with the recursion.
				if(nodeFound==null){
					t.addChild(tempNode);
					insertPath(tempNode,filePathQueue);
				}else{
					insertPath(nodeFound,filePathQueue);
				}



			} catch (InvalidOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

	}
	/**
	 * Calls displayXML() on {@link root}. 
	 */

	public void display() {
		root.displayXML();
	}

	/**
	 * Constructor. You do not need to edit or understand this method.
	 *
	 * @param dirName The name of the directory to examine.
	 */
	public FileSystem(String dirName) throws Exception {
		Loader L = new Loader();
		try {
			root = new Tree<FileNode>(new FileNode("CWD",true));
			HashMap<String,File> hm = L.load(dirName);
			for (String k : hm.keySet()) {
				String[] path = k.split("[\\/]");
				if(false) System.out.println("path: "+k);
				QueueList<String> q = new QueueList<String>();
				for (String p : path) { q.enqueue(p.equals("")?"CWD":p); }
				q.dequeue();
				insertPath(root,q);
			}
		} catch (Exception e) {
			System.out.println("Error loading directory "+dirName);
			throw e;
		}
	}
}