import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;


public class node {
private int x;
private int y;
private int id;
private double temp;
ArrayList<node> neighbours;
ArrayList<Double> weights;
int thesiStoPinaka;
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
	 this.neighbours = new ArrayList<node>();
	 this.weights = new ArrayList<Double>();
}
public ArrayList<edge> getEdges() {
	ArrayList<edge> a = new ArrayList<edge>();
	for (int i = 0; i < this.neighbours.size(); i++) {
		a.add(new edge(this, neighbours.get(i),weights.get(i)));
	}
	return a;
}

public double isNeighborPlusD(node n2, double d)
{
	double alfa= (this.x-n2.getX())* (this.x-n2.getX());
	double vita= (this.y-n2.getY())*(this.y-n2.getY());
	double distance=Math.sqrt(alfa+vita);
	if(distance<=d)
	{
		return distance;
	}
	return -1;
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


public ArrayList<node> getNeighbours() {
	return neighbours;
}
public void setNeighbours(ArrayList<node> neighbours) {
	this.neighbours = neighbours;
}
public ArrayList<Double> getWeights() {
	return weights;
}

public void setWeights(ArrayList<Double> weights) {
	this.weights = weights;
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
	if (weights == null) {
		if (other.weights != null)
			return false;
	} else if (!weights.equals(other.weights))
		return false;
	if (x != other.x)
		return false;
	if (y != other.y)
		return false;
	return true;
}

@Override
public String toString() {
	return id + "\t(" +x+","+y + ")\t" + temp ;
}

}
