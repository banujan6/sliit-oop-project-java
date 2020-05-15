package com.sliit.music.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"statusCode", "statusDescription", "additionalParams"})
public class ErrorResponse {

    private String statusCode;
    private String statusDescription;


    public ErrorResponse(String statusCode, String statusDescription) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "statusCode=" + statusCode +
                ", statusDescription='" + statusDescription + '\'' +
                '}';
    }
}
