package dattvph16984.fpoly.dattvph16984_asm_network.DTO;

import java.io.Serializable;
import java.util.List;

public class TruyenDTO implements Serializable {
    String tenTruyen;
    String moTaNgan;
    String tenTacGia;
    String namSanXat;
    String anhBia;
    List<String> dsAnhNoiDungTruyen;
    int id;

    public TruyenDTO(){

    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getMoTaNgan() {
        return moTaNgan;
    }

    public void setMoTaNgan(String moTaNgan) {
        this.moTaNgan = moTaNgan;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public String getNamSanXat() {
        return namSanXat;
    }

    public void setNamSanXat(String namSanXat) {
        this.namSanXat = namSanXat;
    }

    public String getAnhBia() {
        return anhBia;
    }

    public void setAnhBia(String anhBia) {
        this.anhBia = anhBia;
    }

    public List<String> getDsAnhNoiDungTruyen() {
        return dsAnhNoiDungTruyen;
    }

    public void setDsAnhNoiDungTruyen(List<String> dsAnhNoiDungTruyen) {
        this.dsAnhNoiDungTruyen = dsAnhNoiDungTruyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
