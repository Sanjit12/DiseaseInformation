package com.android.sanjit.diseaseinformation;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchForDisease#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchForDisease extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button searchButton;
    private EditText searchView;
    TextView textView;
    DatabaseHandler db;
    SearchFragmentListener activityCommander;
    public interface SearchFragmentListener{
        public void getDisease(String name);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCommander = (SearchFragmentListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public SearchForDisease() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchForDisease.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchForDisease newInstance(String param1, String param2) {
        SearchForDisease fragment = new SearchForDisease();
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
        View view = inflater.inflate(R.layout.fragment_search_for_disease, container, false);

        searchButton = (Button) view.findViewById(R.id.searchButton);
        searchView = (EditText) view.findViewById(R.id.searchBox);
        textView = (TextView) view.findViewById(R.id.outText);
        db = DatabaseHandler.getInstance(getContext());
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchButtonClicked(v);
            }
        });

        return view;
    }
    public void searchButtonClicked(View view){
        String search = searchView.getText().toString();
        //Toast.makeText(getContext(),search,Toast.LENGTH_LONG).show();
        //activityCommander.getDisease(searchView.getQuery().toString());
        List<Disease> diseases = db.getAlldiseases();
        boolean found = false;
        Disease target = new Disease();
        for(Disease d:diseases){
            if(d.getName().equalsIgnoreCase(search)){
                found = true;
                target = d;
                break;
            }
        }
        if(found){
            Toast.makeText(getContext(),search,Toast.LENGTH_LONG).show();
            setDiseaseName(target);
        }
        else{
            Toast.makeText(getContext(),"Not Found",Toast.LENGTH_LONG).show();
        }
    }
    public void setDiseaseName(Disease d){
        String dis = "";
        dis+=" Disease Name: "+d.getName()+"\n";
        dis+=" Type: "+d.get_type()+"\n";
        dis+=" Common Symptoms: "+d.get_symptomps()+"\n";
        dis+=" Caused By: "+d.get_causes()+"\n";
        dis+=" First AID Treatment: "+d.get_first_aid()+"\n";
        dis+=" Medicines: "+d.get_treatment()+"\n";
        textView.setText(dis);
        searchView.setText("");
    }
}
