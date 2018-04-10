package com.example.lonewolf.appetica;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TutorialsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TutorialsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TutorialsFragment extends Fragment {


    private ListView tutorials_list_view = null;
    private ArrayList<TutorialList> tutorial_list = null;
    private OnFragmentInteractionListener mListener;

    public TutorialsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TutorialsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TutorialsFragment newInstance(String param1, String param2) {
        TutorialsFragment fragment = new TutorialsFragment();
        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

        getActivity().setTitle("Tutoriales");





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tutorials, container, false);
        tutorial_list = new ArrayList<TutorialList>();

        // Instantiation of all the tutorials

        tutorial_list.add(new TutorialList("Técnicas de respiración", getResources().getDrawable(R.drawable.breath_symbol)));

        tutorials_list_view = (ListView) view.findViewById(R.id.tutorials_list);

        AdapterTutorial adapter = new AdapterTutorial(getActivity(), tutorial_list);

        tutorials_list_view.setAdapter(adapter);
        tutorials_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fragment selected_tutorial = null;
                switch(i){
                    case 0:
                        selected_tutorial = new TutorialRespiracion();
                        break;
                    default:
                        selected_tutorial = null;
                        break;
                }
                if(selected_tutorial != null){




                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                    ft.replace(R.id.main_container, selected_tutorial);
                    ft.addToBackStack(null);

                    ft.commit();
                }
            }
        });

        return view;
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
        void onFragmentInteraction(Uri uri);
    }

    }
