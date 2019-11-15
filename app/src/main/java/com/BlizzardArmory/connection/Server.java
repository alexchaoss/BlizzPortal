package com.BlizzardArmory.connection;

public class Server {
    private String clientSecret;

    private String clientID;

    public Server(){}

    public Server(String clientID, String clientSecret){
        this.clientID = clientID;
        this.clientSecret = clientSecret;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientID() {
        return this.clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
