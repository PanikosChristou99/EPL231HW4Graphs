import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	
	
	private static class MinimumSpanningTree {
		private boolean [] taken;
		private edge [] edges;

		public MinimumSpanningTree (Graph grafos )
		{
			
		       grafos.quicksort(0,grafos.getE()-1)
			this.edges= new edge[grafos.getV()-1];
			this.taken=new boolean [grafos.getE()];
			int [] TID = new int[grafos.getV()];
			int count=0;
			int thesi=0;
			int countV=0;
			for(int i=0; i<grafos.getSizeofhashtable(); i++)
	    	{
	    		LinkedList list=grafos.getHashtable(i);
	    		if(list.isEmpty()==true)
	    		{
	    			continue;
	    		}
	    		for(int j=0; j<list.size(); j++)
	    		{
	    			node temp=(node)list.get(j); // etsi exo to kathe v
	    			temp.setThesiStoPinaka(countV);
	    			TID[countV]=countV;
	    			countV++;
	    		}
	    	}
			int i=0;
			for(int j=i; j<grafos.getE();j++)
			{
				this.taken[i]=false;
			}
			for( i=0 ;i<grafos.getE(); i++)
			{
				edge temp=grafos.getEdge(i);
				int index1=temp.getN1().getThesiStoPinaka();
				int index2= temp.getN2().getThesiStoPinaka();
				if(TID[index1]!=TID[index2])
				{
					this.taken[i]=true;
					this.edges[thesi]=temp;
					thesi++;
					boolean changeFirst=occurence(TID,index1,index2);
					if(changeFirst==true)
					{
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==TID[index2])
							{
								TID[j]=TID[index1];
								count++;
							}
						}
					}
					else
					{
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==TID[index1])
							{
								TID[j]=TID[index2];
								count++;
							}
						}
					}
				}
				else
				{
					this.taken[i]=false;
				}
				if(count==grafos.getV())
				{
					break;
				}
			}
		}
		
		boolean occurence(int [] TID,int index1,int index2)
		{
			int count1=0;
			int count2=0;
			for(int i=0; i<TID.length; i++)
			{
				if(TID[i]==TID[index1])
				{
					count1++;
					continue;
				}
				else if(TID[i]==TID[index2])
				{
					count2++;
				}
			}
			if(count1>=count2)
			{
				return true;
			}
			else
				return false;
		}
		
		 public String toString()
		 {
			 String s= new String();
			 s=s+"these are the edges\n\n";
			 for(int i=0; i<this.edges.length; i++)
			 {
			 	s=s+"there is an edge between node with id "+this.edges[i].getN1().getId()+" and "+this.edges[i].getN2().getId()+" and the distance between them is "+this.edges[i].getWeight()+"\n";
			 }
			 s=s+"this is the taken table";
			 for(int i=0;i<this.taken.length;i++)
			 {
				 s=s+" "+i+" "+this.taken[i]+"\n";
			 }
			 return s;
		 }

	}
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
		this.edges = new ArrayList();
		this.mst=null;
	}

	private static int hashFunction(node n) {
		int temp = n.getId() % sizeOfHashtable;
		return temp;
	}

	public void findNeighbors(node n) {
		// perno pu oula ta v gia to node ke vrisko kataposo ine gitones
		// ean ine enimerono ke ta 2 to neightors
		for (int i = 0; i < this.hashtable.length; i++) {
			LinkedList list = this.hashtable[i];
			if (list.isEmpty() == true) {
				continue;
			}
			for (int j = 0; j < list.size(); j++) {
				node temp = (node) list.get(j); // etsi exo to kathe v
				double weight = n.isNeighborPlusD(temp, d);
				if (weight != -1) {
					n.neighbours.add(temp);
					n.weights.add(weight);
					temp.neighbours.add(n);
					temp.weights.add(weight);
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

	public void addNode(int x, int y, int id, double t) {
		node temp = new node(x, y, id, t);
		this.findNeighbors(temp);
		int index = hashFunction(temp);
		this.hashtable[index].add(temp);
		this.V++;
	}

  
    
	public void addNode(node temp) {
		this.findNeighbors(temp);
		int index = hashFunction(temp);
		this.hashtable[index].add(temp);
		this.V++;
		
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

	public void removeNode(int id) {
		for (int i = 0; i < this.hashtable.length; i++) {
			LinkedList<node> list = this.hashtable[i];
			if (list.isEmpty() == true) {
				continue;
			}
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).getId() == id)
					list.remove(j);

			}
		}
	}

	public void removeNode(node temp) {
		for (int i = 0; i < this.hashtable.length; i++) {
			LinkedList<node> list = this.hashtable[i];
			if (list.isEmpty() == true) {
				continue;
			}
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).getId() == temp.getId())
					list.remove(j);

			}
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
	public MinimumSpanningTree calcSpanTree() {
		return new MinimumSpanningTree (this);
	}

	void printSpanTree(MinimumSpanningTree m) {
		System.out.println(m);
	}

	void changeTempOfNode(int id, int temp) {
		node n = search(id);
		n.setTemp(temp);
	}




 public static void main(String [] args)
 {
	 
 Graph grafos= new Graph(5.0);
	 grafos.addNode(0, 0, 0, 20);
	 grafos.addNode(0,1,1,35);
	 grafos.addNode(2,1,2,50);
	 grafos.addNode(3,4,3,23);
	 grafos.addNode(5,0,4,-20);
	 System.out.print(grafos.toString());
	 
	 grafos.quicksort(0, grafos.E-1);
	 System.out.print(grafos.toString());
	
	 MinimumSpanningTree kati =new MinimumSpanningTree(grafos);

		 System.out.print(kati.toString());	 
 }
}
