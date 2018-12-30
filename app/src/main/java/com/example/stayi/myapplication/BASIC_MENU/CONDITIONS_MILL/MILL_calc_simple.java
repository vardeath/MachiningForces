package com.example.stayi.myapplication.BASIC_MENU.CONDITIONS_MILL;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.stayi.myapplication.FieldBaseObject;
import com.example.stayi.myapplication.FieldOnClickListener;
import com.example.stayi.myapplication.FragmentAdaptor;
import com.example.stayi.myapplication.FragmentField.FieldType;
import com.example.stayi.myapplication.R;
/*import com.example.stayi.myapplication.keyboard_listener;*/
import com.example.stayi.myapplication.keyboard_listener;
import com.example.stayi.myapplication.nav_var_storage;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import static com.example.stayi.myapplication.R.id.action_MILL_calc_simple_to_MILL_calc_detail2;

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

        //Инициализация полей TextVIew для хранения и ввода данных.
        List<FieldBaseObject> BaseFieldObjects = new ArrayList<FieldBaseObject>();
        BaseFieldObjects.add(new FieldBaseObject(R.id.TW_Mill_Diameter, FieldType.Diameter));
        BaseFieldObjects.add(new FieldBaseObject(R.id.TW_vc_speed, FieldType.CuttingSpeed));
        BaseFieldObjects.add(new FieldBaseObject(R.id.TW_n_rev, FieldType.Revolution));
        BaseFieldObjects.add(new FieldBaseObject(R.id.TW_n_teeth, FieldType.Teeth));
        BaseFieldObjects.add(new FieldBaseObject(R.id.TW_fz_feed, FieldType.ToothFeed));
        BaseFieldObjects.add(new FieldBaseObject(R.id.TW_min_feed, FieldType.MinuteFeed));


        FragmentAdaptor ButHoldAdapt = new FragmentAdaptor(BaseFieldObjects,rootView, getContext());

        ButHoldAdapt.setRelativeButton(R.id.rehold_but_millsimple_1, R.id.TW_vc_speed, R.id.TW_n_rev, 2);
        ButHoldAdapt.setRelativeButton(R.id.rehold_but_millsimple_2, R.id.TW_fz_feed, R.id.TW_min_feed, 2);

        FieldOnClickListener FragmentOnClicklList =  new FieldOnClickListener(ButHoldAdapt);

        //Инициализация слушателя кастомной клавиатуры.
        View key_board = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_sheet);
        keyboard_listener board = new keyboard_listener(key_board, ButHoldAdapt);
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
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
            super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

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
        navController.navigate(action_MILL_calc_simple_to_MILL_calc_detail2);
        }
        return super.onOptionsItemSelected(item);
    }

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