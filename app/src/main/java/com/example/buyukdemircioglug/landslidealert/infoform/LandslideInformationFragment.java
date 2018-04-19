package com.example.buyukdemircioglug.landslidealert.infoform;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hkm.ui.processbutton.iml.ActionProcessButton;

import butterknife.BindView;
import butterknife.OnClick;

@FragmentWithArgs
public class LandslideInformationFragment extends BaseFragment<LandslideInformationView,
        LandslideInformationPresenter, LandslideInformationViewState>
        implements LandslideInformationView {

    @BindView(R.id.fragment_landslide_information_edit_text_username)
    EditText editTextUsername;

    @BindView(R.id.fragment_landslide_information_edit_text_name)
    EditText editTextName;

    @BindView(R.id.fragment_landslide_information_edit_text_surname)
    EditText editTextSurname;
    //
//    @BindView(R.id.fragment_landslide_information_edit_text_event_location)
//    LAMAInlineErrorEditText editTextEventLocation;
//
//    @BindView(R.id.fragment_landslide_information_edit_text_event_time)
//    LAMAInlineErrorEditText editTextEventTime;
//
    @BindView(R.id.fragment_landslide_information_edit_text_damage_description)
    EditText editTextDamageDescription;

    @BindView(R.id.fragment_landslide_information_edit_text_other_observations)
    EditText editTextOtherObservations;
    //
//    @BindView(R.id.fragment_user_input_text_view_date)
//    LAMATextView textViewDate;
//
//    @BindView(R.id.fragment_user_input_text_view_time)
//    LAMATextView textViewTime;
//
//    @BindView(R.id.fragment_user_input_text_view_location)
//    LAMATextView textViewLocation;
//
    @BindView(R.id.fragment_landslide_information_button_continue)
    ActionProcessButton buttonContinue;

    @Override
    protected int getResourceLayoutId() {
        return R.layout.fragment_landslide_information;
    }

    @Override
    protected void initUserInterface(LayoutInflater inflater, View rootView) {
        setToolbarTitle(getString(R.string.info_form_screen_title));
    }

    @NonNull
    @Override
    public LandslideInformationPresenter createPresenter() {
        return new LandslideInformationPresenter();
    }

    @NonNull
    @Override
    public LandslideInformationViewState createViewState() {
        return new LandslideInformationViewState();
    }

    @Override
    public void onNewViewStateInstance() {
        // No operation needed.
    }

    @OnClick(R.id.fragment_landslide_information_button_continue)
    public void onButtonContinueClicked() {
        presenter.onContinueButtonClicked(getLandslideInfoPOJO());
    }

    @Override
    public void setDateInfo(String date) {
        //textViewDate.setText(date);
    }

    @Override
    public void setTimeInfo(String time) {
        //textViewTime.setText(time);
    }

    @Override
    public void showErrorForUserName() {
        //showErrorForInputField(editTextUsername);
    }

    @Override
    public void setUserNameAsValid() {
        //setInputFieldAsValid(editTextUsername);
    }

    @Override
    public void showErrorForName() {
        //showErrorForInputField(editTextName);
    }

    @Override
    public void setNameAsValid() {
        //setInputFieldAsValid(editTextName);
    }

    @Override
    public void showErrorForSurname() {
        //showErrorForInputField(editTextSurname);
    }

    @Override
    public void setSurnameAsValid() {
        //setInputFieldAsValid(editTextSurname);
    }

    public void setLocationText(String locationText) {
        //textViewLocation.setText(getString(R.string.location, locationText));
    }

    private void showErrorForInputField(EditText editText) {
        //editText.setErrorState(true);
        //editText.setErrorText(getString(R.string.input_not_valid_error_text));
    }

    private void setInputFieldAsValid(EditText editText) {
        //editText.setErrorState(false);
    }

    private LandslideInfo getLandslideInfoPOJO() {
        return new LandslideInfo(
                editTextUsername.getText().toString(),
                editTextName.getText().toString(),
                editTextSurname.getText().toString(),
                "Istanbul",
                "15:30",
                editTextDamageDescription.getText().toString(),
                editTextOtherObservations.getText().toString()
        );
    }
}
