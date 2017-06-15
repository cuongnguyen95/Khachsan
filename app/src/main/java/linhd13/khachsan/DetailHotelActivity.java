package linhd13.khachsan;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

public class DetailHotelActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap map;
    private MapFragment mapFragment;
    private Context context;
    private ImageButton imgSwich;
    private Button btn1, btnDK;
    private TextView txtTenKS, txtDiaChiKS, txtSdtKS, txtGiaKS;
    private Bundle bundle , bundle1 ;
    private String nhanphong , traphong ,sophong ;
    private Long  songay ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hotel);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.myMap);
        mapFragment.getMapAsync(this);
        imgSwich = (ImageButton) findViewById(R.id.imgSwich);
        txtTenKS = (TextView) findViewById(R.id.txtTenKS);
        txtDiaChiKS = (TextView) findViewById(R.id.txtDiaChiKS);
        txtSdtKS = (TextView) findViewById(R.id.txtSdtKS);
        txtGiaKS = (TextView) findViewById(R.id.txtGiaKS);
        btn1 = (Button) findViewById(R.id.button1);
        btnDK = (Button) findViewById(R.id.buttonDK);


        Intent intent = getIntent();
        bundle = intent.getBundleExtra("dulieu");
        nhanphong = bundle.getString("nhanphong");
        traphong = bundle.getString("traphong");
        sophong = bundle.getString("sophong");
        songay = bundle.getLong("songay");
        Picasso.with(context).load(bundle.getString("Anh")).into(imgSwich);
        txtTenKS.setText("Khách sạn : " + bundle.getString("Tensk"));
        txtDiaChiKS.setText("Địa chỉ : " + bundle.getString("Diachi"));
        txtSdtKS.setText("Liên hệ\n " + bundle.getString("Sdt"));

        String price = String.valueOf(bundle.getLong("Gia"));
        double amount = Double.parseDouble(price);
        txtGiaKS.setText("Giá (VNĐ)\n" + String.format("%,.0f", amount));

        if (bundle.getLong("Sophongcon") > 0) {
            btn1.setText("Còn Phòng");
            btn1.setBackgroundColor(Color.parseColor("#00A855"));
            btn1.setTextColor(Color.WHITE);

            btnDK.setBackgroundColor(Color.parseColor("#00A855"));
            btnDK.setTextColor(Color.WHITE);
        } else {
            btn1.setText("Hết Phòng");
            btn1.setBackgroundColor(Color.parseColor("red"));
            btn1.setTextColor(Color.WHITE);

            btnDK.setVisibility(View.INVISIBLE);
        }


        imgSwich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DetailHotelActivity.this, ListImageActivity.class);
                intent1.putExtra("anh", bundle.getString("nAnh"));
                intent1.putExtra("anh", bundle);
                startActivity(intent1);
            }
        });

        btnDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DetailHotelActivity.this , OrderActivity.class);
                bundle1 = new Bundle();
                bundle1.putLong("Id_khachSan" ,bundle.getLong("Id") );
                bundle1.putString("tenkhachsan" , bundle.getString("Tensk") );
                bundle1.putString("dckhachsan" , bundle.getString("Diachi") );
                bundle1.putLong("gia_khachSan" , bundle.getLong("Gia") );
                bundle1.putString("nhanphong", nhanphong );
                bundle1.putString("traphong", traphong);
                bundle1.putString("sophong", sophong );
                bundle1.putLong("songay", songay);

                intent2.putExtra("order" , bundle1);
                startActivity(intent2);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        LatLng sydney = new LatLng(Double.parseDouble(bundle.getString("Kinhdo")),Double.parseDouble(bundle.getString("Vido")));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14));

        map.addMarker(new MarkerOptions()
                .title(bundle.getString("Tensk"))
                .snippet(bundle.getString("Diachi"))
                .position(sydney));
    }
}
