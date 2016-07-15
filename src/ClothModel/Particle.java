package ClothModel;

import java.util.*;

public class Particle {
	
	private final int ID;
	private Vector3D pos;
	private Vector3D vel;

	private double mass;
	private Set<Particle> neighbours;


	public Particle(int id, double x, double y, double z, double mass) {
		this.ID = id;
		pos = new Vector3D(x, y, z);
		vel = new Vector3D(0,0,0);
		this.mass = mass;
		neighbours = new HashSet<>();
	}

	public void setNeighbour(Particle p) {
		neighbours.add(p);
	}
	
	public Set<Particle> getNeighbours() {
		return neighbours;
	}
	
	public int getID() {
		return ID;
	}
	
	public Vector3D getPos() {
		return pos;
	}

	
	public Vector3D getVel(){
		return vel;
	}
	
	

	public double getMass() {
		return mass;
	}
	
	public double getDistance(Particle p){
		return pos.getDistance(p.getPos());

	}
	
	public int hashCode(){
		return ID;
	}
	
	public boolean equals(Object other){
		if(other == null)
			return false;
		if(other.getClass() != this.getClass())
			return false;
		Particle o = (Particle)other;
		if(this.ID != o.ID){
			return false;
		}
		return true;
	}
	
}
