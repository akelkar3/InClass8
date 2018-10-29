package com.mad.homeworkgroup20.inclass8;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
/*Assignment no. 8
group: ankit kelkar, shubhra mishra*/

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MainFragment extends Fragment {
String TAG="test";
    private OnFragmentInteractionListener mListener;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            //mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "AFragment onCreateView");
        final EditText etName = getActivity().findViewById(R.id.Name);
        final EditText etEmail =  getActivity().findViewById(R.id.Mail);
        final RadioGroup rgDepartment =  getActivity().findViewById(R.id.rgDepartment);
        final Button btnSubmit =  getActivity().findViewById(R.id.Submit);
        final SeekBar seekbar =  getActivity().findViewById(R.id.MoodseekBar);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student =  new Student();
                try{
                    if(etName.getText().toString().length()>0){
                        student.name =etName.getText().toString();
                    }else{
                        etName.setError("Required");
                        throw(new Exception("Required Field not Found"));
                    }

                    if(etEmail.getText().toString().length()>0){
                        String email = etEmail.getText().toString();

                        if(checkValidEmail(email)) {
                            student.email =email;
                        }else{
                            etEmail.setError("Valid Email Required");
                            throw(new Exception("Valid Email not Found"));
                        }
                    }else{
                        etEmail.setError("Required");
                        throw(new Exception("Required Field not Found"));
                    }

                    if(seekbar.getProgress()>0){
                        student.mood = seekbar.getProgress()+" % Positive";
                    } else if(seekbar.getProgress()==0){
                        student.mood = "0 % Positive";
                    }

                    student.dept=getRGDepartment(rgDepartment);
                    mListener.onFragmentInteraction(student);



                }catch(Exception e){
                    Log.d("Catch",e.getMessage());
                }


            }
        });
    }



    public final static boolean checkValidEmail(CharSequence input) {
        return (!TextUtils.isEmpty(input) && Patterns.EMAIL_ADDRESS.matcher(input).matches());
    }

    public static String getRGDepartment(RadioGroup rgDepartment){
        String selectedDept="";
        switch(rgDepartment.getCheckedRadioButtonId()){
            case (R.id.SIS):

                selectedDept = "SIS";
                break;
            case (R.id.CS):

                selectedDept = "CS";
                break;
            case (R.id.Bio):

                selectedDept = "BIO";
                break;
            case (R.id.Others):

                selectedDept = "OTHERS";
                break;
            case (-1):
                selectedDept = "";
                break;
        }
        return selectedDept;
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
        void onFragmentInteraction(Student uri);

    }
}
