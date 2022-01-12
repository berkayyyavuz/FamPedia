import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

public class CreateTree extends JPanel {
    JPanel newMemberPanel, treePanel;
    JSplitPane splitPane;
    JTextField txt1, txt2, txt3, txt4, txt5;
    JRadioButton btn1, btn2, btn3, btn4, btn5, btn6;
    Tree tree;
    Member ancient;

    public void setMainPanel() {
        splitPane = new JSplitPane();
        ancient = new Member();
        tree = new Tree(ancient);
        treePanel = new JPanel();
        treePanel.setPreferredSize(new Dimension(500,500));
        setNewMemberPanel();
        splitPane.setLeftComponent(newMemberPanel);
        splitPane.setRightComponent(treePanel);
        add(splitPane);

    }

    public void setNewMemberPanel() {
        newMemberPanel = new JPanel(new GridLayout(20, 0));

        //labels
        JLabel lbl1 = new JLabel("Name:");
        JLabel lbl2 = new JLabel("BirthYear:");
        JLabel lbl3 = new JLabel("DeathYear:");
        JLabel lbl4 = new JLabel("Gender:");
        JLabel lbl5 = new JLabel("Marriage Status:");
        JLabel lbl6 = new JLabel("Live Status:");
        JLabel lbl7 = new JLabel("Father Name:");
        JLabel lbl8 = new JLabel("Mother Name:");
        //text fields
        txt1 = new JTextField(15);
        lbl1.setLabelFor(txt1);
        txt2 = new JTextField(15);
        lbl2.setLabelFor(txt2);
        txt3 = new JTextField(15);
        txt3.setEditable(false); // first, we must know if the member is dead
        lbl3.setLabelFor(txt3);
        txt4 = new JTextField(15);
        lbl7.setLabelFor(txt4);
        txt5 = new JTextField(15);
        lbl8.setLabelFor(txt5);

        //radio buttons
        btn1 = new JRadioButton("Male");
        btn2 = new JRadioButton("Female");
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(btn1);
        buttonGroup1.add(btn2);

        btn3 = new JRadioButton("Alive");
        btn4 = new JRadioButton("Dead");
        ButtonGroup buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(btn3);
        buttonGroup2.add(btn4);

        btn5 = new JRadioButton("Married");
        btn6 = new JRadioButton("Unmarried");
        ButtonGroup buttonGroup3 = new ButtonGroup();
        buttonGroup3.add(btn5);
        buttonGroup3.add(btn6);


        // save button
        JButton saveButton = new JButton("Save");

        // add components into the panel
        newMemberPanel.add(lbl4);
        newMemberPanel.add(btn1);
        newMemberPanel.add(btn2); // gender components
        newMemberPanel.add(lbl1);
        newMemberPanel.add(txt1); // name components
        newMemberPanel.add(lbl2);
        newMemberPanel.add(txt2); // birth year components
        newMemberPanel.add(lbl5);
        newMemberPanel.add(btn5);
        newMemberPanel.add(btn6); // marriage status components
        newMemberPanel.add(lbl6);
        newMemberPanel.add(btn3);
        newMemberPanel.add(btn4); // live status components
        newMemberPanel.add(lbl3);
        newMemberPanel.add(txt3); // death year components
        newMemberPanel.add(lbl7);
        newMemberPanel.add(txt4); // father components
        newMemberPanel.add(lbl8);
        newMemberPanel.add(txt5); // mother components
        newMemberPanel.add(saveButton);

        btn4.addActionListener(e -> txt3.setEditable(true));
        btn3.addActionListener(e -> txt3.setEditable(false));
        newMemberPanel.revalidate();

        saveButton.addActionListener(e ->{
            createMember();
            setComponentsNull();
            treePanel = new JPanel();
            treePanel.add(new PrintTree(tree));
            treePanel.revalidate();
        });
    }

    // get inputs to create a new member
    public void createMember(){
        Member member = new Member();
        try {
            if (btn1.isSelected()) // set gender type male
                member.setGender(GenderType.male);
            if (btn2.isSelected()) // set gender type female
                member.setGender(GenderType.female);
            member.setName(txt1.getText());  // set name
            member.setBirthYear(Integer.parseInt(txt2.getText()));  // set birth year
            if (btn5.isSelected())  // set marriage status as married
                member.setIsMarried(MarriageState.married);
            if (btn6.isSelected())  // set marriage status as unmarried
                member.setIsMarried(MarriageState.unmarried);
            if (btn3.isSelected())  // set if person is alive
                member.setIsAlive(LiveState.alive);
            if (btn4.isSelected()) {  // set if person is died
                member.setIsAlive(LiveState.died);
                member.setDeathYear(Integer.parseInt(txt3.getText())); // set death year
            }

            tree.searchParent(txt4.getText(), txt5.getText(), member); // set mother and father
            if(member.getFather() != null)  // set generation
                member.setGeneration(member.getFather().getGeneration()+1);
            else {
                member.setGeneration(1);
                tree.setRoot(member);
            }
            ancient.getMembers().add(member);
            tree.insert(member); // insert into tree
            System.out.println(member.toString());

        }catch (NumberFormatException e){ // check if properties are valid
            JDialog warning = new JDialog();
            JOptionPane.showMessageDialog(warning, "Please enter a valid input!");
            System.err.println("Year(s) have been written in a wrong format!");
            setComponentsNull();
        }
    }

    // set components to null for adding new members
    public void setComponentsNull() {

        txt1.setText("");
        txt2.setText("");
        txt3.setText("");
        txt4.setText("");
        txt5.setText("");
    }


}
class PrintTree extends JPanel implements TreeSelectionListener {

    private JTree tree;
    private JEditorPane htmlPane;
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    private static boolean useSystemLookAndFeel = false;
    private static boolean DEBUG = false;

    Tree familyTree;

    public PrintTree(Tree familyTree) {
        super(new GridLayout(1,0));

        this.familyTree = familyTree;

        //Create the nodes.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(familyTree.getRoot().getName());
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }
        JScrollPane treeView = new JScrollPane(tree);

        //Create the HTML viewing pane.
        htmlPane = new JEditorPane();
        htmlPane.setEditable(false);
        JScrollPane htmlView = new JScrollPane(htmlPane);

        //Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setTopComponent(treeView);
        splitPane.setBottomComponent(htmlView);

        Dimension minimumSize = new Dimension(100, 50);
        htmlView.setMinimumSize(minimumSize);
        treeView.setMinimumSize(minimumSize);
        splitPane.setDividerLocation(100);
        splitPane.setPreferredSize(new Dimension(500, 300));

        //Add the split pane to this panel.
        add(splitPane);
    }
    private void display(String info) {
        try {
            if (info != null) {
                htmlPane.setPage(info);
            } else { //null url
                htmlPane.setText("File Not Found");
                if (DEBUG) {
                    System.out.println("Attempted to display a null content");
                }
            }
        } catch (IOException e) {
            System.err.println("Attempted to read a bad content: " + info);
        }
    }
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
            Person person = (Person) nodeInfo;
            display(person.info);
        }
        if (DEBUG) {
            System.out.println(nodeInfo.toString());
        }
    }

    private class Person {
        public String personName;
        public String info;

        public Person(Member member) {
            personName = member.getName();
            info = member.toString();
        }

        @Override
        public String toString() {
            return personName;
        }
    }
    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode person = null;

        Member member = familyTree.getRoot(); // initially
        for (int j=0;j<Member.generationCount;j++) {
            if (member.getSpouse() != null) {
                // print spouse
                category = new DefaultMutableTreeNode("Spouse");
                top.add(category);
                person = new DefaultMutableTreeNode(new Person(member.getSpouse()));
                category.add(person);
            }
            // print children
            if (member.getChildren().size() != 0) {
                category = new DefaultMutableTreeNode("Children");
                top.add(category);
                for (int i = 0; i < member.getChildren().size(); i++) {
                    person = new DefaultMutableTreeNode(new Person(member.getChildren().get(i)));
                    category.add(person);
                    member = member.getChildren().get(i);

                }
            }
            top=person;
        }
    }
}

