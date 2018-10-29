package com.mad.homeworkgroup20.inclass8;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/*Assignment no. 8
group: ankit kelkar, shubhra mishra*/
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_STUDENT= "student";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private Student mStudent;

    private OnFragmentInteractionListener mListener;

    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.

     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1,Student student) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_STUDENT, student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mStudent = (Student) getArguments().getSerializable(ARG_STUDENT);
        }
    }
    private RadioGroup Departmentgroup;
    public EditText etName;
    public EditText etEmail;
    public SeekBar seekbar;
    public TextView tvDept;
    public TextView tvMoodText;
    public Button btnSave;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_edit, container, false);
        getActivity().setTitle("Edit Information");
         etName = view.findViewById(R.id.editName);
        etEmail = view.findViewById(R.id.editMail);
        Departmentgroup = view.findViewById(R.id.editrgDepartment);
         seekbar= view.findViewById(R.id.editMoodseekBar);
         tvDept = view.findViewById(R.id.editDepartment);
         tvMoodText = view.findViewById(R.id.MoodText);
         btnSave = view.findViewById(R.id.saveBtn);
        ResetDisplay();
        SetDisplay(mParam1);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Student student =  new Student();
                try{
                    if (mParam1.toString().equalsIgnoreCase("name")) {
                        if (etName.getText().toString().length() > 0) {
                            mStudent.name = etName.getText().toString();
                        } else {
                            etName.setError("Required");
                            throw (new Exception("name Required Field not Found"));
                        }
                    }
                    if (mParam1.toString().equalsIgnoreCase("email")) {
                        if (etEmail.getText().toString().length() > 0) {
                            String email = etEmail.getText().toString();

                            if (MainFragment.checkValidEmail(email)) {
                                mStudent.email = email;
                            } else {
                                etEmail.setError("Valid Email Required");
                                throw (new Exception("Valid Email not Found"));
                            }
                        } else {
                            etEmail.setError("Required");
                            throw (new Exception("email Required Field not Found"));
                        }
                    }
                    if (mParam1.toString().equalsIgnoreCase("mood")) {
                        if (seekbar.getProgress() > 0) {
                            mStudent.mood = seekbar.getProgress() + " % Positive";
                        } else if (seekbar.getProgress() == 0) {
                            mStudent.mood = "0 % Positive";
                        }
                    }
                    if(mParam1.toString().equalsIgnoreCase("department"))
                    mStudent.dept=MainFragment.getRGDepartment(Departmentgroup);

                    mListener.onEditFragmentInteraction(mStudent);



                }catch(Exception e){
                    Log.d("Catch",e.getMessage());
                }


            }
        });

        return  view;
    }
public void ResetDisplay(){
    etName.setVisibility(View.INVISIBLE);
    etEmail.setVisibility(View.INVISIBLE);
    seekbar.setVisibility(View.INVISIBLE);
    Departmentgroup.setVisibility(View.INVISIBLE);
    tvDept.setVisibility(View.INVISIBLE);
    tvMoodText.setVisibility(View.INVISIBLE);
}

public void SetDisplay(String input){
    switch(input.toLowerCase()){
        case ("name"):

            etName.setText(mStudent.name);
            etName.setVisibility(View.VISIBLE);
            break;
        case ("email"):

            etEmail.setText(mStudent.email);
            etEmail.setVisibility(View.VISIBLE);
            break;
        case ("department"):

            switch (mStudent.dept){
                case ("SIS"):
                    Departmentgroup.check(R.id.SIS);
                    break;
                case ("CS"):
                    Departmentgroup.check(R.id.CS);
                    break;
                case ("BIO"):
                    Departmentgroup.check(R.id.Bio);
                    break;
                case ("OTHERS"):
                    Departmentgroup.check(R.id.Others);
                    break;
            }
            tvDept.setVisibility(View.VISIBLE);
            Departmentgroup.setVisibility(View.VISIBLE);
            break;

        case ("mood"):
            String progress =mStudent.mood.replace(" % Positive","");
            seekbar.setProgress(Integer.parseInt(progress));
            tvMoodText.setVisibility(View.VISIBLE);
            seekbar.setVisibility(View.VISIBLE);
            break;

    }

}

    // TODO: Rename method, update argument and hook method into UI event


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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onEditFragmentInteraction(Student student);
    }
}
