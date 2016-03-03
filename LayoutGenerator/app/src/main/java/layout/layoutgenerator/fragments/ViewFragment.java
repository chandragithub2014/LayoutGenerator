package layout.layoutgenerator.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import layout.layoutgenerator.Application.MobileApplication;
import layout.layoutgenerator.DTO.WidgetPropertiesDTO;
import layout.layoutgenerator.R;
import layout.layoutgenerator.utils.UniqueIDGenerator;
import layout.layoutgenerator.utils.XMLGenerator;

/**
 * Created by CHANDRASAIMOHAN on 2/1/2016.
 */
public class ViewFragment extends Fragment implements  View.OnClickListener, AdapterView.OnItemSelectedListener{

    EditText screenWidth,customWidth,customHeight,widgetPadding,widgetMargin,widgetLabel;
    Spinner widgetSelector,widthSelector,heightSelector,gravitySelector;
    Button finish_btn,next_btn;
    WidgetPropertiesDTO widgetPropertiesDTO;

    String selectedWidget = "";
    View v = null;

    int mContainerId = -1;
    Toolbar mtoolBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        mContainerId = container.getId();
         v  = inflater.inflate(R.layout.widgetlayout, container, false);
        mtoolBar = (Toolbar)((AppCompatActivity) getActivity()).findViewById(R.id.toolbar);
        TextView titleBar = (TextView)mtoolBar.findViewById(R.id.title);
        titleBar.setText("VERTICAL LINEAR LAYOUT  GENERATOR");
        widgetPropertiesDTO = new WidgetPropertiesDTO();
        initializeViews(v);
       checkAndPopulateWithDataForPosition();
     //  populateSpinnerDefaultValues();

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return  v;
    }

    private void initializeViews(View v){
        customWidth  = (EditText)v.findViewById(R.id.custom_width);
        customHeight = (EditText)v.findViewById(R.id.custom_height);
        widgetPadding  = (EditText)v.findViewById(R.id.padding);
        widgetMargin  = (EditText)v.findViewById(R.id.margin);
        widgetLabel  = (EditText)v.findViewById(R.id.label);

        widgetSelector = (Spinner)v.findViewById(R.id.widget_selector);
        widthSelector = (Spinner)v.findViewById(R.id.width_selector);
        heightSelector = (Spinner)v.findViewById(R.id.height_selector);
        gravitySelector = (Spinner)v.findViewById(R.id.gravity_selector);

        //FooterLayout
        LinearLayout footerLayout = (LinearLayout)v.findViewById(R.id.footerlayout);
        finish_btn = (Button)footerLayout.findViewById(R.id.finish_btn);
        next_btn = (Button)footerLayout.findViewById(R.id.next_btn);

        //Listeners
        finish_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);

        widgetSelector.setOnItemSelectedListener(this);
        widthSelector.setOnItemSelectedListener(this);
        heightSelector.setOnItemSelectedListener(this);
        gravitySelector.setOnItemSelectedListener(this);

    }
private void checkAndPopulateWithDataForPosition(){
    int position = MobileApplication.getInstance().getWidgetPos();
    Log.d("ViewFragment","Position::::"+position);
    position = position + 1;
   // Log.d("ViewFragment","Incremented Position::::"+position);
    WidgetPropertiesDTO value = MobileApplication.getInstance().getWidgetInfoMap().get(position);
    if(value!=null){
        //Populate WidgetName
        ArrayList<String> list=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.widgetselector)) );  // your array id of string resource
        if(!TextUtils.isEmpty(value.getWidgetName())) {
            int pos = list.indexOf(value.getWidgetName());
            widgetSelector.setSelection(pos);
        }
        list=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.dimension)) );  // your array id of string resource
        if(!TextUtils.isEmpty(value.getWidth())){
            Log.d("ViewFragment","Width:::::"+value.getWidth());
            if(!value.getWidth().equalsIgnoreCase("match_parent") || !value.getWidth().equalsIgnoreCase("wrap_content")){
                String dimension = value.getWidth();
                String alteredString = dimension.substring(0,dimension.length()-2);
                customWidth.setVisibility(View.VISIBLE);
                customWidth.setText(alteredString);
                int pos = list.indexOf("Custom");
                widthSelector.setSelection(pos);
            }else{
                int pos = list.indexOf(value.getWidth());
                widthSelector.setSelection(pos);
            }
        }

        if(!TextUtils.isEmpty(value.getHeight())){
            Log.d("ViewFragment","Height:::::"+value.getHeight());
            if(!value.getHeight().equalsIgnoreCase("match_parent") || !value.getHeight().equalsIgnoreCase("wrap_content")){
                String dimension = value.getHeight();
                String alteredString = dimension.substring(0,dimension.length()-2);
                customHeight.setVisibility(View.VISIBLE);
                customHeight.setText(alteredString);
                int pos = list.indexOf("Custom");
                heightSelector.setSelection(pos);
            }else{
                int pos = list.indexOf(value.getHeight());
                heightSelector.setSelection(pos);
            }
        }

        //Gravity
        list=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.gravityselector)) );  // your array id of string resource
        if(!TextUtils.isEmpty(value.getGravity())) {
            int pos = list.indexOf(value.getGravity());
            gravitySelector.setSelection(pos);
        }
        String padding = value.getPadding();
        String alteredString = padding.substring(0, padding.length() - 2);
        widgetPadding.setText(alteredString);

        String margin = value.getMargin();
        String alteredMarginString = margin.substring(0, margin.length() - 2);
        widgetMargin.setText(alteredMarginString);

        if(!TextUtils.isEmpty(value.getWidgetLabel())){
            widgetLabel.setText(value.getWidgetLabel());
        }


    }else{
        populateSpinnerDefaultValues();
    }

}
    private void populateSpinnerDefaultValues(){
        widgetPropertiesDTO.setWidgetName(widgetSelector.getItemAtPosition(0).toString());
        widgetPropertiesDTO.setWidth(widthSelector.getItemAtPosition(0).toString());
        widgetPropertiesDTO.setHeight(heightSelector.getItemAtPosition(0).toString());
        widgetPropertiesDTO.setGravity(gravitySelector.getItemAtPosition(0).toString());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.finish_btn:
                createWidgetProperties();
                try {
                    String generatedXML = XMLGenerator.getInstance().generateLayoutUsingXMLSerializer();
                    Log.d("ViewFragment", "GeneratedXML::::::"+generatedXML);
                    getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(mContainerId,ResultFragment.newInstance(generatedXML,"")).commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.next_btn:
                createWidgetProperties();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(mContainerId, new ViewFragment()).addToBackStack(null)
                        .commit();
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.widget_selector:
                widgetPropertiesDTO.setWidgetName( widgetSelector.getItemAtPosition(position).toString());
                break;
            case R.id.width_selector:

                if(position==2){
                    customWidth.setVisibility(View.VISIBLE);
                  /*  if(!TextUtils.isEmpty(customWidth.getText().toString())){
                        widgetPropertiesDTO.setWidth(customWidth.getText().toString() + "dp");
                    }
                    else{
                        widgetPropertiesDTO.setWidth("50"+"dp");
                    }*/
                }else {
                    customWidth.setVisibility(View.GONE);
                    widgetPropertiesDTO.setWidth(widthSelector.getItemAtPosition(position).toString());
                }
                break;
            case R.id.height_selector:
                if(position==2){
                    customHeight.setVisibility(View.VISIBLE);
                /*    if(!TextUtils.isEmpty(customHeight.getText().toString())){
                        widgetPropertiesDTO.setHeight(customHeight.getText().toString()+"dp");
                    }
                    else{
                        widgetPropertiesDTO.setHeight("50" + "dp");
                    }*/
                }else {
                    customHeight.setVisibility(View.GONE);
                    widgetPropertiesDTO.setHeight(heightSelector.getItemAtPosition(position).toString());
                }
                break;
            case R.id.gravity_selector:
                String selectedGravity =   gravitySelector.getItemAtPosition(position).toString();
                widgetPropertiesDTO.setGravity(selectedGravity);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void createWidgetProperties(){

  //      calculateScreenWidth();
    //    fetchSelectedWidget();
     //   calculateWidgetWidth();
     //   calculateWidgetHeight();
        calculatePaddingAndMargin();
     //   calculateGravity();
            if(!TextUtils.isEmpty(customWidth.getText().toString())){
                widgetPropertiesDTO.setWidth(customWidth.getText().toString()+"dp");
            }

        if(!TextUtils.isEmpty(customHeight.getText().toString())){
            widgetPropertiesDTO.setHeight(customHeight.getText().toString()+"dp");
        }


        if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetName())
                && !TextUtils.isEmpty(widgetPropertiesDTO.getWidth())
                && !TextUtils.isEmpty(widgetPropertiesDTO.getHeight())
                && !TextUtils.isEmpty(widgetPropertiesDTO.getPadding())
                && !TextUtils.isEmpty(widgetPropertiesDTO.getMargin())
                && !TextUtils.isEmpty(widgetPropertiesDTO.getGravity())
                ){
            if(!TextUtils.isEmpty(widgetLabel.getText().toString())){
                widgetPropertiesDTO.setWidgetLabel(widgetLabel.getText().toString());
            }else{
                widgetPropertiesDTO.setWidgetLabel(widgetPropertiesDTO.getWidgetName());
            }
            int viewID = UniqueIDGenerator.generateViewId();
            widgetPropertiesDTO.setWidgetId(""+widgetPropertiesDTO.getWidgetName()+viewID);
            int widgetPos = MobileApplication.getInstance().getWidgetPos();
            widgetPos = widgetPos + 1;
            MobileApplication.getInstance().setWidgetPos(widgetPos);
            if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetName())) {
                if (widgetPropertiesDTO.getWidgetName().equalsIgnoreCase("ImageView") || widgetPropertiesDTO.getWidgetName().equalsIgnoreCase("ImageButton")) {
                    widgetPropertiesDTO.setWidgetDrawable("ic_launcher");
                }
            }
/*            if(MobileApplication.getInstance().getWidgetList().size()>0){
                List<WidgetPropertiesDTO> tempList = MobileApplication.getInstance().getWidgetList();
                tempList.add(widgetPropertiesDTO);
                MobileApplication.getInstance().setWidgetList(tempList);
            }*/
            if(MobileApplication.getInstance().getWidgetInfoMap()!=null && MobileApplication.getInstance().getWidgetInfoMap().size()>0){
                WidgetPropertiesDTO value = MobileApplication.getInstance().getWidgetInfoMap().get(widgetPos);
                if (value != null) {
                    HashMap<Integer,WidgetPropertiesDTO> temp = MobileApplication.getInstance().getWidgetInfoMap();
                    temp.put(widgetPos,widgetPropertiesDTO);
                    MobileApplication.getInstance().setWidgetInfoMap(temp);
                } else {
                    // Key might be present...
                    if (MobileApplication.getInstance().getWidgetInfoMap().containsKey(widgetPos)) {
                        // Okay, there's a key but the value is null
                    } else {
                        // Definitely no such key
                        HashMap<Integer,WidgetPropertiesDTO> temp = MobileApplication.getInstance().getWidgetInfoMap();
                        temp.put(widgetPos,widgetPropertiesDTO);
                        MobileApplication.getInstance().setWidgetInfoMap(temp);
                    }
                }
            } else{
                List<WidgetPropertiesDTO> tempList = new ArrayList<WidgetPropertiesDTO>();
                HashMap<Integer,WidgetPropertiesDTO> temp = new HashMap<Integer,WidgetPropertiesDTO>();
                temp.put(widgetPos, widgetPropertiesDTO);
                MobileApplication.getInstance().setWidgetInfoMap(temp);
               /* tempList.add(widgetPropertiesDTO);
                MobileApplication.getInstance().setWidgetList(tempList);*/
            }
        }

    }


    private void calculateScreenWidth(){
        if(!TextUtils.isEmpty(screenWidth.getText().toString())){
            MobileApplication.getInstance().setScreenWidth(Integer.parseInt(screenWidth.getText().toString()));
        }else{
            MobileApplication.getInstance().setScreenWidth(0);
        }
    }









    private void calculatePaddingAndMargin(){
        if(!TextUtils.isEmpty(widgetPadding.getText().toString())){
            widgetPropertiesDTO.setPadding(widgetPadding.getText().toString()+"dp");
        }else{
            widgetPropertiesDTO.setPadding("0dp");
        }

        if(!TextUtils.isEmpty(widgetMargin.getText().toString())){
            widgetPropertiesDTO.setMargin(widgetMargin.getText().toString() + "dp");
        }else{
            widgetPropertiesDTO.setMargin("0dp");
        }
    }





    private  void generateXML(){
        if(MobileApplication.getInstance().getWidgetList().size()>0){

        }
    }
}
