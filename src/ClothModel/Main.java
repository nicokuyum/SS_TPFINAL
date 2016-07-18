package ClothModel;

public class Main {
	
	public static void main(String[] args) {
		
		World.getInstance().setWorld(10, 10, 1);
		Simulation s = new Simulation(20,0.0001,0.05);
		s.run();
		
	}

}
