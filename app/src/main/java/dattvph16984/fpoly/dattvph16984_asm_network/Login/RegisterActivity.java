package dattvph16984.fpoly.dattvph16984_asm_network.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import dattvph16984.fpoly.dattvph16984_asm_network.DTO.UserDTO;
import dattvph16984.fpoly.dattvph16984_asm_network.InterFace.UserInterface;
import dattvph16984.fpoly.dattvph16984_asm_network.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    public EditText ed_nameR, ed_emailR, ed_passwordR;
    ImageView img_register;
    private List<UserDTO> list = new ArrayList<UserDTO>();
    private Boolean isSuccessful;
    String Base_url = "https://637278d6348e947299f6766d.mockapi.io/asm/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        img_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                POST_Retrofit();
            }
        });
    }

    void POST_Retrofit() {
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
                    list = response.body();
                    Log.d("TAG", "onResponse: " +list);
                }
           }
           @Override
           public void onFailure(Call<List<UserDTO>> call, Throwable t) {
           }
       });

        String passWordPattern = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        if (ed_nameR.getText().toString().trim().isEmpty()
                || ed_passwordR.getText().toString().trim().isEmpty()
                || ed_emailR.getText().toString().trim().isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Không để trống các trường dữ liệu", Toast.LENGTH_SHORT).show();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(ed_emailR.getText().toString()).matches()) {
            Toast.makeText(this, "Email không đúng định dạng", Toast.LENGTH_SHORT).show();
            return;
        } else if (!Pattern.compile(passWordPattern).matcher(ed_passwordR.getText().toString()).matches() && ed_passwordR.getText().toString().length() < 6) {
            Toast.makeText(this, "Mật khẩu Không chứa ký tự đặc biệt và phải từ 6 ký tự trở lên", Toast.LENGTH_SHORT).show();
            return;

        } else {
            for (int i = 0; i < list.size(); i++) {
                UserDTO userDTO = list.get(i);
                if (ed_nameR.getText().toString().equals(userDTO.getUsername())) {
                    Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

        //Tạo đối tượng gửi lên server
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(ed_emailR.getText().toString());
        userDTO.setUsername(ed_nameR.getText().toString());
        userDTO.setPassword(ed_passwordR.getText().toString());

        Call<UserDTO> PostUser = userInterface.them_moi_nguoi_dung(userDTO);

        PostUser.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    UserDTO userDTO = response.body();
                    Log.d("zzz", "onResponse: " + userDTO.getUsername());
                    if (user != null) {
                        ed_nameR.setText("");
                        ed_emailR.setText("");
                        ed_passwordR.setText("");
                        Toast.makeText(RegisterActivity.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        isSuccessful = true;
                    } else {
                        Toast.makeText(RegisterActivity.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                        isSuccessful = false;
                    }
                } else {
                    Log.d("zzzzz", "onResponse: lỗi " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
            }
        });
    }
        }
        private void init () {
            ed_emailR = findViewById(R.id.ed_emailR);
            ed_nameR = findViewById(R.id.ed_nameR);
            ed_passwordR = findViewById(R.id.ed_passR);
            img_register = findViewById(R.id.img_register);

        }
    }
