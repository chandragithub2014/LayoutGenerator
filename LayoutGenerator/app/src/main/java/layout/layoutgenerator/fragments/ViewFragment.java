package layout.layoutgenerator.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import java.util.ArrayList;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        mContainerId = container.getId();
         v  = inflater.inflate(R.layout.widgetlayout, container, false);
        widgetPropertiesDTO = new WidgetPropertiesDTO();
        initializeViews(v);
        populateSpinnerDefaultValues();

        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return  v;
    }

    private void initializeViews(View v){
        screenWidth = (EditText)v.findViewById(R.id.width);
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
        RelativeLayout footerLayout = (RelativeLayout)v.findViewById(R.id.footerlayout);
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
                    if(!TextUtils.isEmpty(customWidth.getText().toString())){
                        widgetPropertiesDTO.setWidth(customWidth.getText().toString() + "dp");
                    }
                    else{
                        widgetPropertiesDTO.setWidth("50"+"dp");
                    }
                }else {
                    widgetPropertiesDTO.setWidth(widthSelector.getItemAtPosition(position).toString());
                }
                break;
            case R.id.height_selector:
                if(position==2){
                    if(!TextUtils.isEmpty(customHeight.getText().toString())){
                        widgetPropertiesDTO.setHeight(customHeight.getText().toString()+"dp");
                    }
                    else{
                        widgetPropertiesDTO.setHeight("50" + "dp");
                    }
                }else {
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

        calculateScreenWidth();
    //    fetchSelectedWidget();
     //   calculateWidgetWidth();
     //   calculateWidgetHeight();
        calculatePaddingAndMargin();
     //   calculateGravity();

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
            if(MobileApplication.getInstance().getWidgetList().size()>0){
                List<WidgetPropertiesDTO> tempList = MobileApplication.getInstance().getWidgetList();
                tempList.add(widgetPropertiesDTO);
                MobileApplication.getInstance().setWidgetList(tempList);
            }else{
                List<WidgetPropertiesDTO> tempList = new ArrayList<WidgetPropertiesDTO>();
                tempList.add(widgetPropertiesDTO);
                MobileApplication.getInstance().setWidgetList(tempList);
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
