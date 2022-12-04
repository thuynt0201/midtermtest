package com.nguyenthuthuy.midtermtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nguyenthuthuy.Database.DatabaseHelper;
import com.nguyenthuthuy.midtermtest.databinding.ActivityMainBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public static DatabaseHelper databaseHelper;
    int REQUEST_CODE_FOLDER=999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        databaseHelper = new DatabaseHelper(this, "TOURDB.sqlite", null, 1);

        databaseHelper.queryData("CREATE TABLE IF NOT EXISTS TOUR(Id INTEGER PRIMARY KEY AUTOINCREMENT,code VARCHAR,name VARCHAR, des VARCHAR, count REAL,scheldule VARCHAR,price REAL, image BLOB)");

        addEvents();
    }

    private void addEvents() {
        binding.btnChontour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });

        binding.btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    databaseHelper.insertData(
                            binding.edtMatour.getText().toString().trim(),
                            binding.edtTentour.getText().toString().trim(),
                            binding.edtMotatour.getText().toString().trim(),
                            Double.parseDouble(binding.edtSoluongkhach.getText().toString().trim()),
                            binding.edtLichtrinh.getText().toString().trim(),
                            Double.parseDouble(binding.edtGia.getText().toString().trim()),
                            imageViewToByte(binding.imvPhoto)
                    );
                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                    binding.edtMatour.setText("");
                    binding.edtTentour.setText("");
                    binding.edtMotatour.setText("");
                    binding.edtSoluongkhach.setText("");
                    binding.edtLichtrinh.setText("");
                    binding.edtGia.setText("");
                    binding.imvPhoto.setImageResource(R.drawable.img);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        binding.btnXemsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TourListActivity.class);
                startActivity(intent);
            }
        });


    }










    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                binding.imvPhoto.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}