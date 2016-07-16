package ClothModel;

import java.util.HashSet;
import java.util.Set;

public class World {
	
	public final double INIT_HEIGHT = 20;
	public boolean diagonal = true;

	private static World instance = null;
	private Particle[][] matrix = null;
	private Set<Particle> particles;
	
	private static final double GRAVITY = 9.8;
	private static final double C = 0.05;
	private static final double K = 1000;
	public int h, w;

	private World() {
		 particles = new HashSet<Particle>();
	}

	public static World getInstance() {
		if (instance == null)
			instance = new World();
		return instance;
	}

	public void setWorld(int h, int w, int mass) {
		particles.clear();
		matrix = new Particle[h][w];
		this.h = h;
		this.w = w;
		generateParticles(matrix, h, w, mass);
		calculateNeighbours(matrix);
	}

	private void generateParticles(Particle[][] matrix, int height, int width, int mass) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Particle p = new Particle(i*width+j,new Vector3D(i, j, /*INIT_HEIGHT-i/4.0*/15),new Vector3D(0,0,0), mass);
				matrix[i][j] = p;
				particles.add(p);
			}
		}
	}

	private void calculateNeighbours(Particle[][] matrix) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				setNeighbours(i, j);
			}
		}
	}
	
	private void setNeighbours(int i, int j){
		int neighX, neighY;
		Particle p = matrix[i][j];
		for(int x=0; x<=1; x++){
			neighX = i+x;
			if(neighX>=0 && neighX<h){
				for(int y=0; y<=1; y++){
					neighY = j+y;
					// Add particles: right, bottom and bottom right
					if(neighY>=0 && neighY<w && (x!=0 || y!=0)){
						if(!(!diagonal && x==1 && y==1)){
							Particle other = matrix[neighX][neighY];
							double dist = p.getDistance(other);
							p.setNeighbour(new Link(other,dist));
							other.setNeighbour(new Link(p,dist));
						}
					}
				}
			}
		}
		// Add particle: top right
		if(diagonal && i-1>=0 && j+1<w){
			Particle other = matrix[i-1][j+1];
			double dist = p.getDistance(other);
			p.setNeighbour(new Link(other,dist));
			other.setNeighbour(new Link(p,dist));
		}
	}
	
	public Vector3D Force(Particle p){
		return addVector(getInternalForces(p),getExternalForces(p));		
	}
	
	private Vector3D getInternalForces(Particle p){
		Vector3D f=new Vector3D(0,0,0);
		
		for(Link link: p.getNeighbours()){
			Vector3D aux= substractVector(link.getNeighbour().getPos(),p.getPos());
			Vector3D term = substractVector(aux,multiplyVector((substractVector(link.getNeighbour().getPos(),p.getPos())),link.getOriginalDist()/p.getDistance(link.getNeighbour())));
			term.multiply(K);
			f.sum(term);
		}
		
		return f;
	}
	
	private Vector3D getExternalForces(Particle p){

		Vector3D vel = p.getVel();
		vel.multiply(C);
		Vector3D f = new Vector3D(0,0,-GRAVITY*p.getMass());
		f.minus(vel);

		return f;
	}
	
	public Particle[][] getMatrix() {
		return matrix;
	}
	
	private Vector3D addVector(Vector3D v1, Vector3D v2){
		return new Vector3D(v1.getX()+v2.getX(),v1.getY()+v2.getY(),v1.getZ()+v2.getZ());
	}
	
	private Vector3D substractVector(Vector3D v1, Vector3D v2){
		return new Vector3D(v1.getX()-v2.getX(),v1.getY()-v2.getY(),v1.getZ()-v2.getZ());
	}
	
	private Vector3D multiplyVector(Vector3D v1, double value){
		return new Vector3D(v1.getX()*value, v1.getY()*value, v1.getZ()*value);
	}
	
	public Set<Particle> getParticles() {
		return particles;
	}

}
