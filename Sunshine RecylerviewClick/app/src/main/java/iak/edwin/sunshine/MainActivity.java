package iak.edwin.sunshine;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CuacaAdapter adapter;
    List<ListCuacaRepons> list;
    TextView derajat;
    TextView derajat1;
    TextView cuaca;
    ImageView icon_cuaca;
    TextView today;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        today=(TextView)findViewById(R.id.text_today);
        derajat=(TextView)findViewById(R.id.text_derajat);
        derajat1=(TextView)findViewById(R.id.text_derajat1);
        cuaca=(TextView)findViewById(R.id.text_cuaca);
        icon_cuaca=(ImageView)findViewById(R.id.icon_cuaca);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        list=new ArrayList<ListCuacaRepons>();
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ServiceGenerator.getInstance().getForecasr().enqueue(new Callback<CuacaRespons>() {
            @Override
            public void onResponse(Call<CuacaRespons> call, Response<CuacaRespons> response) {
                progressDialog.dismiss();
                if (response.isSuccessful()){
                    today.setText(SunshineDateUtils.getFriendlyDateString(MainActivity.this,response.body().getList().get(0).getDt(),false));
                    derajat.setText(SunshineWeatherUtils.formatTemperature(MainActivity.this,response.body().getList().get(0).getTemp().getDay()));
                    derajat1.setText(SunshineWeatherUtils.formatTemperature(MainActivity.this,response.body().getList().get(0).getTemp().getMin()));
                    cuaca.setText(response.body().getList().get(0).getWeather().get(0).getMain());
                    icon_cuaca.setImageDrawable(getResources().getDrawable(SunshineWeatherUtils.getArtResourceForWeatherCondition(response.body().getList().get(0).getWeather().get(0).getId())));

                    for (int i=0; i<response.body().getList().size();i++){
                        if (i==0){
                            continue;
                        }else {
                            list.add(response.body().getList().get(i));
                            adapter=new CuacaAdapter(list,MainActivity.this);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CuacaRespons> call, Throwable t) {
                progressDialog.dismiss();

                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
