package simpleslickgame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState {
    public GameOver(int state){
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("GAME OVER", 600, 300);
        g.drawString("Start again: Y", 600,500);
        g.drawString("Exit: N", 600, 600);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

        if(input.isKeyDown(input.KEY_Y)){
            sbg.getState(0).init(gc,sbg);
            sbg.enterState(0);
        }
        if(input.isKeyDown(input.KEY_N)){
            sbg.getState(0).init(gc,sbg);
            sbg.enterState(0);
        }



    }

    public int getID(){
        return 3;
    }
}

//make this into a window instead of filling a whole page
