package com.example.ttinterview.model;

import android.widget.Toast;

import com.example.ttinterview.common.ModelObserver;
import com.example.ttinterview.common.ObservableModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class PersonList {
    private ArrayList<Person> persons;
    private Random rnd = new Random();

    public PersonList() throws Exception {
        persons = new ArrayList<>();
        generatePersons();
    }


    private void generatePersons() throws Exception {
        String[] namesDict = new String[]{"Петров", "Иванов", "Сидоров", "Васечкин", "Оношко", "Хрюшко", "Брюшко"};
        String[] lastnameDict = new String[]{"Петров", "Иванов", "Сидоров", "Васечкин", "Оношко", "Хрюшко", "Брюшко"};
        for (int i = 0; i < 20; i++) {
            persons.add(new Person(namesDict[rnd.nextInt(7)], lastnameDict[rnd.nextInt(7)]));
        }
    }

    public String[] nameList() {
        //ArrayList<String> result = new ArrayList<>();
        String[] result = new String[persons.toArray().length];
        for (int i = 0; i < persons.toArray().length; i++) {
            result[i] = persons.get(i).name;
        }
        return result;
    }

    public String[] lastNameList() {
        String[] result = new String[persons.toArray().length];
        for (int i = 0; i < persons.toArray().length; i++) {
            result[i] = persons.get(i).lastName;
        }
        return result;
    }

    public static JSONObject doGet(String urlString) throws IOException, JSONException {
        HttpURLConnection conn = null;
        URL url = new URL(urlString);
        conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        conn.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while ((line = br.readLine()) != null){
            sb.append(line + "\n");
        }
        br.close();

        String jsonString = sb.toString();
        System.out.println(jsonString);

        return new JSONObject(jsonString);
    }
}

