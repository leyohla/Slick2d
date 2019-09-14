package simpleslickgame;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.lang.InterruptedException;

import static java.lang.Thread.interrupted;

public class PlayScreen extends BasicGameState {

    Image background;
    Image character;
    Image enemy;
    int characterX = 650;
    int characterY = 500;
    int enemyX;
    int enemyY;
    int RandomX, RandomY;

    int[] duration = {20, 20};
    Animation ninja, movingUp, movingLeft, movingRight, attack;
    boolean quit = false; //gives player option to quit

    private SpriteSheet blobfishSpritesheet;
    private Animation blobfishAnimation;

    public PlayScreen(int state) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("C:\\Users\\lhunn\\IdeaProjects\\Slick2D\\src\\main\\resources\\image.png");
        character = new Image("C:\\Users\\lhunn\\IdeaProjects\\Slick2D\\src\\main\\resources\\ninja-two.png");
        enemy = new Image("C:\\Users\\lhunn\\IdeaProjects\\Slick2D\\src\\main\\resources\\enemy.png");

        blobfishSpritesheet = new SpriteSheet("sprite_sheet.png", 300, 128);
        blobfishAnimation = new Animation(blobfishSpritesheet, 300);

        Image[] ninjaLeft = {new Image("ninja-two.png"), new Image("ninja-two.png")};
        Image[] ninjaUp = {new Image("ninja-one.png"), new Image("ninja-one.png")};
        Image[] ninjaRight = {new Image("ninja-three.png"), new Image("ninja-three.png")};
        Image[] ninjaJump = {new Image("ninja-five.png"), new Image("ninja-five.png")};

        movingLeft = new Animation(ninjaLeft, duration, false);
        movingRight = new Animation(ninjaRight, duration, false);
        movingUp = new Animation(ninjaUp, duration, false);
        attack = new Animation(ninjaJump, duration, false);
        movingLeft.setAutoUpdate(true);
        ninja = movingLeft;

        Random enemyX = new Random();
        Random enemyY = new Random();
        RandomX = enemyX.nextInt(1300);
        RandomY = enemyY.nextInt(800);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw();
        g.drawString("X pos: " + characterX + "\nY pos: " + characterY, 400, 200);

        ninja.draw(characterX, characterY);
        Rectangle ninjaHitbox = new Rectangle(characterX, characterY, 130, 140);
        ninjaHitbox.setLocation(characterX, characterY);
        ninjaHitbox.getLocation();

        Rectangle enemyHitbox = new Rectangle(RandomX, RandomY, 140, 120);
        enemyHitbox.setLocation(RandomX, RandomY);
        enemyHitbox.getLocation();

        /*LinkedList<Animation> list = new LinkedList<Animation>();
        list.add(blobfishAnimation);
        list.add(blobfishAnimation);

        Iterator<Animation> iterator = list.iterator();
        while(iterator.hasNext()){ //returns true if there are more elements in LinkedList
            iterator.next();
            blobfishAnimation.draw(200, 200);
        }*/


        //instead of a list, add a for loop to loop through the blobfish at random locations,

        if(ninja != attack) {
            if (!ninjaHitbox.intersects(enemyHitbox)) {
                blobfishAnimation.draw(RandomX, RandomY);
            }
        }
        else if (ninja == attack && ninjaHitbox.intersects(enemyHitbox)) {
                blobfishAnimation.draw(300, 300);
            //using a sleep thread is a noooo. Possible for loop?
        }
        else if (ninja == attack && !ninjaHitbox.intersects(enemyHitbox)) {
            blobfishAnimation.draw(RandomX,RandomY);
        }
        if(ninja != attack && ninjaHitbox.intersects(enemyHitbox)) {
            ninja.draw(characterX,characterY,Color.red);
        }

        if(ninja != attack){
            blobfishAnimation.draw(RandomX, RandomY);
        }


        if (quit == true) {
            g.drawString("Quit (Q)", 750, 600);
            g.drawString("Resume (R)", 750, 500);
            g.drawString("Main menu (M)", 750, 400);
            if (quit == false) {
                g.clear();
            }
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();

        if (characterY < 793) {
            if (input.isKeyDown(Input.KEY_DOWN)) {
                ninja = movingLeft;
                characterY += delta * 1f;
            }
        }

        if (characterY > -18) {
            if (input.isKeyDown(Input.KEY_UP)) {
                ninja = movingUp;
                characterY -= delta * 1f;
            }
        }
        if (characterX > -43) {
            if (input.isKeyDown(Input.KEY_LEFT)) {
                ninja = movingLeft;
                characterX -= delta * 1f;
            }
        }
        if (characterX < 1345) {
            if (input.isKeyDown(Input.KEY_RIGHT)) {
                ninja = movingRight;
                characterX += delta * 1f;
            }
        }
        if (input.isKeyPressed(Input.KEY_SPACE)) {
            ninja = attack;
            }
            return;
        }

        public int getID () {
            return 1;
        }

    }