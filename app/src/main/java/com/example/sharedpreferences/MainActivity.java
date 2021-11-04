package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //SharedPreferencesのkeyを定数化する
    private final String KAGI = "key";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //"DataStore"という名前でインスタンスを生成
        SharedPreferences dataStore = getSharedPreferences("DateStore", MODE_PRIVATE);
        //ボタンとテキストビューの取得
        TextView tv_box = findViewById(R.id.tv_box);
        Button btn_true = findViewById(R.id.btn_true);
        Button btn_false = findViewById(R.id.btn_false);
        Button btn_confirm = findViewById(R.id.btn_confirm);

         btn_true.setOnClickListener(new View.OnClickListener() {
             @SuppressLint("SetTextI18n")
             @Override
             public void onClick(View v) {
                 //trueを箱に入れる
                 tv_box.setText("TRUE");
                 //datastoreに保存
                 SharedPreferences.Editor editor_true = dataStore.edit();
                 editor_true.putBoolean(KAGI,true); //keyを何度も書かないように、書きミスをしないように　文字定数として定義し直す
                 editor_true.apply();

             }
         });
         btn_false.setOnClickListener(new View.OnClickListener() {
             @SuppressLint("SetTextI18n")
             @Override
             public void onClick(View v) {
                 //falseを箱に入れる
                 tv_box.setText("FALSE");
                 //datastoreに保存
                 SharedPreferences.Editor editor_false = dataStore.edit();
                 editor_false.putBoolean(KAGI,false);
                 editor_false.apply();
             }
         });


        boolean trueorfalse = dataStore.getBoolean(KAGI,false);
        if(!trueorfalse){

        } else {
            Intent intent = new Intent(MainActivity.this, TrueActivity.class);
            startActivity(intent);
            finish();

        //noHistry:ユーザーが他のactivityに移動して、画面に表示されなくなったactivityをactivityスタックから削除して終了する必要があるかどうか
        //trueだったらactivityの履歴を残さないため、ユーザーはそこに戻ることができなくなる
        //ただマニフェストに書いてしまうと確定させてしまうので、例えば一回は再度起動のとき消えてほしいけれど、ある特定の場合はもう一度表示させたいという場合はプログラム上でfinish()を使って終わらせる
        //仕様によってそこはケースバイケースで判断する
        }
    }
}