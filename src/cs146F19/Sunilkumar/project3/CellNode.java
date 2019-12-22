package cs146F19.Sunilkumar.project3;
import java.util.*;

public class CellNode {
	public int color;
	public int distance;
	public CellNode parent;
	public int val;
	public int visited;
	public CellNode next;
	public ArrayList<CellNode> neighbors;
	public LinkedList<CellNode> connections;
	
	public CellNode(int val) {
		this.val = val;
		this.next = null;
		this.visited = -1;
		this.neighbors = new ArrayList<CellNode>();
		this.connections = new LinkedList<CellNode>();
	}
}