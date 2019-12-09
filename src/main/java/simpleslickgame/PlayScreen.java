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
    Music music;
    Sound whooshSound;
    Sound hurtSound;
    int characterX = 650;
    int characterY = 500;
    int enemyX;
    int enemyY;
    int time = 0;
    private Random RandomX, RandomY;
    private ShapeFill RoundedRectangle;
    private ShapeFill roundRect;

    int[] duration = {20, 20};
    Animation ninja, movingUp, movingLeft, movingRight, attack;
    boolean quit = false; //gives player option to quit

    private SpriteSheet blobfishSpritesheet;
    private Animation blobfishAnimation;

    boolean score = true;
    boolean blobfishAppears = true;

    int maxHealth = 0; //length of green bar
    //int currentHealth = 0;

    Rectangle ninjaHitbox = new Rectangle(characterX, characterY, 130, 140);
    Rectangle enemyHitbox = new Rectangle(enemyX, enemyY, 140, 120);
    Rectangle dangerEnemyHitbox = new Rectangle(enemyX +90, enemyY, 20, 50);


    public PlayScreen(int state) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        background = new Image("src\\main\\resources\\image.png");
        character = new Image("src\\main\\resources\\ninja-two.png");
        enemy = new Image("src\\main\\resources\\enemy.png");
        music = new Music("C:\\Users\\lhunn\\Downloads\\Blazer-Rail.ogg");
        whooshSound = new Sound("C:\\Users\\lhunn\\Downloads\\dustyroom_cartoon_swipe_high_pitched.ogg");
        hurtSound = new Sound("C:\\Users\\lhunn\\Downloads\\zapsplat_cartoon_climb_down_descend_fast_steps_ladder_cute_003_38468.ogg");
        //music = new Music("libs\\Blazer.Rail.ogg");
        //whooshSound = new Sound("libs\\dustyroom_cartoon_swipe_high_pitched.ogg");
        //hurtSound = new Sound("libs\\zapsplat_cartoon_climb_down_descend_fast_steps_ladder_cute_003_38468.ogg");

        music.loop();

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

        RandomX = new Random();
        RandomY = new Random();
        enemyX = RandomX.nextInt(1300);
        enemyY = RandomY.nextInt(800);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

            background.draw();
            g.drawString("Score: " + gameTemplate.gamescore, 900, 100);
            g.drawString("Time: " + time, 100, 100);

        if(time < 20000) {

            ninja.draw(characterX, characterY);

            if (maxHealth == 100) {
                g.drawString("Level 1 complete", 500, 500);
                sbg.enterState(2);
            }
            if (maxHealth == 5) {
                g.drawString("you lose", 500, 500);
                g.setColor(Color.white);
                sbg.enterState(3);
            }

            g.fillRoundRect(1000, 50, maxHealth, 30, 10);
            if (maxHealth >= 50 && maxHealth <= 100) {
                g.setColor(Color.green);
            }

            if (maxHealth <= 50 && maxHealth > 1) {
                g.setColor(Color.yellow);
            }

            ninjaHitbox.setLocation(characterX, characterY);
            enemyHitbox.setLocation(enemyX, enemyY);
            dangerEnemyHitbox.setLocation(enemyX + 90, enemyY);


            if (blobfishAppears == true) {
                g.drawAnimation(blobfishAnimation, enemyX, enemyY);
            }

            if (ninja != attack && ninjaHitbox.intersects(dangerEnemyHitbox)) {
                //score = true;
                ninja.draw(characterX, characterY, Color.red);
            }
        }

        else {
            g.drawRoundRect(400,500,400,300,3);
            //sbg.enterState(3);
            g.drawString("You're out of time!", 450,550);
            music.stop();
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
        time += delta; //adds the time passed since last update() method call

        enemyX -= 0.01 * delta;
        enemyY -= 0.01 * delta;

        if (enemyY < 793) {
            enemyY += delta * 1f;
        }
        if (enemyY > -18) {
            enemyY -= delta * 1f;
        }
        if (enemyX > -43) {
            enemyX -= delta * 1f;
        }
        if (enemyX < 1345) {
            enemyX += delta * 1f;
        }


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
            whooshSound.play();
            ninja = attack;
        }

        if (ninja == attack && ninjaHitbox.intersects(enemyHitbox)) {

            enemyX = RandomX.nextInt(1300);
            enemyY = RandomY.nextInt(800);


            if (score == true) {
                if (maxHealth < 100 && maxHealth >= 0) {
                    maxHealth += 10;
                    gameTemplate.gamescore += 10;
                    score = true;
                }
            }
        }

        if (ninja != attack && ninjaHitbox.intersects(dangerEnemyHitbox)) {
            if (score == true) {
                if (maxHealth >= 10 && maxHealth <= 100) {
                    maxHealth -= 5;
                    gameTemplate.gamescore -= 5;
                    hurtSound.play();
                }
            }
        }
    }

    public int getID () {
        return 1;
    }

}

//fixed respawn
//smoother sprite transition
//red zone - second enemy hitbox
//added health bar
//to-do: fix gamescores