package layout.layoutgenerator.Application;

import android.app.Activity;
import android.app.Application;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import layout.layoutgenerator.DTO.WidgetPropertiesDTO;

/**
 * Created by 245742 on 9/8/2015.
 */
public class MobileApplication extends Application {
    private static MobileApplication singleton;
    private Activity activity;
    private int screenWidth;
    List<WidgetPropertiesDTO> widgetList = new ArrayList<WidgetPropertiesDTO>();
    private int widgetPos;
    HashMap<Integer ,WidgetPropertiesDTO> widgetInfoMap  = new HashMap<Integer ,WidgetPropertiesDTO>();

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
    }

    public static MobileApplication getInstance() {
        return singleton;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public List<WidgetPropertiesDTO> getWidgetList() {
        return widgetList;
    }

    public void setWidgetList(List<WidgetPropertiesDTO> widgetList) {
        this.widgetList = widgetList;
    }

    public int getWidgetPos() {
        return widgetPos;
    }

    public void setWidgetPos(int widgetPos) {
        this.widgetPos = widgetPos;
    }

    public HashMap<Integer, WidgetPropertiesDTO> getWidgetInfoMap() {
        return widgetInfoMap;
    }

    public void setWidgetInfoMap(HashMap<Integer, WidgetPropertiesDTO> widgetInfoMap) {
        this.widgetInfoMap = widgetInfoMap;
    }
}
