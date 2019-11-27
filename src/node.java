

import java.util.ArrayList;

public class node {
private int x;
private int y;
private int id;
private double temp;
private ArrayList<neighbour> neighbours;
int thesiStoPinaka;
private node next;

 public node getNext() {
	return next;
}
public void setNext(node next) {
	this.next = next;
}
public int getThesiStoPinaka() {
	return thesiStoPinaka;
}
public void setThesiStoPinaka(int thesiStoPinaka) {
	this.thesiStoPinaka = thesiStoPinaka;
}

public node(int x, int y, int id,double temp) {
	 this.x =x;
	 this.y =y;
	 this.id =id;
	 this.temp =temp;
	 this.neighbours = new ArrayList<neighbour>();
	 this.next=null;
}
public void addNeigb(neighbour n) {
	this.neighbours.add(n);
}
public void removeNeigh(neighbour n) {
	for (int i = 0; i < neighbours.size(); i++) {
		if(n.equals(neighbours.get(i))) {
			neighbours.remove(i);
			break;
		}
	}
}

//
//public ArrayList<edge> getEdges() {
//	ArrayList<edge> a = new ArrayList<edge>();
//	for (int i = 0; i < this.neighbours.size(); i++) {
//		a.add(new edge(this, neighbours.get(i),weights.get(i)));
//	}
//	return a;
//}

public double isNeighborPlusD(node n2, double d)
{
	double distance=findDistance(this, n2);
	if(distance<=d)
	{
		return distance;
	}
	return -1;
}

public static double findDistance(node n1, node n2)
{
	double alfa= (n1.x-n2.getX())* (n1.x-n2.getX());
	double vita= (n1.y-n2.getY())*(n1.y-n2.getY());
	double distance=Math.sqrt(alfa+vita);
	return distance;
}

public int getX() {
	return x;
}
public void setX(int x) {
	this.x = x;
}
public int getY() {
	return y;
}
public void setY(int y) {
	this.y = y;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public double getTemp() {
	return temp;
}
public void setTemp(double temp) {
	this.temp = temp;
}

public void removeNeighbour(node n)
{
	for(int i=0; i<this.neighbours.size(); i++)
	{
		if(this.neighbours.get(i).getN1().getId()==n.getId())
		{
			this.neighbours.remove(i);
			break;
		}
	}
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	node other = (node) obj;
	if (id != other.id)
		return false;
	if (neighbours == null) {
		if (other.neighbours != null)
			return false;
	} else if (!neighbours.equals(other.neighbours))
		return false;
	if (Double.doubleToLongBits(temp) != Double.doubleToLongBits(other.temp))
		return false;
	if (x != other.x)
		return false;
	if (y != other.y)
		return false;
	return true;
}

public ArrayList<neighbour> getNeighbours() {
	return neighbours;
}
public void setNeighbours(ArrayList<neighbour> neighbours) {
	this.neighbours = neighbours;
}
@Override
public String toString() {
	return "node [x=" + x + ", y=" + y + ", id=" + id + ", temp=" + temp + ", neighbours=" + neighbours
			+ ", thesiStoPinaka=" + thesiStoPinaka + "]";
}

}
