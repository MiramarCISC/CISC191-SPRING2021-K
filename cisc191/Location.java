//Ethan Delgleize
package edu.sdccd.cisc191;
import edu.sdccd.cisc191.gamestatemanager.Condition;
import edu.sdccd.cisc191.gamestatemanager.StateInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

//Drawing everything within the GameState
public class Location extends JPanel implements KeyListener{
    int x= 450 , y = 905;
    int locationID = 1;
    Random rng = new Random();
    int randomEncounter;
    private boolean paused;
    private StateInterface state;


    public Location() {
        paused = false;
        setSize(500, 500);
        addKeyListener(this);

    }
    public void paint(Graphics g) {
        Color myGrey = new Color(152, 158, 154);
        Color myGreen = new Color(74, 200, 79);
        Color myForestGreen = new Color(10, 163, 48);
        Color myBrown = new Color(130, 92, 22);

        this.removeAll();
        this.revalidate();
        this.repaint();


        switch (locationID) {
            // Plains area
            case 1:

//                this.removeAll();
//                this.revalidate();
//                this.repaint();
                //this.setBackground(myGreen);
                g.setColor(myGreen);
                g.fillRect(0,0,1000,1000);

                g.setColor(Color.BLACK);
                g.drawString("Plains", 30,900 );
                if (x != 450 && y!= 905)
                {
                    //randomEncounter = rng.nextInt(6);
                    if(randomEncounter == 5)
                    {
                        battlePause();
                    }

                }
//                this.removeAll();
//                this.revalidate();
//                this.repaint();
                break;
            // Forest area
            case 2:
                //this.setBackground(myGreen);
                g.setColor(myGreen);
                g.fillRect(0,0,1000,1000);

                g.setColor(myBrown);
                for(int i =100;i<1000;i+=200){
                    for(int ii =100;ii<1000;ii+=200) {
                        g.fillRect(i, ii, 10, 50);
                    }
                }

                g.setColor(myForestGreen);
                int npoints = 3;
                for(int i =75;i<1000;i+=200){
                    for(int ii =120;ii<1000;ii+=200) {
                        int xpoints[] = {i,i+30,i+60};
                        int ypoints[] = {ii, ii-45, ii};
                        g.fillPolygon(xpoints, ypoints, npoints);
                    }
                }

                g.setColor(Color.BLACK);
                g.drawString("Woods", 30,900 );
                if (x != 450 && y!= 905)
                {
                    //randomEncounter = rng.nextInt(6);
                    if(randomEncounter == 5)
                    {
                        battlePause();
                    }

                }

                break;
            case 3:
                //Castle
                //this.setBackground(myGrey);
                g.setColor(myGrey);
                g.fillRect(0,0,1000,1000);


                    g.setColor(Color.BLACK);
                    for(int cX = 0; cX < 1000; cX += 50 ) {
                        for(int cY = 20; cY< 1000; cY+= 50) {
                            g.drawRect(cX, cY, 50, 50);
                        }
                    }
                if(x==450 && y==225){
                    g.drawString(" BOSS FIGHT", 400,450);
                    battlePause();
                }
                if (x != 450 && y!= 905)
                {
                    //randomEncounter = rng.nextInt(6);
                    if(randomEncounter == 5)
                    {
                        battlePause();
                    }

                }
                g.drawString("Demon Kings Castle", 30,900 );

                g.setColor(Color.blue);
                g.fillOval(450, 225, 40, 40);

                break;
        }

        g.setColor(Color.BLACK);
        g.drawString("x = " + x + " y= " + y, 40, 45);
        g.setColor(Color.CYAN);
        g.fillOval(x, y, 25, 25);
//        removeAll();
//        revalidate();
//        repaint();
    }
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        switch (keyCode)
        {
            case KeyEvent.VK_UP:

            case KeyEvent.VK_W:
                if(!paused) {
                    randomEncounter = rng.nextInt(6);
                    if (y <= 25) {
                        if (locationID != 3) {
                            locationID++;
                            y = 975;
                        }
                    } else
                        y = y - 50;
                }
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if(!paused) {
                    randomEncounter = rng.nextInt(6);
                    if (y >= 900) {
                        if (locationID != 1) {
                            locationID--;
                            y = 5;
                        }
                    } else
                        y = y + 50;
                }
                break;

            case KeyEvent.VK_LEFT:

            case KeyEvent.VK_A:
                if(!paused) {
                    randomEncounter = rng.nextInt(6);
                    if (x > 0)
                        x = x - 50;
                }
                break;

            case KeyEvent.VK_RIGHT:

            case KeyEvent.VK_D:
                if(!paused) {
                    randomEncounter = rng.nextInt(6);
                    if (x < 950)
                        x = x + 50;
                }
                break;

            case KeyEvent.VK_ESCAPE://pause menu
                if(!paused) {
                    togglePause();
                }
        }

    }
    public void keyTyped(KeyEvent ke) {
    }
    public void keyReleased(KeyEvent ke) {

    }

    public int getLocationID(){
        return locationID;
    }

    public boolean getPause(){
        return paused;
    }

    public void setState(StateInterface s){
        state = s;
    }

    public void togglePause(){
        if(paused){
            paused = false;
            this.state.setSelection(Condition.EXIT);
            this.state.exit();
        }
        else{
            paused = true;
            this.state.setSelection(Condition.PAUSE);
            this.state.exit();
        }
    }

    public void battlePause(){
        if(paused){
            paused = false;
            randomEncounter = rng.nextInt(6);
            this.state.exit();
        }
        else{
            paused = true;
            this.state.setSelection(Condition.BATTLE);
            this.state.exit();
        }
    }

    public boolean checkBoss() {
        if(locationID == 3 && x == 450 && y == 225)
            return true;
        return false;
    }

}
