package linhd13.khachsan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import linhd13.khachsan.Model.Tinh;

public class MainActivity extends AppCompatActivity {
    Spinner spinnerSophong;
    AutoCompleteTextView autoDiachiks;
    EditText edtNhanphong, edtTraphong;
    Button btnTimkiem;
    long ngayvao1, ngayra1, songay;
    ArrayList<Tinh> listTinh;
    Tinh tinh;
    String matinh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoDiachiks = (AutoCompleteTextView) findViewById(R.id.diachiks);
        edtNhanphong = (EditText) findViewById(R.id.nhanphong);
        edtTraphong = (EditText) findViewById(R.id.traphong);
        spinnerSophong = (Spinner) findViewById(R.id.sophong);
        btnTimkiem = (Button) findViewById(R.id.timkiem);

        listTinh = new ArrayList<>();
        tinh = new Tinh();
        listTinh = tinh.AllTinh();
        ArrayList<String> tentinh = new ArrayList<>();
        for (Tinh t1 : listTinh) {
            tentinh.add(t1.getTentinh());
        }

        autoDiachiks.setThreshold(1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, tentinh);
        autoDiachiks.setAdapter(adapter);


        setOnFocusChangeListener(edtNhanphong);
        setOnFocusChangeListener(edtTraphong);

        OnClick(btnTimkiem);


    }

    private void OnClick(View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean failFlag = false;
                // TODO Auto-generated method stub
                if (autoDiachiks.getText().toString().trim().length() == 0) {
                    failFlag = true;
                    autoDiachiks.setBackgroundResource(R.drawable.error);
                } else {
                    failFlag = false;
                    autoDiachiks.setBackgroundResource(0);
                }

                if (edtNhanphong.getText().toString().trim().length() == 0) {
                    failFlag = true;
                    edtNhanphong.setBackgroundResource(R.drawable.error);
                } else {
                    failFlag = false;
                    edtNhanphong.setBackgroundResource(0);
                }

                if (edtTraphong.getText().toString().trim().length() == 0) {
                    failFlag = true;
                    edtTraphong.setBackgroundResource(R.drawable.error);
                } else {
                    failFlag = false;
                    edtTraphong.setBackgroundResource(0);
                }

                if (failFlag == false) {
                    String a = autoDiachiks.getText().toString();
                    if (!a.isEmpty()) {
                        for (Tinh t1 : listTinh) {
                            if (t1.getTentinh().equals(a)) {
                                matinh = t1.getMatinh();
                                break;
                            }
                        }
                    }

                    Intent intent = new Intent(MainActivity.this, ListHotelActivity.class);
                    Bundle bundle = new Bundle();

                    bundle.putString("khuvuc", a );
                    bundle.putString("diachi", matinh);
                    bundle.putString("nhanphong", edtNhanphong.getText().toString());
                    bundle.putString("traphong", edtTraphong.getText().toString());
                    bundle.putLong("songay", songay);
                    bundle.putString("sophong", String.valueOf(spinnerSophong.getSelectedItem()));
                    intent.putExtra("timkiem", bundle);
                    startActivity(intent);
                }


            }
        });
    }

    private void setOnFocusChangeListener(View v) {
        v.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(final View v, boolean hasFocus) {

                if (hasFocus) {
                    final Calendar calendar = Calendar.getInstance();
                    int ngay = calendar.get(Calendar.DATE);
                    int thang = calendar.get(Calendar.MONTH);
                    int nam = calendar.get(Calendar.YEAR);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            calendar.set(year, month, dayOfMonth);

                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");

                            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMddHHmm");

                            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd/MM/yyyy");

                            if (v.getId() == R.id.nhanphong ) {
                                Timeconversion a = new Timeconversion();
                                ngayvao1 = a.timeConversion(simpleDateFormat1.format(calendar.getTime()));
                                edtNhanphong.setText(simpleDateFormat2.format(calendar.getTime()));
                            } else {
                                Timeconversion a = new Timeconversion();
                                ngayra1 = a.timeConversion(simpleDateFormat1.format(calendar.getTime()));
                                edtTraphong.setText(simpleDateFormat2.format(calendar.getTime()));
                            }
                            songay = (ngayra1 - ngayvao1) / (3600 * 24);
                            Log.d("so ngay ", String.valueOf(songay));

                        }
                    }, nam, thang, ngay);
                    datePickerDialog.show();
                }


            }
        });
    }

    public class Timeconversion {
        DateFormat dfm = new SimpleDateFormat("yyyyMMddHHmm");

        long unixtime;

        public long timeConversion(String time) {
            dfm.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));//Specify your timezone
            try {
                unixtime = dfm.parse(time).getTime();
                unixtime = unixtime / 1000;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return unixtime;
        }

    }
}
