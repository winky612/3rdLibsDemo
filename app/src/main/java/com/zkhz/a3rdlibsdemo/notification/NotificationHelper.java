package com.zkhz.a3rdlibsdemo.notification;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.zkhz.a3rdlibsdemo.R;

import static android.support.v4.app.NotificationCompat.VISIBILITY_PUBLIC;

public class NotificationHelper extends ContextWrapper {


    private static final String PRIMARY_CHANNEL = "default";
    private static final String SECONDARY_CHANNEL = "second";
    public static final String ACTION_SNOOZE = "action snooze";
    public static final String EXTRA_NOTIFICATION_ID = "id";
    public static final String MESSAGE_STYLE = "message style";
    public static final String REPLAY = "replay";

    private static final String KEY_TEXT_REPLY = "key_text_reply";


    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);


    }

    /**
     * Notification 对象必须包含:小图标  标题   详细文本
     *
     * 设置常规 Activity PendingIntent
     * 您要启动的 Activity 是应用的正常工作流的一部分。在这种情况下，请设置 PendingIntent 以启动全新任务并为 PendingIntent提供返回栈，这将重现应用的正常“返回”行为
     *  例如，如果您在 Gmail 中撰写消息时点击了一封电子邮件的通知，则会立即转到该电子邮件。 触摸“返回”会依次转到收件箱和主屏幕，而不是转到您在撰写的邮件
     */
    public NotificationCompat.Builder getNotifyBuilder(Context context,String title, String body) {

        Intent resultIntent = new Intent(context, ResultActivity1.class);

        //创建堆栈生成器
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        //将返回栈添加到堆栈生成器
        stackBuilder.addParentStack(ResultActivity1.class);
        //添加可从通知中启动 Activity 的 Intent
        stackBuilder.addNextIntent(resultIntent);
        //获得此返回栈的 PendingIntent
        PendingIntent pendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), PRIMARY_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }


    /**
     * 设置特殊 Activity PendingIntent
     * <p>
     * 特殊 Activity 无需返回栈.仅当从通知中启动时，用户才会看到此 Activity。
     * 从某种意义上说，Activity 是通过提供很难显示在通知本身中的信息来扩展通知。
     * 对于这种情况，请将 PendingIntent 设置为在全新任务中启动。
     * 但是，由于启动的 Activity 不是应用 Activity 流程的一部分，因此无需创建返回栈。点击“返回”仍会将用户带到主屏幕
     */
    public NotificationCompat.Builder getNotifyBuilder2(Context context, String title, String body) {

        Intent intent = new Intent(context, ResultActivity2.class);

        //将 Activity 设置为在新的空任务中启动;taskAffinity与FLAG_ACTIVITY_NEW_TASK 标志相结合，这可确保此 Activity 不会进入应用的默认任务。任何具有应用默认关联的现有任务均不受影响
        //有助于在通过通知打开您的应用程序后保留用户的预期导航体验,但是，您是否想要使用这取决于您启动的活动类型，可能是以下某种活动：:
        // ①在正常的应用程序使用过程中，用户无需导航到此活动，因此该活动将启动一项新任务，而不是添加到您的应用程序的现有任务和后退堆栈中。
        // ②活动存在于您应用的常规应用流中。在这种情况下，启动该活动应创建一个后退堆栈，以便保留用户对Back和Up按钮的期望。
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent snoozeIntent = new Intent(context, MyBroadcastReceiver.class);
        snoozeIntent.setAction(ACTION_SNOOZE);
        snoozeIntent.putExtra(EXTRA_NOTIFICATION_ID, 0);
        PendingIntent snoozePending = PendingIntent.getBroadcast(context, 1, snoozeIntent, 0);

        return new NotificationCompat.Builder(getApplicationContext(), SECONDARY_CHANNEL)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setAutoCancel(true)//当用户点击它时会自动删除通知
                .addAction(R.drawable.ic_wb_sunny_black_24dp, //这就像设置通知的默认点按操作一样，除了不是启动活动外，还可以执行各种其他操作，例如启动在后台执行作业的BroadcastReceiver，以便操作不会中断已经打开的应用程序。
                        getString(R.string.add_action),
                        snoozePending)
                .setCategory(NotificationCompat.CATEGORY_ALARM)//Android使用某些预定义的系统范围类别来确定当用户启用“请勿打扰”模式时是否使用给定通知打扰用户。当设备处于免打扰模式时，系统将使用有关您的通知类别的这些信息来做出关于显示通知的决定
                .setVisibility(VISIBILITY_PUBLIC);//控制锁屏状态下通知显示的详细度


    }


    /**
     * 从Android 7.0（API级别24）开始，Android为消息内容专门提供通知样式模板。
     * 使用NotificationCompat.MessagingStyle类，您可以更改通知中显示的多个标签，包括会话标题，其他消息和通知的内容视图。
     */
    public NotificationCompat.Builder getNotifyBuilder3() {

        //????///////////////////////////////
        long timestamp1 = 5;
        long timestamp2 = 3;
        return new NotificationCompat.Builder(getApplicationContext(), MESSAGE_STYLE)
                .setStyle(new NotificationCompat.MessagingStyle("me")
                        .setConversationTitle("ConversationTitle")
                        .addMessage("Hi", timestamp1, null)
                        .addMessage("add2", timestamp2, "Coworker")
                );
    }


    public NotificationCompat.Builder getNotifyBuilder4(Context context,String title,String body) {



        Intent replyIntent = new Intent(context,ResultActivity2.class);
        PendingIntent replyPendingIntent =PendingIntent.getActivity(context,11,replyIntent,PendingIntent.FLAG_UPDATE_CURRENT);


        //1.创建一个可添加到通知操作的 RemoteInput.Builder 实例。 该类的构造函数接受系统用作文本输入密钥的字符串。 之后，手持式设备应用使用该密钥检索输入的文本。
        String lableReplay = getResources().getString(R.string.reply_label);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
            RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY)
                    .setLabel(lableReplay)
                    .build();

            //2.使用 addRemoteInput() 向操作附加 RemoteInput 对象。
            NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.drawable.ic_wb_sunny_black_24dp,
                    getString(R.string.lable), replyPendingIntent)
                    .addRemoteInput(remoteInput)
                    .build();

            //3.对通知应用操作并发出通知。

            return new NotificationCompat.Builder(getApplicationContext(),REPLAY)
                    .setSmallIcon(getSmallIcon())
                    .setContentTitle(title)
                    .setContentText(body)
                    .addAction(action)
                    .build();

        }







    }

    private int getSmallIcon() {
        return android.R.drawable.stat_notify_chat;
    }


    public void notify(int id, NotificationCompat.Builder builder) {

        getManager().notify(id, builder.build());

    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }
}
