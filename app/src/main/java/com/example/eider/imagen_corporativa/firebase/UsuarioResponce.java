package com.example.eider.imagen_corporativa.firebase;



public  class UsuarioResponce {
    private String id,token,instagram_user;

    public UsuarioResponce(String id, String token,String instagram_user) {
        this.id = id;
        this.token = token;
        this.instagram_user = instagram_user;
    }

    public String getInstagram_user() {
        return instagram_user;
    }

    public void setInstagram_user(String instagram_user) {
        this.instagram_user = instagram_user;
    }

    public UsuarioResponce() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}