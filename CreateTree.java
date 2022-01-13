import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class CreateTree extends JPanel {
    JPanel newMemberPanel, treePanel;
    JSplitPane splitPane;
    JTextField txt1, txt2;
    JRadioButton btn1, btn2;
    JComboBox boxMembers, boxRelations;
    ArrayList<Member> members;

    public void setMainPanel() {
        splitPane = new JSplitPane();
        treePanel = new JPanel();
        treePanel.setPreferredSize(new Dimension(500,500));
        setNewMemberPanel();
        splitPane.setLeftComponent(newMemberPanel);
        splitPane.setRightComponent(treePanel);
        add(splitPane);
        members = new ArrayList<>();
    }

    public void setNewMemberPanel() {
        newMemberPanel = new JPanel(new GridLayout(14, 0));

        //labels
        JLabel lbl1 = new JLabel("Name:");
        JLabel lbl2 = new JLabel("Gender:");
        JLabel lbl3 = new JLabel("Married With (Optional):");
        JLabel lbl4 = new JLabel("Select Member:");
        JLabel lbl5 = new JLabel("Select Relation:");

        //text fields
        txt1 = new JTextField(15);
        lbl1.setLabelFor(txt1);
        txt2 = new JTextField(15);
        lbl2.setLabelFor(txt2);

        //radio buttons
        btn1 = new JRadioButton("Male");
        btn2 = new JRadioButton("Female");
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(btn1);
        buttonGroup1.add(btn2);

        // comboBoxes
        boxMembers = new JComboBox();
        lbl4.setLabelFor(boxMembers);
        String[] relationStrings = { "None", "Mother", "Father", "Child", "Sibling" };
        boxRelations = new JComboBox(relationStrings);
        lbl5.setLabelFor(boxRelations);

        // save button
        JButton saveButton = new JButton("Add Member");
        JButton btnTree = new JButton("Create Your Family Tree");
        JButton exportBtn = new JButton("Export Tree");
        JButton photoBtn = new JButton("Take Photo");

        JPanel space = new JPanel(); // to create a space

        // add components into the panel
        newMemberPanel.add(lbl2);
        newMemberPanel.add(btn1);
        newMemberPanel.add(btn2); // gender components
        newMemberPanel.add(lbl1);
        newMemberPanel.add(txt1); // name components
        newMemberPanel.add(lbl3);
        newMemberPanel.add(txt2); // marriage components
        newMemberPanel.add(lbl4);
        newMemberPanel.add(boxMembers);
        newMemberPanel.add(lbl5);
        newMemberPanel.add(boxRelations);
        newMemberPanel.add(space);
        newMemberPanel.add(saveButton);
        newMemberPanel.add(space);
        newMemberPanel.add(btnTree);
        newMemberPanel.revalidate();

        // save button to add new members to the tree
        saveButton.addActionListener(e ->{
            createMember();
            setComponentsNull();
            newMemberPanel.revalidate();
            splitPane.revalidate();
            treePanel.revalidate();
        });

        btnTree.addActionListener(e -> {
            JPanel boxPanel = new JPanel();
            JPanel btnPanel = new JPanel();
            JPanel btnPanel1 = new JPanel();
            JPanel lblPanel = new JPanel();
            newMemberPanel = new JPanel(new GridLayout(4,0));
            JLabel lblExp = new JLabel("<html><body>Select a family <br>member to see <br>relations:</body></html>");
            lblExp.setFont(new Font("Arial", Font.ITALIC, 14));
            lblPanel.add(lblExp);
            boxPanel.add(boxMembers);
            btnPanel.add(exportBtn);
            btnPanel1.add(photoBtn);
            newMemberPanel.add(lblPanel);
            newMemberPanel.add(boxPanel);
            newMemberPanel.add(btnPanel);
            newMemberPanel.add(btnPanel1);

            // add a check box to center a member in the tree
            boxMembers.addActionListener(e1 -> {
                Member relative = new Member("", 'U');
                for(int i=0;i< members.size();i++){
                    if(boxMembers.getSelectedIndex() == i)
                        relative = members.get(i);
                }
                treePanel = new JPanel();
                // call PrintTree to show the tree at the point of view of selected member
                treePanel.add(new PrintTree(relative));
                Relation.listAllRelations(relative);
                splitPane.setRightComponent(treePanel);
                splitPane.revalidate();
                treePanel.revalidate();
            });

            splitPane.setLeftComponent(newMemberPanel);
            splitPane.revalidate();
            newMemberPanel.revalidate();
        });
    }

    // get inputs to create a new member
    public void createMember() {
        JLabel report = new JLabel(); // print the member info on screen
        report.setFont(new Font("Arial", Font.ITALIC, 16));
        JPanel lblPanel = new JPanel();
        lblPanel.setPreferredSize(new Dimension(500, 25));
        try {

            // checks if one of the fields is null
            if (txt1.getText().isEmpty()) throw new InvalidPropertyException("Name field cannot be null!");
            if (!(btn1.isSelected() || btn2.isSelected())) throw new InvalidPropertyException("None of the gender buttons are selected!");

            Member member = new Member("", 'U');
        if (btn1.isSelected()) member = new Member(txt1.getText(), 'M');
        if (btn2.isSelected()) member = new Member(txt1.getText(), 'F');
        members.add(member);

        report.setText(member.name + " is added.");
        lblPanel.add(report);
        treePanel.add(lblPanel);

        boxMembers.addItem(member.name);  // add member names into the combo box
        System.out.println(member.name + " is added.");

        // set spouse
        Member spouse = new Member("", 'U');
        if (!txt2.getText().isEmpty()) {  // set spouse if exists
            if (btn1.isSelected())
                spouse = new Member(txt2.getText(), 'F');
            if (btn2.isSelected())
                spouse = new Member(txt2.getText(), 'M');
            Relation.setSpouse(member, spouse);
            members.add(spouse);
            boxMembers.addItem(spouse.name);  // add member names into the combo box
            System.out.println(spouse.name + " is added.");
            report.setText(spouse.name + " is set as spouse of " + member.name);
            lblPanel.add(report);
            treePanel.add(lblPanel);
        }

        // get the selected member from combo box
        Member relative = new Member("", 'U');
        for (int i = 0; i < members.size(); i++) {
            if (boxMembers.getSelectedIndex() == i)
                relative = members.get(i);
        }

        // set relations
        int index = boxRelations.getSelectedIndex();
        System.out.println(index);
        switch (index) {
            case 1:  // mother
            case 2:  // father
                Relation.addParent(member, relative);
                report.setText(relative.name + " is set as a parent of " + member.name);
                lblPanel.add(report);
                treePanel.add(lblPanel);
                break;
            case 3:  // child
                Relation.addChild( member, relative);
                report.setText(relative.name + " is set as a child of " + member.name);
                lblPanel.add(report);
                treePanel.add(lblPanel);
                break;
            case 4:  // sibling
                Relation.addSibling(member, relative);
                report.setText(relative.name + " is set as a sibling of " + member.name);
                lblPanel.add(report);
                treePanel.add(lblPanel);
                break;
        }
    } catch (InvalidPropertyException exception) {
        System.out.println(exception.getMessage());
        JDialog help = new JDialog();
        JOptionPane.showMessageDialog(help, exception.getMessage());
    }
    }

    // set components to null for adding new members
    public void setComponentsNull() {
        txt1.setText("");
        txt2.setText("");
    }

}
class PrintTree extends JPanel implements TreeSelectionListener {

    private JTree tree;
    private JEditorPane htmlPane;
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    private static boolean useSystemLookAndFeel = false;
    private static boolean DEBUG = false;
    Member root;

    public PrintTree(Member root) {
        super(new GridLayout(1, 0));

        this.root = root;

        //Create the nodes.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode(root.name);
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
            } else {
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
            display(person.personName);
        }
        if (DEBUG) {
            System.out.println(nodeInfo.toString());
        }
    }

    private class Person {
        public String personName;

        public Person(Member member) {
            personName = member.name;
        }

        @Override
        public String toString() {
            return personName;
        }
    }

    public void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode category1 = null;
        DefaultMutableTreeNode person = null;
        DefaultMutableTreeNode person1 = null;
        DefaultMutableTreeNode person2 = null;

        Member member = root; // initially

        // print grandfathers
        if (Relation.listGrandFathers(root)) {
            category = new DefaultMutableTreeNode("GrandFathers");
            top.add(category);
            for (int i = 0; i < member.relation.getGrandFathers(root).size(); i++) {
                person = new DefaultMutableTreeNode(new Person(member.relation.getGrandFathers(root).get(i)));
                category.add(person);
                // print grandfather's spouse
                if (member.relation.getGrandFathers(root).get(i).relation.getSpouse() != null) {
                    category = new DefaultMutableTreeNode("Spouse");
                    person.add(category);
                    person1 = new DefaultMutableTreeNode(new Person(member.relation.getGrandFathers(root).get(i).relation.getSpouse()));
                    category.add(person1);
                }
                // print children
                if (member.relation.getGrandFathers(root).get(i).relation.getChildren().size() != 0) {
                    category = new DefaultMutableTreeNode("Children");
                    person.add(category);
                    for (int t = 0; t < member.relation.getGrandFathers(root).get(i).relation.getChildren().size(); t++) {
                        person2 = new DefaultMutableTreeNode(new Person(member.relation.getGrandFathers(root).get(i).relation.getChildren().get(t)));
                        category.add(person2);
                    }
                }
            }
        }
        //print parents
        if (member.relation.getParents().size() != 0) {
            category = new DefaultMutableTreeNode("Parents");
            top.add(category);
            for (int i = 0; i < member.relation.getParents().size(); i++) {
                person = new DefaultMutableTreeNode(new Person(member.relation.getParents().get(i)));
                category.add(person);
            }
        }
        // print spouse
        if (member.relation.getSpouse() != null) {
            category = new DefaultMutableTreeNode("Spouse");
            top.add(category);
            person = new DefaultMutableTreeNode(new Person(member.relation.getSpouse()));
            category.add(person);
        }
        // print siblings
        if (member.relation.getSiblings().size() != 0) {
            category = new DefaultMutableTreeNode("Siblings");
            top.add(category);
            for (int i = 0; i < member.relation.getSiblings().size(); i++) {
                person1 = new DefaultMutableTreeNode(new Person(member.relation.getSiblings().get(i)));
                category.add(person1);
                // print sibling's spouse
                if (member.relation.getSiblings().get(i).relation.getSpouse() != null) {
                    category1 = new DefaultMutableTreeNode("Spouse");
                    person1.add(category1);
                    person2 = new DefaultMutableTreeNode(new Person(member.relation.getSiblings().get(i).relation.getSpouse()));
                    category1.add(person2);
                }
                // print sibling's children
                if (member.relation.getSiblings().get(i).relation.getChildren().size() != 0) {
                    category1 = new DefaultMutableTreeNode("Children");
                    person1.add(category1);
                    for (int t = 0; t < member.relation.getSiblings().get(i).relation.getChildren().size(); t++) {
                        person2 = new DefaultMutableTreeNode(new Person(member.relation.getSiblings().get(i).relation.getChildren().get(t)));
                        category1.add(person2);
                    }
                }
            }
        }
        // print children
        if (member.relation.getChildren().size() != 0) {
            category1 = new DefaultMutableTreeNode("Children");
            top.add(category1);
            for (int i = 0; i < member.relation.getChildren().size(); i++) {
                person1 = new DefaultMutableTreeNode(new Person(member.relation.getChildren().get(i)));
                category1.add(person1);
                // print children's spouse
                if (member.relation.getChildren().get(i).relation.getSpouse() != null) {
                    category = new DefaultMutableTreeNode("Spouse");
                    person1.add(category);
                    person2 = new DefaultMutableTreeNode(new Person(member.relation.getChildren().get(i).relation.getSpouse()));
                    category.add(person2);
                }
                // print grandchildren
                if (member.relation.getChildren().get(i).relation.getChildren().size() != 0) {
                    category = new DefaultMutableTreeNode("Children");
                    person1.add(category);
                    for (int t = 0; t < member.relation.getChildren().get(i).relation.getChildren().size(); t++) {
                        person2 = new DefaultMutableTreeNode(new Person(member.relation.getChildren().get(i).relation.getChildren().get(t)));
                        category.add(person2);
                    }
                }
            }
        }

        // print uncles
        if (Relation.listUncles(root)) {
            category = new DefaultMutableTreeNode("Uncles");
            top.add(category);
            for (int i = 0; i < Relation.getUncles(root).size(); i++) {
                person = new DefaultMutableTreeNode(new Person(Relation.getUncles(root).get(i)));
                category.add(person);

                // print uncle's spouse
                if (Relation.getUncles(root).get(i).relation.getSpouse() != null) {
                    category = new DefaultMutableTreeNode("Spouse");
                    person.add(category);
                    person2 = new DefaultMutableTreeNode(new Person(Relation.getUncles(root).get(i).relation.getSpouse()));
                    category.add(person2);
                }
                // print uncle's children - cousins
                if (Relation.getUncles(root).get(i).relation.getChildren().size() != 0) {
                    category = new DefaultMutableTreeNode("Children");
                    person.add(category);
                    for (int t = 0; t < Relation.getUncles(root).get(i).relation.getChildren().size(); t++) {
                        person2 = new DefaultMutableTreeNode(new Person(Relation.getUncles(root).get(i).relation.getChildren().get(t)));
                        category.add(person2);
                    }
                }
            }
        }
        // print aunts
        if (Relation.listAunts(root)) {
            category = new DefaultMutableTreeNode("Aunts");
            top.add(category);
            for (int i = 0; i < Relation.getAunts(root).size(); i++) {
                person = new DefaultMutableTreeNode(new Person(Relation.getAunts(root).get(i)));
                category.add(person);
                // print aunt's spouse
                if (Relation.getAunts(root).get(i).relation.getSpouse() != null) {
                    category = new DefaultMutableTreeNode("Spouse");
                    person.add(category);
                    person2 = new DefaultMutableTreeNode(new Person(Relation.getAunts(root).get(i).relation.getSpouse()));
                    category.add(person2);
                }
                // print aunt's children - cousins
                if (Relation.getAunts(root).get(i).relation.getChildren().size() != 0) {
                    category = new DefaultMutableTreeNode("Children");
                    person.add(category);
                    for (int t = 0; t < Relation.getAunts(root).get(i).relation.getChildren().size(); t++) {
                        person2 = new DefaultMutableTreeNode(new Person(Relation.getAunts(root).get(i).relation.getChildren().get(t)));
                        category.add(person2);
                    }
                }
            }
        }
        // print cousins
        if (Relation.listCousins(root)) {
            category = new DefaultMutableTreeNode("Cousins");
            top.add(category);
            for (int i = 0; i < Relation.getCousins(root).size(); i++) {
                person = new DefaultMutableTreeNode(new Person(Relation.getCousins(root).get(i)));
                category.add(person);
                // print cousin's spouse
                if (Relation.getCousins(root).get(i).relation.getSpouse() != null) {
                    category = new DefaultMutableTreeNode("Spouse");
                    person.add(category);
                    person2 = new DefaultMutableTreeNode(new Person(Relation.getCousins(root).get(i).relation.getSpouse()));
                    category.add(person2);
                }
                // print cousin's children
                if (Relation.getCousins(root).get(i).relation.getChildren().size() != 0) {
                    category = new DefaultMutableTreeNode("Children");
                    person.add(category);
                    for (int t = 0; t < Relation.getCousins(root).get(i).relation.getChildren().size(); t++) {
                        person2 = new DefaultMutableTreeNode(new Person(Relation.getCousins(root).get(i).relation.getChildren().get(t)));
                        category.add(person2);
                    }
                }
            }
        }
    }
}
class InvalidPropertyException extends Exception{
    public InvalidPropertyException(String message) {
        super(message);
    }
}
