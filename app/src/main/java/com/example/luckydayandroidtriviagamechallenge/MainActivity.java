package com.example.luckydayandroidtriviagamechallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private List<Result> results = new ArrayList<>();
    private ViewPagerAdapter viewPagerAdapter;
    private GenericCallback<Boolean> isButtonsDisabledForPagerCallback;
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;
    private String url = "https://opentdb.com/api.php?amount=20&type=boolean";
    private Handler handler = new Handler();
    private int delay = 5000;
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.view_pager);
        requestQueue = Volley.newRequestQueue(this);
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray jsonArray;
                try {
                    jsonArray = response.optJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        String category = jsonObject.optString("category");
                        Log.e("Category", category);
                        String type = jsonObject.optString("type");
                        Log.e("Type", type);
                        String difficulty = jsonObject.optString("difficulty");
                        Log.e("Difficulty", difficulty);
                        String question = jsonObject.optString("question");
                        Log.e("Question", question);
                        String correctAnswer = jsonObject.optString("correct_answer");
                        Log.e("CorrectAnswer", correctAnswer);
                        JSONArray jsonArrayIncorrectAnswers = jsonObject.optJSONArray("incorrect_answers");
                        List<String> incorrectAnswers = new ArrayList<>();
                        for (int j = 0; j < jsonArrayIncorrectAnswers.length(); j++) {
                            incorrectAnswers.add(jsonArrayIncorrectAnswers.optString(j));
                            Log.e("IncorrectAnswer", jsonArrayIncorrectAnswers.optString(j));
                        }
                        results.add(new Result(category, type, difficulty, question, correctAnswer, incorrectAnswers));
                    }
                    isButtonsDisabledForPagerCallback = new GenericCallback<Boolean>() {
                        @Override
                        public void onResponse(final Boolean isButtonsDisabled) {
                            if (isButtonsDisabled) {
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (viewPagerAdapter.getCount() == page) {
                                            page = 0;
                                        } else {
                                            page++;
                                        }
                                        viewPager.setCurrentItem(page, true);
                                    }
                                }, delay);
                            }
                        }
                    };
                    viewPagerAdapter = new ViewPagerAdapter(MainActivity.this, results, isButtonsDisabledForPagerCallback);
                    viewPager.setAdapter(viewPagerAdapter);
                    viewPager.setOffscreenPageLimit(10);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            ;
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
