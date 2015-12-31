package pl.lenarczyk.rafal.pendingexam;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        final PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        final PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wakeLock.acquire();

        final Intent notificationIntent = new Intent(context, ExamInfo.class);
        runNotification(context, notificationIntent);
        wakeLock.release();
    }

    private void runNotification(Context context, Intent notificationIntent) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_msg))
                .setAutoCancel(true);
        final TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(ExamInfo.class);
        stackBuilder.addNextIntent(notificationIntent);
        final PendingIntent pi = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pi);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    public void set(final Context context) {
        final Intent intent = new Intent(context, Alarm.class);
        final PendingIntent alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        final AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//        am.setInexactRepeating(AlarmManager.RTC_WAKEUP, getAlarmTime().getTimeInMillis(), AlarmManager.INTERVAL_DAY, alarmIntent);
//        am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, AlarmManager.INTERVAL_FIFTEEN_MINUTES, AlarmManager.INTERVAL_FIFTEEN_MINUTES, alarmIntent);
        am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 60 * 1000, 60 * 1000, alarmIntent);
    }

    public void cancel(final Context context) {
        final Intent intent = new Intent(context, Alarm.class);
        final PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        final AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(sender);
    }

    private static Calendar getAlarmTime() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 11);
        return calendar;
    }


}
