import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {

    public GUI() {



        JFrame frame = new JFrame();

        ImageIcon icon = new ImageIcon("src/logo.png");

        JTextField textField = new JTextField("Enter Family Tree Name");

        // Main Title
        JLabel label = new JLabel("Welcome to FamPedia", JLabel.CENTER);

        // Subtitle
        JLabel label2 = new JLabel("Create Family Tree", JLabel.CENTER);

        // Header Title Font and Color Settings
        label.setFont(new Font("Helvatica", Font.BOLD, 54));
        label2.setFont(new Font("Helvatica", Font.PLAIN, 34));
        label.setForeground(new Color(0x111111));
        label2.setForeground(new Color(0x999999));

        // Create Family Button
        JLabel iconLabel = new JLabel(icon);
        JButton button = new JButton("Create New Family Tree");

        JButton button2 = new JButton("Load Previous Family Tree");

        JButton close = new JButton("EXIT");


        // Change Button Style <NEED MORE WORK>
        button.setFont(new Font("Helvatica", Font.PLAIN, 20));
        button2.setFont(new Font("Helvatica", Font.PLAIN, 20));
        close.setFont(new Font("Helvatica", Font.PLAIN, 20));
        button.setForeground(new Color(0x333333));
        button2.setForeground(new Color(0x333333));
        close.setForeground(new Color(0xF33334));


        // Putting All the Components Together in a Panel
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(200, 400, 400, 400));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(iconLabel);
        panel.add(label);
        panel.add(label2);
        panel.add(textField);
        panel.add(button);
        panel.add(button2);
        panel.add(close);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Welcome to the FamPedia");
        frame.pack();
        frame.setVisible(true);





    }


}
