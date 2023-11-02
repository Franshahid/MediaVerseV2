package com.example.myapplication;

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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

public class signup extends Fragment implements View.OnClickListener{

    FirebaseAuth auth;
    FirebaseFirestore db;
    public signup() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_signup, container, false);
        TextView signup = v.findViewById(R.id.textView8);
        signup.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Button sign = v.findViewById(R.id.SignUP);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewww) {
                Log.d("debug", "hello");
                EditText First = v.findViewById(R.id.First);
                EditText Second = v.findViewById(R.id.Second);
                EditText Username = v.findViewById(R.id.email_num);
                EditText Password = v.findViewById(R.id.Password);
                EditText Confirm = v.findViewById(R.id.Confirm_pass);
                String firstName = First.getText().toString();
                Log.d("debug", firstName);

                String lastName = Second.getText().toString();
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                String confirm = Confirm.getText().toString();
                Log.d("debug",password);
                Log.d("debug", confirm);
                if(firstName.length()==0 || lastName.length()==0 || username.length()==0 || password.length()==0 || confirm.length()==0)
                {
                    Toast.makeText(getContext(), "Please Fill Up All The Options", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals(confirm))
                {
                    auth.createUserWithEmailAndPassword(username, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // User registration was successful
                                        FirebaseUser user = auth.getCurrentUser();
                                        if (user != null) {
                                            Toast.makeText(getContext(), "Account Created!", Toast.LENGTH_SHORT).show();
                                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                                            login lll = new login(); // Instantiate the fragment
                                            fragmentTransaction.replace(R.id.fragmentContainer, lll);
                                            fragmentTransaction.commit();
                                            // Store user information in Firestore
                                            //saveSignUpInfo(user.getUid(), firstName, lastName, username, password);

                                        }
                                    } else {
                                        // User registration failed
                                        Log.d("debug", "User registration failed: " + task.getException().getMessage());
                                        Toast.makeText(getContext(), "User registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else{
                    Log.d("debug",password);
                    Log.d("debug", confirm);
                    Toast.makeText(getContext(), "Passwords Do Not Match!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }

    private void saveSignUpInfo(String userId, String first, String last, String user, String pass) {
        FirebaseFirestore db = FirebaseFirestore.getInstance(); // Initialize Firestore

        // Reference to the "Users" collection
        CollectionReference usersRef = db.collection("Users");


        // Create a Map to represent the user data
        Map<String, Object> userData = new HashMap<>();
        userData.put("FirstName", first);
        userData.put("LastName", last);
        userData.put("Username", user);
        userData.put("Password", pass);

        usersRef.document(userId)
                .set(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Account Created!", Toast.LENGTH_SHORT).show();
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                        login lll = new login(); // Instantiate the fragment
                        fragmentTransaction.replace(R.id.fragmentContainer, lll);
                        fragmentTransaction.commit();
                        // Document has been successfully written to Firestore
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Error! Could Not Save Information!", Toast.LENGTH_SHORT).show();
                        // Handle the error if the document write fails
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textView8) {

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            login lll = new login(); // Instantiate the fragment
            fragmentTransaction.replace(R.id.fragmentContainer, lll);
            fragmentTransaction.commit();
        }
    }
}