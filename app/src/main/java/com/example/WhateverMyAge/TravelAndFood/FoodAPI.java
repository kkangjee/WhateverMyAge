package com.example.WhateverMyAge.TravelAndFood;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

class APIfdata {
    private String add1;
    private String add2;
    private String dist;
    private String title;
    private String image;
    private String cat1;

    public APIfdata (String add1, String add2, String dist, String title, String image) {
        this.add1 = add1;
        this.add2 = add2;
        this.dist = dist;
        this.title = title;
        this.image = image;
        this.cat1=cat1;
    }

    String getAdd1() {
        return this.add1;
    }
    String getAdd2() {
        return this.add2;
    }
    String getDist() {
        return this.dist;
    }
    String getTitle() {
        return this.title;
    }
    String getImage() {
        return this.image;
    }
    String getCat1() { return this.cat1;}
    void setAdd1(String add1) {
        this.add1 = add1;
    }
    void setAdd2(String add2) {
        this.add2 = add2;
    }
    void setDist(String dist) {
        this.dist = dist;
    }
    void setTitle(String title) {
        this.title = title;
    }
    void setImage(String image) {
        this.image = image;
    }
}


public class FoodAPI {
    Bitmap bitmap;
    public static String baseURL;

    public ArrayList<APIdata> getFAPI (double lat, double lng) {

        StrictMode.enableDefaults();

        boolean initem = false, inaddr1 = false, inaddr2 = false, indist = false, intitle = false, inimage = false,
               incat1=false;;

        String addr1 = null, addr2 = null, dist = null, title = null, image = null,cat1=null;
        String key = "yOv5P9kxcP5VnWt8txP86aulztqNFQ1Tx848A4ZIyOgQCl0nnx6Zgp2iZO768lX2VyqpNqF8eXFYosPaxm6z%2FQ%3D%3D";

        double xpos = lat == 0 ? 126.9815706850 : lat;
        double ypos = lng == 0 ? 37.5685207373 : lng;

        ArrayList<APIdata> apiData=new ArrayList<>();

        try {
            int index = 0;
            Log.i("인덱스 초기화", " " + index);

            URL url = new URL("https://api.visitkorea.or.kr/openapi/service/rest/KorService/locationBasedList?ServiceKey="
                    + key + "&mapX=" + xpos + "&mapY=" + ypos + "&radius=1000&listYN=Y&arrange=B&MobileOS=ETC&MobileApp=AppTest"
            ); //검색 URL부분

            /*
             * arrange 를 A가 제목순 B가 조회순 E가 거리순
             * */

            Log.i("인덱스다음", "url");

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            Log.i("파스", "url");

            XmlPullParser parser = parserCreator.newPullParser();
            Log.i("파서", "url");

            parser.setInput(url.openStream(), null);
            Log.i("인풋", "url");


            int parserEvent = parser.getEventType();
            Log.i("파싱시작합니다.","");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("addr1")) { //addr1 만나면 내용을 받을수 있게 하자
                            inaddr1 = true;
                        }
                        if (parser.getName().equals("addr2")) { //addr2 만나면 내용을 받을수 있게 하자
                            inaddr2 = true;
                        }
                        if (parser.getName().equals("dist")) { //dist 만나면 내용을 받을수 있게 하자
                            indist = true;
                        }
                        if (parser.getName().equals("title")) { //title 만나면 내용을 받을수 있게 하자
                            intitle = true;
                        }
                        if (parser.getName().equals("firstimage")) {
                            inimage = true;
                        }

                        if (parser.getName().equals("cat1")) {
                            incat1 = true;
                        }



                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (inaddr1) { //isaddr1이 true일 때 태그의 내용을 저장.
                            addr1 = parser.getText();
                            //apiData[index].setAdd1(parser.getText());
                            inaddr1 = false;
                        }
                        if (inaddr2) { //inaddr2이 true일 때 태그의 내용을 저장.
                            addr2 = parser.getText();
                            //apiData[index].setAdd2(parser.getText());
                            inaddr2 = false;
                        }
                        if (indist) { //indist이 true일 때 태그의 내용을 저장.
                            dist = parser.getText();
                            indist = false;
                        }
                        if (intitle) { //inMapx이 true일 때 태그의 내용을 저장.
                            title = parser.getText();
                            // apiData[index].APIdata(addr1, addr2, dist, title);
                            intitle = false;
                        }
                        if (inimage) {
                            image = parser.getText();
                            inimage = false;
                        }
                        if (incat1) {
                            cat1 = parser.getText();
                            incat1 = false;
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            if (cat1.equals("A05")) {
                                Log.i("현재 인덱스", " " + index);
                                Log.i("현재 정보", addr1 + " " + addr2 + " " + dist + " " + title);
                                apiData.add(new APIdata(addr1, addr2, dist, title, image));
//                            status1.setText(status1.getText()+"지명 : "+ addr1 +"\n 주소: "+ addr2 +"\n 거리 : " + (dist*3) +"걸음" + "m\n 제목 : " + title
//                                    +"\n\n ");
//                            initem = false;
                                index++;
                            }
                            break;
                        }
                }
                parserEvent = parser.next();
            }

        } catch (Exception e) {
            Log.e("안 되지롱~", "");
        }
        return apiData;
    }


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

    //pic.setText(apiData[0].getImage());
    // pic.setImageURI(Uri.parse(apiData[0].getImage()));

}