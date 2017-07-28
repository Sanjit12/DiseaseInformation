package com.android.sanjit.diseaseinformation;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class DataFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String SYMPTOMS = "symptoms";
    private static final String CAUSE = "cause";
    private static final String FIRSTAID = "firstaid";
    private static final String TREATMENT = "treatment";

    private String name;
    private String type;
    private String symptoms;
    private String cause;
    private String firstaid;
    private String treatment;
    private OnFragmentInteractionListener mListener;
    TextView textView;
    DatabaseHandler db;

    public DataFragment() {
        // Required empty public constructor
    }
    // TODO: Rename and change types and number of parameters
    public static DataFragment newInstance(Disease disease) {
        DataFragment fragment = new DataFragment();
        Bundle args = new Bundle();
        args.putString(NAME, disease.getName());
        args.putString(TYPE, disease.get_type());
        args.putString(SYMPTOMS, disease.get_symptomps());
        args.putString(CAUSE, disease.get_causes());
        args.putString(FIRSTAID, disease.get_first_aid());
        args.putString(TREATMENT, disease.get_symptomps());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
            type = getArguments().getString(TYPE);
            symptoms = getArguments().getString(SYMPTOMS);
            cause = getArguments().getString(CAUSE);
            firstaid = getArguments().getString(FIRSTAID);
            treatment = getArguments().getString(TREATMENT);
        }
        db=DatabaseHandler.getInstance(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_data, container, false);
        textView = (TextView) v.findViewById(R.id.info);
        //Disease disease = db.getdisease(1);
        //Toast.makeText(getContext(),disease.getName()+" "+disease.get_type(),Toast.LENGTH_LONG).show();
        //textView.setText(disease.getName()+" "+disease.get_type());
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void showDiseaseName(){
        Toast.makeText(getContext(),name,Toast.LENGTH_LONG).show();
        String dis = "";
        dis+=name+"\n";
        dis+=type+"\n";
        dis+=symptoms+"\n";
        dis+=cause+"\n";
        dis+=firstaid+"\n";
        dis+=treatment+"\n";

        //textView.setText(dis);
    }
}
