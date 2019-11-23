package com.example.ezgroceries;

import android.app.Application;

public class AppClass  extends Application {
    private String globalUsername = "Guest User";
    private String globalEmail = "No email available";

    public String getGlobalVariable(String varName) {

        switch(varName) {
            case "user":
                return globalUsername;
            case "email":
                return globalEmail;
            default:
                return null;
        }
    }

    public void setGlobalVariable(String varName, String varValue) {

        switch(varName) {
            case "user":
                globalUsername = varValue;
            case "email":
                globalEmail = varValue;
            default:
                break;
        }
    }
}