package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.util.Log;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final EditText _name=findViewById(R.id.editText1);
        final EditText _phonu=findViewById(R.id.editText2);
        final EditText _addr=findViewById(R.id.editText3);
        final EditText _work=findViewById(R.id.editText4);
        ImageButton _add=findViewById(R.id.imageButton2);
        ImageButton _back=findViewById(R.id.imageButton1);
        _add.setOnClickListener(new View.OnClickListener() {//提交事件
            @Override
            public void onClick(View view) {
            Intent intent=new Intent();
            String a=_name.getText().toString();
            intent.putExtra("name",a);
            String b=_phonu.getText().toString();
            intent.putExtra("number",b);
            String c=_addr.getText().toString();
            Log.d("AddActivity",c);
            intent.putExtra("address",c);
            String d=_work.getText().toString();
            intent.putExtra("work",d);
            if(!a.equals("") && !b.equals(""))//姓名和电话为空时不反回数据
                setResult(RESULT_OK,intent);
            finish();//结束该界面，返回上一层
            }
        });
        _back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//返回按钮
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
