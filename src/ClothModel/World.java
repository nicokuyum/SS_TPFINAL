package ClothModel;

public class World {
	
	public final double INIT_HEIGHT = 0;
	public boolean diagonal = true;

	private static World instance = null;
<<<<<<< HEAD
	private Particle[][] matrix = null;
	private int h, w;
=======

	private Particle[][] matrix = null;
	private static final double GRAVITY = 9.8;

	public int h, w;
>>>>>>> 4186c81b3628afccf4bb1483e5ba674d95c9815e

	private World() {
	}

	public static World getInstance() {
		if (instance == null)
			instance = new World();
		return instance;
	}

	public void setWorld(int h, int w, int mass) {
		if (matrix == null) {
			matrix = new Particle[h][w];
			this.h = h;
			this.w = w;
		}
		generateParticles(matrix, h, w, mass);
		calculateNeighbours(matrix);
	}

	private void generateParticles(Particle[][] matrix, int height, int width, int mass) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				matrix[i][j] = new Particle(i*width+j,i, j, INIT_HEIGHT, mass);
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
		for(int x=0; x<=1; x++){
			neighX = i+x;
			if(neighX>=0 && neighX<h){
				for(int y=0; y<=1; y++){
					neighY = j+y;
					// Add particles: right, bottom and bottom right
					if(neighY>=0 && neighY<w && (x!=0 || y!=0)){
						if(!(!diagonal && x==1 && y==1)){
							matrix[i][j].setNeighbour(matrix[neighX][neighY]);
							matrix[neighX][neighY].setNeighbour(matrix[i][j]);
						}
					}
				}
			}
		}
		// Add particle: top right
		if(diagonal && i-1>=0 && j+1<w){
			matrix[i][j].setNeighbour(matrix[i-1][j+1]);
			matrix[i-1][j+1].setNeighbour(matrix[i][j]);
		}
	}
	
	public Vector3D Force(Particle p){
		Vector3D f = new Vector3D(0,0,-p.getMass()*GRAVITY);
		return addVector(getInternalForces(p),getExternalForces(p));		
		
	}
	
	private Vector3D getInternalForces(Particle p){
		Vector3D f=new Vector3D(0,0,0);
		double k =0.3;
		double original = 1;
		
		for(Particle neighbour: p.getNeighbours()){
			Vector3D aux= substractVector(neighbour.getPos(),p.getPos());
			
			f.sum(substractVector(aux,(substractVector(neighbour.getPos(),p.getPos())).multiply(original/p.getDistance(neighbour)) );
		}
		
		
		return f;
		
	}
	
	private Vector3D getExternalForces(Particle p){
		Vector3D f = new Vector(0,0,GRAVITY*p.get);
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

}
