package com.example.mystudents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    RecyclerView recycler_enroll;

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
        recycler_enroll = findViewById(R.id.recycler_enroll);



        Intent i = getIntent();

        String ID = i.getStringExtra("ID");
        String Name = i.getStringExtra("Name");

        GetEnrolledDetails(ID, Name);

        // Layout for RecyclerView
        recycler_enroll.setHasFixedSize(true);
        recycler_enroll.setLayoutManager(new LinearLayoutManager(this)); //Left to Right.

    }

    private void GetEnrolledDetails(String ID, String Name){
        // post our data.
        String url = "http://192.168.254.100/Students/DisplayStudentEnrolled.php";

        // creating a new variable for our request queue.
        RequestQueue q = Volley.newRequestQueue(StudentEnrolledActivity.this);

        StringRequest sr = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject oh = new JSONObject(response);

                            //get JSON Array Node
                            JSONArray detailsarray = oh.getJSONArray("Details");

                            int size = detailsarray.length();

                                //Create Array for each key value json object
                                String[] A4 = new String[size];
                                String[] A5 = new String[size];
                                String[] A6 = new String[size];
                                String[] A7 = new String[size];
                                String[] A8 = new String[size];


                                //Get JSON key value json object once
                                JSONObject ok = detailsarray.getJSONObject(0);
                                nameIn.append(ok.getString("StudentName"));
                                courIn.append(ok.getString("Course"));
                                yearIn.append(ok.getString("YearLevel"));

                                for(int i=0; i<size; i++) {
                                    //Get JSON key value json each time it loop
                                    JSONObject ob = detailsarray.getJSONObject(i);

                                    //Store each value on there each perspective array.
                                    A4[i] = ob.getString("OfferingNo");
                                    A5[i] = ob.getString("SubjCode");
                                    A6[i] = ob.getString("Schedule");
                                    A7[i] = ob.getString("Room");
                                    A8[i] = ob.getString("TeacherID");

                                    //Pass the value thru param as argument.
                                    GetDetails(size,A4,A5,A6,A7,A8);
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

    private void GetDetails(int Count,String[] offer, String[] sub, String[] sched, String[] room, String[] teach){
        //Stores each value on each perspective array.
        String[] OfferArr = new String[Count];
        String[] SubArr = new String[Count];
        String[] SchedArr = new String[Count];
        String[] RoomArr = new String[Count];
        String[] TeactArr = new String[Count];

        int size = OfferArr.length; //instantiate the value as size.

        for(int i=0;i<size;i++){ //Stack each at a time every loop.
            OfferArr[i] = offer[i];
            SubArr[i] = sub[i];
            SchedArr[i] = sched[i];
            RoomArr[i] = room[i];
            TeactArr[i] = teach[i];
        }

        //Pass the Whole value Array on Constructor array to MainAdapter
        EnrolledAdapter ada = new EnrolledAdapter(this,OfferArr,SubArr,SchedArr,RoomArr,TeactArr);
        recycler_enroll.setAdapter(ada);
    }
}