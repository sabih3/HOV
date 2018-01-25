package ae.netaq.homesorder_vendor.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;

import ae.netaq.homesorder_vendor.network.services.ProductsSyncService;

/**
 * Created by sabih on 17-Jan-18.
 */

public class NotificationHelper {

    public static void showProgressNotification(Context context,
                                                boolean showProgress,
                                                long productID,
                                                String title,
                                                String desc){
        final Notification.Builder mBuilder;

        if(showProgress){
            final NotificationManager mNotifyManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder = new Notification.Builder(context);
            mBuilder.setContentTitle(title)
                    .setContentText(desc)
                    .setSmallIcon(android.R.drawable.ic_popup_sync)
                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                            android.R.drawable.ic_popup_sync))
                    .setOngoing(true)
                    .setOnlyAlertOnce(true);


            mBuilder.setProgress(0, 0, true);
            // Issues the notification
            mNotifyManager.notify((int) productID, mBuilder.build());
        }else{
            final NotificationManager mNotifyManager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            mBuilder = new Notification.Builder(context);
            mBuilder.setContentTitle(title)
                    .setContentText(desc)
                    .setSmallIcon(android.R.drawable.stat_sys_upload_done);

            // Issues the notification
            mNotifyManager.notify((int) productID, mBuilder.build());
        }

    }

    public static void showExceptionNotification(Context context,int notificationID,
                                                 String title,String desc){
        Notification.Builder mBuilder;
        mBuilder = new Notification.Builder(context);
        NotificationManager mNotifyManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new Notification.Builder(context);
        mBuilder.setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(android.R.drawable.stat_notify_error);

        // Issues the notification
        mNotifyManager.notify((int) notificationID, mBuilder.build());
    }

    public static void dismissProgressNotification(Context context, int notificationID) {
        NotificationManager mNotifyManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotifyManager.cancel(notificationID);
    }
}

