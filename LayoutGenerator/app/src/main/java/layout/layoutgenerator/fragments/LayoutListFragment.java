package layout.layoutgenerator.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import layout.layoutgenerator.Adapters.MyRecyclerViewAdapter;
import layout.layoutgenerator.Application.MobileApplication;
import layout.layoutgenerator.DTO.DataObject;
import layout.layoutgenerator.DTO.WidgetPropertiesDTO;
import layout.layoutgenerator.R;
import layout.layoutgenerator.interfaces.MyClickListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LayoutListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LayoutListFragment extends Fragment implements MyClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view = null;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "LayoutListFragment";

    int mContainerId = -1;

    public LayoutListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LayoutListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LayoutListFragment newInstance(String param1, String param2) {
        LayoutListFragment fragment = new LayoutListFragment();
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
        titleBar.setText("LAYOUT  GENERATOR");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  =  inflater.inflate(R.layout.fragment_layout_list, container, false);
        mContainerId = container.getId();


        mRecyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet(),LayoutListFragment.this);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);
        return view;
    }


    private ArrayList<DataObject> getDataSet() {
        String[] stringArray = getActivity().getResources().getStringArray(R.array.layoutselector);
        ArrayList results = new ArrayList<DataObject>();
        for(int i=0;i<stringArray.length;i++) {
            DataObject obj = new DataObject(stringArray[i]);
            results.add(i, obj);
        }
            return results;
    }

    @Override
    public void onItemClick(int position, View v) {
        if(position==0){
            //End
            MobileApplication.getInstance().setLayoutType(0);
            MobileApplication.getInstance().setIsGeneratorFragment(true);
            MobileApplication.getInstance().setWidgetPos(0);
            HashMap<Integer,WidgetPropertiesDTO> widgetInfo = new HashMap<Integer,WidgetPropertiesDTO>();
            MobileApplication.getInstance().setWidgetInfoMap(widgetInfo);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(mContainerId, new ViewFragment()).addToBackStack(null)
                    .commit();
            //View.generateViewId();
        }else{
            MobileApplication.getInstance().setLayoutType(1);
            MobileApplication.getInstance().setIsHorizonVertGeneratorFragment(true);
            MobileApplication.getInstance().setRowPosition(0);
            HashMap<Integer, HashMap<Integer, WidgetPropertiesDTO>> rowHash = new HashMap<Integer, HashMap<Integer, WidgetPropertiesDTO>>();
            MobileApplication.getInstance().setRowHashMap(rowHash);
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(mContainerId, new VerticallyHorizontalFragment()).addToBackStack(null)
                    .commit();
            //  Toast.makeText(getActivity(),"Clicked on Position::::"+position,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onSpecificViewOnItemClick(int position, View v) {
        Fragment  infoFragment = null;
        if(position==0){
         infoFragment =          InfoFragment.newInstance(position,"");
        }else if(position==1){
            infoFragment =          InfoFragment.newInstance(position,"");
        }
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(mContainerId, infoFragment).addToBackStack(null)
                .commit();
    }
}
