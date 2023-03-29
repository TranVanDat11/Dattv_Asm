package dattvph16984.fpoly.dattvph16984_asm_network.InterFace;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.regex.Pattern;

import dattvph16984.fpoly.dattvph16984_asm_network.DTO.UserDTO;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserInterface {
    //get lấy danh sách
    //post tạo mới danh sách

    @GET("/nguoiDung")
    Call<List<UserDTO>> lay_danh_sach();

    @POST("/nguoiDung")
    Call<UserDTO> them_moi_nguoi_dung(@Body UserDTO ObjUser);
}
