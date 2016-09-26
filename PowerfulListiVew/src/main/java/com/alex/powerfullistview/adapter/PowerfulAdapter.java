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
package com.alex.powerfullistview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.alex.powerfullistview.R;
import com.alex.powerfullistview.holder.ViewHolder;

import java.util.List;


public abstract class PowerfulAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> mData;
    private LayoutInflater mInflater;
    private int mLayoutId;


    protected PowerfulAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        this.mData = datas;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return this.mData.size();
    }

    @Override
    public T getItem(int i) {
        return this.mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mData.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Log.i("TAG", "getView: mData.size()" + mData.size());

        if (mData.size() == 0) {
            return mInflater.inflate(R.layout.support_simple_spinner_dropdown_item, null);
        }
        ViewHolder holder = ViewHolder.get(this.mContext, viewGroup, this.mLayoutId, i, view);
        this.convert(holder, this.getItem(i));
        return holder.getConvertView();


    }

    public abstract void convert(ViewHolder var1, T var2);

}
