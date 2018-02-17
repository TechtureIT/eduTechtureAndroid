package techture.edutechture.bean.response;

import java.util.ArrayList;

import techture.edutechture.bean.UserInstructor;

/**
 * Created by Akshay on 02/17/18.
 */

public class UserListResponse {

    private ArrayList<UserInstructor> users;

    public ArrayList<UserInstructor> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserInstructor> users) {
        this.users = users;
    }
}
