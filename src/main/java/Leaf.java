import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Leaf extends JPanel {

    int xSize;
    int ySize;
    int valueOfLeaf;
    boolean isThereSnail;
    Color[] colors = new Color[11];
    /*SETTERS AND GETTERS */
    public int getxSize() {
        return xSize;
    }
    public int getySize() {
        return ySize;
    }
    public int getValueOfLeaf() {
        return valueOfLeaf;
    }
    public boolean isThereSnail() {
        return isThereSnail;
    }
    public void setxSize(int xSize) {
        this.xSize = xSize;
    }
    public void setySize(int ySize) {
        this.ySize = ySize;
    }
    public void setValueOfLeaf(int valueOfLeaf) {
        this.valueOfLeaf = valueOfLeaf;
    }
    public void setIsThereSnail(boolean thereSnail) {
        isThereSnail = thereSnail;
    }


    public Leaf() {
        colors[1] =  new Color(204, 255, 204); //
        colors[2] =  new Color(153, 255, 153); //2
        colors[3] =  new Color(102, 255, 102); //3
        colors[4] =  new Color(0, 255, 0);
        colors[5] =  new Color(51, 204, 51);
        colors[6] =  new Color(0, 204, 0);
        colors[7] =  new Color(0, 153, 0);
        colors[8] =  new Color(0, 120, 0);
        colors[9] =  new Color(0, 70, 0);
        colors[10] =  new Color(0, 30, 0);


        this.isThereSnail = false;
        this.setSize(40,40);        //ustawianie wymiarów powinno byc potem powiazane z liczba lisci
        Random random = new Random();
        int randomGreen = Math.abs(random.nextInt()%11);
        this.setBackground(colors[randomGreen]);

        this.valueOfLeaf = Math.abs(random.nextInt()%11);       // przypisuje wartość

    }

    public void showSnail(){
        setIsThereSnail(true);
        repaint();
    }

    public void hideSnail(){
        setIsThereSnail(false);
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D comp2D = (Graphics2D) g;
        if(isThereSnail){
            comp2D.setColor(new Color(250,0,0));
            comp2D.fillOval(5,5,10,10);
        }
        setBackground(colors[valueOfLeaf]);
        comp2D.dispose();
    }

    public void update(Graphics g){
        super.paintComponent(g);
    }






}
