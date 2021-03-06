package com.leancloud.im.guide;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMMessageManager;

/**
 * Created by zhangxiaobo on 15/4/15.
 */
public class Application extends android.app.Application {
  public static final String KEY_CLIENT_ID = "client_id";
  static SharedPreferences preferences;

  @Override
  public void onCreate() {
    super.onCreate();
    AVOSCloud.setDebugLogEnabled(true);

    // 这是使用美国节点的 app 信息，如果不使用美国节点，请 comment 这两行
//    AVOSCloud.useAVCloudUS();
//    AVOSCloud.initialize(this, "l8j5lm8c9f9d2l90213i00wsdhhljbrwrn6g0apptblu7l90",
//            "b3uyj9cmk84s5t9n6z1rqs9pvf2azofgacy9bfigmiehhheg");

    // 这是使用中国节点的 app 信息，如果使用中国节点，请 uncomment 这两行
    // 这是用于 SimpleChat 的 app id 和 app key，如果更改将不能进入 demo 中相应的聊天室
    AVOSCloud.initialize(this, "9p6hyhh60av3ukkni3i9z53q1l8yy3cijj6sie3cewft18vm",
        "nhqqc1x7r7r89kp8pggrme57i374h3vyd0ukr2z3ayojpvf4");
    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    AVIMMessageManager.registerDefaultMessageHandler(new CustomMessageHandler());
  }


  public static String getClientIdFromPre() {
    return preferences.getString(KEY_CLIENT_ID, "");
  }

  public static void setClientIdToPre(String id) {
    preferences.edit().putString(KEY_CLIENT_ID, id).apply();
  }

  public static AVIMClient getIMClient(){
    return AVIMClient.getInstance(getClientIdFromPre());
  }

}
