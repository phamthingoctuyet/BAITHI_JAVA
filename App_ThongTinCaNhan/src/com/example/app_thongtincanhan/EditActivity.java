package com.example.app_thongtincanhan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditActivity extends Activity {    
	 ImageView imgEditAvatar;
	 EditText edtEditName, edtEditAddress, edtEditPhone;
	 Button btnSaveEdit, btnExitEdit, btnDel;
	 person person;

	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_edit);
	        // Gọi phương thức ivitView và getData
	        initView();
	        getData();
	    }
	// Khởi tạo initView
	    private void initView() {
	        imgEditAvatar = (ImageView) findViewById(R.id.imgEditAvatar);
	        edtEditName = (EditText) findViewById(R.id.edtEditName);
	        edtEditAddress = (EditText) findViewById(R.id.edtEditAddress);
	        edtEditPhone = (EditText) findViewById(R.id.edtEditPhone);
	        btnExitEdit = (Button) findViewById(R.id.btnExitEdit);
	        btnSaveEdit = (Button) findViewById(R.id.btnEdit);
	        btnDel = (Button) findViewById(R.id.btnDel);
	        btnSaveEdit.setOnClickListener(SaveEdit_Click);
	        btnExitEdit.setOnClickListener(Exit_Edit);
	        btnDel.setOnClickListener(Del_Click);
	    }
	// Khơi tạo getData
	    private void getData() {
	        if (getIntent().getExtras() != null) {
	            person = (person) getIntent().getSerializableExtra("EDIT");
	            int a = person.getCode();
	            imgEditAvatar.setImageResource(person.getAvatar());
	            edtEditName.setText(person.getName());
	            edtEditAddress.setText(person.getAddress());
	            edtEditPhone.setText(person.getPhone());
	        }
	    }
	 
	    View.OnClickListener SaveEdit_Click = new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            Intent intent = new Intent(EditActivity.this, MainActivity.class);
	            int b = person.getCode();
	            person person1 = new person(b, person.getAvatar(),
	                    edtEditName.getText().toString(), edtEditAddress.getText().toString(), edtEditPhone.getText().toString());
	            intent.putExtra("EDIT", person1);
	            setResult(200, intent);
	            finish();
	        }
	    };
	    View.OnClickListener Del_Click = new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            Intent intent = new Intent(EditActivity.this, MainActivity.class);
	            intent.putExtra("DEL", person.getCode());
	            setResult(300, intent);
	            finish();
	        }
	    };
	    View.OnClickListener Exit_Edit = new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	            finish();
	        }
	    };

}
