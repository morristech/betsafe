package net.betsafeapp.android.addbet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;

import net.betsafeapp.android.BaseActivity;
import net.betsafeapp.android.BetSafeApp;
import net.betsafeapp.android.Constants;
import net.betsafeapp.android.R;

import javax.inject.Inject;

/**
 * Created by tyln on 26/01/2017.
 */

public final class AddBetActivity extends BaseActivity {
    private static final String BUNDLE_BANKROLL_ID = "bankroll_id";

    @NonNull
    @Inject
    AddBetPresenter mAddBetPresenter;

    @Nullable
    @Inject
    String mBankrollId;

    @NonNull
    public static Intent newIntent(@NonNull Context context, @Nullable String bankrollId) {
        final Intent intent = new Intent(context, AddBetActivity.class);
        intent.putExtra(BUNDLE_BANKROLL_ID, bankrollId);
        return intent;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_add_bet;
    }

    @Override
    protected int getTitleRes() {
        return R.string.screen_title_add_bet;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String bankrollId = null;
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            bankrollId = bundle.getString(BUNDLE_BANKROLL_ID);
        }

        AddBetFragment addBetFragment = (AddBetFragment) getSupportFragmentManager()
                .findFragmentById(R.id.framelayout_main);

        if (addBetFragment == null) {
            addBetFragment = AddBetFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_main, addBetFragment)
                    .commit();
        }

        DaggerAddBetComponent.builder()
                .bankRollRepositoryComponent(((BetSafeApp) getApplication()).getBankRollRepositoryComponent())
                .addBetPresenterModule(new AddBetPresenterModule(addBetFragment, bankrollId))
                .build()
                .inject(this);
    }
}
