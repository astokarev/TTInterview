package com.example.ttinterview.controller;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ttinterview.common.RequestService;
import com.example.ttinterview.model.Categories;
import com.example.ttinterview.model.Joke;
import com.example.ttinterview.model.JokeList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends ListActivity{
    private ArrayAdapter<String> adapterCategories;
    /*private ArrayAdapter<String> adapterLastname;
    private String name, lastName;
    private PersonList personList;
    //TextView textView;
    //Button button;
    String joke;
    JSONObject object;*/
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


    private void addNewJoke() {
        String url = "https://api.chucknorris.io/jokes/random";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jokes.add(new Joke(response.getString("value")));

                            for (int i = 0; i < jokes.getLength(); i++) {
                                //textView.setText(jokes.titles[i]);
                                //textView.setText(jokes.getJoke(i));
                            }
                            //textView.setText("Response: " + response.getString("value"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });
        requestService.getInstance(this).addToRequestQueue(jsonObjectRequest);
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

    /*public void onButtonClick(View view) {
        getCategories();
        //addNewJoke();
    }*/

   /* @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(getListAdapter() == adapterName){
            name = (String) l.getItemAtPosition(position);
            setListAdapter(adapterLastname);
            adapterLastname.notifyDataSetChanged();
        } else{
            lastName = (String) l.getItemAtPosition(position);
            setListAdapter(adapterName);
            adapterName.notifyDataSetChanged();
        }
    }*/

}