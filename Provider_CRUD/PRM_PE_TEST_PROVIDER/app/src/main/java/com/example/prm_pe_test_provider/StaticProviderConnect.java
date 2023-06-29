package com.example.prm_pe_test_provider;

import android.net.Uri;

public class StaticProviderConnect {
    public static final String PROVIDER_NAME = "com.example.pe_prm_test/ItemProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/my_library";
//    URL ="content://com.example.pe_prm_test/ItemProvider/my_library";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String TABLE_NAME = "my_library";
    public static final String ID = "_id";
    public  static final String TITLE = "book_title";
    public static final String AUTHOR = "book_author";
    public static final String PAGES = "book_pages";
}
