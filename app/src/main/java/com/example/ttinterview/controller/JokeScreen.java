package com.example.ttinterview.controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ttinterview.R;
import com.example.ttinterview.common.DatabaseHelper;
import com.example.ttinterview.common.RequestService;

import org.json.JSONException;
import org.json.JSONObject;

public class JokeScreen extends AppCompatActivity {
    RequestService requestService;
    TextView textView;

    private DatabaseHelper mDBHelper;
    private SQLiteDatabase jokesDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView1);
        mDBHelper = new DatabaseHelper(this);
        mDBHelper.create_db();
        jokesDb = mDBHelper.open();
        setTitle(getIntent().getStringExtra("category"));
        addNewJoke();
    }

    private void addNewJoke() {
        String categoryName = getIntent().getStringExtra("category");
        String url = "https://api.chucknorris.io/jokes/random?category=" + categoryName;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ContentValues cv = new ContentValues();
                            cv.put(DatabaseHelper.COLUMN_JOKE, response.getString("value"));
                            jokesDb.insert(DatabaseHelper.JOKE_TABLE, null, cv);
                            textView.setText(
                                    response.getString("value")
                            );

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        loadFromDB();
                    }
                });
        requestService.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public void onButtonClick(View view) {
        addNewJoke();
    }

    private void loadFromDB() {
        Cursor cursor = jokesDb.rawQuery("select * from " + DatabaseHelper.JOKE_TABLE, null);
        cursor.moveToLast();
        textView.setText("///NETWORK ERROR! While you're checking your internet connection, read this previously loaded joke: " + "\n"
                + cursor.getString(1));
        cursor.close();
    }
}
