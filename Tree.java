import java.util.ArrayList;
import java.util.LinkedList;

 /** linkedlist by using objects (ece) vs linkedlist triple (kadir)*/
public class Tree {
    private Member root;
    private LinkedList<Member> sortedlist;
    private ArrayList<Integer> generationCount;


    public Tree(Member ancient) {
        root = ancient;
        generationCount = new ArrayList<>();
    }

    public void insert(Member newMember) {

            resort(newMember);
        }
//
        // need to improve
    public void resort(Member newMember){
        boolean flag = false;
        sortedlist = new LinkedList<>();
        for (int i = 0; i < sortedlist.size()-1; i++) {
            if (newMember.compareTo(sortedlist.get(i))>=0 && newMember.compareTo(sortedlist.get(i+1))<0){
                sortedlist.add(i+1,newMember);
                flag =true;
                break;
            }
        }
        if (sortedlist.size()>0){
            if (newMember.compareTo(sortedlist.get(0))<0){
                sortedlist.add(0,newMember);
                flag=true;
            }
        }
        if (!flag)
            sortedlist.add(sortedlist.size(),newMember);

    }
    public void setFatherRelative(Member member){
        for (int i = 0; i < getSortedlist().size(); i++) {
            if(member.getFather().getMother() == getSortedlist().get(i).getMother())
                switch (getSortedlist().get(i).getGender()) {
                    case male:
                        member.setAmca(getSortedlist().get(i));
                        break;
                    case female:
                        member.setHala(getSortedlist().get(i));
                        break;
                    case unknown:
                }
        }
    }
     public void setMotherRelative(Member member){
         for (int i = 0; i < getSortedlist().size(); i++) {
             if(member.getMother().getMother() == getSortedlist().get(i).getMother())
                 switch (getSortedlist().get(i).getGender()) {
                     case male:
                     //    member.setDayı(getSortedlist().get(i));
                         break;
                     case female:
                     //    member.setTeyze(getSortedlist().get(i));
                         break;
                     case unknown:
                 }
         }
     }

    public Member getRoot() {
        return root;
    }

    public void setRoot(Member root) {
        this.root = root;
    }

    public LinkedList<Member> getSortedlist() {
        return sortedlist;
    }

    public void setSortedlist(LinkedList<Member> sortedlist) {
        this.sortedlist = sortedlist;
    }

    public ArrayList<Integer> getGenerationCount() {
        return generationCount;
    }

    public void setGenerationCount(ArrayList<Integer> generationCount) {
        this.generationCount = generationCount;
    }

    /** sinan we need your algorithm methods here */
    @Override
    public String toString() {

        String result = "";
        int count = getRoot().getGeneration();

        for (int j = 0; j < generationCount.size(); j++){
            for (int i = 0; i < getSortedlist().size(); i++) {
                if (sortedlist.get(i).getGeneration() == count) {
                    result += getSortedlist().get(i).getName() + "\t";
                }
                count++;
                System.out.println();
            }
    }
            return "Tree:\n " + result;
    }
}
