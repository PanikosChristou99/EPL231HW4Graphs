
public class neighbour {
private node n1;
private double weight;
public neighbour(node n1, double weight) {
	super();
	this.n1 = n1;
	this.weight = weight;
}
@Override
public String toString() {
	return "neighbour [n1=" + n1 + ", weight=" + weight + "]";
}
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	neighbour other = (neighbour) obj;
	if (n1 == null) {
		if (other.n1 != null)
			return false;
	} else if (!n1.equals(other.n1))
		return false;
	if (weight != other.weight)
		return false;
	return true;
}
public node getN1() {
	return n1;
}
public void setN1(node n1) {
	this.n1 = n1;
}
public double getWeight() {
	return weight;
}
public void setWeight(int weight) {
	this.weight = weight;
}

}
