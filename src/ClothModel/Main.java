package ClothModel;

public class Main {
	
	public static void main(String[] args) {
		
		World.getInstance().setWorld(15, 15, 1);
		Simulation s = new Simulation(1000,0.0001,1);
		s.run();
		
	}

}
