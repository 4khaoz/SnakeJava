import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake {

    private ArrayList<Rectangle> body;
    int w = Game.width;
    int h = Game.height;
    int d = Game.d_scale;

    public enum DIRECTION
    {
        IDLE,
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    private DIRECTION movement = DIRECTION.IDLE;

    public Snake()
    {
        body = new ArrayList<>();



        setup();
    }

    public ArrayList<Rectangle> getBody()
    {
        return body;
    }

    public void move(boolean bExtend)
    {
        if (movement != DIRECTION.IDLE)
        {
            Rectangle rect = new Rectangle(Game.d_scale, Game.d_scale);
            Rectangle head = body.get(0);
            if (movement == DIRECTION.UP)
            {
                rect.setLocation(head.x, head.y - Game.d_scale);
            }
            else if (movement == DIRECTION.DOWN)
            {
                rect.setLocation(head.x, head.y + Game.d_scale);
            }
            else if (movement == DIRECTION.LEFT)
            {
                rect.setLocation(head.x - Game.d_scale, head.y);
            }
            else
            {
                rect.setLocation(head.x + Game.d_scale, head.y);
            }

            // Move snake my adding new rect and removing last rect in body
            body.add(0, rect);
            if (!bExtend)
            {
                body.remove(body.size() - 1);
            }
        }
    }

    public DIRECTION getMovementDirection()
    {
        return movement;
    }

    public void setMovementDirection(DIRECTION dir)
    {
        movement = dir;
    }

    public int getPositionX()
    {
        return body.get(0).x;
    }

    public int getPositionY()
    {
        return body.get(0).y;
    }

    public void setup()
    {
        body.clear();

        // Init 3 Rectangle as snake body
        Rectangle head = new Rectangle(d, d);
        head.setLocation(w/2 * d, h/2 * d);

        Rectangle part1 = new Rectangle(d, d);
        part1.setLocation(w/2 * d, (h/2 + 1) * d);

        Rectangle part2 = new Rectangle(d, d);
        part2.setLocation(w/2 * d, (h/2 + 2) * d);

        body.add(head);
        body.add(part1);
        body.add(part2);
    }
}
