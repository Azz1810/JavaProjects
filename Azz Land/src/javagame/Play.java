package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {

	Animation player, movingUp, movingDown, movingLeft, movingRight;
	Image worldMap;
	boolean quit = false;
	int[] duration = { 200, 200 };
	float playerPosX = 0;
	float playerPosY = 0;
	float shiftX = playerPosX + 320; // change this later!
	float shiftY = playerPosY + 160;

	public Play(int state) {

	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// the world map
		worldMap = new Image("res/world2.png");

		// character images
		Image[] walkUp = { new Image("res/characterBack.png"), new Image("res/characterBack.png") };
		Image[] walkDown = { new Image("res/characterFront.png"), new Image("res/characterFront.png") };
		Image[] walkLeft = { new Image("res/characterLeft.png"), new Image("res/characterLeft.png") };
		Image[] walkRight = { new Image("res/characterRight.png"), new Image("res/characterRight.png") };

		// animations
		movingUp = new Animation(walkUp, duration, false);
		movingDown = new Animation(walkDown, duration, false);
		movingLeft = new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);

		player = movingDown;
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawImage(worldMap, playerPosX, playerPosY);
		player.draw(shiftX, shiftY); // draw character in the middle of the
										// screen
		g.setColor(Color.black);
		g.fillRect(400, 0, 300, 40);
		g.setColor(Color.white);

		g.drawString("X Position: " + playerPosX + "\nY Position: " + playerPosY, 400, 0);

		if (quit == true) {
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Menu (M)", 250, 150);
			g.drawString("Quit Game (Q)", 250, 200);
			if (quit == false) {
				g.clear();
			}
		}
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();

		if (input.isKeyDown(Input.KEY_UP)) {
			player = movingUp;
			playerPosY += delta * .1f;
			if (playerPosY > 162) {
				playerPosY -= delta * .1f;
			}

		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			player = movingDown;
			playerPosY -= delta * .1f;
			if (playerPosY < -567) {
				playerPosY += delta * .1f;
			}
		}
		if (input.isKeyDown(Input.KEY_LEFT)) {
			player = movingLeft;
			playerPosX += delta * .1f;
			if (playerPosX > 324) {
				playerPosX -= delta * .1f;
			}
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			player = movingRight;
			playerPosX -= delta * .1f;
			if (playerPosX < -800) {
				playerPosX += delta * .1f;
			}
		}

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			quit = true;
		}

		if (quit == true) {
			if (input.isKeyDown(Input.KEY_R)) {
				quit = false;
			}
			if (input.isKeyDown(Input.KEY_M)) {
				game.enterState(0);
				quit = false;
				playerPosX = 0;
				playerPosY = 0;
			}
			if (input.isKeyDown(Input.KEY_Q)) {
				System.exit(0);
			}
		}
	}

	public int getID() {

		return 1;
	}

}
