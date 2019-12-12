package com.example.caculation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.math.*;
import java.util.*;

import java.lang.String;
public class MainActivity extends AppCompatActivity {
    String ss="";//保存输入的表达式
    String ou="";
    EditText edit;//实例化输入框
    EditText edit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridlayout);
        Button bt0=(Button) findViewById(R.id.button0);//获取布局文件中定义的按钮并实例化
        Button bt1=(Button) findViewById(R.id.button1);
        Button bt2=(Button) findViewById(R.id.button2);
        Button bt3=(Button) findViewById(R.id.button3);
        Button bt4=(Button) findViewById(R.id.button4);
        Button bt5=(Button) findViewById(R.id.button5);
        Button bt6=(Button) findViewById(R.id.button6);
        Button bt7=(Button) findViewById(R.id.button7);
        Button bt8=(Button) findViewById(R.id.button8);
        Button bt9=(Button) findViewById(R.id.button9);
        Button bt_log=(Button) findViewById(R.id.button_log);
        Button bt_ln=(Button) findViewById(R.id.button_ln);
        Button bt_x2=(Button) findViewById(R.id.button_X2);
        Button bt_sqart=(Button) findViewById(R.id.button_sq);
        Button bt_clear=(Button) findViewById(R.id.button_C);
        Button bt_di=(Button) findViewById(R.id.button_divide);
        Button bt_mul=(Button) findViewById(R.id.button_multiply);
        Button bt_ba=(Button) findViewById(R.id.button_back);
        Button bt_sub=(Button) findViewById(R.id.button_sub);
        Button bt_add=(Button) findViewById(R.id.button_add);
        Button bt_eq=(Button) findViewById(R.id.button_equal);
        Button bt_rem=(Button) findViewById(R.id.button_rem);
        Button bt_po=(Button) findViewById(R.id.button_point);
        Button bt_sin=(Button) findViewById(R.id.button_sin);
        Button bt_cos=(Button) findViewById(R.id.button_cos);
        Button bt_tan=(Button) findViewById(R.id.button_tan);
        Button bt_left=(Button) findViewById(R.id.button_left);
        Button bt_right=(Button) findViewById(R.id.button_right) ;
        edit=(EditText) findViewById(R.id.text_view);//获取布局文件中的输入框
        edit1=(EditText) findViewById(R.id.text_view1);
        bt0.setOnClickListener(new Click());//注册侦听器
        bt1.setOnClickListener(new Click());
        bt2.setOnClickListener(new Click());
        bt3.setOnClickListener(new Click());
        bt4.setOnClickListener(new Click());
        bt5.setOnClickListener(new Click());
        bt6.setOnClickListener(new Click());
        bt7.setOnClickListener(new Click());
        bt8.setOnClickListener(new Click());
        bt9.setOnClickListener(new Click());
        bt_add.setOnClickListener(new Click());
        bt_ba.setOnClickListener(new Click());
        bt_clear.setOnClickListener(new Click());
        bt_di.setOnClickListener(new Click());
        bt_eq.setOnClickListener(new Click());
        bt_ln.setOnClickListener(new Click());
        bt_log.setOnClickListener(new Click());
        bt_mul.setOnClickListener(new Click());
        bt_po.setOnClickListener(new Click());
        bt_rem.setOnClickListener(new Click());
        bt_sqart.setOnClickListener(new Click());
        bt_sub.setOnClickListener(new Click());
        bt_x2.setOnClickListener(new Click());
        bt_sin.setOnClickListener(new Click());
        bt_cos.setOnClickListener(new Click());
        bt_tan.setOnClickListener(new Click());
        bt_left.setOnClickListener(new Click());
        bt_right.setOnClickListener(new Click());
    }
    class Click implements View.OnClickListener {
        public void onClick(View v) {//点击事件，更改表达式ss并显示
            switch(v.getId()) {//通过getID()得到布局文件中元素的ID
                case R.id.button0: {//数字0
                    ss += "0";
                    edit.setText(ss);//显示语句

                }
                break;
                case R.id.button1: {//数字1
                    ss += "1";
                    edit.setText(ss);
                }
                break;
                case R.id.button2: {//数字2
                    ss += "2";
                    edit.setText(ss);
                }
                break;
                case R.id.button3: {//数字3
                    ss += "3";
                    edit.setText(ss);
                }
                break;
                case R.id.button4: {//数字4
                    ss += "4";
                    edit.setText(ss);
                }
                break;
                case R.id.button5: {//数字5
                    ss += "5";
                    edit.setText(ss);
                }
                break;
                case R.id.button6: {//数字6
                    ss += "6";
                    edit.setText(ss);
                }
                break;
                case R.id.button7: {//数字7
                    ss += "7";
                    edit.setText(ss);
                }
                break;
                case R.id.button8: {//数字8
                    ss += "8";
                    edit.setText(ss);
                }
                break;
                case R.id.button9: {//数字9
                    ss += "9";
                    edit.setText(ss);
                }
                break;
                case R.id.button_sin: {//三角函数sin
                    try{
                    ou=String.valueOf(Math.sin(Math.PI*Double.valueOf(ss)/180));//将输入的数字转化为度数并计算sin值
                    edit1.setText(ou);}
                    catch(Exception e)//抛出异常
                    {
                        edit1.setText("NaN");
                        ss="";
                    }
                }
                break;
                case R.id.button_cos: {//三角函数cos
                    try{
                    ou=String.valueOf(Math.cos(Math.PI*Double.valueOf(ss)/180));
                    edit1.setText(ou);}
                    catch(Exception e)
                    {
                        edit1.setText("NaN");
                        ss="";
                    }
                }
                break;
                case R.id.button_tan: {//三角函数tan
                    try{
                    ou=String.valueOf(Math.tan(Math.PI*Double.valueOf(ss)/180));
                    edit1.setText(ou);}
                    catch(Exception e)
                    {
                        edit1.setText("NaN");
                        ss="";
                    }
                }
                break;
                case R.id.button_left: {//前括号
                    ss+="(";
                    edit.setText(ss);
                }
                break;
                case R.id.button_right://后括号
                {
                    ss+=")";
                    edit.setText(ss);
                }
                break;
                case R.id.button_add: {//加法操作
                    ss += "+";
                    edit.setText(ss);
                }
                break;
                case R.id.button_back: {//将表达式最后一个元素删除
                    try{
                    ss=ss.substring(0,ss.length()-1);
                    edit.setText(ss);
                    edit1.setText("");

                    }
                    catch(Exception e)
                    {
                        edit.setText("");
                        ss="";
                    }
                }
                break;
                case R.id.button_C: {//清空ss并显示
                    ss = "";
                    edit.setText(ss);
                    edit1.setText(ss);
                }
                break;
                case R.id.button_divide: {//除号
                    ss += "/";
                    edit.setText(ss);
                }
                break;
                case R.id.button_equal: {//等于，将表达书计算出并显示
                    try{
                    double result=compete(ss);//调用函数进行计算，数据为double型
                    ou=String.valueOf(result);//将double转为String
                    edit1.setText(ou);}//显示结果
                    catch(Exception e)
                    {
                        ss="";
                        edit1.setText("NaN");
                    }

                }
                break;
                case R.id.button_ln: {//ln函数计算
                    try{
                    ou=String.valueOf(Math.log(Double.valueOf(ss.toString())));//调用
                    edit1.setText(ou);}
                    catch(Exception e)
                    {
                        edit1.setText("NaN");
                        ss="";
                    }
                }
                break;
                case R.id.button_log: {//log函数
                    try{
                    ou=String.valueOf(Math.log10(Double.valueOf(ss.toString())));
                    edit1.setText(ou);}
                    catch(Exception e)
                    {
                        edit1.setText("NaN");
                        ss="";
                    }
                }
                break;
                case R.id.button_multiply: {//乘号
                    ss+="*";
                    edit.setText(ss);
                }
                break;
                case R.id.button_point: {//小数点
                    ss+=".";
                    edit.setText(ss);
                }
                break;
                case R.id.button_rem: {//取余操作
                    ss+="%";
                    edit.setText(ss);
                }
                break;
                case R.id.button_sq: {//开根号
                    try{
                    ou=String.valueOf(Math.sqrt(Double.valueOf(ss.toString())));
                    edit1.setText(ou);}
                    catch(Exception e)
                    {
                        edit1.setText("NaN");
                        ss="";
                    }
                }
                break;
                case R.id.button_sub: {//减号
                    ss+="-";
                    edit.setText(ss);
                }
                break;
                case R.id.button_X2: {//平方
                    try{
                    ou=String.valueOf(Math.pow(Double.valueOf(ss.toString()),2));
                    edit1.setText(ou);}
                    catch(Exception e)
                    {
                        edit1.setText("NaN");
                        ss="";
                    }
                }
                break;



            }
        }
        public double compete(String express)
        {

            Stack<String> stack=new Stack();//定义栈stack，用于存放计算结果
            Stack<String> stack1=new Stack();//定义栈stack1，用与存放符号
            Queue<String> queue = new LinkedList<String>();//队列，用于存放后缀表达式
            Map<String,String> map=new HashMap();//定义哈希表，用于比较计算符号的优先级
            /*
            符号对应的优先级
             */
            map.put("+","1");
            map.put("-","1");
            map.put("%","2");
            map.put("*","2");
            map.put("/","2");
            map.put("(","3");
            double result=0,a,b,res=0;
            char head;
            String sym,top;
            String digit="";//用于提取表达式中的数
            while(!express.isEmpty())//表达式不为空则循环
            {
                head=express.charAt(0);//取表达式第一个元素（char型）
                sym=express.substring(0,1);//取表达式第一个元素（string型）
                if(Character.isDigit(head)||head=='.')//判断该元素是否为数字或小数点
                {
                    digit+=head;//是就将数字存入digit
                }
                else
                {
                    if(!digit.equals("")) {
                        Log.d("jkdshjds", digit);
                        queue.offer(digit);//不是就将此数字压入队列中
                    }
                    digit="";//清空digit
                    if(stack1.empty())//stack1为空
                    {
                        stack1.push(sym);
                    }//压入stack1
                    else
                    {
                        /*
                        如果当前符号为“）”则将stack1中的符号压出
                        知道第一次遇到“（”，并将此“(”从栈中删除
                         */
                        if(sym.equals(")"))//判断当前符号是否为“）”
                        {
                            top=stack1.peek();
                            while(!top.equals("("))
                            {
                                Log.d("jkdshjds",top);
                                queue.offer(top);//将运算后的数压入stack
                                stack1.pop();//弹出stack1中栈顶元素
                                top=stack1.peek();
                            }
                            stack1.pop();
                        }
                        /*
                        如果不是“）”，则按照正常优先级判断进行压栈
                        不过当栈顶符号为“（”时不进行弹出操作
                        例如：stack1中已存“-+*（”，那么A下一个符号为“*”时不将
                        “*（”弹出
                         */
                        else {

                            top = stack1.peek();//取出stack1栈顶元素

                            if (Integer.valueOf(map.get(top)) < Integer.valueOf(map.get(sym)))//判断符号优先级
                            {
                                stack1.push(sym);//当前符号优先级大于栈顶元素，则压入栈中

                            }
                            else {
                                while (Integer.valueOf(map.get(top)) >= Integer.valueOf(map.get(sym)) && (!stack1.empty())&&!top.equals("("))
                                //如果当前符号优先级小于等于栈顶元素且栈顶元素不为“（”时，则将栈顶元素弹出
                                {
                                    //Log.d("jkdshjds",top);
                                    queue.offer(top);//将符号加入队列
                                    stack1.pop();//弹出stack1中栈顶元素
                                    if (!stack1.empty())//stack1非空则将此时栈顶元素赋值给top
                                        top = stack1.peek();
                                }
                                stack1.push(sym);//将当前符号压入stack1
                            }
                        }

                    }

                }
                express=express.substring(1,express.length());//删除表达式中第一个元素
            }
            if(!digit.equals(""))//判断digit是否还存有数据
                queue.offer(digit);//压入最后一位数
            while(!stack1.empty())//将stack1中的符号一个一个弹出
            {

                top=stack1.peek();//弹出符号
                Log.d("jkdshjds",top);
                queue.offer(top);//压入queue
                stack1.pop();//删除栈顶元素

            }
            /*
            进行后缀运算
             */
            while(!queue.isEmpty())
            {
                if(!queue.peek().equals("+")&&!queue.peek().equals("-")&&!queue.peek().equals("*")&&!queue.peek().equals("/")&&!queue.peek().equals("%"))
                {
                    Log.d("MainActivity!!!!",queue.peek());
                    stack.push(queue.poll());//stack中存放数据

                }
                else
                {
                    top=queue.poll();
                    Log.d("Maina",top);
                    b=Double.valueOf(stack.pop());//弹出数据
                    a=Double.valueOf(stack.pop());//弹出数据
                    //进行运算
                    if(top.charAt(0)=='+')
                      res=a+b;
                    if(top.charAt(0)=='-')
                      res=a-b;
                    if(top.charAt(0)=='*')
                      res=a*b;
                    if(top.charAt(0)=='/')
                      res=a/b;
                    if(top.charAt(0)=='%')
                      res=a%b;
                    stack.push(String.valueOf(res));//压入数据
                }
            }
            return Double.valueOf(stack.peek());//返回stack中的栈顶元素，即最后的运算结果
        }


    }
}
