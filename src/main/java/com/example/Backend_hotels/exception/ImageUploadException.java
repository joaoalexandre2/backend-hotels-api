package com.example.Backend_hotels.exception;

public class ImageUploadException  extends RuntimeException{
    public ImageUploadException(String message){
        super(message);
    }

    public ImageUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
