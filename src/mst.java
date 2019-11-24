import java.util.LinkedList;

public class mst {
	private boolean [] taken;
	private edge [] edges;
	
	
	
	public mst (Graph grafos )
	{
	       grafos.quicksort(0,grafos.getE()-1); 
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
	 public static void main(String [] args)
	 {
		 Graph grafos= new Graph(5.0);
		 grafos.addNode(0, 0, 0, 20);
		 grafos.addNode(0,1,1,35);
		 grafos.addNode(2,1,2,50);
		 System.out.print(grafos.toString());
		 
		 mst kati =new mst(grafos);
		 System.out.print(kati.toString());
	 }
	 

}
