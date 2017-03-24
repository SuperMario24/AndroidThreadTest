package com.example.saber.androidthreadtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnChangeText;
    private TextView tvText;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    tvText.setText("Nice to meet you");
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChangeText = (Button) findViewById(R.id.btn_change_test);
        tvText = (TextView) findViewById(R.id.tv_text);

        btnChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = Message.obtain();
                        handler.sendEmptyMessage(1);
                    }
                }).start();
            }
        });

    }

    /**
     * 完整的AsyncTask
     */
    class DownloadTask extends AsyncTask<Void,Integer,Boolean>{


        //OnPreExecute方法在后台任务开始之前调用
        @Override
        protected void onPreExecute() {
            //progressDialog.show()//显示进度对话框
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try{
//                while(true){
//                    int downloadPercent = doDownload();
//
//                    可向UIxianc线程反馈当前进度，类似于发消息
//                    publishProgress(downloadPercent);
//                    if(downloadPercent >=100){
//                        break;
//                    }
//
//                }

            }catch (Exception e){
                return false;
            }
            return true;
        }

        /**
         * 更新进度
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            //在这里更新下载进度
            //progressDialog.setMessage("Downloaded"+values[0]+"%");
        }

        /**
         * 后台任务结束后在这里更新主线程UI
         * @param aBoolean
         */
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                Toast.makeText(MainActivity.this, "Download Succeeded", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Download Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }





}
