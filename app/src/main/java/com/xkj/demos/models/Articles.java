package com.xkj.demos.models;

import android.net.Uri;

/**
 * Created by fuqiang on 15/12/14.
 */
public class Articles {

    /* Column Name */
    public static final String ID = "_id";
    public static final String TITLE = "_title";
    public static final String ABSTRACT = "_abstract";
    public static final String URL = "_url";


    /* sort Order*/
    public static final String DEFAULT_SORT_ORDER = "_id asc";

    /* Call Method */
    public static final String METHOD_GET_ITEM_COUNT = "METHOD_GET_ITEM_COUNT";

    /* Authority */
    public static final String AUTHORITY = "com.xkj.chen";

    /* Match Code */
    public static final int ITEM = 1;
    public static final int ITEM_ID = 2;
    public static final int ITEM_POS = 3;

    /* MIME */
    public static final String CONTENT_TYPE = "com.xkj.chen.cursor.dir/article";
    public static final String CONTENT_ITEM_TYPE = "com.xkj.chen.cursor.item/article";

    /* Content Item*/
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/item");
    public static final Uri CONTENT_POS_URI = Uri.parse("content://" + AUTHORITY + "/pos");

}
