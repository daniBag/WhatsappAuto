package org.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextFieldWithLengthLimit extends JTextArea {

    public TextFieldWithLengthLimit(int x, int y, int width, int height, int maxLength){
        this.setBounds(x, y, width, height);
        this.setLineWrap(true);
        this.setWrapStyleWord(true);
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (getText().length() >= maxLength){
                    e.consume();
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        this.setTransferHandler(new TransferHandler(){
            public void paste(JComponent component){

            }
        });
    }
}
