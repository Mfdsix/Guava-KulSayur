package com.zgenit.kulsayur;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class VegetableDetailActivity extends AppCompatActivity {
    private ImageView imgVegetable, imgFavorite;
    private TextView tvName, tvPrice, tvDescription, tvStore, tvAddress, tvStock;
    private RecyclerView rvVegetableImage;
    boolean isFavorite = false;
    private String[] listImages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetable_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Detail Produk");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        imgVegetable = findViewById(R.id.img_vegetable_photo);
        tvName = findViewById(R.id.tv_vegetable_name);
        tvPrice = findViewById(R.id.tv_vegetable_price);
        tvDescription = findViewById(R.id.tv_vegetable_description);
        tvStore = findViewById(R.id.tv_vegetable_store);
        tvAddress = findViewById(R.id.tv_vegetable_address);
        tvStock = findViewById(R.id.tv_vegetable_stock);
        CardView cvFavorite = findViewById(R.id.cv_favorite);
        imgFavorite = findViewById(R.id.img_favorite);
        Button btnBuy = findViewById(R.id.btn_buy_product);
        Button btnCart = findViewById(R.id.btn_add_to_cart);
        rvVegetableImage = findViewById(R.id.rv_vegetable_images);

        int vegetablePosition = getIntent().getIntExtra(MainActivity.VEGETABLE_POSITION, 0);
        showVegetableDetail(vegetablePosition);

        cvFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickFavorite();
            }
        });
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyProduct();
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void showVegetableDetail(int position){
        Vegetable vegetable = VegetablesData.getListData().get(position);

        if(vegetable.getImage().length > 0) {
            Glide.with(this)
                    .load(vegetable.getImage()[0])
                    .into(imgVegetable);

            setUpRecyclerImage(vegetable.getImage());
        }

        tvName.setText(vegetable.getName());
        tvPrice.setText("Rp "+vegetable.getPrice());
        tvDescription.setText(vegetable.getDetail());
        tvStore.setText(vegetable.getStore());
        tvAddress.setText(vegetable.getAddress());
        tvStock.setText("Tersedia "+vegetable.getStock()+" "+vegetable.getUnit());
    }

    private void clickFavorite(){
        if(isFavorite){
            isFavorite = false;
            imgFavorite.setImageResource(R.drawable.ic_favorite_off);
            Toast.makeText(this, "Dihilangkan dari Wishlist", Toast.LENGTH_SHORT).show();
        }else{
            isFavorite = true;
            imgFavorite.setImageResource(R.drawable.ic_favorite_on);
            Toast.makeText(this, "Ditambahkan ke Wishlist", Toast.LENGTH_SHORT).show();
        }
    }

    private void buyProduct(){
        Toast.makeText(this, "Terimakasih, silahkan menunggu pesanan anda :)", Toast.LENGTH_SHORT).show();
    }

    private void addToCart(){
        Toast.makeText(this, "Produk Ditambahkan Ke Keranjang", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setUpRecyclerImage(String[] images){
        listImages = images;
        rvVegetableImage.setLayoutManager(new LinearLayoutManager(VegetableDetailActivity.this, LinearLayoutManager.HORIZONTAL, false));
        ListVegetableImageAdapter listVegetableImageAdapter = new ListVegetableImageAdapter(listImages);
        rvVegetableImage.setAdapter(listVegetableImageAdapter);

        listVegetableImageAdapter.setOnItemClickCallback(new ListVegetableImageAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(String image_url) {
                Glide.with(VegetableDetailActivity.this)
                        .load(image_url)
                        .into(imgVegetable);
            }
        });
    }
}
