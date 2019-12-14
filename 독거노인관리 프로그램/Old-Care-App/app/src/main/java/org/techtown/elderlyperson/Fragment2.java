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

public class Fragment2 extends Fragment {
    RecyclerView recyclerView3;
    humanDetectAdapter humandetectAdapter;
    Retrofit retrofit;
    APIService apiService;
    private static final String BASE = "http://192.168.0.4:8000";
   // private static final String BASE = "http://192.168.200.138:8000";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);
        init();
        recyclerView3 = (RecyclerView) v.findViewById(R.id.recyclerView3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView3.setLayoutManager(layoutManager);
        init();
        apiService = retrofit.create(APIService.class);
        Call<Person> content = apiService.per();
        content.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                final Person co1 = response.body();
                int size = co1.getHumanDetect().size();
                String[] outdate = new String[size];
                String[] comebackdate = new String[size];
                String[] outcheck = new String[size];
                String[] chong = new String[size];
                humandetectAdapter = new humanDetectAdapter();
                for(int i=0;i<size;i++){
                    outdate[i] = co1.getHumanDetect().get(i).getComebackDate();
                    comebackdate[i] = co1.getHumanDetect().get(i).getOutDate();
                    outcheck[i] = co1.getHumanDetect().get(i).getOutCheck();
                    String[] test = outdate[i].split("T");
                    String[] test2 = outdate[i].split("-");
                    String[] test3 = test2[2].split("T");
                    String[]test1 = test[1].split(":");
                    chong[i] = test2[1] + "-" + test3[0] +" "+ test1[0] + ":" + test1[1];

                    recyclerView3 =v.findViewById(R.id.recyclerView3);
                    if(outcheck[i].equals("come")) {
                        humandetectAdapter.addItem(new humanDetect(chong[i],":집에 계심"));
                    }
                    else if(outcheck[i].equals("out")){
                        humandetectAdapter.addItem(new humanDetect(chong[i],":외출 중"));
                    }
                    recyclerView3.setAdapter(humandetectAdapter);
                }

            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

            }
        });


        final   SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.sr_layout2);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                apiService = retrofit.create(APIService.class);
                Call<Person> content = apiService.per();
                content.enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        final Person co1 = response.body();
                        int size = co1.getHumanDetect().size();
                        String[] outdate = new String[size];
                        String[] comebackdate = new String[size];
                        String[] outcheck = new String[size];
                        String[] chong = new String[size];
                        humandetectAdapter = new humanDetectAdapter();
                        for(int i=0;i<size;i++){
                            outdate[i] = co1.getHumanDetect().get(i).getComebackDate();
                            comebackdate[i] = co1.getHumanDetect().get(i).getOutDate();
                            outcheck[i] = co1.getHumanDetect().get(i).getOutCheck();
                            String[] test = outdate[i].split("T");
                            String[] test2 = outdate[i].split("-");
                            String[] test3 = test2[2].split("T");
                            String[]test1 = test[1].split(":");
                            chong[i] = test2[1] + "-" + test3[0] +" "+ test1[0] + ":" + test1[1];

                            recyclerView3 =v.findViewById(R.id.recyclerView3);
                            if(outcheck[i].equals("come")) {
                                humandetectAdapter.addItem(new humanDetect(chong[i],":집에 계심"));
                            }
                            else if(outcheck[i].equals("out")){
                                humandetectAdapter.addItem(new humanDetect(chong[i],":외출 중"));
                            }
                            recyclerView3.setAdapter(humandetectAdapter);
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
