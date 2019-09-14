/*package simpleslickgame;

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

public class BlobfishCharacter extends BasicGameState {
    Image enemy; // 8-bit blobfish attacc (4 frames)
    Image background;
    int enemyX;
    int enemyY;
    int RandomX, RandomY;
    int characterX = 650;
    int characterY = 500;
    Animation ninja, attack, movingLeft;
    int[] duration = {20, 20};

    private SpriteSheet blobfishSpritesheet;
    private Animation blobfishAnimation;

    public BlobfishCharacter(int enemy){
        super();
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = new Image("C:\\Users\\lhunn\\IdeaProjects\\Slick2D\\src\\main\\resources\\image.png");
        enemy = new Image("C:\\Users\\lhunn\\IdeaProjects\\Slick2D\\src\\main\\resources\\enemy.png");

        blobfishSpritesheet = new SpriteSheet("sprite_sheet.png", 300, 128);
        blobfishAnimation = new Animation(blobfishSpritesheet, 300);

        Image[] ninjaJump = {new Image("ninja-five.png"), new Image("ninja-five.png")};
        Image[] ninjaLeft = {new Image("ninja-two.png"), new Image("ninja-two.png")};

        attack = new Animation(ninjaJump, duration, false);
        movingLeft = new Animation(ninjaLeft, duration, false);
        movingLeft.setAutoUpdate(true);
        ninja = movingLeft;

        Random enemyX = new Random();
        Random enemyY = new Random();
        RandomX = enemyX.nextInt(1300);
        RandomY = enemyY.nextInt(800);

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        background.draw();
        ninja.draw(characterX, characterY);
        Rectangle enemyHitbox = new Rectangle(RandomX, RandomY, 140, 120);
        enemyHitbox.setLocation(RandomX, RandomY);
        enemyHitbox.getLocation();

        if(ninja != attack){
            blobfishAnimation.draw(RandomX, RandomY);
        }

        LinkedList<Animation> list = new LinkedList<Animation>();
        list.add(blobfishAnimation);
        list.add(blobfishAnimation);

        Iterator<Animation> iterator = list.iterator();
        while(iterator.hasNext()){
            iterator.next();
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }

    public int getID () {
        return 1;
    }
}

//something is wrong in this class. Ninja appears fine, no ninja or blobfish when this state is included.
//update: now blobfish fully functioning but no ninja...*/