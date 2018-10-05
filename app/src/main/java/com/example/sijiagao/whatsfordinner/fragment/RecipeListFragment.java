package com.example.sijiagao.whatsfordinner.fragment;


import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sijiagao.whatsfordinner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeListFragment extends Fragment {
    public static final String TAG = "yes";
    private ListFragmentListener mListener;

    public RecipeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view =inflater.inflate(R.layout.fragment_recipe_list, container, false);


        String[] sampleList = {"apple", "ham", "BBQ","Coke","apple", "ham", "BBQ","Coke","apple", "ham", "BBQ","Coke","apple", "ham", "BBQ","Coke"
        ,"apple", "ham", "BBQ","Coke"};
        ListView lv;
        lv =(ListView) view.findViewById(R.id.recipe_listview);
        lv.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.recipe_listview_detail, sampleList));
        Log.i(TAG,"before clickDone");


        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i(TAG, "clickDone in LandScape"); // for later send to meal
                    mListener.onListItemClick();
                }
            });
        }

        else {
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.i(TAG, "clickDone in portrait"); // for later send to meal
                }
            });

        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListFragmentListener) {
            mListener = (ListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    public interface ListFragmentListener {
        void onListItemClick();
    }
}
