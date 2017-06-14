package com.example.app_thongtincanhan;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity implements AdapterView.OnItemLongClickListener {
    personAdapter adapter;
    ArrayList<person> listData;
    ListView lvData;
    ImageView imgAddPerson;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        initView();
    }
	private void initView() {
        adapter = new personAdapter(listData, MainActivity.this);
        lvData = (ListView) findViewById(R.id.lvData);
        lvData.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        imgAddPerson = (ImageView) findViewById(R.id.imgAddPerson);
        imgAddPerson.setOnClickListener(Add_Click);
        lvData.setOnItemLongClickListener(this);
    }
 
    View.OnClickListener Add_Click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent inte = new Intent(MainActivity.this, AddpersonAvtivity.class);
            startActivityForResult(inte, 10);
        }
    };

    private void getData() {
        listData = new ArrayList<person>();
        listData.add(new person(0, R.drawable.baothy, "Bảo Thy", "TP.Hồ Chí Minh", "0905425005"));
        listData.add(new person(1, R.drawable.hoquanghieu, "Hồ Quang Hiếu", "Hà nội", "0971256789"));
        listData.add(new person(2, R.drawable.minhhang, "Minh Hằng", "TP.Hồ Chí Minh", "01234546889"));
        listData.add(new person(3, R.drawable.iu, " IU ", "Hàn Quốc", "00100200123"));
        listData.add(new person(4, R.drawable.sontung, " Sơn Tùng ", "Thái Bình", "016491372"));
    }
 
    // onActivityRessult là hàm nhận giá trị truyền về từ các Activity khác thông qua request Code và ResultCode
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 10: // Kiểm tra nếu requestCode = 10 thì sẽ chạy hàm dưới
                if (data != null) {
                    // Lấy ra giá trị truyền về từ AddpersonActivity và gán vào đối tượng person
                    person person = (person) data.getSerializableExtra("ADD");
                    if (resultCode == 100) {
                        // gán thuộc tính code trong Person bằng số lượng của adapter + 1
                        person.setCode(adapter.getCount() + 1);
                        adapter.getListData().add(0, person);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(MainActivity.this, "Có lỗi sảy ra, vui lòng thử lại", Toast.LENGTH_SHORT).show();
                break;
            case 20:
                if (data != null) {
                    // Lấy ra dữ liệu được truyền về từ EditActivity
                    person select = (person) data.getSerializableExtra("EDIT");
                    if (resultCode == 200) {
                        for (person item : adapter.getListData()) {
                            if (item.getCode() == select.getCode()) {
                                item.setName(select.getName());
                                item.setAddress(select.getAddress());
                                item.setPhone(select.getPhone());
                                break;
                            }
                        }
                        adapter.notifyDataSetChanged();
                        break;
                    } else if (resultCode == 300) {
                        int ID = data.getExtras().getInt("DEL");
                        int position = -1;
                        for (int i = 0; i < adapter.getCount(); i++) {
                            if (adapter.getItem(i).getCode() == ID) {
                                position = i;
                                break;
                            }
                        }
                        if (position < 0) {
                            Toast.makeText(MainActivity.this, "Không xác định được vị trí", Toast.LENGTH_SHORT).show();
                        } else {
                            adapter.getListData().remove(position);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        }
    }
 
// onItemLongClick là sự kiện khi nhấn giữ vào một item listview
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        intent.putExtra("EDIT", adapter.getItem(position));
        startActivityForResult(intent, 20);
        return true;
    }
}
