
public class edge {
private node n1;
private node n2;
private double weight;

public edge(node n1, node n2,double weight) {
	this.n1 = n1;
	this.n2 = n2;
	this.weight = weight;
}

public static edge copyEdge(edge original)
{
	edge temp=new edge(original.n1,original.n2, original.weight);
	return temp;
}

public int compareTo(edge kati)
{
	if(this.weight>kati.weight)
	{
		return 1;
	}
	else if(this.weight<kati.weight)
	{
		return -1;
	}
	else 
	{
		return 0;
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
	edge other = (edge) obj;
	if (n1 == null) {
		if (other.n1 != null)
			return false;
	} else if (!n1.equals(other.n1))
		return false;
	if (n2 == null) {
		if (other.n2 != null)
			return false;
	} else if (!n2.equals(other.n2))
		return false;
	if (weight != other.weight)
		return false;
	return true;
}

@Override
public String toString() {
	return "edge [n1=" + n1 + ", n2=" + n2 + ", weight=" + weight + "]";
}

public node getN1() {
	return n1;
}
public void setN1(node n1) {
	this.n1 = n1;
}
public node getN2() {
	return n2;
}
public void setN2(node n2) {
	this.n2 = n2;
}
public double getWeight() {
	return weight;
}
public void setWeight(int weight) {
	this.weight = weight;
}



}
