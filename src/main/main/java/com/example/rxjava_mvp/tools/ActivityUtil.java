package com.example.rxjava_mvp.tools;

import android.app.Activity;
import android.os.Process;

import java.util.ArrayList;
import java.util.List;

public class ActivityUtil {
    private List<Activity> activityList=new ArrayList<>();
    private static ActivityUtil instance;
    //单例模式中获取唯一的ExitApplication实例
    public static synchronized ActivityUtil getInstance(){
        if (null==instance){
            instance=new ActivityUtil();
        }
        return instance;
    }
    //添加activity到容器中
    public void addActivity(Activity activity){
        if (activityList==null){
            activityList=new ArrayList<>();
            activityList.add(activity);
        }else {
            activityList.add(activity);
        }
    }
    //移除Activity
    public void removeActivity(Activity activity){
        if (activityList!=null){
            activityList.remove(activity);
        }
    }
    //遍历所以Activity并finsh
    public void exitSystem(){
        for (Activity activity:activityList){
            if (activity!=null){
                activity.finish();
            }
        }
        //退出进程
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}
