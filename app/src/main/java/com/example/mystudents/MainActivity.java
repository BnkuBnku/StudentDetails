package com.example.mystudents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // variable for RecyclerView
    private RecyclerView recycler_v;

    // variable for Adapter
    private EnrollmentAdapter adapt;

    // variable for button
    private Button BClear;
    private Button BSearch;

    // variable for EditText
    private EditText IDText;
    private EditText NameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing all our variables
        recycler_v = findViewById(R.id.recycler_v);
        IDText = findViewById(R.id.editTextTextPersonName);
        NameText = findViewById(R.id.editTextTextPersonName2);
        BClear = findViewById(R.id.buttonClear);
        BSearch = findViewById(R.id.buttonSearch);



        // adding click listener for our button
        BSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the Edittext field empty or not.
                if(TextUtils.isEmpty(IDText.getText().toString()) && TextUtils.isEmpty(NameText.getText().toString())){
                    Toast.makeText(MainActivity.this, "Please Enter Student's ID and Name", Toast.LENGTH_LONG).show();

                } else {
                    getStudentDetails(IDText.getText().toString(), NameText.getText().toString());
                }
            }
        });

        BClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IDText.getText().clear();
                NameText.getText().clear();
                Toast.makeText(getBaseContext(), "Clear Successfully", Toast.LENGTH_SHORT).show();
            }
        });

        // Layout for RecyclerView
        recycler_v.setHasFixedSize(true);
        recycler_v.setLayoutManager(new LinearLayoutManager(this)); //Left to Right.
    }

    private void getStudentDetails(String sid, String stn) {
        // post our data.
        String url = "http://192.168.254.105/Students/DisplayIDName.php";

        // creating a new variable for our request queue.
        RequestQueue q = Volley.newRequestQueue(MainActivity.this);


        StringRequest r = new StringRequest(
            Request.Method.POST,
            url,
            new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {



                    // Try display results
                    JSONObject parent = new JSONObject(response);

                    //get JSON Array Node
                    JSONArray j = parent.getJSONArray("Student");

                    StudentID[] student = new StudentID[10];
                        for(int i=0; i<j.length(); i++){

                            student[i] = new StudentID();

                            JSONObject ob = j.getJSONObject(i);

                            student[i].Student_ID = ob.optString("StudentID");
                            student[i].Student_Name = ob.optString("StudentName");

                    }



                    adapt = new EnrollmentAdapter(this, );
                    recycler_v.setAdapter(adapt);





                } catch (JSONException e) { //Display Error if any
                    Toast.makeText(getBaseContext(),"ERROR \n\n" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        },
            new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getBaseContext(), "Failed to Search. \n\n" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("SI", sid);
                param.put("ST", stn);
                return param;
            }
        };
        q.add(r);
    }

}