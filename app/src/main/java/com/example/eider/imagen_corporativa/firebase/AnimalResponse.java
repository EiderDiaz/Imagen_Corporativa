package com.example.eider.imagen_corporativa.firebase;



public  class AnimalResponse {
    private String id,token,animal;

    public AnimalResponse(String id, String token, String animal) {
        this.id = id;
        this.token = token;
        this.animal = animal;
    }

    public String getanimal() {
        return animal;
    }

    public void setanimal(String animal) {
        this.animal = animal;
    }

    public AnimalResponse() {
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