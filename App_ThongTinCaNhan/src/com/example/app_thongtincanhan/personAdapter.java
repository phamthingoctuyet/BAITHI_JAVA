package com.example.app_thongtincanhan;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class personAdapter extends BaseAdapter {
    ArrayList<person> listData;
    LayoutInflater inflater;
    ImageView imgAvatar;
    TextView txtName, txtAddress, txtPhone;
 
    public personAdapter(ArrayList<person> listData, Context context) {
        this.listData = listData;
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
 
    public ArrayList<person> getListData() {
        return listData;
    }
 
    public void setListData(ArrayList<person> listData) {
        this.listData = listData;
    }
 
    @Override
    public int getCount() {
        return listData.size();
    }
 
    @Override
    public person getItem(int position) {
        return listData.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_listview, null);
 
        imgAvatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
        txtName = (TextView) convertView.findViewById(R.id.txtName);
        txtAddress = (TextView) convertView.findViewById(R.id.txtAddress);
        txtPhone = (TextView) convertView.findViewById(R.id.txtPhone);
// gán dữ liệu vào từng đối tượng trong item_listview.xml
 
        person ps = getItem(position);
        imgAvatar.setImageResource(ps.getAvatar());
        txtName.setText(ps.getName().toString());
        txtAddress.setText(ps.getAddress().toString());
        txtPhone.setText(ps.getPhone().toString());
        return convertView;
    }
}
