package com.zgenit.kulsayur;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ListVegetableImageAdapter extends RecyclerView.Adapter<ListVegetableImageAdapter.ListViewHolder> {
    private String[] listImages;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListVegetableImageAdapter(String[] list){
        this.listImages = list;
    }

    @NonNull
    @Override
    public ListVegetableImageAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_vegetable_image, parent, false);
        return new ListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListVegetableImageAdapter.ListViewHolder holder, final int position) {
        final String image = listImages[position];
            Glide.with(holder.itemView.getContext())
                    .load(image)
                    .apply(new RequestOptions().override(55, 55))
                    .into(holder.imgPhoto);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickCallback.onItemClicked(image);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listImages.length;
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_vegetable_photo);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(String image_url);
    }
}