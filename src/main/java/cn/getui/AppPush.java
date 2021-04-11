package cn.getui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.StartActivityTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.gexin.rp.sdk.template.style.Style6;

public class AppPush {

    // STEP1：获取应用基本信息
    private static String appId = "dlNJqZbnkw87ukTzvQshL6";
    private static String appKey = "UOMmHWsTAF64OXHpltiFl2";
    private static String masterSecret =  "PZYfFnsfPk7n3WhEgfy1q6";
    // 如果需要使用HTTPS，直接修改url即可
    //private static String url = "https://api.getui.com/apiex.htm";
    private static String url = "http://api.getui.com/apiex.htm";

    public static void main(String[] args) throws IOException {

        IGtPush push = new IGtPush(url, appKey, masterSecret);
        String title="biaoti1";
        String content="昨夜西风凋碧树";

//        NotificationTemplate notificationTemplateDemo = notificationTemplateDemo("biaoti","{\"1\":'2'}");

//		StartActivityTemplate demo = startActivityTemplateDemo(title, content);
		LinkTemplate demo = linkTemplateDemo(title, content);
        // STEP5：定义"AppMessage"类型消息对象,设置推送消息有效期等推送参数
        List<String> appIds = new ArrayList<String>();
        appIds.add(appId);
        AppMessage message = new AppMessage();
        message.setData(demo);
        message.setAppIdList(appIds);
        message.setOffline(true);
        message.setOfflineExpireTime(1000 * 600);  // 时间单位为毫秒

        // STEP6：执行推送
        IPushResult ret = push.pushMessageToApp(message);
        System.out.println(ret.getResponse().toString());
    }
    
    
    /**
     * 打开首页通知模板
     * @return
     */
    public static NotificationTemplate notificationTemplateDemo(String title,String content) {
        NotificationTemplate template = new NotificationTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionType(1);
        template.setTransmissionContent("请输入您要透传的内容");

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        style.setChannel("自定义channel");
        style.setChannelName("自定义channelName");
        style.setChannelLevel(3);
        template.setStyle(style);
        //template.setAPNInfo(getAPNPayload()); //详见本页iOS通知样式设置

        // 设置定时展示时间，安卓机型可用
        // template.setDuration("2019-08-16 11:40:00", "2019-08-16 12:24:00");

        // 消息覆盖
        // template.setNotifyid(123); // 在消息推送的时候设置自定义的notifyid。如果需要覆盖此条消息，则下次使用相同的notifyid发一条新的消息。客户端sdk会根据notifyid进行覆盖。
        return template;
    }
    
    
    /**
     * 通知消息打开包内页面
     * @param title
     * @param content
     * @return
     */
    public static StartActivityTemplate startActivityTemplateDemo(String title,String content) {
        StartActivityTemplate template = new StartActivityTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        style.setChannel("自定义channel");
        style.setChannelName("自定义channelName");
        style.setChannelLevel(3);
        template.setStyle(style);

        String intent = "intent:#Intent;component=com.getui.demo/.NewsActivity;end";
        template.setIntent(intent); //最大长度限制为1000
        template.setNotifyid(123); // 在消息推送的时候设置notifyid。如果需要覆盖此条消息，则下次使用                                                
        // 设置定时展示时间，安卓机型可用
        // template.setDuration("2019-08-16 11:40:00", "2019-08-16 12:24:00");

        // 消息覆盖
        // template.setNotifyid(123); // 在消息推送的时候设置自定义的notifyid。如果需要覆盖此条消息，则下次使用相同的notifyid发一条新的消息。客户端sdk会根据notifyid进行覆盖。

        //template.setAPNInfo(getAPNPayload()); //详见本页iOS通知样式设置
        return template;
    }
    
    
    /**
     * 通知消息打开浏览器网页
     * @param title
     * @param content
     * @return
     */
    public static LinkTemplate linkTemplateDemo(String title,String content) {
        LinkTemplate template = new LinkTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);

//        Style0 style = new Style0();
        Style6 style = new Style6();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
//        style.setBigStyle1("/attached/image/20201223/20201223164126_217.jpg");
        style.setBigStyle2("0：无声音，无震动，不显示。(不推荐)\r\n" + 
        		"1：无声音，无震动，锁屏不显示，通知栏中被折叠显示，导航栏无logo。\r\n" + 
        		"2：无声音，无震动，锁屏和通知栏中都显示，通知不唤醒屏幕。\r\n" + 
        		"3：有声音，有震动，锁屏和通知栏中都显示，通知唤醒屏幕。（推荐）\r\n" + 
        		"4：有声音，有震动，亮屏下通知悬浮展示，锁屏通知以默认形式展示且唤醒屏幕。（推荐）");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        style.setChannel("自定义channel");
        style.setChannelName("自定义channelName");
        style.setChannelLevel(4);
        template.setStyle(style);

        // 设置打开的网址地址
        template.setUrl("http://www.tjsyns.com/");
        // 设置定时展示时间，安卓机型可用
        // template.setDuration("2019-08-16 11:40:00", "2019-08-16 12:24:00");

        // 消息覆盖
        // template.setNotifyid(123); // 在消息推送的时候设置自定义的notifyid。如果需要覆盖此条消息，则下次使用相同的notifyid发一条新的消息。客户端sdk会根据notifyid进行覆盖。

        //template.setAPNInfo(getAPNPayload()); //详见本页iOS通知样式设置
        return template;
    }
    
    
    
}