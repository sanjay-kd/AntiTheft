package com.nsit.antitheft;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register")
    Call<JsonObject> registerUser(@Field("username") String username, @Field("email")
            String email, @Field("name") String name, @Field("phone") String phone, @Field("password") String password, @Field("aadhaar") String aadhaarNumber, @Field("license") String licenseNumber, @Field("address") String address);

    @FormUrlEncoded
    @POST("login")
    Call<JsonObject> loginUser(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("newStolen")
    Call<JsonObject> addStolenVehicle(@Field("username") String username, @Field("phone") String phoneNumber, @Field("licensePlate") String licensePlate, @Field("ownerLicense") String ownerLicense);

    @GET("Spots")
    Call<JsonArray> getTrackDetails(@Query("licensePlate") String licensePlate);

}
