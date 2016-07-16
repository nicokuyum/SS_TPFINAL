package ClothModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class Output {
	private static Output instance = null;

	public static Output getInstace() {
		if (instance == null)
			instance = new Output();
		return instance;
	}

	public void write(Set<Particle> particles,double time, int runNum) {
		String fileName = "output" + runNum + ".xyz";
		if (time == 0) {
			try {
				PrintWriter pw = new PrintWriter(fileName);
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
			out.write("" + (particles.size()+8) + "\n");
			
			// Comment line
			out.write("Tiempo: " + time + "\n");
			
			// Boundary Particles for Ovito
			out.write("25000\t0\t0\t0\t0.1\t0\t0\t0\n");
			out.write("25000\t20\t0\t0\t0.1\t0\t0\t0\n");
			out.write("25000\t0\t20\t0\t0.1\t0\t0\t0\n");
			out.write("25000\t0\t0\t20\t0.1\t0\t0\t0\n");
			out.write("25000\t20\t20\t0\t0.1\t0\t0\t0\n");
			out.write("25000\t20\t0\t20\t0.1\t0\t0\t0\n");
			out.write("25000\t0\t20\t20\t0.1\t0\t0\t0\n");
			out.write("25000\t20\t20\t20\t0.1\t0\t0\t0\n");
			
			for(Particle p: particles){
					out.write(p.getID() + "\t" + p.getPos().getX() + "\t" + p.getPos().getY() + "\t" + p.getPos().getZ() + "\t" + 0.2 + "\t"
							+ getColor(p));
					
					getColor(p);
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String getColor(Particle p){
		//System.out.println(p.getForce().toString());
		int x = (int)(Math.abs(p.getForce().getX())*100)%256;
		int y = (int)(Math.abs(p.getForce().getY())*100)%256;
		int z = (int)(Math.abs(p.getForce().getZ())*100)%256;
		return x + "\t" + y + "\t" + z + "\n";
		//return 255 + "\t" + 255 + "\t" + 255 + "\n"
	}

}
