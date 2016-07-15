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
							+ 255 + "\t" + 255 + "\t" + 255 + "\n");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
