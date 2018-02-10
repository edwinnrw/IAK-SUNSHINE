package iak.edwin.sunshine;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by EDN on 2/10/2018.
 */

public class ServiceGenerator {
    private static ServiceGenerator instance=null;
    private ApiService service;
    String base_url="http://api.openweathermap.org/";
    public ServiceGenerator() {
        Retrofit retrofit = createAdapter().build();
        service = retrofit.create(ApiService.class);    }

    private Retrofit.Builder createAdapter() {
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create());
    }

    public static ServiceGenerator getInstance(){
        if (instance==null){
            instance=new ServiceGenerator();
        }
        return instance;
    }
    public Call<CuacaRespons> getForecasr(){
        return service.getForecast();
    }
}
