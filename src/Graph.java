
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	
	

	static final int sizeOfHashtable = 5;
	
	private MinimumSpanningTree mst;
	private int V;
	private int E;
	private LinkedList<node> hashtable[];
	private ArrayList<edge> edges;
	private double d;

	// constructor
	Graph(double d) {
		this.d = d;
		this.V=0;
		this.E=0;

		// define the size of array as
		// number of vertices
		this.hashtable = new LinkedList[sizeOfHashtable];

		// Create a new list for each vertex
		// such that adjacent nodes can be stored
		for (int i = 0; i < sizeOfHashtable; i++) {
			hashtable[i] = new LinkedList<>();
		}
		this.edges = new ArrayList<edge>();
		this.mst=new MinimumSpanningTree(); ;
	}

	private static int hashFunction(node n) {
		int temp = n.getId() % sizeOfHashtable;
		return temp;
	}
	
	public MinimumSpanningTree getMst() {
		return mst;
	}

	public void setMst(MinimumSpanningTree mst) {
		this.mst = mst;
	}

	
	
	public void findNeighbors(node n) {
		// perno pu oula ta v gia to node ke vrisko kataposo ine gitones
		// ean ine enimerono ke ta 2 to neightors
		for (int i = 0; i < this.hashtable.length; i++) {
			LinkedList<node> list = this.hashtable[i];
			if (list.isEmpty() == true) {
				continue;
			}
			for (int j = 0; j < list.size(); j++) {
				node temp = (node) list.get(j); // etsi exo to kathe v
				double weight = n.isNeighborPlusD(temp, d);
				if (weight != -1) {
					neighbour temp2=new neighbour(temp,weight);
					n.neighbours.add(temp2);
					temp2=new neighbour(n,weight);
					temp.neighbours.add(temp2);
					addEdge(n, temp, weight);
				}
			}
		}
	}
	
	public LinkedList<node>[] getHashtable() {
		return hashtable;
	}

	public LinkedList<node> getHashtable(int index) {
		return hashtable[index];
	}

	public void setHashtable(LinkedList<node>[] hashtable) {
		this.hashtable = hashtable;
	}

	public ArrayList<edge> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<edge> edges) {
		this.edges = edges;
	}

	public double getD() {
		return d;
	}

	
	public void setD(double d) {
		this.d = d;
	}

	public static int getSizeofhashtable() {
		return sizeOfHashtable;
	}

	public void setV(int v) {
		V = v;
	}

	public void setE(int e) {
		E = e;
	}

	public node addNode(int x, int y, int id, double t) {
		node temp = new node(x, y, id, t);
		this.findNeighbors(temp);
		int index = hashFunction(temp);
		this.hashtable[index].add(temp);
		this.V++;
		this.insertionSort();
		return temp;
	}

  
    
	public node addNode(node temp) {
		this.findNeighbors(temp);
		int index = hashFunction(temp);
		this.hashtable[index].add(temp);
		this.V++;
		return temp;
	}
	
	
	public void addNodeAdvance(int x, int y, int id, double t)
	{
		node temp=addNode(x,y,id,t);
		this.mst.addNodeInMST(this,temp);
	}
	
	public void removeNodeAdvance(int id)
	{
		node toBeRemoved= search(id);
		this.mst.removeNodeInMST(this, toBeRemoved);
		this.removeNode(toBeRemoved);
	}
	
	


	public node search(int id) {
		for (int i = 0; i < this.hashtable.length; i++) {
			LinkedList<node> list = this.hashtable[i];
			if (list.isEmpty() == true) {
				continue;
			}
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).getId() == id)
					return list.get(j);
			}
		}
		return null;
	}

	
	/* check if i missed to remove something   */
	public void removeNode(int id) {
		for(int i=0; i<this.getE(); i++)
		{
			node temp = null;
			if(this.edges.get(i).getN1().getId()==id)
			{
				temp=this.edges.get(i).getN2();
			}
			else if(this.edges.get(i).getN2().getId()==id)
			{
				temp=this.edges.get(i).getN1();
			}
			if(temp==null)
			{
				continue;
			}
			this.edges.remove(i);
			temp.removeNeighbour(temp);
		}
		
	}

	public void removeNode(node n) {
		for(int i=0; i<this.getE(); i++)
		{
			node temp = null;
			if(this.edges.get(i).getN1().equals(n))
			{
				temp=this.edges.get(i).getN2();
			}
			else if(this.edges.get(i).getN2().equals(n))
			{
				temp=this.edges.get(i).getN1();
			}
			if(temp==null)
			{
				continue;
			}
			this.edges.remove(i);
			temp.removeNeighbour(temp);
		}
	}
//	List<edge> l = convertALtoLL(this.edges); experiment en na t akamo avrio!
	
//	for (edge tempEdge : l) {
//		if(tempEdge.getWeight()>=temp.w)
//	}
	// Adds an edge to an undirected graph
	void addEdge(node node1, node node2, double weight) {
		edge temp = new edge(node1, node2, weight);
		this.edges.add(temp);
		this.mst.getTaken().add(false);
		this.E++;
	}
	
	public int getV() {
		return this.V;
	}

	public int getE() {
		return this.E;
	}

	/*
	 * This function takes last element as pivot, places the pivot element at its
	 * correct position in sorted array, and places all smaller (smaller than pivot)
	 * to left of pivot and all greater elements to right of pivot
	 */
	int partition(int low, int high) {
		edge pivot = this.edges.get(high);
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (this.edges.get(j).compareTo(pivot) <= 0) {
				i++;
				// swap arr[i] and arr[j]
				edge temp = this.edges.get(i);
				this.edges.set(i, this.edges.get(j));
				this.edges.set(j, temp);
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		edge temp = this.edges.get(i + 1);
		this.edges.set(i + 1, this.edges.get(high));
		this.edges.set(high, temp);
		return i + 1;
	}
	
	/*
	 * The main function that implements QuickSort() arr[] --> Array to be sorted,
	 * low --> Starting index, high --> Ending index
	 */
	void quicksort(int low, int high) {
		if (low < high) {
			/*
			 * pi is partitioning index, arr[pi] is now at right place
			 */
			int pi = partition(low, high);

			// Recursively sort elements before
			// partition and after partition
			quicksort(low, pi - 1);
			quicksort(pi + 1, high);
		}
	}
	
    /*Function to sort array using insertion sort*/
    void insertionSort() 
    { 
        int n = this.edges.size(); 
        for (int i = 1; i < n; ++i) { 
            edge key = this.edges.get(i); 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && this.edges.get(j).compareTo(key) > 0) { 
            	this.edges.set(j + 1,this.edges.get(j)); 
                j = j - 1; 
            } 
            this.edges.set(j + 1, key); 
        } 
    }


	public edge getEdge(int index) {
		return this.edges.get(index);
	}

	public String toString() {
		String s = new String();
		s = s + "there are the nodes\n\n";
		for (int i = 0; i < this.hashtable.length; i++) {
			LinkedList<node> list = this.hashtable[i];
			if (list.isEmpty() == true) {
				continue;
			}
			for (int j = 0; j < list.size(); j++) {
				node temp = (node) list.get(j); // etsi exo to kathe v
				s = s + "id " + temp.getId() + "position (" + temp.getX() + "," + temp.getY() + ") temperature "
						+ temp.getTemp() + "\n";
			}
		}
		s = s + "these are the edges\n\n";
		for (int i = 0; i < this.getE(); i++) {
			s = s + "there is an edge between node with id " + this.edges.get(i).getN1().getId() + " and "
					+ this.edges.get(i).getN2().getId() + " and the distance between them is "
					+ this.edges.get(i).getWeight() + "\n";
		}
		return s;
	}
	
	 public static <T> List<T> convertALtoLL(List<T> aL) 
	    { 
	  
	        // Create an empty LinkedList 
	        List<T> lL = new LinkedList<>(); 
	  
	        // Iterate through the aL 
	        for (T t : aL) { 
	  
	            // Add each element into the lL 
	            lL.add(t); 
	        } 
	  
	        // Return the converted LinkedList 
	        return lL; 
	    } 

	void printSpanTree(MinimumSpanningTree m) {
		System.out.println(m);
	}
	//
	void changeTempOfNode(int id, int temp) {
		node n = search(id);
		n.setTemp(temp);
	}

 public static void main(String [] args)
 {
	 
 Graph grafos= new Graph(3.0);
	 grafos.addNode(0, 0, 0, 20);
	 grafos.addNode(0,1,1,35);
	 grafos.addNode(2,1,2,50);
	 grafos.addNode(4,1,3,50);
	 System.out.print(grafos.toString());
	 	
	 grafos.mst.createMinimumSpanningTree(grafos);
		 System.out.print(grafos.mst.toString());	
	grafos.addNodeAdvance(0, 3,5,100);
	 System.out.print(grafos.toString());
	 System.out.print(grafos.mst.toString());	

 }
}
