import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel {
    private int[][] board;
    GameManager gameManager;


    public Board(int[][] boardArray, int x, int y) {
        setPreferredSize(new Dimension(x, y));
        this.board = boardArray;
        this.gameManager = new GameManager(this::repaint, this.board, x, y);


        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                gameManager.handleMouseClick(me.getX(), me.getY());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int size = Math.min(getWidth(), getHeight());
        int cellSize = size / 3;
        g.setColor(Color.black);
        for (int i = 1; i < 3; i++) {
            g.drawLine(0, i * cellSize, size, i * cellSize);
            g.drawLine(i * cellSize, 0, i * cellSize, size);
        }
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                int value = board[row][column];
                int x = column * cellSize;
                int y = row * cellSize;
                if (value == 1) {
                    drawX(g, x, y, cellSize);
                } else if (value == 2) {
                    drawO(g, x, y, cellSize);
                }
            }
        }
    }

    private void drawX(Graphics g, int x, int y, int size) {
        g.setColor(Color.red);
        g.drawLine(x + 10, y + 10, x + size - 10, y + size - 10);
        g.drawLine(x + 10, y + size - 10, x + size - 10, y + 10);
    }

    private void drawO(Graphics g, int x, int y, int size) {
        g.setColor(Color.blue);
        g.drawOval(x + 10, y + 10, size - 20, size - 20);
    }

}
