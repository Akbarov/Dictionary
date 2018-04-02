package com.example.hp_pk.dictionary.database;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

/**
 * @auther ZOHIDJON
 * @since 2/28/18.
 */
@Entity(indexes = {@Index(value = "mainCategory,id,bookKey", unique = true)})
public class Book implements Parcelable {

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[0];
        }

    };
    String imageUrl;
    String bookUrl;
    double rate;
    long rateCount;
    String category;
    String description;
    String size;
    @Property
    @NotNull
    String mainCategory;
    @Property
    @NotNull
    private String id;
    @Property
    @NotNull
    private String bookKey;
    private String name;
    private String author;

    protected Book(Parcel in) {
        id = in.readString();
        bookKey = in.readString();
        name = in.readString();
        author = in.readString();
        imageUrl = in.readString();
        bookUrl = in.readString();
        rate = in.readDouble();
        rateCount = in.readLong();
        category = in.readString();
        description = in.readString();
        size = in.readString();
        mainCategory = in.readString();
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(bookKey);
        parcel.writeString(name);
        parcel.writeString(author);
        parcel.writeString(imageUrl);
        parcel.writeString(bookUrl);
        parcel.writeDouble(rate);
        parcel.writeLong(rateCount);
        parcel.writeString(category);
        parcel.writeString(description);
        parcel.writeString(size);
        parcel.writeString(mainCategory);
    }

    @Generated(hash = 2089288464)
    public Book(String imageUrl, String bookUrl, double rate, long rateCount,
                String category, String description, String size,
                @NotNull String mainCategory, @NotNull String id,
                @NotNull String bookKey, String name, String author) {
        this.imageUrl = imageUrl;
        this.bookUrl = bookUrl;
        this.rate = rate;
        this.rateCount = rateCount;
        this.category = category;
        this.description = description;
        this.size = size;
        this.mainCategory = mainCategory;
        this.id = id;
        this.bookKey = bookKey;
        this.name = name;
        this.author = author;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    @Override
    public int describeContents() {
        return 0;
    }



    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBookUrl() {
        return this.bookUrl;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public double getRate() {
        return this.rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public long getRateCount() {
        return this.rateCount;
    }

    public void setRateCount(long rateCount) {
        this.rateCount = rateCount;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return this.size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getMainCategory() {
        return this.mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookKey() {
        return this.bookKey;
    }

    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "imageUrl='" + imageUrl + '\'' +
                ", bookUrl='" + bookUrl + '\'' +
                ", rate=" + rate +
                ", rateCount=" + rateCount +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", mainCategory='" + mainCategory + '\'' +
                ", id='" + id + '\'' +
                ", bookKey='" + bookKey + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
