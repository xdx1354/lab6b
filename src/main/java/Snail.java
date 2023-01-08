import javax.swing.plaf.TableHeaderUI;

public class Snail implements Runnable {
    int id;
    int xPos;
    int yPos;
    //Thread snailThread = new Thread();

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public Snail(int id, int x, int y) {
        this.id = id;
        this.xPos = x;
        this.yPos = y;
    }

    public int getId() {
        return id;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void move(){

    }


    @Override
    public void run() {


    }
}
