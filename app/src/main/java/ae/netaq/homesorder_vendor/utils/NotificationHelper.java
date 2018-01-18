package ae.netaq.homesorder_vendor.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;

/**
 * Created by sabih on 17-Jan-18.
 */

public class NotificationHelper {

    public static void showProgressNotification(Context context,
                                                boolean showProgress,
                                                long productID){
        final Notification.Builder mBuilder;

        if(showProgress){
            final NotificationManager mNotifyManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder = new Notification.Builder(context);
            mBuilder.setContentTitle("Product Update")
                    .setContentText("Updating Product")
                    .setSmallIcon(android.R.drawable.stat_notify_sync)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                            android.R.drawable.stat_notify_sync))
                    .setOngoing(true)
                    .setOnlyAlertOnce(true);


            mBuilder.setProgress(0, 0, true);
            // Issues the notification
            mNotifyManager.notify((int) productID, mBuilder.build());
        }else{
            final NotificationManager mNotifyManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder = new Notification.Builder(context);
            mBuilder.setContentTitle("Product Update")
                    .setContentText("Updated Product Successfully")
                    .setSmallIcon(android.R.drawable.stat_sys_upload_done);

            // Issues the notification
            mNotifyManager.notify((int) productID, mBuilder.build());
        }

    }
}

