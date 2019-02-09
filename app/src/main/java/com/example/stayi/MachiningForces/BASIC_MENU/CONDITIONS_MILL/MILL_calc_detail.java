package com.example.stayi.MachiningForces.BASIC_MENU.CONDITIONS_MILL;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import java.lang.FunctionalInterface;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Method.*;
import java.util.function.*;

import com.example.stayi.MachiningForces.ConditionsModule.FieldBaseObject;
import com.example.stayi.MachiningForces.ConditionsModule.FragmentAdaptor;
import com.example.stayi.MachiningForces.ConditionsModule.KeyboardListener;
import com.example.stayi.MachiningForces.CustomView.CustomOutputField;
import com.example.stayi.MachiningForces.CustomView.CustomFieldInitializer;
import com.example.stayi.MachiningForces.CustomView.CustomInputField;
import com.example.stayi.MachiningForces.Enumerations.ButtonLockPosition;
import com.example.stayi.MachiningForces.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import static com.example.stayi.MachiningForces.Enumerations.ConditionsPreset.MillDetail;
import static com.example.stayi.MachiningForces.Enumerations.FieldType.MillCuttingSpeed;
import static com.example.stayi.MachiningForces.Enumerations.FieldType.MillMinuteFeed;
import static com.example.stayi.MachiningForces.Enumerations.FieldType.MillRevolutionQuantity;
import static com.example.stayi.MachiningForces.Enumerations.FieldType.MillToothFeed;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MILL_calc_detail.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MILL_calc_detail#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MILL_calc_detail extends Fragment implements Runnable {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MILL_calc_detail() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MILL_calc_detail.
     */
    // TODO: Rename and change types and number of parameters
    private static MILL_calc_detail newInstance(String param1, String param2) {
        MILL_calc_detail fragment = new MILL_calc_detail();
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
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.mill_calc_detail, container, false);
        ScrollView myScroll = rootView.findViewById(R.id.millScroll);

        String TAG = String.valueOf(MillDetail); //Тэг используется для создания имени переменных для хранения значений полей ввода.

        List<CustomInputField> mCustomInputField = new ArrayList<>();
        mCustomInputField.add(rootView.findViewById(R.id.MillField1));
        mCustomInputField.add(rootView.findViewById(R.id.MillField2));
        mCustomInputField.add(rootView.findViewById(R.id.MillField3));
        mCustomInputField.add(rootView.findViewById(R.id.MillField4));
        mCustomInputField.add(rootView.findViewById(R.id.MillField5));
        mCustomInputField.add(rootView.findViewById(R.id.MillField6));
        mCustomInputField.add(rootView.findViewById(R.id.MillField7));
        mCustomInputField.add(rootView.findViewById(R.id.MillField8));
        mCustomInputField.add(rootView.findViewById(R.id.MillField9));
        mCustomInputField.add(rootView.findViewById(R.id.MillField10));
        mCustomInputField.add(rootView.findViewById(R.id.MillField11));
        mCustomInputField.add(rootView.findViewById(R.id.MillField12));
        mCustomInputField.add(rootView.findViewById(R.id.MillField13));

        List<CustomOutputField> mCustomOutputField = new ArrayList<>();
        mCustomOutputField.add(rootView.findViewById(R.id.MillField14));
        mCustomOutputField.add(rootView.findViewById(R.id.MillField15));
        mCustomOutputField.add(rootView.findViewById(R.id.MillField16));
        mCustomOutputField.add(rootView.findViewById(R.id.MillField17));
        mCustomOutputField.add(rootView.findViewById(R.id.MillField18));

        /*LinearLayout Underground = rootView.findViewById(R.id.LAY_RESULTS);
        Underground.getLayoutParams().height = 0;*/

        try {

            CustomFieldInitializer CustomViewArr = new CustomFieldInitializer(getContext(), mCustomInputField, MillDetail);
            CustomViewArr.setCustomOutputObjectsFields(mCustomOutputField);
            CustomViewArr.setRelativeButton(R.id.HoldButton1, MillCuttingSpeed, MillRevolutionQuantity, ButtonLockPosition.TWO);
            CustomViewArr.setRelativeButton(R.id.HoldButton2, MillToothFeed, MillMinuteFeed, ButtonLockPosition.TWO);

            List<FieldBaseObject> BaseFieldObjects = CustomViewArr.getBaseFieldObjects();

            FragmentAdaptor ButHoldAdapt = new FragmentAdaptor(BaseFieldObjects, rootView, getContext(), TAG);
            ButHoldAdapt.setRelativeButton(CustomViewArr.getRelativeButtonArr());

            View key_board = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_sheet);
            KeyboardListener board = new KeyboardListener(key_board, ButHoldAdapt, getContext(), myScroll);

        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return rootView;
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
    public void run() {

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
    /*
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_mill_detail);
        item.setChecked(true);
    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Запоминаем состояние меню фрагмента.
        if (id !=R.id.action_mill_detail) {
            Storage.init(getContext());
            Storage.addProperty("hasVisited", true);
            NavController navController;
            navController = Navigation.findNavController(Objects.requireNonNull(getActivity()), R.id.fragment);
            //navController.navigate(action_MILL_calc_detail_to_MILL_calc_simple);
            //item.setChecked(true);
        }
        return super.onOptionsItemSelected(item);
    }
    */
}