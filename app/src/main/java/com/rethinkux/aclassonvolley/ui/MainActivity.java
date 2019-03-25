package com.rethinkux.aclassonvolley.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.rethinkux.aclassonvolley.R;
import com.rethinkux.aclassonvolley.adapters.UsersAdapter;
import com.rethinkux.aclassonvolley.models.Phone;
import com.rethinkux.aclassonvolley.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =  "MainActivity";
    String URL_JSONREQUEST = "https://api.androidhive.info/volley/person_array.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lvUsers = findViewById(R.id.listOfUsers);

//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_JSONREQUEST, null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        Log.d(TAG, "onResponse: "+ response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "onErrorResponse: "+ error);
//            }
//        });
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonObjectRequest);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_JSONREQUEST, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "onResponse: "+ response);

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

                        // pass context and list of users to UserAdapter
                        UsersAdapter adapter = new UsersAdapter(MainActivity.this, users);
                        lvUsers.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "onErrorResponse: "+ error);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
