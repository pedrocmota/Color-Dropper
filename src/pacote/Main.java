package pacote;

import static pacote.Frame.frame;

public class Main {
    
    public static void main(String args[]) throws Exception {
        javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        frame = new Frame();
        frame.setVisible(true);
    }

}
