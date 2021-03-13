package com.example.carsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    CarServiceApi carServiceApi;
    private TextView textView;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit=new Retrofit.Builder()
                               .baseUrl("https://private-anon-97c1ba0fb0-carsapi1.apiary-mock.com/manufacturers/")
                               .addConverterFactory(GsonConverterFactory.create())
                               .build();
        carServiceApi=retrofit.create(CarServiceApi.class);

        textView=findViewById(R.id.textView);
        listView=findViewById(R.id.listView);

        getAllCarsApi();




    }

    private void getAllCarsApi(){
        Call<List<CarAttributes>> call=carServiceApi.getAllCars();
        ArrayList<String> carNames=new ArrayList<>();
        call.enqueue(new Callback<List<CarAttributes>>() {
            @Override
            public void onResponse(Call<List<CarAttributes>> call, Response<List<CarAttributes>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Response Failed",Toast.LENGTH_SHORT).show();
                    return;
                }
                List<CarAttributes> carAttributes=response.body();
                for(CarAttributes attributes:carAttributes){
                    String nameUP=attributes.getName().toUpperCase();
                    carNames.add(nameUP);
                }

                adapter=new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,carNames);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(
                        new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent=new Intent(MainActivity.this,DetailsActivity.class);
                                intent.putExtra("num_models",carAttributes.get(position).getNum_model());
                                intent.putExtra("img_url",carAttributes.get(position).getImg_url());
                                intent.putExtra("id",carAttributes.get(position).getId());
                                intent.putExtra("max_car_id",carAttributes.get(position).getMax_car_id());
                                intent.putExtra("hp",carAttributes.get(position).getAvg_hp());
                                intent.putExtra("price",carAttributes.get(position).getAvg_price());
                                intent.putExtra("name",carAttributes.get(position).getName());
                                startActivity(intent);
                            }
                        }
                );



            }

            @Override
            public void onFailure(Call<List<CarAttributes>> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
