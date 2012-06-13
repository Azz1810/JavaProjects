package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

public class Menu extends BasicGameState {

	Image playNow;
	Image exitGame;
	Image azz;

	public Menu(int state) {

	}

	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		playNow = new Image("res/playNow.png");
		exitGame = new Image("res/exitGame.png");
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		g.drawString("Welcome to Azz's Land!", 200, 50);
		g.drawImage(playNow, 200, 100);
		g.drawImage(exitGame, 200, 200);		
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		Input input = container.getInput();
		int mouseX = Mouse.getX();
		int mouseY = Mouse.getY();

		// are the coords of the mouse inside the play button?
		if ((mouseX > 100 && mouseX < 311) && (mouseY > 209 && mouseY < 260)) {
			if (input.isMouseButtonDown(0)) {
				game.enterState(1);		// enter play state
			}
		}
		
		// are the coord of the mouse inside the exit button?
		if ((mouseX > 100 && mouseX < 311) && (mouseY > 109 && mouseY < 160)) {
			if (input.isMouseButtonDown(0)) {
				System.exit(0);		// exit the game
			}
		}
	}

	public int getID() {

		return 0;
	}

}
