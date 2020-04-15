package com.zgenit.kulsayur;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private CircleImageView imgProfile;
    private TextView tvName, tvEmail;
    private CardView cvProfile, cvPortfolio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("About");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        imgProfile = findViewById(R.id.img_profile);
        tvName = findViewById(R.id.tv_name);
        tvEmail = findViewById(R.id.tv_email);
        cvProfile = findViewById(R.id.cv_profile);
        cvPortfolio = findViewById(R.id.cv_portfolio);

        getUserDetail();

        cvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("https://www.dicoding.com/users/175353");
            }
        });

        cvPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink("https://www.dicoding.com/users/175353/academies");
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void getUserDetail(){
        Glide.with(ProfileActivity.this)
                .load("https://d17ivq9b7rppb3.cloudfront.net/small/avatar/20200408085221a4ea42e41374ebf5f3615e0e0f4dffc2.png")
                .apply(new RequestOptions().override(75,75))
                .into(imgProfile);

        tvName.setText("Mahfudz Ainur Rif'an");
        tvEmail.setText("mfdsix.1nd0@gmail.com");
    }

    private void openLink(String url){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
