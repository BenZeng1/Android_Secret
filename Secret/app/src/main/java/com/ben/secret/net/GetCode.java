package com.ben.secret.net;

import com.ben.secret.Config;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ben on 2015/6/17.
 */
public class GetCode {

    public GetCode(String Phone, final SucessCallback sucessCallback, final FailCallback failCallback) {

        new NetConnection(Config.SERVER_URL, HttpMethod.POST, new NetConnection.SuccessCallback() {
            @Override
            public void onSucess(String result) {

                try {
                    JSONObject jsonObj = new JSONObject(result);
                    switch (jsonObj.getInt(Config.KEY_STATUS)) {
                        case Config.RESULT_STATUS_SUCCESS:
                            if (sucessCallback != null) {
                                sucessCallback.onSucess();
                            }
                            break;
                        default:
                            if (failCallback != null) {
                                failCallback.onFail();
                            }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (failCallback != null) {
                        failCallback.onFail();
                    }
                }
            }
        }, new NetConnection.FailCallback() {
            @Override
            public void onFail() {
                if (failCallback != null) {
                    failCallback.onFail();
                }
            }
        }, Config.KEY_ACTION, Config.ACTION_GET_CODE, Config.KEY_PHONE_NUM, Phone);

    }


    public static interface SucessCallback {
        void onSucess();
    }

    public static interface FailCallback {
        void onFail();
    }


}
