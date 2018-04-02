/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.buyukdemircioglug.landslidealert.userinput;

import android.view.LayoutInflater;
import android.view.View;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.example.buyukdemircioglug.landslidealert.core.BasePresenter;
import com.example.buyukdemircioglug.landslidealert.view.CustomTextView;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;

@FragmentWithArgs
public class UserInputFragment extends BaseFragment {

    @BindView(R.id.fragment_user_input_text_view_date)
    CustomTextView textViewDate;

    @BindView(R.id.fragment_user_input_text_view_time)
    CustomTextView textViewTime;

    @BindView(R.id.fragment_user_input_text_view_location)
    CustomTextView textViewLocation;

    @Override
    protected int getResourceLayoutId() {
        return R.layout.fragment_user_input;
    }

    @Override
    protected void initUserInterface(LayoutInflater inflater, View rootView) {

        final Date date = Calendar.getInstance().getTime();

        final Locale locale = getResources().getConfiguration().locale;

        //
        // Display a date in day, month, year format
        //
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", locale);
        String today = formatter.format(date);
        textViewDate.setText(today);

        DateFormat formatter2 = new SimpleDateFormat("HH:mm", locale);
        String time = formatter2.format(date);
        textViewTime.setText(time);

    }

    @Override
    public void setPresenter(BasePresenter presenter) {

    }
}
