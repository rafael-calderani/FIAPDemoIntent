package com.rm39951.demointent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

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
        }
    }
}
