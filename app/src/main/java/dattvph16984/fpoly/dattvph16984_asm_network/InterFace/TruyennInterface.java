package dattvph16984.fpoly.dattvph16984_asm_network.InterFace;

import java.util.List;

import dattvph16984.fpoly.dattvph16984_asm_network.DTO.TruyenDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TruyennInterface {
    @GET("/TruyenTranh")
    Call<List<TruyenDTO>> lay_list_truyen();

    @POST("/TruyenTranh")
    Call<TruyenDTO> them_truyen(@Body TruyenDTO ObjTruyen);
}
