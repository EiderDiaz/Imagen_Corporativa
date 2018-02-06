package com.example.eider.imagen_corporativa.firebase;


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class NotificationIDTokenService extends FirebaseInstanceIdService {
        public static final String TAG = "FIREBASE_TOKEN";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
    }

    private void enviarTokenRegistro(String token) {
        Log.d(TAG,token);
    }
}