import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) {
        
        /*
        // Create a new JFrame container with a title
        JFrame frame = new JFrame("FamPedia - Family Tree Program");
        frame.setVisible(true);

        // Set default size of window
        frame.setSize(1600, 1200);

        // Specify what happens when the close button is clicked
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Prevent frame from being resized
        frame.setResizable(false);

        frame.getContentPane().setBackground(new Color(0xeeeeee));
        */


        JLabel label = new JLabel("Welcome to FamPedia");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setForeground(new Color(0x111111));
        label.setFont(new Font("Helvetica", Font.BOLD, 48));


        // Create a new JFrame container with a title
        MyFrame mainFrame = new MyFrame();
        mainFrame.add(label);







    }





}
