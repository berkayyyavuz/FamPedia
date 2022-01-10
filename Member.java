import java.util.ArrayList;

public class Member{
    private String name;
    private int birthYear; //year
    private int deathYear; //year
    private GenderType gender;//female male unknown
    private MarriageState isMarried;//unmarried married unknown
    private LiveState isAlive;//died alive unknown

    private int generation;
    private Member father;
    private Member mother;
    private ArrayList<Member> members;
    static int generationCount = 0;

    private Member amca;
    private Member hala;
    private Member spouse;
    private Member child;

    public Member(String name, int birthYear, int deathYear,
                  GenderType gender, MarriageState isMarried, LiveState isAlive,
                  Member father, Member mother) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.gender = gender;
        this.isMarried = isMarried;
        this.isAlive = isAlive;

        if(father != null)
        this.generation = father.getGeneration()+1;
        else generation = 1;

        this.father = father;
        this.mother = mother;

        generationCount = getGeneration();
    }

    public Member() {
         name = "";
         birthYear = 0;
         deathYear = 0;
         gender = GenderType.unknown;
         isMarried = MarriageState.unknown;
         isAlive = LiveState.unknown;
         generation = 0;
         father = null;
         mother = null;
         members = new ArrayList<>();
    }

    public Member getSpouse() {
        return spouse;
    }

    public void setSpouse(Member spouse) {
        spouse.setGeneration(this.getGeneration());
        this.spouse = spouse;
    }

    public Member getChild() {
        return child;
    }

    public void setChild(Member child) {
        this.child = child;
    }

    public Member getAmca() {
        return amca;
    }

    public void setAmca(Member amca) {
        this.amca = amca;
    }

    public Member getHala() {
        return hala;
    }

    public void setHala(Member hala) {
        this.hala = hala;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }
}
enum GenderType {male,female,unknown}
enum MarriageState {married,unmarried,unknown}
enum LiveState {alive,died,unknown}