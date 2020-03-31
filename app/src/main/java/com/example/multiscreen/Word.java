package com.example.multiscreen;

import androidx.annotation.NonNull;

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mimageresourceid = IMAGEIS;
    private static final int IMAGEIS = -1;
    private int maudioresourceid;

    public Word(String defaultTranslation, String miwokTranslation, int imageid, int audioresourceid){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mimageresourceid = imageid;
        maudioresourceid = audioresourceid;
    }

    public Word(String defaultTranslation, String miwokTranslation, int audioresourceid){

        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        maudioresourceid = audioresourceid;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }

    public String getMiwokTranslation(){

        return mMiwokTranslation;
    }

    public int getMimageresourceid(){
        return mimageresourceid;
    }

    public boolean hasimage(){
        return mimageresourceid != IMAGEIS;
    }

    public  int getaudioresourceid(){
        return maudioresourceid;
    }

    @NonNull
    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mAudioResourceId=" + maudioresourceid +
                ", mImageResourceId=" + mimageresourceid +
                '}';
    }
}
