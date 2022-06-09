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
import com.example.ttinterview.common.RequestService;
import com.example.ttinterview.model.Categories;
import com.example.ttinterview.model.Joke;

import org.json.JSONException;
import org.json.JSONObject;

public class JokeScreen extends AppCompatActivity {
    RequestService requestService;
    TextView textView;
    String value;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase db;
    Cursor userCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView1);
        //addNewJoke();
        mDBHelper = new DatabaseHelper(this);
        mDBHelper.create_db();
        db = mDBHelper.open();
    }

/*    private void saveJokeToDB(){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_JOKE, textView.getText().toString());
        db.insert(DatabaseHelper.TABLE, null, cv);
        //db.execSQL();
        //db.close();
    }*/

    private void addNewJoke() {
        value ="nun";
        value = getIntent().getStringExtra("category");
        String url = "https://api.chucknorris.io/jokes/random?category=" + value;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //jokes.add(new Joke(response.getString("value")));
                            ContentValues cv = new ContentValues();
                            cv.put(DatabaseHelper.COLUMN_JOKE, response.getString("value"));
                            db.insert(DatabaseHelper.TABLE, null, cv);
                            textView.setText(
                                    response.getString("value")
                            );
                            //textView.setText(jokes.getJoke(i));

                            //textView.setText("Response: " + response.getString("value"));

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
        //saveJokeToDB();

    }
    private void loadFromDB(){
        userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE + " where " +
                DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(4)});
        userCursor.moveToFirst();
        textView.setText(userCursor.getString(1));
        userCursor.close();
    }
}
