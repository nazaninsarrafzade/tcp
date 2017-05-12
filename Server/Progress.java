package Server; /**
 * Created by nazanin-sarrafzadeh on 5/12/2017.
 */
import javax.swing.*;
import java.awt.*;

public class Progress extends JFrame{
    public void progress(String[] args) {
        final int MAX = 100;
        final JFrame frame = new JFrame("JProgress Demo");

        // creates progress bar
        final JProgressBar pb = new JProgressBar();
        pb.setMinimum(0);
        pb.setMaximum(MAX);
        pb.setStringPainted(true);

        // add progress bar
        frame.setLayout(new FlowLayout());
        frame.getContentPane().add(pb);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);

        // update progressbar
        for (int i = 0; i <= MAX; i++) {
            final int currentValue = i;
            try {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        pb.setValue(currentValue);
                    }
                });
                java.lang.Thread.sleep(100);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }
        }

    }
//    JProgressBar jb;
//    int i=0,num=0;
//    ProgressBarExample(){
//        jb=new JProgressBar(0,2000);
//        jb.setBounds(40,40,160,30);
//        jb.setValue(0);
//        jb.setStringPainted(true);
//        add(jb);
//        setSize(250,150);
//        setLayout(null);
//    }
//    public void iterate(){
//        while(i<=2000){
//            jb.setValue(i);
//            i=i+20;
//            try{Thread.sleep(150);}catch(Exception e){}
//        }
//    }
//    public static void main(String[] args) {
//        ProgressBarExample m=new ProgressBarExample();
//        m.setVisible(true);
//        m.iterate();
//    }
}