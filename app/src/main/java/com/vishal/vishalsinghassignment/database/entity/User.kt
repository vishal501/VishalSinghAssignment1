package com.vishal.vishalsinghassignment.database.entity

import android.os.Parcel
import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vishal.vishalsinghassignment.utils.Utils
import java.util.Date


@Entity
class User : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "name")
    var name: String?

    @ColumnInfo(name = "image")
    var image: String?

    @ColumnInfo(name = "email")
    var email: String?

    @ColumnInfo(name = "country")
    var country: String?

    @ColumnInfo(name = "date")
    var date: String?

    @ColumnInfo(name = "dob")
    var dob: String?

    @ColumnInfo(name = "city")
    var city: String?

    @ColumnInfo(name = "state")
    var state: String?

    @ColumnInfo(name = "postcode")
    var postcode: String?

    @ColumnInfo(name = "age")
    var age: String?

    constructor(`in`: Parcel) {
        id = if (`in`.readByte().toInt() == 0) {
            null
        } else {
            `in`.readInt()
        }
        name = `in`.readString()
        image = `in`.readString()
        email = `in`.readString()
        country = `in`.readString()
        date = `in`.readString()
        dob = `in`.readString()
        city = `in`.readString()
        state = `in`.readString()
        postcode = `in`.readString()
        age = `in`.readString()
    }

    val dobDate: String
        get() = "com.vishal.vishalsinghassignment.models.Dob : " + dob?.let { Utils.getDate(it) }
    val detailEmail: String
        get() = "Email : $email"

    fun dateJoined(): String {
        return "Date Joined : " + date?.let { Utils.getDate(it) }
    }

    fun userJoinedDate(): String {
        return date?.let { Utils.getDate(it) }.toString()
    }

//    fun getCity(): String {
//        return "City : $city"
//    }
//
//    fun getState(): String {
//        return "State : $state"
//    }

    val userCountry: String
        get() = "Country | $country"
    val detailCountry: String
        get() = "Country : $country"
    val postCode: String
        get() = "Postcode : $postcode"

    constructor(
        name: String?,
        image: String?,
        email: String?,
        country: String?,
        date: String?,
        dob: String?,
        city: String?,
        state: String?,
        postcode: String?,
        age: String?
    ) {
        this.name = name
        this.image = image
        this.email = email
        this.country = country
        this.date = date
        this.dob = dob
        this.city = city
        this.state = state
        this.postcode = postcode
        this.age = age
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        if (id == null) {
            dest.writeByte(0.toByte())
        } else {
            dest.writeByte(1.toByte())
            dest.writeInt(id!!)
        }
        dest.writeString(name)
        dest.writeString(image)
        dest.writeString(email)
        dest.writeString(country)
        dest.writeString(date)
        dest.writeString(dob)
        dest.writeString(city)
        dest.writeString(state)
        dest.writeString(postcode)
        dest.writeString(age)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User?> = object : Parcelable.Creator<User?> {
            override fun createFromParcel(`in`: Parcel): User {
                return User(`in`)
            }

            override fun newArray(size: Int): Array<User?> {
                return arrayOfNulls(size)
            }
        }

        @kotlin.jvm.JvmStatic
        @BindingAdapter("avatar")
        fun loadImage(imageView: ImageView, imageURL: String?) {
            Glide.with(imageView.context)
                .setDefaultRequestOptions(
                    RequestOptions()
                        .circleCrop()
                )
                .load(imageURL)
                .into(imageView)
        }
    }


}