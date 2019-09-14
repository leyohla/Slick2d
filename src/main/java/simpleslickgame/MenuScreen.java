package simpleslickgame;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Graphics;

public class MenuScreen extends BasicGameState {

    public static final String gamename = "Welcome to the GAME";
    public static String mouse = "co-ordinates";

    public MenuScreen(int state){
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Image newgame = new Image("C:\\Users\\lhunn\\IdeaProjects\\Slick2D\\src\\main\\resources\\newgame.png");

        g.drawString(gamename, 500, 200);
        g.drawString(mouse, 400, 300);
        g.drawImage(newgame, 400, 400);
        //g.setColor(Color.white);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

        int xpos = Mouse.getX(); //tracks x and y co-ordinates of the mouse
        int ypos = Mouse.getY();

        mouse = "x: " + xpos + "y: " + ypos;

        if((xpos < 900 && xpos > 500) && (ypos < 600 && ypos > 400)){
            if(input.isMouseButtonDown(0)){
                sbg.enterState(1);
            }

        }
    }
    public int getID() {
        return 0;
    }
}

