package com.android.sanjit.diseaseinformation;

import android.content.Context;
import android.hardware.display.VirtualDisplay;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    EditText disaseName;
    EditText diseaseType;
    EditText symptomps;
    EditText cause;
    EditText faid;
    EditText treatment;
    Button addButton;

    MainFragmentListner activityCommander;

    public interface MainFragmentListner{
        public void insertData(String name,String type,String symptoms,String cause,String faid,String treat);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCommander = (MainFragmentListner) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        disaseName = (EditText)v.findViewById(R.id.inputroll);
        diseaseType = (EditText)v.findViewById(R.id.inputname);
        symptomps = (EditText)v.findViewById(R.id.inputmark);
        cause = (EditText)v.findViewById(R.id.editText2);
        faid = (EditText)v.findViewById(R.id.editText3);
        treatment = (EditText)v.findViewById(R.id.editText4);
        addButton = (Button)v.findViewById(R.id.addbutton);


        addButton.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        addbuttonclicked(view);
                    }
                }
        );

        return v;
    }

    public void addbuttonclicked(View view){
        //Toast.makeText(getContext(),"ADD Button Clicked",Toast.LENGTH_LONG).show();
        activityCommander.insertData(disaseName.getText().toString(),diseaseType.getText().toString(),
                symptomps.getText().toString(),cause.getText().toString(),
                faid.getText().toString(),treatment.getText().toString());
        disaseName.setText("");
        diseaseType.setText("");
        symptomps.setText("");
        cause.setText("");
        faid.setText("");
        treatment.setText("");
    }
}
