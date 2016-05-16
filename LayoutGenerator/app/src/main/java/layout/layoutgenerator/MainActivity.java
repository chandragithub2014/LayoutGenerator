package layout.layoutgenerator;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import java.util.HashMap;

import layout.layoutgenerator.Application.MobileApplication;
import layout.layoutgenerator.DTO.WidgetPropertiesDTO;
import layout.layoutgenerator.fragments.LayoutGeneratorCatalogListFragment;
import layout.layoutgenerator.fragments.LayoutListFragment;
import layout.layoutgenerator.fragments.ViewFragment;

public class MainActivity extends AppCompatActivity {
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ToolBar Start
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("Login");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        MobileApplication.getInstance().setDeviceType(isTablet(MainActivity.this));
        if(isTablet(MainActivity.this) == 7 || isTablet(MainActivity.this)==10){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }

        LayoutInflater mInflater= LayoutInflater.from(getApplicationContext());
        View mCustomView = mInflater.inflate(R.layout.toolbar_custom_view, null);
        mToolbar.addView(mCustomView);


        populateColorHashMap();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout, new LayoutListFragment())
                .commit();

    }
//

    private int isTablet(Context context) {
        int deviceSize = 0;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        float scaleFactor = metrics.density;
        float widthDp = widthPixels / scaleFactor;
        float heightDp = heightPixels / scaleFactor;
        int smallestWidth = (int) Math.min(widthDp, heightDp);

        if (smallestWidth >= 720) {
            //Device is a 10" tablet
            deviceSize = 10;
            Configuration configuration = getResources().getConfiguration();
            configuration.fontScale=(float) 1.5; //0.85 small size, 1 normal size, 1,15 big etc

            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
            return deviceSize;
        }
        else if (smallestWidth >= 600) {
            //Device is a 7" tablet
            deviceSize = 7;
            Configuration configuration = getResources().getConfiguration();
            configuration.fontScale=(float) 1.3; //0.85 small size, 1 normal size, 1,15 big etc

            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
            return deviceSize;
        }
        else
            return deviceSize;

//
//	    return (context.getResources().getConfiguration().screenLayout
//	            & Configuration.SCREENLAYOUT_SIZE_MASK)
//	            >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    //
    private void populateColorHashMap(){

      HashMap<String,String> colorHash = new HashMap<String,String>();
        colorHash.put("Black","#000000");
        colorHash.put("Red","#FF0000");
        colorHash.put("Cyan","#00FFFF");
        colorHash.put("Blue","#0000FF");
        colorHash.put("Purple","#800080");
        colorHash.put("Yellow","#FFFF00");
        colorHash.put("Magenta","#FF00FF");
        colorHash.put("White","#ffffff");
        colorHash.put("Blue Gray","#FF00FF");
        colorHash.put("Slate Gray","#FF00FF");
        colorHash.put("Orange","#FFA500");

        MobileApplication.getInstance().setColorHash(colorHash);
    }
    @Override
    public void onBackPressed() {
        boolean isGeneratorFragment = MobileApplication.getInstance().isGeneratorFragment();
        boolean isHoriVertGenFrag = MobileApplication.getInstance().isHorizonVertGeneratorFragment();
        if(isGeneratorFragment) {
            int widgetPosition = MobileApplication.getInstance().getWidgetPos();
            if (widgetPosition != 0) {
                widgetPosition = widgetPosition - 1;
                MobileApplication.getInstance().setWidgetPos(widgetPosition);
            }
            super.onBackPressed();
        }else if(isHoriVertGenFrag){
            int rowPosition = MobileApplication.getInstance().getRowPosition();
            MobileApplication.getInstance().setIsBack(true);
            if(rowPosition!=0){
                rowPosition = rowPosition - 1;
                MobileApplication.getInstance().setRowPosition(rowPosition);
            }
            super.onBackPressed();
        }
        else{
            super.onBackPressed();
        }
    }
}
