package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class signup extends Fragment implements View.OnClickListener{

    private EditText firstName;
    private EditText lastName;
    private  EditText password;
    private EditText confirmPassword;
    private Button signUp;
    TextView signup;
    private FirebaseAuth auth;
    public signup() {
        //firstName = findViewById(R.id.SignupFirstName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        // Inflate the layout for this fragment
        firstName = view.findViewById(R.id.SignupFirstName);
        lastName = view.findViewById(R.id.SignupLastName);
        password = view.findViewById(R.id.SignupPassword);
        confirmPassword = view.findViewById(R.id.SignupConfirmPassword);
        signup = view.findViewById(R.id.textView8);
        signUp = view.findViewById(R.id.SignUp);
        signup.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.SignUp)
        {
            String firstNameText = firstName.getText().toString();
            String lastNameText = lastName.getText().toString();
            String passwordText = password.getText().toString();
            String confirmPasswordText = confirmPassword.getText().toString();
            if(passwordText.equals(confirmPasswordText) && passwordText.length()>=6 && firstNameText.length()>0 && passwordText.length()>0)
            {
                registerUser(firstNameText, lastNameText, passwordText);
            }
        }
        if (v.getId() == R.id.textView8) {

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            login lll = new login(); // Instantiate the fragment
            fragmentTransaction.replace(R.id.fragmentContainer, lll);
            fragmentTransaction.commit();
        }
    }

    private void registerUser(String firstNameText, String lastNameText, String passwordText) {
        auth.createUserWithEmailAndPassword(firstNameText+"@gmail.com", passwordText);
    }
}