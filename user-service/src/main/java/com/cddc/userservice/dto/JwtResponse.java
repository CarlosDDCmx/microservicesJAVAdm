package com.cddc.userservice.dto;

import java.util.Date;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Date expiration;

    public JwtResponse(String token, Date expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Date getExpiration() { return expiration; }
    public void setExpiration(Date expiration) { this.expiration = expiration; }
}