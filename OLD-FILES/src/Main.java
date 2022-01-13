public class Main {

    public static void main(String[] args) {


// Create a family tree

        Member dede = new Member("Avni", 'M');
        Member nene = new Member("Melek", 'F');

        Member mom = new Member("Dilek", 'F');
        Member dad = new Member("Turgay", 'M');

        Member me = new Member("ECE", 'F');


        Member mom2 = new Member("Ayfer", 'F');
        Member dad2 = new Member("Cengiz", 'M');

        Member kuzi = new Member("Oğuz", 'M');

        Relation.setSpouse(dede, nene);

        Relation.addParent(dad, dede);
        Relation.addParent(dad2, dede);

        Relation.setSpouse(dad, mom);
        Relation.setSpouse(dad2, mom2);

        Relation.addParent(me, dad);
        Relation.addParent(kuzi, dad2);


        Relation.listAllRelations(me);
        me.printRelations();





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



    }

}
