package com.example.eider.imagen_corporativa.firebase;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import  android.support.v4.app.NotificationCompat;
import  android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;

import com.example.eider.imagen_corporativa.MainActivity;
import com.example.eider.imagen_corporativa.R;
import com.example.eider.imagen_corporativa.lista_mascota;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    public static final String TAG = "Firebase";
    public static final  int  NOTIFICATION_ID= 001;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());


        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            lanzarNotificacion( remoteMessage.getNotification().getBody());
        }
    }


    public void lanzarNotificacion(String mensaje) {
        /*Intent intent = new Intent();
        intent.setAction("TOQUE_ANIMAL");
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
  // TODO: 18/02/2018 aqui es donde modificas la accion para un werable
        NotificationCompat.Action action= new NotificationCompat.Action.
                Builder(R.drawable.ic_full_pokes,getString(R.string.texto_accion_toque),pendingIntent).build();
        */
        
        Intent intent1 = new Intent();
        intent1.setAction("VER-MI-PERFIL");

        Intent intentFollow = new Intent();
        intentFollow.setAction("DAR-FOLLOW-UNFOLLOW");

        Intent intentVerUsuario = new Intent();
        intentVerUsuario.setAction("VER-USUARIO");

        PendingIntent pendingIntentVerMiPerfil = PendingIntent.getBroadcast(this, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentFollow = PendingIntent.getBroadcast(this, 2, intentFollow, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentVerUsuario = PendingIntent.getBroadcast(this, 3, intentVerUsuario, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action2= new NotificationCompat.Action.
                Builder(R.drawable.home,"ver mi perfil",pendingIntentVerMiPerfil).build();

        NotificationCompat.Action actionfollow= new NotificationCompat.Action.
                Builder(R.drawable.back,"follow/unfollow",pendingIntentFollow).build();

        NotificationCompat.Action actionVerUsuario= new NotificationCompat.Action.
                Builder(R.drawable.avatar,"ver usuario",pendingIntentVerUsuario).build();


// TODO: 18/02/2018 se supone que aqui estan caracteristicas que vuelven a la notificacion en el dispoitivo werable mas visuazlmente atractivo
        NotificationCompat.WearableExtender wearableExtender  = new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(),R.drawable.hex_background))
                .setGravity(Gravity.CENTER_VERTICAL);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Notificacion")
                .setContentText(mensaje)
                .setSound(sonido)
                .setContentIntent(pendingIntentVerMiPerfil)
                .setAutoCancel(true)
                .extend(wearableExtender
                        .addAction(action2)
                        .addAction(actionfollow)
                        .addAction(actionVerUsuario));
                //.addAction(action)
                ;


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificationCompat.build());


    }

}