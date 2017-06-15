package linhd13.khachsan.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import linhd13.khachsan.Model.KhachSan;
import linhd13.khachsan.R;

import static linhd13.khachsan.R.id.imgSao;
import static linhd13.khachsan.R.id.txtDiaChi;
import static linhd13.khachsan.R.id.txtGia;
import static linhd13.khachsan.R.id.txtPhong;
import static linhd13.khachsan.R.id.txtSdt;
import static linhd13.khachsan.R.id.txtTen;

/**
 * Created by cuong pc on 6/11/2017.
 */

public class HotelAdapter extends ArrayAdapter<KhachSan> {
    Context context;
    List<KhachSan> items;
    int resource;

    public HotelAdapter(Context context, int resource, List<KhachSan> items) {
        super(context, resource, items);
        this.context = context;
        this.resource = resource;
        this.items = items;
    }

    private class ViewHolder{
        private ImageView imgHinh , imgSao ;
        private TextView txtTen , txtDiaChi , txtSdt , txtGia , txtPhong ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.listview_row, null);
            viewHolder = new ViewHolder();
            // Anh xa
            viewHolder.imgHinh = (ImageView) view.findViewById(R.id.imgHinh);
            viewHolder.txtTen = (TextView) view.findViewById(txtTen);
            viewHolder.txtDiaChi = (TextView) view.findViewById(txtDiaChi);
            viewHolder.txtSdt = (TextView) view.findViewById(txtSdt);
            viewHolder.txtGia = (TextView) view.findViewById(txtGia);
            viewHolder.txtPhong = (TextView) view.findViewById(txtPhong);
            viewHolder.imgSao = (ImageView) view.findViewById(imgSao);
            view.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) view.getTag();
        }


        // Gan gia tri
        Picasso.with(context).load(items.get(position).getAnh()).into(viewHolder.imgHinh);
        viewHolder.txtTen.setText(items.get(position).getTen());
        viewHolder.txtDiaChi.setText(items.get(position).getDiachi());
        viewHolder.txtSdt.setText(items.get(position).getSdt());

        String price = String.valueOf(items.get(position).getGia());
        double amount = Double.parseDouble(price);
        viewHolder.txtGia.setText(String.format("%,.0f", amount) + "(VNĐ)");

        switch (items.get(position).getSao() + "") {
            case "1":
                viewHolder.imgSao.setImageResource(R.drawable.mot_sao);
                break;
            case "2":
                viewHolder.imgSao.setImageResource(R.drawable.hai_sao);
                break;
            case "3":
                viewHolder.imgSao.setImageResource(R.drawable.ba_sao);
                break;
            case "4":
                viewHolder.imgSao.setImageResource(R.drawable.bon_sao);
                break;
            case "5":
                viewHolder.imgSao.setImageResource(R.drawable.nam_sao);
                break;
        }

        if (items.get(position).getSophongcon() > 0){
            viewHolder.txtPhong.setText("Còn phòng");
        }
        else{
            viewHolder.txtPhong.setText("Hết phòng");
        }


        return view;
    }


}
