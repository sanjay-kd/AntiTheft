package com.nsit.antitheft.Calls;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.nsit.antitheft.ApiInterface;
import com.nsit.antitheft.MainActivity;
import com.nsit.antitheft.RetrofitInstance;

import java.io.IOException;

import retrofit2.Call;

public class LoginCall extends AsyncTask<Void, Void, Void> {

    private Context context;
    private ProgressDialog progressDialog;
    private String username;

    public LoginCall(Context context){
        this.context = context;
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait while we log you in!");
    }

    @Override
    protected void onPreExecute() {
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance();
        Call<JsonObject> registerUser = apiInterface.loginUser("sanjayk","sanjay123");
        try {
            JsonObject jsonObject = registerUser.execute().body();
            assert jsonObject != null;
            username = jsonObject.get("username").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
        if (username != null){
            Intent intent = new Intent(context,MainActivity.class);
            context.startActivity(intent);
        }
        else{
            Toast.makeText(context,"No user exists with the entered username or password might be wrong",Toast.LENGTH_LONG).show();
        }
    }
}
