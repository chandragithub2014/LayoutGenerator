package layout.layoutgenerator.preview;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.ViewGroup;
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

import org.xmlpull.v1.XmlSerializer;

import layout.layoutgenerator.Application.MobileApplication;
import layout.layoutgenerator.DTO.WidgetPropertiesDTO;
import layout.layoutgenerator.R;
import layout.layoutgenerator.utils.UniqueIDGenerator;

/**
 * Created by CHANDRASAIMOHAN on 3/4/2016.
 */
public class WidgetProperties {

    private static WidgetProperties instance;
    Context ctx;
    XmlSerializer xmlSerializer;
    private WidgetProperties(){

    }


    public static WidgetProperties getInstance(){
        if(instance == null){
            instance = new WidgetProperties();
        }
        return instance;
    }


    public  TextView setTextViewProperties(TextView tv, WidgetPropertiesDTO temp) {
        int WRAP_CONTENT = LinearLayout.LayoutParams.WRAP_CONTENT;
        int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
        int width=0,height=0;

        LinearLayout.LayoutParams paramss;

     //   paramss  = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        tv.setId(UniqueIDGenerator.generateViewId());
        //Width
        if (temp.getWidth().equalsIgnoreCase("wrap_content")) {
            //tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
    } else if (temp.getWidth().equalsIgnoreCase("match_parent")) {
   //     tv.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        width = LinearLayout.LayoutParams.MATCH_PARENT;
    } else {
        String dimension = temp.getWidth();
        String alteredString = dimension.substring(0, dimension.length() - 2);
        width = Integer.parseInt(alteredString);
        // tv.setWidth(Integer.parseInt(alteredString));
    }

        //Height
        if (temp.getHeight().equalsIgnoreCase("wrap_content")) {
            //tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            height = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getHeight().equalsIgnoreCase("match_parent")) {
            //tv.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
            height = LinearLayout.LayoutParams.MATCH_PARENT;
        } else {
            String dimension = temp.getHeight();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
              //  tv.setHeight(Integer.parseInt(alteredString));
                height = Integer.parseInt(alteredString);
            }
        }
        paramss  = new LinearLayout.LayoutParams(width,height);
        //padding
        if (!TextUtils.isEmpty(temp.getPadding())) {
            String dimension = temp.getPadding();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setPadding(padding, padding, padding, padding);
            }
        }


        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
              //  LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramss.setMargins(padding, padding, padding, padding);

            }
        }


        //gravity
       if(!MobileApplication.getInstance().isFrameLayout()) {
        if (!TextUtils.isEmpty(temp.getGravity())) {
            String gravity = temp.getGravity();
            if (gravity.equalsIgnoreCase("center")) {
                tv.setGravity(Gravity.CENTER);
            } else if (gravity.equalsIgnoreCase("center_horizontal")) {
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("center_vertical")) {
                tv.setGravity(Gravity.CENTER_VERTICAL);

            } else if (gravity.equalsIgnoreCase("clip_horizontal")) {
                tv.setGravity(Gravity.CLIP_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("clip_vertical")) {
                tv.setGravity(Gravity.CLIP_VERTICAL);

            } else if (gravity.equalsIgnoreCase("end")) {
                tv.setGravity(Gravity.END);

            } else if (gravity.equalsIgnoreCase("left")) {
                tv.setGravity(Gravity.LEFT);

            } else if (gravity.equalsIgnoreCase("right")) {
                tv.setGravity(Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("start")) {
                tv.setGravity(Gravity.START);

            } else if (gravity.equalsIgnoreCase("top")) {
                tv.setGravity(Gravity.TOP);

            } else if (gravity.equalsIgnoreCase("fill_horizontal")) {
                tv.setGravity(Gravity.FILL_HORIZONTAL);

            } else if (gravity.equalsIgnoreCase("fill_vertical")) {
                tv.setGravity(Gravity.FILL_VERTICAL);

            } else if (gravity.equalsIgnoreCase("fill")) {
                tv.setGravity(Gravity.FILL);

            }else if (gravity.equalsIgnoreCase("right|bottom")) {
                tv.setGravity(Gravity.RIGHT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("left|bottom")) {
                tv.setGravity(Gravity.LEFT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("top|right")) {
                tv.setGravity(Gravity.TOP|Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("top|left")) {
                tv.setGravity(Gravity.TOP|Gravity.LEFT);

            }
        }
           else{
               String gravity = temp.getGravity();
               if(!TextUtils.isEmpty(gravity)) {
                   paramss = setGravity(paramss, gravity);
               }
           }

        }
        // Name
        if (!TextUtils.isEmpty(temp.getWidgetLabel())) {
            tv.setText(temp.getWidgetLabel());
        }


        //Color and TextSize
        if(!TextUtils.isEmpty(temp.getTextSize())){
            String dimension = temp.getTextSize();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setTextSize(padding);
            }
        }

        if(!TextUtils.isEmpty(temp.getColor())){
            tv.setBackgroundColor(Color.parseColor(MobileApplication.getInstance().getColorHash().get(temp.getColor())));
           // mTextView.setTextColor(Color.parseColor("#bdbdbd"));
        }

        tv.setLayoutParams(paramss);
        return tv;
    }



    public EditText setEditTextProperties(EditText tv, WidgetPropertiesDTO temp) {

        int width=0,height=0;

        LinearLayout.LayoutParams paramss;
        tv.setId(UniqueIDGenerator.generateViewId());
        //Width
        if (temp.getWidth().equalsIgnoreCase("wrap_content")) {
            //tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getWidth().equalsIgnoreCase("match_parent")) {
            //     tv.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            width = LinearLayout.LayoutParams.MATCH_PARENT;
        } else {
            String dimension = temp.getWidth();
            String alteredString = dimension.substring(0, dimension.length() - 2);
            width = Integer.parseInt(alteredString);
            // tv.setWidth(Integer.parseInt(alteredString));
        }

        //Height
        if (temp.getHeight().equalsIgnoreCase("wrap_content")) {
            //tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            height = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getHeight().equalsIgnoreCase("match_parent")) {
            //tv.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
            height = LinearLayout.LayoutParams.MATCH_PARENT;
        } else {
            String dimension = temp.getHeight();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                //  tv.setHeight(Integer.parseInt(alteredString));
                height = Integer.parseInt(alteredString);
            }
        }
        paramss  = new LinearLayout.LayoutParams(width,height);
        //padding
        if (!TextUtils.isEmpty(temp.getPadding())) {
            String dimension = temp.getPadding();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setPadding(padding, padding, padding, padding);
            }
        }


        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
            //    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramss.setMargins(padding, padding, padding, padding);
                //tv.setLayoutParams(paramss);
            }
        }


        //gravity
        if(!MobileApplication.getInstance().isFrameLayout()) {
        if (!TextUtils.isEmpty(temp.getGravity())) {
            String gravity = temp.getGravity();
            if (gravity.equalsIgnoreCase("center")) {
                tv.setGravity(Gravity.CENTER);
            } else if (gravity.equalsIgnoreCase("center_horizontal")) {
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("center_vertical")) {
                tv.setGravity(Gravity.CENTER_VERTICAL);

            } else if (gravity.equalsIgnoreCase("clip_horizontal")) {
                tv.setGravity(Gravity.CLIP_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("clip_vertical")) {
                tv.setGravity(Gravity.CLIP_VERTICAL);

            } else if (gravity.equalsIgnoreCase("end")) {
                tv.setGravity(Gravity.END);

            } else if (gravity.equalsIgnoreCase("left")) {
                tv.setGravity(Gravity.LEFT);

            } else if (gravity.equalsIgnoreCase("right")) {
                tv.setGravity(Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("start")) {
                tv.setGravity(Gravity.START);

            } else if (gravity.equalsIgnoreCase("top")) {
                tv.setGravity(Gravity.TOP);

            } else if (gravity.equalsIgnoreCase("fill_horizontal")) {
                tv.setGravity(Gravity.FILL_HORIZONTAL);

            } else if (gravity.equalsIgnoreCase("fill_vertical")) {
                tv.setGravity(Gravity.FILL_VERTICAL);

            } else if (gravity.equalsIgnoreCase("fill")) {
                tv.setGravity(Gravity.FILL);

            }else if (gravity.equalsIgnoreCase("right|bottom")) {
                tv.setGravity(Gravity.RIGHT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("left|bottom")) {
                tv.setGravity(Gravity.LEFT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("top|right")) {
                tv.setGravity(Gravity.TOP|Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("top|left")) {
                tv.setGravity(Gravity.TOP|Gravity.LEFT);

            }
        }else{
            String gravity = temp.getGravity();
            if(!TextUtils.isEmpty(gravity)) {
                paramss = setGravity(paramss, gravity);
            }
        }


        }
        // Name
        if (!TextUtils.isEmpty(temp.getWidgetLabel())) {
            tv.setText(temp.getWidgetLabel());
        }


        //Color and TextSize
        if(!TextUtils.isEmpty(temp.getTextSize())){
            String dimension = temp.getTextSize();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setTextSize(padding);
            }
        }

        if(!TextUtils.isEmpty(temp.getColor())){
            tv.setBackgroundColor(Color.parseColor(MobileApplication.getInstance().getColorHash().get(temp.getColor())));
            // mTextView.setTextColor(Color.parseColor("#bdbdbd"));
        }
        tv.setLayoutParams(paramss);
        return tv;
    }


    public Button setButtonProperties(Button tv, WidgetPropertiesDTO temp) {

        int width=0,height=0;

        LinearLayout.LayoutParams paramss;
        tv.setId(UniqueIDGenerator.generateViewId());
        //Width
        if (temp.getWidth().equalsIgnoreCase("wrap_content")) {
            //tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getWidth().equalsIgnoreCase("match_parent")) {
            //     tv.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            width = LinearLayout.LayoutParams.MATCH_PARENT;
        } else {
            String dimension = temp.getWidth();
            String alteredString = dimension.substring(0, dimension.length() - 2);
            width = Integer.parseInt(alteredString);
            // tv.setWidth(Integer.parseInt(alteredString));
        }

        //Height
        if (temp.getHeight().equalsIgnoreCase("wrap_content")) {
            //tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            height = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getHeight().equalsIgnoreCase("match_parent")) {
            //tv.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
            height = LinearLayout.LayoutParams.MATCH_PARENT;
        } else {
            String dimension = temp.getHeight();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                //  tv.setHeight(Integer.parseInt(alteredString));
                height = Integer.parseInt(alteredString);
            }
        }
        paramss  = new LinearLayout.LayoutParams(width,height);

        //padding
        if (!TextUtils.isEmpty(temp.getPadding())) {
            String dimension = temp.getPadding();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setPadding(padding, padding, padding, padding);
            }
        }


        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
               // LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                paramss.setMargins(padding, padding, padding, padding);
              //
            }
        }


        //gravity
        if(!MobileApplication.getInstance().isFrameLayout()) {
        if (!TextUtils.isEmpty(temp.getGravity())) {
            String gravity = temp.getGravity();
            if (gravity.equalsIgnoreCase("center")) {
                tv.setGravity(Gravity.CENTER);
            } else if (gravity.equalsIgnoreCase("center_horizontal")) {
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("center_vertical")) {
                tv.setGravity(Gravity.CENTER_VERTICAL);

            } else if (gravity.equalsIgnoreCase("clip_horizontal")) {
                tv.setGravity(Gravity.CLIP_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("clip_vertical")) {
                tv.setGravity(Gravity.CLIP_VERTICAL);

            } else if (gravity.equalsIgnoreCase("end")) {
                tv.setGravity(Gravity.END);

            } else if (gravity.equalsIgnoreCase("left")) {
                tv.setGravity(Gravity.LEFT);

            } else if (gravity.equalsIgnoreCase("right")) {
                tv.setGravity(Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("start")) {
                tv.setGravity(Gravity.START);

            } else if (gravity.equalsIgnoreCase("top")) {
                tv.setGravity(Gravity.TOP);

            } else if (gravity.equalsIgnoreCase("fill_horizontal")) {
                tv.setGravity(Gravity.FILL_HORIZONTAL);

            } else if (gravity.equalsIgnoreCase("fill_vertical")) {
                tv.setGravity(Gravity.FILL_VERTICAL);

            } else if (gravity.equalsIgnoreCase("fill")) {
                tv.setGravity(Gravity.FILL);

            }else if (gravity.equalsIgnoreCase("right|bottom")) {
                tv.setGravity(Gravity.RIGHT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("left|bottom")) {
                tv.setGravity(Gravity.LEFT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("top|right")) {
                tv.setGravity(Gravity.TOP|Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("top|left")) {
                tv.setGravity(Gravity.TOP|Gravity.LEFT);

            }
        }else{
            String gravity = temp.getGravity();
            if(!TextUtils.isEmpty(gravity)) {
                paramss = setGravity(paramss, gravity);
            }
        }


        }
        // Name
        if (!TextUtils.isEmpty(temp.getWidgetLabel())) {
            tv.setText(temp.getWidgetLabel());
        }

        //Color and TextSize
        if(!TextUtils.isEmpty(temp.getTextSize())){
            String dimension = temp.getTextSize();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setTextSize(padding);
            }
        }

        if(!TextUtils.isEmpty(temp.getColor())){
            tv.setBackgroundColor(Color.parseColor(MobileApplication.getInstance().getColorHash().get(temp.getColor())));
            // mTextView.setTextColor(Color.parseColor("#bdbdbd"));
        }
        tv.setLayoutParams(paramss);
        return tv;
    }


    public Spinner setSpinnerProperties(Spinner tv, WidgetPropertiesDTO temp) {

        int width=0,height=0;
        tv.setId(UniqueIDGenerator.generateViewId());
        //Width
    //    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);




        //Width
        if (temp.getWidth().equalsIgnoreCase("wrap_content")) {
            //tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getWidth().equalsIgnoreCase("match_parent")) {
            //     tv.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            width = LinearLayout.LayoutParams.MATCH_PARENT;
        }else{
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
        }

        //Height
        if (temp.getHeight().equalsIgnoreCase("wrap_content")) {
            //tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            height = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getHeight().equalsIgnoreCase("match_parent")) {
            //tv.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
            height = LinearLayout.LayoutParams.MATCH_PARENT;
        }else{
            height = LinearLayout.LayoutParams.WRAP_CONTENT;
        }


        //padding
        if (!TextUtils.isEmpty(temp.getPadding())) {
            String dimension = temp.getPadding();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setPadding(padding, padding, padding, padding);
            }
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);
        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
           //     LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(padding, padding, padding, padding);
              //  tv.setLayoutParams(params);
            }
        }


        //gravity
        if(!MobileApplication.getInstance().isFrameLayout()) {
        if (!TextUtils.isEmpty(temp.getGravity())) {
            String gravity = temp.getGravity();
            if (gravity.equalsIgnoreCase("center")) {
                tv.setGravity(Gravity.CENTER);
            } else if (gravity.equalsIgnoreCase("center_horizontal")) {
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("center_vertical")) {
                tv.setGravity(Gravity.CENTER_VERTICAL);

            } else if (gravity.equalsIgnoreCase("clip_horizontal")) {
                tv.setGravity(Gravity.CLIP_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("clip_vertical")) {
                tv.setGravity(Gravity.CLIP_VERTICAL);

            } else if (gravity.equalsIgnoreCase("end")) {
                tv.setGravity(Gravity.END);

            } else if (gravity.equalsIgnoreCase("left")) {
                tv.setGravity(Gravity.LEFT);

            } else if (gravity.equalsIgnoreCase("right")) {
                tv.setGravity(Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("start")) {
                tv.setGravity(Gravity.START);

            } else if (gravity.equalsIgnoreCase("top")) {
                tv.setGravity(Gravity.TOP);

            } else if (gravity.equalsIgnoreCase("fill_horizontal")) {
                tv.setGravity(Gravity.FILL_HORIZONTAL);

            } else if (gravity.equalsIgnoreCase("fill_vertical")) {
                tv.setGravity(Gravity.FILL_VERTICAL);

            } else if (gravity.equalsIgnoreCase("fill")) {
                tv.setGravity(Gravity.FILL);

            }
        }else{
            String gravity = temp.getGravity();
            if(!TextUtils.isEmpty(gravity)) {
                layoutParams = setGravity(layoutParams, gravity);
            }
        }


        }
        if(!TextUtils.isEmpty(temp.getColor())){
            tv.setBackgroundColor(Color.parseColor(MobileApplication.getInstance().getColorHash().get(temp.getColor())));
            // mTextView.setTextColor(Color.parseColor("#bdbdbd"));
        }
        tv.setLayoutParams(layoutParams);
        return tv;
    }

    public CheckBox setCheckBoxProperties(CheckBox tv, WidgetPropertiesDTO temp) {
int width =0,height = 0;

        tv.setId(UniqueIDGenerator.generateViewId());

      /*  tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);*/

        //Width
        if (temp.getWidth().equalsIgnoreCase("wrap_content")) {
            //tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getWidth().equalsIgnoreCase("match_parent")) {
            //     tv.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            width = LinearLayout.LayoutParams.MATCH_PARENT;
        }else{
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
        }

        //Height
        if (temp.getHeight().equalsIgnoreCase("wrap_content")) {
            //tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            height = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getHeight().equalsIgnoreCase("match_parent")) {
            //tv.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
            height = LinearLayout.LayoutParams.MATCH_PARENT;
        }else{
            height = LinearLayout.LayoutParams.WRAP_CONTENT;
        }


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);

        //padding
        if (!TextUtils.isEmpty(temp.getPadding())) {
            String dimension = temp.getPadding();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setPadding(padding, padding, padding, padding);
            }
        }


        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
              //  LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(padding, padding, padding, padding);
               // tv.setLayoutParams(params);
            }
        }


        //gravity
        if (!TextUtils.isEmpty(temp.getGravity())) {
            String gravity = temp.getGravity();
            if (gravity.equalsIgnoreCase("center")) {
                layoutParams.gravity = (Gravity.CENTER);
            } else if (gravity.equalsIgnoreCase("center_horizontal")) {
                layoutParams.gravity = (Gravity.CENTER_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("center_vertical")) {
                layoutParams.gravity = (Gravity.CENTER_VERTICAL);

            }  else if (gravity.equalsIgnoreCase("end")) {
                layoutParams.gravity = (Gravity.END);

            } else if (gravity.equalsIgnoreCase("left")) {
                layoutParams.gravity = (Gravity.LEFT);

            } else if (gravity.equalsIgnoreCase("right")) {
                layoutParams.gravity = (Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("start")) {
                layoutParams.gravity = (Gravity.START);

            } else if (gravity.equalsIgnoreCase("top")) {
                layoutParams.gravity = (Gravity.TOP);

            } else if (gravity.equalsIgnoreCase("fill_horizontal")) {
                layoutParams.gravity = (Gravity.FILL_HORIZONTAL);

            } else if (gravity.equalsIgnoreCase("fill_vertical")) {
                layoutParams.gravity = (Gravity.FILL_VERTICAL);

            } else if (gravity.equalsIgnoreCase("fill")) {
                layoutParams.gravity = (Gravity.FILL);

            }


        }
        // Name
        if (!TextUtils.isEmpty(temp.getWidgetLabel())) {
            tv.setText(temp.getWidgetLabel());
        }

        if(!TextUtils.isEmpty(temp.getColor())){
            tv.setBackgroundColor(Color.parseColor(MobileApplication.getInstance().getColorHash().get(temp.getColor())));
            // mTextView.setTextColor(Color.parseColor("#bdbdbd"));
        }
        tv.setLayoutParams(layoutParams);
        return tv;
    }



    public RadioButton setRadioProperties(RadioButton tv, WidgetPropertiesDTO temp) {

        int width =0,height = 0;
        tv.setId(UniqueIDGenerator.generateViewId());
       /* tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);*/
        //Width
        //Width
        if (temp.getWidth().equalsIgnoreCase("wrap_content")) {
            //tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getWidth().equalsIgnoreCase("match_parent")) {
            //     tv.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            width = LinearLayout.LayoutParams.MATCH_PARENT;
        }else{
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
        }

        //Height
        if (temp.getHeight().equalsIgnoreCase("wrap_content")) {
            //tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            height = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getHeight().equalsIgnoreCase("match_parent")) {
            //tv.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
            height = LinearLayout.LayoutParams.MATCH_PARENT;
        }else{
            height = LinearLayout.LayoutParams.WRAP_CONTENT;
        }


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);
        //Height


        //padding
        if (!TextUtils.isEmpty(temp.getPadding())) {
            String dimension = temp.getPadding();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setPadding(padding, padding, padding, padding);
            }
        }


        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
             //   LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(padding, padding, padding, padding);
               // tv.setLayoutParams(params);
            }
        }


        //gravity
        if (!TextUtils.isEmpty(temp.getGravity())) {
            String gravity = temp.getGravity();
            if (gravity.equalsIgnoreCase("center")) {
                tv.setGravity(Gravity.CENTER);
            } else if (gravity.equalsIgnoreCase("center_horizontal")) {
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("center_vertical")) {
                tv.setGravity(Gravity.CENTER_VERTICAL);

            } else if (gravity.equalsIgnoreCase("clip_horizontal")) {
                tv.setGravity(Gravity.CLIP_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("clip_vertical")) {
                tv.setGravity(Gravity.CLIP_VERTICAL);

            } else if (gravity.equalsIgnoreCase("end")) {
                tv.setGravity(Gravity.END);

            } else if (gravity.equalsIgnoreCase("left")) {
                tv.setGravity(Gravity.LEFT);

            } else if (gravity.equalsIgnoreCase("right")) {
                tv.setGravity(Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("start")) {
                tv.setGravity(Gravity.START);

            } else if (gravity.equalsIgnoreCase("top")) {
                tv.setGravity(Gravity.TOP);

            } else if (gravity.equalsIgnoreCase("fill_horizontal")) {
                tv.setGravity(Gravity.FILL_HORIZONTAL);

            } else if (gravity.equalsIgnoreCase("fill_vertical")) {
                tv.setGravity(Gravity.FILL_VERTICAL);

            } else if (gravity.equalsIgnoreCase("fill")) {
                tv.setGravity(Gravity.FILL);

            }else if (gravity.equalsIgnoreCase("right|bottom")) {
                 tv.setGravity(Gravity.RIGHT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("left|bottom")) {
                tv.setGravity(Gravity.LEFT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("top|right")) {
                tv.setGravity(Gravity.TOP|Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("top|left")) {
                tv.setGravity(Gravity.TOP|Gravity.LEFT);

            }


        }
        // Name
        if (!TextUtils.isEmpty(temp.getWidgetLabel())) {
            tv.setText(temp.getWidgetLabel());
        }
        if(!TextUtils.isEmpty(temp.getColor())){
            tv.setBackgroundColor(Color.parseColor(MobileApplication.getInstance().getColorHash().get(temp.getColor())));
            // mTextView.setTextColor(Color.parseColor("#bdbdbd"));
        }
        tv.setLayoutParams(layoutParams);
        return tv;
    }



    public ImageView setImageViewProperties(ImageView tv, WidgetPropertiesDTO temp) {
        int width =0,height = 0;

        tv.setId(UniqueIDGenerator.generateViewId());
       /* LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(layoutParams);*/
        //Width
//Width
       /* if (temp.getWidth().equalsIgnoreCase("wrap_content")) {
            //tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getWidth().equalsIgnoreCase("match_parent")) {
            //     tv.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
            width = LinearLayout.LayoutParams.MATCH_PARENT;
        }else*/{
            width = LinearLayout.LayoutParams.WRAP_CONTENT;
        }

        //Height
        /*if (temp.getHeight().equalsIgnoreCase("wrap_content")) {
            //tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            height = LinearLayout.LayoutParams.WRAP_CONTENT;
        } else if (temp.getHeight().equalsIgnoreCase("match_parent")) {
            //tv.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
            height = LinearLayout.LayoutParams.MATCH_PARENT;
        }else*/{
            height = LinearLayout.LayoutParams.WRAP_CONTENT;
        }


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);
        //Height

        //Height


        //padding
        if (!TextUtils.isEmpty(temp.getPadding())) {
            String dimension = temp.getPadding();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setPadding(padding, padding, padding, padding);
            }
        }


        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
              //  LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(padding, padding, padding, padding);
              //  tv.setLayoutParams(params);
            }
        }


        //gravity
        if (!TextUtils.isEmpty(temp.getGravity())) {
            String gravity = temp.getGravity();
            if (gravity.equalsIgnoreCase("center")) {
                layoutParams.gravity = (Gravity.CENTER);
            } else if (gravity.equalsIgnoreCase("center_horizontal")) {
                layoutParams.gravity = (Gravity.CENTER_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("center_vertical")) {
                layoutParams.gravity = (Gravity.CENTER_VERTICAL);

            }  else if (gravity.equalsIgnoreCase("end")) {
                layoutParams.gravity = (Gravity.END);

            } else if (gravity.equalsIgnoreCase("left")) {
                layoutParams.gravity = (Gravity.LEFT);

            } else if (gravity.equalsIgnoreCase("right")) {
                layoutParams.gravity = (Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("start")) {
                layoutParams.gravity = (Gravity.START);

            } else if (gravity.equalsIgnoreCase("top")) {
                layoutParams.gravity = (Gravity.TOP);

            } else if (gravity.equalsIgnoreCase("fill_horizontal")) {
                layoutParams.gravity = (Gravity.FILL_HORIZONTAL);

            } else if (gravity.equalsIgnoreCase("fill_vertical")) {
                layoutParams.gravity = (Gravity.FILL_VERTICAL);

            } else if (gravity.equalsIgnoreCase("fill")) {
                layoutParams.gravity = (Gravity.FILL);

            }else if (gravity.equalsIgnoreCase("right|bottom")) {
                layoutParams.gravity = (Gravity.RIGHT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("left|bottom")) {
                layoutParams.gravity = (Gravity.LEFT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("top|right")) {
                layoutParams.gravity = (Gravity.TOP|Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("top|left")) {
                layoutParams.gravity = (Gravity.TOP|Gravity.LEFT);

            }


        }
        // src
        if(!TextUtils.isEmpty(temp.getWidgetDrawable())){
          tv.setImageResource(R.drawable.ic_launcher);


        }

        if(!TextUtils.isEmpty(temp.getColor())){
            tv.setBackgroundColor(Color.parseColor(MobileApplication.getInstance().getColorHash().get(temp.getColor())));
            // mTextView.setTextColor(Color.parseColor("#bdbdbd"));
        }
        tv.setLayoutParams(layoutParams);

        return tv;
    }


    public ImageButton setImageButtonProperties(ImageButton tv, WidgetPropertiesDTO temp) {
        int width =0,height = 0;
        width = LinearLayout.LayoutParams.WRAP_CONTENT;
        height = LinearLayout.LayoutParams.WRAP_CONTENT;

        tv.setId(UniqueIDGenerator.generateViewId());
       /* LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(layoutParams);*/
        //Width
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);

        //Height


        //padding
        if (!TextUtils.isEmpty(temp.getPadding())) {
            String dimension = temp.getPadding();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setPadding(padding, padding, padding, padding);
            }
        }


        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
             //   LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(padding, padding, padding, padding);

            }
        }


        //gravity
        if (!TextUtils.isEmpty(temp.getGravity())) {
            String gravity = temp.getGravity();
            if (gravity.equalsIgnoreCase("center")) {
                layoutParams.gravity = (Gravity.CENTER);
            } else if (gravity.equalsIgnoreCase("center_horizontal")) {
                layoutParams.gravity = (Gravity.CENTER_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("center_vertical")) {
                layoutParams.gravity = (Gravity.CENTER_VERTICAL);

            }  else if (gravity.equalsIgnoreCase("end")) {
                layoutParams.gravity = (Gravity.END);

            } else if (gravity.equalsIgnoreCase("left")) {
                layoutParams.gravity = (Gravity.LEFT);

            } else if (gravity.equalsIgnoreCase("right")) {
                layoutParams.gravity = (Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("start")) {
                layoutParams.gravity = (Gravity.START);

            } else if (gravity.equalsIgnoreCase("top")) {
                layoutParams.gravity = (Gravity.TOP);

            } else if (gravity.equalsIgnoreCase("fill_horizontal")) {
                layoutParams.gravity = (Gravity.FILL_HORIZONTAL);

            } else if (gravity.equalsIgnoreCase("fill_vertical")) {
                layoutParams.gravity = (Gravity.FILL_VERTICAL);

            } else if (gravity.equalsIgnoreCase("fill")) {
                layoutParams.gravity = (Gravity.FILL);

            }else if (gravity.equalsIgnoreCase("right|bottom")) {
                layoutParams.gravity = (Gravity.RIGHT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("left|bottom")) {
                layoutParams.gravity = (Gravity.LEFT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("top|right")) {
                layoutParams.gravity = (Gravity.TOP|Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("top|left")) {
                layoutParams.gravity = (Gravity.TOP|Gravity.LEFT);

            }


        }
        // src
        if(!TextUtils.isEmpty(temp.getWidgetDrawable())){
            tv.setImageResource(R.drawable.ic_launcher);


        }

        if(!TextUtils.isEmpty(temp.getColor())){
            tv.setBackgroundColor(Color.parseColor(MobileApplication.getInstance().getColorHash().get(temp.getColor())));
            // mTextView.setTextColor(Color.parseColor("#bdbdbd"));
        }
        tv.setLayoutParams(layoutParams);
        return tv;
    }


    public RatingBar setRatingBarProperties(RatingBar tv, WidgetPropertiesDTO temp) {

        int width =0,height = 0;
        width = LinearLayout.LayoutParams.WRAP_CONTENT;
        height = LinearLayout.LayoutParams.WRAP_CONTENT;
        tv.setId(UniqueIDGenerator.generateViewId());
        //Width
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width,height);


     /*   //padding
        if (!TextUtils.isEmpty(temp.getPadding())) {
            String dimension = temp.getPadding();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                tv.setPadding(padding, padding, padding, padding);
            }
        }
*/

        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
              //  LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(padding, padding, padding, padding);
            //    tv.setLayoutParams(params);
            }
        }

        if(!TextUtils.isEmpty(temp.getColor())){
            tv.setBackgroundColor(Color.parseColor(MobileApplication.getInstance().getColorHash().get(temp.getColor())));
            // mTextView.setTextColor(Color.parseColor("#bdbdbd"));
        }
        //gravity
        if (!TextUtils.isEmpty(temp.getGravity())) {
            String gravity = temp.getGravity();
            if (gravity.equalsIgnoreCase("center")) {
                layoutParams.gravity = (Gravity.CENTER);
            } else if (gravity.equalsIgnoreCase("center_horizontal")) {
                layoutParams.gravity = (Gravity.CENTER_HORIZONTAL);
            } else if (gravity.equalsIgnoreCase("center_vertical")) {
                layoutParams.gravity = (Gravity.CENTER_VERTICAL);

            }  else if (gravity.equalsIgnoreCase("end")) {
                layoutParams.gravity = (Gravity.END);

            } else if (gravity.equalsIgnoreCase("left")) {
                layoutParams.gravity = (Gravity.LEFT);

            } else if (gravity.equalsIgnoreCase("right")) {
                layoutParams.gravity = (Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("start")) {
                layoutParams.gravity = (Gravity.START);

            } else if (gravity.equalsIgnoreCase("top")) {
                layoutParams.gravity = (Gravity.TOP);

            } else if (gravity.equalsIgnoreCase("fill_horizontal")) {
                layoutParams.gravity = (Gravity.FILL_HORIZONTAL);

            } else if (gravity.equalsIgnoreCase("fill_vertical")) {
                layoutParams.gravity = (Gravity.FILL_VERTICAL);

            } else if (gravity.equalsIgnoreCase("fill")) {
                layoutParams.gravity = (Gravity.FILL);

            }else if (gravity.equalsIgnoreCase("right|bottom")) {
                layoutParams.gravity = (Gravity.RIGHT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("left|bottom")) {
                layoutParams.gravity = (Gravity.LEFT|Gravity.BOTTOM);

            } else if (gravity.equalsIgnoreCase("top|right")) {
                layoutParams.gravity = (Gravity.TOP|Gravity.RIGHT);

            } else if (gravity.equalsIgnoreCase("top|left")) {
                layoutParams.gravity = (Gravity.TOP|Gravity.LEFT);

            }

            tv.setNumStars(4);
            tv.setStepSize(1.0f);
            tv.setRating(2f);

        }
        tv.setLayoutParams(layoutParams);
        return tv;
    }

    private LinearLayout.LayoutParams setGravity(LinearLayout.LayoutParams layoutParams,String gravity){
      if(!TextUtils.isEmpty(gravity)) {
          if (gravity.equalsIgnoreCase("center")) {
              layoutParams.gravity = (Gravity.CENTER);
          } else if (gravity.equalsIgnoreCase("center_horizontal")) {
              layoutParams.gravity = (Gravity.CENTER_HORIZONTAL);
          } else if (gravity.equalsIgnoreCase("center_vertical")) {
              layoutParams.gravity = (Gravity.CENTER_VERTICAL);

          } else if (gravity.equalsIgnoreCase("end")) {
              layoutParams.gravity = (Gravity.END);

          } else if (gravity.equalsIgnoreCase("left")) {
              layoutParams.gravity = (Gravity.LEFT);

          } else if (gravity.equalsIgnoreCase("right")) {
              layoutParams.gravity = (Gravity.RIGHT);

          } else if (gravity.equalsIgnoreCase("start")) {
              layoutParams.gravity = (Gravity.START);

          } else if (gravity.equalsIgnoreCase("top")) {
              layoutParams.gravity = (Gravity.TOP);

          } else if (gravity.equalsIgnoreCase("fill_horizontal")) {
              layoutParams.gravity = (Gravity.FILL_HORIZONTAL);

          } else if (gravity.equalsIgnoreCase("fill_vertical")) {
              layoutParams.gravity = (Gravity.FILL_VERTICAL);

          } else if (gravity.equalsIgnoreCase("fill")) {
              layoutParams.gravity = (Gravity.FILL);

          } else if (gravity.equalsIgnoreCase("right|bottom")) {
              layoutParams.gravity = (Gravity.RIGHT|Gravity.BOTTOM);

          } else if (gravity.equalsIgnoreCase("left|bottom")) {
              layoutParams.gravity = (Gravity.LEFT|Gravity.BOTTOM);

          } else if (gravity.equalsIgnoreCase("top|right")) {
              layoutParams.gravity = (Gravity.TOP|Gravity.RIGHT);

          } else if (gravity.equalsIgnoreCase("top|left")) {
              layoutParams.gravity = (Gravity.TOP|Gravity.LEFT);

          }
      }
        return  layoutParams;
    }
}
