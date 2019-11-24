import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	
	static final int sizeOfHashtable=5;
        private int V; 
        private int E;
        private LinkedList<node> hashtable [];
        private ArrayList<edge> edges;
        private double d;
        
        // constructor  
        Graph(double d) 
        { 
            this.d=d;
              
            // define the size of array as  
            // number of vertices 
            this.hashtable = new LinkedList [sizeOfHashtable]; 
          
            // Create a new list for each vertex 
            // such that adjacent nodes can be stored 
            for(int i = 0; i < sizeOfHashtable ; i++){ 
                hashtable[i] = new LinkedList<>(); 
            } 
            this.edges= new ArrayList(1);
        } 
    private static int hashFunction(node n)
    {
    	int temp=n.getId()%sizeOfHashtable;
    	return temp;
    }
    
    public void findNeighbors(node n)
    {	
    	//perno pu oula ta v gia to node ke vrisko kataposo ine gitones
    	// ean ine enimerono ke ta 2 to neightors
    	for(int i=0; i<this.hashtable.length; i++)
    	{
    		LinkedList list=this.hashtable[i];
    		if(list.isEmpty()==true)
    		{
    			continue;
    		}
    		for(int j=0; j<list.size(); j++)
    		{
    			node temp=(node)list.get(j); // etsi exo to kathe v
    			double weight=n.isNeighborPlusD(temp, d);
    			if(weight!=-1)
    			{
    				n.neighbours.add(temp);
    				n.weights.add(weight);
    				temp.neighbours.add(n);
    				temp.weights.add(weight);
    				addEdge(n,temp,weight);
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
	public void addNode(int x, int y, int id, double t)
    {
    	node temp= new node(x,y,id,t);
    	this.findNeighbors(temp);
    	int index=hashFunction(temp);
    	this.hashtable[index].add(temp);
    	this.V++;
    }
	public void addNode(node temp)
    {
    	this.findNeighbors(temp);
    	int index=hashFunction(temp);
    	this.hashtable[index].add(temp);
    	this.V++;
    }
    // Adds an edge to an undirected graph 
    void addEdge(node node1, node node2, double weight) 
    { 
		edge temp= new edge(node1,node2,weight);
		this.edges.add(temp);
		this.E++;
    } 
    
    public int getV()
    {
    	return this.V;
    }
    
    public int getE()
    {
    	return this.E;
    }
    
    /* This function takes last element as pivot, 
    places the pivot element at its correct 
    position in sorted array, and places all 
    smaller (smaller than pivot) to left of 
    pivot and all greater elements to right 
    of pivot */
 int partition(int low, int high) 
 { 
     edge pivot = this.edges.get(high);  
     int i = (low-1); // index of smaller element 
     for (int j=low; j<high; j++) 
     { 
         // If current element is smaller than or 
         // equal to pivot 
         if (this.edges.get(j).compareTo(pivot) <= 0) 
         { 
             i++; 
             // swap arr[i] and arr[j] 
             edge temp =this.edges.get(i); 
             this.edges.set(i, this.edges.get(j)); 
             this.edges.set(j, temp); 
         } 
     } 

     // swap arr[i+1] and arr[high] (or pivot) 
     edge temp = this.edges.get(i+1); 
     this.edges.set(i+1,this.edges.get(high)); 
     this.edges.set(high,temp); 
     return i+1; 
 } 


 /* The main function that implements QuickSort() 
   arr[] --> Array to be sorted, 
   low  --> Starting index, 
   high  --> Ending index */
 void quicksort(int low, int high) 
 { 
     if (low < high) 
     { 
         /* pi is partitioning index, arr[pi] is  
           now at right place */
         int pi = partition( low, high); 

         // Recursively sort elements before 
         // partition and after partition 
         quicksort( low, pi-1); 
         quicksort(pi+1, high); 
     } 
 } 
 
 public edge getEdge(int index)
 {
	 return this.edges.get(index);
 }
 
 public String toString()
 {
	 String s= new String();
	 s=s+"there are the nodes\n\n";
 	for(int i=0; i<this.hashtable.length; i++)
 	{
 		LinkedList list=this.hashtable[i];
 		if(list.isEmpty()==true)
 		{
 			continue;
 		}
 		for(int j=0; j<list.size(); j++)
 		{
 			node temp=(node)list.get(j); // etsi exo to kathe v
 			s=s+"id "+temp.getId()+"position ("+temp.getX()+","+temp.getY()+") temperature "+temp.getTemp()+ "\n";
 		}
 	}
 	s=s+"these are the edges\n\n";
 	for(int i=0; i<this.getE(); i++)
 	{
 		s=s+"there is an edge between node with id "+this.edges.get(i).getN1().getId()+" and "+this.edges.get(i).getN2().getId()+" and the distance between them is "+this.edges.get(i).getWeight()+"\n";
 	}
 	return s;
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

	 
	 mst kati =new mst(grafos);
	 
 }
}
