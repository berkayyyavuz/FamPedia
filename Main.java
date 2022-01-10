
public class Main {
    public static void main(String[] args) {
        Member member = new Member();

        //father
        Member member1 = new Member("Father", 1950,2005, GenderType.male, MarriageState.married, LiveState.died, null, null);
        //mother
        Member member2 = new Member("Mother", 1955, 2007, GenderType.female, MarriageState.married, LiveState.died, null, null);
        //children/2
        Member member3 = new Member("child1", 1968, 0, GenderType.male, MarriageState.married, LiveState.alive, member1, member2);
        Member member4 = new Member("child2", 1965, 0, GenderType.male, MarriageState.married, LiveState.alive, member1, member2);
        //wife
        Member member5 = new Member("wife of child1", 1972, 0, GenderType.male, MarriageState.married, LiveState.alive, null,null);
        //grandchildren
        Member member6 = new Member("child1 COCUK", 2001,0, GenderType.male, MarriageState.married, LiveState.alive, member3, member5);

        member.getMembers().add(member1); // ancient
        member.getMembers().add(member2);
        member.getMembers().add(member3);
        member.getMembers().add(member4);
        member.getMembers().add(member5);
        member.getMembers().add(member6);

        member1.setSpouse(member2);
        member2.setSpouse(member1);
        member3.setSpouse(member5);
        member5.setSpouse(member3);

        Tree tree = new Tree(member1);
        tree.setRoot(member1);
        tree.insert(member1);
        tree.insert(member2);
        tree.insert(member3);
        tree.insert(member4);
        tree.insert(member5);
        tree.insert(member6);



        // print amca
        System.out.println("Amca: "+member6.getAmca().getName());
        System.out.println("Wife:" + member3.getSpouse().getName());
        System.out.println("Mother:" + member6.getMother().getName());

        System.out.println();

        //print tree
        System.out.print(tree.toString());
    }
}
