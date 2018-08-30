//
//package com.example.puza.mobileui.models.LoginDAO;
//
//import java.io.Serializable;
//import android.os.Parcel;
//import android.os.Parcelable;
//import android.os.Parcelable.Creator;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class Login implements Serializable, Parcelable
//{
//
//    @SerializedName("status")
//    @Expose
//    private Boolean status;
//    @SerializedName("message")
//    @Expose
//    private Message message;
//    public final static Creator<Login> CREATOR = new Creator<Login>() {
//
//
//        @SuppressWarnings({
//            "unchecked"
//        })
//        public Login createFromParcel(Parcel in) {
//            return new Login(in);
//        }
//
//        public Login[] newArray(int size) {
//            return (new Login[size]);
//        }
//
//    }
//    ;
//    private final static long serialVersionUID = 7524278053658488906L;
//
//    protected Login(Parcel in) {
//        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
//        this.message = ((Message) in.readValue((Message.class.getClassLoader())));
//    }
//
//    public Login() {
//    }
//
//    public Boolean getStatus() {
//        return status;
//    }
//
//    public void setStatus(Boolean status) {
//        this.status = status;
//    }
//
//    public Message getMessage() {
//        return message;
//    }
//
//    public void setMessage(Message message) {
//        this.message = message;
//    }
//
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeValue(status);
//        dest.writeValue(message);
//    }
//
//    public int describeContents() {
//        return  0;
//    }
//
//}
