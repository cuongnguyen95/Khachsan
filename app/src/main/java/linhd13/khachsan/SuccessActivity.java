package linhd13.khachsan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SuccessActivity extends AppCompatActivity {
    private TextView txtTenkhachsan , txtDiachi , txtTenkhachhang ,txtSdt ,txtSongaythue ,txtGiaphong , txtTongtien , txtSophongthue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        txtTenkhachsan = (TextView)findViewById(R.id.tenkhachsan);
        txtDiachi = (TextView)findViewById(R.id.diachi);
        txtTenkhachhang = (TextView)findViewById(R.id.tenkhachhang);
        txtSdt = (TextView)findViewById(R.id.sdt);
        txtSophongthue = (TextView)findViewById(R.id.sophongthue);
        txtGiaphong = (TextView)findViewById(R.id.giaphong);
        txtSongaythue = (TextView)findViewById(R.id.songaythue);
        txtTongtien = (TextView)findViewById(R.id.tongtien);

        Intent intent2 = getIntent();
        Bundle bundle = intent2.getBundleExtra("thongtin");

        txtTenkhachsan.setText(bundle.getString("tenkhachsan"));
        txtDiachi.setText(bundle.getString("diachi"));
        txtTenkhachhang.setText(bundle.getString("name"));
        txtSdt.setText(bundle.getString("sdt"));
        txtSophongthue.setText(bundle.getString("sophong"));
        txtGiaphong.setText(bundle.getLong("gia") + "");
        txtSongaythue.setText(bundle.getLong("songay") + "");
        txtTongtien.setText(bundle.getLong("tongsotien") + "");
    }
}
