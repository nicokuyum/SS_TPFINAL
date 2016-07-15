package ClothModel;

import java.util.Set;

public class Simulation {
	private World world;
	private Set<Particle> particles;
	
	private double totalTime,dt,dt2;
	
	public Simulation(double totalTime, double dt, double dt2){
		if(dt>dt2 || dt<=0 || totalTime<=0)
			throw new IllegalArgumentException();
		this.world = World.getInstance();
		this.particles = world.getParticles();
		this.totalTime = totalTime;
		this.dt = dt;
		this.dt2 = dt2;
	}
	
	public void beeman(Particle p){
		//calculate next position
		p.getNextPos().setX(p.getPos().getX() + p.getVel().getX()*dt + (2.0/3.0)*p.getForce().getX()*dt*dt/p.getMass() - (1.0/6.0)*p.getPrevF().getX()*dt*dt/p.getMass());
		p.getNextPos().setY(p.getPos().getY() + p.getVel().getY()*dt + (2.0/3.0)*p.getForce().getY()*dt*dt/p.getMass() - (1.0/6.0)*p.getPrevF().getY()*dt*dt/p.getMass());
		p.getNextPos().setZ(p.getPos().getZ() + p.getVel().getZ()*dt + (2.0/3.0)*p.getForce().getZ()*dt*dt/p.getMass() - (1.0/6.0)*p.getPrevF().getZ()*dt*dt/p.getMass());
		
		//predict next vel
		Particle predicted = new Particle(new Vector3D(p.getPos()),new Vector3D(0,0,0),p.getMass());
		predicted.getVel().setX(p.getVel().getX() + (3.0/2.0)*dt*p.getForce().getX()/p.getMass() - 0.5*dt*p.getPrevF().getX()/p.getMass());
		predicted.getVel().setY(p.getVel().getY() + (3.0/2.0)*dt*p.getForce().getY()/p.getMass() - 0.5*dt*p.getPrevF().getY()/p.getMass());
		predicted.getVel().setZ(p.getVel().getZ() + (3.0/2.0)*dt*p.getForce().getZ()/p.getMass() - 0.5*dt*p.getPrevF().getZ()/p.getMass());
		
		//calculate next accel using position and predicted vel
		predicted.addSameNeighbours(p);
		p.setNextF(World.getInstance().Force(predicted));
		
		//correct the next vel
		p.getVel().setX(p.getVel().getX() + (1.0/3.0)*dt*p.getNextF().getX()/p.getMass() + (5.0/12.0)*dt*p.getPrevF().getX()/p.getMass() + (2.0/3.0)*dt*p.getForce().getX()/p.getMass() - (1.0/12.0)*dt*p.getPrevF().getX()/p.getMass());
		p.getVel().setY(p.getVel().getY() + (1.0/3.0)*dt*p.getNextF().getY()/p.getMass() + (5.0/12.0)*dt*p.getPrevF().getY()/p.getMass() + (2.0/3.0)*dt*p.getForce().getY()/p.getMass() - (1.0/12.0)*dt*p.getPrevF().getY()/p.getMass());
		p.getVel().setZ(p.getVel().getZ() + (1.0/3.0)*dt*p.getNextF().getZ()/p.getMass() + (5.0/12.0)*dt*p.getPrevF().getZ()/p.getMass() + (2.0/3.0)*dt*p.getForce().getZ()/p.getMass() - (1.0/12.0)*dt*p.getPrevF().getZ()/p.getMass());
		
		p.setPrevF(p.getForce());
		
		p.setPos(p.getNextPos());
		
		p.setForce(World.getInstance().Force(p));
	}
	
	public void run(){
		int runs = 0;
		double time = 0, printTime = 0;
		for(Particle p: particles){
			p.setForce(World.getInstance().Force(p));
			Vector3D prevpos = eulerPos(p,dt);
			Vector3D prevvel = eulerVel(p,dt);
			Particle auxPrev = new Particle(prevpos,prevvel,p.getMass());
			auxPrev.addSameNeighbours(p);
			p.setPrevF(World.getInstance().Force(auxPrev));
		}
		while(time <= totalTime){
			if(runs%100==0)
				System.out.println((100*time/totalTime) + "%");
			if(printTime<=time){
				Output.getInstace().write(particles,time,0);
				printTime += dt2;
			}
			for(Particle p: particles)
				beeman(p);
			time += dt;
		}
	}
	
	private Vector3D eulerPos(Particle part, double dt){
		double x = part.getPos().getX() + dt*part.getVel().getX() + dt*dt*part.getForce().getX()/(2*part.getMass());
		double y = part.getPos().getY() + dt*part.getVel().getY() + dt*dt*part.getForce().getY()/(2*part.getMass());
		double z = part.getPos().getZ() + dt*part.getVel().getZ() + dt*dt*part.getForce().getZ()/(2*part.getMass());
		return new Vector3D(x,y,z);
	}
	
	private Vector3D eulerVel(Particle part, double dt){
		double velx = part.getVel().getX() + dt*part.getForce().getX()/part.getMass();
		double vely = part.getVel().getY() + dt*part.getForce().getY()/part.getMass();
		double velz = part.getVel().getZ() + dt*part.getForce().getZ()/part.getMass();
		return new Vector3D(velx,vely,velz);
	}
	
}
