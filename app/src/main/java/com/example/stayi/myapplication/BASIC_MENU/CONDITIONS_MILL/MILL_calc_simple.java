package com.example.stayi.myapplication.BASIC_MENU.CONDITIONS_MILL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stayi.myapplication.R;
import com.example.stayi.myapplication.nav_var_storage;

import java.util.Objects;
import static android.view.KeyEvent.*;

import static com.example.stayi.myapplication.R.id.action_MILL_calc_simple_to_MILL_calc_detail2;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MILL_calc_simple.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MILL_calc_simple#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MILL_calc_simple extends Fragment {

    boolean use_var = true;
    private EditText editText_tool_diameter;
    private EditText editText_tool_speed;
    private EditText editText_tool_rev;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public MILL_calc_simple() {
        // Required empty public constructor
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
    public static MILL_calc_simple newInstance(String param1, String param2) {
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
        if (getArguments () != null) {
            mParam1 = getArguments ().getString (ARG_PARAM1);
            mParam2 = getArguments ().getString (ARG_PARAM2);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.mill_calc_simple, container, false);

        editText_tool_diameter = rootView.findViewById(R.id.editText_Mill_Diameter);
        editText_tool_speed = rootView.findViewById(R.id.editText2_Speed);
        editText_tool_rev = rootView.findViewById(R.id.editText3_rev);

        editText_tool_diameter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editText_tool_diameter.getText().length() == 0 && use_var) {
                    editText_tool_diameter.setText("0");
                    editText_tool_diameter.setSelection(editText_tool_diameter.getText().length());
                }
                use_var = true;
            }
        });
        editText_tool_diameter.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                String ret = String.valueOf(keyCode);
                //Toast.makeText(getContext(), ret, Toast.LENGTH_LONG).show();
                char zero = '0';
                char point = '.';
                String empty = "";
                String val = String.valueOf(editText_tool_diameter.getText());
                if (val.equals(String.valueOf(zero)) && (keyCode == KEYCODE_0 || keyCode == KEYCODE_DEL))
                    return true;
                if (val.equals(String.valueOf(zero)) && keyCode != KEYCODE_NUMPAD_DOT && editText_tool_diameter.getSelectionEnd() == 1) {
                    use_var = false;
                    editText_tool_diameter.setText(empty);
                }
                if (keyCode == KEYCODE_NUMPAD_DOT) {
                    for (int i = 0; i < val.length(); ++i) {
                        if (val.charAt(i) == point) return true;
                    }
                }
                if (val.charAt(0) == zero && val.charAt(0) != point && editText_tool_diameter.getSelectionEnd() == 1 && keyCode == KEYCODE_0) {
                    return true;
                }
                if (val.charAt(0) == zero && editText_tool_diameter.getSelectionEnd() == 0 && keyCode == KEYCODE_0) {
                    return true;
                }
                return false;
            }
        });
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
    public void onDetach() {
        super.onDetach ();
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

        void onClick();
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
        //item.setChecked(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
