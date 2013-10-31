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

		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		// Implement this method. Hint: You will need to use
		// recursion, at each step dequeuing the next path and then
		// searching to see if the directory already exists.
		// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


		while(!filePathQueue.isEmpty()){
			String name;
			try {
				// Get the name of the top in the queue
				name = filePathQueue.dequeue();
				FileNode temp = new FileNode(name,true);

				//if its empty we change it to a file
				if(filePathQueue.isEmpty()){
					temp.isDir = false;
				}

				Tree<FileNode> tempNode = new Tree<FileNode>(temp);
				Tree<FileNode> nodeFound = t.findChild(temp);
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