package org.techtown.bsymapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Search extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //뒤로가기 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_search);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.searchbar, menu);
        return true;
    }
    //뒤로가기 버튼 동작 시키기 위한 것
    public boolean onOptionsItemSelected(MenuItem item) {
        int id =item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return true;
    }
}