package layout.layoutgenerator.preview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
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

public class FrameLayoutActivity extends AppCompatActivity {

  //  LinearLayout rootLayout;
  FrameLayout rootLayout;

    // View tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          /*  setContentView(R.layout.activity_vertical_linear_layout);
            Log.d("VerticalLinearLayout", "In Vertical Linear Layout");
            rootLayout = (LinearLayout) findViewById(R.id.containerroot);
       */
        rootLayout = new FrameLayout(this);
        rootLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        setContentView(rootLayout);

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
        HashMap<Integer,WidgetPropertiesDTO> widgetInfoMap = MobileApplication.getInstance().getWidgetInfoMap();
        Map<Integer, WidgetPropertiesDTO> treeMap = new TreeMap<Integer, WidgetPropertiesDTO>(widgetInfoMap);
        if(widgetInfoMap!=null && widgetInfoMap.size()>0){
            for (Map.Entry<Integer, WidgetPropertiesDTO> entry : treeMap.entrySet()){
                WidgetPropertiesDTO temp = entry.getValue();
                String widgetName = temp.getWidgetName();
                if (widgetName.equalsIgnoreCase("TextView")){
                    TextView tv = new TextView(this);
                    tv = FrameLayoutWidgetProperties.getInstance().setTextViewProperties(tv,temp);
                    rootLayout.addView(tv);
                }else if(widgetName.equalsIgnoreCase("EditText")){
                    EditText et = new EditText(this);
                    et = FrameLayoutWidgetProperties.getInstance().setEditTextProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("Button")){
                    Button et = new Button(this);
                    et = FrameLayoutWidgetProperties.getInstance().setButtonProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("RadioButton")){
                    RadioButton et = new RadioButton(this);
                    et = FrameLayoutWidgetProperties.getInstance().setRadioProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("CheckBox")){
                    CheckBox et = new CheckBox(this);
                    et = FrameLayoutWidgetProperties.getInstance().setCheckBoxProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("ImageView")){
                    ImageView et = new ImageView(this);
                    et = FrameLayoutWidgetProperties.getInstance().setImageViewProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("ImageButton")){
                    ImageButton et = new ImageButton(this);
                    et = FrameLayoutWidgetProperties.getInstance().setImageButtonProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("RatingBar")){
                    RatingBar et = new RatingBar(this);
                    et = FrameLayoutWidgetProperties.getInstance().setRatingBarProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("Spinner")){
                    Spinner et = new Spinner(this);
                    et = FrameLayoutWidgetProperties.getInstance().setSpinnerProperties(et,temp);
                    rootLayout.addView(et);
                }


            }
        }
    }





}







