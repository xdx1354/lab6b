import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class MyFrame extends JFrame {

    public MyFrame(int x, int y, int numberOfSnails) {
        this.setSize(40 * y, 40 * x);
        LeavesPanel leavesPanel = new LeavesPanel(x,y,numberOfSnails);
        this.add(leavesPanel);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        for(int i=1; i<=numberOfSnails; i++){
            Thread snailThread = new Thread(
                    new Runnable() {
                        @Override
                        public void run() {

                        }
                    }
            );
        }

    }



}

