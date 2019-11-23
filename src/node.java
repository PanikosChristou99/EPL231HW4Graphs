import java.util.ArrayList;
import java.util.LinkedList;

public class node {
private int x;
private int y;
private int id;
private double temp;
LinkedList<node> neighbours;
ArrayList<Integer> weights;
 public node(int x, int y, int id,double temp) {
	 this.x =x;
	 this.y =y;
	 this.id =id;
	 this.temp =temp;
	 this.neighbours = new LinkedList<node>();
	 ArrayList<Integer> weights = new ArrayList<Integer>();
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
public LinkedList<node> getNeighbours() {
	return neighbours;
}
public void setNeighbours(LinkedList<node> neighbours) {
	this.neighbours = neighbours;
}

public ArrayList<Integer> getWeights() {
	return weights;
}

public void setWeights(ArrayList<Integer> weights) {
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
	return "node [x=" + x + ", y=" + y + ", id=" + id + ", temp=" + temp + ", neighbours=" + neighbours + ", weights="
			+ weights + "]";
}

}
