package com.example.app_thongtincanhan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddpersonAvtivity extends Activity {
    TextView edtAddName, edtAddAddress, edtAddphone;
    Button btnAdd;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_person);
        initview();
    }
 
    private void initview() {
        edtAddName = (EditText) findViewById(R.id.edtAddname);
        edtAddAddress = (TextView) findViewById(R.id.edtaddAddress);
        edtAddphone = (TextView) findViewById(R.id.edtAddPhone);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person person = new person(0,R.drawable.miule, edtAddName.getText().toString(),
                        edtAddAddress.getText().toString(), edtAddphone.getText().toString());
                Intent intent = new Intent(AddpersonAvtivity.this, MainActivity.class);
                intent.putExtra("ADD", person);
                setResult(100, intent);
                finish();
            }
        });
    }


}
