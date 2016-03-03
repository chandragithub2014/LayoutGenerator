package layout.layoutgenerator.preview;

import android.content.Context;
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


        tv.setId(UniqueIDGenerator.generateViewId());
        //Width
        if (temp.getWidth().equalsIgnoreCase("wrap_content")) {
            tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        } else if (temp.getWidth().equalsIgnoreCase("match_parent")) {
            tv.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        } else {
            String dimension = temp.getWidth();
            String alteredString = dimension.substring(0, dimension.length() - 2);
            tv.setWidth(Integer.parseInt(alteredString));
        }

        //Height
        if (temp.getHeight().equalsIgnoreCase("wrap_content")) {
            tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        } else if (temp.getHeight().equalsIgnoreCase("match_parent")) {
            tv.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        } else {
            String dimension = temp.getHeight();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                tv.setHeight(Integer.parseInt(alteredString));
            }
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


        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(padding, padding, padding, padding);
                tv.setLayoutParams(params);
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

            }


        }
        // Name
        if (!TextUtils.isEmpty(temp.getWidgetLabel())) {
            tv.setText(temp.getWidgetLabel());
        }
        return tv;
    }



    public EditText setEditTextProperties(EditText tv, WidgetPropertiesDTO temp) {


        tv.setId(UniqueIDGenerator.generateViewId());
        //Width
        if (temp.getWidth().equalsIgnoreCase("wrap_content")) {
            tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        } else if (temp.getWidth().equalsIgnoreCase("match_parent")) {
            tv.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        } else {
            String dimension = temp.getWidth();
            String alteredString = dimension.substring(0, dimension.length() - 2);
            tv.setWidth(Integer.parseInt(alteredString));
        }

        //Height
        if (temp.getHeight().equalsIgnoreCase("wrap_content")) {
            tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        } else if (temp.getHeight().equalsIgnoreCase("match_parent")) {
            tv.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        } else {
            String dimension = temp.getHeight();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                tv.setHeight(Integer.parseInt(alteredString));
            }
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


        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(padding, padding, padding, padding);
                tv.setLayoutParams(params);
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

            }


        }
        // Name
        if (!TextUtils.isEmpty(temp.getWidgetLabel())) {
            tv.setText(temp.getWidgetLabel());
        }
        return tv;
    }


    public Button setButtonProperties(Button tv, WidgetPropertiesDTO temp) {


        tv.setId(UniqueIDGenerator.generateViewId());
        //Width
        if (temp.getWidth().equalsIgnoreCase("wrap_content")) {
            tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        } else if (temp.getWidth().equalsIgnoreCase("match_parent")) {
            tv.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        } else {
            String dimension = temp.getWidth();
            String alteredString = dimension.substring(0, dimension.length() - 2);
            tv.setWidth(Integer.parseInt(alteredString));
        }

        //Height
        if (temp.getHeight().equalsIgnoreCase("wrap_content")) {
            tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        } else if (temp.getHeight().equalsIgnoreCase("match_parent")) {
            tv.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        } else {
            String dimension = temp.getHeight();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                tv.setHeight(Integer.parseInt(alteredString));
            }
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


        //margin
        if (!TextUtils.isEmpty(temp.getMargin())) {
            String dimension = temp.getMargin();
            if (!TextUtils.isDigitsOnly(dimension)) {
                String alteredString = dimension.substring(0, dimension.length() - 2);
                int padding = Integer.parseInt(alteredString);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(padding, padding, padding, padding);
                tv.setLayoutParams(params);
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

            }


        }
        // Name
        if (!TextUtils.isEmpty(temp.getWidgetLabel())) {
            tv.setText(temp.getWidgetLabel());
        }
        return tv;
    }


    public Spinner setSpinnerProperties(Spinner tv, WidgetPropertiesDTO temp) {


        tv.setId(UniqueIDGenerator.generateViewId());
        //Width
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(layoutParams);

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
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(padding, padding, padding, padding);
                tv.setLayoutParams(params);
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

            }


        }

        return tv;
    }

    public CheckBox setCheckBoxProperties(CheckBox tv, WidgetPropertiesDTO temp) {


        tv.setId(UniqueIDGenerator.generateViewId());
        tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);

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
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(padding, padding, padding, padding);
                tv.setLayoutParams(params);
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

            }


        }
        // Name
        if (!TextUtils.isEmpty(temp.getWidgetLabel())) {
            tv.setText(temp.getWidgetLabel());
        }
        return tv;
    }



    public RadioButton setRadioProperties(RadioButton tv, WidgetPropertiesDTO temp) {


        tv.setId(UniqueIDGenerator.generateViewId());
        tv.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //Width

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
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(padding, padding, padding, padding);
                tv.setLayoutParams(params);
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

            }


        }
        // Name
        if (!TextUtils.isEmpty(temp.getWidgetLabel())) {
            tv.setText(temp.getWidgetLabel());
        }
        return tv;
    }



    public ImageView setImageViewProperties(ImageView tv, WidgetPropertiesDTO temp) {


        tv.setId(UniqueIDGenerator.generateViewId());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(layoutParams);
        //Width

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
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(padding, padding, padding, padding);
                tv.setLayoutParams(params);
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
        // src
        if(!TextUtils.isEmpty(temp.getWidgetDrawable())){
          tv.setImageResource(R.drawable.ic_launcher);


        }

        return tv;
    }


    public ImageButton setImageButtonProperties(ImageButton tv, WidgetPropertiesDTO temp) {


        tv.setId(UniqueIDGenerator.generateViewId());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(layoutParams);
        //Width

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
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(padding, padding, padding, padding);
                tv.setLayoutParams(params);
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
        // src
        if(!TextUtils.isEmpty(temp.getWidgetDrawable())){
            tv.setImageResource(R.drawable.ic_launcher);


        }

        return tv;
    }


    public RatingBar setRatingBarProperties(RatingBar tv, WidgetPropertiesDTO temp) {


        tv.setId(UniqueIDGenerator.generateViewId());
        //Width
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(layoutParams);

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
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(padding, padding, padding, padding);
                tv.setLayoutParams(params);
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

            tv.setNumStars(4);
            tv.setStepSize(1.0f);
            tv.setRating(2f);

        }

        return tv;
    }
}
