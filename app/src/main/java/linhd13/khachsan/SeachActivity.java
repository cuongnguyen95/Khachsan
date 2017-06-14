package linhd13.khachsan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SeachActivity extends AppCompatActivity {
    EditText giatu , giaden  ;
    Spinner sosao ;
    Button timkiem ;
    String diachi , khuvuc , nhanphong , traphong , sophong ;
    Long songay ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        giatu = (EditText)findViewById(R.id.giatu);
        giaden = (EditText)findViewById(R.id.giaden);
        sosao = (Spinner)findViewById(R.id.sosao);
        timkiem = (Button)findViewById(R.id.timkiem);

        Intent intent1 = getIntent();
        Bundle bundle1 = intent1.getBundleExtra("timkiem");

         diachi = bundle1.getString("diachi");
         khuvuc = bundle1.getString("khuvuc");
         nhanphong = bundle1.getString("nhanphong");
         traphong = bundle1.getString("traphong");
         sophong = bundle1.getString("sophong");
         songay = bundle1.getLong("songay");

        timkiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean failFlag = false;
                if (giatu.getText().toString().trim().length() == 0) {
                    failFlag = true;
                    giatu.setBackgroundResource(R.drawable.error);
                } else {
                    failFlag = false;
                    giatu.setBackgroundResource(0);
                }

                if (giaden.getText().toString().trim().length() == 0) {
                    failFlag = true;
                    giaden.setBackgroundResource(R.drawable.error);
                } else {
                    failFlag = false;
                    giaden.setBackgroundResource(0);
                }
                if (failFlag == false) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);

                    Intent intent3 = new Intent(SeachActivity.this , ListHotelActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("diachi", diachi );
                    bundle.putString("khuvuc", khuvuc );
                    bundle.putString("nhanphong", nhanphong );
                    bundle.putString("traphong", traphong);
                    bundle.putString("sophong", sophong );
                    bundle.putLong("songay", songay);
                    bundle.putString("giatu", giatu.getText().toString() );
                    bundle.putString("giaden", giaden.getText().toString());
                    bundle.putString("sosao", String.valueOf(sosao.getSelectedItem()));
                    intent3.putExtra("timkiemchitiet", bundle);
                    startActivity(intent3);
                }
            }
        });
    }
}
