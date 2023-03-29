package dattvph16984.fpoly.dattvph16984_asm_network.InterFace;

import java.util.List;

import dattvph16984.fpoly.dattvph16984_asm_network.DTO.BinhLuanDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BinhLuanInterface {
    @GET("/BinhLuan")
    Call<List<BinhLuanDTO>> lay_list_binh_luan();

    @POST("/BinhLuan")
    Call<BinhLuanDTO> them_binh_luan_truyen(@Body BinhLuanDTO ObjBinhLuan);
}
