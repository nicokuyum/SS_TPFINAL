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
			out.write("" + (particles.size()/*+8*/) + "\n");
			
			// Comment line
			out.write("Tiempo: " + time + "\n");
			
			// Boundary Particles for Ovito
			/*out.write("25000\t-10\t-10\t-10\t0.1\t0\n");
			out.write("25001\t30\t-10\t-10\t0.1\t0\n");
			out.write("25002\t-10\t30\t-10\t0.1\t0\n");
			out.write("25003\t-10\t-10\t30\t0.1\t0\n");
			out.write("25004\t30\t30\t-10\t0.1\t0\n");
			out.write("25005\t30\t-10\t30\t0.1\t0\n");
			out.write("25006\t-10\t30\t30\t0.1\t0\n");
			out.write("25007\t30\t30\t30\t0.1\t0\n");*/
			
			for(Particle p: particles){
					out.write(p.getID() + "\t" + p.getPos().getX() + "\t" + p.getPos().getY() + "\t" + p.getPos().getZ() + "\t" + p.getRadius() + "\t" + p.getForce().magnitude() + "\n");
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeEnergies(Set<Particle> particles,double time, int runNum) {
		String fileName = "energy" + runNum + ".txt";
		if (time == 0) {
			try {
				PrintWriter pw = new PrintWriter(fileName);
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)))) {
			out.write(time + "\t" + totEnergy(particles) + "\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private double UGEnergy(Set<Particle> particles){
		double total = 0;
		for(Particle p: particles)
			total += p.getMass()*p.getPos().getZ()*World.GRAVITY;
		return total;
	}
	
	private double KEnergy(Set<Particle> particles){
		double total = 0;
		for(Particle p: particles)
			total += p.getMass()*Math.pow(p.getVel().magnitude(),2)/2;
		return total;
	}
	
	private double UEEnergy(Set<Particle> particles){
		double total = 0;
		for(Particle p: particles){
			for(Link link: p.getNeighbours())
				total += Math.pow(link.getOriginalDist()-p.getDistance(link.getNeighbour()),2)*World.K/2;
		}
		return total/2; // (The sum is done twice per spring)
	}
	
	private double totEnergy(Set<Particle> particles){
		return UGEnergy(particles) + UEEnergy(particles) + KEnergy(particles);
	}

}
