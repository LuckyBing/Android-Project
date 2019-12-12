package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mo);
        final Intent intent=getIntent();
        final EditText editext1=(EditText)findViewById(R.id._editText1);
        final EditText editext2=(EditText)findViewById(R.id._editText2);
        final EditText editext3=(EditText)findViewById(R.id._editText3);
        final EditText editext4=(EditText)findViewById(R.id._editText4);
        editext1.setText(intent.getStringExtra("namex"));
        editext2.setText(intent.getStringExtra("numberx"));
        editext3.setText(intent.getStringExtra("addressx"));
        editext4.setText(intent.getStringExtra("workx"));
        ImageButton imageButton1=(ImageButton)findViewById(R.id._imageButton1);
        ImageButton imageButton2=(ImageButton)findViewById(R.id._imageButton2);
        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });//返回上一层
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//修改内容后将数据传入上一层
                Intent intent1=new Intent();
                intent1.putExtra("_namex",editext1.getText().toString());
                intent1.putExtra("_numberx",editext2.getText().toString());
                intent1.putExtra("_addressx",editext3.getText().toString());
                intent1.putExtra("_workx",editext4.getText().toString());
                intent1.putExtra("position",intent.getStringExtra("position"));
                setResult(RESULT_OK,intent1);
                finish();
            }
        });
    }
}
