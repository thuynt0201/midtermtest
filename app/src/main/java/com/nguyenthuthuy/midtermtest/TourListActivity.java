package com.nguyenthuthuy.midtermtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import com.nguyenthuthuy.adapters.TourAdapter;
import com.nguyenthuthuy.midtermtest.databinding.ActivityTourListBinding;
import com.nguyenthuthuy.models.Tour;

import java.util.ArrayList;

public class TourListActivity extends AppCompatActivity {

    ActivityTourListBinding binding;

    ArrayList<Tour> tourArrayList;
    TourAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_tour_list);
        binding = ActivityTourListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tourArrayList = new ArrayList<>();
        adapter = new TourAdapter(this, R.layout.item_list, tourArrayList);
        binding.lvTour.setAdapter(adapter);

        // get all data from sqlite
        Cursor cursor = MainActivity.databaseHelper.getData("SELECT * FROM TOUR");
        tourArrayList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String code = cursor.getString(1);
            String name = cursor.getString(2);
            String des = cursor.getString(3);
            Double count= cursor.getDouble(4);
            String scheldule = cursor.getString(5);
            Double price= cursor.getDouble(6);
            byte[] image = cursor.getBlob(7);

            tourArrayList.add(new Tour(id, code, name, des,count,  scheldule,price, image));
        }
        adapter.notifyDataSetChanged();
        addEvent();
        

    }

    private void addEvent() {
        binding.btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TourListActivity.this, MainActivity.class);
               startActivity(intent);
            }
        });
    }

    public void DialogXoaCV(String tenCV, int Id){

        AlertDialog.Builder dialogXoa=new AlertDialog.Builder(TourListActivity.this);
        dialogXoa.setMessage("Bạn có muốn xóa"+ tenCV +" hay không?");
        dialogXoa.setPositiveButton("Co", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.databaseHelper.getData("DELETE FROM Laptop WHERE Id = '"+ Id +"'");
            }
        });

        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        dialogXoa.show();
    }

}