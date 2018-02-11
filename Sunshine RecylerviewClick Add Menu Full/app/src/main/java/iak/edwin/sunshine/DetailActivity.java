package iak.edwin.sunshine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView day,date,derajat,derjat1,cuaca,humadity,preasure,wind;
    ImageView icon_cuaca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        day=(TextView)findViewById(R.id.text_day);
        date=(TextView)findViewById(R.id.text_date);
        derajat=(TextView)findViewById(R.id.text_derajat);
        derjat1=(TextView)findViewById(R.id.text_derajat1);
        cuaca=(TextView)findViewById(R.id.text_cuaca);
        humadity=(TextView)findViewById(R.id.text_humadity);
        preasure=(TextView)findViewById(R.id.text_preasure);
        wind=(TextView)findViewById(R.id.text_wind);
        icon_cuaca=(ImageView)findViewById(R.id.icon_cuaca);
        String dayDate=SunshineDateUtils.getFriendlyDateString(this,getIntent().getIntExtra("date",0),true);
        String daySplit[]=dayDate.split(",");
        day.setText(daySplit[0]);
        date.setText(daySplit[1]);
        derajat.setText(getIntent().getStringExtra("derajat"));
        derjat1.setText(getIntent().getStringExtra("derajat1"));
        cuaca.setText(getIntent().getStringExtra("cuaca"));
        humadity.setText("Humadity : "+String.valueOf(getIntent().getIntExtra("humadity",0)+ " %"));
        preasure.setText("Preasure : "+String.valueOf(getIntent().getDoubleExtra("preasure",0)+ " hPa"));
        wind.setText("Wind: "+
                SunshineWeatherUtils.getFormattedWind(this,getIntent().getFloatExtra("wind",0),
                        getIntent().getFloatExtra("deg",0)));
        icon_cuaca.setImageDrawable(getResources().getDrawable(SunshineWeatherUtils.getArtResourceForWeatherCondition(getIntent().getIntExtra("icon",0))));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_setting:
                startActivity(new Intent(this,SettingActivity.class));
                break;
            case R.id.menu_share:
                break;
        }
        return super.onOptionsItemSelected(item);

    }
}
