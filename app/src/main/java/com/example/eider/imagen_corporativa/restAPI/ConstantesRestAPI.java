package com.example.eider.imagen_corporativa.restAPI;

public final class ConstantesRestAPI {

    public static final String VERSION = "/v1/";
    public static final String BASE_URL = "https://api.instagram.com"+VERSION;
    public static final String ACCESS_TOKEN = "301295440.800d162.6b24086df07341f5886ebc48b677a1d9";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_INFO_USER = " users/self/media/recent/";
    public static final String URL_GET_INFO_USER = KEY_GET_INFO_USER+KEY_ACCESS_TOKEN+ACCESS_TOKEN;



    //https://api.instagram.com/v1/users/self/?access_token=ACCESS-TOKEN


}