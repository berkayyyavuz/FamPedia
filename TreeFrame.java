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
        loadTree.addActionListener(e -> warning());  // to load a tree
        mergeTree.addActionListener(e -> warning()); // to merge trees
        exit.addActionListener(e -> exit()); // to exit
        guideline.addActionListener(e -> openWebPage("https://github.com/berkayyyavuz/FamPedia/wiki/Fampedia"));
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

        /*CreateTree.exportBtn.addActionListener(e1 -> {
            FileManager fileManager = new FileManager(this, createTree);
            fileManager.saveFunction();
        });*/
    }
   /* public void setMergedTree(){
        setTitle("Merge Trees");
        JPanel panel = new JPanel();
        JButton mergeBtn = new JButton("Load Tree");
        panel.add(mergeBtn);
        scrollPane.setViewportView(panel);

        mergeBtn.addActionListener(e -> {});
    }
    public void setLoadedTree(){
        setTitle("Load A Tree");
        JPanel panel = new JPanel();
        JButton loadBtn = new JButton("Load Tree");
        panel.add(loadBtn);
        scrollPane.setViewportView(panel);

        loadBtn.addActionListener(e -> {});
    }
*/
    // terminates the program
    public void exit(){

        System.exit(0);
    }

    public void openWebPage(String url){
        try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }
        catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }
    public void aboutProgram() { // menu item
        JDialog help = new JDialog();
        JOptionPane.showMessageDialog(help, "The application helps the user to create and edit their own custom family tree. \n" +
                "It allows you to establish close relationships with ancestors and family members whom you may not know which family ties you have. \n" +
                "With this application, you can easily understand what kind of ancestry you come from and which culture you are more inclined to. \n" +
                "It is intended to be useful for all users." +
                "\nCreated by Ece IŞIK & Sinan YAŞBEK & Suphi Kadir ÖZARPACI & Berkay YAVUZ");
    }
    public void warning(){
        JDialog help = new JDialog();
        JOptionPane.showMessageDialog(help, "This section is under development. Coming soon!");
    }
}
