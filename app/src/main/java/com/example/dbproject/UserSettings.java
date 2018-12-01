package com.example.dbproject;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link UserSettings.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link UserSettings#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserSettings extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "UserID";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int userID;
    private String mParam2;

    //User
    User user = null;
    ImageButton lockButton = null;
    ImageView avatar = null;
    EditText name = null;
    EditText password = null;
    EditText email = null;
    EditText phoneNumber = null;
    boolean canEditUserInfo = false;

    private OnFragmentInteractionListener mListener;
    GamePickerDatabase gamePickerDatabase = null;

    public UserSettings() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserSettings.
     */
    // TODO: Rename and change types and number of parameters
    public static UserSettings newInstance(String param1, String param2) {
        UserSettings fragment = new UserSettings();
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

            userID = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        gamePickerDatabase = GamePickerDatabase.getInstance(getContext());
        return inflater.inflate(R.layout.fragment_user_settings, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lockButton = view.findViewById(R.id.imageLock);
        avatar = view.findViewById(R.id.imageViewAvatar);
        name = view.findViewById(R.id.editName);
        password = view.findViewById(R.id.editPassword);
        email = view.findViewById(R.id.editEmail);
        phoneNumber = view.findViewById(R.id.editPhoneNumber);

        user = gamePickerDatabase.getUserDao().getUser(userID);
        if (user != null){
            name.setText(user.getUserName());
            password.setText(user.getUserPassword());
            email.setText(user.getUserEmail());
            phoneNumber.setText(user.getUserPhoneNumber());
        }
        name.setEnabled(canEditUserInfo);
        password.setEnabled(canEditUserInfo);
        email.setEnabled(canEditUserInfo);
        phoneNumber.setEnabled(canEditUserInfo);

        lockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canEditUserInfo == true){
                    canEditUserInfo = false;
                    lockButton.setImageResource(R.drawable.baselinelockblack18dp);

                    user.setUserName(name.getText().toString());
                    user.setUserPassword(password.getText().toString());
                    user.setUserEmail(email.getText().toString());
                    user.setUserPhoneNumber(phoneNumber.getText().toString());

                    gamePickerDatabase.getUserDao().update(user);
                    Toast.makeText(v.getContext(),"User updated.", Toast.LENGTH_SHORT).show();

                }else{
                    canEditUserInfo = true;
                    lockButton.setImageResource(R.drawable.baseline_save_black_18dp);
                }

                name.setEnabled(canEditUserInfo);
                password.setEnabled(canEditUserInfo);
                email.setEnabled(canEditUserInfo);
                phoneNumber.setEnabled(canEditUserInfo);
            }
        });


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
