package ClothModel;

public class Main {
	
	public static void main(String[] args) {
		
		World.getInstance().setWorld(10, 10, 1);
		
		for(int i=0;i<10;i++){
			for(int j=0; j<10; j++){
				System.out.print(World.getInstance().matrix[i][j].hashCode() + " ");
			}
			System.out.println("");
		}
		
		System.out.println();
		World.getInstance().matrix[5][2].printNeighbours();
		
		
	}

}
