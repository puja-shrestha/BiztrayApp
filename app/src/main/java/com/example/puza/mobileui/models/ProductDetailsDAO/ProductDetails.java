
package com.example.puza.mobileui.models.ProductDetailsDAO;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetails implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private Message message;
    public final static Parcelable.Creator<ProductDetails> CREATOR = new Creator<ProductDetails>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ProductDetails createFromParcel(Parcel in) {
            return new ProductDetails(in);
        }

        public ProductDetails[] newArray(int size) {
            return (new ProductDetails[size]);
        }

    }
    ;
    private final static long serialVersionUID = -7070341284230080404L;

    protected ProductDetails(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((Message) in.readValue((Message.class.getClassLoader())));
    }

    public ProductDetails() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
