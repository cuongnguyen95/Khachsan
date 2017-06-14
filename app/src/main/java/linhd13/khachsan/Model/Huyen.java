package linhd13.khachsan.Model;

/**
 * Created by cuong pc on 6/11/2017.
 */

public class Huyen {
    Long Id ;
    String Matinh , Mahuyen , Tenhuyen ;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getMatinh() {
        return Matinh;
    }

    public void setMatinh(String matinh) {
        Matinh = matinh;
    }

    public String getMahuyen() {
        return Mahuyen;
    }

    public void setMahuyen(String mahuyen) {
        Mahuyen = mahuyen;
    }

    public String getTenhuyen() {
        return Tenhuyen;
    }

    public void setTenhuyen(String tenhuyen) {
        Tenhuyen = tenhuyen;
    }
}
