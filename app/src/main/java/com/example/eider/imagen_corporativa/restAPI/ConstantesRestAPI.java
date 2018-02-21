package com.example.eider.imagen_corporativa.restAPI;

public final class ConstantesRestAPI {

    public static final String VERSION = "/v1/";
    public static final String BASE_URL = "https://api.instagram.com"+VERSION;
    public static final String ACCESS_TOKEN = "301295440.800d162.6b24086df07341f5886ebc48b677a1d9";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";
    public static final String KEY_GET_INFO_USER = " users/self/media/recent/";
    public static final String URL_GET_INFO_USER = KEY_GET_INFO_USER+KEY_ACCESS_TOKEN+ACCESS_TOKEN;
    public  static  final String ID_NEGRO = "users/26745972/media/recent/?access_token=301295440.800d162.6b24086df07341f5886ebc48b677a1d9";


    public static final String BASE_URL_FIREBASE = "https://morning-shelf-64678.herokuapp.com/";
    public static final String POST_FIREBASE_TOKEN = "token-device/";
    public static final String INSERTAR_USUARIO = "registrar-usuario/";
    public static final String INSERTAR_USUARIO_INSTAGRAM = "registrar-usuario-insagram/";


    public static final String TOQUE_ANIMAL = "toque-animal/{id}/{animal}/";

    public static final String LIKE_INSTAGRAM = "media/{mediaid}/likes";

    public static final String FOLLOW_INSTAGRAM =  "users/{user-id}/relationship";

    public static final String VERIFICAR_FOLLOW_INSTAGRAM =  "users/26745972/relationship?access_token=301295440.800d162.6b24086df07341f5886ebc48b677a1d9";

    //https://api.instagram.com/v1/media/1496158914924204736_26745972/likes?access_token=301295440.800d162.6b24086df07341f5886ebc48b677a1d9
    //https://api.instagram.com/v1/users/26745972/media/recent/?access_token=301295440.800d162.6b2
    //https://api.instagram.com/v1/users/self/?access_token
    //?access_token=301295440.800d162.6b24086df07341f5886ebc48b677a1d9

// mediaid   1496158914924204736_26745972
}