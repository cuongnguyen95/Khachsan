package linhd13.khachsan.Model;

/**
 * Created by cuong pc on 6/11/2017.
 */

public class KhachSan {
    Long Id , Sao , Gia , Sophong , Sophongcon ;
    String Matinh , Mahuyen , Ten , Diachi , Sdt , Anh , nAnh , Kinhdo , Vido;

    public Long getId() {
        return Id;
    }

    public String getKinhdo() {
        return Kinhdo;
    }

    public void setKinhdo(String kinhdo) {
        Kinhdo = kinhdo;
    }

    public String getVido() {
        return Vido;
    }

    public void setVido(String vido) {
        Vido = vido;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getSao() {
        return Sao;
    }

    public void setSao(Long sao) {
        Sao = sao;
    }

    public Long getGia() {
        return Gia;
    }

    public void setGia(Long gia) {
        Gia = gia;
    }

    public Long getSophong() {
        return Sophong;
    }

    public void setSophong(Long sophong) {
        Sophong = sophong;
    }

    public Long getSophongcon() {
        return Sophongcon;
    }

    public void setSophongcon(Long sophongcon) {
        Sophongcon = sophongcon;
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

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String diachi) {
        Diachi = diachi;
    }

    public String getSdt() {
        return Sdt;
    }

    public void setSdt(String sdt) {
        Sdt = sdt;
    }

    public String getAnh() {
        return Anh;
    }

    public void setAnh(String anh) {
        Anh = anh;
    }

    public String getnAnh() {
        return nAnh;
    }

    public void setnAnh(String nAnh) {
        this.nAnh = nAnh;
    }
}
