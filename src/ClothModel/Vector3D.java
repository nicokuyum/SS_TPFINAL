package ClothModel;

public class Vector3D {
	
	private double x,y,z;
	
	public Vector3D(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getDistance(Vector3D other){
		return Math.sqrt(Math.pow(this.x, other.x) + Math.pow(this.y, other.y) + Math.pow(this.z, other.z));
	}
	
	public void multiply(double value){
		this.x *= value;
		this.y *= value;
		this.z *= value;
	}
	
	public void divide(double value){
		this.x /= value;
		this.y /= value;
		this.z /= value;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setZ(double z) {
		this.z = z;
	}

	public void sum(Vector3D v2){
		this.x+=v2.getX();
		this.y+=v2.getY();
		this.z+=v2.getZ();
	}

	public void minus(Vector3D v2){
		this.x-=v2.getX();
		this.y-=v2.getY();
		this.z-=v2.getZ();
	}
	
	public String toString(){
		return "[" + x + " - " + y + " - " + z + "]";
	}
}
