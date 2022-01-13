public class Relationship {
    int ID;
    String relation;
    Member member1, member2;

    public Relationship(int ID, String relation, Member member1, Member member2) {
        this.ID = ID;
        this.relation = relation;
        this.member1 = member1;
        this.member2 = member2;
    }

    public Relationship() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Member getMember1() {
        return member1;
    }

    public void setMember1(Member member1) {
        this.member1 = member1;
    }

    public Member getMember2() {
        return member2;
    }

    public void setMember2(Member member2) {
        this.member2 = member2;
    }
}
