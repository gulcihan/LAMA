package com.example.buyukdemircioglug.landslidealert.userinput;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.example.buyukdemircioglug.landslidealert.view.CustomButton;
import com.example.buyukdemircioglug.landslidealert.view.CustomTextView;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import butterknife.BindView;
import butterknife.OnClick;

@FragmentWithArgs
public class UserInputFragment extends BaseFragment implements UserInputContract.View {

    @BindView(R.id.fragment_user_input_text_view_date)
    CustomTextView textViewDate;

    @BindView(R.id.fragment_user_input_text_view_time)
    CustomTextView textViewTime;

    @BindView(R.id.fragment_user_input_text_view_location)
    CustomTextView textViewLocation;

    @BindView(R.id.fragment_user_input_button_continue)
    CustomButton buttonContinue;

    private UserInputContract.Presenter presenter;

    @Override
    protected int getResourceLayoutId() {
        return R.layout.fragment_user_input;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    protected void initUserInterface(LayoutInflater inflater, View rootView) {

    }

    @Override
    public void setPresenter(@NonNull UserInputContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void setLocationText (String locationText) {
        textViewLocation.setText(getString(R.string.input_location, locationText));
    }

    @OnClick(R.id.fragment_user_input_button_continue)
    public void onButtonContinueClicked() {
        presenter.onContinueButtonClicked();
    }

    @Override
    public void setDateInfo(String date) {
        textViewDate.setText(date);
    }

    @Override
    public void setTimeInfo(String time) {
        textViewTime.setText(time);
    }

    @Override
    public void setLocationInfo(String locationInfo) {

    }
}
