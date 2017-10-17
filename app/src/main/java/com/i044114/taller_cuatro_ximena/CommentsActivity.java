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

import com.i044114.taller_cuatro_ximena.Adapters.AdapterComments;
import com.i044114.taller_cuatro_ximena.Conecction.HttpManager;
import com.i044114.taller_cuatro_ximena.Models.Comments;
import com.i044114.taller_cuatro_ximena.Parser.JsonComments;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterComments adapterComments;
    List<Comments> commentsList;
    ProgressBar progressBar;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        progressBar = (ProgressBar) findViewById(R.id.id_pb_itemcomment);
        recyclerView = (RecyclerView) findViewById(R.id.id_rv_itemcomment);
        toolbar = (Toolbar) findViewById(R.id.id_toolbar);

        showToolbar(getResources().getString(R.string.str_4), true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadData(Integer.toString(getIntent().getExtras().getInt("pantallapost")));
    }

    public void showToolbar(String title, boolean flecha){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(flecha);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuitem) {
        getMenuInflater().inflate(R.menu.menu_3, menuitem);

        return super.onCreateOptionsMenu(menuitem);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.load_tres: {
                loadData(Integer.toString(getIntent().getExtras().getInt("pantallapost")));
                break;
            }

        }
        return super.onOptionsItemSelected(item);
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
                commentsList = JsonComments.getData(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            processData();
            progressBar.setVisibility(View.GONE);

        }
    }

    public void processData(){
        adapterComments = new AdapterComments(commentsList, getApplicationContext());
        recyclerView.setAdapter(adapterComments);
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

    public void loadData(String suma2){
        if (isOnLine()){
            CommentsActivity.TaskCountry taskCountry = new CommentsActivity.TaskCountry();
            taskCountry.execute("https://jsonplaceholder.typicode.com/comments?postId=" + suma2);
        }else {
            Toast.makeText(this, "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }
}

