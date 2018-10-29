package com.mad.homeworkgroup20.inclass8;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/*Assignment no. 8
group: ankit kelkar, shubhra mishra*/
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DisplayFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_STD = "student";
    private TextView NameValue;
    private TextView EmailValue;
    private TextView DepartmentValue;
    private TextView Mood;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Student student;

    private OnFragmentInteractionListener mListener;

    public DisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment DisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayFragment newInstance(Student student) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();

        args.putSerializable(ARG_STD,student);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            student= (Student) getArguments().getSerializable(ARG_STD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_display, container, false);
        getActivity().setTitle("Display Information");
        NameValue =  view.findViewById(R.id.NameValue);
        EmailValue =  view.findViewById(R.id.EmailValue);
        DepartmentValue =  view.findViewById(R.id.DepartmentValue);
        Mood =  view.findViewById(R.id.MoodValue);
        NameValue.setText(student.name);
        NameValue.setRight(View.TEXT_ALIGNMENT_TEXT_START);
        EmailValue.setText(student.email);
        EmailValue.setRight(View.TEXT_ALIGNMENT_TEXT_START);
        DepartmentValue.setText(student.dept);
        DepartmentValue.setRight(View.TEXT_ALIGNMENT_TEXT_START);
        Mood.setText(student.mood);
        Mood.setRight(View.TEXT_ALIGNMENT_TEXT_START);


        //code for image-views
        final ImageView editImage1 = view.findViewById(R.id.editImageView1);
        final ImageView editImage2 = view.findViewById(R.id.editImageView2);
        final ImageView editImage3 =  view.findViewById(R.id.editImageView3);
        final ImageView editImage4 =  view.findViewById(R.id.editImageView4);
        editImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDisplayFragmentInteraction("name");
            }
        });

        editImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onDisplayFragmentInteraction("email");
            }
        });
        editImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onDisplayFragmentInteraction("department");
            }
        });
        editImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mListener.onDisplayFragmentInteraction("mood");
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onDisplayFragmentInteraction(uri);
//        }
//    }

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
        void onDisplayFragmentInteraction(String uri);
    }
}
