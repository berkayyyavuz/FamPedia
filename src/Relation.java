import java.util.LinkedList;

public class Relation {

    private Member spouse;
    private LinkedList<Member> children;
    private LinkedList<Member> parents;
    private LinkedList<Member> siblings;

    // Secondary relations
    private LinkedList<Member> grandMothers;
    private LinkedList<Member> grandFathers;
    private LinkedList<Member> uncles;
    private LinkedList<Member> aunts;
    private LinkedList<Member> cousins;


    public Relation() {
        this.spouse = null;

        this.children = new LinkedList<Member>();
        this.parents = new LinkedList<Member>();
        this.siblings = new LinkedList<Member>();

        this.grandMothers = new LinkedList<Member>();
        this.grandFathers = new LinkedList<Member>();
        this.uncles = new LinkedList<Member>();
        this.aunts = new LinkedList<Member>();
        this.cousins = new LinkedList<Member>();
    }

    public static void setSpouse(Member current, Member spouse) {
        current.relation.spouse = spouse;

        // add current to spouse's spouse
        if (spouse.relation.getSpouse() == null) {
            Relation.setSpouse(spouse, current);
        }
    }

    public static void addChild(Member current, Member child) {
        current.relation.children.add(child);

        // If child doesn't have this as a parent, add it
        if (!child.relation.parents.contains(current)) {
            Relation.addParent(child, current);
        }
    }

    public static void addParent(Member current, Member parent) {
        current.relation.parents.add(parent);

        // If parent doesn't have this as a child, add it
        if (!parent.relation.children.contains(current)) {
            Relation.addChild(parent, current);
        }
    }

    public static void addSibling(Member current, Member sibling) {
        current.relation.siblings.add(sibling);

        // Add sibling to the parent's children
        for (Member parent : current.relation.parents) {
            if (!parent.relation.children.contains(sibling)) {
                Relation.addChild(parent, sibling);
            }
        }

        // Update all the siblings of the sibling
        for (Member tsibling : current.relation.siblings) {
            if (!tsibling.relation.siblings.contains(current)) {
                tsibling.relation.siblings.add(current);
            }

            // Get the rest of the sibling from the current's siblings
            Member[] restSiblings = new Member[current.relation.siblings.size() - 1];

            int i = 0;
            for (Member tsibling2 : current.relation.siblings) {
                if (tsibling2 != tsibling) {
                    restSiblings[i] = tsibling2;
                    i++;
                }
            }

            // Add the rest of the siblings to the sibling
            for (Member restSibling : restSiblings) {
                if (!restSibling.relation.siblings.contains(tsibling)) {
                    restSibling.relation.siblings.add(tsibling);
                }
            }
        }
        
    }

    // Get the uncle of the current
    public static boolean listUncles(Member current) {
        boolean found = false;

        for (Member parent : current.relation.parents) {
            for (Member sibling : parent.relation.siblings) {
                if (sibling != current && sibling.gender == 'M') {
                    current.relation.uncles.add(sibling);
                    found = true;
                }
            }
        }

        // Update current's sibling's uncle
        for (Member sibling : current.relation.siblings) {
            sibling.relation.uncles = current.relation.uncles;
        }

        return found;
    }

    // Get the aunt of the current
    public static boolean listAunts(Member current) {
        boolean found = false;

        for (Member parent : current.relation.parents) {
            for (Member sibling : parent.relation.siblings) {
                if (sibling != current && sibling.gender == 'F') {
                    current.relation.aunts.add(sibling);
                    found = true;
                }

                if (sibling.relation.spouse != null && sibling.relation.spouse.gender == 'F') {
                    current.relation.aunts.add(sibling.relation.spouse);
                    found = true;
                }
            }
        }

        // Update current's sibling's uncle
        for (Member sibling : current.relation.siblings) {
            sibling.relation.aunts = current.relation.aunts;
        }

        return found;
    }

    // Get the cousin of the current
    public static boolean listCousins(Member current) {
        boolean found = false;

        for (Member parent : current.relation.parents) {
            for (Member sibling : parent.relation.siblings) {
                for (Member cousin : sibling.relation.children) {
                    if (cousin != current) {
                        current.relation.cousins.add(cousin);
                        found = true;
                    }
                }
            }
        }

        // Update current's sibling's uncle
        for (Member sibling : current.relation.siblings) {
            sibling.relation.cousins = current.relation.cousins;
        }

        return found;
    }

    // Get the grandMother of the current
    public static boolean listGrandMothers(Member current) {
        boolean found = false;

        for (Member parent : current.relation.parents) {
            for (Member grandParent : parent.relation.parents) {
                if (grandParent.gender == 'F') {
                    current.relation.grandMothers.add(grandParent);
                    found = true;
                }
            }
        }

        // Update current's sibling's uncle
        for (Member sibling : current.relation.siblings) {
            sibling.relation.grandMothers = current.relation.grandMothers;
        }

        return found;
    }

    // Get the grandMother of the current
    public static boolean listGrandFathers(Member current) {
        boolean found = false;

        for (Member parent : current.relation.parents) {
            for (Member grandParent : parent.relation.parents) {
                if (grandParent.gender == 'M') {
                    current.relation.grandFathers.add(grandParent);
                    found = true;
                }
            }
        }

        // Update current's sibling's uncle
        for (Member sibling : current.relation.siblings) {
            sibling.relation.grandFathers = current.relation.grandFathers;
        }

        return found;
    }


    // Return uncles of the current
    public static LinkedList<Member> getUncles(Member current) {
        return current.relation.uncles;
    }

    // Return aunts of the current
    public static LinkedList<Member> getAunts(Member current) {
        return current.relation.aunts;
    }

    // Return cousins of the current
    public static LinkedList<Member> getCousins(Member current) {
        return current.relation.cousins;
    }

    // Return grandMothers of the current
    public static LinkedList<Member> getGrandMothers(Member current) {
        return current.relation.grandMothers;
    }

    // Return grandFathers of the current
    public static LinkedList<Member> getGrandFathers(Member current) {
        return current.relation.grandFathers;
    }

    public Member getSpouse() {
        return this.spouse;
    }

    public LinkedList<Member> getChildren() {
        return this.children;
    }

    public LinkedList<Member> getParents() {
        return this.parents;
    }

    public LinkedList<Member> getSiblings() {
        return this.siblings;
    }
}
