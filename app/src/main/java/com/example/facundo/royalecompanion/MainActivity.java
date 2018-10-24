package com.example.facundo.royalecompanion;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements RewardedVideoAdListener {

    private RewardedVideoAd mRewardedVideoAd;
    private TextView mTextMessage;
    private Button mButtonGetUpcomingChests;
    private ListView listUpcomingChestsView;
    private String apiKey = BuildConfig.ApiKey;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_profile:
                    mTextMessage.setVisibility(View.VISIBLE);
                    mTextMessage.setText(R.string.title_profile);
                    mButtonGetUpcomingChests.setVisibility(View.GONE);
                    listUpcomingChestsView.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_chests:
                    mTextMessage.setVisibility(View.GONE);
                    listUpcomingChestsView.setVisibility(View.VISIBLE);
                    mButtonGetUpcomingChests.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setVisibility(View.VISIBLE);
                    mTextMessage.setText(R.string.title_settings);
                    mButtonGetUpcomingChests.setVisibility(View.GONE);
                    listUpcomingChestsView.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-1088902000251944~1740402091");
        // Use an activity context to get the rewarded video instance.
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);

        mTextMessage = (TextView) findViewById(R.id.message);
        listUpcomingChestsView = (ListView) findViewById(R.id.list_upcoming_chests);
        listUpcomingChestsView.setVisibility(View.GONE);
        mButtonGetUpcomingChests = (Button) findViewById(R.id.get_upcoming_chests_button);
        mButtonGetUpcomingChests.setVisibility(View.GONE);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadRewardedVideoAd();
    }

    public void getUpcomingChestsClick(android.view.View view) {
        if (mRewardedVideoAd.isLoaded()) {
            mRewardedVideoAd.show();
        }
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
        RequestQueue queue = Volley.newRequestQueue(this);
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
                        // TODO: Toast ERROR
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

    private void loadUpcomingChests(List<ChestItem> upcomingChests) {
        final UpcomingChestsAdapter adapter = new UpcomingChestsAdapter(this, upcomingChests);
        listUpcomingChestsView.setAdapter(adapter);
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {
        //Toast.makeText(this, "onRewardedVideoAdLeftApplication", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        //Toast.makeText(this, "onRewardedVideoAdClosed", Toast.LENGTH_SHORT).show();
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //Toast.makeText(this, "onRewardedVideoAdLoaded", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoAdOpened() {
        //Toast.makeText(this, "onRewardedVideoAdOpened", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoStarted() {
        //Toast.makeText(this, "onRewardedVideoStarted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRewardedVideoCompleted() {
        //Toast.makeText(this, "onRewardedVideoCompleted", Toast.LENGTH_SHORT).show();
    }

}
