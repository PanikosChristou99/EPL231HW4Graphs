import java.util.ArrayList;
import java.util.LinkedList;

class MinimumSpanningTree { 
		ArrayList<Boolean> taken;
		ArrayList<edge>  Medges;
		
		public MinimumSpanningTree()
		{
			taken= new ArrayList<Boolean>(1);
			this.Medges= new ArrayList<edge>(0);
		}


		public MinimumSpanningTree (Graph grafos )
		{
		       grafos.quicksort(0,grafos.getE()-1);/// prob tuto theli svisimo
			this.Medges= new ArrayList<edge>(grafos.getV()-1);
			this.taken=new ArrayList<Boolean>(grafos.getE());
			int [] TID = new int[grafos.getV()];
			int count=0;
			int thesi=0;
			int countV=0;
			for(int i=0; i<grafos.getSizeofhashtable(); i++)
	    	{
	    		LinkedList<node> list=grafos.getHashtable(i);
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
				this.taken.set(i,false);
			}
			for( i=0 ;i<grafos.getE(); i++)
			{
				edge temp=grafos.getEdge(i);
				int index1=temp.getN1().getThesiStoPinaka();
				int index2= temp.getN2().getThesiStoPinaka();
				if(TID[index1]!=TID[index2])
				{
					this.taken.set(i, true);
					this.Medges.set(thesi,temp);
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
					this.taken.set(i, false);
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
		
		public void addNodeInMST(node n, Graph grafos, node newNode)
		{
			this.Medges.ensureCapacity(this.Medges.size()+1);
			this.taken.ensureCapacity(grafos.getE());
			int [] TID = new int[grafos.getV()];
			int count=0;
			int thesi=0;
			newNode.thesiStoPinaka=grafos.getV()-1; // logika tuto bori na en +-1
			int i=0;
			int intTemp=0;
			for(i=0; i<grafos.getE();i++)
			{
				node node1=grafos.edges.get(i).getN1();
				node node2=grafos.edges.get(i).getN2();
				
				if((node1.equals(newNode))||(node2.equals(newNode)))
				{
					intTemp=i;
					break;
				}
				else
				{
					// en elenxo epd troi parapano ora.
					TID[node1.thesiStoPinaka]=100;
					TID[node2.thesiStoPinaka]=100;
				}
			}
			for( i=intTemp ;i<grafos.getE(); i++)
			{
				edge temp=grafos.getEdge(i);
				int index1=temp.getN1().getThesiStoPinaka();
				int index2= temp.getN2().getThesiStoPinaka();
				if(TID[index1]!=TID[index2])
				{
					this.taken.set(i,true);
					this.Medges.set(thesi,temp);
					thesi++;
					boolean changeFirst=occurence(TID,index1,index2);
					if(changeFirst==true)// tuto to occurence maybe en axristo
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
					this.taken.set(i,false);
				}
				if(count==grafos.getV())
				{
					break;
				}
			}
		}
		
		 public String toString()
		 {
			 String s= new String();
			 s=s+"these are the Medges\n\n";
			 for(int i=0; i<this.Medges.size(); i++)
			 {
			 	s=s+"there is an edge between node with id "+this.Medges.get(i).getN1().getId()+" and "+this.Medges.get(i).getN2().getId()+" and the distance between them is "+this.Medges.get(i).getWeight()+"\n";
			 }
			 s=s+"this is the taken table";
			 for(int i=0;i<this.taken.size();i++)
			 {
				 s=s+" "+i+" "+this.taken.get(i)+"\n";
			 }
			 return s;
		 }

	}