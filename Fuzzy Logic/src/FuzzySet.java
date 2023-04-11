import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

class Pair<K,V>{
	K first;
	V second;
	
	public Pair(K key, V value) {
		super();
		this.first = key;
		this.second = value;
	}
	
	public K getKey() {
		return first;
	}
	public void setKey(K key) {
		this.first = key;
	}
	public V getValue() {
		return second;
	}
	public void setValue(V value) {
		this.second = value;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
	
	
	
	
}
class FuzzySet {
	String name;
	String type;
	ArrayList<Pair<Integer, Integer>> points;
	ArrayList<Integer> tmp = new ArrayList<>(Arrays.asList(0, 1, 1, 0));
	Double memValue;
	
	public FuzzySet(String name, String typeName, ArrayList<Integer> points) {
		this.name = name;
		this.type = typeName.toLowerCase();
		this.memValue = 0.;
		
		this.points = new ArrayList<>(); 
		for (int i=0; i<points.size(); i++) {
			if(points.size()==3 && i == 2)
				this.points.add(new Pair(points.get(i), tmp.get(0)));
			else
				this.points.add(new Pair(points.get(i), tmp.get(i)));
		}
		
	}
	
	public FuzzySet(FuzzySet other)
	{
		this.name = other.getName();
		this.memValue = other.memValue;
		this.points = new ArrayList<>(other.points);
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Pair<Integer, Integer>> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Pair<Integer, Integer>> points) {
		this.points = points;
	}
	
	public double calcMemValue(Integer crispVal)
	{
		double slope = 0, res = 0;
		
		for(int i=0; i<points.size()-1; i++)
		{
			Pair p1 = points.get(i);
			Pair p2 = points.get(i+1);

			if ((int)p1.first > crispVal || crispVal > (int)p2.first)
				continue;
			
			if(((int)p2.first - (int)p1.first) != 0)
			{
			
				slope = (double)((int)p2.second - (int)p1.second) / ((int)p2.first - (int)p1.first);
			}
			else
			{
				slope = 0;
			}
			/*System.out.print("Crisp is " + crispVal + " ");
			System.out.print("Points are " + p1 + p2 + " ");
			System.out.println("slope is " + slope);*/
			Double c = (int)p1.second - (slope * (int)p1.first);
			res = (slope * crispVal) + c;
			//System.out.println("res is " + res);
			if (res != 0.)
			{
				memValue = res;
			}
			
		}


		if(memValue > 1.)
		{
			memValue = 1.;
		}
		else if(memValue < 0.)
		{
			memValue = 0.;
		}
			
		return memValue;
	}

	@Override
	public String toString() {
		return "FuzzySet [name=" + name + ", type=" + type + ", points=" + points + ", tmp=" + tmp + ", memValue="
				+ memValue + "]";
	}
	
	
	
}

