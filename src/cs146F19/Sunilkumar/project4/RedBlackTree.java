package cs146F19.Sunilkumar.project4;

public class RedBlackTree<Key extends Comparable<Key>> {	
		static RedBlackTree.Node<String> root;
		static final int RED = 0; //new fix
		static final int BLACK = 1; //new fix
		
		public RedBlackTree() {
			root = null;
		}
		
		public static class Node<Key extends Comparable<Key>> { //changed to static 
			
			  Key key;  		  
			  Node<String> parent;
			  Node<String> leftChild;
			  Node<String> rightChild;
			  static final int RED = 0; //new fix
			  static final int BLACK = 1; //new fix
			  int color;
			  
			  public Node(Key data){
				  this.key = data;
				  leftChild = null;
				  rightChild = null;
			  }		
			  
			  public int compareTo(Node<Key> n){ 	//this < that  <0
			 		return key.compareTo(n.key);  	//this > that  >0
			  }
			  
			  public boolean isLeaf(){
				  if (this.equals(root) && this.leftChild == null && this.rightChild == null) return true;
				  if (this.equals(root)) return false;
				  if (this.leftChild == null && this.rightChild == null){
					  return true;
				  }
				  return false;
			  }
		}

		 public boolean isLeaf(RedBlackTree.Node<String> n){
			  if (n.equals(root) && n.leftChild == null && n.rightChild == null) return true;
			  if (n.equals(root)) return false;
			  if (n.leftChild == null && n.rightChild == null){
				  return true;
			  }
			  return false;
		  }
		
		public interface Visitor<Key extends Comparable<Key>> {
			/**
			This method is called at each node.
			@param n the visited node
			*/
			void visit(Node<Key> n);  
		}
		
		public void visit(Node<Key> n){
			System.out.println(n.key);
		}
		
		public void printTree(){  //preorder: visit, go left, go right
			RedBlackTree.Node<String> currentNode = root;	
			printTree(currentNode);
		}
		
		public static void printTree(RedBlackTree.Node<String> node){
			if(node==null)
				return;
			System.out.print(node.key);
			if (node.isLeaf()){
				return;
			}
			printTree(node.leftChild);
			printTree(node.rightChild);
		}
		
		// place a new node in the RB tree with data the parameter and color it red. 
		public void addNode(String data){  	//this < that  <0.  this > that  >0
			
			Node n = new Node(data);
			// If n is the root, color it black and exit
			if (root == null){ 
				root = n;
				root.color = BLACK;
				root.parent = null;
				return;
			}
			Node temp = root;
			while(temp != null || !temp.isLeaf()){
				//if n.key should come before the key of temp, go left
				if (n.compareTo(temp) < 0){
					if (temp.leftChild == null){ //found the position to add
						temp.leftChild = n;
						n.parent = temp;
						n.color = RED;
						fixTree(n);
						return;
					} else { //continue searching
						temp = temp.leftChild;
					}
				} else if (n.compareTo(temp) > 0){
					//if n.key should come after the key of temp, go right
					if (temp.rightChild == null){ //found the position to add
						temp.rightChild = n;
						n.parent = temp;
						n.color = RED;
						fixTree(n);
						return;
					} else { //continue searching
						temp = temp.rightChild;
					}
				} else {
					return;
				}
			} 
		}	

		public void insert(String data){
			addNode(data);	
		}
		
		//returns the Node containing the given String as the key, and null if it doesnt exist
		public RedBlackTree.Node<String> lookup(String k){ 
			Node temp = root;
			while(temp!=null) {
				//if the node is found, return it
				if(temp.key.equals(k)) {
					return temp;
				} else { //continue searching 
					if(temp.key.compareTo(k)<0) //go right is k comes after temp.key
						temp = temp.rightChild;
					else //else, go left
						temp = temp.leftChild;
				}
			}
			//if the node was not found, return null
			return null;
		}
	 	
		//returns the sibling node of the given node
		public RedBlackTree.Node<String> getSibling(RedBlackTree.Node<String> n){  
			if(root==n)
				return null;
			Node parent = n.parent;
			//if the node is a leftchild of the parent, return rightchild
			if(isLeftChild(parent, n)) {
				return parent.rightChild;
			} else { //else, return leftchild
				return parent.leftChild;
			}
		}
		
		//returns the Aunt node of the given Node
		public RedBlackTree.Node<String> getAunt(RedBlackTree.Node<String> n){
			if(root==n)
				return null;
			Node grandParent = getGrandparent(n);
			Node parent = n.parent;
			//if parent is a leftchild, return rightchild of grandparent
			if(isLeftChild(grandParent, parent)) {
				return grandParent.rightChild;
			} else { //else, return leftchild of grandparent
				return grandParent.leftChild;
			}
		}
		
		public RedBlackTree.Node<String> getGrandparent(RedBlackTree.Node<String> n){
			return n.parent.parent;
		}
		
		//rotates the rbt to the left at the given node
		public void rotateLeft(RedBlackTree.Node<String> n){
			//followed textbook algorithm
			Node y = n.rightChild;
			//if the node has no right child, set it equal to the root
			if(y==null) {
				root = n;
				root.rightChild = null;
				return;
			}
			n.rightChild = y.leftChild;
			if(y.leftChild!=null) {
				y.leftChild.parent = n;
			}
			y.parent = n.parent;
			if(n.parent==null) {
				root = y;
			} else if(n==n.parent.leftChild) {
					n.parent.leftChild = y;
			} else {
				n.parent.rightChild = y;
			}
			y.leftChild = n;
			n.parent = y;
		}
		
		//rotates the rbt to the right at the given node
		public void rotateRight(RedBlackTree.Node<String> n){
			//followed textbook algorithm
			Node y = n.leftChild;
			n.leftChild = y.rightChild;
			if(y.rightChild!=null) {
				y.rightChild.parent = n;
			}
			y.parent = n.parent;
			if(n.parent==null) {
				root = y;
			} else if(n==n.parent.rightChild) {
				n.parent.rightChild = y;
			} else {
				n.parent.leftChild = y;
			}
			y.rightChild = n;
			n.parent = y;
		}
		
		//fixes the tree to restore the properties of a reb black tree
		public void fixTree(RedBlackTree.Node<String> current) {
			//if current is root, make it black and return
			if(current==root) {
				root.color = BLACK;
				return;
			}
			//if parent is black, it is already a red black tree			
			if(current.parent.color==BLACK)
				return;
			
			//if the current node is red and its parent is red, tree is unbalanced
			if (current.color == RED && current.parent.color == RED){ 
				Node parent = current.parent;
				Node grandparent = getGrandparent(current);
				
				//if aunt is empty or black
				if (getAunt(current) == null || getAunt(current).color == BLACK){ 
					
					//if parent is left child of grandparent
					if (isLeftChild(grandparent, parent)){ 
						if (isLeftChild(parent,current)){ //case 1: current is leftchild
							parent.color = BLACK;
							grandparent.color = RED;
							rotateRight(grandparent);
							return;
						} else { //case 2: current is rightchild
							rotateLeft(parent);
							fixTree(parent);
						}
					} else { //if parent is right child of grandparent
						if (isLeftChild(parent,current)){ //case 1: current is leftchild of parent
							rotateRight(parent);
							fixTree(parent);
						} else {						 //case2: current is rightchild of parent
							parent.color = BLACK;
							grandparent.color = RED;
							rotateLeft(grandparent);
							return;
						}
					}
				} else if (getAunt(current).color == RED) { //final case, when aunt is red
					parent.color = BLACK;
					getAunt(current).color = BLACK;
					grandparent.color = RED;
					fixTree(grandparent);
				}
			}
		}
		
		public boolean isEmpty(RedBlackTree.Node<String> n){
			if (n.key == null){
				return true;
			}
			return false;
		}
		 
		public boolean isLeftChild(RedBlackTree.Node<String> parent, RedBlackTree.Node<String> child)
		{
			if (child.compareTo(parent) < 0 ) {//child is less than parent
				return true;
			}
			return false;
		}

		public void preOrderVisit(Visitor<String> v) {
		   	preOrderVisit(root, v);
		}
		 
		 
		private static void preOrderVisit(RedBlackTree.Node<String> n, Visitor<String> v) {
		  	if (n == null) {
		  		return;
		  	}
		  	v.visit(n);
		  	preOrderVisit(n.leftChild, v);
		  	preOrderVisit(n.rightChild, v);
		}
	}

