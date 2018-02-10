package iak.edwin.sunshine;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by EDN on 2/10/2018.
 */

public class CuacaRespons {
    @SerializedName("list")
    @Expose
    private List<ListCuacaRepons> list;

    public List<ListCuacaRepons> getList() {
        return list;
    }

    public void setList(List<ListCuacaRepons> list) {
        this.list = list;
    }
}
