
package com.example.puza.mobileui.models.HotAtBiztrayDao;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HBiztray implements Serializable, Parcelable
{

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private List<Message> message = null;
    public final static Creator<HBiztray> CREATOR = new Creator<HBiztray>() {


        @SuppressWarnings({
            "unchecked"
        })
        public HBiztray createFromParcel(Parcel in) {
            return new HBiztray(in);
        }

        public HBiztray[] newArray(int size) {
            return (new HBiztray[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7600411035676237075L;

    protected HBiztray(Parcel in) {
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.message, (com.example.puza.mobileui.models.HotAtBiztrayDao.Message.class.getClassLoader()));
    }

    public HBiztray() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Message> getMessage() {
        return message;
    }

    public void setMessage(List<Message> message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeList(message);
    }

    public int describeContents() {
        return  0;
    }

}
