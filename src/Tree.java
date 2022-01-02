import java.util.ArrayList;
import java.util.Date;

public class Tree {
    int ID;
    String name;
    Date creationDate;
    ArrayList <Member> listOfMember;

    public Tree(int ID, String name, Date creationDate, ArrayList<Member> listOfMember) {
        this.ID = ID;
        this.name = name;
        this.creationDate = creationDate;
        this.listOfMember = listOfMember;
    }

    public Tree() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ArrayList<Member> getListOfMember() {
        return listOfMember;
    }

    public void setListOfMember(ArrayList<Member> listOfMember) {
        this.listOfMember = listOfMember;
    }
}
