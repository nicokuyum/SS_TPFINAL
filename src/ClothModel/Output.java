package ClothModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
			out.write("" + (h*w) + "\n");
			out.write("Comment line\n");
			// radio de la particula?
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					Particle p = matrix[i][j];
					out.write(p.getID() + "\t" + p.pos.getX() + "\t" + p.pos.getY() + "\t" + p.pos.getZ() + "\t" + 0.1 + "\t"
							+ 255 + "\t" + 255 + "\t" + 255 + "\n");
				}
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
