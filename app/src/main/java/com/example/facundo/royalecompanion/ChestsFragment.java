package com.example.facundo.royalecompanion;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChestsFragment extends Fragment implements RewardedVideoAdListener {

    private RewardedVideoAd mRewardedVideoAd;
    private ListView listUpcomingChestsView;
    private String apiKey = BuildConfig.ApiKey;

    public ChestsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chests_fragment, container, false);

        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getActivity());
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        listUpcomingChestsView = view.findViewById(R.id.list_upcoming_chests);
        Button button = (Button) view.findViewById(R.id.get_upcoming_chests_button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        });

        loadRewardedVideoAd();

        return view;
    }

    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewarded(RewardItem reward) {
        //Toast.makeText(this, "onRewarded! currency: " + reward.getType() + "  amount: " + reward.getAmount(), Toast.LENGTH_SHORT).show();

        // Reward the user.

        // TODO: Receive Player TAG from User input
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String playerTag = "%23" + "2LRRQPPJC";
        String url = "https://api.clashroyale.com/v1/players/" + playerTag + "/upcomingchests";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        UpcomingChestsResponse upcomingChestsResponse = gson.fromJson(response, UpcomingChestsResponse.class);
                        loadUpcomingChests(upcomingChestsResponse.getItems());
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

    private void showError() {
        Toast.makeText(getActivity(), "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
    }

    private void loadUpcomingChests(List<ChestItem> upcomingChests) {
        final UpcomingChestsAdapter adapter = new UpcomingChestsAdapter(getActivity(), upcomingChests);
        listUpcomingChestsView.setAdapter(adapter);
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        Toast.makeText(getActivity(), "An error occurred. Please try again.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() { loadRewardedVideoAd(); }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdLoaded() {}

    @Override
    public void onRewardedVideoAdOpened() {}

    @Override
    public void onRewardedVideoStarted() {}

    @Override
    public void onRewardedVideoCompleted() {}

}
