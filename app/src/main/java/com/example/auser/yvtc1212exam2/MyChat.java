package com.example.auser.yvtc1212exam2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.auser.yvtc1212exam2.firebase.ChatRoom;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyChat extends AppCompatActivity {
    EditText editText;
    ListView listView;
    ArrayAdapter<String> adapter;
    FirebaseDatabase database;
    DatabaseReference myRef;
    public static String fromID="tenant001";
    String toID="lanlord001";
    String fromName="陳大";
    String toName="邱小美";
    String userType="tenant";
    TextView tvmsg;
    CheckBox chkbox;
    String title;
    ArrayList<String> myList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_chat);

        editText=(EditText)findViewById(R.id.editText);
        listView=(ListView)findViewById(R.id.listView1);
        adapter=new ArrayAdapter<String>(this,R.layout.simple_list_item_1,R.id.text1);
        listView.setAdapter(adapter);


        View root;
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        root = inflater.inflate(R.layout.simple_list_item_1, null);//找出根源樹,
        tvmsg = root.findViewById(R.id.text1);
//
//        tvmsg.setGravity(Gravity.RIGHT);
//        database = FirebaseDatabase.getInstance();
//        myRef = database.getReference("ChartRoom");


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");
//
//        myRef.setValue("Hello, World!");
    }



    public void btnSubmit(View v){
        // Write a message to the database

        //目前時間
        Date date = new Date();
//設定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//進行轉換
        String dateString = sdf.format(date);
//        System.out.println(dateString);

        ChatRoom chatRoom=new ChatRoom(fromName,dateString,editText.getText().toString());
        myRef.push().setValue(chatRoom);
        editText.setText("");
//        myRef.setValue(editText.getText().toString());
//        myRef.setValue("Hello, World!");
//        adapter.add(editText.getText().toString());
    }
}
