package android.rentit.app.rent_it;

/**
 * Created by kprince on 29/10/17.
 */

public class UserInformation {

    private String name;
    private String email;
    private String mobNo;

    public UserInformation()
    {

    }

    public UserInformation(String name, String email, String mobNo) {
        this.name = name;
        this.email = email;
        this.mobNo = mobNo;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }
}
