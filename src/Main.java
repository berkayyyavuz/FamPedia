public class Main {

    public static void main(String[] args) {


// Create a family tree

        Member dede = new Member("Mehmet", 'M');
        Member dede2 = new Member("Mehmet2", 'M');
        Member nene = new Member("Yüksel", 'F');
        Member nene2 = new Member("Yüksel2", 'F');

        Member mom = new Member("Nurgül", 'F');
        Member dad = new Member("Kadir", 'M');
        Member brother = new Member("Cihan", 'M');
        Member me = new Member("SİNAN", 'M');


        Member akif = new Member("akif", 'M');
        Member hulya = new Member("hulya", 'F');

        Member kuzi = new Member("Kaan", 'M');

        Relation.addParent(dad, dede);
        Relation.addParent(dad, nene);
        Relation.addParent(mom, dede2);
        Relation.addParent(mom, nene2);

        Relation.addParent(me, mom);
        Relation.addParent(me, dad);
        Relation.addSibling(me, brother);

        Relation.addSibling(dad, akif);


        Relation.setSpouse(akif, hulya);
        Relation.addParent(kuzi, akif);


        Relation.listAllRelations(dad);


        dad.printRelations();

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
