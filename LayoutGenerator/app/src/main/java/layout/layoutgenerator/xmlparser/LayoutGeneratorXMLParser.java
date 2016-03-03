/*
package layout.layoutgenerator.xmlparser;

import android.content.Context;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import layout.layoutgenerator.DTO.WidgetPropertiesDTO;

*/
/**
 * Created by CHANDRASAIMOHAN on 3/4/2016.
 *//*

public class LayoutGeneratorXMLParser {


    private static LayoutGeneratorXMLParser instance;
    Context ctx;


        private LayoutGeneratorXMLParser(){

        }


    public static LayoutGeneratorXMLParser getInstance(){
        if(instance == null){
            instance = new LayoutGeneratorXMLParser();
        }
        return instance;
    }

        Map<Integer,WidgetPropertiesDTO>getParsedList(Context ctx,String xmlResponse) throws XmlPullParserException, IOException {
        Map<Integer,WidgetPropertiesDTO> resultantParsedXML = new HashMap<Integer,WidgetPropertiesDTO>();
            WidgetPropertiesDTO temp ;
        int position = -1;
            String tagName = null;
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory
                    .newInstance();
            xmlPullParserFactory.setNamespaceAware(true);
            XmlPullParser */
/**//*
xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlResponse));
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                tagName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:
                        temp = new WidgetPropertiesDTO();
                        if (!tagName.equalsIgnoreCase("ScrollView") ||!tagName.equalsIgnoreCase("LinearLayout")  ) {
                            temp.setWidgetName(tagName);
                            for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                                if (xmlPullParser.getAttributeName(i).equalsIgnoreCase(
                                        OMSMessages.DB_TABLENAME.getValue())) {
                                    destination.destinataionTableName = xmlPullParser
                                            .getAttributeValue(i);
                                }
                            }

                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                eventType = xmlPullParser.next();

            }



        return  resultantParsedXML;
    }
}
*/
