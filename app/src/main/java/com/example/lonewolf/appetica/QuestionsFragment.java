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
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuestionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuestionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionsFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    // Question bank, maybe you want to think of a better way of implementing it in the future
    String[][] question_bank = new String[][] {
            {"No me siento triste", "Me siento triste",
             "Me siento triste todo el tiempo y no puedo librarme de ello",
             "Me siento tan triste o desdichado que no puedo soportarlo"},

             {"No estoy particularmente desanimado con respecto al futuro",
               "Me siento desanimado respecto al futuro",
             "Siento que no puedo esperar nada del futuro",
             "Siento que el futuro es irremediable y que las cosas no pueden mejorar."},

            {"No me siento fracasado",
             "Siento que he fracasado más que una persona normal",
             "Cuando miro hacia el pasado lo único que puedo ver en mi vida es un montón de fracasos",
             "Siento que como persona soy un fracaso completo"},

            {"Sigo obteniendo tanto placer de las cosas como antes",
             "No disfruto de las cosas como solía hacerlo",
             "Ya nada me satisface realmente",
             "Todo me aburre o me desagrada"},

            {"No siento ninguna culpa particular",
             "Me siento culpable buena parte del tiempo",
             "Me siento bastante culpable la mayor parte del tiempo",
             "Me siento culpable todo el tiempo"},

            {"No siento que esté siendo castigado",
             "Siento que puedo estar siendo castigado",
             "Espero ser castigado",
             "Siento que estoy siendo castigado"},

            {"No me siento decepcionado en mí mismo",
             "Estoy decepcionado conmigo",
             "Estoy harto de mí mismo",
             "Me odio a mí mismo"},

            {"No me siento peor que otros",
             "Me critico por mis debilidades o errores",
             "Me culpo todo el tiempo por mis faltas",
             "Me culpo por todas las cosas malas que suceden"},

            {"No tengo ninguna idea de matarme",
             "Tengo ideas de matarme, pero no las llevo a cabo",
             "Me gustaría matarme",
             "Me mataría si tuviera la oportunidad"},

            {"No lloro más de lo habitual",
             "Lloro más que antes",
             "Ahora lloro todo el tiempo",
             "Antes era capáz de llorar, pero ahora no puedo llorar nunca aunque quisiera"},

            {"No me irrito más ahora que antes",
             "Me enojo o irrito más fácilmente ahora que antes",
             "Me siento irritado todo el tiempo",
             "No me irrito para nada con las cosas que solían irritarme"},

            {"No he perdido interés en otras personas",
             "Estoy menos interesado en otras personas de lo que solía estar",
             "He perdido la mayor parte de mi interés en los demás",
             "He perdido todo mi interés en los demás"},

            {"Tomo decisiones como siempre",
             "Dejo de tomar decisiones más frecuentemente que antes",
             "Tengo mayor dificultad que antes en tomar decisiones",
             "Ya no puedo tomar ninguna decisión"},

            {"No creo que me va peor que antes",
             "Me preocupa que esté pareciendo avejentado o inatractivo",
             "Siento que hay cambios permanentes en mi apariencia que me hacen parecer inatractivo",
             "Creo que me veo horrible"},

            {"Puedo trabajar tan bien como antes",
             "Me cuesta un mayor esfuerzo empezar a hacer algo",
             "Tengo que hacer un gran esfuerzo para hacer cualquier cosa",
             "No puedo hacer ningún tipo de trabajo"},

            {"Puedo dormir tan bien como antes",
             "No duermo tan bien como antes",
             "Me despierto 1 o 2 horas más temprano de lo habitual y me cuesta volver a dormir",
             "Me despierto varias horas más temprano de lo habitual y no puedo volver a dormirme"},

            {"No me canso más de lo habitual",
             "Me canso más fácilmente de lo que solía cansarme",
             "Me canso al hacer cualquier cosa",
             "Estoy demasiado cansado para hacer cualquier cosa"},

            {"Mi apetito no ha variado",
             "Mi apetito no es tan bueno como antes",
             "Mi apetito es mucho peor que antes",
             "Ya no tengo nada de apetito"},

            {"Últimamente no he perdido mucho peso, si es que perdí algo",
             "He perdido más de 2 kilos",
             "He perdido más de 4 kilos",
             "He perdido más de 6 kilos"},

            {"No estoy más preocupado por mi salud de lo habitual",
             "Estoy preocupado por problemas físicos tales como malestares y dolores de estómago o constipación",
             "Estoy muy preocupado por problemas físicos y es difícil pensar en otra cosa",
             "EStoy tan preocupado por mis problemas físicos que no puedo pensar en nada más"},

            {"No he notado cambio reciente de mi interés por el sexo",
             "Estoy menos interesado por el sexo de lo que solía estar",
             "Estoy mucho menos interesado por el sexo ahora",
             "He perdido por completo mi interés por el sexo"}

    };

    private RadioGroup answers = null;
    private RadioButton answer_1 = null;
    private RadioButton answer_2 = null;
    private RadioButton answer_3 = null;
    private RadioButton answer_4 = null;

    private int[] selected_answers = null;

    private ImageButton next_question = null;
    private ImageButton previous_question = null;

    private TextView question_count = null;

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

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

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

        selected_answers = new int[question_bank.length];

        answer_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_answers[current_question] = 1;
            }
        });

        answer_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_answers[current_question] = 2;
            }
        });

        answer_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_answers[current_question] = 3;
            }
        });

        answer_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_answers[current_question] = 4;
            }
        });


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

        question_count = (TextView) view.findViewById(R.id.question_count);

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
            if(selected_answers[question_id] == 0){
                answers.clearCheck();
            } else if(selected_answers[question_id] == 1){
                answers.check(R.id.answer_1);
            }
        }

        if(answer_2 != null){
            answer_2.setText(question_bank[question_id][1]);
            if(selected_answers[question_id] == 0){
                answers.clearCheck();
            } else if(selected_answers[question_id] == 2){
                answers.check(R.id.answer_2);
            }
        }

        if(answer_3 != null){
            answer_3.setText(question_bank[question_id][2]);
            if(selected_answers[question_id] == 0){
                answers.clearCheck();
            } else if(selected_answers[question_id] == 3){
                answers.check(R.id.answer_3);
            }
        }

        if(answer_4 != null){
            answer_4.setText(question_bank[question_id][3]);
            if(selected_answers[question_id] == 0){
                answers.clearCheck();
            } else if(selected_answers[question_id] == 4){
                answers.check(R.id.answer_4);
            }
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
        if(question_count != null) {
            question_count.setText(Integer.toString(question_id + 1) + " / " + Integer.toString(question_bank.length));
        }
    }


}
