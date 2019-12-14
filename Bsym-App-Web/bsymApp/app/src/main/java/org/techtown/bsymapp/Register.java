package org.techtown.bsymapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class
Register extends AppCompatActivity {
    EditText userName;
    EditText Name;
    EditText password;
    EditText passwordConfirm;
    private Button btnDone;
    private Button btnCancel;
    public static String SERVER_ADRESS = "http://192.168.0.4:9000";
    //private static final String SERVER_ADRESS = "http://192.168.200.138:8000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        userName = (EditText) findViewById(R.id.userName);
        Name = (EditText) findViewById(R.id.Name);
        password = (EditText) findViewById(R.id.password);
        passwordConfirm = (EditText) findViewById(R.id.passwordConfirm);
        btnDone = (Button) findViewById(R.id.btnDone);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = userName.getText().toString();
                String password2 = password.getText().toString();
                String name2 = Name.getText().toString();

                if (userName.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Email을 입력하세요", Toast.LENGTH_SHORT).show();
                    userName.requestFocus();
                    return;
                }
               if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userName.getText().toString()).matches()) {
                    Toast.makeText(getApplicationContext(), "Email형식이 아닙니다.", Toast.LENGTH_SHORT).show();
                    userName.requestFocus();
                    return;
                }

              if (Name.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
                    Name.requestFocus();
                    return;
                }
             if (password.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                    return;
                }
                if (password.getText().toString().length() < 5 && passwordConfirm.getText().toString().length() < 5) {
                    Toast.makeText(getApplicationContext(), "5자이상적어주세요", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                    return;
                }
                if (!password.getText().toString().equals(passwordConfirm.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치 하지 않습니다.", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    passwordConfirm.setText("");
                    password.requestFocus();
                    return;
                }
               else{
                    final Call<User> m = Register.RetrofitServiceImplFactory.serverPost().sendName(username, name2, password2);
                    m.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {

                        }
                    });
                }
               }

        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
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