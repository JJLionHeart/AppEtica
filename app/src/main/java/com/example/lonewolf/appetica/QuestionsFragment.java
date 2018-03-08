package com.example.lonewolf.appetica;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // Question bank, maybe you want to think of a better way of implementing it in the future
    String[][] question_bank = new String[][] {{"No me siento triste", "Me siento triste",
             "Me siento triste todo el tiempo y no puedo librarme de ello",
             "Me siento tan triste o desdichado que no puedo soportarlo"},
             {"No estoy particularmente desanimado con respecto al futuro",
               "Me siento desanimado respecto al futuro",
             "Siento que no puedo esperar nada del futuro",
             "Siento que el futuro es irremediable y que las cosas no pueden mejorar."}};

    private RadioGroup answers = null;
    private RadioButton answer_1 = null;
    private RadioButton answer_2 = null;
    private RadioButton answer_3 = null;
    private RadioButton answer_4 = null;

    private ImageButton next_question = null;
    private ImageButton previous_question = null;

    private int current_question;

    public QuestionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionsFragment newInstance(String param1, String param2) {
        QuestionsFragment fragment = new QuestionsFragment();
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
        View view = inflater.inflate(R.layout.fragment_questions, container, false);

        answers = (RadioGroup) view.findViewById(R.id.answers_group);
        answer_1 = (RadioButton) view.findViewById(R.id.answer_1);
        answer_2 = (RadioButton) view.findViewById(R.id.answer_2);
        answer_3 = (RadioButton) view.findViewById(R.id.answer_3);
        answer_4 = (RadioButton) view.findViewById(R.id.answer_4);

        current_question = 0;

        next_question = (ImageButton) view.findViewById(R.id.next_question);
        previous_question = (ImageButton) view.findViewById(R.id.previous_question);

        next_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current_question != question_bank.length-1){
                    current_question++;
                    change_question(current_question);
                }
            }
        });

        previous_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(current_question != 0){
                    current_question--;
                    change_question(current_question);
                }
            }
        });

        change_question(current_question);
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

    private void change_question(int question_id){
        if(answer_1 != null){
            answer_1.setText(question_bank[question_id][0]);
        }

        if(answer_2 != null){
            answer_2.setText(question_bank[question_id][1]);
        }

        if(answer_3 != null){
            answer_3.setText(question_bank[question_id][2]);
        }

        if(answer_4 != null){
            answer_4.setText(question_bank[question_id][3]);
        }

        if(question_id == 0 && previous_question != null){
            previous_question.setVisibility(View.GONE);
        } else if(question_id != 0){
            previous_question.setVisibility(View.VISIBLE);
        }

        if(question_id == question_bank.length-1 && next_question != null){
            next_question.setVisibility(View.GONE);
        } else if(question_id != question_bank.length){
            next_question.setVisibility(getView().VISIBLE);
        }
    }
}
