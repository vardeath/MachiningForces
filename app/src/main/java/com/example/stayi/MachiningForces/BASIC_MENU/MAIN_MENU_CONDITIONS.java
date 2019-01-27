package com.example.stayi.MachiningForces.BASIC_MENU;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.stayi.MachiningForces.R;

import java.util.Objects;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import static com.example.stayi.MachiningForces.R.id.action_MAIN_MENU_CONDITIONS_to_MAIN_MILL_MENU2;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MAIN_MENU_CONDITIONS.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MAIN_MENU_CONDITIONS#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MAIN_MENU_CONDITIONS extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    NavController navController;

    public MAIN_MENU_CONDITIONS() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MAIN_MENU_CONDITIONS.
     */
    // TODO: Rename and change types and number of parameters
    private static MAIN_MENU_CONDITIONS newInstance(String param1, String param2) {
        MAIN_MENU_CONDITIONS fragment = new MAIN_MENU_CONDITIONS();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
        View rootViewA = (View) inflater.inflate (
                R.layout.main_menu_conditions, container, false);
        Button btn_GoMillMainMenu = rootViewA.findViewById(R.id.GOtoMILL);
        navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.fragment);
        btn_GoMillMainMenu.setOnClickListener(this);
        return rootViewA;
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
            case R.id.GOtoMILL:
                navController.navigate(action_MAIN_MENU_CONDITIONS_to_MAIN_MILL_MENU2);
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