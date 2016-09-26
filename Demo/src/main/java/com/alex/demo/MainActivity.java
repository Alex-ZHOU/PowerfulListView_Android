/*
 * Copyright 2016 AlexZHOU
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alex.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.alex.R;
import com.alex.powerfullistview.adapter.PowerfulAdapter;
import com.alex.powerfullistview.holder.ViewHolder;
import com.alex.powerfullistview.view.PowerfulListView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PowerfulListView.IXListViewListener {

    private PowerfulListView mListView;
    private ArrayList<String> items = new ArrayList<String>();
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;
    PowerfulAdapter<String> adapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        //geneItems();


        adapter = new PowerfulAdapter<String>(getApplicationContext(), items, R.layout.list_item) {
            @Override
            public void convert(ViewHolder var1, String var2) {
                var1.setText_TextView(R.id.list_item_textview, var2);
            }
        };

        mListView = (PowerfulListView) findViewById(R.id.xListView);
        mListView.setPullLoadEnable(true);
        //mListView.setAdapter(mAdapter);
        mListView.setAdapter(adapter);
//		mListView.setPullLoadEnable(false);
//		mListView.setPullRefreshEnable(false);
        mListView.setXListViewListener(this);
//        View emptyView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.background, null);
//        ((ViewGroup) mListView.getParent()).addView(emptyView);
//        mListView.setEmptyView(emptyView);

        mHandler = new Handler();
    }

    private void geneItems() {
        for (int i = 0; i < 5; ++i) {
            items.add("refresh cnt " + (++start));
        }
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                start = ++refreshCnt;
                items.clear();
                //geneItems();
                // mAdapter.notifyDataSetChanged();
                //mAdapter = new ArrayAdapter<String>(MainActivity.this, R.layout.list_item, items);
                mListView.setAdapter(adapter);
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                adapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }
}
