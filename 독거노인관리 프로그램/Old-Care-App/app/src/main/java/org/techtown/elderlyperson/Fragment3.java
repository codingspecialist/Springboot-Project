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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment3 extends Fragment {
    RecyclerView recyclerView;
    tahDataAdapter tahdataAdapter;
    Retrofit retrofit;
    APIService apiService;
    TextView sangse1;
    private static final String BASE = "http://192.168.0.4:8000";
    //private static final String BASE = "http://192.168.200.138:8000";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);
        init();
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        sangse1 = (TextView)v.findViewById(R.id.sangse1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        recyclerView.setLayoutManager(layoutManager);
        tahdataAdapter = new tahDataAdapter();
        apiService = retrofit.create(APIService.class);
        Call<Person> content = apiService.per();
        content.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                final Person co1 = response.body();
                int size = co1.getTahData().size();
                String[] humi = new String[size];
                String[] create = new String[size];
                String[] temp = new String[size];
                String[] chong = new String[size];
                tahdataAdapter = new tahDataAdapter();
                for(int i=0;i<size;i++){
                    humi[i] = co1.getTahData().get(i).getHumi();
                    create[i] = co1.getTahData().get(i).getCreateDate();
                    temp[i] = co1.getTahData().get(i).getTemp();
                    String[] test = create[i].split("T");
                    String[] test2 = create[i].split("-");
                    String[] test3 = test2[2].split("T");
                    String[]test1 = test[1].split(":");
                     chong[i] = test2[1] + "-" + test3[0] +" "+ test1[0] + ":" + test1[1];
                    recyclerView =v.findViewById(R.id.recyclerView);
                    if(!temp[i].equals("0")&&!humi[i].equals("0")) {
                        tahdataAdapter.addItem(new tahData(chong[i], temp[i], humi[i]));
                        recyclerView.setAdapter(tahdataAdapter);
                    }
                }

            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

            }
        });
    final     SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.sr_layout3);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiService = retrofit.create(APIService.class);
                Call<Person> content = apiService.per();
                content.enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        final Person co1 = response.body();
                        int size = co1.getTahData().size();
                        String[] humi = new String[size];
                        String[] create = new String[size];
                        String[] temp = new String[size];
                        String[] chong = new String[size];
                        tahdataAdapter = new tahDataAdapter();
                        for(int i=0;i<size;i++){
                            humi[i] = co1.getTahData().get(i).getHumi();
                            create[i] = co1.getTahData().get(i).getCreateDate();
                            temp[i] = co1.getTahData().get(i).getTemp();
                            String[] test = create[i].split("T");
                            String[] test2 = create[i].split("-");
                            String[] test3 = test2[2].split("T");
                            String[]test1 = test[1].split(":");
                            chong[i] = test2[1] + "-" + test3[0] +" "+ test1[0] + ":" + test1[1];
                            recyclerView =v.findViewById(R.id.recyclerView);
                            if(!temp[i].equals("0")&&!humi[i].equals("0")) {
                                tahdataAdapter.addItem(new tahData(chong[i], temp[i], humi[i]));
                                recyclerView.setAdapter(tahdataAdapter);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {

                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return v;
    }

    public void init() {
        retrofit = new Retrofit.Builder().baseUrl(BASE).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
