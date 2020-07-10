package com.prevail.assignment.dogbreeds.models;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;

abstract class APIResponse {
    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    MutableLiveData<Integer> errorCode = new MutableLiveData<>();
    public MutableLiveData<Integer> getErrorCode() {
        return errorCode;
    }

}



