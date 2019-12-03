package com.example.instagram;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("LZq7XuPUBEkxliueuKPSY7oFeaiwzmTzEbHCgYq3")
                // if defined
                .clientKey("GHTe99BR4HHCzgCIYupojY3qVQ1FpUQA4QupthCZ")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}
