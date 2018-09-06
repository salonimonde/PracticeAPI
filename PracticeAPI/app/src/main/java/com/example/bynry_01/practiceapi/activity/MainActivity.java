package com.example.bynry_01.practiceapi.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bynry_01.practiceapi.R;
import com.example.bynry_01.practiceapi.adapters.MyJsonAdapter;
import com.example.bynry_01.practiceapi.interfaces.ApiServiceCaller;
import com.example.bynry_01.practiceapi.models.Contacts;
import com.example.bynry_01.practiceapi.utility.App;
import com.example.bynry_01.practiceapi.webservices.ApiConstants;
import com.example.bynry_01.practiceapi.webservices.JsonResponse;
import com.example.bynry_01.practiceapi.webservices.WebRequest;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ApiServiceCaller {


    private Context mContext;
    private RecyclerView recyclerView;
    private ArrayList<Contacts> mContacts = new ArrayList<>();
    Contacts classContacts = new Contacts();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mContext = this;
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        
        getDetails();
    }

    private void getDetails() {

        JsonObjectRequest request = WebRequest.callPostMethod(Request.Method.GET, null, ApiConstants.GET_DETAILS,
                ApiConstants.GET_DETAILS, this, "");
        App.getInstance().addToRequestQueue(request, ApiConstants.GET_DETAILS);
    }

    public void onAsyncSuccess(JsonResponse jsonResponse, String label){
        Log.i("Success","Success");

        switch (label){
            case ApiConstants.GET_DETAILS: {
                if (jsonResponse != null){
                    mContacts.addAll(jsonResponse.contacts);




                    MyJsonAdapter myJsonAdapter = new MyJsonAdapter(mContext,mContacts);
                    recyclerView.setAdapter(myJsonAdapter);

                }
            }
        }
    }

    @Override
    public void onAsyncFail(String message, String label, NetworkResponse response) {

        Log.i("Fai;","Fail");

    }

    @Override
    public void onAsyncCompletelyFail(String message, String label) {
        Log.i("Fail1","Fail1");

    }
}
