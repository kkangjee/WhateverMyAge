package com.example.WhateverMyAge.Main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageURL {

    static Bitmap bitmap;

    public Bitmap setImageURL (String originalURL) {
        //baseURL = apiData[0].getImage();
        if (originalURL.charAt(4) != 's')
            originalURL = originalURL.substring(0, 4) + "s" + originalURL.substring(4, originalURL.length());

        final String baseURL = originalURL;

        /// baseURL = "https://tong.visitkorea.or.kr/cms/resource/36/1009936_image2_1.jpg";

        //String encode = URLEncoder.encode(, "UTF-8");
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


}
