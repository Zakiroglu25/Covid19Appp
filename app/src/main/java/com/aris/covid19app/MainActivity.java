package com.aris.covid19app;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.aris.covid19app.adapter.MainAdapter;
import com.aris.covid19app.model.Response;
import com.aris.covid19app.rest.ApiClient;
import com.aris.covid19app.rest.ApiInterface;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.notification_rv);
        mainAdapter = new MainAdapter(new ArrayList<>(), this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
        getCountry();
    }
    private void getCountry() {
        service.country().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response != null && response.code() == 200) {
                    System.out.println(response.body());
                    mainAdapter.addList(response.body().getCountries());
                    mainAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("covid error", "Failed : " + t.getMessage());
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mainAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
