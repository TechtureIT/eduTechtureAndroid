package techture.edutechture.techture;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.HashMap;

import techture.edutechture.MainActivity;

/**
 * Created by Akshay on 01/23/18.
 */

public class TechtureApplication extends Application {
    private static final String TAG = TechtureApplication.class.getSimpleName();
    private RequestQueue mRequestQueue;
    private HashMap<Object, Request> requestList = new HashMap<>();
    private ArrayList<Activity> listLoginSignUpActivity = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
//        Fabric.with(this, new Crashlytics());
        SugarContext.init(this);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        getRequestQueue().getCache().remove(tag);
        cancelPendingRequests(tag);
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
        requestList.put(req.getTag(), req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void cancelAllPendingRequests() {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(new RequestQueue.RequestFilter() {
                @Override
                public boolean apply(Request<?> request) {
                    return true;
                }
            });
        }
    }

    public void addActivityToStack(Activity activity) {
        listLoginSignUpActivity.add(activity);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void removeFromRequestsList(Object tag) {
        if (requestList.containsKey(tag))
            requestList.remove(tag);
    }

    public String getAccessToken() {

        try{
            Admin admin = LocalDataServiceImpl.getInstance(this).getAdminResponse();
            if(admin!=null){
                return admin.getAccess_token();
            }
            return "";

        }catch (SQLiteException e){
            return "";
        }

//        OAuthDetailsRequestResponse oAuthResponse = LocalDataServiceImpl.getInstance(this).getOAuthRequestResponse();
//        if (oAuthResponse != null)
//            return oAuthResponse.getAccess_token();
//        return "";
    }

    public void resartPedingRequest() {
        for (Request request :
                requestList.values()) {
            try {
                if (request instanceof GsonRequest) {
                    GsonRequest gsonRequest = (GsonRequest) request;
                    String requestStringBody = "";
                    if (gsonRequest.getBody() != null)
                        requestStringBody = new String(gsonRequest.getBody());
                    GsonRequest jsonRequest = new GsonRequest(this, gsonRequest.getPriority(), gsonRequest.getWebServiceType(),
                            gsonRequest.getResponseClass(),
                            gsonRequest.getUrl(),
                            requestStringBody,
                            gsonRequest.getResponseListener(), gsonRequest.getResponseErrorListener());
                    getRequestQueue().add(jsonRequest);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        requestList.clear();
    }

    public void openLoginActivity() {
//        Intent intent = new Intent(this, CommonOpenUpActivity.class);
//        intent.putExtra(CommonOpenUpFragmentType.TAG_FRAGMENT_TYPE_ORDINAL, CommonOpenUpFragmentType.LOG_IN.ordinal());
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent);

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
