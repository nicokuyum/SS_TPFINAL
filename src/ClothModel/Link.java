package ClothModel;

public class Link {
	
	private Particle neighbour;
	private double originalDist;
	
	public Link(Particle n, double o){
		this.neighbour = n;
		this.originalDist = o;
	}
	
	public Particle getNeighbour() {
		return neighbour;
	}
	
	public double getOriginalDist() {
		return originalDist;
	}
}
