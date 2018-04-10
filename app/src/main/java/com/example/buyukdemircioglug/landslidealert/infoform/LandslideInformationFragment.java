package com.example.buyukdemircioglug.landslidealert.infoform;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.example.buyukdemircioglug.landslidealert.view.LAMAButton;
import com.example.buyukdemircioglug.landslidealert.view.LAMATextView;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import butterknife.BindView;
import butterknife.OnClick;

@FragmentWithArgs
public class LandslideInformationFragment extends BaseFragment implements LandslideInformationContract.View {

    @BindView(R.id.fragment_user_input_text_view_date)
    LAMATextView textViewDate;

    @BindView(R.id.fragment_user_input_text_view_time)
    LAMATextView textViewTime;

    @BindView(R.id.fragment_user_input_text_view_location)
    LAMATextView textViewLocation;

    @BindView(R.id.fragment_user_input_button_continue)
    LAMAButton buttonContinue;

    private LandslideInformationContract.Presenter presenter;

    @Override
    protected int getResourceLayoutId() {
        return R.layout.fragment_landslide_information;
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
    public void setPresenter(@NonNull LandslideInformationContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void setLocationText (String locationText) {
        textViewLocation.setText(getString(R.string.location, locationText));
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
