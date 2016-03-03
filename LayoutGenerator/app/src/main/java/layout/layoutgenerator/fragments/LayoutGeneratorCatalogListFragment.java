package layout.layoutgenerator.fragments;



import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;

import layout.layoutgenerator.Application.MobileApplication;
import layout.layoutgenerator.DTO.WidgetPropertiesDTO;
import layout.layoutgenerator.R;

/**
 * A simple {@link ListFragment} subclass.
 * Use the {@link LayoutGeneratorCatalogListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LayoutGeneratorCatalogListFragment extends ListFragment implements AdapterView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int mContainerId = -1;


    public LayoutGeneratorCatalogListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LayoutGeneratorCatalogListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LayoutGeneratorCatalogListFragment newInstance(String param1, String param2) {
        LayoutGeneratorCatalogListFragment fragment = new LayoutGeneratorCatalogListFragment();
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
        MobileApplication.getInstance().setIsGeneratorFragment(false);
        mContainerId = container.getId();
        return inflater.inflate(R.layout.fragment_layout_generator_catalog_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MobileApplication.getInstance().setIsGeneratorFragment(false);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.layoutselector, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       if(position==0){
           //End
           MobileApplication.getInstance().setIsGeneratorFragment(true);
           MobileApplication.getInstance().setWidgetPos(0);
           HashMap<Integer,WidgetPropertiesDTO> widgetInfo = new HashMap<Integer,WidgetPropertiesDTO>();
           MobileApplication.getInstance().setWidgetInfoMap(widgetInfo);
           getActivity().getSupportFragmentManager().beginTransaction()
                   .replace(mContainerId, new ViewFragment()).addToBackStack(null)
                   .commit();
           //View.generateViewId();
       }else{
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
}
