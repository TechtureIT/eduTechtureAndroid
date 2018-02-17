package techture.edutechture.techture;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Akshay on 31-07-2017.
 */

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "fcmsharedprefdemo";
    private static final String KEY_ACCESS_TOEKN = "token";
    private static Context ctx;

    private static SharedPrefManager mInstance;

    private SharedPrefManager(Context context){
        ctx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){

        if(mInstance==null){
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;

    }

    public boolean storeToken(String token){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ACCESS_TOEKN, token);
        editor.apply();
        return true;

    }

    public String getToken(){

        SharedPreferences sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getString(KEY_ACCESS_TOEKN, null);

    }
}
