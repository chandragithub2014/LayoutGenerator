package layout.layoutgenerator.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import layout.layoutgenerator.Application.MobileApplication;
import layout.layoutgenerator.DTO.WidgetPropertiesDTO;
import layout.layoutgenerator.R;
import layout.layoutgenerator.utils.UniqueIDGenerator;
import layout.layoutgenerator.utils.XMLGenerator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerticallyHorizontalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerticallyHorizontalFragment extends Fragment implements  AdapterView.OnItemSelectedListener ,View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int mContainerId = -1;
    View view = null;
    LinearLayout horVertHeader;
    EditText availableWidth,totalWeight;
    LinearLayout widget1View,widget2View,widget3View,widget4View;
    Spinner weight1,weight2,weight3,weight4;
    Spinner widgetSelector1,widgetSelector2,widgetSelector3,widgetSelector4;
    EditText widgetName1,widgetName2,widgetName3,widgetName4;
    Spinner gravitySelector1,gravitySelector2,gravitySelector3,gravitySelector4;
    EditText widgetMargin1,widgetMargin2,widgetMargin3,widgetMargin4;
    EditText widgetPadding1,widgetPadding2,widgetPadding3,widgetPadding4;
    EditText  widgetTextSize1,widgetTextSize2,widgetTextSize3,widgetTextSize4;
    Spinner colorSelector1,colorSelector2,colorSelector3,colorSelector4;

    Button finish_btn,next_btn;
    List<HashMap<Integer,WidgetPropertiesDTO>> colList;
    HashMap<Integer,WidgetPropertiesDTO> colHashMap;
    HashMap<Integer,HashMap<Integer,WidgetPropertiesDTO>> rowHashMap;
    HashMap<Integer,WidgetPropertiesDTO> prepopulatedColMap;
    private boolean isRatingBarSelected = false;
    TextView widget4Label,widget3Label;
    public VerticallyHorizontalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerticallyHorizontalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VerticallyHorizontalFragment newInstance(String param1, String param2) {
        VerticallyHorizontalFragment fragment = new VerticallyHorizontalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        Log.d("VerticallyHorizFragment", "in newInstance()......");
        return fragment;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("TAG", "onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG","onResume()");
        initHeaderView(view);
        initViewForWidget1(view);
        initViewForWidget2(view);
        initViewForWidget3(view);
        initViewForWidget4(view);
        initNextFinishButtons(view);
        checkAndPrepopulateDataForEachRow();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("vhorfra", "calling setRetainInstance(true)");
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("VerticallyHorizFragment", "in onCreate()......");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MobileApplication.getInstance().setIsGeneratorFragment(false);
        mContainerId = container.getId();
        view =  inflater.inflate(R.layout.hortvertmainlayout, container, false);
        Toolbar mtoolBar = (Toolbar)((AppCompatActivity) getActivity()).findViewById(R.id.toolbar);
        TextView titleBar = (TextView)mtoolBar.findViewById(R.id.title);
        titleBar.setText("VERTICALLY HORIZONTAL LINEAR LAYOUT  GENERATOR");
        Log.d("TAG", "onCreateView()");
       /* initHeaderView(view);
        initViewForWidget1(view);
       *//* initViewForWidget2(view);
        initViewForWidget3(view);
        initViewForWidget4(view);*//*
        initNextFinishButtons(view);
       checkAndPrepopulateDataForEachRow();*/
        return view;
    }
    private void initHeaderView(View v){
        horVertHeader = (LinearLayout)v.findViewById(R.id.headerlayout);
        availableWidth  = (EditText)horVertHeader.findViewById(R.id.availableweight);
        totalWeight = (EditText)horVertHeader.findViewById(R.id.totalweight);
    }

    private void initViewForWidget1(View v){
        widget1View = (LinearLayout)v.findViewById(R.id.widget1);
        widget1View.setTag(R.id.tagwidget1);
        weight1 = (Spinner)widget1View.findViewById(R.id.weight_selector);
       weight1.setTag(R.id.widg1weight);
       weight1.setOnItemSelectedListener(this);


        widgetSelector1 = (Spinner)widget1View.findViewById(R.id.widget_selector);
       widgetSelector1.setTag(R.id.widg1type);


        widgetSelector1.setOnItemSelectedListener(this);
        widgetName1 = (EditText)widget1View.findViewById(R.id.label);

        gravitySelector1 = (Spinner)widget1View.findViewById(R.id.gravity_selector);


        widgetMargin1 = (EditText)widget1View.findViewById(R.id.margin);
        widgetPadding1 = (EditText)widget1View.findViewById(R.id.padding);

        colorSelector1= (Spinner)widget1View.findViewById(R.id.color_selector);
        widgetTextSize1 = (EditText)widget1View.findViewById(R.id.textsize);

        //margin
 }

    private void initViewForWidget2(View v){
        widget2View = (LinearLayout)v.findViewById(R.id.widget2);
        widget2View.setTag(R.id.tagwidget2);
        weight2 = (Spinner)widget2View.findViewById(R.id.weight_selector);
     weight2.setTag(R.id.widg2weight);
     weight2.setOnItemSelectedListener(this);


        widgetSelector2 = (Spinner)widget2View.findViewById(R.id.widget_selector);
      //  widgetSelector2.setTag(R.id.widget2);
        widgetSelector2.setOnItemSelectedListener(this);
        widgetSelector2.setTag(R.id.widg2type);
        boolean inBack = MobileApplication.getInstance().isBack();


        widgetName2 = (EditText)widget2View.findViewById(R.id.label);
        gravitySelector2 = (Spinner)widget2View.findViewById(R.id.gravity_selector);
        widgetMargin2 = (EditText)widget2View.findViewById(R.id.margin);
        widgetPadding2 = (EditText)widget2View.findViewById(R.id.padding);

        colorSelector2= (Spinner)widget2View.findViewById(R.id.color_selector);
        widgetTextSize2 = (EditText)widget2View.findViewById(R.id.textsize);
    }

    private void initViewForWidget3(View v){
        widget3View = (LinearLayout)v.findViewById(R.id.widget3);
        widget3View.setTag(R.id.tagwidget3);
        weight3 = (Spinner)widget3View.findViewById(R.id.weight_selector);
        weight3.setTag(R.id.widg3weight);
      weight3.setOnItemSelectedListener(this);


        widgetSelector3 = (Spinner)widget3View.findViewById(R.id.widget_selector);
    //   widgetSelector3.setTag(R.id.widget3);
        widgetSelector3.setTag(R.id.widg3type);
        widgetSelector3.setOnItemSelectedListener(this);



        widgetName3 = (EditText)widget3View.findViewById(R.id.label);
        gravitySelector3 = (Spinner)widget3View.findViewById(R.id.gravity_selector);
        widgetMargin3 = (EditText)widget3View.findViewById(R.id.margin);
        widgetPadding3 = (EditText)widget3View.findViewById(R.id.padding);
         widget3Label = (TextView)v.findViewById(R.id.widgt3label);

        colorSelector3= (Spinner)widget3View.findViewById(R.id.color_selector);
        widgetTextSize3 = (EditText)widget3View.findViewById(R.id.textsize);
   }

    private void initViewForWidget4(View v){
        widget4View = (LinearLayout)v.findViewById(R.id.widget4);
        widget4View.setTag(R.id.tagwidget4);
        weight4 = (Spinner)widget4View.findViewById(R.id.weight_selector);
       weight4.setTag(R.id.widg4weight);
     weight4.setOnItemSelectedListener(this);


        widgetSelector4 = (Spinner)widget4View.findViewById(R.id.widget_selector);
        widgetSelector4.setTag(R.id.widg4type);
        widgetSelector4.setOnItemSelectedListener(this);



        widgetName4 = (EditText)widget4View.findViewById(R.id.label);
        gravitySelector4 = (Spinner)widget4View.findViewById(R.id.gravity_selector);
        widgetMargin4 = (EditText)widget4View.findViewById(R.id.margin);
        widgetPadding4 = (EditText)widget4View.findViewById(R.id.padding);
         widget4Label = (TextView)v.findViewById(R.id.widgt4label);

        colorSelector4= (Spinner)widget4View.findViewById(R.id.color_selector);
        widgetTextSize4 = (EditText)widget4View.findViewById(R.id.textsize);
    }

    private void initNextFinishButtons(View v){
        LinearLayout footerLayout = (LinearLayout)v.findViewById(R.id.footerlayout);
               finish_btn = (Button)footerLayout.findViewById(R.id.finish_btn);
                next_btn = (Button)footerLayout.findViewById(R.id.next_btn);
        finish_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);

    }
 private void populateDataForWidget1(WidgetPropertiesDTO widgetPropertiesDTO){
     ArrayList<String> list=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.widgetselector)) );
     if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetName())) {
         int pos = list.indexOf(widgetPropertiesDTO.getWidgetName());
if(widgetPropertiesDTO.getWidgetName().equalsIgnoreCase("RatingBar")){
    isRatingBarSelected = true;
}
         widgetSelector1.setSelection(pos);
     }
     if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetLabel())){
         widgetName1.setText(widgetPropertiesDTO.getWidgetLabel());
     }
     ArrayList<String> weightList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.weightselector)) );
     if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetWeight())) {
           int pos = weightList.indexOf(widgetPropertiesDTO.getWidgetWeight());
         weight1.setSelection(pos);
     }
     ArrayList<String> gravityList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.gravityselector)) );
     if(!TextUtils.isEmpty(widgetPropertiesDTO.getGravity())){
         int pos = gravityList.indexOf(widgetPropertiesDTO.getGravity());
         gravitySelector1.setSelection(pos);
     }

     if(!TextUtils.isEmpty(widgetPropertiesDTO.getMargin())){
          String margin = widgetPropertiesDTO.getMargin();
         String alteredString = margin.substring(0, margin.length() - 2);
         widgetMargin1.setText(alteredString);
     }

     if(!TextUtils.isEmpty(widgetPropertiesDTO.getPadding())){
         String padding = widgetPropertiesDTO.getPadding();
         String alteredString = padding.substring(0, padding.length() - 2);
         widgetPadding1.setText(alteredString);
     }

     ArrayList<String> colorList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.colorselector)) );
     if(!TextUtils.isEmpty(widgetPropertiesDTO.getColor())){
         int pos = colorList.indexOf(widgetPropertiesDTO.getColor());
         colorSelector1.setSelection(pos);
     }

     if(!TextUtils.isEmpty(widgetPropertiesDTO.getTextSize())) {
         String padding = widgetPropertiesDTO.getTextSize();
         String alteredString = padding.substring(0, padding.length() - 2);
         widgetTextSize1.setText(alteredString);
     }


 }

    private void populateDataForWidget2(WidgetPropertiesDTO widgetPropertiesDTO){
        ArrayList<String> list=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.widgetselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetName())) {
            int pos = list.indexOf(widgetPropertiesDTO.getWidgetName());
            widgetSelector2.setSelection(pos);
        }

        if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetLabel())){
            widgetName2.setText(widgetPropertiesDTO.getWidgetLabel());
        }

        ArrayList<String> weightList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.weightselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetWeight())) {
            int pos = weightList.indexOf(widgetPropertiesDTO.getWidgetWeight());
            weight2.setSelection(pos);
        }


        ArrayList<String> gravityList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.gravityselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getGravity())){
            int pos = gravityList.indexOf(widgetPropertiesDTO.getGravity());
            gravitySelector2.setSelection(pos);
        }


        if(!TextUtils.isEmpty(widgetPropertiesDTO.getMargin())){
            String margin = widgetPropertiesDTO.getMargin();
            String alteredString = margin.substring(0, margin.length() - 2);
            widgetMargin2.setText(alteredString);
        }

        if(!TextUtils.isEmpty(widgetPropertiesDTO.getPadding())){
            String padding = widgetPropertiesDTO.getPadding();
            String alteredString = padding.substring(0, padding.length() - 2);
            widgetPadding2.setText(alteredString);
        }


        ArrayList<String> colorList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.colorselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getColor())){
            int pos = colorList.indexOf(widgetPropertiesDTO.getColor());
            colorSelector2.setSelection(pos);
        }

        if(!TextUtils.isEmpty(widgetPropertiesDTO.getTextSize())) {
            String padding = widgetPropertiesDTO.getTextSize();
            String alteredString = padding.substring(0, padding.length() - 2);
            widgetTextSize2.setText(alteredString);
        }
    }

    private void populateDataForWidget3(WidgetPropertiesDTO widgetPropertiesDTO){
        ArrayList<String> list=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.widgetselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetName())) {
            int pos = list.indexOf(widgetPropertiesDTO.getWidgetName());
            widgetSelector3.setSelection(pos);
        }
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetLabel())){
            widgetName3.setText(widgetPropertiesDTO.getWidgetLabel());
        }

        ArrayList<String> weightList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.weightselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetWeight())) {
            int pos = weightList.indexOf(widgetPropertiesDTO.getWidgetWeight());
            weight3.setSelection(pos);
        }


        ArrayList<String> gravityList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.gravityselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getGravity())){
            int pos = gravityList.indexOf(widgetPropertiesDTO.getGravity());
            gravitySelector3.setSelection(pos);
        }

        if(!TextUtils.isEmpty(widgetPropertiesDTO.getMargin())){
            String margin = widgetPropertiesDTO.getMargin();
            String alteredString = margin.substring(0, margin.length() - 2);
            widgetMargin3.setText(alteredString);
        }

        if(!TextUtils.isEmpty(widgetPropertiesDTO.getPadding())){
            String padding = widgetPropertiesDTO.getPadding();
            String alteredString = padding.substring(0, padding.length() - 2);
            widgetPadding3.setText(alteredString);
        }


        ArrayList<String> colorList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.colorselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getColor())){
            int pos = colorList.indexOf(widgetPropertiesDTO.getColor());
            colorSelector3.setSelection(pos);
        }

        if(!TextUtils.isEmpty(widgetPropertiesDTO.getTextSize())) {
            String padding = widgetPropertiesDTO.getTextSize();
            String alteredString = padding.substring(0, padding.length() - 2);
            widgetTextSize3.setText(alteredString);
        }
    }

    private void populateDataForWidget4(WidgetPropertiesDTO widgetPropertiesDTO){
        ArrayList<String> list=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.widgetselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetName())) {
            int pos = list.indexOf(widgetPropertiesDTO.getWidgetName());
            widgetSelector4.setSelection(pos);
        }

        if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetLabel())){
            widgetName4.setText(widgetPropertiesDTO.getWidgetLabel());
        }

        ArrayList<String> weightList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.weightselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getWidgetWeight())) {
            int pos = weightList.indexOf(widgetPropertiesDTO.getWidgetWeight());
            weight4.setSelection(pos);
        }


        ArrayList<String> gravityList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.gravityselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getGravity())){
            int pos = gravityList.indexOf(widgetPropertiesDTO.getGravity());
            gravitySelector4.setSelection(pos);
        }

        if(!TextUtils.isEmpty(widgetPropertiesDTO.getMargin())){
            String margin = widgetPropertiesDTO.getMargin();
            Log.d("TAG","Margin:::::"+margin);
            String alteredString = margin.substring(0, margin.length() - 2);
            widgetMargin4.setText(alteredString);
        }

        if(!TextUtils.isEmpty(widgetPropertiesDTO.getPadding())){
            String padding = widgetPropertiesDTO.getPadding();
            String alteredString = padding.substring(0, padding.length() - 2);
            widgetPadding4.setText(alteredString);
        }


        ArrayList<String> colorList=new ArrayList( Arrays.asList(getResources().getStringArray(R.array.colorselector)) );
        if(!TextUtils.isEmpty(widgetPropertiesDTO.getColor())){
            int pos = colorList.indexOf(widgetPropertiesDTO.getColor());
            colorSelector4.setSelection(pos);
        }

        if(!TextUtils.isEmpty(widgetPropertiesDTO.getTextSize())) {
            String padding = widgetPropertiesDTO.getTextSize();
            String alteredString = padding.substring(0, padding.length() - 2);
            widgetTextSize4.setText(alteredString);
        }
    }

    private boolean checkForAvailability(){
        boolean isAvailable = false;
        int rowPosition = MobileApplication.getInstance().getRowPosition();
        Log.d("ViewFragment","Position::::"+rowPosition);
        rowPosition = rowPosition + 1;
        prepopulatedColMap =  MobileApplication.getInstance().getRowHashMap().get(rowPosition);
        if(prepopulatedColMap!=null){
            isAvailable = true;
        }
        return isAvailable;
    }
    //Back Press functionality
    private void checkAndPrepopulateDataForEachRow(){
        int rowPosition = MobileApplication.getInstance().getRowPosition();
        Log.d("ViewFragment","Position::::"+rowPosition);
        rowPosition = rowPosition + 1;

       HashMap<Integer,WidgetPropertiesDTO> value = MobileApplication.getInstance().getRowHashMap().get(rowPosition);
        if(value!=null){
            for (Integer key : value.keySet()) {
                System.out.println("------------------------------------------------"+key);
            //    System.out.println("Iterating or looping map using java5 foreach loop");
             //   System.out.println("key: " + key + " value: " + value.get(key));
                WidgetPropertiesDTO temp  = (WidgetPropertiesDTO) value.get(key);
                Log.d("TAG","TEMP"+temp.getWidgetName()+" "+"VALUE LENGTH:::::"+value.size());
                if(key == 1){
                    //populate colm1
                    populateDataForWidget1(temp);
                }else if(key == 2){
                    //popl col2
                    populateDataForWidget2(temp);
                }else if(key == 3 ){
                    populateDataForWidget3(temp);

                }else if(key == 4){
                    populateDataForWidget4(temp);
                }
                if(isRatingBarSelected){
                    widget3View.setVisibility(View.INVISIBLE);
                    widget4View.setVisibility(View.INVISIBLE);
                    widget4Label.setVisibility(View.INVISIBLE);
                    widget3Label.setVisibility(View.INVISIBLE);
                }
            }



           /* Iterator it = value.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry pair = (Map.Entry)it.next();
              //  System.out.println(pair.getKey() + " = " + pair.getValue());
            //    WidgetPropertiesDTO temp = (WidgetPropertiesDTO)pair.getValue();
                int key = (Integer) pair.getKey();
                Log.d("TAG", "KEY ON BACK:::"+key);
                WidgetPropertiesDTO temp  =  value.get(key);
                Log.d("TAG","TEMP"+temp.getWidgetName()+" "+"VALUE LENGTH:::::"+value.size());
                if(key == 1){
                    //populate colm1
                    populateDataForWidget1(temp);
                }else if(key == 2){
                    //popl col2
                    populateDataForWidget2(temp);
                }else if(key == 3 ){
                    populateDataForWidget3(temp);

                }else if(key == 4){
                    populateDataForWidget4(temp);
                }
            }*/
/*
{
        HashMap<Integer, HashMap<Integer, WidgetPropertiesDTO>> rowHashMap = MobileApplication.getInstance().getRowHashMap();

        Iterator it = rowHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            HashMap<Integer,WidgetPropertiesDTO> colHash =  ( HashMap<Integer,WidgetPropertiesDTO>) pair.getValue();
            for(int i=1;i<=4;i++){
               WidgetPropertiesDTO tempp = colHash.get(i);
                Log.d("TAG","Weight:::"+tempp.getWidgetWeight()+"WidgetType:::"+tempp.getWidgetName()+" "+"WidgetLabel:::"+tempp.getWidgetLabel());
                Log.d("TAG","Gravity:::"+tempp.getGravity()+"MArgin:::"+tempp.getMargin()+" "+"Padding:::"+tempp.getPadding());
                Log.d("TAG","WidgetID:::"+tempp.getWidgetId());

            }
           // it.remove(); // avoids a ConcurrentModificationException
        }
    }

 */
        }


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.finish_btn:
                createWidgetProperties();

                try {
                    String generatedXML = XMLGenerator.getInstance().generateLayoutUsingXMLSerializerForVerticalHorizontalLayout();
                    Log.d("ViewFragment", "GeneratedXML For Vertical Horizontal Layout::::::"+generatedXML);
                    getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(mContainerId,ResultFragment.newInstance(generatedXML,"")).commit();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.next_btn:
                createWidgetProperties();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(mContainerId, new VerticallyHorizontalFragment()).addToBackStack(null)
                        .commit();
                break;
        }
    }

    private void createWidgetProperties(){
    //    colList = new ArrayList<HashMap<Integer,WidgetPropertiesDTO>>();
      colHashMap = new HashMap<Integer,WidgetPropertiesDTO>();
        createWidget1Properties();
        createWidget2Properties();
        if(!isRatingBarSelected) {
            createWidget3Properties();
            createWidget4Properties();
        }
        Log.d("TAG", "ColHashMAPSize:::" + colHashMap.size());
        Log.d("TAG", "RowHashMapSize::::" + MobileApplication.getInstance().getRowHashMap().size());

        int rowPos = MobileApplication.getInstance().getRowPosition();
        rowPos = rowPos + 1;
        MobileApplication.getInstance().setRowPosition(rowPos);

        if(colHashMap!=null && colHashMap.size()>0) {

         if (MobileApplication.getInstance().getRowHashMap() != null && MobileApplication.getInstance().getRowHashMap().size() > 0) {
                HashMap<Integer,WidgetPropertiesDTO> value = MobileApplication.getInstance().getRowHashMap().get(rowPos);
                if(value!=null){
                    HashMap<Integer, HashMap<Integer, WidgetPropertiesDTO>> temp = MobileApplication.getInstance().getRowHashMap();
                    temp.put(MobileApplication.getInstance().getRowPosition(), colHashMap);
                    MobileApplication.getInstance().setRowHashMap(temp);
                }else{
                    if (MobileApplication.getInstance().getRowHashMap().containsKey(rowPos)) {
                        // Okay, there's a key but the value is null
                    }else{
                        HashMap<Integer, HashMap<Integer, WidgetPropertiesDTO>> temp = MobileApplication.getInstance().getRowHashMap();
                        temp.put(MobileApplication.getInstance().getRowPosition(), colHashMap);
                        MobileApplication.getInstance().setRowHashMap(temp);
                    }
                }
            }else {
               rowHashMap = new HashMap<Integer, HashMap<Integer, WidgetPropertiesDTO>>();
               rowHashMap.put(rowPos, colHashMap);
               MobileApplication.getInstance().setRowHashMap(rowHashMap);

           }


          /* if (MobileApplication.getInstance().getRowHashMap() != null && MobileApplication.getInstance().getRowHashMap().size() > 0) {

                HashMap<Integer, HashMap<Integer, WidgetPropertiesDTO>> temp = MobileApplication.getInstance().getRowHashMap();
                temp.put(MobileApplication.getInstance().getRowPosition(), colHashMap);
                MobileApplication.getInstance().setRowHashMap(temp);
            } else {
                rowHashMap = new HashMap<Integer, HashMap<Integer, WidgetPropertiesDTO>>();
                rowHashMap.put(MobileApplication.getInstance().getRowPosition(), colHashMap);
                MobileApplication.getInstance().setRowHashMap(rowHashMap);

            }*/
      //      updateRowPosition();

    //        displayRow();
        }

      //  colList.add(colHashMap);

    }

    private void updateRowPosition(){
        int rowPosition = MobileApplication.getInstance().getRowPosition()+ 1;
        MobileApplication.getInstance().setRowPosition(rowPosition);
    }

    private void displayRow(){
        HashMap<Integer, HashMap<Integer, WidgetPropertiesDTO>> rowHashMap = MobileApplication.getInstance().getRowHashMap();

        Iterator it = rowHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            HashMap<Integer,WidgetPropertiesDTO> colHash =  ( HashMap<Integer,WidgetPropertiesDTO>) pair.getValue();
            for(int i=1;i<=4;i++){
               WidgetPropertiesDTO tempp = colHash.get(i);
                Log.d("TAG","Weight:::"+tempp.getWidgetWeight()+"WidgetType:::"+tempp.getWidgetName()+" "+"WidgetLabel:::"+tempp.getWidgetLabel());
                Log.d("TAG","Gravity:::"+tempp.getGravity()+"MArgin:::"+tempp.getMargin()+" "+"Padding:::"+tempp.getPadding());
                Log.d("TAG","WidgetID:::"+tempp.getWidgetId());

            }
           // it.remove(); // avoids a ConcurrentModificationException
        }
    }
    private void createWidget1Properties(){
        WidgetPropertiesDTO temp = new WidgetPropertiesDTO();
        temp.setGravity("" + gravitySelector1.getSelectedItem());
        if(!TextUtils.isEmpty(widgetMargin1.getText().toString())) {
            temp.setMargin(widgetMargin1.getText().toString()+"dp");
        }else{
            temp.setMargin("0"+"dp");
        }
        if(!TextUtils.isEmpty(widgetPadding1.getText().toString())){
            temp.setPadding(widgetPadding1.getText().toString()+"dp");
        }else{
            temp.setPadding("0"+"dp");
        }
        if(!TextUtils.isEmpty(widgetName1.getText().toString())){
            temp.setWidgetLabel(widgetName1.getText().toString());
        }else{
            temp.setWidgetLabel("");
        }
        temp.setWidgetWeight(""+weight1.getSelectedItem());
        temp.setWidgetName(""+ widgetSelector1.getSelectedItem());

        if (temp.getWidgetName().equalsIgnoreCase("ImageView") || temp.getWidgetName().equalsIgnoreCase("ImageButton")) {
            temp.setWidgetDrawable("ic_launcher");
        }

        temp.setColor(""+colorSelector1.getSelectedItem());
        if(!TextUtils.isEmpty(widgetTextSize1.getText().toString())) {
            temp.setTextSize(widgetTextSize1.getText().toString() + "sp");
        }else{
            temp.setTextSize("18" + "sp");
        }
        setWidgetId(temp);
        colHashMap.put(1, temp);
    }

    private void createWidget2Properties(){
        WidgetPropertiesDTO temp = new WidgetPropertiesDTO();
        temp.setGravity("" + gravitySelector2.getSelectedItem());
        if(!TextUtils.isEmpty(widgetMargin2.getText().toString())) {
            temp.setMargin(widgetMargin2.getText().toString()+"dp");
        }else{
            temp.setMargin("0"+"dp");
        }
        if(!TextUtils.isEmpty(widgetPadding2.getText().toString())){
            temp.setPadding(widgetPadding2.getText().toString()+"dp");
        }else{
            temp.setPadding("0"+"dp");
        }
        if(!TextUtils.isEmpty(widgetName2.getText().toString())){
            temp.setWidgetLabel(widgetName2.getText().toString());
        }else{
            temp.setWidgetLabel("");
        }
        temp.setWidgetWeight(""+weight2.getSelectedItem());
        temp.setWidgetName("" + widgetSelector2.getSelectedItem());

        if (temp.getWidgetName().equalsIgnoreCase("ImageView") || temp.getWidgetName().equalsIgnoreCase("ImageButton")) {
            temp.setWidgetDrawable("ic_launcher");
        }

        temp.setColor("" + colorSelector2.getSelectedItem());
        if(!TextUtils.isEmpty(widgetTextSize2.getText().toString())) {
            temp.setTextSize(widgetTextSize2.getText().toString() + "sp");
        }else{
            temp.setTextSize("18" + "sp");
        }


        setWidgetId(temp);
        colHashMap.put(2,temp);
    }


    private void createWidget3Properties(){
        WidgetPropertiesDTO temp = new WidgetPropertiesDTO();
        temp.setGravity("" + gravitySelector3.getSelectedItem());
        if(!TextUtils.isEmpty(widgetMargin3.getText().toString())) {
            temp.setMargin(widgetMargin3.getText().toString()+"dp");
        }else{
            temp.setMargin("0"+"dp");
        }
        if(!TextUtils.isEmpty(widgetPadding3.getText().toString())){
            temp.setPadding(widgetPadding3.getText().toString()+"dp");
        }else{
            temp.setPadding("0"+"dp");
        }
        if(!TextUtils.isEmpty(widgetName3.getText().toString())){
            temp.setWidgetLabel(widgetName3.getText().toString());
        }else{
            temp.setWidgetLabel("");
        }
        temp.setWidgetWeight(""+weight3.getSelectedItem());
        temp.setWidgetName("" + widgetSelector3.getSelectedItem());

        if (temp.getWidgetName().equalsIgnoreCase("ImageView") || temp.getWidgetName().equalsIgnoreCase("ImageButton")) {
            temp.setWidgetDrawable("ic_launcher");
        }

        temp.setColor("" + colorSelector3.getSelectedItem());
        if(!TextUtils.isEmpty(widgetTextSize3.getText().toString())) {
            temp.setTextSize(widgetTextSize3.getText().toString() + "sp");
        }else{
            temp.setTextSize("18" + "sp");
        }
        setWidgetId(temp);

        colHashMap.put(3, temp);
    }

    private void createWidget4Properties(){
        WidgetPropertiesDTO temp = new WidgetPropertiesDTO();
        temp.setGravity("" + gravitySelector4.getSelectedItem());
        if(!TextUtils.isEmpty(widgetMargin4.getText().toString())) {
            temp.setMargin(widgetMargin4.getText().toString()+"dp");
        }else{
            temp.setMargin("0"+"dp");
        }
        if(!TextUtils.isEmpty(widgetPadding4.getText().toString())){
            temp.setPadding(widgetPadding4.getText().toString()+"dp");
        }else{
            temp.setPadding("0"+"dp");
        }
        if(!TextUtils.isEmpty(widgetName4.getText().toString())){
            temp.setWidgetLabel(widgetName4.getText().toString());
        }else{
            temp.setWidgetLabel("");
        }
        temp.setWidgetWeight(""+weight4.getSelectedItem());
        temp.setWidgetName("" + widgetSelector4.getSelectedItem());

        if (temp.getWidgetName().equalsIgnoreCase("ImageView") || temp.getWidgetName().equalsIgnoreCase("ImageButton")) {
            temp.setWidgetDrawable("ic_launcher");
        }

        temp.setColor("" + colorSelector4.getSelectedItem());
        if(!TextUtils.isEmpty(widgetTextSize4.getText().toString())) {
            temp.setTextSize(widgetTextSize4.getText().toString() + "sp");
        }else{
            temp.setTextSize("18" + "sp");
        }
        setWidgetId(temp);
        colHashMap.put(4,temp);
    }

    private void setWidgetId(WidgetPropertiesDTO temp){
        int viewID = UniqueIDGenerator.generateViewId();
        temp.setWidgetId("" + temp.getWidgetName() + viewID);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
   //     Log.d("VerticalHoriz", "in onItemSelected()");
        if(parent.getTag()!=null) {
            int tag = (Integer) parent.getTag();
            int totalWidgetWeight = Integer.parseInt(totalWeight.getText().toString());
            int availableWeight = Integer.parseInt(availableWidth.getText().toString());
            int selectedWeight = -1;
            switch (parent.getId()) {
                case R.id.weight_selector:
                    if (tag == R.id.widg1weight) {
                        //                 Toast.makeText(getActivity(), "Selected in Widget1:::" + weight1.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                        selectedWeight = Integer.parseInt(weight1.getItemAtPosition(position).toString());

                    } else if (tag == R.id.widg2weight) {
                        //           Toast.makeText(getActivity(), "Selected in Widget2:::" + weight2.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                        selectedWeight = Integer.parseInt(weight2.getItemAtPosition(position).toString());
                    }
                    if (tag == R.id.widg3weight) {
                        //         Toast.makeText(getActivity(), "Selected in Widget3:::" + weight3.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                        selectedWeight = Integer.parseInt(weight3.getItemAtPosition(position).toString());
                    }
                    if (tag == R.id.widg4weight) {
                        //           Toast.makeText(getActivity(), "Selected in Widget4:::" + weight4.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                        selectedWeight = Integer.parseInt(weight4.getItemAtPosition(position).toString());
                    }

                    if (selectedWeight <= availableWeight) {
                        int newlyAvailableWeight = -1;
                        if (availableWeight == 100) {
                            newlyAvailableWeight = totalWidgetWeight - selectedWeight;
                        } else {
                            newlyAvailableWeight = availableWeight - selectedWeight;
                        }
                        availableWidth.setText("" + newlyAvailableWeight);
                    } else {
                        Toast.makeText(getActivity(), "Total Weights of all 4 widgets should  be equal to 100", Toast.LENGTH_LONG).show();
                    }
                    checkForAllWidgetWeights();
                    break;
                case R.id.widget_selector:
                    String selectedWidget = "";
                    if(tag==R.id.widg1type){
                        selectedWidget  = widgetSelector1.getItemAtPosition(position).toString();
                        if(selectedWidget.equalsIgnoreCase("RatingBar")){
                            widget3View.setVisibility(View.INVISIBLE);
                            widget4View.setVisibility(View.INVISIBLE);
                            isRatingBarSelected = true;
                            weight1.setSelection(2);
                            weight2.setSelection(2);
                            widget4Label.setVisibility(View.INVISIBLE);
                            widget3Label.setVisibility(View.INVISIBLE);
                        }
                    }
                    break;
            }
        }

    }






private void checkForAllWidgetWeights(){
    int  widget1Weight = Integer.parseInt(weight1.getSelectedItem().toString());
    int  widget2Weight = Integer.parseInt(weight2.getSelectedItem().toString());
    int  widget3Weight = Integer.parseInt(weight3.getSelectedItem().toString());
    int  widget4Weight = Integer.parseInt(weight4.getSelectedItem().toString());
    int total = widget1Weight + widget2Weight + widget3Weight + widget4Weight;
    if(total<=(Integer.parseInt(totalWeight.getText().toString()))){
        availableWidth.setText(""+((Integer.parseInt(totalWeight.getText().toString()))-total));
    }
}
    @Override
    public void onNothingSelected(AdapterView<?> parent) {





    }
}
