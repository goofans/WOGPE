package me.WatchingYoutube.ParticleEditor.Objects;

import java.util.ArrayList;

//This class is used to define Particle Objects which are used to store information about Particles

public class Particle {
	
	//Define all fields
	
	int id;
	
	ArrayList<String> image; //Required
	boolean additive; //Optional
	float dampening; //Optional
	boolean directed; //Optional
	boolean fade; //Optional
	
	MinMax lifespan;
	MinMax scale;
	float finalscale;
	MinMax speed;
	
	MinMax acceleration;
	int movedir;
	int movedirvar;
	MinMax rotation;
	
	MinMax rotspeed;
	
	public Particle(
	int id,
	ArrayList<String> image, 
	boolean additive, 
	float dampening, 
	boolean directed, 
	boolean fade, 
	MinMax lifespan, 
	MinMax scale, 
	float finalscale, 
	MinMax speed, 
	MinMax acceleration,
	int movedir,
	int movedirvar,
	MinMax rotation,
	MinMax rotspeed) 
	{
		
		this.id = id;
		this.image = image;
		this.additive = additive;
		this.dampening = dampening;
		this.directed = directed;
		this.fade = fade;
		this.lifespan = lifespan;
		this.scale = scale;
		this.finalscale = finalscale;
		this.speed = speed;
		this.acceleration = acceleration;
		this.movedir = movedir;
		this.movedirvar = movedirvar;
		this.rotation = rotation;
		this.rotspeed = rotspeed;
		
	}

}
