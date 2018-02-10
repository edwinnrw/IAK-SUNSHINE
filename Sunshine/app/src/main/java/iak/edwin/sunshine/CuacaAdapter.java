package iak.edwin.sunshine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        holderBind.textViewHari.setText(SunshineDateUtils.getFriendlyDateString(context,iteminfo.getDt(),true));
        holderBind.textViewCuaca.setText(iteminfo.getWeather().get(0).getMain());
        holderBind.textViewDerajat.setText(SunshineWeatherUtils.formatTemperature(context,iteminfo.getTemp().getDay()));
        holderBind.textViewDerajat1.setText(SunshineWeatherUtils.formatTemperature(context,iteminfo.getTemp().getMin()));
        holder.icon.setImageDrawable(context.getResources().getDrawable(SunshineWeatherUtils.getIconResourceForWeatherCondition(iteminfo.getWeather().get(0).getId())));
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView icon;
        TextView textViewHari;
        TextView textViewCuaca;
        TextView textViewDerajat;
        TextView textViewDerajat1;

        public ViewHolder(View itemView) {
            super(itemView);
            icon=(ImageView) itemView.findViewById(R.id.icon_cuaca);
            textViewCuaca=(TextView)itemView.findViewById(R.id.txt_cuaca);
            textViewHari=(TextView)itemView.findViewById(R.id.text_hari);
            textViewDerajat=(TextView)itemView.findViewById(R.id.text_derajat);
            textViewDerajat1=(TextView)itemView.findViewById(R.id.text_derajat1);
        }
    }
}
