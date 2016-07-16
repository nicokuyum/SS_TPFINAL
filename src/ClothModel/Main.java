package ClothModel;

public class Main {
	
	public static void main(String[] args) {
		
		World.getInstance().setWorld(20, 20, 1);
		Simulation s = new Simulation(100,0.01,0.1);
		s.run();
		
	}

}
