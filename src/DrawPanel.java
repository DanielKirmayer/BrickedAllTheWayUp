import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Arrays;

public class DrawPanel extends JPanel implements MouseListener {
    private int[][] bricksLayout = new int[30][40];

    BrickLayout bricks = new BrickLayout("src/bricks", 40, true);


    public DrawPanel() {
        this.addMouseListener(this);
        int blah = bricks.getBricks().size();
        for (int i = 0; i < blah; i++) {
            bricks.doOneBrick();
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;

        int[][] layout = bricks.getBrickLayout();







        layout[0] = bricksLayout[bricksLayout.length-1];



        for (int[] ints : bricksLayout) {
            for (int j = 0; j < bricksLayout[0].length - 1; j++) {

                g.drawRect(x, y, 20, 20);
                if (ints[j] == 1) {
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
        bricks.doOneBrick();
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
}