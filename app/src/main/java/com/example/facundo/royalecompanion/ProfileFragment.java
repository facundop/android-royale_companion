package com.example.facundo.royalecompanion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.facundo.royalecompanion.domain.profile.Profile;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ProfileFragment extends Fragment {

    private TextView playerDetails;
    private Button getProfileButton;
    private String apiKey = BuildConfig.ApiKey;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);

        playerDetails = (TextView) view.findViewById(R.id.player_details_text);
        getProfileButton = (Button) view.findViewById(R.id.get_profile_button);
        getProfileButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                loadPlayerProfile();
            }
        });

        return view;
    }

    private void loadPlayerProfile() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String playerTag = "%23" + "2LRRQPPJC";
        String url = "https://api.clashroyale.com/v1/players/" + playerTag;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        Profile profileResponse = gson.fromJson(response, Profile.class);
                        loadProfile(profileResponse);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showError();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("authorization", "Bearer " + apiKey);
                params.put("Accept", "application/json");

                return params;
            }
        };

        queue.add(stringRequest);
    }

    private void loadProfile(Profile profileResponse) {
        playerDetails.setText(profileResponse.getName() + " " + profileResponse.getClan().getName());
    }

    private void showError() {
        Toast.makeText(getActivity(), "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
    }

}
