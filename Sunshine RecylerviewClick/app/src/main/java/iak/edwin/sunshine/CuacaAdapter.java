package iak.edwin.sunshine;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Callback;

/**
 * Created by EDN on 2/10/2018.
 */

public class CuacaAdapter extends RecyclerView.Adapter<CuacaAdapter.ViewHolder> {
    List<ListCuacaRepons> mDataSet;
    Context context;

    public CuacaAdapter(List<ListCuacaRepons> mDataSet, Context context) {
        this.mDataSet = mDataSet;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;

        v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewHolder holderBind = (ViewHolder) holder;
        ListCuacaRepons iteminfo = (ListCuacaRepons) mDataSet.get(position);
        Log.d("CucacaDay",String.valueOf(iteminfo.getDt()));
        holderBind.date=iteminfo.getDt();
        holderBind.iconOr=iteminfo.getWeather().get(0).getId();
        holderBind.textViewHari.setText(SunshineDateUtils.getFriendlyDateString(context,iteminfo.getDt(),false));
        holderBind.textViewCuaca.setText(iteminfo.getWeather().get(0).getMain());
        holderBind.textViewDerajat.setText(SunshineWeatherUtils.formatTemperature(context,iteminfo.getTemp().getDay()));
        holderBind.textViewDerajat1.setText(SunshineWeatherUtils.formatTemperature(context,iteminfo.getTemp().getMin()));
        holder.icon.setImageDrawable(context.getResources().getDrawable(SunshineWeatherUtils.getIconResourceForWeatherCondition(iteminfo.getWeather().get(0).getId())));
        holderBind.context=this.context;
        holder.wind=iteminfo.getSpeed();
        holder.deg=iteminfo.getDeg();
        holder.humadiy= iteminfo.getHumidity();
        holder.preasure= iteminfo.getPressure();
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView icon;
        TextView textViewHari;
        TextView textViewCuaca;
        TextView textViewDerajat;
        TextView textViewDerajat1;
        int date,iconOr;
        Context context;
        Double preasure;
        Integer humadiy;
        Float deg,wind;
        public ViewHolder(View itemView) {
            super(itemView);
            icon=(ImageView) itemView.findViewById(R.id.icon_cuaca);
            textViewCuaca=(TextView)itemView.findViewById(R.id.txt_cuaca);
            textViewHari=(TextView)itemView.findViewById(R.id.text_hari);
            textViewDerajat=(TextView)itemView.findViewById(R.id.text_derajat);
            textViewDerajat1=(TextView)itemView.findViewById(R.id.text_derajat1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context,DetailActivity.class);
            intent.putExtra("cuaca",textViewCuaca.getText().toString());
            intent.putExtra("derajat",textViewDerajat.getText().toString());
            intent.putExtra("derajat1",textViewDerajat1.getText().toString());
            intent.putExtra("date",date);
            intent.putExtra("icon",iconOr);
            intent.putExtra("humadity",humadiy);
            intent.putExtra("preasure",preasure);
            intent.putExtra("wind",wind);
            intent.putExtra("deg",deg);
            context.startActivity(intent);
        }
    }
}
