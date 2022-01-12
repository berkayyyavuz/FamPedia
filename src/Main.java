public class Main {

    public static void main(String[] args) {


        // Create a family tree
        Member mom = new Member("Aygül", 'F');
        Member dad = new Member("Veysel", 'M');
        Member sister = new Member("İgor", 'F');
        Member brother = new Member("Kadir", 'M');
        Member me = new Member("Kadirin EX", 'M');

        Member kadirincocu = new Member("Kadirin Çocuğu", 'M');

        Member hikmet = new Member("Hikmet", 'M');
        Member emanet = new Member("Emanet", 'F');

        Member selamet = new Member("Selamet", 'F');

        Member mahmut = new Member("Mahmut", 'M');

        Member ibrahim = new Member("İbrahim", 'M');
        Member vildan = new Member("Vildan", 'F');

        Member uncle = new Member("Cemal", 'M');
        Member aunt = new Member("Çınare", 'F');
        Member cousin = new Member("Ahmet", 'M');

        // Add members to the family tree
        Relation.addParent(me, mom);
        Relation.addParent(me, dad);
        Relation.addSibling(me, sister);
        Relation.addSibling(me, brother);

        // Kadirin cocu
        Relation.addParent(kadirincocu, brother);

        Relation.addSibling(mom, selamet);
        Relation.addSibling(mom, hikmet);

        Relation.addChild(selamet, ibrahim);
        Relation.addParent(vildan, hikmet);

        Relation.setSpouse(mom, dad);
        Relation.setSpouse(selamet, mahmut);
        Relation.setSpouse(hikmet, emanet);

        Relation.addSibling(dad, uncle);

        Relation.addParent(cousin, uncle);
        Relation.addParent(cousin, aunt);
        Relation.setSpouse(aunt, uncle);

        /*
        Relation.listUncles(cousin);
        Relation.listAunts(cousin);
        Relation.listCousins(cousin);
        Relation.listUncles(dad);
        Relation.listAunts(dad);
        Relation.listCousins(dad);
        */

        /*
    uncle.printRelations();
        aunt.printRelations();
        cousin.printRelations();
        */

        /*
        me.printRelations();
        sister.printRelations();
        brother.printRelations();
        mom.printRelations();
        dad.printRelations();
        selamet.printRelations();
        hikmet.printRelations();
        */

        Relation.listUncles(kadirincocu);
        Relation.listAunts(kadirincocu);
        Relation.listCousins(kadirincocu);

        kadirincocu.printRelations();
        brother.printRelations();
        

    }

}
