import java.io.Serializable;
import java.util.ArrayList;

public class Member implements Serializable, Comparable{
    private String name;
    private int[] birthday;//year/month/day
    private int[] deathday;//year/month/day
    private GenderType gender;//female male unknown
    private MarriageState isMarried;//unmarried married unknown
    private LiveState isAlive;//died alive unknown

    private int generation;
    private Member father;
    private Member mother;
    private ArrayList<Member> members;

    public Member(String name, int birthYear, int birthMonth, int birthDay,
                  int deathYear, int deathMonth, int deathDay,
                  GenderType gender, MarriageState isMarried, LiveState isAlive,
                  Member father, Member mother) {
        this.name = name;
        setBirthday(birthYear, birthMonth, birthDay);
        setDeathday(deathYear, deathMonth, deathDay);
        this.gender = gender;
        this.isMarried = isMarried;
        this.isAlive = isAlive;

        if(father != null)
        this.generation = father.getGeneration()+1;
        else generation = 1;

        this.father = father;
        this.mother = mother;
    }

    public Member() {
         name = "";
         birthday = new int[3];
         deathday = new int[3];
         gender = GenderType.unknown;
         isMarried = MarriageState.unknown;
         isAlive = LiveState.unknown;
         generation = 0;
         father = null;
         mother = null;
         members = new ArrayList<>();
    }

    public void setBirthday(int year, int month, int day) {
        birthday = new int[3];
        this.birthday[0] = year;
        this.birthday[1] = month;
        this.birthday[2] = day;
    }
    public void setDeathday(int year, int month, int day) {
        deathday = new int[3];
        this.deathday[0] = year;
        this.deathday[1] = month;
        this.deathday[2] = day;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getBirthday() {
        return birthday;
    }

    public int[] getDeathday() {
        return deathday;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public MarriageState getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(MarriageState isMarried) {
        this.isMarried = isMarried;
    }

    public LiveState getIsAlive() {
        return isAlive;
    }

    public void setIsAlive(LiveState isAlive) {
        this.isAlive = isAlive;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public Member getFather() {
        return father;
    }

    public void setFather(Member father) {
        this.father = father;
    }

    public Member getMother() {
        return mother;
    }

    public void setMother(Member mother) {
        this.mother = mother;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    @Override
    //compare birthday
    public int compareTo(Object o) {
        Member other = (Member)o;
        if (this.birthday[0]>other.birthday[0])
            return 1;
        else if (this.birthday[0]==other.birthday[0])
            if (this.birthday[1]>other.birthday[1])
                return 1;
            else if (this.birthday[1]==other.birthday[1])
                if (this.birthday[2] >other.birthday[2])
                    return 1;
                else if (this.birthday[2] ==other.birthday[2])
                    return 0;
        return -1;
    }
}
enum GenderType {male,female,unknown}
enum MarriageState {married,unmarried,unknown}
enum LiveState {alive,died,unknown}