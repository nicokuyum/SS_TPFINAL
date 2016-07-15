package ClothModel;

import java.util.*;

public class Particle {
	
	private static int counter = 0;
	private final int ID;
	private Vector3D pos, nextPos;
	private Vector3D vel, force, nextF, prevF;

	private double mass;
	private Set<Particle> neighbours;


	public Particle(int id, Vector3D pos, Vector3D vel, double mass) {
		this.ID = id;
		this.pos = pos;
		this.vel = vel;
		this.mass = mass;
		this.nextPos = new Vector3D();
		neighbours = new HashSet<>();
		counter++;
	}
	
	public Particle(Vector3D p,Vector3D v, double mass){
		this(counter,p,v,mass);
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

	public void setPos(Vector3D pos) {
		this.pos = pos;
	}
	
	public Vector3D getVel(){
		return vel;
	}
	
	public Vector3D getNextPos() {
		return nextPos;
	}

	public double getMass() {
		return mass;
	}
	
	public Vector3D getForce() {
		return force;
	}
	
	public void setForce(Vector3D f){
		this.force = f;
	}
	
	public Vector3D getNextF() {
		return nextF;
	}
	
	public void setNextF(Vector3D nextF) {
		this.nextF = nextF;
	}
	
	public double getDistance(Particle p){
		return pos.getDistance(p.getPos());

	}
	
	public Vector3D getPrevF() {
		return prevF;
	}
	
	public void setPrevF(Vector3D prevF) {
		this.prevF = prevF;
	}
	
	public void setNextPos(Vector3D nextPos) {
		this.nextPos = nextPos;
	}
	
	public void addSameNeighbours(Particle p){
		for(Particle paux: p.getNeighbours())
			this.neighbours.add(paux);
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
