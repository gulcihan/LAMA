package com.example.buyukdemircioglug.landslidealert.infoform;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.dd.processbutton.iml.ActionProcessButton;
import com.example.buyukdemircioglug.landslidealert.R;
import com.example.buyukdemircioglug.landslidealert.core.BaseFragment;
import com.example.buyukdemircioglug.landslidealert.util.KeyboardUtil;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;

import butterknife.BindView;
import butterknife.OnClick;

@FragmentWithArgs
public class LandslideInformationFragment
        extends BaseFragment<LandslideInformationView, LandslideInformationPresenter, LandslideInformationViewState>
        implements LandslideInformationView {

    @BindView(R.id.fragment_landslide_information_layout_info_form)
    LinearLayout layoutInfoForm;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

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

    @OnClick(R.id.fragment_landslide_information_image_view_damage_description_info)
    public void onDamageDescriptionInfoClicked() {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage(getString(R.string.damage_description_info));
        alertDialogBuilder.setPositiveButton(getString(R.string.button_text_ok), (dialogInterface, i) -> {
            // Do nothing.
        });

        alertDialogBuilder.create().show();
    }

    @OnClick(R.id.fragment_landslide_information_image_view_other_observations_info)
    public void onOtherObservationsInfoClicked() {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setMessage(getString(R.string.other_observations_info));
        alertDialogBuilder.setPositiveButton(getString(R.string.button_text_ok), (dialogInterface, i) -> {
            // Do nothing.
        });

        alertDialogBuilder.create().show();
    }

    @OnClick(R.id.fragment_landslide_information_button_continue)
    public void onContinueButtonClicked() {

        // Check for empty fields
        final String username = editTextUsername.getText().toString();
        final String name = editTextName.getText().toString();
        final String surname = editTextSurname.getText().toString();

        layoutInfoForm.clearAnimation();

        if (TextUtils.isEmpty(username)) {
            editTextUsername.clearAnimation();
            editTextUsername.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake));
            return;
        }

        if (TextUtils.isEmpty(name)) {
            editTextName.clearAnimation();
            editTextName.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake));
            return;
        }

        if (TextUtils.isEmpty(surname)) {
            editTextSurname.clearAnimation();
            editTextSurname.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.shake));
            return;
        }

        // Hide keyboard
        if (!KeyboardUtil.hideKeyboard(editTextUsername)) {
            KeyboardUtil.hideKeyboard(editTextSurname);
        }

        presenter.onContinueButtonClicked(getLandslideInfoPOJO());
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
