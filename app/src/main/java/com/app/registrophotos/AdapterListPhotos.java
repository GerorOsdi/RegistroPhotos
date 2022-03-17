package com.app.registrophotos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.app.registrophotos.Tablas.photograh;

import java.util.List;

public class AdapterListPhotos extends RecyclerView.Adapter<AdapterListPhotos.ViewHolder> {
    private List<photograh> Data;
    private LayoutInflater mInflater;
    private Context context;

    public AdapterListPhotos(List<photograh> itemPhoto, Context cont){
        this.mInflater = LayoutInflater.from(cont);
        this.context = cont;
        this.Data = itemPhoto;
    }

    @Override
    public int getItemCount(){return Data.size();}

    @Override
    public AdapterListPhotos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_photos, null);

        return new AdapterListPhotos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterListPhotos.ViewHolder holder, final int position){
        holder.binData(Data.get(position));
    }

    public void setItems(List<photograh> items){Data = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView descp;

        ViewHolder(View itemView){
            super(itemView);
            icon = itemView.findViewById(R.id.iconView);
            descp = itemView.findViewById(R.id.txtDescripcion_2);
        }

        void binData(final photograh item){
            byte [] bitArray = Base64.decode(item.getIMAGE().toString(),Base64.DEFAULT);
            Bitmap decode = BitmapFactory.decodeByteArray(bitArray,0,bitArray.length);

            icon.setImageBitmap(decode);
            descp.setText(item.getDESC().toString());
        }
    }
}
