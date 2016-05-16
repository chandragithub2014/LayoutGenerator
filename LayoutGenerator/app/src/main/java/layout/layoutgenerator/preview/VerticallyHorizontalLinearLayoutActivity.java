package layout.layoutgenerator.preview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import layout.layoutgenerator.Application.MobileApplication;
import layout.layoutgenerator.DTO.WidgetPropertiesDTO;
import layout.layoutgenerator.R;

public class VerticallyHorizontalLinearLayoutActivity extends AppCompatActivity {

    LinearLayout parentLayout;

    // View tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_linear_layout);
Log.d("VerticalLinearLayout", "In Vertical Linear Layout");

        //ToolBar Start
        Toolbar   mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("PREVIEW");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        LayoutInflater mInflater= LayoutInflater.from(getApplicationContext());
        View mCustomView = mInflater.inflate(R.layout.toolbar_custom_view, null);
        mToolbar.addView(mCustomView);

        parentLayout = (LinearLayout)findViewById(R.id.containerroot);
        //rootLayout = (LinearLayout)findViewById(R.id.containerroot);
        /*int width  = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        TextView tv = new TextView(this);
        tv.setId(UniqueIDGenerator.generateViewId());
        LinearLayout.LayoutParams paramss;
        paramss  = new LinearLayout.LayoutParams(width,height);
        tv.setText("Hello Motooooooo");

        tv.setPadding(5, 5, 5, 5);
        paramss.setMargins(5, 5, 5, 5);
        tv.setGravity(Gravity.CENTER);
        tv.setLayoutParams(paramss);
        rootLayout.addView(tv);*/
     //   parentView.addView(rootView);

// fill in any details dynamically here


// insert into main view

       parseViewMap();


    }


    private void parseViewMap(){
        try{
            HashMap<Integer, HashMap<Integer, WidgetPropertiesDTO>> verticalHorizontalRow  = MobileApplication.getInstance().getRowHashMap();
            Map<Integer, HashMap<Integer, WidgetPropertiesDTO>> treeMap = new TreeMap<Integer, HashMap<Integer, WidgetPropertiesDTO>>(verticalHorizontalRow);
            LinearLayout rootLayout;
            for (Map.Entry<Integer, HashMap<Integer, WidgetPropertiesDTO>> entry : treeMap.entrySet()){
                rootLayout = new LinearLayout(this);
                rootLayout = VerticalHorizontalWidgetProperties.getInstance().setLinearLayoutHorizontalProperties(rootLayout);
                HashMap<Integer,WidgetPropertiesDTO> widgetInfoMap = entry.getValue();
                Map<Integer, WidgetPropertiesDTO> widgetInfotreeMap = new TreeMap<Integer, WidgetPropertiesDTO>(widgetInfoMap);
                for (Map.Entry<Integer, WidgetPropertiesDTO> infoentry : widgetInfotreeMap.entrySet()){
                    System.out.println(infoentry.getKey() + "/" + infoentry.getValue());
                    WidgetPropertiesDTO temp = infoentry.getValue();
                    String widgetName = temp.getWidgetName();
                    if (widgetName.equalsIgnoreCase("TextView")){
                        TextView tv = new TextView(this);
                        tv = VerticalHorizontalWidgetProperties.getInstance().setTextViewProperties(tv,temp);
                        rootLayout.addView(tv);
                    }else if(widgetName.equalsIgnoreCase("EditText")){
                        EditText et = new EditText(this);
                        et = VerticalHorizontalWidgetProperties.getInstance().setEditTextProperties(et, temp);
                        rootLayout.addView(et);
                    }else if(widgetName.equalsIgnoreCase("Button")){
                        Button et = new Button(this);
                        et = VerticalHorizontalWidgetProperties.getInstance().setButtonProperties(et, temp);
                        rootLayout.addView(et);
                    }else if(widgetName.equalsIgnoreCase("RadioButton")){
                        RadioButton et = new RadioButton(this);
                        et = VerticalHorizontalWidgetProperties.getInstance().setRadioProperties(et, temp);
                        rootLayout.addView(et);
                    }else if(widgetName.equalsIgnoreCase("CheckBox")){
                        CheckBox et = new CheckBox(this);
                        et = VerticalHorizontalWidgetProperties.getInstance().setCheckBoxProperties(et, temp);
                        rootLayout.addView(et);
                    }else if(widgetName.equalsIgnoreCase("ImageView")){
                        ImageView et = new ImageView(this);
                        et = VerticalHorizontalWidgetProperties.getInstance().setImageViewProperties(et, temp);
                        rootLayout.addView(et);
                    }else if(widgetName.equalsIgnoreCase("ImageButton")){
                        ImageButton et = new ImageButton(this);
                        et = VerticalHorizontalWidgetProperties.getInstance().setImageButtonProperties(et, temp);
                        rootLayout.addView(et);
                    }else if(widgetName.equalsIgnoreCase("RatingBar")){
                        RatingBar et = new RatingBar(this);
                        et = VerticalHorizontalWidgetProperties.getInstance().setRatingBarProperties(et, temp);
                        rootLayout.addView(et);
                    }else if(widgetName.equalsIgnoreCase("Spinner")){
                        Spinner et = new Spinner(this);
                        et = VerticalHorizontalWidgetProperties.getInstance().setSpinnerProperties(et,temp);
                        rootLayout.addView(et);
                    }
                }
                parentLayout.addView(rootLayout);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
       // HashMap<Integer,WidgetPropertiesDTO> widgetInfoMap = MobileApplication.getInstance().getWidgetInfoMap();
      //  Map<Integer, WidgetPropertiesDTO> treeMap = new TreeMap<Integer, WidgetPropertiesDTO>(widgetInfoMap);
        /*if(widgetInfoMap!=null && widgetInfoMap.size()>0){
            for (Map.Entry<Integer, WidgetPropertiesDTO> entry : treeMap.entrySet()){
                WidgetPropertiesDTO temp = entry.getValue();
                String widgetName = temp.getWidgetName();
                if (widgetName.equalsIgnoreCase("TextView")){
                    TextView tv = new TextView(this);
                    tv = WidgetProperties.getInstance().setTextViewProperties(tv,temp);
                    rootLayout.addView(tv);
                }else if(widgetName.equalsIgnoreCase("EditText")){
                    EditText et = new EditText(this);
                    et = WidgetProperties.getInstance().setEditTextProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("Button")){
                    Button et = new Button(this);
                    et = WidgetProperties.getInstance().setButtonProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("RadioButton")){
                    RadioButton et = new RadioButton(this);
                    et = WidgetProperties.getInstance().setRadioProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("CheckBox")){
                    CheckBox et = new CheckBox(this);
                    et = WidgetProperties.getInstance().setCheckBoxProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("ImageView")){
                    ImageView et = new ImageView(this);
                    et = WidgetProperties.getInstance().setImageViewProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("ImageButton")){
                    ImageButton et = new ImageButton(this);
                    et = WidgetProperties.getInstance().setImageButtonProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("RatingBar")){
                    RatingBar et = new RatingBar(this);
                    et = WidgetProperties.getInstance().setRatingBarProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("Spinner")){
                    Spinner et = new Spinner(this);
                    et = WidgetProperties.getInstance().setSpinnerProperties(et,temp);
                    rootLayout.addView(et);
                }


            }
        }*/
    }





}







