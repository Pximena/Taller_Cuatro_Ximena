package com.i044114.taller_cuatro_ximena;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.i044114.taller_cuatro_ximena.Adapters.AdapterPost;
import com.i044114.taller_cuatro_ximena.Conecction.HttpManager;
import com.i044114.taller_cuatro_ximena.Models.Post;
import com.i044114.taller_cuatro_ximena.Parser.JsonPost;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterPost adapterPost;
    List<Post> postList;
    ProgressBar progressBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        progressBar = (ProgressBar) findViewById(R.id.id_pb_itempost);
        recyclerView = (RecyclerView) findViewById(R.id.id_rv_itempost);
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);

        showToolbar(getResources().getString(R.string.str_3), true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadData(Integer.toString(getIntent().getExtras().getInt("pantallauser")));
        Bundle us = getIntent().getExtras();


    }

    public void showToolbar(String title, boolean flecha){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(flecha);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_dos, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.load_dos: {
                loadData(Integer.toString(getIntent().getExtras().getInt("pantallauser")));
                break;
            }
            default:
                return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);
    }

    public void layoutChange() {

        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
        startActivity(intent);

    }


    public class TaskCountry extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected String doInBackground(String... strings) {
            String content = null;
            try {
                content = HttpManager.getData(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                postList = JsonPost.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();
            progressBar.setVisibility(View.GONE);

        }
    }

    public void processData(){
        adapterPost = new AdapterPost(postList, getApplicationContext());
        recyclerView.setAdapter(adapterPost);
    }

    public Boolean isOnLine(){
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null){
            return true;
        }else {
            return false;
        }
    }

    public void loadData(String suma1){
        if (isOnLine()){
            PostActivity.TaskCountry taskCountry = new PostActivity.TaskCountry();
            taskCountry.execute("https://jsonplaceholder.typicode.com/posts?userId=" + suma1);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }
}
