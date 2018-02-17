package techture.edutechture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;

import techture.edutechture.bean.request.AdminLoginRequest;
import techture.edutechture.bean.response.BooleanResponse;
import techture.edutechture.techture.GsonRequest;
import techture.edutechture.techture.TechtureActivity;
import techture.edutechture.techture.VolleyResponseBean;
import techture.edutechture.techture.WebDataServiceImpl;
import techture.edutechture.techture.WebServiceType;

public class LoginActivity extends TechtureActivity implements View.OnClickListener, Response.Listener<VolleyResponseBean>, GsonRequest.ErrorListener {


    private EditText edtLoginUsername;
    private EditText edtLoginPassword;
    private Button btnAdminLogin;
    private ProgressBar pbAdminLogin;


    private String loginUsername, loginPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edtLoginUsername = (EditText)findViewById(R.id.edt_login_email);
        edtLoginPassword = (EditText)findViewById(R.id.edt_login_password);

        btnAdminLogin = (Button)findViewById(R.id.btn_admin_login);
        pbAdminLogin  = (ProgressBar)findViewById(R.id.pb_admin_login);

        pbAdminLogin.setVisibility(View.GONE);
        btnAdminLogin.setVisibility(View.VISIBLE);



        btnAdminLogin.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {

        switch(view.getId()){

            case R.id.btn_admin_login:

                loginUsername = edtLoginUsername.getText().toString();
                loginPassword = edtLoginPassword.getText().toString();

                if(validate()){

                    login();
                }else{

                    Toast.makeText(mApp, "Enter Valid Credentials", Toast.LENGTH_SHORT).show();
                }
        }

    }


    public void login(){


        pbAdminLogin.setVisibility(View.VISIBLE);
        btnAdminLogin.setVisibility(View.GONE);


        AdminLoginRequest adminLoginRequest = new AdminLoginRequest();
        adminLoginRequest.setUsername(loginUsername);
        adminLoginRequest.setPassword(loginPassword);
        WebDataServiceImpl.getInstance(mApp).adminLogin(WebServiceType.ADMIN_LOGIN, adminLoginRequest, BooleanResponse.class, this, this);


    }


    public boolean validate(){

        boolean status = true;
        if(loginUsername.isEmpty()){
            edtLoginUsername.setError("Username Cannot be Empty");
            status = false;
        }else if(loginPassword.isEmpty()){

            edtLoginPassword.setError("Password cannot be Empty");
            status = false;
        }

        return status;
    }

    @Override
    public void onResponse(VolleyResponseBean response) {

        pbAdminLogin.setVisibility(View.GONE);
        btnAdminLogin.setVisibility(View.VISIBLE);

        if (response.isValidData(response)) {
            switch (response.getWebServiceType()) {
                case ADMIN_LOGIN:

                    if(response.getData() instanceof BooleanResponse){


                        BooleanResponse booleanResponse = (BooleanResponse) response.getData();
                        if(booleanResponse.getStatus()){


                            Toast.makeText(mApp, "Login Successful", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, NavigationMainActivity.class);
                            startActivity(intent);
                            finish();


                        }else{

                            edtLoginPassword.setText("");
                            edtLoginUsername.setText("");
                            Toast.makeText(mApp, "Invalid Username Or Password", Toast.LENGTH_SHORT).show();

                        }


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
