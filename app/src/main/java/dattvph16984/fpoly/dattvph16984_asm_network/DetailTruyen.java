package dattvph16984.fpoly.dattvph16984_asm_network;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import dattvph16984.fpoly.dattvph16984_asm_network.DTO.BinhLuanDTO;
import dattvph16984.fpoly.dattvph16984_asm_network.DTO.TruyenDTO;
import dattvph16984.fpoly.dattvph16984_asm_network.InterFace.BinhLuanInterface;
import dattvph16984.fpoly.dattvph16984_asm_network.InterFace.TruyennInterface;
import dattvph16984.fpoly.dattvph16984_asm_network.adapter.DeltailAdapter;
import dattvph16984.fpoly.dattvph16984_asm_network.adapter.TruyenAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailTruyen extends AppCompatActivity {
    TextView tenTruyen,tenTacGia;
    ImageView avatar,img_Send;
    EditText ed_cmt;


    private ArrayList<BinhLuanDTO> listBinhLuan;
    private ArrayList<TruyenDTO> listTruyen;
    private DeltailAdapter adapter;
    private RecyclerView rcvDetailBinhLuan;
    String Base_url = "https://637278d6348e947299f6766d.mockapi.io/asm/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_truyen);
        tenTruyen = findViewById(R.id.tv_tenTruyen);
        tenTacGia = findViewById(R.id.tv_tenTG);
        avatar = findViewById(R.id.avt_truyenS);
        img_Send = findViewById(R.id.img_send);
        ed_cmt = findViewById(R.id.ed_cmt);
        //
        adapter = new DeltailAdapter(this,listBinhLuan);
        rcvDetailBinhLuan = findViewById(R.id.rcv_item_binh_luan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvDetailBinhLuan.setLayoutManager(linearLayoutManager);
        listBinhLuan = new ArrayList<>();
        Intent intent = getIntent();
        TruyenDTO truyen = (TruyenDTO) intent.getSerializableExtra("truyen");
        tenTruyen.setText("Truyện: "+truyen.getTenTruyen());
        tenTacGia.setText("Tác Giả: "+truyen.getTenTacGia());
        Glide.with(this)
                .load(truyen.getAnhBia())
                .centerCrop()
                .into(avatar);
        GET_Retrofit();
        img_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                POST_Retrofit_CMT();
                ed_cmt.setText("");
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        GET_Retrofit();
                    }
                };
                Thread thread = new Thread(runnable);
                try {
                    thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                thread.start();
            }
        });

    }

    void GET_Retrofit() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        BinhLuanInterface productInterface = retrofit.create(BinhLuanInterface.class);
        Call<List<BinhLuanDTO>> objCall = productInterface.lay_list_binh_luan();

        objCall.enqueue(new Callback<List<BinhLuanDTO>>() {
            @Override
            public void onResponse(Call<List<BinhLuanDTO>> call, Response<List<BinhLuanDTO>> response) {
                listBinhLuan.clear();
                //kết quả trả về xử lý ở đây
                listBinhLuan = (ArrayList<BinhLuanDTO>) response.body();
                adapter.setData(listBinhLuan);
                rcvDetailBinhLuan.setAdapter(adapter);
                if (response.isSuccessful()){
                    List<BinhLuanDTO> ds_sp = response.body();
                    for(int i = 0; i < ds_sp.size(); i++){
                        BinhLuanDTO objPro = ds_sp.get(i);

                        Log.d("zzzzzz", "onResponse: sp truyen thứ " + i + "==" + objPro.getNoiDung());
                    }
                }else {
                    Log.d("zzzzz", "onResponse: lỗi " + response.errorBody() );
                }
            }
            @Override
            public void onFailure(Call<List<BinhLuanDTO>> call, Throwable t) {
                //Chết server sẽ chạy hàm này
                Log.e("TAG","onFai");

            }
        });
    }
    void POST_Retrofit_CMT() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        BinhLuanInterface productInterface = retrofit.create(BinhLuanInterface.class);
        //Tạo đối tượng gửi lên server
        BinhLuanDTO binhLuanDTO = new BinhLuanDTO();
        binhLuanDTO.setNoiDung(ed_cmt.getText().toString());

        Call<BinhLuanDTO> objCall = productInterface.them_binh_luan_truyen(binhLuanDTO);
        objCall.enqueue(new Callback<BinhLuanDTO>() {
            @Override
            public void onResponse(Call<BinhLuanDTO> call, Response<BinhLuanDTO> response) {

                if(response.isSuccessful()){
                    BinhLuanDTO binhLuanDTO = response.body();
                    Log.d("zzz", "Bình Luận: "+binhLuanDTO.getNoiDung());

                }else {
                    Log.d("zzzzz", "onResponse: lỗi " + response.errorBody() );
                }
            }
            @Override
            public void onFailure(Call<BinhLuanDTO> call, Throwable t) {

            }
        });

    }



}