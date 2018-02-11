package iak.edwin.sunshine;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout temp_layout;
    LinearLayout alarm;
    TextView text_tempt_unit;
    TextView text_onoff;
    AlertDialog alertDialog1;
    CharSequence[] values = {"Metric (Celcius)","Imperial (Farenheit) "};
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        temp_layout=(LinearLayout)findViewById(R.id.layout_temp);
        alarm=(LinearLayout)findViewById(R.id.layout_alarm);
        text_tempt_unit=(TextView)findViewById(R.id.txt_temp_unit);
        text_onoff=(TextView)findViewById(R.id.txt_on_off);
        temp_layout.setOnClickListener(this);
        alarm.setOnClickListener(this);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();
        text_tempt_unit.setText(pref.getString("temp",""));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_temp:
                showDialogTemp();
                break;
            case R.id.layout_alarm:
                showDialogAlarm();
                break;
        }
    }

    private void showDialogAlarm() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
            }
        }, hour, minute, true);//Yes 24 hour time
        mTimePicker.setTitle("Select Time");
        mTimePicker.show();
    }

    private void showDialogTemp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Your Choice");
        builder.setSingleChoiceItems(values, -1, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int item) {

                switch(item)
                {
                    case 0:
                        text_tempt_unit.setText("Metric (Celcius)");
                        editor.putString("temp", text_tempt_unit.getText().toString());
                        editor.commit();

                        break;
                    case 1:
                        text_tempt_unit.setText("Imperial (Farenheit)");
                        editor.putString("temp", text_tempt_unit.getText().toString());
                        break;

                }
                alertDialog1.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog1.dismiss();
            }
        });
        alertDialog1 = builder.create();
        alertDialog1.show();
    }
}
