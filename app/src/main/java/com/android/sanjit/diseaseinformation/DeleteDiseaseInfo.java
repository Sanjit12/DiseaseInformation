package com.android.sanjit.diseaseinformation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteDiseaseInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteDiseaseInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    DatabaseHandler db;
    EditText editText;
    Button button;
    public DeleteDiseaseInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteDiseaseInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteDiseaseInfo newInstance(String param1, String param2) {
        DeleteDiseaseInfo fragment = new DeleteDiseaseInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        db = DatabaseHandler.getInstance(getContext());
        View view = inflater.inflate(R.layout.fragment_delete_disease_info, container, false);
        editText = (EditText)view.findViewById(R.id.editText);
        button = (Button)view.findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = editText.getText().toString();
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
                    db.deletedisease(target);
                    Toast.makeText(getContext(),"Successfully deleted "+target.getName(),Toast.LENGTH_LONG).show();
                    editText.setText("");
                }
                else{
                    Toast.makeText(getContext(),search+" is not in Database",Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }

}
