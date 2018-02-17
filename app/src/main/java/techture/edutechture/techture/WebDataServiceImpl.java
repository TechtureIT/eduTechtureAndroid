package techture.edutechture.techture;

import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.util.HashMap;

import techture.edutechture.R;
import techture.edutechture.bean.request.AdminLoginRequest;
import techture.edutechture.bean.response.BooleanResponse;

import static techture.edutechture.techture.TechtureConstants.isNetworkOnline;


/**
 * Created by Akshay on 08-07-2017.
 */

public class WebDataServiceImpl {

    private static WebDataServiceImpl mInstance;
    private static TechtureApplication mApp;

    private WebDataServiceImpl() {
    }

    public static WebDataServiceImpl getInstance(TechtureApplication application) {
        if (mInstance == null) {
            mApp = application;
            mInstance = new WebDataServiceImpl();
        }
        return mInstance;
    }


    /**
     * Common method to request for a response object
     *
     * @param serviceType      : represents which service API type is called
     * @param responseClass    : in which class the response data is supposed to cast
     * @param url              : url of service type
     * @param objBody          : body which is to be attached along with the request
     * @param headers          : extra headers that are to be included along with API call
     * @param responseListener : listener where the response is to be passed
     * @param errorListener    : listener where the error is to be fetched.
     */
    public void getResponse(WebServiceType serviceType, Class<?> responseClass, String url, Object objBody,
                            HashMap<String, String> headers, Response.Listener<VolleyResponseBean> responseListener,
                            GsonRequest.ErrorListener errorListener) {
        try {
            String body = null;
            if (objBody != null) {
                body = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PROTECTED, Modifier.PUBLIC).create().toJson(objBody);
//                if (body.contains(LocalDatabaseUtils.ID_REPLACED))
//                    body = body.replace(LocalDatabaseUtils.ID_REPLACED, LocalDatabaseUtils.ID);
//                if (body.contains(LocalDatabaseUtils.FROM_VALUE))
//                    body = body.replace(LocalDatabaseUtils.FROM_VALUE, LocalDatabaseUtils.FROM);
//                if (body.contains(LocalDatabaseUtils.TO_VALUE))
//                    body = body.replace(LocalDatabaseUtils.TO_VALUE, LocalDatabaseUtils.TO);
            }
            if (url.contains(GsonRequest.SPACE))
                url = url.replace(GsonRequest.SPACE, GsonRequest.SPACE_REPLACED);
            GsonRequest request = new GsonRequest(mApp, Request.Priority.HIGH, serviceType, responseClass, url, body, headers, responseListener,
                    errorListener);
            request.setRetryPolicy(new DefaultRetryPolicy('\uea60', 1, 1.0F));
            mApp.addToRequestQueue(request, serviceType.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getResponseForLogin(WebServiceType serviceType, Class<?> responseClass, String url,
                                    HashMap<String, String> headers, Response.Listener<VolleyResponseBean> responseListener,
                                    GsonRequest.ErrorListener errorListener) {
        try {
//            String body = null;
//            if (objBody != null) {
//                body = new GsonBuilder().excludeFieldsWithModifiers(Modifier.PROTECTED, Modifier.PUBLIC).create().toJson(objBody);
////                if (body.contains(LocalDatabaseUtils.ID_REPLACED))
////                    body = body.replace(LocalDatabaseUtils.ID_REPLACED, LocalDatabaseUtils.ID);
////                if (body.contains(LocalDatabaseUtils.FROM_VALUE))
////                    body = body.replace(LocalDatabaseUtils.FROM_VALUE, LocalDatabaseUtils.FROM);
////                if (body.contains(LocalDatabaseUtils.TO_VALUE))
////                    body = body.replace(LocalDatabaseUtils.TO_VALUE, LocalDatabaseUtils.TO);
//            }
            if (url.contains(GsonRequest.SPACE))
                url = url.replace(GsonRequest.SPACE, GsonRequest.SPACE_REPLACED);
            GsonRequest request = new GsonRequest(mApp, Request.Priority.HIGH, serviceType, responseClass, url, null, headers, responseListener,
                    errorListener);
            request.setRetryPolicy(new DefaultRetryPolicy('\uea60', 1, 1.0F));
            mApp.addToRequestQueue(request, serviceType.getUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void checkVersion(Class<?> class1, VersionCheckRequest versionCheckRequest, Response.Listener<VolleyResponseBean> responseListener, GsonRequest.ErrorListener errorListener) {
//        WebServiceType webServiceType = WebServiceType.VERSION_CONTROL_CHECK;
//        Util.checkNetworkStatus(mApp);
//        if (HealthCocoConstants.isNetworkOnline) {
//            String url = webServiceType.getUrl();
//            getResponse(webServiceType, class1, url, versionCheckRequest, null, responseListener,
//                    errorListener);
//        } else {
//            showUserOffline(webServiceType, responseListener);
//        }
//    }

    private void showUserOffline(WebServiceType webServiceType, Response.Listener<VolleyResponseBean> responseListener) {
        VolleyResponseBean volleyResponseBean = new VolleyResponseBean();
        volleyResponseBean.setWebServiceType(webServiceType);
        volleyResponseBean.setIsUserOnline(false);
        volleyResponseBean.setIsDataFromLocal(true);
        responseListener.onResponse(volleyResponseBean);
        Util.showToast(mApp.getApplicationContext(), R.string.user_offline);
    }

    public void generateToken(Class<?> class1, String emailPhone, String password, int flag, Response.Listener<VolleyResponseBean> responseListener, GsonRequest.ErrorListener errorListener) {
        WebServiceType webServiceType = WebServiceType.GENERATE_ACCESS_TOKEN;
        Util.checkNetworkStatus(mApp);
        if (isNetworkOnline) {

//            String username = oAuthDetailsRequestResponse.getUsername();
//            String password = oAuthDetailsRequestResponse.getPassword();
            String url = webServiceType.getUrl() +
                    "/" + flag + "/" + emailPhone + "/" + password;

//            String json = "{\n" +
//                    "\t\"username\": \""+ username+"\",\n" +
//                    "\t\"password\": \""+ password+"\"\n" +
//                    "}";
            Log.d("URL REQUEST", url);
            getResponseForLogin(webServiceType, class1, url, null, responseListener,
                    errorListener);
        } else {
            showUserOffline(webServiceType, responseListener);
        }
    }


    public void adminLogin(WebServiceType adminLogin, AdminLoginRequest adminLoginRequest, Class<BooleanResponse> booleanResponseClass, Response.Listener<VolleyResponseBean> responseListener, GsonRequest.ErrorListener errorListener) {


        Util.checkNetworkStatus(mApp);
        if (TechtureConstants.isNetworkOnline) {

            String url = adminLogin.getUrl();


            Log.d("URL REQUEST", url);
            getResponse(adminLogin, booleanResponseClass, url, adminLoginRequest, null, responseListener, errorListener);
        } else {
            showUserOffline(adminLogin, responseListener);
        }

    }
}


