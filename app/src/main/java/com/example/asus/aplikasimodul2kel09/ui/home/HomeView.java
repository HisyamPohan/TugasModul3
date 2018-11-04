package com.example.asus.aplikasimodul2kel09.ui.home;

import com.example.asus.aplikasimodul2kel09.data.model.DataCar;

import java.util.List;

public interface HomeView {
    void successShowCar(List<DataCar> dataCars);
    void failedShowCar(String message);

}
