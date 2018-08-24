package com.wzlab.smartcity.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.skateboard.zxinglib.CaptureActivity;
import com.wzlab.smartcity.R;
import com.wzlab.smartcity.activity.account.Config;
import com.wzlab.smartcity.net.HttpMethod;
import com.wzlab.smartcity.net.NetConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HandleAlarmActivity extends AppCompatActivity {
    private Spinner spinner;
    private Button mBtnsubmit;
    private ImageView mIvpicture;
    private EditText mEtid,mEtdetail;
    private String deviceInfo,cardNumber;
    TextView text;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle_alarm);
        phone = Config.getCachedPhone(getApplicationContext());
        text=findViewById(R.id.tv_http);
//        findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SubmitURL(Config.SERVER_URL+Config.ACTION_UPLOAD_REPAIR_RESULT);
//            }
//        });

        String[] ctype = new String[]{"请选择故障类型", "摄像头损坏", "APP出现BUG", "网络问题"};
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ctype);  //创建一个数组适配器
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);     //设置下拉列表框的下拉选项样式
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cardNumber = spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mEtid = findViewById(R.id.et_id);

        mEtdetail=findViewById(R.id.et_detail);
        mBtnsubmit = findViewById(R.id.btn_submit);
        mBtnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String deviceId = mEtid.getText().toString().trim();
                String detail = mEtdetail.getText().toString().trim();


                uploadRepairResult("phone",phone,"device_id",deviceId,"result_description",detail);
                Toast.makeText(HandleAlarmActivity.this,"提交成功"+ deviceId+cardNumber +detail,Toast.LENGTH_SHORT).show();
            }
        });

        mIvpicture = findViewById(R.id.iv_picture);
        mIvpicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HandleAlarmActivity.this,CaptureActivity.class);
                startActivityForResult(intent,1001);


            }

        });

    }

    private void uploadRepairResult(String ... kv) {
        new NetConnection(Config.SERVER_URL + Config.ACTION_UPLOAD_REPAIR_RESULT, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(result);

                    if(jsonObject.getString("status").equals("1")){
                        String msg = jsonObject.getString("msg");
                        Toast.makeText(getApplicationContext(), msg ,Toast.LENGTH_SHORT).show();
                    }else{

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                Toast.makeText(getApplicationContext(),"未能连接服务器",Toast.LENGTH_SHORT).show();
            }
        },kv);
    }

//    public void SubmitURL(String url){
//        new AsyncTask<String,Float,String>(){
//
//            @Override
//            protected String doInBackground(String... strings) {
//                String phone="18392888103";
//                String device_id="";
//                String result_description="";
//                try {
//                    URL url = new URL(strings[0]);
//                    URLConnection connection=url.openConnection();
//                    long total = connection.getContentLength();
//                    InputStream inputStream = connection.getInputStream();
//                    InputStreamReader isr = new InputStreamReader(inputStream);
//                    BufferedReader br = new BufferedReader(isr);
//                    String line;
//                    StringBuilder builder = new StringBuilder();
//                    while((line=br.readLine())!=null){
//                        builder.append(line);
//                        publishProgress((float)builder.toString().length()/total);
//                    }
//                    br.close();
//                    inputStream.close();
//                    return builder.toString();
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            protected void onCancelled() {
//                super.onCancelled();
//            }
//
//            @Override
//            protected void onCancelled(String s) {
//                super.onCancelled(s);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                text.setText(s);
//                super.onPostExecute(s);
//            }
//
//            @Override
//            protected void onPreExecute() {
//                Toast.makeText(HandleAlarmActivity.this,"开始读取",Toast.LENGTH_SHORT).show();
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onProgressUpdate(Float... values) {
//                System.err.println(values[0]);
//                super.onProgressUpdate(values);
//            }
//        }.execute(url);
//    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1001 && resultCode== Activity.RESULT_OK)
        {
            deviceInfo = data.getStringExtra(CaptureActivity.KEY_DATA);
            mEtid.setText(deviceInfo.split("#")[0]);
            Toast.makeText(HandleAlarmActivity.this, deviceInfo, Toast.LENGTH_SHORT).show();
        }
    }
}


