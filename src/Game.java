import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {

    /*
    Components
     */
    private Snake snake;
    private Point point;
    private JFrame window;
    private GraphicsPanel gpanel;

    /*
    Variables
     */
    public static final int width = 20;
    public static final int height = 20;
    public static final int d_scale = 20;

    public enum GAME_STATE
    {
        IDLE,
        PLAYING,
        FINISHED
    }
    private GAME_STATE state = GAME_STATE.IDLE;

    public Game()
    {
        // Init Components
        window = new JFrame();
        snake = new Snake();
        point = new Point(snake);
        gpanel = new GraphicsPanel(this);

        // Setup Window
        window.setSize(new Dimension(width * d_scale, height * d_scale));
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Snake Game");
        window.setMinimumSize(new Dimension(400, 400));

        window.add(gpanel);
    }

    public void updateGame()
    {
        if (state == GAME_STATE.PLAYING)
        {
            if (checkPointCollection())
            {
                snake.move(true);
                point.spawn(snake);
            }
            else if (checkSnakeCollision())
            {
                state = GAME_STATE.FINISHED;
            }
            else
            {
                snake.move(false);
            }
        }
    }

    private boolean checkPointCollection()
    {
        if (snake.getPositionX() == point.getPosX() * d_scale && snake.getPositionY() == point.getPosY() * d_scale)
        {
            System.out.print("Collision");
            return true;
        }
        return false;
    }

    private boolean checkSnakeCollision()
    {
        // Check if snake is out of bounds
        if (snake.getPositionX() < 0 || snake.getPositionX() >= width * d_scale
                || snake.getPositionY() < 0 || snake.getPositionY() >= height * d_scale)
        {
            return true;
        }
        // Check whether snake head (body.get(0)) collides with any other body part
        for (int i = 1; i < snake.getBody().size(); i++)
        {
            if (snake.getPositionX() == snake.getBody().get(i).x
                    && snake.getPositionY() == snake.getBody().get(i).y)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (state == GAME_STATE.PLAYING)
        {
            switch(e.getKeyCode())
            {
                case KeyEvent.VK_W:
                    if (snake.getMovementDirection() != Snake.DIRECTION.DOWN)
                        snake.setMovementDirection(Snake.DIRECTION.UP);
                    break;
                case KeyEvent.VK_S:
                    if (snake.getMovementDirection() != Snake.DIRECTION.UP)
                        snake.setMovementDirection(Snake.DIRECTION.DOWN);
                    break;
                case KeyEvent.VK_A:
                    if (snake.getMovementDirection() != Snake.DIRECTION.RIGHT)
                        snake.setMovementDirection(Snake.DIRECTION.LEFT);
                    break;
                case KeyEvent.VK_D:
                    if (snake.getMovementDirection() != Snake.DIRECTION.LEFT)
                        snake.setMovementDirection(Snake.DIRECTION.RIGHT);
                    break;
            }
        }
        else
        {
            startGame();
        }
    }

    public void startGame()
    {
        // Start new game
        state = GAME_STATE.PLAYING;
        snake.setup();
        snake.setMovementDirection(Snake.DIRECTION.UP);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Do nothing
    }


    public GAME_STATE getGameState()
    {
        return state;
    }

    public Snake getSnake()
    {
        return snake;
    }

    public Point getPoint()
    {
        return point;
    }
}
