package com.example.ttinterview;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.ttinterview.common.RequestService;
import com.example.ttinterview.model.Joke;
import com.example.ttinterview.model.JokeList;
import com.example.ttinterview.model.PersonList;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapterName;
    private ArrayAdapter<String> adapterLastname;
    private String name, lastName;
    private PersonList personList;
    TextView textView;
    //Button button;
    String joke;
    JSONObject object;
    RequestService requestService;
    JokeList jokes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView1);
        jokes = new JokeList();
        //addNewJoke();

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

                            for(int i=0; i<jokes.getLength();i++) {
                                //textView.setText(jokes.titles[i]);
                                textView.setText(jokes.getJoke(i));
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

    public void onButtonClick(View view) {
        addNewJoke();
    }

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