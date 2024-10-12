import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        int [][] array = new int [3][3];
        JFrame jframe = new JFrame("Window");
        Board board = new Board(array, 600, 600);

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(board);
        jframe.pack();
        jframe.setVisible(true);

        try {
            Thread.sleep(1000);
        } catch (java.lang.InterruptedException e) {

        }



    }
}
