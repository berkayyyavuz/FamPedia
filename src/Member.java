public class Member {
    private int ID;
    private int[] birthDate = new int[3];//year/month/day
    private int[] deathDate = new int[3];//year/month/day
    private String name, surname;
    private boolean genderType;
    private boolean marriageState;
    private boolean liveState;

    public Member(int ID, int[] birthDate, int[] deathDate, String name, String surname, boolean genderType, boolean marriageState, boolean liveState) {
        this.ID = ID;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
        this.name = name;
        this.surname = surname;
        this.genderType = genderType;
        this.marriageState = marriageState;
        this.liveState = liveState;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int [] getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(int [] birthDate) {
        this.birthDate = birthDate;
    }

    public int [] getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(int [] deathDate) {
        this.deathDate = deathDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}



