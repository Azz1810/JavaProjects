package com.azz.rigs.graphics;

import java.util.Random;

public class Screen extends Render {

	private Render test;
	private Render3D render;

	public Screen(int width, int height) {
		super(width, height);
		Random random = new Random();
		render = new Render3D(width, height);
		test = new Render(256, 256);
		for (int i = 0; i < 256 * 256; i++) {
			test.pixels[i] = random.nextInt();
		}
	}

	public void render() {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = 0;
		}

		for (int i = 0; i < 100; i++) {
			int anim = (int) (Math.sin((System.currentTimeMillis() + i * 4) % 2000.0 / 2000 * Math.PI * 2) * 200);
			int anim2 = (int) (Math.cos((System.currentTimeMillis() + i * 4) % 2000.0 / 2000 * Math.PI * 2) * 200);
		}
		render.floor();
		draw(render, 0,0);
	}

}
