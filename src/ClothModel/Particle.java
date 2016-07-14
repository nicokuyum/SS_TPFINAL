package ClothModel;

import java.util.*;

public class Particle {

	Vector pos;
	int mass;
	List<Particle> neighbours;

	public Particle(int x, int y, int mass) {
		pos = new Vector(x, y);
		this.mass = mass;
		neighbours = new ArrayList<>();
	}

	public void setNeighbours(int posi, int posj, int h, int w, Particle[][] matrix) {
		List<Vector> posNeighbours = new ArrayList<>();
		posNeighbours.add(new Vector(1, 0));
		posNeighbours.add(new Vector(1, 1));
		posNeighbours.add(new Vector(1, -1));
		posNeighbours.add(new Vector(0, -1));
		posNeighbours.add(new Vector(0, 1));
		posNeighbours.add(new Vector(-1, 1));
		posNeighbours.add(new Vector(-1, 0));
		posNeighbours.add(new Vector(-1, -1));
		
		for(Vector v : posNeighbours){
			if(!(posi + v.getX() < 0 || posi + v.getX() > h)){
				if(!(posj + v.getY() < 0 || posj + v.getY() > w)){
					neighbours.add(matrix[posi+v.getX()][posj + v.getY()]);
				}
			}
		}

	}
	
	

}
