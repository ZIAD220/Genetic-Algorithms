import java.util.ArrayList;

public class Variable {
	String name;
	String type;
	Integer minRange, maxRange;
	Integer crisp = 0;
	ArrayList<FuzzySet> fuzzySets;
	
	public Variable(String name)
	{
		this.name = name;
		this.fuzzySets = new ArrayList<>();
	
	}
	
	
	public Variable(String name, String type, Integer minRange, Integer maxRange) {
		this.name = name;
		this.type = type.toLowerCase();
		this.minRange = minRange;
		this.maxRange = maxRange;
		this.fuzzySets = new ArrayList<>();
	}


	public Variable(String name, ArrayList<FuzzySet> fuzzySets) {
		this.name = name;
		this.fuzzySets = new ArrayList<>(fuzzySets);
		
	}
	
	public Variable(String name, String type, ArrayList<FuzzySet> fuzzySets) {
		this.name = name;
		this.type = type.toLowerCase();
		this.fuzzySets = new ArrayList<>(fuzzySets);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<FuzzySet> getFuzzySets() {
		return fuzzySets;
	}

	public void setFuzzySets(ArrayList<FuzzySet> fuzzySets) {
		this.fuzzySets = fuzzySets;
	}
	
	public void addFuzzySet(FuzzySet fuzzySet) {
		this.fuzzySets.add(fuzzySet);
	}


	@Override
	public String toString() {
		return "Variable [name=" + name + ", type=" + type + ", minRange=" + minRange + ", maxRange=" + maxRange
				+ ", fuzzySets=" + fuzzySets + "]" + "\n";
	}
	
	
}
