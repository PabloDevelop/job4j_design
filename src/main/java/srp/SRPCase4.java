package srp;

public class SRPCase4 {

    private int age;

    private String name;

    private String city;

    private char gender;

    private int phone;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public SRPCase4(int age, String name, String city, char gender, int phone) {
        this.age = age;
        this.name = name;
        this.city = city;
        this.gender = gender;
        this.phone = phone;
    }

    public void saveUserIntoBD(SRPCase4 srpCase4) {
    }

    public void makeACall() {
    }

    public void sendCallLogViaEmail(String log) {
    }
}