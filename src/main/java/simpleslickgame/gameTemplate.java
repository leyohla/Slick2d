package simpleslickgame;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;


public class gameTemplate extends StateBasedGame {

    public static final String gamename = "Welcome to the GAME";
    public static final int MENU = 0;
    public static final int PLAY_SCREEN = 1;
    public static final int NINJA = 1;
    public static final int ENEMY = 1;
    public static int gamescore = 0;


    public gameTemplate(String gamename) {
        super(gamename); //superclass's constructor
        this.addState(new MenuScreen(MENU));
        this.addState(new PlayScreen(PLAY_SCREEN));
        //this.addState(new BlobfishCharacter(ENEMY));
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(MENU).init(gc, this);
        this.getState(PLAY_SCREEN).init(gc, this);
        //this.getState(NINJA).init(gc, this);
        //this.getState(ENEMY).init(gc, this);
        this.enterState(MENU);
    }

    public void renderStatesList(GameContainer gc, Graphics g) throws SlickException {

    }

    public void updateStatesList(GameContainer gc, int i) throws SlickException {
    }


    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new gameTemplate("Simple Slick Game"));
            appgc.setDisplayMode(1500, 1000, false);
            Display.setResizable(true);
//            appgc.setIcon("C:\\Users\\lhunn\\IdeaProjects\\Slick2D\\src\\main\\resources\\ninja-icon.png");
            appgc.setIcon("src\\main\\resources\\ninja-icon.png");

            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(gameTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
