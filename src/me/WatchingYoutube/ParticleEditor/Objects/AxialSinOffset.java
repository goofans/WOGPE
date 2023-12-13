package me.WatchingYoutube.ParticleEditor.Objects;

//This class is used to define AxialSinOffset Objects which are used to store information about AxialSinOffsets

public class AxialSinOffset {
	
	Particle parent;
	
	int id;
	
	MinMax amp;
	char axis;
	MinMax freq;
	MinMax phaseshift;
	
	public AxialSinOffset(Particle parent, int id) {
		this.parent = parent;
		
		this.id = id;
		
		this.amp = null;
		this.axis = 'x';
		this.freq = null;
		this.phaseshift = null;
		
	}
	
	public AxialSinOffset(Particle parent, int id, MinMax amp, char axis, MinMax freq, MinMax phaseshift) {
		this.parent = parent;
		
		this.id = id;
		
		this.amp = amp;
		this.axis = axis;
		this.freq = freq;
		this.phaseshift = phaseshift;
		
	}

}
