package com.rm39951.demointent;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by logonrm on 07/06/2017.
 */

public class MainBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context ctx, Intent i) {
        switch (i.getAction()) {
            case Intent.ACTION_BATTERY_LOW:
                break;
            case Intent.ACTION_DEVICE_STORAGE_LOW:
                break;
            case Intent.ACTION_LOCALE_CHANGED: //Language changed
                break;
            case Intent.ACTION_INSERT_OR_EDIT:
                break;
        }

        final Bundle bundle = i.getExtras();

        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int j = 0; j < pdusObj.length; j++) {

                    SmsMessage currentMessage;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        String format = i.getStringExtra("format");
                        currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[j], format);
                    } else {
                        currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[j]);
                    }

                    String numeroTelefone = currentMessage.getDisplayOriginatingAddress();

                    String mensagem = currentMessage.getDisplayMessageBody();
                    Log.i("SmsReceiver", "senderNum: "+ numeroTelefone + "; message: " + mensagem);

                    Intent i2= new Intent("android.intent.action.SMSRECEBIDO")
                            .putExtra("remetente", numeroTelefone)
                            .putExtra("mensagem", mensagem);
                    ctx.sendBroadcast(i2);

                    showNotification(ctx, numeroTelefone, mensagem);
                }
            }
        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);
        }
    }

    private void showNotification(Context context, String numeroTelefone, String mensagem) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Mensagem de: " + numeroTelefone);
        mBuilder.setContentText(mensagem);

        Intent resultIntent = new Intent(context, SMSActivity.class);

        resultIntent
                .putExtra("remetente", numeroTelefone)
                .putExtra("mensagem", mensagem);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(SMSActivity.class);


        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }
}
