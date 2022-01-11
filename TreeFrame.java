import javax.swing.*;
import java.awt.*;

public class TreeFrame extends JFrame {
    JScrollPane scrollPane;
    public static void main(String[] args) {
        new TreeFrame();
    }

    public TreeFrame(){
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("FamPedia");
        setLayout(new BorderLayout());
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        scrollPane = new JScrollPane();
        add(scrollPane);
        add(menuBar(), BorderLayout.NORTH);
        setVisible(true);
    }

    public JMenuBar menuBar(){

        JMenuBar menuBar = new JMenuBar(); // menu bar

        //add some menu options into menu bar
        JMenu menuOperations = new JMenu("Tree");
        JMenu menuHelp = new JMenu("Help");
        menuBar.add(menuOperations);
        menuBar.add(menuHelp);

        //add menu items of Operations menu
        JMenuItem createTree = new JMenuItem("Create new tree");
        JMenuItem loadTree = new JMenuItem("Load a tree");
        JMenuItem mergeTree = new JMenuItem("Merge trees");
        JMenuItem exit = new JMenuItem("Exit");
        menuOperations.add(createTree);
        menuOperations.add(loadTree);
        menuOperations.add(mergeTree);
        menuOperations.add(exit);

        //add menu items of Help menu
        JMenuItem guideline = new JMenuItem("Guideline");
        JMenuItem about = new JMenuItem("About Program");
        menuHelp.add(guideline);
        menuHelp.add(about);

        //add item listeners for menu items
        createTree.addActionListener(e -> setCreateTree()); // to create new tree
        loadTree.addActionListener(e -> setLoadedTree());  // to load a tree
        mergeTree.addActionListener(e -> setMergedTree()); // to merge trees
        exit.addActionListener(e -> exit()); // to exit
        about.addActionListener(e -> aboutProgram()); // about program

        return menuBar;
    }

    public void setCreateTree(){
        CreateTree createTree = new CreateTree(); // creates an object of CreateTree class
        setTitle("Create New Tree");
        createTree.setMainPanel();
        scrollPane.setViewportView(createTree);

        scrollPane.repaint();
        revalidate();
    }
    public void setMergedTree(){setTitle("Merge Trees");}
    public void setLoadedTree(){setTitle("Load A Tree");}

    // terminates the program
    public void exit(){
        System.exit(0);
    }

    public void aboutProgram() { // menu item
        JDialog help = new JDialog();
        JOptionPane.showMessageDialog(help, "Created by \nEce IŞIK & \nSinan YAŞBEK & \nKadir Suphi ÖZARPACI & \nBerkay YAVUZ");
    }
}
