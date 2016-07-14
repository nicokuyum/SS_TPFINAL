package ClothModel;

public class World {

	private static World instance = null;
	private Particle[][] matrix = null;
	public int h, w;

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
		generateParticle(matrix, h, w, mass);
		calculateNeighbours(matrix);
	}

	private void generateParticle(Particle[][] matrix, int height, int width, int mass) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				matrix[i][j] = new Particle(i, j, mass);
			}
		}
	}

	private void calculateNeighbours(Particle[][] matrix) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				matrix[i][j].setNeighbours(i, j, h, w, matrix);
			}
		}
	}
	
	public Particle[][] getMatrix() {
		return matrix;
	}

}
