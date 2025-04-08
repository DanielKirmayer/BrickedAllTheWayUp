import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener, ActionListener {
    private int[][] bricksLayout = new int[30][40];
    private Timer timer;
    BrickLayout bricks = new BrickLayout("src/bricks", 40, true);

    public DrawPanel() {
        timer = new Timer(50, this);
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;

        bricksLayout = bricks.getBrickLayout();

        for (int i = 0; i < bricksLayout.length; i++) {
            for (int j = 0; j < bricksLayout[0].length; j++) {
                g.drawRect(x, y, 20, 20);
                if (bricksLayout[i][j] == 1) {
                    g.setColor(new Color(165, 42, 42));
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(x, y, 20, 20);
                g.setColor(Color.BLACK);
                x += 25;
            }
            x = 10;
            y += 25;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!bricks.getBricks().isEmpty()) {
            bricks.doOneBrick();
            repaint();
        }
    }
}
