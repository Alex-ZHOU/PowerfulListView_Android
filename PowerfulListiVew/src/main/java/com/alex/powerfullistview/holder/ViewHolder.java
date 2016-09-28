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
package com.alex.powerfullistview.holder;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewHolder {

    private SparseArray<View> mView;
    private int mPosition;
    private View mConvertView;
    private final String TAG = ViewHolder.class.getName();

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mView = new SparseArray<>();
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, ViewGroup parent, int layoutId, int position, View convertView) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    private <T extends View> T getView(int viewId) {
        View view = this.mView.get(viewId);
        if (view == null) {
            view = this.mConvertView.findViewById(viewId);
            this.mView.put(viewId, view);
        }
        return (T) view;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public View getConvertView() {
        return this.mConvertView;
    }

    public ViewHolder setText_TextView(int viewId, String text) {
        TextView tv = (TextView) this.getView(viewId);
        tv.setText(text);
        return this;
    }
}
