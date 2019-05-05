package fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.onlineclothingshoppingapp.DashboardActivity;
import com.example.onlineclothingshoppingapp.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText etPhoneNumber;
    private EditText etPassword;
    private Button btnLogin;
    private Map<String, String > credentails;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        etPhoneNumber = view.findViewById(R.id.etPhoneNumber);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);

        credentails = new HashMap<>();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    login();
                }
            }
        });
        return view;
    }
    private void login(){
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("user",MODE_PRIVATE);
        String phoneNumber=sharedPreferences.getString("phoneNumber","");
        String Password=sharedPreferences.getString("password","");
        if(phoneNumber.equals(etPhoneNumber.getText().toString()) && Password.equals(etPassword.getText().toString())){

            Toast.makeText(getActivity(),"Login successfull ",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getActivity(), DashboardActivity.class);
            getActivity().startActivity(intent);

        }
        else{
            Toast.makeText(getActivity(),"Either username or password is incorrect ",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate(){
        boolean validated=true;
        if(TextUtils.isEmpty(etPhoneNumber.getText().toString())){
            etPhoneNumber.setError("Username is required");
            etPhoneNumber.requestFocus();
            validated=false;
        }
        if(TextUtils.isEmpty(etPassword.getText().toString())){
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            validated=false;
        }
        return  validated;

    }

}
