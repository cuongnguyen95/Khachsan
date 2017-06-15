package linhd13.khachsan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import linhd13.khachsan.Adapter.HotelAdapter;
import linhd13.khachsan.Model.KhachSan;


public class ListHotelActivity extends AppCompatActivity {
    private ListView lvKhachSan;
    private TextView khuvuc;
    private Button tkchitiet;
    private ArrayList<KhachSan> listKhachSan;
    private HotelAdapter hotelAdapter;
    private String nhanphong, traphong, sophong, diachi , khuvuc1 , sao, tu, den , url;
    private Long songay;
    private Bundle bundle1 ;
    private Intent intent2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hotel);

        lvKhachSan = (ListView) findViewById(R.id.listHotel);
        tkchitiet = (Button) findViewById(R.id.tkchitiet);
        khuvuc = (TextView) findViewById(R.id.khuvuc);
        listKhachSan = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(this);


        intent2 = getIntent();

        if (intent2.getBundleExtra("timkiem") != null) {
            bundle1 = intent2.getBundleExtra("timkiem");
            khuvuc1 = bundle1.getString("khuvuc") ;
            diachi = bundle1.getString("diachi");
            nhanphong = bundle1.getString("nhanphong");
            traphong = bundle1.getString("traphong");
            sophong = bundle1.getString("sophong");
            songay = bundle1.getLong("songay");

            khuvuc.setText("Khu vực : " + khuvuc1 );
            url = "http://webservicelinh.somee.com/api/khachsan?sao=0&tu=1&den=2000000&diachi=" + diachi;
        }
        if (intent2.getBundleExtra("timkiemchitiet") != null) {
            bundle1 = intent2.getBundleExtra("timkiemchitiet");
            khuvuc1 = bundle1.getString("khuvuc") ;
            diachi = bundle1.getString("diachi");
            nhanphong = bundle1.getString("nhanphong");
            traphong = bundle1.getString("traphong");
            sophong = bundle1.getString("sophong");
            songay = bundle1.getLong("songay");
            sao = bundle1.getString("sosao");
            tu = bundle1.getString("giatu");
            den = bundle1.getString("giaden");

            khuvuc.setText("Khu vực : " + khuvuc1 );
            url = "http://webservicelinh.somee.com/api/khachsan?sao=" + sao + "&tu=" + tu + "&den=" + den + "&diachi=" + diachi;

        }

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ShowJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListHotelActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);


        tkchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInsertAuthorDialog();
            }
        });


    }

    public void ShowJson(JSONArray response) {
        if (response.length() == 0) {
            Toast.makeText(ListHotelActivity.this, "Không có khách sạn nào được tìm thấy !", Toast.LENGTH_LONG).show();
        }
        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject khachsanObject = response.getJSONObject(i);
                KhachSan khachSan = new KhachSan();
                khachSan.setId(khachsanObject.getLong("Id"));
                khachSan.setMatinh(khachsanObject.getString("Matinh"));
                khachSan.setMahuyen(khachsanObject.getString("Mahuyen"));
                khachSan.setTen(khachsanObject.getString("Ten"));
                khachSan.setSao(khachsanObject.getLong("Sao"));
                khachSan.setGia(khachsanObject.getLong("Gia"));
                khachSan.setDiachi(khachsanObject.getString("Diachi"));
                khachSan.setSophong(khachsanObject.getLong("Sophong"));
                khachSan.setSophongcon(khachsanObject.getLong("Sophongcon"));
                khachSan.setSdt(khachsanObject.getString("Sdt"));
                khachSan.setAnh(khachsanObject.getString("Anh"));
                khachSan.setnAnh(khachsanObject.getString("nAnh"));
                khachSan.setKinhdo(khachsanObject.getString("Kinhdo"));
                khachSan.setVido(khachsanObject.getString("Vido"));

                listKhachSan.add(khachSan);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        hotelAdapter = new HotelAdapter(ListHotelActivity.this, R.layout.listview_row, listKhachSan);
        hotelAdapter.notifyDataSetChanged();
        lvKhachSan.setAdapter(hotelAdapter);

        lvKhachSan.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(ListHotelActivity.this, DetailHotelActivity.class);
                        Bundle bundle = new Bundle();

                        bundle.putLong("Id", listKhachSan.get(position).getId());
                        bundle.putString("Matinh", listKhachSan.get(position).getMatinh());
                        bundle.putString("Mahuyen", listKhachSan.get(position).getMahuyen());
                        bundle.putString("Tensk", listKhachSan.get(position).getTen());
                        bundle.putLong("Sao", listKhachSan.get(position).getSao());
                        bundle.putLong("Gia", listKhachSan.get(position).getGia());
                        bundle.putString("Diachi", listKhachSan.get(position).getDiachi());
                        bundle.putLong("Sophong", listKhachSan.get(position).getSophong());
                        bundle.putLong("Sophongcon", listKhachSan.get(position).getSophongcon());
                        bundle.putString("Sdt", listKhachSan.get(position).getSdt());
                        bundle.putString("Anh", listKhachSan.get(position).getAnh());
                        bundle.putString("nAnh", listKhachSan.get(position).getnAnh());
                        bundle.putString("Kinhdo", listKhachSan.get(position).getKinhdo());
                        bundle.putString("Vido", listKhachSan.get(position).getVido());
                        bundle.putString("nhanphong", nhanphong);
                        bundle.putString("traphong", traphong);
                        bundle.putString("sophong", sophong);
                        bundle.putLong("songay", songay);


                        intent.putExtra("dulieu", bundle);
                        startActivity(intent);

                    }
                }
        );
    }

    public void showInsertAuthorDialog() {
        Intent intent1 = new Intent(ListHotelActivity.this, SeachActivity.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("diachi", diachi );
        bundle2.putString("khuvuc", khuvuc1 );
        bundle2.putString("nhanphong", nhanphong);
        bundle2.putString("traphong", traphong);
        bundle2.putString("sophong", sophong);
        bundle2.putLong("songay", songay);
        intent1.putExtra("timkiem", bundle2);
        startActivity(intent1);
    }

}
