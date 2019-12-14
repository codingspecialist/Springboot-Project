package org.techtown.bsymapp;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.techtown.bsymapp.Login.nick;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    SelectMenuAdapter selectMenuAdapter;
    RecyclerView recyclerView2;
    ContentMenuAdapter contentMenuAdapter;
    Button button44;
    Toolbar toolbar;
    Retrofit retrofit;
    ApiService apiService;
    ImageView imageView2;
    CheckBox cbSelect;
    TextView textView;
    private static final String BASE = "http://192.168.0.4:9000";
   // private static final String BASE = "http://192.168.200.138:8000";
    public static String a;
    int size;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
//            @Override
//            public void onSuccess(InstanceIdResult result) {
//                String newToken = result.getToken();
//                Log.d("test133",newToken);
//            }
//        });
        //첫화면 띄우기
        init();
        SelectMenu();
        contentMenu();
        apiService = retrofit.create(ApiService.class);
        cbSelect = (CheckBox) findViewById(R.id.cbSelect2);
        Call<List<as>> content = apiService.asds("수미네반찬", nick);
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
                contentMenuAdapter = new ContentMenuAdapter();
                for (int i = 0; i < size; i++) {
                    like[i] = co1.get(i).likeCheck;
                    content[i] = co1.get(i).title;
                    b1[i] = co1.get(i).imglink;
                    link[i] = co1.get(i).link;
                    id[i] = co1.get(i).id;
                    recyclerView2 = findViewById(R.id.recyclerView2);
                    contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                    recyclerView2.setAdapter(contentMenuAdapter);
                }

                contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                    @Override
                    public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                        ContentMenu item = contentMenuAdapter.getItem(position);
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
                        //true false 알켜줌 //일단 클릭시 트루 펄스 그리고 위치 알켜줌
                        //               Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                        //                      startActivity(intent3);
                        a = item.getLink();
                        Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                        startActivity(intent3);
                    }
                });


                TabLayout tabs = findViewById(R.id.tabs);
                tabs.addTab(tabs.newTab().setText("방송요리"));
                tabs.addTab(tabs.newTab().setText("방송맛집"));
                tabs.addTab(tabs.newTab().setText("내 주변맛집"));
                tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        int position = tab.getPosition();
                        //블로그 이동
                        if (position == 2) {
                            Intent intent2 = new Intent(getApplicationContext(), InternetActivity.class);
                            startActivity(intent2);
                        }
                        if (position == 0) {
                            SelectMenu();
                            recyclerView2 = findViewById(R.id.recyclerView2);
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            final String[] c2 = new String[size];
                            boolean[] like = new boolean[size];
                            int[] id = new int[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                like[i] = co1.get(i).likeCheck;
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                String[] link = new String[size];
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            recyclerView2.setAdapter(contentMenuAdapter);
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
                                }
                            });
                        } else if (position == 1) {
                            SelectMenu1();
                            apiService = retrofit.create(ApiService.class);
                            Call<List<as>> content = apiService.asds("밥블레스유", nick);
                            content.enqueue(new Callback<List<as>>() {
                                @Override
                                public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                                    final List<as> co1 = response.body();
                                    size = co1.size();
                                    String[] content = new String[size];
                                    String[] b1 = new String[size];
                                    String[] link = new String[size];
                                    int[] id = new int[size];
                                    boolean[] like = new boolean[size];
                                    contentMenuAdapter = new ContentMenuAdapter();
                                    for (int i = 0; i < size; i++) {
                                        like[i] = co1.get(i).likeCheck;
                                        content[i] = co1.get(i).title;
                                        b1[i] = co1.get(i).imglink;
                                        link[i] = co1.get(i).link;
                                        id[i] = co1.get(i).id;
                                        recyclerView2 = findViewById(R.id.recyclerView2);
                                        contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                        recyclerView2.setAdapter(contentMenuAdapter);
                                    }
                                    contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                        @Override
                                        public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                            ContentMenu item = contentMenuAdapter.getItem(position);
                                            a = item.getLink();
                                            Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                            startActivity(intent3);
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Call<List<as>> call, Throwable t) {
                                }
                            });
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                });
            }

            @Override
            public void onFailure(Call<List<as>> call, Throwable t) {

            }
        });
        //웹뷰의 설정 수정
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.logo);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        View nav_header_view = navigationView.inflateHeaderView(R.layout.nav_header_main);
        final TextView nav_header_id_text = (TextView) nav_header_view.findViewById(R.id.nickname);
        nav_header_id_text.setText(nick);
        navigationView.setNavigationItemSelectedListener(this);


        button44 = (Button) findViewById(R.id.button44);
        button44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView2.smoothScrollToPosition(0);
                // recyclerView2.smoothScrollToPosition(contentMenuAdapter.getItemCount()-1);
            }
        });
    }

    /*메뉴 만든거 가져오기*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    //메뉴바 버튼 클랙했을 때의 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search_icon) {
            Intent searchintent = new Intent(getApplicationContext(), Search.class);
            startActivity(searchintent);
            // Toast.makeText(this, "검색", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.like_icon) {
            if (nick.equals("로그인 해주세요")) {

                AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
                alert_confirm.setMessage("로그인이 필요한 서비스 입니다.");
                alert_confirm.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent login = new Intent(getApplicationContext(), Login.class);
                        startActivity(login);
                    }
                });
                AlertDialog alert = alert_confirm.create();
                alert.setTitle("알림");
                alert.show();
            } else {
                Intent Likeintent = new Intent(getApplicationContext(), LikeHouse.class);
                startActivity(Likeintent);
                // Toast.makeText(this, "보관함", Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Call<List<as>> content = apiService.asds("수미네반찬", nick);
        final int id = item.getItemId();
        content.enqueue(new Callback<List<as>>() {
            @Override
            public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                final List<as> co1 = response.body();
                if (id == R.id.menu1) {
                    SelectMenu();
                    size = co1.size();
                    String[] content = new String[size];
                    boolean[] like = new boolean[size];
                    String[] b1 = new String[size];
                    int[] id = new int[size];
                    contentMenuAdapter = new ContentMenuAdapter();
                    for (int i = 0; i < size; i++) {
                        like[i] = co1.get(i).likeCheck;
                        content[i] = co1.get(i).title;
                        b1[i] = co1.get(i).imglink;
                        String[] link = new String[size];
                        link[i] = co1.get(i).link;
                        id[i] = co1.get(i).id;
                        recyclerView2 = findViewById(R.id.recyclerView2);
                        contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                        recyclerView2.setAdapter(contentMenuAdapter);
                    }

                    contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                        @Override
                        public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                            ContentMenu item = contentMenuAdapter.getItem(position);
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
                            a = item.getLink();
                            Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                            startActivity(intent3);
                        }
                    });

                } else if (id == R.id.menu2) {
                    SelectMenu1();
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("밥블레스유", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });

                } else if (id == R.id.menu3) {
                    Intent intent2 = new Intent(getApplicationContext(), InternetActivity.class);
                    startActivity(intent2);

                }
            }

            @Override
            public void onFailure(Call<List<as>> call, Throwable t) {

            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void loginclick(View v) {
        Intent myIntent = new Intent(getApplicationContext(), Login.class);
        startActivity(myIntent);
    }

    public void contentMenu() {
        recyclerView2 = findViewById(R.id.recyclerView2);
        contentMenuAdapter = new ContentMenuAdapter();
        GridLayoutManager layoutManager1 = new GridLayoutManager(this, 2);
        recyclerView2.setLayoutManager(layoutManager1);
    }

    public void SelectMenu() {
        recyclerView = findViewById(R.id.recyclerView);
        selectMenuAdapter = new SelectMenuAdapter();
        LinearLayoutManager horizonalLayoutManager
                = new LinearLayoutManager(this, LinearLayout.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizonalLayoutManager);
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.sumi, 11));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.toran, 12));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.mulsang, 13));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.sphs, 14));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.jubang, 15));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.backteacher, 16));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.bigyul, 17));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.please, 18));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.jungbo, 19));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.yun, 20));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.three, 21));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.coffee, 22));

        recyclerView.setAdapter(selectMenuAdapter);
        selectMenuAdapter.setOnItemClickListener(new OnSelectMenuItemClickListener() {
            @Override
            public void onItemClick(SelectMenuAdapter.ViewHolder holder, View view, int position) {
                SelectMenu item = selectMenuAdapter.getItem(position);
                if (item.getNum() == 11) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("수미네반찬", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            boolean[] like = new boolean[size];
                            int[] id = new int[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 12) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("알토란", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });

                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 13) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("만물상", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 14) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("스페인하숙", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            boolean[] like = new boolean[size];
                            int[] id = new int[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 15) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("모두의주방", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 16) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("집밥백선생", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            boolean[] like = new boolean[size];
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);

                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 17) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("요리비결", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }

                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);

                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 18) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("냉부해", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 19) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("생생정보", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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


                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 20) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("윤식당", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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


                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 21) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("삼시세끼", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;

                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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


                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 22) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("커피프렌즈", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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


                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
            }
        });
    }

    public void SelectMenu1() {
        recyclerView = findViewById(R.id.recyclerView);
        selectMenuAdapter = new SelectMenuAdapter();
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.riceyou, 1));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.sicdang, 2));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.three, 3));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.wenseday, 4));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.jungbo, 5));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.today, 6));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.pig, 7));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.load, 15));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.dalin, 9));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.wsic, 10));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.king, 11));
        selectMenuAdapter.addItem(new SelectMenu(R.drawable.club, 12));
        recyclerView.setAdapter(selectMenuAdapter);
        selectMenuAdapter.setOnItemClickListener(new OnSelectMenuItemClickListener() {
            @Override
            public void onItemClick(SelectMenuAdapter.ViewHolder holder, View view, int position) {
                SelectMenu item = selectMenuAdapter.getItem(position);
                if (item.getNum() == 1) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("밥블레스유", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }

                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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


                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 2) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("골목식당", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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


                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 3) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("전참시", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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


                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 4) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("수요미식회", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 5) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("생생정보", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            boolean[] like = new boolean[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;

                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 6) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("생방송투데이", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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


                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 7) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("맛있는녀석들", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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


                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 15) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("식신로드", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 9) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("생활의달인", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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


                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 10) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("외식하는날", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                like[i] = co1.get(i).likeCheck;
                                id[i] = co1.get(i).id;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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

                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 11) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("3대천왕", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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
                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
                if (item.getNum() == 12) {
                    apiService = retrofit.create(ApiService.class);
                    Call<List<as>> content = apiService.asds("미식클럽", nick);
                    content.enqueue(new Callback<List<as>>() {
                        @Override
                        public void onResponse(Call<List<as>> call, Response<List<as>> response) {
                            final List<as> co1 = response.body();
                            size = co1.size();
                            String[] content = new String[size];
                            String[] b1 = new String[size];
                            String[] link = new String[size];
                            int[] id = new int[size];
                            boolean[] like = new boolean[size];
                            contentMenuAdapter = new ContentMenuAdapter();
                            for (int i = 0; i < size; i++) {
                                content[i] = co1.get(i).title;
                                b1[i] = co1.get(i).imglink;
                                link[i] = co1.get(i).link;
                                id[i] = co1.get(i).id;
                                like[i] = co1.get(i).likeCheck;
                                recyclerView2 = findViewById(R.id.recyclerView2);
                                contentMenuAdapter.addItem(new ContentMenu(content[i], link[i], like[i], id[i]), b1[i]);
                                recyclerView2.setAdapter(contentMenuAdapter);
                            }
                            contentMenuAdapter.setOnItemClickListener(new OnContentMenuItemClickListener() {
                                @Override
                                public void onItemClick(ContentMenuAdapter.ViewHolder holder, View view, int position) {
                                    ContentMenu item = contentMenuAdapter.getItem(position);
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
                                    a = item.getLink();
                                    Intent intent3 = new Intent(getApplicationContext(), ContentDetail.class);
                                    startActivity(intent3);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<List<as>> call, Throwable t) {
                        }
                    });
                }
            }
        });
    }

    public void init() {
        textView = (TextView) findViewById(R.id.textView13);
        retrofit = new Retrofit.Builder().baseUrl(BASE).addConverterFactory(GsonConverterFactory.create()).build();
        imageView2 = findViewById(R.id.imageView22);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                String alertTitle = "종료";
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(alertTitle)
                        .setMessage("프로그램을 종료합니다. \n 종료 하겠습니까?")
                        .setPositiveButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                moveTaskToBack(true);
                                finish();
                                ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                                //am.restartPackage(getPackageName());
                                nick = "로그인 해주세요";
                            }
                        })
                        .setNegativeButton("아니요", null)
                        .show();
        }
        return true;
    }

    static class RetrofitServiceImplFactory {
        private static Retrofit getretrofit() {
            return new Retrofit.Builder()
                    .baseUrl(BASE)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }

        public static ApiService serverPost() {
            return getretrofit().create(ApiService.class);
        }
    }
}