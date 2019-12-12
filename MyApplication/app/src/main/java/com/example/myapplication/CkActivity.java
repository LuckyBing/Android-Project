package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ck);
        final TextView edit1ext1x = (TextView) findViewById(R.id.editText1x);
        final TextView edit1ext2x = (TextView) findViewById(R.id.editText2x);
        final TextView edit1ext3x = (TextView) findViewById(R.id.editText3x);
        final TextView edit1ext4x = (TextView) findViewById(R.id.editText4x);
        Intent intent2x = getIntent();
        final String first2x = intent2x.getStringExtra("name1x");
        final String second2x = intent2x.getStringExtra("number1x");
        String third2x = intent2x.getStringExtra("address1x");
        String fourth2x = intent2x.getStringExtra("work1x");
        final String position = intent2x.getStringExtra("position");
        edit1ext1x.setText(first2x);
        edit1ext2x.setText(second2x);
        edit1ext3x.setText(third2x);
        edit1ext4x.setText(fourth2x);
        ImageButton imagebtton1x = (ImageButton) findViewById(R.id.imageButton1x);
        Button button1x = (Button) findViewById(R.id.button1x2);
        Button call = (Button) findViewById(R.id.call);
        Button messa = (Button) findViewById(R.id.message);
        imagebtton1x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                setResult(RESULT_CANCELED);
                finish();
            }
        });
        button1x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//编辑事件
                Intent intent = new Intent(CkActivity.this, MoActivity.class);
                intent.putExtra("namex", edit1ext1x.getText().toString());
                intent.putExtra("numberx", edit1ext2x.getText().toString());
                intent.putExtra("addressx", edit1ext3x.getText().toString());
                intent.putExtra("workx", edit1ext4x.getText().toString());
                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + second2x));
                startActivity(intent);
            }
        });
        messa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:"+second2x));
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)//获取编辑后的信息
    {
        switch(requestCode)
        {
            case 1:
                if(resultCode==RESULT_OK)
                {
                    String first2x=data.getStringExtra("_namex");
                    String second2x=data.getStringExtra("_numberx");
                    //String third2x=data.getStringExtra("_addressx");
                    //String fourth2x=data.getStringExtra("_workx");

                    if(!first2x.equals("") && ! second2x.equals(""))
                        setResult(RESULT_OK,data);
                    finish();
                }
        }
    }

}
