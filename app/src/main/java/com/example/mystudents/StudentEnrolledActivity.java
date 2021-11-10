package com.example.mystudents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class StudentEnrolledActivity extends AppCompatActivity {

    TextView nameIn, courIn, yearIn, offerIn, subIn, schedIn, roomIn, teachIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_enrolled);
        nameIn = findViewById(R.id.name_tv_in);
        courIn = findViewById(R.id.cour_tv_in);
        yearIn = findViewById(R.id.year_tv_in);
        offerIn = findViewById(R.id.off_tv_in);
        subIn = findViewById(R.id.sub_tv_in);
        schedIn = findViewById(R.id.sched_tv_in);
        roomIn = findViewById(R.id.room_tv_in);
        teachIn = findViewById(R.id.teach_tv_in);

        Intent i = getIntent();

        String ID = i.getStringExtra("ID");
        String Name = i.getStringExtra("Name");

        // post our data.
        String url = "http://192.168.254.105/Students/DisplayStudentEnrolled.php";

        // creating a new variable for our request queue.
        RequestQueue q = Volley.newRequestQueue(StudentEnrolledActivity.this);

        StringRequest sr = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray oh = new JSONArray(response);

                                for(int i=0; i<oh.length(); i++) {
                                    JSONObject ob = oh.getJSONObject(i);

                                    nameIn.append(ob.getString("StudentName"));
                                    courIn.append(ob.getString("Course"));
                                    yearIn.append(ob.getString("YearLevel"));

                                    offerIn.append(ob.getString("OfferingNo"));
                                    subIn.append(ob.getString("SubjCode"));
                                    schedIn.append(ob.getString("Schedule"));
                                    roomIn.append(ob.getString("Room"));
                                    teachIn.append(ob.getString("TeacherID"));
                                }

                        } catch (JSONException e) {
                            Toast.makeText(StudentEnrolledActivity.this, "Error " + e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StudentEnrolledActivity.this, "Error " + error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("StudentID", ID);
                param.put("StudentName", Name);
                return param;
            }
        };
        q.add(sr);
    }
}