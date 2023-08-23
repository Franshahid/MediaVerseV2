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

public class otp extends Fragment implements View.OnClickListener {
    public otp() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_otp, container, false);
        TextView signup = view.findViewById(R.id.textView8);
        signup.setOnClickListener(this);

        Button reset = view.findViewById(R.id.Loginlogin);
        reset.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.textView8) {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            signup sss = new signup();
            fragmentTransaction.replace(R.id.fragmentContainer, sss);
            fragmentTransaction.commit();
        }
        else if (v.getId() == R.id.Loginlogin) {

            Toast.makeText(requireContext(),"Password has reset",Toast.LENGTH_SHORT).show();
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            login lll = new login();
            fragmentTransaction.replace(R.id.fragmentContainer, lll);
            fragmentTransaction.commit();
        }
    }
}