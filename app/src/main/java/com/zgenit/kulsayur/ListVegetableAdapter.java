package com.zgenit.kulsayur;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class ListVegetableAdapter extends RecyclerView.Adapter<ListVegetableAdapter.ListViewHolder> {
    private ArrayList<Vegetable> listVegetable;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListVegetableAdapter(ArrayList<Vegetable> list){
        this.listVegetable = list;
    }

    @NonNull
    @Override
    public ListVegetableAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_vegetable, parent, false);
        return new ListViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListVegetableAdapter.ListViewHolder holder, final int position) {
        Vegetable vegetable = listVegetable.get(position);
        if(vegetable.getImage().length > 0) {
            Glide.with(holder.itemView.getContext())
                    .load(vegetable.getImage()[0])
                    .apply(new RequestOptions().override(55, 55))
                    .into(holder.imgPhoto);
        }
        holder.tvName.setText(vegetable.getName());
        holder.tvPrice.setText("Rp "+vegetable.getPrice());
        holder.tvStore.setText(vegetable.getStore());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listVegetable.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgPhoto;
        TextView tvName, tvPrice, tvStore;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_vegetable_photo);
            tvName = itemView.findViewById(R.id.tv_vegetable_name);
            tvPrice = itemView.findViewById(R.id.tv_vegetable_price);
            tvStore = itemView.findViewById(R.id.tv_vegetable_store);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(int position);
    }
}