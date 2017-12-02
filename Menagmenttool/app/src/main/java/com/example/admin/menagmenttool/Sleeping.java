package com.example.admin.menagmenttool;

/**
 * Created by admin on 27.11.2017.
 */

public class Sleeping {
    String title;
    String means;
    String detailMean;

    public Sleeping(String title, String means, String detailMean) {
        this.title = title;
        this.means = means;
        this.detailMean = detailMean;
    }
    public Sleeping(){}


    public void setTitle(String title) {
        this.title = title;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public void setDetailMean(String detailMean) {
        this.detailMean = detailMean;
    }



    public String getTitle() {
        return title;
    }

    public String getMeans() {
        return means;
    }

    public String getDetailMean() {
        return detailMean;
    }


}

