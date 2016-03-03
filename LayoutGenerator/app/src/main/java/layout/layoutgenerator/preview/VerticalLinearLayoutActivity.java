package layout.layoutgenerator.preview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
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
import layout.layoutgenerator.utils.UniqueIDGenerator;

public class VerticalLinearLayoutActivity extends AppCompatActivity {

    LinearLayout rootLayout;

    // View tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_vertical_linear_layout);


        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.activity_vertical_linear_layout, null);

// fill in any details dynamically here


// insert into main view
        rootLayout = (LinearLayout) v.findViewById(R.id.containerroot);
        parseViewMap();
        setContentView(v);
    }


    private void parseViewMap(){
        HashMap<Integer,WidgetPropertiesDTO> widgetInfoMap = MobileApplication.getInstance().getWidgetInfoMap();
        Map<Integer, WidgetPropertiesDTO> treeMap = new TreeMap<Integer, WidgetPropertiesDTO>(widgetInfoMap);
        if(widgetInfoMap!=null && widgetInfoMap.size()>0){
            for (Map.Entry<Integer, WidgetPropertiesDTO> entry : treeMap.entrySet()){
                WidgetPropertiesDTO temp = entry.getValue();
                String widgetName = temp.getWidgetName();
                if (widgetName.equalsIgnoreCase("TextView")){
                    TextView tv = new TextView(getApplicationContext());
                    tv = WidgetProperties.getInstance().setTextViewProperties(tv,temp);
                    rootLayout.addView(tv);
                }else if(widgetName.equalsIgnoreCase("EditText")){
                    EditText et = new EditText(getApplicationContext());
                    et = WidgetProperties.getInstance().setEditTextProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("Button")){
                    Button et = new Button(getApplicationContext());
                    et = WidgetProperties.getInstance().setButtonProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("RadioButton")){
                    RadioButton et = new RadioButton(getApplicationContext());
                    et = WidgetProperties.getInstance().setRadioProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("CheckBox")){
                    CheckBox et = new CheckBox(getApplicationContext());
                    et = WidgetProperties.getInstance().setCheckBoxProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("ImageView")){
                    ImageView et = new ImageView(getApplicationContext());
                    et = WidgetProperties.getInstance().setImageViewProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("ImageButton")){
                    ImageButton et = new ImageButton(getApplicationContext());
                    et = WidgetProperties.getInstance().setImageButtonProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("RatingBar")){
                    RatingBar et = new RatingBar(getApplicationContext());
                    et = WidgetProperties.getInstance().setRatingBarProperties(et, temp);
                    rootLayout.addView(et);
                }else if(widgetName.equalsIgnoreCase("Spinner")){
                    Spinner et = new Spinner(getApplicationContext());
                    et = WidgetProperties.getInstance().setSpinnerProperties(et,temp);
                    rootLayout.addView(et);
                }


            }
        }
    }





}







