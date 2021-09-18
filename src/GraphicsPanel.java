import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicsPanel extends JPanel implements ActionListener {

    private Timer t = new Timer(100, this);

    private Game gameRef;
    private Snake snakeRef;
    private Point pointRef;

    public GraphicsPanel(Game g)
    {
        t.start();

        gameRef = g;
        snakeRef = g.getSnake();
        pointRef = g.getPoint();

        setBackground(Color.GRAY);

        addKeyListener(g);
        setFocusable(true);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        // Cast to Graphics2D since Snake uses Rectangles as body
        Graphics2D g2d = (Graphics2D) g;

        // Draw Background
        g2d.setColor(Color.GREEN);
        g2d.fillRect(0, 0, Game.width * Game.d_scale, Game.height * Game.d_scale);

        switch(gameRef.getGameState())
        {
            case PLAYING:
                // Draw Point
                g2d.setColor(Color.RED);
                g2d.fillRect(pointRef.getPosX() * Game.d_scale, pointRef.getPosY() * Game.d_scale, 20, 20);

                // Draw Snake
                g2d.setColor(Color.BLUE);
                for (Rectangle rect : snakeRef.getBody())
                {
                    g2d.fill(rect);
                }
                break;

            case FINISHED:
                g2d.setColor(Color.BLACK);
                g2d.drawString(
                        "Score: " + (snakeRef.getBody().size() - 3),
                        (Game.width / 2) * Game.d_scale - 32,
                        (Game.height / 2) * Game.d_scale
                );
                break;

            case IDLE:
            default:
                g2d.setColor(Color.BLACK);
                g2d.drawString(
                        "Press Any Key to start",
                        ((Game.width / 2) * Game.d_scale) - 60,
                        (Game.height / 2) * Game.d_scale
                );
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        gameRef.updateGame();
    }
}
