package com.example.stayi.MachiningForces.BASIC_MENU.CONDITIONS_MILL;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stayi.MachiningForces.FieldBaseObject;
import com.example.stayi.MachiningForces.FragmentAdaptor;
import com.example.stayi.MachiningForces.FragmentField.FieldType;
import com.example.stayi.MachiningForces.KeyboardListener;
import com.example.stayi.MachiningForces.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MILL_calc_simple.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MILL_calc_simple#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MILL_calc_simple extends Fragment implements View.OnClickListener {

    private int FRAGMENT_ID;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private OnFragmentInteractionListener mListener;

    private TextView[] FIXIES;
    private boolean[] Fix_values;

    public MILL_calc_simple() {
        FRAGMENT_ID = R.id.MILL_calc_simple;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MILL_calc_simple.
     */
    // TODO: Rename and change types and number of parameters
    private static MILL_calc_simple newInstance(String param1, String param2) {
        MILL_calc_simple fragment = new MILL_calc_simple ();
        Bundle args = new Bundle ();
        args.putString (ARG_PARAM1, param1);
        args.putString (ARG_PARAM2, param2);
        fragment.setArguments (args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        /*if (getArguments () != null) {
        }*/
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.mill_calc_simple, container, false);

        String TAG = "MillSimple"; //Тэг используется для создания имени переменных для хранения значений полей ввода.

        //Инициализация полей TextVIew для хранения и ввода данных.
        List<FieldBaseObject> BaseFieldObjects = new ArrayList<FieldBaseObject>();
        BaseFieldObjects.add(new FieldBaseObject(R.id.MillDiameter, FieldType.Diameter));
        BaseFieldObjects.add(new FieldBaseObject(R.id.CuttingSpeed, FieldType.CuttingSpeed));
        BaseFieldObjects.add(new FieldBaseObject(R.id.Revolution, FieldType.Revolution));
        BaseFieldObjects.add(new FieldBaseObject(R.id.Teeth, FieldType.Teeth));
        BaseFieldObjects.add(new FieldBaseObject(R.id.ToothFeed, FieldType.ToothFeed));
        BaseFieldObjects.add(new FieldBaseObject(R.id.MinuteFeed, FieldType.MinuteFeed));

        FragmentAdaptor ButHoldAdapt = new FragmentAdaptor(BaseFieldObjects, rootView, getContext(), TAG);
        ButHoldAdapt.setRelativeButton(R.id.HoldCutRev, R.id.CuttingSpeed, R.id.Revolution, FragmentAdaptor.Position_TWO);
        ButHoldAdapt.setRelativeButton(R.id.HoldButton2, R.id.ToothFeed, R.id.MinuteFeed, FragmentAdaptor.Position_TWO);

        /**Инициализация слушателя кастомной клавиатуры.*/
        View key_board = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_sheet);
        KeyboardListener board = new KeyboardListener(key_board, ButHoldAdapt, getContext());
        LinearLayout llBottomSheet = getActivity().findViewById(R.id.bottom_sheet);
        BottomSheetBehavior behavior = BottomSheetBehavior.from(llBottomSheet);
        behavior.setPeekHeight(700);
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction (uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach (context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException (context.toString ()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    /*
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_mill_simple);
        item.setChecked(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id != R.id.action_mill_simple) {
        //Запоминаем состояние меню фрагмента.
        nav_var_storage.init(getContext());
        nav_var_storage.addProperty("hasVisited", false);
        NavController navController;
        navController = Navigation.findNavController (Objects.requireNonNull (getActivity ()), R.id.fragment);
        //navController.navigate(action_MILL_calc_simple_to_MILL_calc_detail2);
        }
        return super.onOptionsItemSelected(item);
    }
    */
    @Override
    public void onClick(View v) {

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

        void onClick();
    }

    @Override
    public void onPause() {
        super.onPause();
        super.onStop();
    }
}