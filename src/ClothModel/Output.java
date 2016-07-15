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

	public void write(Particle[][] matrix, int h, int w, double time, int runNum) {
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
			/*
			 * out.write((h*w+6) + "\n"); out.write("Comment line\n");
			 * out.write(100000 + "\t" + 0 + "\t" + 20 + "\t" + 0.5 +
			 * "\t0\t0\t0" + "\n"); out.write(100001 + "\t" + 0 + "\t" + 0 +
			 * "\t" + 0.5 + "\t0\t0\t0" + "\n"); out.write(100002 + "\t" + 20 +
			 * "\t" + 0 + "\t" + 0.5 + "\t0\t0\t0" + "\n"); out.write(100004+
			 * "\t" + 20 + "\t" + 20 + "\t" + 0.5 + "\t0\t0\t0" + "\n");
			 * out.write(100005+ "\t" + 9.15 + "\t" + 0 + "\t" + 0.25 +
			 * "\t0\t255\t0" + "\n"); out.write(100006 + "\t" + 10.85 + "\t" + 0
			 * + "\t" + 0.25 + "\t0\t255\t0" + "\n");
			 */

			// radio de la particula?
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					Particle p = matrix[i][j];
					out.write(p.ID + "\t" + p.pos.getX() + "\t" + p.pos.getY() + "\t" + p.pos.getZ() + "\t" + p.r + "\t"
							+ 255 + "\t" + 255 + "\t" + 255 + "\n");
				}
			}

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeEnergy(Particle[][] matrix, int h, int w, double time) {
		if (time == 0) {
			try {
				PrintWriter pw = new PrintWriter("energy.txt");
				pw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("energy.txt", true)))) {
			out.write(time + "\t" + (getK(matrix, h, w)) + "\n");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private double getK(Particle[][] matrix, int h, int w) {
		double K = 0;
		/*
		 * for(Particle p: particles){ //K += 0.5*p.m*p.vx*p.vx*p.vy*p.vy;
		 * //Chequear esto!!! K += 0.5*p.mass*Math.pow(p.vel.getX(),
		 * 2)*Math.pow(p.vel.getY(), 2)*Math.pow(p.vel.getZ(), 2); }
		 */
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Particle p = matrix[i][j];
				K += 0.5 * p.mass * Math.pow(p.vel.getX(), 2) * Math.pow(p.vel.getY(), 2) * Math.pow(p.vel.getZ(), 2);
			}
		}

		return K;
	}
}
