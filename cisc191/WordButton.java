// Christopher Diep
package edu.sdccd.cisc191;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/*
this is a custom JLabel that has input functionality and is used to create the buttons on the main MenuPanel object
 */

public class WordButton extends JLabel{

    private final ArrayList<ActionListener> actionListeners;
    private final Font RESTINGVERANDA = new Font("Unispace", 1, 40);
    private final Font ACTIVEVERANDA = new Font("Unispace", 1, 42);
    private final Color RESTINGCOLOR = Color.decode("#222a35");
    private final Color ACTIVECOLOR = Color.decode("#FFD966");//
    private final LineBorder RESTINGBORDER = new LineBorder(RESTINGCOLOR);
    private String filename;

    public WordButton(String s){
        super(s, SwingConstants.CENTER);
        this.filename = s;
        this.setFont(RESTINGVERANDA);
        this.setForeground(RESTINGCOLOR);
        this.setBorder(RESTINGBORDER);

        actionListeners = new ArrayList();  //this is the arraylist of actionlisteners that can be applied to any
        //particular WordButton

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //upon mouse click, all action listeners are triggered
                WordButton.this.performAction();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                enter();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exit();
            }
        });
    }
    //do all the actions defined to this button
    public void performAction(){
        for(int i = 0; i< actionListeners.size(); i++){
            actionListeners.get(i).actionPerformed(new ActionEvent(this,0,"Click"));
        }
    }

    public void addActionListener(ActionListener al){
        this.actionListeners.add(al);
    }

    public void enter(){
        this.setFont(ACTIVEVERANDA);
        this.setForeground(this.ACTIVECOLOR);
    }

    public void exit(){
        this.setFont(RESTINGVERANDA);
        this.setForeground(this.RESTINGCOLOR);
    }
    public String getFilename(){
        return filename;
    }
}