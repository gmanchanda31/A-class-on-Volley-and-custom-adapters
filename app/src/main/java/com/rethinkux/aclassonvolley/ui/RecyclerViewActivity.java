package com.rethinkux.aclassonvolley.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rethinkux.aclassonvolley.R;
import com.rethinkux.aclassonvolley.adapters.RecyclerAdapter;
import com.rethinkux.aclassonvolley.models.Phone;
import com.rethinkux.aclassonvolley.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private static final String GET_USERS = "https://api.androidhive.info/volley/person_array.json";

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mRecyclerView = findViewById(R.id.rvUsers);

        fetchUsers(GET_USERS);
    }

    private void fetchUsers(String url) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                convertJsonToJava(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(RecyclerViewActivity.this);
        requestQueue.add(jsonArrayRequest);
    }

    private void convertJsonToJava(JSONArray response) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i <= users.size(); i++){
            try {
                JSONObject userJsonObject = response.getJSONObject(i);
                User user = new User();

                Phone phone = new Phone();
                String name = userJsonObject.getString("name");
                String email = userJsonObject.getString("email");
                JSONObject phoneJsonObject = userJsonObject.getJSONObject("phone");
                String home = phoneJsonObject.getString("home");
                String mobile  = phoneJsonObject.getString("mobile");
                phone.setHome(home);
                phone.setMobile(mobile);
                user.setName(name);
                user.setEmail(email);
                user.setPhone(phone);

                users.add(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        RecyclerAdapter adapter = new RecyclerAdapter(RecyclerViewActivity.this, users);
        mRecyclerView.setAdapter(adapter);

    }
}
