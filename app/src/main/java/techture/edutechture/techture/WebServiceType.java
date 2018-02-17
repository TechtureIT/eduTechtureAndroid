package techture.edutechture.techture;

import com.android.volley.Request;

/**
 * Created by Akshay on 08-07-2017.
 */

public enum WebServiceType {

    FRAGMENT_INITIALISATION(0, null),

    GENERATE_ACCESS_TOKEN(Request.Method.GET, "login"),


    // admintechture/register/
    ADMIN_LOGIN(Request.Method.POST, "admintechture/login/"),

    // preliminary/users/
    LIST_USERS(Request.Method.GET, "preliminary/users/"),


    REFRESH_TOKEN(Request.Method.POST, "oauth/token?");

    private int methodType;
    private String url;

    private WebServiceType(int methodType, String url) {
        this.methodType = methodType;
        this.url = url;
    }

    public int getMethodType() {
        return methodType;
    }

    public String getUrl() {
        return url;
    }
}
