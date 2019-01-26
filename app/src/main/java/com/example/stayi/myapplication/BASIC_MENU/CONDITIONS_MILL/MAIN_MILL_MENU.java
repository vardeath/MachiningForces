package com.example.stayi.myapplication.BASIC_MENU.CONDITIONS_MILL;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.stayi.myapplication.R;

import java.util.Objects;

import static com.example.stayi.myapplication.R.id.action_MAIN_MILL_MENU2_to_MILL_calc_simple;
import static com.example.stayi.myapplication.R.id.action_MAIN_MILL_MENU2_to_MILL_calc_detail;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MAIN_MILL_MENU.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MAIN_MILL_MENU#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MAIN_MILL_MENU extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private NavController navController;


    public MAIN_MILL_MENU() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MAIN_MILL_MENU.
     */
    // TODO: Rename and change types and number of parameters
    public static MAIN_MILL_MENU newInstance(String param1, String param2) {
        MAIN_MILL_MENU fragment = new MAIN_MILL_MENU();
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootViewA = (View) inflater.inflate(
                R.layout.fragment_main__mill__menu, container, false);
        Button but_MILL_Simple = rootViewA.findViewById(R.id.GOTOMILLSIMPLE);
        but_MILL_Simple.setOnClickListener(this);
        Button but_MILL_Detail = rootViewA.findViewById(R.id.GOTOMILLDETAIL);
        but_MILL_Detail.setOnClickListener(this);
        navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.fragment);

        // Inflate the layout for this fragment
        return rootViewA;

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

    @Override
    public void onClick(View v) {
        int ID = v.getId();
        switch (ID) {
            case R.id.GOTOMILLSIMPLE:
                navController.navigate(action_MAIN_MILL_MENU2_to_MILL_calc_simple);
                break;
            case R.id.GOTOMILLDETAIL:
                navController.navigate(action_MAIN_MILL_MENU2_to_MILL_calc_detail);
                break;
        }
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
