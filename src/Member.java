
public class Member {

    public String name;
    public char gender;
    public boolean isAlive;

    public Relation relation;

    public Member(String name, char gender) {
        this.name = name;
        this.gender = gender;
        this.relation = new Relation();

        // We assume that dead person can't be added as a member
        this.isAlive = true;
    }

    // Print the relations
    public void printRelations() {
        System.out.println("\n" + this.name + "'s relations:");

        if (this.relation.getSpouse() != null) {
            System.out.println("Spouse: " + this.relation.getSpouse().name);
        } else {
            System.out.println("Spouse: None");
        }

        // loop the parents
        System.out.println("\tParents: ");
        for (Member parent : this.relation.getParents()) {
            System.out.println("\t\t" + parent.name);
        }

        // loop the children
        System.out.println("\tChildren: ");
        for (Member child : this.relation.getChildren()) {
            System.out.println("\t\t" + child.name);
        }

        // loop the siblings
        System.out.println("\tSiblings: ");
        for (Member sibling : this.relation.getSiblings()) {
            System.out.println("\t\t" + sibling.name);
        }

        // loop the uncles
        System.out.println("\tUncles: ");
        for (Member uncle : Relation.getUncles(this)) {
            System.out.println("\t\t" + uncle.name);
        }

        // loop the aunts
        System.out.println("\tAunts: ");
        for (Member aunt : Relation.getAunts(this)) {
            System.out.println("\t\t" + aunt.name);
        }

        // loop the cousins
        System.out.println("\tCousins: ");
        for (Member cousin : Relation.getCousins(this)) {
            System.out.println("\t\t" + cousin.name);
        }
    }
    
}
