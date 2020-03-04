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

        if(input.isKeyDown(Input.KEY_SPACE)){
            sbg.getState(1).init(gc,sbg);
            sbg.enterState(1);
        }

    }
    public int getID() {
        return 0;
    }
}

