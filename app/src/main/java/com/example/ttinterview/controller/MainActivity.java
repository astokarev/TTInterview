package com.example.ttinterview.controller;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ttinterview.R;
import com.example.ttinterview.common.RequestService;
import com.example.ttinterview.model.Categories;
import com.example.ttinterview.model.Joke;
import com.example.ttinterview.model.JokeList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends ListActivity{

    TextView textView;
    Button button;
    ArrayAdapter adapterCategories;
    RequestService requestService;
    JokeList jokes;
    //String categories[];
    Categories categories;
    Context context;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //setContentView(R.layout.activity_main);
        categories = new Categories();

        getCategories(); //async process!
        //addNewJoke();

        adapterCategories = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories.categoriesArray());
        //adapterCategories = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories.categoriesArray());
        //setListAdapter(adapterCategories);
        //singleton.init(this);
        //getData("https://api.chucknorris.io/jokes/random");

/*        try {
            personList = new PersonList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        adapterName = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, personList.nameList());
        adapterLastname = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, personList.lastNameList());*/
        //setListAdapter(adapterName);
    }




    private void getCategories() {
        //categories = new Categories();
        String url = "https://api.chucknorris.io/jokes/categories";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for(int i=0; i<response.length(); i++) {
                                categories.add(response.getString(i));
                            }
                            //Response.Listener<JSONArray> temp = this;
                            adapterCategories = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, categories.categoriesArray());
                            setListAdapter(adapterCategories);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //textView.setText("ERROR! "+ error.toString());

                    }
                });
        requestService.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String value = categories.getCategory(position);
       Intent intent = new Intent(this, JokeScreen.class);
       intent.putExtra("category", value);
       startActivity(intent);
        //setContentView(R.layout.activity_main);
    }

}