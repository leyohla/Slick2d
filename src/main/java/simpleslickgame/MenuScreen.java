package simpleslickgame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Graphics;

public class MenuScreen extends BasicGameState {

    public static final String gamename = "Welcome to blob attack";

    public MenuScreen(int state){
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        Image newgame = new Image("src\\main\\resources\\newgame.png");

        g.drawString(gamename, 500, 200);
        g.drawImage(newgame, 400, 400);
        g.drawString("Don't get too close to the fish! Press spacebar to start", 400, 300);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

        int xpos = Mouse.getX(); //tracks x and y co-ordinates of the mouse
        int ypos = Mouse.getY();

        //if((xpos < 900 && xpos > 500) && (ypos < 600 && ypos > 400)){
        //if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {

        // click doesn't properly transition to state 1 for some reason. so using spacebar

        if(input.isKeyDown(Input.KEY_SPACE)){
            sbg.getState(1).init(gc,sbg);
            sbg.enterState(1);
        }

    }
    public int getID() {
        return 0;
    }
}

