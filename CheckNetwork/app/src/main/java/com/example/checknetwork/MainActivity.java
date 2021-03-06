package com.example.checknetwork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click(View view){
        ConnectivityManager connectivityManager =
                (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (null != networkInfo) {
            //行動數據
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "有網路可用(行動數據)", Toast.LENGTH_SHORT).show();
                //WIFI
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "有網路可用(WIFI)", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "無網路可用", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("目前沒有網路");
            builder.setMessage("是否前往設定?");
            builder.setCancelable(true);
            builder.setNeutralButton("不用設定", null);//關閉訊息
            builder.setPositiveButton("設定", new DialogInterface.OnClickListener() {
                @Override
                //前往設定>WIFI設定頁面
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent();
                    intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");//開啟WIFI設定
                    startActivity(intent);
                }
            });
            builder.show();//顯示訊息
        }
    }
}