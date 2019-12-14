package org.techtown.elderlyperson;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment1 extends Fragment {

    private ImageButton imageButton;
    private ImageButton imageButton2;
    private ImageButton imageButton3;
    private BottomNavigationView bottomNavigationView;
    private Retrofit retrofit;
    private TextView ongut;
    private TextView textView, onoff, gasnu;
    private APIService apiService;
    static String ht;
    private static final String BASE = "http://192.168.0.4:8000";
    //private static final String BASE = "http://192.168.200.138:8000";

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup v = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);
        imageButton = (ImageButton) v.findViewById(R.id.imageButton);
        imageButton2 = (ImageButton) v.findViewById(R.id.imageButton2);
        imageButton3 = (ImageButton) v.findViewById(R.id.imageButton3);
        textView = (TextView) v.findViewById(R.id.textView);
        ongut = (TextView) v.findViewById(R.id.ongut);
        onoff = (TextView) v.findViewById(R.id.onoff);
        gasnu = (TextView) v.findViewById(R.id.gasnu);
        bottomNavigationView = getActivity().findViewById(R.id.bottom_navigation);

        init();
        apiService = retrofit.create(APIService.class);
        Call<Person> content = apiService.per();
        content.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                final Person co1 = response.body();
                int size = co1.getTahData().size();
                int size1 = co1.getHumanDetect().size();
                String[] humi = new String[size];
                String[] create = new String[size];
                String[] temp = new String[size];
                String[] come = new String[size1];
                ht = co1.getUser().getImg_ipaddress();
                come[size1 - 1] = co1.getHumanDetect().get(size1 - 1).getOutCheck();
                humi[size - 1] = co1.getTahData().get(size - 1).getHumi();
                temp[size - 1] = co1.getTahData().get(size - 1).getTemp();
                create[size - 1] = co1.getTahData().get(size - 1).getCreateDate();
                String[] test = create[size - 1].split("T");
                String[] test2 = create[size - 1].split("-");
                String[] test3 = test2[2].split("T");
                String[] test1 = test[1].split(":");
                if (humi[size - 1].equals("0") && temp[size - 1].equals("0")) {
                    onoff.setText("서버 오류\n");
                } else {
                    onoff.setText(test2[1] + "-" + test3[0] + " " + test1[0] + ":" + test1[1] + "\n" + "습도 : " + humi[size - 1] + "%" + "\n" + test2[1] + "-" + test3[0] + " " + test1[0] + ":" + test1[1] + " \n" + "온도 : " + temp[size - 1] + "°C" + "\n");
                }
                String name = co1.getUser().getName();
                textView.setText(name);
                String s = co1.getGasDetect().getGasCheck();
                if (s.equals("false")) {
                    gasnu.setText("가스 위험이 없습니다.");
                } else if (s.equals("true")) {
                    gasnu.setText("가스 노출!");
                }
                if (come[size1 - 1].equals("come")) {
                    ongut.setText("집에 계십니다.");
                } else if (!come[size1 - 1].equals("come")) {
                    ongut.setText("외출중이 십니다.");
                }


            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {

            }
        });
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.sr_layout);
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
                        int size1 = co1.getHumanDetect().size();
                        String[] humi = new String[size];
                        String[] create = new String[size];
                        String[] temp = new String[size];
                        String[] come = new String[size1];
                        ht = co1.getUser().getImg_ipaddress();
                        come[size1 - 1] = co1.getHumanDetect().get(size1 - 1).getOutCheck();
                        humi[size - 1] = co1.getTahData().get(size - 1).getHumi();
                        temp[size - 1] = co1.getTahData().get(size - 1).getTemp();
                        create[size - 1] = co1.getTahData().get(size - 1).getCreateDate();
                        String[] test = create[size - 1].split("T");
                        String[] test2 = create[size - 1].split("-");
                        String[] test3 = test2[2].split("T");
                        String[] test1 = test[1].split(":");
                        if (humi[size - 1].equals("0") && temp[size - 1].equals("0")) {
                            onoff.setText("서버 오류\n");
                        } else {
                            onoff.setText(test2[1] + "-" + test3[0] + " " + test1[0] + ":" + test1[1] + "\n" + "습도 : " + humi[size - 1] + "%" + "\n" + test2[1] + "-" + test3[0] + " " + test1[0] + ":" + test1[1] + " \n" + "온도 : " + temp[size - 1] + "°C" + "\n");
                        }
                        String name = co1.getUser().getName();
                        textView.setText(name);
                        String s = co1.getGasDetect().getGasCheck();
                        if (s.equals("false")) {
                            gasnu.setText("가스 위험이 없습니다.");
                        } else if (s.equals("true")) {
                            gasnu.setText("가스 노출!");
                        }
                        if (come[size1 - 1].equals("come")) {
                            ongut.setText("집에 계십니다.");
                        } else if (!come[size1 - 1].equals("come")) {
                            ongut.setText("외출중이 십니다.");
                        }


                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {

                    }
                });
                swipeRefreshLayout.setRefreshing(false);
            }

        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(getContext(),"tt",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Fragment2()).commit();
                bottomNavigationView.setSelectedItemId(R.id.tab2);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Fragment3()).commit();
                bottomNavigationView.setSelectedItemId(R.id.tab3);
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new Fragment4()).commit();
                bottomNavigationView.setSelectedItemId(R.id.tab4);
            }
        });
        return v;
    }

    public void init() {
        retrofit = new Retrofit.Builder().baseUrl(BASE).addConverterFactory(GsonConverterFactory.create()).build();
    }
}
