package com.example.admin.emailnew4;

/**
 * Created by admin on 17.09.2017.
 */

public class Details {
    private int image_id;
    private String name_sender;
    private String sms_title;
    private String date;

    public Details(int newImageId, String newNameSender, String newSmsTitle,String newDate){
        image_id = newImageId;
        name_sender = newNameSender;
        sms_title = newSmsTitle;
        date = newDate;
    }

    public int getImage_id(){
        return image_id;
    }

    public String getName_sender(){
        return name_sender;
    }

    public String getSms_title(){
        return  sms_title;
    }

    public String getDate(){
        return date;
    }

}
