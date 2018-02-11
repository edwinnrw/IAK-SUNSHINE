package iak.edwin.sunshine;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by EDN on 2/10/2018.
 */

public interface ApiService {

    @GET("./data/2.5/forecast/daily?q=malang&mode=json&units=metric&cnt=7&APPID=93a3696714297ee5a9f65486aa8cb824")
    Call<CuacaRespons> getForecast();
//
//
//
//    @GET(".3/movie/{movie_id}?api_key=<<api_key>>&language=en-US")
//    Call<CuacaRespons> getDetail(@Path("movie_id")String id);
}
