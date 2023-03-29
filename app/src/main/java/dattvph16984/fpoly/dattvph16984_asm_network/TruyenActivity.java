package dattvph16984.fpoly.dattvph16984_asm_network;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import dattvph16984.fpoly.dattvph16984_asm_network.DTO.TruyenDTO;
import dattvph16984.fpoly.dattvph16984_asm_network.InterFace.TruyennInterface;
import dattvph16984.fpoly.dattvph16984_asm_network.adapter.TruyenAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TruyenActivity extends AppCompatActivity {
   private ArrayList<TruyenDTO> listTruyen;
   //private List<TruyenDTO> listTruyen;
    private TruyenAdapter adapter;
    private RecyclerView rcvTruyen;
    private Boolean isSuccessful;
    String Base_url = "https://637278d6348e947299f6766d.mockapi.io/asm/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truyen);

        adapter = new TruyenAdapter(this,listTruyen);
        rcvTruyen = findViewById(R.id.rcv_truyen);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvTruyen.setLayoutManager(linearLayoutManager);
        listTruyen = new ArrayList<>();

        GET_Retrofit();
    }
    void GET_Retrofit() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        TruyennInterface productInterface = retrofit.create(TruyennInterface.class);
        Call<List<TruyenDTO>> objCall = productInterface.lay_list_truyen();

        objCall.enqueue(new Callback<List<TruyenDTO>>() {
            @Override
            public void onResponse(Call<List<TruyenDTO>> call, Response<List<TruyenDTO>> response) {
                //kết quả trả về xử lý ở đây
                listTruyen = (ArrayList<TruyenDTO>) response.body();
                Log.d("list", "onResponse: "+listTruyen);
                adapter.setData(listTruyen);
                rcvTruyen.setAdapter(adapter);
                if (response.isSuccessful()){
                    List<TruyenDTO> ds_sp = response.body();
                    for(int i = 0; i < ds_sp.size(); i++){
                        TruyenDTO objPro = ds_sp.get(i);

                        Log.d("zzzzzz", "onResponse: sp truyen thứ " + i + "==" + objPro.getTenTruyen());
                    }
                }else {
                    Log.d("zzzzz", "onResponse: lỗi " + response.errorBody() );
                }
            }
            @Override
            public void onFailure(Call<List<TruyenDTO>> call, Throwable t) {
                //Chết server sẽ chạy hàm này
                Log.e("TAG","onFai");

            }
        });
    }
}