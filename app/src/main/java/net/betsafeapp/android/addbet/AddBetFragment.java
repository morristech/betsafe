package net.betsafeapp.android.addbet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import net.betsafeapp.android.R;
import net.betsafeapp.android.RxFragment;
import net.betsafeapp.android.data.BankRoll;
import net.betsafeapp.android.data.Pick;
import net.betsafeapp.android.util.AlertUtil;
import net.betsafeapp.android.util.ConversionUtil;

import java.util.List;

/**
 * Created by tyln on 26/01/2017.
 */

public final class AddBetFragment extends RxFragment<AddBetContract.Presenter>
        implements AddBetContract.View {

    // Views
    private EditText mEditTextEventName;
    private EditText mEditTextBookmaker;
    private EditText mEditTextBetOdd;
    private EditText mEditTextStake;

    private Spinner mSpinnerBankrolls;
    private Spinner mSpinnerSports;
    private Spinner mSpinnerPicks;

    @NonNull
    public static AddBetFragment newInstance() {
        return new AddBetFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_add_bet;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.menu_add_bet;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mEditTextEventName = (EditText) view.findViewById(R.id.edittext_event_name);
        mEditTextBookmaker = (EditText) view.findViewById(R.id.edittext_bookmaker_name);
        mEditTextBetOdd = (EditText) view.findViewById(R.id.edittext_bet_odd);
        mEditTextStake = (EditText) view.findViewById(R.id.edittext_bet_stake);

        mSpinnerBankrolls = (Spinner) view.findViewById(R.id.spinner_bankrolls);
        mSpinnerSports = (Spinner) view.findViewById(R.id.spinner_sports);
        mSpinnerPicks = (Spinner) view.findViewById(R.id.spinner_picks);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_bet:
                mPresenter.addBet(
                        ((BankRoll) mSpinnerBankrolls.getSelectedItem()).getId(),
                        mEditTextEventName.getText().toString().trim(),
                        mEditTextBookmaker.getText().toString().trim(),
                        ConversionUtil.convertStringToDouble(mEditTextBetOdd.getText().toString().trim()),
                        ConversionUtil.convertStringToDouble(mEditTextStake.getText().toString().trim()),
                        mSpinnerSports.getSelectedItemPosition(),
                        new Pick(mSpinnerPicks.getSelectedItemPosition(), (String) mSpinnerPicks.getSelectedItem()));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showBankrolls(@NonNull List<BankRoll> bankrolls) {
        final BankRollsSpinnerAdapter bankRollsSpinnerAdapter = new BankRollsSpinnerAdapter(getActivity(),
                android.R.layout.simple_spinner_item,
                bankrolls);
        bankRollsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerBankrolls.setAdapter(bankRollsSpinnerAdapter);
    }

    @Override
    public void addBetError() {
        AlertUtil.alert(getApplicationContext(), getString(R.string.error_message_validate_add_bet_inputs));
    }

    @Override
    public void betAddedSuccessfully() {
        AlertUtil.alert(getApplicationContext(), getString(R.string.success_message_new_bet_added_to_bankroll));
        getActivity().finish();
    }

    @Override
    public void enableOrDisableBankRollSelection(boolean isEnabled) {
        mSpinnerBankrolls.setEnabled(isEnabled);
    }
}
