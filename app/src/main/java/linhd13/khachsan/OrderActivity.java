package linhd13.khachsan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class OrderActivity extends AppCompatActivity {
    private EditText edtName, edtSdt ;
    private Button btnSubmitForm;
    private Bundle bundle;
    private String  id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        edtName = (EditText) findViewById(R.id.name);
        edtSdt = (EditText) findViewById(R.id.sdt);
        btnSubmitForm = (Button) findViewById(R.id.submitForm);

        setOnFocusChangeListener(edtName);
        setOnFocusChangeListener(edtSdt);
        setOnClick(btnSubmitForm);

    }

    private void setOnFocusChangeListener(View v) {
        v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(final View v, boolean hasFocus) {
                if (hasFocus) {
                    v.setBackgroundResource(R.drawable.select);
                } else {
                    v.setBackgroundResource(0);
                }
            }
        });
    }

    private void setOnClick(View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean failFlag = false;
                if (edtName.getText().toString().trim().length() == 0) {
                    failFlag = true;
                    edtName.setBackgroundResource(R.drawable.error);
                } else {
                    failFlag = false;
                    edtName.setBackgroundResource(0);
                }

                if (edtSdt.getText().toString().trim().length() == 0) {
                    failFlag = true;
                    edtSdt.setBackgroundResource(R.drawable.error);
                } else {
                    failFlag = false;
                    edtSdt.setBackgroundResource(0);
                }

                if (failFlag == false) {
                    Intent intent2 = getIntent();
                    bundle = intent2.getBundleExtra("order");
                    String name = edtName.getText().toString();
                    name = name.replace(" ", "-");
                    String ngayvao = bundle.getString("nhanphong");
                    String ngayra = bundle.getString("traphong");
                    final String sophong = bundle.getString("sophong");
                    final Long songay = bundle.getLong("songay");

                    RequestQueue requestQueue1 = Volley.newRequestQueue(OrderActivity.this);

                    String url = "http://www.hostlinh386.somee.com/api/datphong?idks=" + bundle.getLong("Id_khachSan") + "&sdt=" + edtSdt.getText().toString() + "&ngayvao=" + ngayvao + "&ngayra=" + ngayra + "&sophong=" + sophong + "&tenkh=" + name;
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equals("\"success\"")) {
                                Intent intentSuccess = new Intent(OrderActivity.this, SuccessActivity.class);
                                Bundle bundleSuccess = new Bundle();
                                bundleSuccess.putLong("id_khachsan", bundle.getLong("Id_khachSan"));
                                bundleSuccess.putString("tenkhachsan", bundle.getString("tenkhachsan"));
                                bundleSuccess.putString("diachi", bundle.getString("dckhachsan"));
                                bundleSuccess.putString("name", edtName.getText().toString());
                                bundleSuccess.putString("sdt", edtSdt.getText().toString());
                                bundleSuccess.putString("sophong", sophong );
                                bundleSuccess.putLong("gia", bundle.getLong("gia_khachSan"));
                                bundleSuccess.putLong("songay", songay);
                                bundleSuccess.putLong("tongsotien", bundle.getLong("gia_khachSan") * songay * Long.parseLong(sophong));

                                intentSuccess.putExtra("thongtin", bundleSuccess);
                                startActivity(intentSuccess);

                            } else {
                                Toast.makeText(OrderActivity.this, "them that bai", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(OrderActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    });
                    requestQueue1.add(stringRequest);
                }

            }
        });
    }
}
