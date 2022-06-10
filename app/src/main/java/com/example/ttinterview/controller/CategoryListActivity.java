package com.example.ttinterview.controller;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.ttinterview.R;
import com.example.ttinterview.common.RequestService;
import com.example.ttinterview.model.Categories;

import org.json.JSONArray;
import org.json.JSONException;

public class CategoryListActivity extends ListActivity {

    ArrayAdapter adapterCategories;
    RequestService requestService;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        getCategories(); //async process!

    }


    private void getCategories() {
        Categories categories = new Categories();
        String url = "https://api.chucknorris.io/jokes/categories";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {        //При удачном подключении получаем категории по запросу
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                categories.add(response.getString(i));
                            }
                            adapterCategories = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, categories.categoriesArray());
                            setListAdapter(adapterCategories);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        adapterCategories = new ArrayAdapter(context, android.R.layout.simple_list_item_1, categories.offlineCategories());
                        setListAdapter(adapterCategories);
                    }
                });
        requestService.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String categoryName = l.getItemAtPosition(position).toString();
        Intent jokeIntent = new Intent(this, JokeScreen.class);
        jokeIntent.putExtra("category", categoryName);
        startActivity(jokeIntent);
    }

}