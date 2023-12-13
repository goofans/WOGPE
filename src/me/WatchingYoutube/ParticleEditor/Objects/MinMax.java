package me.WatchingYoutube.ParticleEditor.Objects;

//This class is used to define MinMax Objects, they can take 1 or 2 float values depending on what they are given, idk if java has something that does this already but I'm too lazy to check

public class MinMax {
	
	float min;
	float max;
	float SingleFloat;
	
	public MinMax() {
		
		this.min = 0F;
		this.max = 0F;
		
	}
	
	public MinMax(float min, float max) {
		this.min = min;
		this.max = max;
	}
	
	public MinMax(float SingleFloat) {
		this.SingleFloat = SingleFloat;
	}

}
