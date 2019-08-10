package com.G1B4.WhateverMyAge.Main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageURL {

    private Bitmap bitmap;

    public Bitmap setImageURL(String originalURL) {
        if (originalURL.charAt(4) != 's')
            originalURL = originalURL.substring(0, 4) + "s" + originalURL.substring(4, originalURL.length());

        final String baseURL = originalURL;

        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(baseURL);
                    HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                } catch (IOException ex) {

                }
            }
        };
        mThread.start();
        try {
            mThread.join();
        } catch (InterruptedException e) {

        }
        return bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

   public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


}
