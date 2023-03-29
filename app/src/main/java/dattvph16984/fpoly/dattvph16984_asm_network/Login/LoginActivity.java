package dattvph16984.fpoly.dattvph16984_asm_network.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import dattvph16984.fpoly.dattvph16984_asm_network.DTO.UserDTO;
import dattvph16984.fpoly.dattvph16984_asm_network.InterFace.UserInterface;
import dattvph16984.fpoly.dattvph16984_asm_network.R;
import dattvph16984.fpoly.dattvph16984_asm_network.TruyenActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private TextView tv_register;
    private Button btn_fb,btn_gg;
    private ImageView img_login;
    EditText ed_email,ed_password;
    private List<UserDTO> list = new ArrayList<UserDTO>();
    String Base_url = "https://637278d6348e947299f6766d.mockapi.io/asm/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        img_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    void login(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        UserInterface userInterface = retrofit.create(UserInterface.class);
        Call<List<UserDTO>> user = userInterface.lay_danh_sach();
        user.enqueue(new Callback<List<UserDTO>>() {
            @Override
            public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                if(response.isSuccessful()) {
                    for (int i = 0; i < list.size(); i++) {
                        UserDTO userDTO = list.get(i);
                        if (ed_email.getText().toString().equals(userDTO.getEmail())
                                && ed_password.getText().toString().equals(userDTO.getPassword())) {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, TruyenActivity.class);
                            startActivity(intent);
                            ed_email.setText("");
                            ed_password.setText("");

                        }else if (ed_email.getText().toString().isEmpty() || ed_password.getText().toString().isEmpty()){
                            Toast.makeText(LoginActivity.this, "Tài khoản và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("zzzzzz", "onResponse: sp thứ " + i + "==" + userDTO.getUsername());
                    }
                    list = response.body();
                }
            }
            @Override
            public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                Log.e("TAG","onFai");
            }

        });

    }
    private void init(){
        tv_register = findViewById(R.id.tv_register);
        img_login = findViewById(R.id.img_login);
        ed_email = findViewById(R.id.edEmail);
        ed_password = findViewById(R.id.edPass);
    }
}