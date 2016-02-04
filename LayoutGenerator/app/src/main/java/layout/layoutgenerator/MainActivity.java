package layout.layoutgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

import layout.layoutgenerator.Application.MobileApplication;
import layout.layoutgenerator.DTO.WidgetPropertiesDTO;
import layout.layoutgenerator.fragments.ViewFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileApplication.getInstance().setWidgetPos(0);
        HashMap<Integer,WidgetPropertiesDTO> widgetInfo = new HashMap<Integer,WidgetPropertiesDTO>();
        MobileApplication.getInstance().setWidgetInfoMap(widgetInfo);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout, new ViewFragment())
                .commit();
        //View.generateViewId();
    }

    @Override
    public void onBackPressed() {
        int widgetPosition = MobileApplication.getInstance().getWidgetPos();
        if(widgetPosition!=0){
            widgetPosition = widgetPosition - 1;
            MobileApplication.getInstance().setWidgetPos(widgetPosition);
        }
        super.onBackPressed();
    }
}
