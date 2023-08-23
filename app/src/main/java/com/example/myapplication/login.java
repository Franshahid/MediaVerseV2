package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

import org.w3c.dom.Text;


public class login extends Fragment implements View.OnClickListener {

    private TextView username;
    private TextView password;
    private Button login;

    private FirebaseAuth auth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        TextView signup = view.findViewById(R.id.textView8);
        username = view.findViewById(R.id.Username);
        password = view.findViewById(R.id.Password);
        login = view.findViewById(R.id.Loginlogin);
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

        TextView reset = view.findViewById(R.id.textView7);
        reset.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textView8) {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            signup sss = new signup(); // Instantiate the fragment
            fragmentTransaction.replace(R.id.fragmentContainer, sss);
            fragmentTransaction.commit();
        }
        else if (v.getId() == R.id.textView7) {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            reset rrr= new reset(); // Instantiate the fragment
            fragmentTransaction.replace(R.id.fragmentContainer, rrr);
            fragmentTransaction.commit();
        }
        else if(v.getId() == R.id.Loginlogin)
        {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            loginUser(user, pass);
        }
    }

    private void loginUser(String user, String pass) {
        auth.signInWithEmailAndPassword(user, pass);
    }

}