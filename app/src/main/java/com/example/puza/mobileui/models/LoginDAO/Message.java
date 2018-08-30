//
//package com.example.puza.mobileui.models.LoginDAO;
//
//import java.io.Serializable;
//import java.util.List;
//import android.os.Parcel;
//import android.os.Parcelable;
//import android.os.Parcelable.Creator;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class Message implements Serializable, Parcelable
//{
//
//    @SerializedName("email")
//    @Expose
//    private List<String> email = null;
//    @SerializedName("password")
//    @Expose
//    private List<String> password = null;
//    public final static Creator<Message> CREATOR = new Creator<Message>() {
//
//
//        @SuppressWarnings({
//            "unchecked"
//        })
//        public Message createFromParcel(Parcel in) {
//            return new Message(in);
//        }
//
//        public Message[] newArray(int size) {
//            return (new Message[size]);
//        }
//
//    }
//    ;
//    private final static long serialVersionUID = -3953164551143331386L;
//
//    protected Message(Parcel in) {
//        in.readList(this.email, (String.class.getClassLoader()));
//        in.readList(this.password, (String.class.getClassLoader()));
//    }
//
//    public Message() {
//    }
//
//    public List<String> getEmail() {
//        return email;
//    }
//
//    public void setEmail(List<String> email) {
//        this.email = email;
//    }
//
//    public List<String> getPassword() {
//        return password;
//    }
//
//    public void setPassword(List<String> password) {
//        this.password = password;
//    }
//
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeList(email);
//        dest.writeList(password);
//    }
//
//    public int describeContents() {
//        return  0;
//    }
//
//}
