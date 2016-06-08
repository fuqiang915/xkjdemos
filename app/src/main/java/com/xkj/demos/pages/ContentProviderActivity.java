package com.xkj.demos.pages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.xkj.demos.adapter.ArticlesAdapter;
import com.xkj.demos.models.Article;
import com.xkj.xkjdemos.R;

import java.util.Date;
import java.util.LinkedList;

/**
 * ContentProvider Demo
 * 1. 取联系人数据
 * 2. 自定义ContentProvider，并利用其它App来访。
 */
public class ContentProviderActivity extends BaseActivity implements View.OnClickListener {

    ListView mListView;
    ArrayAdapter<Article> mListAdapter;
    Button mAdd;
    ArticlesAdapter articlesAdapter;
    LinkedList<Article> mArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);

        mListView = (ListView) findViewById(R.id.list_view);
        mAdd = (Button) findViewById(R.id.add_article);
        articlesAdapter = new ArticlesAdapter(this);
        mAdd.setOnClickListener(this);
        showContacts();

    }

    /**
     * 1.取联系人数据
     **/

    private void showContacts() {
        //TODO: add adapter to this
        mArticles = articlesAdapter.getAllArticles();
        mListAdapter = new ArrayAdapter<Article>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mArticles);
        mListView.setAdapter(mListAdapter);
    }

    private void newPage(){
        Intent intent = new Intent(this,CustomViewDemo.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_article:
                long id = articlesAdapter.insertArticle(new Article(0, new Date().toString()));
                toast(" add " + id);
                mArticles.clear();
                mArticles.addAll(articlesAdapter.getAllArticles());
                mListAdapter.notifyDataSetChanged();
                newPage();
                break;
        }
    }

}
