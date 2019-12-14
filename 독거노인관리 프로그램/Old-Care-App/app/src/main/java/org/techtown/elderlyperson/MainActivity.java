package org.techtown.elderlyperson;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    APIService apiService;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;
    Fragment5 fragment5;
    private static final String BASE = "http://192.168.0.4:8000";
    //private static final String BASE = "http://192.168.200.138:8000";
    static int as;
    NotificationManager manager;
    private static String CHANNEL_ID2 = "channel2";
    private static String CHANNEL_NAME2 = "Channel2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();
        fragment5 = new Fragment5();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment1).commit();
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        //   Toast.makeText(getApplicationContext(), "첫 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment1).commit();

                        return true;
                    case R.id.tab2:
                        // Toast.makeText(getApplicationContext(), "두 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment2).commit();

                        return true;
                    case R.id.tab3:
                        // Toast.makeText(getApplicationContext(), "세 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment3).commit();

                        return true;
                    case R.id.tab4:
                        //  Toast.makeText(getApplicationContext(), "네 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment4).commit();
                        return true;

                    case R.id.tab5:
                        // Toast.makeText(getApplicationContext(), "다섯 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, fragment5).commit();

                        return true;
                }
                return false;
            }
        });

        apiService = retrofit.create(APIService.class);
        Call<Person> content = apiService.per();
        content.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                final Person co1 = response.body();
//                Toast.makeText(getContext(),co1.getUser().getName()+"",Toast.LENGTH_SHORT).show();
                int size = co1.getTahData().size();
                int size1 = co1.getHumanDetect().size();
                String[] temp = new String[size];
                Log.d("test1", co1.getTahData().size() + "");
                temp[size - 1] = co1.getTahData().get(size - 1).getTemp();
                as = Integer.parseInt(temp[size - 1]);
                if (as >= 25) {
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                    NotificationCompat.Builder builder = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        if (manager.getNotificationChannel(CHANNEL_ID2) == null) {
                            manager.createNotificationChannel(new NotificationChannel(
                                    CHANNEL_ID2, CHANNEL_NAME2, NotificationManager.IMPORTANCE_DEFAULT
                            ));
                            builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID2);
                        }
                    } else {
                        builder = new NotificationCompat.Builder(getApplicationContext());
                    }
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 101, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                    builder.setContentTitle("위험");
                    builder.setContentText("온도가 높습니다.");
                    builder.setSmallIcon(android.R.drawable.ic_menu_view);
                    builder.setAutoCancel(true);
                    builder.setContentIntent(pendingIntent);
                    Notification noti = builder.build();
                    manager.notify(2, noti);
                    if (Build.VERSION.SDK_INT >= 26) {
                        vibrator.vibrate(VibrationEffect.createOneShot(1000, 10));
                    } else {
                        vibrator.vibrate(1000);
                    }
                }
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

            }
        });

        if (as >= 25) {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            if (Build.VERSION.SDK_INT >= 26) {
                vibrator.vibrate(VibrationEffect.createOneShot(1000, 10));
            } else {
                vibrator.vibrate(2000);
            }
        }
    }

    public void init() {
        retrofit = new Retrofit.Builder().baseUrl(BASE).addConverterFactory(GsonConverterFactory.create()).build();
    }

}
