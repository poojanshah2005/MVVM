package com.example.kalpesh.firstapi.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class CakeListModel implements Parcelable{



    protected CakeListModel(Parcel in) {
        title = in.readString();
        desc = in.readString();
        image = in.readString();
    }

    public static final Creator<CakeListModel> CREATOR = new Creator<CakeListModel>() {
        @Override
        public CakeListModel createFromParcel(Parcel in) {
            return new CakeListModel(in);
        }

        @Override
        public CakeListModel[] newArray(int size) {
            return new CakeListModel[size];
        }
    };

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     * @see #CONTENTS_FILE_DESCRIPTOR
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(image);
    }

    @Expose
private String title;
@Expose
private String desc;
@Expose
private String image;

/**
*
* @return
* The title
*/
public String getTitle() {
return title;
}

/**
*
* @param title
* The title
*/
public void setTitle(String title) {
this.title = title;
}

/**
*
* @return
* The desc
*/
public String getDesc() {
return desc;
}

/**
*
* @param desc
* The desc
*/
public void setDesc(String desc) {
this.desc = desc;
}

/**
*
* @return
* The image
*/
public String getImage() {
return image;
}

/**
*
* @param image
* The image
*/
public void setImage(String image) {
this.image = image;
}

}
