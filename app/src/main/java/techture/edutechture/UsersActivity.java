package techture.edutechture;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Response;

import java.util.List;

import techture.edutechture.adaptors.UsersAdaptor;
import techture.edutechture.bean.UserInstructor;
import techture.edutechture.bean.response.UserListResponse;
import techture.edutechture.techture.GsonRequest;
import techture.edutechture.techture.TechtureActivity;
import techture.edutechture.techture.VolleyResponseBean;
import techture.edutechture.techture.WebDataServiceImpl;
import techture.edutechture.techture.WebServiceType;

public class UsersActivity extends TechtureActivity implements Response.Listener<VolleyResponseBean>, GsonRequest.ErrorListener {


    private List<UserInstructor> listUserInstructors;
    private ListView lstViewusers;
    private UsersAdaptor usersAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        lstViewusers = (ListView) findViewById(R.id.lv_users);


        getUsers();
    }

    private void getUsers() {

        showLoading(false);
        WebDataServiceImpl.getInstance(mApp).listUsers(WebServiceType.LIST_USERS, UserListResponse.class, this, this);
    }


    @Override
    public void onResponse(VolleyResponseBean response) {
        hideLoading();
        if (response.isValidData(response)) {
            switch (response.getWebServiceType()) {
                case LIST_USERS:
                    if (response.getData() instanceof UserListResponse) {


                        UserListResponse usersListResponse = (UserListResponse) response.getData();


                        listUserInstructors = usersListResponse.getUsers();

                        usersAdaptor = new UsersAdaptor(UsersActivity.this, listUserInstructors);
                        lstViewusers.setAdapter(usersAdaptor);
                    }


                    break;
            }
        }
    }

    @Override
    public void onErrorResponse(VolleyResponseBean volleyResponseBean, String errorMessage) {

    }

    @Override
    public void onNetworkUnavailable(WebServiceType webServiceType) {

    }
}
