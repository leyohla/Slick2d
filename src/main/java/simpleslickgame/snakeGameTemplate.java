/* package simpleslickgame;

import org.newdawn.slick.*;

import java.util.logging.Level;
        import java.util.logging.Logger;

public class snakeGameTemplate extends BasicGame
{

    public float[][] snake_body = new float[200][2];
    int snake_size=0; //length of the snake (how many circles)
    int foodx;
    int foody;
    static int width=1300; //width of the window
    static int height=1000; //height of the window
    int snkb=30; //snkb means 'snake body'. Speed variable
    int foodsize=30;

    final int LEFT = 0;
    final int RIGHT = 1;
    final int UP = 2;
    final int DOWN = 3;

    int DIRECTION = 0;

    public snakeGameTemplate(String gamename)
    {
        super(gamename); //inherits game name from parent class
    }

    public void draw_snake(Graphics g) {
        for (int i=0;i<snake_size;i++) {
            g.fillOval(snake_body[i][0],snake_body[i][1],30,30);
            g.setColor(Color.green);
        }
    }

    public void grow_snake(float x, float y) {
        this.snake_body[snake_size][0] = x;
        this.snake_body[snake_size][1] = y;
        snake_size++;
    }

    public void prop_snake_body() {
        for(int j=snake_size-1;j>0;j--){
            snake_body[j][0]=snake_body[j-1][0];
            snake_body[j][1]=snake_body[j-1][1];
        }
    }

    public void plant_food() {
        foodx=60;
        foody=60;
    }

    public void draw_food(Graphics g) {
            g.fillOval(foodx,foody,foodsize,foodsize);
            g.setColor(Color.blue);
    }

    @Override
    public void keyPressed(int key, char c) { //int is the asci, char is the key on keyboard.
        switch(key) {
            case Input.KEY_UP: {
                DIRECTION = UP;
                break;
            }
            case Input.KEY_DOWN: {
                DIRECTION = DOWN;
                break;
            }
            case Input.KEY_LEFT: {
                DIRECTION = LEFT;
                break;
            }
            case Input.KEY_RIGHT: {
                DIRECTION = RIGHT;
                break;
            }
        }
    }

    public void move_snake() {
        switch(DIRECTION) {
            case UP: {
                snake_body[0][1] -= snkb;
                break;
            }
            case DOWN: {
                snake_body[0][1] += snkb;
                break;
            }
            case LEFT: {
                snake_body[0][0] -= snkb;
                break;
            }
            case RIGHT: {
                snake_body[0][0] += snkb;
                break;
            }
        }
        snake_body[0][0] = snake_body[0][0] % width;
        snake_body[0][1] = snake_body[0][1] % height;

    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        grow_snake(210,210);
        plant_food();
        DIRECTION = (int) Math.floor(Math.random()*4);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        if(foodx==snake_body[0][0]&&foody==snake_body[0][1]) {
            plant_food();
            System.out.println("you just got bigger");
            grow_snake(snake_body[snake_size][0],snake_body[snake_size][1]);
        }
        prop_snake_body();
        move_snake();
        draw_snake(g);
        draw_food(g);
    }

    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new snakeGameTemplate("Simple Slick Game"));
            appgc.setTargetFrameRate(5);
            appgc.setDisplayMode(width, height, false);
            appgc.start();

        }
        catch (SlickException ex)
        {
            Logger.getLogger(snakeGameTemplate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
*/
