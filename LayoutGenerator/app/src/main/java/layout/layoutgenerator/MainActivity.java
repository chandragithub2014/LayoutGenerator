package layout.layoutgenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        LayoutInflater mInflater= LayoutInflater.from(getApplicationContext());
        View mCustomView = mInflater.inflate(R.layout.toolbar_custom_view, null);
        mToolbar.addView(mCustomView);


        populateColorHashMap();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout, new LayoutListFragment())
                .commit();

    }

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
