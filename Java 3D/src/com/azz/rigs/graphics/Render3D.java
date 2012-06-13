package com.azz.rigs.graphics;

public class Render3D extends Render {

	public Render3D(int width, int height) {
		super(width, height);

	}
	
	double time = 0;

	public void floor() {
		for (int y = 0; y < height; y++) {
			double ceilling = (y - height / 2.0) /height;
			
			if(ceilling < 0){
				ceilling = -ceilling;
			}
			
			double z = 8 / ceilling;
			
			time+=0.0005;
						
			for (int x = 0; x < width; x++) {
				double depth = (x - width / 2.0) /height;
				depth *= z;
				int xx = (int) (depth) & 15;
				int yy = (int) (z + time) & 15;
				pixels[x + y * width] = (xx * 16) | (yy * 16) << 8;
			}
		}
	}

}
