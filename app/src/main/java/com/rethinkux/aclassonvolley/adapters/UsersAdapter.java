package com.rethinkux.aclassonvolley.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rethinkux.aclassonvolley.R;
import com.rethinkux.aclassonvolley.models.User;

import java.util.List;

public class UsersAdapter extends BaseAdapter {

    private Context mContext;
    private List<User> mUsers;

    public UsersAdapter(Context context, List<User> users){
        mContext = context;
        mUsers = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.item_user, parent, false);
        TextView name, email, home, mobile;
        name = convertView.findViewById(R.id.textUser);
        email = convertView.findViewById(R.id.textEmail);
        home = convertView.findViewById(R.id.textHome);
        mobile = convertView.findViewById(R.id.textMobile);

        name.setText(mUsers.get(position).getName());
        email.setText(mUsers.get(position).getEmail());
        home.setText(mUsers.get(position).getPhone().getHome());
        return convertView;
    }

    @Override
    public int getCount() {
        return mUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
