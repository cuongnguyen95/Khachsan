package linhd13.khachsan.Model;

import java.util.ArrayList;

/**
 * Created by cuong pc on 6/11/2017.
 */

public class Tinh {
    Long id ;
    String Matinh , Tentinh ;

    public Tinh() {
    }

    public Tinh(String matinh, String tentinh) {
        Matinh = matinh;
        Tentinh = tentinh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatinh() {
        return Matinh;
    }

    public void setMatinh(String matinh) {
        Matinh = matinh;
    }

    public String getTentinh() {
        return Tentinh;
    }

    public void setTentinh(String tentinh) {
        Tentinh = tentinh;
    }

    public ArrayList<Tinh> AllTinh(){
        ArrayList<Tinh> lstTinh = new ArrayList<>();
        Tinh t1 = new Tinh("01", "Hà Nội");
        Tinh t2 = new Tinh("02", "Đà Nẵng");
        Tinh t3 = new Tinh("03", "TP.Hồ Chí Minh");
        lstTinh.add(t1);
        lstTinh.add(t2);
        lstTinh.add(t3);
        return lstTinh;
    }
}
