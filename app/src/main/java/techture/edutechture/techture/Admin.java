package techture.edutechture.techture;

import com.orm.SugarRecord;

/**
 * Created by Akshay on 01/23/18.
 */
public class Admin extends SugarRecord {

    private String access_token;
    private String fcmtoken;
    private String priviledges;

    public Admin() {
    }

    public Admin(String access_token, String fcmtoken, String priviledges) {
        this.access_token = access_token;
        this.fcmtoken = fcmtoken;
        this.priviledges = priviledges;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getFcmtoken() {
        return fcmtoken;
    }

    public void setFcmtoken(String fcmtoken) {
        this.fcmtoken = fcmtoken;
    }

    public String getPriviledges() {
        return priviledges;
    }

    public void setPriviledges(String priviledges) {
        this.priviledges = priviledges;
    }
}