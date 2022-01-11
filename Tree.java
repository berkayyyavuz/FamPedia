import java.util.ArrayList;
import java.util.LinkedList;

public class Tree {
    private Member root;
    private LinkedList<Member> sortedlist;
    private ArrayList<Integer> generationCount;


    public Tree(Member ancient) {
        root = ancient;
        generationCount = new ArrayList<>();
        sortedlist = new LinkedList<>();

    }

    public void insert(Member newMember) {
            sortedlist.add(newMember);
            setChild(newMember);
            setFatherRelative(newMember);
           // setMotherRelative(newMember);
    }


    public void resort(Member newMember){
        for(int i = 0; i < getSortedlist().size(); i++) {
            if (newMember.getIsMarried().equals(MarriageState.married)) {
                sortedlist.add(0, newMember);
            }
        }
    }

    public void setFatherRelative(Member member){
        if(member.getFather() != null && member.getFather().getMother() != null ) {
            for (int i = 0; i < getSortedlist().size(); i++) {
                if (member.getFather().getMother() == getSortedlist().get(i).getMother())
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

     public void setChild(Member member){

        if(member.getMother() != null && member.getFather() != null){
            member.getMother().setChild(member);
            member.getMother().getChildren().add(member.getName());
            member.getFather().setChild(member);
            member.getFather().getChildren().add(member.getName());
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

//new method
    public int getGenerationSize(int generation){
        int count = 0;
        for(int i=0;i< sortedlist.size();i++) {
            if (sortedlist.get(i).getGeneration() == generation)
                count++;
        }
        return count;
    }

    public void searchParent(String fatherName, String motherName,  Member member){
        for(int i=0;i< sortedlist.size();i++) {
            switch (getSortedlist().get(i).getGender()) {
                case male:
                    if (getSortedlist().get(i).getName().equals(fatherName) && getSortedlist().get(i).getGeneration() < member.getGeneration())
                        member.setFather(getSortedlist().get(i));
                    break;
                case female:
                    if (getSortedlist().get(i).getName().equals(motherName) && getSortedlist().get(i).getGeneration() < member.getGeneration())
                        member.setMother(getSortedlist().get(i));
                    break;
                case unknown:
            }

        }
    }
    @Override
    public String toString() {

        String result = "";

        for (int j = 1; j < Member.generationCount+1; j++){
            for (int i = 0; i < getSortedlist().size(); i++) {
                if (sortedlist.get(i).getGeneration() == j) {
                    result += getSortedlist().get(i).getName() + "\t";
                }
            }
            result += "\n";
    }
            return "Tree:\n " + result;
    }
}
