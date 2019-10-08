import javax.swing.JFrame;

public class Driver {

    public static void main(String[] args) throws InterruptedException {
        myFrame piet = new myFrame();
        piet.setTitle("C-curve Graphic");
        piet.setSize(1000,700);
        piet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        piet.setVisible(true);

    }

}