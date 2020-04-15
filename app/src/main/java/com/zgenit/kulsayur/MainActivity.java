package com.zgenit.kulsayur;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvVegetables;
    private ArrayList<Vegetable> list = new ArrayList<>();
    static String VEGETABLE_POSITION = "vegetable_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Home");
        }

        rvVegetables = findViewById(R.id.rv_vegetables);
        rvVegetables.setHasFixedSize(true);

        list.addAll(VegetablesData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        rvVegetables.setLayoutManager(new LinearLayoutManager(this));
        ListVegetableAdapter listVegetableAdapter = new ListVegetableAdapter(list);
        rvVegetables.setAdapter(listVegetableAdapter);

        listVegetableAdapter.setOnItemClickCallback(new ListVegetableAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(int position) {
                showSelectedVegetable(position);
            }
        });
    }

    private void showSelectedVegetable(int position){
        Intent moveIntent = new Intent(MainActivity.this, VegetableDetailActivity.class);
        moveIntent.putExtra(VEGETABLE_POSITION, position);
        startActivity(moveIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_profile) {
            showProfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showProfile(){
        Intent moveIntent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(moveIntent);
    }
}
