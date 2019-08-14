package com.WhateverMyAge.WhateverMyAge.TravelAndFood;

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


