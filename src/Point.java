import java.awt.*;

public class Point {

    private int posX;
    private int posY;

    public Point(Snake s)
    {
        // Spawn at random Location OnConstruction
        spawn(s);
    }

    public void spawn(Snake snakeRef)
    {
        boolean bSpawnCollides = false;

        do {
            // Generate random Coordinates
            posX = (int) (Math.random() * (Game.width - 1));
            posY = (int) (Math.random() * (Game.height - 1));

            // Check whether Spawn Location collides with snake body
            for (Rectangle rect : snakeRef.getBody())
            {
                if (rect.x == posX && rect.y == posY)
                {
                    bSpawnCollides = true;
                }
            }
        }
        while (bSpawnCollides);
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }
}
