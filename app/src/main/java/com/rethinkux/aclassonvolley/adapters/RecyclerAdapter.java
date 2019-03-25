package com.rethinkux.aclassonvolley.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rethinkux.aclassonvolley.R;
import com.rethinkux.aclassonvolley.models.User;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context mContext;
    private List<User> mUsers;

    public RecyclerAdapter(Context context, List<User> users){
        mContext = context;
        mUsers = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_user, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.name.setText(mUsers.get(i).getName());
        myViewHolder.email.setText(mUsers.get(i).getEmail());
        myViewHolder.home.setText(mUsers.get(i).getPhone().getHome());
        myViewHolder.mobile.setText(mUsers.get(i).getPhone().getMobile());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    TextView name, email, home, mobile;

    public MyViewHolder(View view) {
        super(view);

        name = view.findViewById(R.id.textUser);
        email = view.findViewById(R.id.textEmail);
        home = view.findViewById(R.id.textHome);
        mobile = view.findViewById(R.id.textMobile);
    }
}
