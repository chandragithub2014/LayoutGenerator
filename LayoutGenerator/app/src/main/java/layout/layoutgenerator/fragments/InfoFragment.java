package layout.layoutgenerator.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import layout.layoutgenerator.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;

    private String html_for_pos_1  = "<h2>Vertical Linear Layout Generator</h2>\n" +
            "<p>Vertical Linear Layout generator generates a layout xml of Linear Layout in Vertical Orientation <br>\n" +
            "This generator provides interface that allows you to generate LinearLayout with Vertical orientation <br> The procedure to generate layout is mentioned below: <br>\n" +
            "</p>\n" +
            "<h2>Procedure to generate layout</h2>\n" +
            "<h2> Widget Info section </h2>\n" +
            "<p>This section contains drop down to select preferred widget and Editable text field is provided to enter label for widget as per your requirement.</p>\n" +
            "<h2> Widget Dimensions section </h2>\n" +
            "<p>This section contains drop down to set selected widget's width and height. Selecting widget width or height to <b> custom </b>rather <b>match_parent or wrap_content </b>will allow to add dimensions as numbers</p>\n" +
            "<h2> Allignments section </h2>\n" +
            "<p>This section allows you  to set <b>margin or padding </b> which should be given in <b>integer numbers</b> <br> Also provides drop down which contains various options to  set <b> gravity  </b>for your widget </p>\n" +
            "<h2>Next and Finish Buttons</h2>\n" +
            "<p> <b>Next </b> button allows to configure next widget of your layout where you need to repeat the steps provided in above sections <br>\n" +
            " <b>Finish </b> button displays the resultant layout xml which you send to email or save it locally in your mobile.</p>\n" +
            "<h3>Enjoy layout generating !!!!!!!!!!!!!!</h3> ";

    private String getHtml_for_pos_2 = "<h2>Vertically Horizontal Linear Layout Generator</h2>\n" +
            "<p>\n" +
            "This generator provides interface that allows you to configure 4 elements a row horizontally.We can configure as many  rows as per your requirement. Rows were added vertically and elements in each row were added horizontally . so the name vertically horizontal linear layout . Each row is considered as 100 so that  max of 4 elements with 25% each can be accommodated. Some of the elements like Rating Bar occupies more than 25% space .In that case only 2 elements can be configured each with 50% occupancy as per your requirement.  <br> The procedure to generate layout is mentioned below: <br>\n" +
            "</p>\n" +
            "<h2>Procedure to generate layout</h2>\n" +
            "\n" +
            "<h2> Widget Info section </h2>\n" +
            "<p>This section contains drop down to select preferred widget and Editable text field is provided to enter label for widget as per your requirement.</p>\n" +
            "<h2> Weight and Gravity Sections </h2>\n" +
            "<p>This section contains drop down to set weight of widget that specifies percentage of each widget occupancy.<br> Also provides drop down which contains various options to  set <b> gravity  </b>for your widget</p>\n" +
            "<h2> Allignments section </h2>\n" +
            "<p>This section allows you  to set <b>margin or padding </b> which should be given in <b>integer numbers</b> <br> \n" +
            "<b>The above steps needs to be repeated for all the 4 widgets . </b> </p>\n" +
            "<h2>Next and Finish Buttons</h2>\n" +
            "<p> <b>Next </b> button allows to configure next row of your layout where you need to repeat the steps provided in above sections <br>\n" +
            " <b>Finish </b> button displays the resultant layout xml which you send to email or save it locally in your mobile.</p>\n" +
            "<h3>Enjoy layout generating !!!!!!!!!!!!!!</h3>";

    public InfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InfoFragment newInstance(int  param1, String param2) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_info, container, false);
        TextView infoView = (TextView)view.findViewById(R.id.info_layout);
        if(mParam1 == 0){
            infoView.setText(Html.fromHtml(html_for_pos_1));
        }else if(mParam1==1){
            infoView.setText(Html.fromHtml(getHtml_for_pos_2));
        }

        return view;
    }

}
