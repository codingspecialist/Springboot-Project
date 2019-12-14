package org.techtown.elderlyperson;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment4 extends Fragment {
Retrofit retrofit;
APIService apiService;
TextView textView9;
    private static final String BASE = "http://192.168.0.4:8000";
  // private static final String BASE = "http://192.168.200.138:8000";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment4, container, false);
        init();
        apiService = retrofit.create(APIService.class);
        Call<Person> content = apiService.per();
        textView9 = (TextView)v.findViewById(R.id.textView9);
        content.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                final Person co1 = response.body();
                String a=co1.getGasDetect().getGasCheck();
                if(a.equals("true")){
                    textView9.setText("가스 노출!!!");
                }
                else if(a.equals("false")){
                    textView9.setText("가스 위험이 없습니다.");
                }

            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

            }
        });
      final   SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.sr_layout4);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiService = retrofit.create(APIService.class);
                Call<Person> content = apiService.per();
                textView9 = (TextView)v.findViewById(R.id.textView9);
                content.enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        final Person co1 = response.body();
                        String a=co1.getGasDetect().getGasCheck();
                        if(a.equals("true")){
                            textView9.setText("가스 노출!!!");
                        }
                        else if(a.equals("false")){
                            textView9.setText("가스 위험이 없습니다.");
                        }

                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {

                    }
                });
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(),"변경",Toast.LENGTH_SHORT);
            }
        });
        return v;
    }
    public void init() {
        retrofit = new Retrofit.Builder().baseUrl(BASE).addConverterFactory(GsonConverterFactory.create()).build();
    }

}
