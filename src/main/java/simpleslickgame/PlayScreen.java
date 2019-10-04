package simpleslickgame;

import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class PlayScreen extends BasicGameState {

    Image background;
    Image backgroundHitbox;
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
    private Animation blobfishAnimation1;
    private Animation blobfishAnimation2;


    long runningTime = 0;
    boolean score = true;
    boolean HitsFish = true;
    boolean blobfishAppears = true;
    boolean blobfishTwoAppears = true;
    boolean blobfishThreeAppears = true;

    Rectangle ninjaHitbox = new Rectangle(characterX, characterY, 130, 140);
    Rectangle enemyHitbox = new Rectangle(RandomX, RandomY, 140, 120);
    Rectangle enemyTwoHitbox = new Rectangle(400, 400, 140, 120);
    Rectangle enemyThreeHitbox = new Rectangle(600, 600, 140, 120);


    public PlayScreen(int state) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        background = new Image("src\\main\\resources\\image.png");
        backgroundHitbox = new Image("src\\main\\resources\\image.png");
        character = new Image("src\\main\\resources\\ninja-two.png");
        enemy = new Image("src\\main\\resources\\enemy.png");

        blobfishSpritesheet = new SpriteSheet("sprite_sheet.png", 300, 128);
        blobfishAnimation = new Animation(blobfishSpritesheet, 300);
        blobfishAnimation1 = new Animation(blobfishSpritesheet, 300);
        blobfishAnimation2 = new Animation(blobfishSpritesheet, 300);

        Image blobThree = new Image("blob-three.gif");
        blobfishAnimation.addFrame(blobThree, 10);

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

        Random enemyX = new Random();
        Random enemyY = new Random();
        RandomX = enemyX.nextInt(1300);
        RandomY = enemyY.nextInt(800);

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        background.draw();
        g.drawString("X pos: " + characterX + "\nY pos: " + characterY, 400, 200);
        g.drawString("Score: " + gameTemplate.gamescore, 900, 100);

        ninja.draw(characterX, characterY);

        ninjaHitbox.setLocation(characterX, characterY);
        enemyHitbox.setLocation(RandomX, RandomY);
        enemyTwoHitbox.setLocation(400, 400);
        enemyThreeHitbox.setLocation(600, 600);

        Rectangle backgroundHitbox = new Rectangle(RandomX, RandomY, 140, 120);
        backgroundHitbox.setLocation(RandomX,RandomY);
        backgroundHitbox.getLocation();

        LinkedList<Animation> list = new LinkedList<Animation>();

        list.add(blobfishAnimation);
        list.add(blobfishAnimation1);
        list.add(blobfishAnimation2);
        Iterator<Animation> iterator = list.iterator();
        //while (iterator.hasNext()){ //returns true if there are more elements in LinkedList

        if(blobfishAppears == true){
            g.drawAnimation(blobfishAnimation, RandomX, RandomY);
        }
        if(blobfishAppears == false){
            blobfishTwoAppears = true;
        }
        if(blobfishTwoAppears == true){
            g.drawAnimation(blobfishAnimation1, 400, 400);
            //blobfishThreeAppears = true;
        }
        /*if(blobfishTwoAppears == false && blobfishAppears == false){
            blobfishThreeAppears = true;
        }*/
        if(blobfishThreeAppears == true) {
            g.drawAnimation(blobfishAnimation2, 600, 600);
        }

        if(ninja == attack && ninjaHitbox.intersects(enemyHitbox)){
            blobfishAppears = false;
            blobfishTwoAppears = true;
            if(score == true){
                gameTemplate.gamescore += 10;
                score = false;
            }
        }
        else{
            blobfishTwoAppears = false;
        }
        if(ninja == attack && ninjaHitbox.intersects(enemyTwoHitbox)){
            blobfishTwoAppears = false;
            blobfishThreeAppears = true;
            if(score == true){
                gameTemplate.gamescore += 10;
                score = false;
            }
        }
        else{
            blobfishThreeAppears = false;
        }

        //if ninja intersects with 2nd hitbox, b3 appears. if not, then it disappears. b2 always appears



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

        /*runningTime += delta;
        if(runningTime > 200){
            ninja = movingLeft;
        }*/

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

//new gitcommit