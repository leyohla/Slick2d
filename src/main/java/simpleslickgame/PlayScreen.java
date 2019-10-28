package simpleslickgame;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;

import java.util.Random;

public class PlayScreen extends BasicGameState {

    Image background;
    Image character;
    Image enemy;
    int characterX = 650;
    int characterY = 500;
    int enemyX;
    int enemyY;
    private Random RandomX, RandomY;
    private ShapeFill RoundedRectangle;

    int[] duration = {20, 20};
    Animation ninja, movingUp, movingLeft, movingRight, attack;
    boolean quit = false; //gives player option to quit

    private SpriteSheet blobfishSpritesheet;
    private Animation blobfishAnimation;

    boolean score = true;
    boolean blobfishAppears = true;

    int maxHealth = 200;
    int currentHealth = 200;

    Rectangle ninjaHitbox = new Rectangle(characterX, characterY, 130, 140);
    Rectangle enemyHitbox = new Rectangle(enemyX, enemyY, 140, 120);
    Rectangle dangerEnemyHitbox = new Rectangle(enemyX +90, enemyY, 20, 50);


    public PlayScreen(int state) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        background = new Image("src\\main\\resources\\image.png");
        character = new Image("src\\main\\resources\\ninja-two.png");
        enemy = new Image("src\\main\\resources\\enemy.png");

        blobfishSpritesheet = new SpriteSheet("sprite_sheet.png", 300, 128);
        blobfishAnimation = new Animation(blobfishSpritesheet, 300);


        Image[] ninjaLeft = {new Image("ninja-two.png"), new Image("ninja-two.png")};
        Image[] ninjaUp = {new Image("ninja-one.png"), new Image("ninja-one.png")};
        Image[] ninjaRight = {new Image("ninja-three.png"), new Image("ninja-three.png")};
        Image[] ninjaJump = {new Image("ninja-five.png"), new Image("ninja-five.png")};


        movingLeft = new Animation(ninjaLeft, duration, false);
        movingRight = new Animation(ninjaRight, duration, false);
        movingUp = new Animation(ninjaUp, duration, false);
        attack = new Animation(ninjaJump, duration, true);
        movingLeft.setAutoUpdate(true);
        ninja = movingLeft;

        RandomX = new Random();
        RandomY = new Random();
        enemyX = RandomX.nextInt(1300);
        enemyY = RandomY.nextInt(800);

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        background.draw();
        //g.drawString("X pos: " + characterX + "\nY pos: " + characterY, 400, 200);
        g.drawString("Score: " + gameTemplate.gamescore, 900, 100);

        ninja.draw(characterX, characterY);

        /*g.fillRoundRect(200, 50, currentHealth, 30,3);
        g.setColor(Color.red);*/
        g.fillRoundRect(1000, 50, maxHealth, 30, 10);
        g.setColor(Color.green);


        ninjaHitbox.setLocation(characterX, characterY);
        enemyHitbox.setLocation(enemyX, enemyY);
        dangerEnemyHitbox.setLocation(enemyX +90, enemyY);


        if(blobfishAppears == true){
            g.drawAnimation(blobfishAnimation, enemyX, enemyY);
        }


        if(ninja == attack && ninjaHitbox.intersects(enemyHitbox)){
            enemyX = RandomX.nextInt(1300);
            enemyY = RandomY.nextInt(800);

            if(score == true){
                //if(maxHealth != 200){
                    //maxHealth += 20;
                    gameTemplate.gamescore += 10;
                    //score = false;
                //}
            }
        }
        if(ninja != attack && ninjaHitbox.intersects(dangerEnemyHitbox)) {
            ninja.draw(characterX,characterY, Color.red);

            if(score == true){
                //maxHealth -= 20;
                gameTemplate.gamescore -= 5;
                score = false;
                update(gc,sbg,20);
            }
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

//fixed respawn
//smoother sprite transition

//to-do: fix gamescores