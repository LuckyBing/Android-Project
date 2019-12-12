package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Mess> list=new ArrayList<>();//Listview显示的内容
    private MyDatabaseHelper dbHealper;//数据库
    private List<String> popupMenuItemList = new ArrayList<>();//存放长按后显示的内容
    private float mRawX;
    private float mRawY;
    MessAdapter adapter;//适配器
    ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popupMenuItemList.add(getString(R.string.copy));
        popupMenuItemList.add(getString(R.string.delete));
        popupMenuItemList.add(getString(R.string.share));
        popupMenuItemList.add(getString(R.string.more));
        initShow();
        listview=findViewById(R.id.listView);
        final MessAdapter adapter=new MessAdapter(MainActivity.this,R.layout.mess_layout,list);//布局绑定
        listview.setAdapter(adapter);
        listview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {//获取点击位置
                mRawX = event.getRawX();
                mRawY = event.getRawY();
                return false;
            }
        });
        SearchView msearch=findViewById(R.id.searchView);//搜索框
        msearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {//搜索框内容事件绑定
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(MainActivity.this,"null",Toast.LENGTH_LONG).show();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {//搜索框内容改变事件
                list.clear();//在内容改变时清空list
                SQLiteDatabase db=dbHealper.getWritableDatabase();
                Cursor cursor=db.rawQuery("select * from contact where name like ?",new String[]{'%'+newText+'%'});//从数据库中筛选符合条件的记录
                if(cursor.moveToFirst())
                {
                    do{
                        String first=cursor.getString(cursor.getColumnIndex("name"));
                        String second=cursor.getString(cursor.getColumnIndex("phonenumber"));
                        Mess mess=new Mess(R.drawable.one,first,second);
                        list.add(mess);//将记录中的数据存入list显示
                    }
                    while(cursor.moveToNext());
                }
                adapter.notifyDataSetChanged();//刷新界面
                return true;
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {//点击事件
                Mess mess1x=list.get(i);//获取点击对象
                Intent intent1x=new Intent(MainActivity.this,CkActivity.class);
                SQLiteDatabase db=dbHealper.getReadableDatabase();

                Cursor cursor1x=db.rawQuery("select * from contact where name=?",new String[]{mess1x.getName()});//从数据库中获取该条记录
                cursor1x.moveToFirst();
                String first1x=cursor1x.getString(cursor1x.getColumnIndex("name"));
                String second1x = cursor1x.getString(cursor1x.getColumnIndex("phonenumber"));
                String third1x=cursor1x.getString(cursor1x.getColumnIndex("address"));
                String fourth1x=cursor1x.getString(cursor1x.getColumnIndex("work"));
                intent1x.putExtra("name1x", first1x);
                intent1x.putExtra("number1x", second1x);
                intent1x.putExtra("address1x", third1x);
                intent1x.putExtra("work1x", fourth1x);
                intent1x.putExtra("position",String.valueOf(i));//显示点击的信息写入intent1x传入子活动

                startActivityForResult(intent1x,2);//打开查看信息界面，并绑定请求码
            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {//长按事件
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                PopupList popupList = new PopupList(view.getContext());
                popupList.showPopupListWindow(view, position, mRawX, mRawY, popupMenuItemList, new PopupList.PopupListListener() {
                    @Override
                    public boolean showPopupList(View adapterView, View contextView, int contextPosition) {
                        return true;
                    }


                    @Override
                    public void onPopupListClick(View contextView, int contextPosition, int position) {
                        switch (popupMenuItemList.get(position))//点击各事件后的响应
                        {
                            case "复制":
                                Toast.makeText(contextView.getContext(), list.get(contextPosition).getName() + "," + popupMenuItemList.get(position), Toast.LENGTH_SHORT).show();
                                break;
                            case "删除":
                                SQLiteDatabase db=dbHealper.getReadableDatabase();
                                String data=list.get(contextPosition).getName();
                                Toast.makeText(MainActivity.this,data,Toast.LENGTH_SHORT).show();
                                db.delete("contact","name=?",new String[]{data});//删除此条记录
                                Iterator<Mess> it_b=list.iterator();
                                while(it_b.hasNext())
                                {
                                    Mess nextdata=it_b.next();
                                    if(list.get(contextPosition)==nextdata) {
                                        it_b.remove();
                                        break;
                                    }
                                }
                                adapter.notifyDataSetChanged();
                                break;
                            case "拨号":
                                        Intent intent_call = new Intent(Intent.ACTION_DIAL);
                                        intent_call.setData(Uri.parse("tel:" + list.get(contextPosition).getNumber()));
                                        startActivity(intent_call);
                                        break;
                            case "发送信息":
                                        Intent intent_me=new Intent(Intent.ACTION_SENDTO);
                                        intent_me.setData(Uri.parse("smsto:" + list.get(contextPosition).getNumber()));
                                        startActivity(intent_me);
                                        break;
                             default:
                                 Toast.makeText(contextView.getContext(), list.get(contextPosition).getName() + "," + popupMenuItemList.get(position)+"sdv", Toast.LENGTH_SHORT).show();
                                 break;
                        }


                    }
                });
                return true;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);//绑定悬浮按钮
        fab.setOnClickListener(new View.OnClickListener() {//悬浮按钮点击事件
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,1);//打开添加信息界面，并绑定请求码
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)//接受其他界面传回来的数据并处理
    {
        switch (requestCode)
        {
            case 1://添加数据
                if(resultCode==RESULT_OK)
                {
                    SQLiteDatabase db=dbHealper.getReadableDatabase();
                    ContentValues values=new ContentValues();
                    String _name= data.getStringExtra("name");
                    String _pho= data.getStringExtra("number");
                    String _addr= data.getStringExtra("address");
                    String _work= data.getStringExtra("work");
                    Toast.makeText(MainActivity.this,_name,Toast.LENGTH_SHORT).show();
                    values.put("name",_name);
                    values.put("phonenumber",_pho);
                    values.put("address",_addr);
                    values.put("work",_work);
                    db.insert("contact",null,values);
                    list.clear();
                    final Cursor cursor=db.rawQuery("select * from contact order by name",null);//查询后将数据库数据存入cursor
                    if(cursor.moveToFirst())//一条一条记录的读
                    {
                        do{
                            String first=cursor.getString(cursor.getColumnIndex("name"));
                            String second=cursor.getString(cursor.getColumnIndex("phonenumber"));
                            Mess mess=new Mess(R.drawable.one,first,second);
                            list.add(mess);
                        }
                        while(cursor.moveToNext());
                    }
                    values.clear();
                }
                break;
            case 2://修改数据
                if(resultCode==RESULT_OK)
                {
                    SQLiteDatabase db=dbHealper.getReadableDatabase();
                    ContentValues valuesx=new ContentValues();
                    String first=data.getStringExtra("_namex");
                    String second=data.getStringExtra("_numberx");
                    String third=data.getStringExtra("_addressx");
                    String fourth=data.getStringExtra("_workx");
                    String positionx=data.getStringExtra("position");
                    Toast.makeText(MainActivity.this,first,Toast.LENGTH_SHORT).show();
                    valuesx.put("name",first);
                    valuesx.put("phonenumber",second);
                    valuesx.put("address",third);
                    valuesx.put("work",fourth);
                    db.update("contact",valuesx,"name=?",new String[]{list.get(Integer.valueOf(positionx)).getName()});
                    Log.d("MainActivity",positionx);
                    Mess mess=new Mess(R.drawable.one,first,second);
                    list.set((Integer.valueOf(positionx)),mess);
                }
                break;
                default:
                    break;
        }
    }

private void initShow()
{
    list.clear();
    dbHealper=new MyDatabaseHelper(this,"BOOK",null,1);
    SQLiteDatabase db=dbHealper.getWritableDatabase();
    final Cursor cursor=db.rawQuery("select * from contact order by name",null);//查询后将数据库数据存入cursor
    if(cursor.moveToFirst())//一条一条记录的读
    {
        do{
            String first=cursor.getString(cursor.getColumnIndex("name"));
            String second=cursor.getString(cursor.getColumnIndex("phonenumber"));
            Mess mess=new Mess(R.drawable.one,first,second);
            list.add(mess);
        }
        while(cursor.moveToNext());
    }
}



}
