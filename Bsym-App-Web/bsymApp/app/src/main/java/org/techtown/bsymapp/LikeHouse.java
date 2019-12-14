package org.techtown.bsymapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.techtown.bsymapp.Login.nick;

public class LikeHouse extends AppCompatActivity {
    Retrofit retrofit;
    TextView textView13;
    ImageView imageView2;
    int size;
    ApiService apiService;
    RecyclerView recyclerView5;
    ContentMenuAdapter adapter;

    private static final String BASE = "http://192.168.0.4:9000";

    //private static final String BASE = "http://192.168.200.138:8000";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.likehouse);
        recyclerView5 = findViewById(R.id.recycleView5);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        apiService = retrofit.create(ApiService.class);
        Call<List<as>> content = apiService.boguanham(nick);
        content.enqueue(new Callback<List<as>>() {
            @Override
            public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                final List<as> co1 = response.body();
                size = co1.size();
                String[] content = new String[size];
                String[] b1 = new String[size];
                String[] link = new String[size];
                int[] id = new int[size];
                final boolean[] like = new boolean[size];
                GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                recyclerView5.setLayoutManager(layoutManager);
                adapter = new ContentMenuAdapter();
                textView13 = (TextView) findViewById(R.id.textView13);
                for (int i = 0; i < size; i++) {
                    like[i] = co1.get(i).likeCheck;
                    content[i] = co1.get(i).title;
                    b1[i] = co1.get(i).imglink;
                    link[i] = co1.get(i).link;
                    id[i] = co1.get(i).id;
                    recyclerView5 = findViewById(R.id.recycleView5);
                    adapter.addItem(new ContentMenu(content[i], link[i], true, id[i]), b1[i]);
                }
                recyclerView5.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                    @Override
                    public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                        ContentMenu item = adapter.getItem(position);
                        final Call<as> content = MainActivity.RetrofitServiceImplFactory.serverPost().test(item.getId());
                        content.enqueue(new Callback<as>() {
                            @Override
                            public void onResponse(Call<as> call, Response<as> response) {
                                final as co1 = response.body();
                            }

                            @Override
                            public void onFailure(Call<as> call, Throwable t) {

                            }
                        });
                        Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                        startActivity(intent3);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<as>> call, Throwable t) {
                Log.d("test1", "testrrr");
            }
        });

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return true;
    }

    public void init() {
        retrofit = new Retrofit.Builder().baseUrl(BASE).addConverterFactory(GsonConverterFactory.create()).build();
        imageView2 = findViewById(R.id.imageView22);
    }
}
