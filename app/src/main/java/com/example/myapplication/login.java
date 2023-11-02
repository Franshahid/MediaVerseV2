package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;


public class login extends Fragment implements View.OnClickListener {

    Context context;
    public login() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        TextView signup = view.findViewById(R.id.textView8);
        signup.setOnClickListener(this);

        TextView reset = view.findViewById(R.id.textView7);
        reset.setOnClickListener(this);

        Button log = view.findViewById(R.id.logIN);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth auth = FirebaseAuth.getInstance();
                EditText Username = view.findViewById(R.id.username);
                EditText Password = view.findViewById(R.id.password);
                String user = Username.getText().toString();
                String password = Password.getText().toString();
                Log.d("debug", user);
                Log.d("debug", password);
                auth.signInWithEmailAndPassword(Username.getText().toString(), Password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // User registration successful
                                    FirebaseUser user = auth.getCurrentUser();
                                    Intent intent = new Intent(requireActivity(), homeActivity.class);
                                    startActivity(intent);
                                } else {
                                    // Handle registration errors
                                    Toast.makeText(getContext(), "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

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

    }

}