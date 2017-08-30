package com.tah.touchaheart.app.retrieve;

/**
 * Created by Lee on 8/19/2017.
 */

public class Donations {
    int id;
    String rtype, remail,description, rgender, rquantity,rsize,rcondition,rlocation,imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return rtype;
    }

    public void setType(String rtype) {
        this.rtype = rtype;
    }

    public String getRemail() {
        return remail;
    }

    public void setRemail(String remail) {
        this.remail = remail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getSize() {
        return rsize;
    }

    public void setSize(String rsize) {
        this.rsize = rsize;
    }

    public String getCondition() {
        return rcondition;
    }

    public void setCondition(String rcondition) {
        this.rcondition = rcondition;
    }

    public String getLocation() {
        return rlocation;
    }

    public void setlocation(String rlocation) {
        this.rlocation = rlocation;

    }

    public String getGender() {
        return rgender;
    }

    public void setGender(String rgender) {
        this.rgender = rgender;
    }

    public String getQuantity() {
        return rquantity;
    }

    public void setQuantity(String rquantity) {
        this.rquantity = rquantity;
    }
}
