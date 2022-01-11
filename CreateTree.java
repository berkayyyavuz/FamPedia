import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.ListIterator;

public class CreateTree extends JPanel {
    JPanel newMemberPanel, treePanel;
    JSplitPane splitPane;
    JTextField txt1, txt2, txt3, txt4, txt5;
    JRadioButton btn1, btn2, btn3, btn4, btn5, btn6;
    Tree tree;
    Member ancient;
    public void setMainPanel() {
        splitPane = new JSplitPane();
        treePanel = new JPanel();
        tree = new Tree(ancient);
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
//boş bırakmaması için zorlayayım mı?
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
            //treePanel.add(new TreePanel(tree)); /** does not work */
            treePanel.revalidate();
        });
        treePanel.add(new TreePanel(tree));
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
            else member.setGeneration(1);
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
class TreePanel extends JPanel{
    Tree tree;
    private HashMap<String, Coordinates> coord;

    public TreePanel(Tree tree) {
        this.tree = tree;
        coord = new HashMap<>();
        Coordinates coordinates = new Coordinates(0,0,0,0);
        coordinates.setAllCoords();

        revalidate();
    }
/** does not work */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        for (int i = 1; i < 20; i++) {
            for (int j = 0; j < tree.getGenerationSize(i); j++) {
                Coordinates personCoords = coord.get(tree.getSortedlist().get(j).getName());
                System.out.println(tree.getSortedlist().get(j).getName());
                System.out.println(personCoords.x);
                System.out.println(personCoords.y);

                g.setFont(new Font("SERIF", 1, 12));
                g.setColor(Color.white);
                g.fillOval(personCoords.x, personCoords.y, personCoords.width, personCoords.height);
                g.setColor(Color.black);
                g.drawString(tree.getSortedlist().get(j).getName(), personCoords.x + 5, personCoords.y + 15);
                g.setColor(Color.black);
                g.drawOval(personCoords.x, personCoords.y, personCoords.width, personCoords.height);
                g.setColor(Color.black);
                if (tree.getSortedlist().get(j).getSpouse() != null) {
                    for (int l = 0; l < tree.getGenerationSize(i); l++) {
                        if (tree.getSortedlist().get(j).getSpouse().equals(tree.getSortedlist().get(l))) {
                            if (j < l) {
                                g.drawLine(personCoords.x + personCoords.width, personCoords.y + 10, coord.get(tree.getSortedlist().get(l).getName()).x, coord.get(tree.getSortedlist().get(l).getName()).y + 10);
                            } else {

                            }
                        }

                    }

                }
                if (tree.getSortedlist().get(j).getChildren().size() != 0 && i < 19) {
                    ListIterator<String> iter = tree.getSortedlist().get(j).getChildren().listIterator();
                    while (iter.hasNext()) {
                        String child = iter.next();
                        for (int k = 0; k < tree.getGenerationSize(i + 1); k++) {
                            if (child.equals(tree.getSortedlist().get(k).getName())) {

                                g.drawLine(personCoords.x + personCoords.width / 2, personCoords.y + 20, coord.get(tree.getSortedlist().get(k).getName()).x + coord.get(tree.getSortedlist().get(k).getName()).width / 2, coord.get(tree.getSortedlist().get(k).getName()).y);

                            }
                        }

                    }
                }
            }
        }
        revalidate();
        repaint();
    }
    public class Coordinates {

        private int x;
        private int y;
        private int width;
        private int height;

        public Coordinates(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void setAllCoords(){
            for(int i=0;i<tree.getSortedlist().size();i++){
                changePersonCoords(i);
            }
        }
        public void changePersonCoords(int i) {

            calcCoordinates(tree.getSortedlist().get(i).getGeneration(), i, new Dimension(100,50));
            coord.put(tree.getSortedlist().get(i).getName(), new Coordinates(x, y, width,height));
        }
        public void calcCoordinates(int level, int i, Dimension dim) {
            AffineTransform affinetransform = new AffineTransform();
            FontRenderContext frc = new FontRenderContext(affinetransform, false, false);
            Font f = new Font("SERIF", 1, 12);
            this.width = (int) (f.getStringBounds(tree.getSortedlist().get(i).getName(), frc).getWidth()) + 10;
            this.height = 20;
            this.x = 300+dim.width / (tree.getGenerationSize(level) + 1) * (i + 1) - this.width / 2;
            this.y = 20 + level * 50 + 34;
        }


        public String toString() {
            String str = x + "." + y + "." + width + "." + height + ".";
            return str;
        }

    }
}