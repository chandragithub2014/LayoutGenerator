package layout.layoutgenerator.utils;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import layout.layoutgenerator.Application.MobileApplication;
import layout.layoutgenerator.DTO.WidgetPropertiesDTO;

/**
 * Created by CHANDRASAIMOHAN on 2/3/2016.
 */
public class XMLGenerator {
    private static XMLGenerator instance;
    Context ctx;
    XmlSerializer xmlSerializer;

    private XMLGenerator(){

    }

    public static XMLGenerator getInstance(){
        if(instance == null){
            instance = new XMLGenerator();
        }
        return instance;
    }

    public  String generateLayoutUsingXMLSerializer() throws Exception {
        String xml = "";
        StringWriter writer=null;
        try {
            //   FileOutputStream fos = new FileOutputStream("layoutxml.xml");
            //   FileOutputStream fileos = openFileOutput(xmlFile, Context.MODE_PRIVATE);

            XmlSerializer xmlSerializer = Xml.newSerializer();
            writer = new StringWriter();

            xmlSerializer.setOutput(writer);
            // start DOCUMENT
            xmlSerializer.startDocument("UTF-8", true);

            // open tag: <record>
            xmlSerializer.startTag("", "ScrollView");
            xmlSerializer.attribute("", "xmlns:android", "http://schemas.android.com/apk/res/android");
            xmlSerializer.attribute("", "xmlns:tools", "http://schemas.android.com/tools");
            xmlSerializer.attribute("", "android:layout_width", "match_parent");
            xmlSerializer.attribute("", "android:layout_height", "wrap_content");

            xmlSerializer.startTag("", "LinearLayout");



            xmlSerializer.attribute("", "android:layout_width", "match_parent");
            xmlSerializer.attribute("", "android:layout_height", "match_parent");
            xmlSerializer.attribute("", "android:orientation", "vertical");


         /*   xmlSerializer.startTag("", "TextView");
            xmlSerializer.attribute("", "android:layout_width", "wrap_content");
            xmlSerializer.attribute("", "android:layout_height", "wrap_content");
            xmlSerializer.attribute("", " android:text", "Hello World");

            xmlSerializer.endTag("", "TextView");

            xmlSerializer.startTag("", "TextView");
            xmlSerializer.attribute("", "android:layout_width", "wrap_content");
            xmlSerializer.attribute("", "android:layout_height", "wrap_content");
            xmlSerializer.attribute("", " android:text", "Hello XML");
            xmlSerializer.endTag("", "TextView");
*/
            xmlSerializer = updateXMLSerializerWithWidgets(xmlSerializer);
            xmlSerializer.endTag("", "LinearLayout");
            xmlSerializer.endTag("", "ScrollView");

            // end DOCUMENT
            xmlSerializer.endDocument();

            String dataWrite = writer.toString();
            //  fileos.write(dataWrite.getBytes());
            //  fileos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return writer.toString();

    }

    private  XmlSerializer updateXMLSerializerWithWidgets1(XmlSerializer xmlSerializer){
        this.xmlSerializer = xmlSerializer;
        try{
            List<WidgetPropertiesDTO> widgetPropertiesDTOList = MobileApplication.getInstance().getWidgetList();
            if(widgetPropertiesDTOList.size()>0){
                for(int i = 0;i<widgetPropertiesDTOList.size();i++){
                    WidgetPropertiesDTO temp = widgetPropertiesDTOList.get(i);
                    xmlSerializer.startTag("", temp.getWidgetName());
                    xmlSerializer.attribute("", "android:layout_width", temp.getWidth());
                    xmlSerializer.attribute("", "android:layout_height", temp.getHeight());
                    xmlSerializer.attribute("", "android:text", temp.getWidgetLabel());
                    xmlSerializer.attribute("", "android:layout_margin", temp.getMargin());
                    xmlSerializer.attribute("", "android:padding", temp.getPadding());
                    xmlSerializer.attribute("", "android:gravity", temp.getGravity());
                    xmlSerializer.attribute("", "android:id", "@+id/"+temp.getWidgetId());
                    xmlSerializer.endTag("", temp.getWidgetName());



                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  xmlSerializer;
    }


    private  XmlSerializer updateXMLSerializerWithWidgets(XmlSerializer xmlSerializer){
        this.xmlSerializer = xmlSerializer;
        try{
            HashMap<Integer,WidgetPropertiesDTO> widgetInfoMap = MobileApplication.getInstance().getWidgetInfoMap();
            Map<Integer, WidgetPropertiesDTO> treeMap = new TreeMap<Integer, WidgetPropertiesDTO>(widgetInfoMap);
            if(widgetInfoMap!=null && widgetInfoMap.size()>0){

                for (Map.Entry<Integer, WidgetPropertiesDTO> entry : treeMap.entrySet())
                {
                    System.out.println(entry.getKey() + "/" + entry.getValue());
                    WidgetPropertiesDTO temp = entry.getValue();
                    xmlSerializer.startTag("", temp.getWidgetName());
                    if(temp.getWidgetName().equalsIgnoreCase("RatingBar") ||temp.getWidgetName().equalsIgnoreCase("RadioButton") || temp.getWidgetName().equalsIgnoreCase("CheckBox") || temp.getWidgetName().equalsIgnoreCase("ImageView")||temp.getWidgetName().equalsIgnoreCase("ImageButton")){
                        xmlSerializer.attribute("", "android:layout_width", "wrap_content");
                        xmlSerializer.attribute("", "android:layout_height", "wrap_content");
                    }else {
                        xmlSerializer.attribute("", "android:layout_width", temp.getWidth());
                        xmlSerializer.attribute("", "android:layout_height", temp.getHeight());
                    }
                    xmlSerializer.attribute("", "android:text", temp.getWidgetLabel());
                    xmlSerializer.attribute("", "android:layout_margin", temp.getMargin());
                    if(!temp.getWidgetName().equalsIgnoreCase("RatingBar")) {
                        xmlSerializer.attribute("", "android:padding", temp.getPadding());
                    }
                    if(temp.getWidgetName().equalsIgnoreCase("ImageView")||temp.getWidgetName().equalsIgnoreCase("ImageButton") ||temp.getWidgetName().equalsIgnoreCase("RatingBar") || temp.getWidgetName().equalsIgnoreCase("RadioButton") || temp.getWidgetName().equalsIgnoreCase("CheckBox")){
                        xmlSerializer.attribute("", "android:layout_gravity", temp.getGravity());
                    }else {
                        xmlSerializer.attribute("", "android:gravity", temp.getGravity());
                    }
                    xmlSerializer.attribute("", "android:id", "@+id/" + temp.getWidgetId());
                    if(temp.getWidgetName().equalsIgnoreCase("ImageView")||temp.getWidgetName().equalsIgnoreCase("ImageButton")){
                        xmlSerializer.attribute("", " android:src", "@drawable/" + temp.getWidgetDrawable());
                    }
                    if(temp.getWidgetName().equalsIgnoreCase("RatingBar")){
                        xmlSerializer.attribute("", "android:numStars", "4");
                        xmlSerializer.attribute("", "android:stepSize", "1.0");
                        xmlSerializer.attribute("", "android:rating", "2.0");
                    }
                    if(temp.getWidgetName().equalsIgnoreCase("TextView") || temp.getWidgetName().equalsIgnoreCase("EditText") || temp.getWidgetName().equalsIgnoreCase("Button")){
                        xmlSerializer.attribute("", "android:textSize", temp.getTextSize());

                    }
                    String colorSelector = temp.getColor();
                    String hexaDecimalColorCode = MobileApplication.getInstance().getColorHash().get(colorSelector);
                    xmlSerializer.attribute("", " android:background", hexaDecimalColorCode);

                    xmlSerializer.endTag("", temp.getWidgetName());
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  xmlSerializer;
    }



    public  String generateLayoutUsingXMLSerializerForVerticalHorizontalLayout() throws Exception {
        String xml = "";
        StringWriter writer=null;
        try {
            //   FileOutputStream fos = new FileOutputStream("layoutxml.xml");
            //   FileOutputStream fileos = openFileOutput(xmlFile, Context.MODE_PRIVATE);

            XmlSerializer xmlSerializer = Xml.newSerializer();
            writer = new StringWriter();

            xmlSerializer.setOutput(writer);
            // start DOCUMENT
            xmlSerializer.startDocument("UTF-8", true);

            // open tag: <record>
            xmlSerializer.startTag("", "ScrollView");
            xmlSerializer.attribute("", "xmlns:android", "http://schemas.android.com/apk/res/android");
            xmlSerializer.attribute("", "xmlns:tools", "http://schemas.android.com/tools");
            xmlSerializer.attribute("", "android:layout_width", "match_parent");
            xmlSerializer.attribute("", "android:layout_height", "wrap_content");

            xmlSerializer.startTag("", "LinearLayout");



            xmlSerializer.attribute("", "android:layout_width", "match_parent");
            xmlSerializer.attribute("", "android:layout_height", "match_parent");
            xmlSerializer.attribute("", "android:orientation", "vertical");


         /*   xmlSerializer.startTag("", "TextView");
            xmlSerializer.attribute("", "android:layout_width", "wrap_content");
            xmlSerializer.attribute("", "android:layout_height", "wrap_content");
            xmlSerializer.attribute("", " android:text", "Hello World");

            xmlSerializer.endTag("", "TextView");

            xmlSerializer.startTag("", "TextView");
            xmlSerializer.attribute("", "android:layout_width", "wrap_content");
            xmlSerializer.attribute("", "android:layout_height", "wrap_content");
            xmlSerializer.attribute("", " android:text", "Hello XML");
            xmlSerializer.endTag("", "TextView");
*/
            xmlSerializer = updateXMLSerializerWithWidgetsForVerticallyHorizontalLayout(xmlSerializer);
            xmlSerializer.endTag("", "LinearLayout");
            xmlSerializer.endTag("", "ScrollView");

            // end DOCUMENT
            xmlSerializer.endDocument();

            String dataWrite = writer.toString();
            //  fileos.write(dataWrite.getBytes());
            //  fileos.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return writer.toString();

    }

    private  XmlSerializer updateXMLSerializerWithWidgetsForVerticallyHorizontalLayout(XmlSerializer xmlSerializer) {
        this.xmlSerializer = xmlSerializer;
        try{
            HashMap<Integer, HashMap<Integer, WidgetPropertiesDTO>> verticalHorizontalRow  = MobileApplication.getInstance().getRowHashMap();
            Map<Integer, HashMap<Integer, WidgetPropertiesDTO>> treeMap = new TreeMap<Integer, HashMap<Integer, WidgetPropertiesDTO>>(verticalHorizontalRow);
            for (Map.Entry<Integer, HashMap<Integer, WidgetPropertiesDTO>> entry : treeMap.entrySet()){
                System.out.println(entry.getKey() + "/" + entry.getValue());
                //Create Linear Layout with weight

                xmlSerializer.startTag("", "LinearLayout");
                xmlSerializer.attribute("", "android:layout_width", "match_parent");
                xmlSerializer.attribute("", "android:layout_height", "wrap_content");
                xmlSerializer.attribute("", "android:orientation", "horizontal");
                xmlSerializer.attribute("", "android:weightSum", "100");

                HashMap<Integer,WidgetPropertiesDTO> widgetInfoMap = entry.getValue();
                Map<Integer, WidgetPropertiesDTO> widgetInfotreeMap = new TreeMap<Integer, WidgetPropertiesDTO>(widgetInfoMap);
                for (Map.Entry<Integer, WidgetPropertiesDTO> infoentry : widgetInfotreeMap.entrySet()){

                    System.out.println(infoentry.getKey() + "/" + infoentry.getValue());
                    WidgetPropertiesDTO temp = infoentry.getValue();
                    xmlSerializer.startTag("", temp.getWidgetName());
                    if(temp.getWidgetName().equalsIgnoreCase("RatingBar") ||temp.getWidgetName().equalsIgnoreCase("RadioButton") || temp.getWidgetName().equalsIgnoreCase("CheckBox") || temp.getWidgetName().equalsIgnoreCase("ImageView")||temp.getWidgetName().equalsIgnoreCase("ImageButton")){
                        xmlSerializer.attribute("", "android:layout_width", "0dp");
                        xmlSerializer.attribute("", "android:layout_height", "wrap_content");
                    }else {
                        xmlSerializer.attribute("", "android:layout_width", "0dp");
                        xmlSerializer.attribute("", "android:layout_height", "wrap_content");
                    }
                    xmlSerializer.attribute("", "android:text", temp.getWidgetLabel());
                    xmlSerializer.attribute("", "android:layout_margin", temp.getMargin());
                    if(!temp.getWidgetName().equalsIgnoreCase("RatingBar")) {
                        xmlSerializer.attribute("", "android:padding", temp.getPadding());
                    }
                    if(temp.getWidgetName().equalsIgnoreCase("ImageView")||temp.getWidgetName().equalsIgnoreCase("ImageButton") ||temp.getWidgetName().equalsIgnoreCase("RatingBar") || temp.getWidgetName().equalsIgnoreCase("RadioButton") || temp.getWidgetName().equalsIgnoreCase("CheckBox")){
                        xmlSerializer.attribute("", "android:layout_gravity", temp.getGravity());
                    }else {
                        xmlSerializer.attribute("", "android:gravity", temp.getGravity());
                    }
                    xmlSerializer.attribute("", "android:id", "@+id/" + temp.getWidgetId());
                    if(temp.getWidgetName().equalsIgnoreCase("ImageView")||temp.getWidgetName().equalsIgnoreCase("ImageButton")){
                        xmlSerializer.attribute("", " android:src", "@drawable/" + temp.getWidgetDrawable());
                    }
                    if(temp.getWidgetName().equalsIgnoreCase("RatingBar")){
                        xmlSerializer.attribute("", "android:numStars", "4");
                        xmlSerializer.attribute("", "android:stepSize", "1.0");
                        xmlSerializer.attribute("", "android:rating", "2.0");
                    }

                    if(temp.getWidgetName().equalsIgnoreCase("RatingBar")){
                        xmlSerializer.attribute("", "android:layout_weight", "50");
                    }
                    xmlSerializer.attribute("", "android:layout_weight", temp.getWidgetWeight());
                    if(temp.getWidgetName().equalsIgnoreCase("TextView") || temp.getWidgetName().equalsIgnoreCase("EditText") ||  temp.getWidgetName().equalsIgnoreCase("Button")){
                        xmlSerializer.attribute("", "android:textSize", temp.getTextSize());

                    }
                    String colorSelector = temp.getColor();
                    String hexaDecimalColorCode = MobileApplication.getInstance().getColorHash().get(colorSelector);
                    xmlSerializer.attribute("", " android:background", hexaDecimalColorCode);
                    xmlSerializer.endTag("", temp.getWidgetName());


                }

                xmlSerializer.endTag("", "LinearLayout");

            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  xmlSerializer;
    }
}





