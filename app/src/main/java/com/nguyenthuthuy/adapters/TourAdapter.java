package com.nguyenthuthuy.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nguyenthuthuy.midtermtest.R;
import com.nguyenthuthuy.models.Tour;

import java.util.ArrayList;

public class TourAdapter extends BaseAdapter {

    private Context context;
    private  int layout;
    private ArrayList<Tour> tourArrayList;

    public TourAdapter(Context context, int layout, ArrayList<Tour> tourArrayList) {
        this.context = context;
        this.layout = layout;
        this.tourArrayList = tourArrayList;
    }

    @Override
    public int getCount() {
        return tourArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return tourArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imagePhoto,imageDelete;
        TextView txtCode,txtName,txtDes,txtCount,txtScheldule,txtPrice;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);
            holder.txtCode = (TextView) row.findViewById(R.id.txt_Ma);
            holder.txtName = (TextView) row.findViewById(R.id.txt_Ten);
            holder.txtDes = (TextView) row.findViewById(R.id.txt_Mota);
            holder.txtCount = (TextView) row.findViewById(R.id.txt_Soluong);
            holder.txtScheldule = (TextView) row.findViewById(R.id.txt_Lichtrinh);
            holder.txtPrice = (TextView) row.findViewById(R.id.txt_Gia);

            holder.imagePhoto= (ImageView) row.findViewById(R.id.imv_Tour);
            holder.imageDelete=row.findViewById(R.id.imv_Delete);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Tour tour = tourArrayList.get(position);
        holder.txtCode.setText(tour.getCode());
        holder.txtName.setText(tour.getName());
        holder.txtDes.setText(tour.getDes());
        holder.txtCount.setText(tour.getCount().toString());
        holder.txtDes.setText(tour.getDes());
       holder.txtPrice.setText(tour.getPrice().toString());
        byte[] productImage = tour.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(productImage, 0, productImage.length);
        holder.imagePhoto.setImageBitmap(bitmap);

        holder.imageDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        return row;
    }
}
