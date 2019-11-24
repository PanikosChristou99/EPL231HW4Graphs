import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	
	static final int sizeOfHashtable=100;
        int V; 
        int E;
        LinkedList<node> hashtable [];
        ArrayList<edge> edges;
        double d;
        
        // constructor  
        Graph(int V,double d) 
        { 
            this.V = V; 
            this.d=d;
              
            // define the size of array as  
            // number of vertices 
            this.hashtable = new LinkedList [V]; 
          
            // Create a new list for each vertex 
            // such that adjacent nodes can be stored 
            for(int i = 0; i < V ; i++){ 
                hashtable[i] = new LinkedList<>(); 
            } 
            this.edges= new ArrayList(V-1);
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
    
    public void addNode(int x, int y, int id, double t)
    {
    	node temp= new node(x,y,id,t);
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
    } 
}
