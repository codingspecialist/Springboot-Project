package org.techtown.bsymapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    EditText userName;
    EditText password;
    Retrofit retrofit;
    private Button btnlogin;
    private Button btnRegister;
    private Button btnno;
    public static String nick ="로그인 해주세요";
   public static String SERVER_ADRESS = "http://192.168.0.4:9000";
  //private static final String SERVER_ADRESS = "http://192.168.200.138:8000";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnno =(Button) findViewById(R.id.btnno);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = userName.getText().toString();
                final String password2 = password.getText().toString();

                final Call<User> content = Login.RetrofitServiceImplFactory.serverPost().login(username, password2);
                content.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        final User a = response.body();

                        //Toast.makeText(getApplicationContext(), a.getResult(), Toast.LENGTH_SHORT).show();
                        if (a.getResult().equals("user login success")) {
                            Toast.makeText(getApplicationContext(),username.toString()+"님 안녕하세요",Toast.LENGTH_SHORT).show();
                            nick = username;
//                            finish();
                            Intent intent3 = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent3);
                        } else if (a.getResult().equals("user login false")) {
                            Toast.makeText(getApplicationContext(),"Id 및 비밀번호가 틀립니다.",Toast.LENGTH_SHORT).show();
                            password.setText("");
                            password.requestFocus();

                        } else if (a.getResult().equals("user not found")) {
                            Toast.makeText(getApplicationContext(),"Id 및 비밀번호가 틀립니다.",Toast.LENGTH_SHORT).show();
                            userName.setText("");
                            password.setText("");
                            userName.requestFocus();
                        }

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "에러", Toast.LENGTH_SHORT).show();
                    }
                });



            }

        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(), Register.class);
                startActivity(intent3);
            }
        });
        btnno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    static class RetrofitServiceImplFactory {
        private static Retrofit getretrofit() {
            return new Retrofit.Builder()
                    .baseUrl(SERVER_ADRESS)
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        public static ApiService serverPost() {
            return getretrofit().create(ApiService.class);
        }
    }
}