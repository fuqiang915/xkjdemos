package com.xkj.demos.adapter;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.xkj.demos.models.Article;
import com.xkj.demos.models.Articles;

import java.util.LinkedList;
import java.util.TimerTask;


/**
 * Created by fuqiang on 15/12/14.
 */
public class ArticlesAdapter {

    private static final String TAG = "ArticlaAdapter";

    private ContentResolver resolver = null;

    public ArticlesAdapter(Context context) {
        super();
        resolver = context.getContentResolver();
    }

    public long insertArticle(Article article) {
        ContentValues values = new ContentValues();
        values.put(Articles.TITLE, article.getTitle());

        Uri uri = resolver.insert(Articles.CONTENT_URI, values);
        String itemId = uri.getPathSegments().get(1);
        return Integer.valueOf(itemId).longValue();
    }

    public boolean updateArticle(Article article) {
        Uri uri = ContentUris.withAppendedId(Articles.CONTENT_URI, article.getId());
        ContentValues values = new ContentValues();
        values.put(Articles.TITLE, article.getTitle());

        int count = resolver.update(uri, values, null, null);
        return count > 0;
    }

    public boolean removeArticle(int id) {
        Uri uri = ContentUris.withAppendedId(Articles.CONTENT_URI, id);

        int count = resolver.delete(uri, null, null);
        return count > 0;
    }

    public LinkedList<Article> getAllArticles() {
        LinkedList<Article> articles = new LinkedList<>();
        String[] projection = new String[]{
                Articles.ID,
                Articles.TITLE,
        };

        Cursor cursor = resolver.query(Articles.CONTENT_URI, projection, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                Article article = new Article(id, title);
                articles.add(article);
            } while (cursor.moveToNext());
        }
        return articles;
    }

    public Article getArticleById(int id) {
        Uri uri = ContentUris.withAppendedId(Articles.CONTENT_URI, id);
        String[] projection = new String[]{
                Articles.ID,
                Articles.TITLE
        };

        Cursor cursor = resolver.query(uri, projection, null, null, Articles.DEFAULT_SORT_ORDER);
        if (!cursor.moveToFirst()) {
            return null;
        }
        String title = cursor.getString(1);
        return new Article(id, title);
    }


}
