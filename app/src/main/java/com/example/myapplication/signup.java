package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

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
        firstName = view.findViewById(R.id.Username);
        lastName = view.findViewById(R.id.SignupLastName);
        password = view.findViewById(R.id.Password);
        confirmPassword = view.findViewById(R.id.SignupConfirmPassword);
        signup = view.findViewById(R.id.textView8);
        signUp = view.findViewById(R.id.Loginlogin);
        signup.setOnClickListener(this);
        signUp.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.Loginlogin)
        {
            String firstNameText = firstName.getText().toString();
            String lastNameText = lastName.getText().toString();
            String passwordText = password.getText().toString();
            String confirmPasswordText = confirmPassword.getText().toString();
            if(passwordText.equals(confirmPasswordText) && passwordText.length()>=6 && firstNameText.length()>0 && passwordText.length()>0)
            {
                registerUser(firstNameText, lastNameText, passwordText);
            }

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            login lll= new login();
            fragmentTransaction.replace(R.id.fragmentContainer, lll);
            fragmentTransaction.commit();
        }
        else if (v.getId() == R.id.textView8) {

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