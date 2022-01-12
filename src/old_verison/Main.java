import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Member member = new Member();

        //father
        Member member1 = new Member("Avni", 1950, 12, 10 , 2005, 11, 10,
                GenderType.male, MarriageState.married, LiveState.died, null, null);
        //mother
        Member member2 = new Member("Melek", 1955, 9, 10 , 2007, 11, 10,
                GenderType.female, MarriageState.married, LiveState.died, null, null);
        //children/2
        Member member3 = new Member("Turgay", 1968, 2, 19 , 0, 0, 0,
                GenderType.male, MarriageState.married, LiveState.alive, member1, member2);
        Member member4 = new Member("Cengiz", 1965, 5, 10 , 0, 0, 0,
                GenderType.male, MarriageState.married, LiveState.alive, member1, member2);

        member.getMembers().add(member1); // ancient
        member.getMembers().add(member2);
        member.getMembers().add(member3);
        member.getMembers().add(member4);

        Tree tree = new Tree(member1);
        tree.resort(member1);


        //print tree
        System.out.println(tree.toString());
    }
}
