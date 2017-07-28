package com.android.sanjit.diseaseinformation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmergencyHelp#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmergencyHelp extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public EmergencyHelp() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmergencyHelp.
     */
    // TODO: Rename and change types and number of parameters
    public static EmergencyHelp newInstance(String param1, String param2) {
        EmergencyHelp fragment = new EmergencyHelp();
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
        View v = inflater.inflate(R.layout.fragment_emergency_help, container, false);

        String[] numbers = {"Seba Ambulance Service-01661247452","Asha Fast Service-01784658954","Ma Ambulance Seba-01754123652",
                "Wasim Ambulance Service-01823742382","Doa Ambulance Service-01734246324","Prantik Ambulance Seba-01562524524"};
        ListAdapter myadap = new CustomAdapter(getContext(),numbers);
        ListView mlv = (ListView) v.findViewById(R.id.listview);
        mlv.setAdapter(myadap);

        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] number = String.valueOf(parent.getItemAtPosition(position)).split("-");
                Toast.makeText(getContext(),number[0]+" "+number[1],Toast.LENGTH_LONG).show();

                String uri = "tel:" + number[1].trim() ;
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });
        return v;
    }

}
