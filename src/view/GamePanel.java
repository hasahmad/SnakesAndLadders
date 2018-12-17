package view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private JLabel squareLabel[][];
    private static int count = 101;

    public GamePanel() {
        Dimension dim = getPreferredSize();
        dim.width = 700;
        setPreferredSize(dim);
        setLayout(new GridLayout(10, 10, 0, 0));

        squareLabel = new JLabel[10][10];

        createView();
    }

    public void createView() {
        int temp = 0; //used to make snake pattern
        for (int x = 0; x < squareLabel.length; x++) { //loop start for x-axis
            for (int y = 0; y < squareLabel.length; y++) { //loop start for y-axis
                if (x % 2 == 0) {

                    count--;
                    temp = count - 10;
                    squareLabel[x][y] = new JLabel("" + count);
                    squareLabel[x][y].setText(count + "");

                    squareLabel[x][y].setIcon(new ImageIcon(getClass()
                            .getResource("/resources/pics/" + count + ".jpg")));

                    squareLabel[x][y].setPreferredSize(new Dimension(70, 70));
                    add(squareLabel[x][y]);
                } else if (x % 2 != 0) {

                    squareLabel[x][y] = new JLabel("" + temp);
                    squareLabel[x][y].setText("" + temp);

                    squareLabel[x][y].setIcon(new ImageIcon(getClass()
                            .getResource("/resources/pics/" + temp + ".jpg")));

                    squareLabel[x][y].setPreferredSize(new Dimension(70, 70));
                    add(squareLabel[x][y]);
                    temp++;
                    count--;
                }
            }
        }
    }

    public JLabel[][] getSquareLabel() {
        return squareLabel;
    }

    public void setLabelPosition(int x, int y, String icon) {
        squareLabel[x][y].setIcon(new ImageIcon(GamePanel.class.getClass()
                .getResource(icon)));
    }
}
