package layout.layoutgenerator.Adapters;

/**
 * Created by CHANDRASAIMOHAN on 2/27/2016.
 */

import android.support.v7.widget.RecyclerView;



        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

        import java.util.ArrayList;

import layout.layoutgenerator.DTO.DataObject;
import layout.layoutgenerator.R;
import layout.layoutgenerator.interfaces.MyClickListener;

public class MyRecyclerViewAdapter extends RecyclerView
        .Adapter<MyRecyclerViewAdapter
        .DataObjectHolder> {
    private  static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<DataObject> mDataset;
    private static  MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView label;
        ImageButton infoImgBtn;

        public DataObjectHolder(View itemView) {
            super(itemView);

            label = (TextView) itemView.findViewById(R.id.textView);
            infoImgBtn = (ImageButton)itemView.findViewById(R.id.info);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
            infoImgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tag = (Integer) v.getTag();
                    Log.d(LOG_TAG,"Clicked on info Button"+tag);
                    myClickListener.onSpecificViewOnItemClick(tag,v);
                }
            });
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(),v);


        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MyRecyclerViewAdapter(ArrayList<DataObject> myDataset,MyClickListener myClickListener) {
        mDataset = myDataset;
        this.myClickListener = myClickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layoutlistitem, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getmText1());
        holder.infoImgBtn.setTag(position);

    }

    public void addItem(DataObject dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}