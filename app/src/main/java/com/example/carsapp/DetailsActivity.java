package com.example.carsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Bundle bundle=getIntent().getExtras();

        imageView=findViewById(R.id.imageView);
        textViewResult=findViewById(R.id.textViewResult);
        if(bundle!=null){
            String name=bundle.getString("name").toUpperCase();
            Double price=bundle.getDouble("price");
            String mPrice=String.format("%.3f",price).toString();
            int id=bundle.getInt("id");
            Double hp=bundle.getDouble("hp");
            String mHp=String.format("%.3f",hp).toString();
            int max_car_id=bundle.getInt("max_car_id");
            int num_models=bundle.getInt("num_models");
            String img_url=bundle.getString("img_url");

            Picasso.with(DetailsActivity.this).load(img_url)
                    .resize(70,70)
                    .centerCrop()
                    .placeholder(R.drawable.car)
                    .error(R.drawable.car)
                    .into(imageView);

            String content="";
            content+="Name: "+name+"\n";
            content+="Average Price: "+mPrice+"\n";
            content+="Average HorsePower: "+mHp+"\n";
            content+="Max Car Id: "+max_car_id+"\n";
            content+="Id: "+id+"\n";
            content+="Num Models: "+num_models+"\n";

            textViewResult.setText(content);

        }
    }
}