package com.ben.secret.net;

import android.os.AsyncTask;
import android.util.Log;

import com.ben.secret.Config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Ben on 2015/6/17. NetConnection
 */
public class NetConnection {

    public NetConnection(final String url, final HttpMethod method, final SuccessCallback successCallback,final FailCallback failCallback, final String ... kvs){


        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... params) {

                //配置上传参数对
                StringBuffer paramsStr = new StringBuffer();
                for (int i = 0; i < kvs.length; i+=2){
                    paramsStr.append(kvs[i]).append("=").append(kvs[i+1]).append("&");
                }


                try {
                    URLConnection uc;

                    switch (method){
                        case POST:    //向服务器输出信息。 流的形式，写进
                            uc = new URL(url).openConnection();
                            uc.setDoOutput(true);
                            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(uc.getOutputStream(), Config.CHARSET));
                            bw.write(paramsStr.toString());
                            break;
                        default:     //添加到url内发出去
                            uc = new URL(url + "?" + paramsStr.toString()).openConnection();
                            break;
                    }

                    Log.d("Request url:", uc.getURL().toString());
                    Log.d("Request data", paramsStr.toString());

                    BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream(),Config.CHARSET));
                    String line = null;
                    StringBuffer result = new StringBuffer();
                    while ((line = br.readLine()) != null){
                        result.append(line);
                    }

                    Log.d("Result:", result.toString());

                    return result.toString();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }


            @Override
            protected void onPostExecute(String result) {

                if (result != null){
                    if (successCallback != null){
                        successCallback.onSucess(result);
                    }
                }else {
                    if (failCallback != null){
                        failCallback.onFail();
                    }
                }
                super.onPostExecute(result);
            }
        };
    }

    public static interface SuccessCallback{
        void onSucess(String result);
    }

    public static interface FailCallback{
        void onFail();
    }

}
