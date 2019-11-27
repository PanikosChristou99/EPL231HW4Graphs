import java.util.ArrayList;
import java.util.LinkedList;


public  class MinimumSpanningTree {
		private ArrayList<Boolean> taken;
		private ArrayList<edge>  Medges;
		
		public MinimumSpanningTree()
		{
			taken= new ArrayList<Boolean>(10);
			this.Medges= new ArrayList<edge>(0);
		}

		public void createMinimumSpanningTree (Graph grafos )
		{
		   //  grafos.insertionSort();/// prob tuto theli svisimo
			grafos.getMst().Medges= new ArrayList<edge>(grafos.getV()-1);
			int [] TID = new int[grafos.getV()];
			int count=0;
			int thesi=0;
			int countV=0;
			for(int i=0; i<Graph.getSizeofhashtable(); i++)
	    	{
	    		LinkedList<node> list=grafos.getHashtable(i);
	    		if(list.isEmpty()==true)
	    		{
	    			continue;
	    		}
    			node temp=(node)list.getFirst();
    			while(temp!=null)
    			{
	    			temp.setThesiStoPinaka(countV);
	    			TID[countV]=countV;
	    			countV++;
	    			temp=temp.getNext();
	    		}
	    	}
			int i=0;
			for(int j=i; j<grafos.getE();j++)
			{
				grafos.getMst().taken.set(i,false);
			}
			for( i=0 ;i<grafos.getE(); i++)
			{
				edge temp=grafos.getEdge(i);
				int index1=temp.getN1().getThesiStoPinaka();
				int index2= temp.getN2().getThesiStoPinaka();
				if(TID[index1]!=TID[index2])
				{
					grafos.getMst().taken.set(i, true);
					grafos.getMst().Medges.add(temp);
					thesi++;
					boolean changeFirst=occurence(TID,index1,index2);
					if(changeFirst==true)
					{
						int tempI=TID[index2];
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==tempI)
							{
								TID[j]=TID[index1];
								count++;
							}
						}
					}
					else
					{
						int tempI=TID[index1];
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==tempI)
							{
								TID[j]=TID[index2];
								count++;
							}
						}
					}
				}
				else
				{
					grafos.getMst().taken.set(i, false);
				}
				if(count==grafos.getV())
				{
					break;
				}
			}
		}
		public void recreateMinimumSpanningTree (Graph grafos )
		{
		       //grafos.quicksort(0,grafos.getE()-1);/// prob tuto theli svisimo
			grafos.getMst().Medges= new ArrayList<edge>(grafos.getV()-1);
			grafos.getMst().taken= new ArrayList<Boolean>();
			for(int i=0; i<grafos.getE(); i++)
			{
				grafos.getMst().taken.add(false);
			}
			int [] TID = new int[grafos.getV()];
			int count=0;
			int thesi=0;
			int countV=0;
			for(int i=0; i<Graph.getSizeofhashtable(); i++)
	    	{
	    		LinkedList<node> list=grafos.getHashtable(i);
	    		if(list.isEmpty()==true)
	    		{
	    			continue;
	    		}
    			node temp=(node)list.getFirst();
    			while(temp!=null)
    			{
	    			temp.setThesiStoPinaka(countV);
	    			TID[countV]=countV;
	    			countV++;
	    			temp=temp.getNext();
	    		}
	    	}
			int i=0;
//			for(int j=i; j<grafos.getE();j++)
//			{
//				grafos.getMst().taken.set(i,false);
//			}
			for( i=0 ;i<grafos.getE(); i++)
			{
				edge temp=grafos.getEdge(i);
				int index1=temp.getN1().getThesiStoPinaka();
				int index2= temp.getN2().getThesiStoPinaka();
				if(TID[index1]!=TID[index2])
				{
					grafos.getMst().taken.set(i, true);
					grafos.getMst().Medges.add(temp);
					thesi++;
					boolean changeFirst=occurence(TID,index1,index2);
					if(changeFirst==true)
					{
						int tempI=TID[index2];
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==tempI)
							{
								TID[j]=TID[index1];
								count++;
							}
						}
					}
					else
					{
						int tempI=TID[index1];
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==tempI)
							{
								TID[j]=TID[index2];
								count++;
							}
						}
					}
				}
				else
				{
					grafos.getMst().taken.set(i, false);
				}
//				if(count==grafos.getV())
//				{
//					break;
//				}
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
		public void addNodeInMSTVersion2(node n,Graph grafos,ArrayList<edge> newEdges)
		{
			grafos.getMst().Medges= new ArrayList<edge>(grafos.getV()-1);
//			grafos.getMst().taken= new ArrayList<Boolean>();
//			for(int i=0; i<grafos.getE(); i++)
//			{
//				grafos.getMst().taken.add(false);
//			}
			int [] TID = new int[grafos.getV()];
			int count=0;
			int thesi=0;
			int countV=0;
			for(int i=0; i<Graph.getSizeofhashtable(); i++)
	    	{
	    		LinkedList<node> list=grafos.getHashtable(i);
	    		if(list.isEmpty()==true)
	    		{
	    			continue;
	    		}
    			node temp=(node)list.getFirst();
    			while(temp!=null)
    			{
	    			temp.setThesiStoPinaka(countV);
	    			TID[countV]=countV;
	    			countV++;
	    			temp=temp.getNext();
	    		}
	    	}
			for(int i=0; i<grafos.getMst().Medges.size(); i++)
			{
				newEdges.add(grafos.getMst().getMedge(i));
			}
			Graph.insertionSort(newEdges);
			int i=0;
//			for(int j=i; j<grafos.getE();j++)
//			{
//				grafos.getMst().taken.set(i,false);
//			}
			for( i=0 ;i<newEdges.size(); i++)
			{
				edge temp=newEdges.get(i);
				int index1=temp.getN1().getThesiStoPinaka();
				int index2= temp.getN2().getThesiStoPinaka();
				if(TID[index1]!=TID[index2])
				{
					//grafos.getMst().taken.set(i, true);
					grafos.getMst().Medges.add(temp);
					thesi++;
					boolean changeFirst=occurence(TID,index1,index2);
					if(changeFirst==true)
					{
						int tempI=TID[index2];
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==tempI)
							{
								TID[j]=TID[index1];
								count++;
							}
						}
					}
					else
					{
						int tempI=TID[index1];
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==tempI)
							{
								TID[j]=TID[index2];
								count++;
							}
						}
					}
				}
				else
				{
					//grafos.getMst().taken.set(i, false);
				}
				if(count==grafos.getV())
				{
					break;
				}
			}
		}
		
		public void addNodeInMST(Graph grafos, node newNode)
		{
			grafos.getMst().Medges.ensureCapacity(grafos.getMst().Medges.size()+1);
			grafos.getMst().taken.ensureCapacity(grafos.getE());
			int [] TID = new int[grafos.getV()];
			int count=0;
			int thesi=0;
			newNode.thesiStoPinaka=grafos.getV()-1; // logika tuto bori na en +-1
			int i=0;
			int intTemp=0;
			for(i=0; i<grafos.getE();i++)
			{
				node node1=grafos.getEdges().get(i).getN1();
				node node2=grafos.getEdges().get(i).getN2();
				if((node1.equals(newNode))||(node2.equals(newNode)))
				{
					intTemp=i;
					break;
				}
				else
				{
					if(grafos.getMst().taken.get(i)==true)
					{
					// en elenxo epd troi parapano ora.
					TID[node1.thesiStoPinaka]=100;
					TID[node2.thesiStoPinaka]=100;
					count++;
					}
				}
				
			}
			ArrayList<edge> edgeTemp= new ArrayList<edge>(intTemp);
			for( i=0; i<intTemp; i++)
			{
				edgeTemp.add(grafos.getMst().getMedge(i));
			}
			for( i=intTemp ;i<grafos.getE(); i++)
			{
				edge temp=grafos.getEdge(i);
				int index1=temp.getN1().getThesiStoPinaka();
				int index2= temp.getN2().getThesiStoPinaka();
				if(TID[index1]!=TID[index2])
				{
					grafos.getMst().taken.set(i,true);
					grafos.getMst().Medges.add(temp);
					thesi++;
					boolean changeFirst=occurence(TID,index1,index2);
					if(changeFirst==true)
					{
						int tempI=TID[index2];
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==tempI)
							{
								TID[j]=TID[index1];
								count++;
							}
						}
					}
					else
					{
						int tempI=TID[index1];
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==tempI)
							{
								TID[j]=TID[index2];
								count++;
							}
						}
					}
				}
				else
				{
					grafos.getMst().taken.set(i,false);
				}
				if(count==grafos.getV()-1)
				{
					intTemp=i+1;
					break;
				}
			}
			for(i=intTemp; i<grafos.getE(); i++)
			{
				grafos.getMst().taken.set(i, false);
			}
		}
		
		public void removeNodeInMST(Graph grafos, node toBeRemoved)
		{
			int [] TID = new int[grafos.getV()];
			int count=0;
			int thesi=0;
			int intTemp=0;
			int i=0;
			for(i=0; i<grafos.getE(); i++)
			{
				if(grafos.getEdge(i).isOneOfNodes(toBeRemoved))
				{
					intTemp=i;
					break;
				}
				if(grafos.getMst().taken.get(i)==true)
				{
					node node1=grafos.getEdges().get(i).getN1();
					node node2=grafos.getEdges().get(i).getN2();
					TID[node1.thesiStoPinaka]=100;
					TID[node2.thesiStoPinaka]=100;
				}
			}
//			if(intTemp<grafos.getMst().Medges.size())
//			{
//			grafos.getMst().Medges= (ArrayList<edge>) grafos.getMst().Medges.subList(0, intTemp);
//			}
			ArrayList<edge> edgeTemp= new ArrayList<edge>(intTemp);
			for( i=0; i<intTemp; i++)
			{
				edgeTemp.add(grafos.getMst().getMedge(i));
			}
			grafos.getMst().setMedges(edgeTemp);
			for( i=intTemp ;i<grafos.getE(); i++)
			{
				edge temp=grafos.getEdge(i);
				int index1=temp.getN1().getThesiStoPinaka();
				int index2= temp.getN2().getThesiStoPinaka();
				
				if(temp.isOneOfNodes(toBeRemoved)==true)
				{
					//skip
				}
				else
				{
				if(TID[index1]!=TID[index2])
				{
					grafos.getMst().taken.set(i,true);
					grafos.getMst().Medges.add(temp);
					thesi++;
					boolean changeFirst=occurence(TID,index1,index2);
					if(changeFirst==true)
					{
						int tempI=TID[index2];
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==tempI)
							{
								TID[j]=TID[index1];
								count++;
							}
						}
					}
					else
					{
						int tempI=TID[index1];
						for(int j=0;j<TID.length; j++)
						{
							if(TID[j]==tempI)
							{
								TID[j]=TID[index2];
								count++;
							}
						}
					}
				}
				else
				{
					grafos.getMst().taken.set(i,false);
				}
				if(count==grafos.getV()-1-(1))// to epipleon plin ena ine epd ena fii 1 node
				{
					intTemp=i+1;
					break;
				}
				}
			}
			for(i=intTemp; i<grafos.getE(); i++)
			{
				grafos.getMst().taken.set(i, false);
			}
		}
		
		public void removeNodeInMSTVersion2(node n, Graph grafos,ArrayList<edge>newEdges )
		{
		grafos.getMst().Medges= new ArrayList<edge>(grafos.getV()-1-(1));
		int [] TID = new int[grafos.getV()];
		int count=0;
		int thesi=0;
		int countV=0;
		for(int i=0; i<Graph.getSizeofhashtable(); i++)
    	{
    		LinkedList<node> list=grafos.getHashtable(i);
    		if(list.isEmpty()==true)
    		{
    			continue;
    		}
			node temp=(node)list.getFirst();
			while(temp!=null)
			{
    			temp.setThesiStoPinaka(countV);
    			TID[countV]=countV;
    			countV++;
    			temp=temp.getNext();
    		}
    	}
		for(int i=0; i<grafos.getMst().Medges.size(); i++)
		{
			edge temp= grafos.getMst().getMedge(i);
			if(temp.isOneOfNodes(n)==false)
			{
			newEdges.add(temp);
			}
		}
		Graph.insertionSort(newEdges);
		int i=0;
//		for(int j=i; j<grafos.getE();j++)
//		{
//			grafos.getMst().taken.set(i,false);
//		}
		for( i=0 ;i<newEdges.size(); i++)
		{
			edge temp=newEdges.get(i);
			int index1=temp.getN1().getThesiStoPinaka();
			int index2= temp.getN2().getThesiStoPinaka();
			if(TID[index1]!=TID[index2])
			{
				//grafos.getMst().taken.set(i, true);
				grafos.getMst().Medges.add(temp);
				thesi++;
				boolean changeFirst=occurence(TID,index1,index2);
				if(changeFirst==true)
				{
					int tempI=TID[index2];
					for(int j=0;j<TID.length; j++)
					{
						if(TID[j]==tempI)
						{
							TID[j]=TID[index1];
							count++;
						}
					}
				}
				else
				{
					int tempI=TID[index1];
					for(int j=0;j<TID.length; j++)
					{
						if(TID[j]==tempI)
						{
							TID[j]=TID[index2];
							count++;
						}
					}
				}
			}
			else
			{
				//grafos.getMst().taken.set(i, false);
			}
			if(count==grafos.getV())
			{
				break;
			}
		}
		}
		
		 public ArrayList<Boolean> getTaken() {
			return taken;
		}

		public void setTaken(ArrayList<Boolean> taken) {
			this.taken = taken;
		}

		public ArrayList<edge> getMedges() {
			return Medges;
		}
		
		public edge getMedge(int index) {
			return Medges.get(index);
		}

		public void setMedges(ArrayList<edge> medges) {
			Medges = medges;
		}

		public String toString()
		 {
			 String s= new String();
			 s=s+"these are the Medges\n\n";
			 for(int i=0; i<this.Medges.size(); i++)
			 {
			 	s=s+"there is an edge between node with id "+this.Medges.get(i).getN1().getId()+" and "+this.Medges.get(i).getN2().getId()+" and the distance between them is "+this.Medges.get(i).getWeight()+"\n";
			 }
			 s=s+"this is the taken table\n";
			 for(int i=0;i<this.taken.size();i++)
			 {
				 s=s+" "+i+" "+this.taken.get(i)+"\n";
			 }
			 return s;
		 }

	}
