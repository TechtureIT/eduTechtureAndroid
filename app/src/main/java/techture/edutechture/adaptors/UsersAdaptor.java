package techture.edutechture.adaptors;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import techture.edutechture.R;
import techture.edutechture.bean.UserInstructor;

/**
 * Created by Akshay on 02/17/18.
 */

public class UsersAdaptor extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<UserInstructor> listUsers;

    public UsersAdaptor(Activity activity, List<UserInstructor> listUsers) {
        this.activity = activity;
        this.listUsers = listUsers;

    }


    @Override
    public int getCount() {
        return listUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return listUsers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listUsers.get(i).getUniqueId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {


        convertView = activity.getLayoutInflater().inflate(R.layout.item_user,viewGroup,false);

        TextView txtUserName = (TextView)convertView.findViewById(R.id.txt_user_name);
        TextView txtUserEmail = (TextView)convertView.findViewById(R.id.txt_user_email);
        TextView txtUserPhone = (TextView)convertView.findViewById(R.id.txt_user_phone);


        UserInstructor userInstructor  = listUsers.get(i);


        txtUserName.setText(userInstructor.getFname()+ "  "+ userInstructor.getLname());
        txtUserEmail.setText(userInstructor.getEmail()+"");
        txtUserPhone.setText(userInstructor.getContact());


        return convertView;
    }

}
