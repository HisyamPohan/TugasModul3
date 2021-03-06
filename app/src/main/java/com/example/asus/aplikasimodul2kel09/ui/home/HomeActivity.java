package com.example.asus.aplikasimodul2kel09.ui.home;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.asus.aplikasimodul2kel09.R;
import com.example.asus.aplikasimodul2kel09.adapter.car.CarAdapter;
import com.example.asus.aplikasimodul2kel09.adapter.car.CarListener;
import com.example.asus.aplikasimodul2kel09.data.model.DataCar;
import com.example.asus.aplikasimodul2kel09.ui.addCar.AddActivity;
import com.example.asus.aplikasimodul2kel09.ui.detailCar.DetailActivity;
import com.example.asus.aplikasimodul2kel09.utility.Constant;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeView, CarListener {

    private HomePresenter homePresenter;
    private CarAdapter carAdapter;
    FloatingActionButton fabHome;
    RecyclerView rvHome;
    SwipeRefreshLayout srlHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initPresenter();
        initView();
        initDataPresenter();

        addCar();
        refresh();
    }

    private void initView() {
        fabHome = findViewById(R.id.fabHome);
        rvHome = findViewById(R.id.rvHome);
        srlHome = findViewById(R.id.srlHome);
    }

    private void initPresenter() {
        homePresenter = new HomePresenter(this);
    }

    private void initDataPresenter() {
        homePresenter.getAllCar();
    }

    private void addCar() {
        fabHome.setOnClickListener(v -> {
            Intent addCar = new Intent(HomeActivity.this, AddActivity.class);
            startActivity(addCar);
            finish();
        });
    }

    private void refresh(){
        srlHome.setOnRefreshListener(() -> initDataPresenter());
    }

    @Override
    public void successShowCar(List<DataCar> dataCars) {
        if (srlHome.isRefreshing()){
            srlHome.setRefreshing(false);
        }
        carAdapter = new CarAdapter(dataCars);
        carAdapter.setAdapterListener(this);
        rvHome.setLayoutManager(new LinearLayoutManager(this));
        rvHome.setAdapter(carAdapter);
    }

    @Override
    public void failedShowCar(String message) {
        Toast.makeText(this, "Maaf terjadi kesalahan", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCarClick(DataCar dataCar) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Constant.Extra.DATA, dataCar);
        startActivity(intent);
    }
}

