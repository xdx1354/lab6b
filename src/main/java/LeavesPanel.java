import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class LeavesPanel extends JPanel {

    int speedOfEating = 500;
    int sleepingTime = 750;
    int leavesGrowingTime = 10000;
    int x;
    int y;
    int numberOfSnails;
    Leaf[][] squares;
    ArrayList <Snail> snailsList = new ArrayList<>();
    ArrayList <Thread> threads = new ArrayList<>();

    LeavesPanel(int x, int y, int numberOfSnails) {
        this.x = x;
        this.y = y;
        this.numberOfSnails = numberOfSnails;
        squares = new Leaf[x][y];
        this.setLayout(new GridLayout(x, y));
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Leaf leaf = new Leaf();
                leaf.setIsThereSnail(false);
                squares[i][j] = leaf;
                this.add(leaf);
            }
        }
        generateSnails();

        //LEAVES GROWING THREAD
        Thread t = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Thread.currentThread().setName("Leaves growing thread");
                        while (true) {
                            System.out.println("Growing leaves...");
                            growLeaves();
                            try {
                                Thread.sleep(leavesGrowingTime);
                            } catch (InterruptedException e) {
                                System.out.println("Exception: " + e.getMessage());
                            }
                            System.out.println("Growing leaves...");
                        }

                    }
                }
        );
        t.start();

        //SNAILS THREAD - eating and movement
        int counter=0;
        for(Snail s:snailsList){
            Thread snailThread = new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            while (true) {

                                    if (eat(s.xPos, s.yPos)) {
                                        squares[s.xPos][s.yPos].repaint();
                                        try {
                                            Thread.sleep(speedOfEating);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else{
                                        Random rand = new Random();
                                        int directionOfMovement = Math.abs(rand.nextInt() % 8); // numeracja zgodnie z ruchem wskazówek zegara
                                        while (!isMoveLegal(s.xPos, s.yPos, directionOfMovement)) {
                                            directionOfMovement = Math.abs(rand.nextInt() % 8);
                                        }
                                        System.out.println("Moving:" + Thread.currentThread().getName());
                                        int xOld = s.getxPos();
                                        int yOld = s.getyPos();
                                        squares[s.getxPos()][s.getyPos()].setIsThereSnail(false);
                                        squares[s.getxPos()][s.getyPos()].repaint();
                                        switch (directionOfMovement) {
                                            case 0: {    //x, y+1
                                                s.setxPos(xOld);
                                                s.setyPos(yOld + 1);
                                                break;
                                            }
                                            case 1: {    //x+1,y+1
                                                s.setxPos(xOld + 1);
                                                s.setyPos(yOld + 1);
                                                break;
                                            }
                                            case 2: {    //x+1, y
                                                s.setxPos(xOld + 1);
                                                s.setyPos(yOld);
                                                break;
                                            }
                                            case 3: {    //x+1, y-1
                                                s.setxPos(xOld + 1);
                                                s.setyPos(yOld - 1);
                                                break;
                                            }
                                            case 4: {    //x, y-1
                                                s.setxPos(xOld);
                                                s.setyPos(yOld - 1);
                                                break;
                                            }
                                            case 5: {    //x-1, y-1
                                                s.setxPos(xOld - 1);
                                                s.setyPos(yOld - 1);
                                                break;
                                            }
                                            case 6: {    //x-1, y
                                                s.setxPos(xOld - 1);
                                                s.setyPos(yOld);
                                                break;
                                            }
                                            case 7: {    //x-1, y+1
                                                s.setxPos(xOld - 1);
                                                s.setyPos(yOld + 1);

                                                break;
                                            }
                                        }
                                        squares[s.getxPos()][s.getyPos()].setIsThereSnail(true);
                                        squares[s.getxPos()][s.getyPos()].repaint();

                                        try {
                                            Thread.sleep(sleepingTime);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }

                            }
                        }
                    }
            );
            snailThread.setName("Snail number: " + counter);
            counter++;
            snailThread.start();
        }

    }

    private synchronized boolean isMoveLegal(int xOld, int yOld, int randDirection){
        //0 x++ y
        //1 x++, y++
        //
        switch (randDirection){
            case 0:{    //x, y+1
                int xNew = xOld;
                int yNew = yOld+1;
                if(0<=xNew && xNew<x && 0<=yNew && yNew<y && !squares[xNew][yNew].isThereSnail){
                    return !squares[xNew][yNew].isThereSnail;
                }
                break;
            }
            case 1:{    //x+1,y+1
                int xNew = xOld+1;
                int yNew = yOld+1;
                if(0<=xNew && xNew<x && 0<=yNew && yNew<y && !squares[xNew][yNew].isThereSnail){
                    return !squares[xNew][yNew].isThereSnail;
                }
                break;
            }
            case 2:{ //x+1, y
                int xNew = xOld+1;
                int yNew = yOld;
                if(0<=xNew && xNew<x && 0<=yNew && yNew<y && !squares[xNew][yNew].isThereSnail){
                    return !squares[xNew][yNew].isThereSnail;
                }
                break;
            }
            case 3:{ //x+1, y-1
                int xNew = xOld+1;
                int yNew = yOld-1;
                if(0<=xNew && xNew<x && 0<=yNew && yNew<y && !squares[xNew][yNew].isThereSnail){
                    return !squares[xNew][yNew].isThereSnail;
                }
                break;
            }
            case 4:{ //x, y-1
                int xNew = xOld;
                int yNew = yOld-1;
                if(0<=xNew && xNew<x && 0<=yNew && yNew<y && !squares[xNew][yNew].isThereSnail){
                    return !squares[xNew][yNew].isThereSnail;
                }
                break;
            }
            case 5:{ //x-1, y-1
                int xNew = xOld-1;
                int yNew = yOld-1;
                if(0<=xNew && xNew<x && 0<=yNew && yNew<y && !squares[xNew][yNew].isThereSnail){
                    return !squares[xNew][yNew].isThereSnail;
                }
                break;
            }
            case 6:{ //x-1, y
                int xNew = xOld-1;
                int yNew = yOld;
                if(0<=xNew && xNew<x && 0<=yNew && yNew<y && !squares[xNew][yNew].isThereSnail){
                    return !squares[xNew][yNew].isThereSnail;
                }
                break;
            }
            case 7:{ //x-1, y+1
                int xNew = xOld-1;
                int yNew = yOld+1;
                if(0<=xNew && xNew<x && 0<=yNew && yNew<y && !squares[xNew][yNew].isThereSnail){
                    return !squares[xNew][yNew].isThereSnail;
                }
                break;
            }
        }
        return false;
    }

    public void generateSnails() {
        Random rand = new Random();
        for (int i = 0; i < numberOfSnails; i++) {
            boolean empty = true;
            while (empty) {
                int tempX = Math.abs(rand.nextInt() % x);
                int tempY = Math.abs(rand.nextInt() % y);
                if (!squares[tempX][tempY].isThereSnail) {
                    squares[tempX][tempY].setIsThereSnail(true);
                    snailsList.add(new Snail(i,tempX,tempY));
                    squares[tempX][tempY].repaint();
                    empty = false;
                    System.out.println("Snail added");
                }
            }
        }

    }

    public void moveSnail(int oldX, int oldY, int newX, int newY){
        squares[oldX][oldY].hideSnail();
        squares[newX][newY].showSnail();
    }

    public boolean eat(int currentX, int currentY){ //zwraca true gdy sie udało i false gdy nie
        if(squares[currentX][currentY].valueOfLeaf!=0){
            squares[currentX][currentY].valueOfLeaf--;
            squares[currentX][currentY].repaint();
            return true;
        }
        squares[currentX][currentY].repaint();
        return false;
    }

    private synchronized void growLeaves(){
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                int temp = squares[i][j].valueOfLeaf;
                if(temp<10){
                    //temp++;
                    squares[i][j].valueOfLeaf++;
                    //squares[i][j].setBackground(squares[i][j].colors[squares[i][j].valueOfLeaf]); // dlaczego to samo wywołuje repaint Liścia?
                    squares[i][j].repaint();
                }
                //System.out.println("valeue of{ " + i + " ," + j +"}: " + squares[i][j].valueOfLeaf);
                //System.out.println("Color of{ " + i + " ," + j +"}: " + squares[i][j].colors[squares[i][j].valueOfLeaf]);
            }
        }


    }

}

