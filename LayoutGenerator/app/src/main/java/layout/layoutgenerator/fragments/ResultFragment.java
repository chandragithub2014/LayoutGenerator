package layout.layoutgenerator.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.jar.Manifest;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import layout.layoutgenerator.Application.MobileApplication;
import layout.layoutgenerator.MainActivity;
import layout.layoutgenerator.R;
import layout.layoutgenerator.preview.VerticalLinearLayoutActivity;
import layout.layoutgenerator.preview.VerticallyHorizontalLinearLayoutActivity;


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
    EditText emailID;
    String resultXML="";



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
    public void onResume() {
        super.onResume();
        Toolbar mtoolBar = (Toolbar)((AppCompatActivity) getActivity()).findViewById(R.id.toolbar);
        TextView titleBar = (TextView)mtoolBar.findViewById(R.id.title);
        titleBar.setText("Generated XML");
        ImageView homeView = (ImageView)mtoolBar.findViewById(R.id.home_icon);
        homeView.setVisibility(View.VISIBLE);
        homeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                Intent homeIntent = new Intent(getActivity(), MainActivity.class);
                homeIntent.setFlags(homeIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY); // Adds the FLAG_ACTIVITY_NO_HISTORY flag
                startActivity(homeIntent);
            }
        });

        ImageView preView = (ImageView)mtoolBar.findViewById(R.id.map_icon);
        preView.setVisibility(View.VISIBLE);
        preView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MobileApplication.getInstance().getLayoutType() == 0) {
                    Intent homeIntent = new Intent(getActivity(), VerticalLinearLayoutActivity.class);

                    startActivity(homeIntent);
                } else if (MobileApplication.getInstance().getLayoutType() == 1) {
                    Intent homeIntent = new Intent(getActivity(), VerticallyHorizontalLinearLayoutActivity.class);

                    startActivity(homeIntent);
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_result, container, false);
         xmlLabel = (TextView)v.findViewById(R.id.resultView);
        if(!TextUtils.isEmpty(mParam1)){
            resultXML  = format(mParam1);
            xmlLabel.setText(resultXML);
        }
        Button emailButton  = (Button)v.findViewById(R.id.email);
        emailButton.setOnClickListener(this);
        emailID = (EditText)v.findViewById(R.id.email_txt);
        Button saveLocally = (Button)v.findViewById(R.id.save);
        saveLocally.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.email:
                if(!TextUtils.isEmpty(emailID.getText().toString())) {
                    sendEmail();
                }else{
                    Toast.makeText(getActivity(),"Email not entered",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.save:
                checkForPermissions();
                /*writeToXMLFile(resultXML);*/
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

        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{emailID.getText().toString()});

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Linear Layout Vertical orientation Generated XML");

        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,  xmlLabel.getText().toString());

        startActivity(Intent.createChooser(emailIntent, "Send email..."));

        /*Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", "abc@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailtext.getText());
        startActivity(Intent.createChooser(emailIntent, "Send email..."));*/

    }

    private void checkForPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.System.canWrite(getActivity())) {
                requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                // continue with your code
                Log.d("TAG","Can write");
            }
        }else{
            Log.d("TAG","Build.VERSION.SDK_INT <= Build.VERSION_CODES.M");
        }
     // int hasWriteExternalStoragePermission  = getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

        //if(getActivity().checkSelfPermission(Manifest.Per))
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.e("Permission", "Granted");
                    writeToXMLFile(resultXML);
                } else {
                    Log.e("Permission", "Denied");
                }
                return;
            }
        }
    }

    private void writeToXMLFile(String xml){
        File root=null;
        if(!TextUtils.isEmpty(xml)) {
            try {
                // check for SDcard
                root = Environment.getExternalStorageDirectory();
                Log.i("ResultFragment", "path.." + root.canWrite());
             /*   String newFolder = "/layoutGenerator";
                String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
                File myNewFolder = new File(extStorageDirectory + newFolder);
                myNewFolder.mkdir();*/
               if(root.canWrite()){
                    // create a File object for the parent directory
                    File sourceDirectory = new File("/sdcard/layoutGenerator/");
                    if(!sourceDirectory.exists()) {
                        sourceDirectory.mkdirs();
                        Log.d("TAG", "Directory Created");

                    }else{
                        Log.d("TAG", "Directory Already Exists");
                    }

                    String fileName = "uilayout" + System.currentTimeMillis();
                    File file= new File(sourceDirectory, fileName+".txt");
                    Log.d("ResultFragment", "FileName::::" + fileName);
                    FileWriter filewriter = new FileWriter(file);
                    BufferedWriter out = new BufferedWriter(filewriter);
                    out.write(xml);
                    out.close();
                }

            /*    FileOutputStream fos = new FileOutputStream(new File(getActivity().getFilesDir(), fileName + ".xml"));
             //   FileOutputStream fileos = getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);
                fos.write(xml.getBytes());
                fos.close();*/
            } /*catch (FileNotFoundException e) {
                e.printStackTrace();
            }*/ catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getActivity(),"XML NOT FOUND",Toast.LENGTH_LONG).show();
        }
    }
}
