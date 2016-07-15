package ClothModel;

public class Main {
	
	public static void main(String[] args) {
		
		World.getInstance().setWorld(100, 100, 1);
		
		Output.getInstace().write(World.getInstance().getMatrix(), 100, 100, 0, 0);
		
	}

}
