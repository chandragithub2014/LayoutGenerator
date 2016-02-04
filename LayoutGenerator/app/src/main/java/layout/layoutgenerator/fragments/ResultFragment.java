package layout.layoutgenerator.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import layout.layoutgenerator.R;




/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment implements  View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TextView xmlLabel;


    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance(String param1, String param2) {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_result, container, false);
         xmlLabel = (TextView)v.findViewById(R.id.resultView);
        if(!TextUtils.isEmpty(mParam1)){
            String resultantXML  = format(mParam1);
            xmlLabel.setText(resultantXML);
        }
        Button emailButton  = (Button)v.findViewById(R.id.email);
        emailButton.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.email:
                sendEmail();
                break;
        }
    }

    public String format(String xml) {
       StringWriter stringWriter = null;
       try {
           Document document = DocumentBuilderFactory.newInstance()
                   .newDocumentBuilder()
                   .parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

           XPath xPath = XPathFactory.newInstance().newXPath();
           NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']",
                   document,
                   XPathConstants.NODESET);

           for (int i = 0; i < nodeList.getLength(); ++i) {
               Node node = nodeList.item(i);
               node.getParentNode().removeChild(node);
           }

           Transformer transformer = TransformerFactory.newInstance().newTransformer();
           transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
           transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
           transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

           stringWriter = new StringWriter();
           StreamResult streamResult = new StreamResult(stringWriter);

           transformer.transform(new DOMSource(document), streamResult);

           System.out.println(stringWriter.toString());
       }
       catch (Exception e) {
           e.printStackTrace();
       }
          return stringWriter.toString();
    }

    private void sendEmail(){
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");

        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"b.chandrasaimohan@gmail.com"});

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Linear Layout Vertical orientation Generated XML");

        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,  xmlLabel.getText().toString());

        startActivity(Intent.createChooser(emailIntent, "Send email..."));

        /*Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "abc@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailtext.getText());
        startActivity(Intent.createChooser(emailIntent, "Send email..."));*/

    }
}
