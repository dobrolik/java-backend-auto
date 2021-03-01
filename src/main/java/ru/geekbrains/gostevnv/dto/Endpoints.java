package ru.geekbrains.gostevnv.dto;

public class Endpoints {

    public static final String getUserAccountInfo = "/account/{username}";
    public static final String postImage = "/image";
    public static final String getImage = "/image/{imageId}";
    public static final String deleteImage = "/account/{username}/image/{deleteHash}";

}
