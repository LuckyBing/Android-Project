package com.coolweather.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.coolweather.android.gson.Basic;
import com.coolweather.android.gson.Forecast;
import com.coolweather.android.gson.Lifestyle;
import com.coolweather.android.gson.Now;
import com.coolweather.android.gson.Weather;
import com.coolweather.android.service.AutoUpdateService;
import com.coolweather.android.util.HttpUtil;
import com.coolweather.android.util.Utility;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
/*
天气界面
 */
public class WeatherActivity extends AppCompatActivity {

    public DrawerLayout drawerLayout;

    public SwipeRefreshLayout swipeRefresh;

    private ScrollView weatherLayout;

    private Button navButton;

    private TextView titleCity;

    private TextView titleUpdateTime;

    private TextView degreeText;

    private TextView weatherInfoText;

    private LinearLayout forecastLayout;

    private TextView aqiText;

    private TextView pm25Text;

    private TextView comfortText;

    private TextView carWashText;

    private TextView sportText;

    private ImageView bingPicImg;

    private String mWeatherId;
    private String levelId;
    //private String levelId1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_weather);
        // 初始化各控件
        bingPicImg = (ImageView) findViewById(R.id.bing_pic_img);
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.title_city);
        titleUpdateTime = (TextView) findViewById(R.id.title_update_time);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        aqiText = (TextView) findViewById(R.id.aqi_text);
        pm25Text = (TextView) findViewById(R.id.pm25_text);
        comfortText = (TextView) findViewById(R.id.comfort_text);
        carWashText = (TextView) findViewById(R.id.car_wash_text);
        sportText = (TextView) findViewById(R.id.sport_text);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navButton = (Button) findViewById(R.id.nav_button);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = prefs.getString("weather", null);
       if (weatherString != null) {
            //有缓存时直接解析天气数据
            Weather weather = Utility.handleWeatherResponse(weatherString);
            mWeatherId = weather.basic.cName;
            levelId=weather.basic.pName;
            showWeatherInfo(weather);
        } else {
            // 无缓存时去服务器查询天气
            String cityname1=getIntent().getStringExtra("cityname");
            String levelId1=getIntent().getStringExtra("upper");
            //System.out.println(mWeatherId);
            weatherLayout.setVisibility(View.INVISIBLE);
            requestWeather(cityname1,levelId1);
       }
       /*
       手动刷新
        */
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(mWeatherId,levelId);
                Log.d("dsff",mWeatherId);
                Log.d("sdjf",levelId);
            }
        });
        navButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        String bingPic = prefs.getString("bing_pic", null);
        if (bingPic != null) {
            Glide.with(this).load(bingPic).into(bingPicImg);
        } else {
            loadBingPic();
        }
    }

    /**
     * 根据天气id请求城市天气信息。
     */
    public void requestWeather(final String weatherId,final String level) {
        String weatherUrl = "https://free-api.heweather.net/s6/weather?location=" + weatherId +"&location="+level+"&key=678d37f14b944d90834582ae366d9d4a";
        //Log.d("dsfsfd",weatherUrl);
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            mWeatherId = weather.basic.cName;
                            levelId=weather.basic.pName;
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();
    }
                //Log.d("kjfdk","safs");

                /*try {
                    JSONObject jsonObject = new JSONObject(responseText);
                    JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
                    String basicinfo=jsonArray.getJSONObject(0).getString("basic");
                    final Basic basic=new Gson().fromJson(basicinfo, Basic.class);
                    Log.d("dsjk",basicinfo);
                    String nowinfo=jsonArray.getJSONObject(0).getString("now");
                    final Now now=new Gson().fromJson(nowinfo, Now.class);
                    Log.d("dssjk",nowinfo);
                    String forecastinfo=jsonArray.getJSONObject(0).getString("daily_forecast");
                    final Forecast forecast=new Gson().fromJson(forecastinfo, Forecast.class);
                    Log.d("dssjgk",forecastinfo);
                    String lifestyleinfo=jsonArray.getJSONObject(0).getString("lifestyle");
                    final Lifestyle lifestyle=new Gson().fromJson(lifestyleinfo, Lifestyle.class);
                    Log.d("dssjgqk",lifestyleinfo);
                    final String status=jsonArray.getJSONObject(0).getString("status");
                    Log.d("rgd",status);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (basic != null && "ok".equals(status)) {
                                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                                editor.putString("weather", responseText);
                                editor.apply();
                                mWeatherId = basic.cName;
                                showWeatherInfo(basic,now,forecast,lifestyle);
                            } else {
                                Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                            }
                            swipeRefresh.setRefreshing(false);
                        }
                    });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
        loadBingPic();
    }*/

    /**
     * 加载必应每日一图
     */
    private void loadBingPic() {
        String requestBingPic = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(requestBingPic, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String bingPic = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("bing_pic", bingPic);
                editor.apply();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(bingPic).into(bingPicImg);
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 处理并展示Weather实体类中的数据。
     */
    private void showWeatherInfo(Weather weather) {
        String cityName = weather.basic.cName;
        String updateTime = "2019-12-29";
        String degree = weather.now.temperature + "℃";
        String weatherInfo = weather.now.weathercase;
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();
        for (Forecast cast : weather.forecasts) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            TextView minText = (TextView) view.findViewById(R.id.min_text);
            dateText.setText(cast.date);
            infoText.setText(cast.lightinfo);
            maxText.setText(cast.min);
//            Log.d("sa",cast.max);
            minText.setText(cast.max);
            forecastLayout.addView(view);
        }
        aqiText.setText(weather.now.hum);
        pm25Text.setText(weather.now.visiable);
        String comfort = "舒适度：" + weather.lifestyles.get(0).brif;
        String carWash = "穿衣建议：" + weather.lifestyles.get(1).info;
        String sport = "流感情况：" + weather.lifestyles.get(2).info;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, AutoUpdateService.class);
        startService(intent);
    }

}
