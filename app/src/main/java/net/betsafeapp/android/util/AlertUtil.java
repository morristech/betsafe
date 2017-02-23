package net.betsafeapp.android.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

/**
 * Created by tyln on 16/01/2017.
 */

public final class AlertUtil {
    private AlertUtil() {
        // Empty Constructor
    }

    public static void alert(@NonNull final Context context, @Nullable final String mesage) {
        if (context == null || TextUtils.isEmpty(mesage)) {
            return;
        }

        Toast.makeText(context, mesage, Toast.LENGTH_SHORT).show();
    }

    interface AlertButtonClickListener {
        void onButtonClicked(DialogInterface dialog, int id);
    }

    @NonNull
    public static AlertDialog createNewAlertDialog(@NonNull Context context,
                                                   @Nullable final CharSequence title,
                                                   @NonNull final CharSequence message,
                                                   final boolean isCancelable,
                                                   @Nullable final CharSequence positiveButtonText,
                                                   @Nullable final CharSequence negativeButtonText,
                                                   @Nullable final AlertButtonClickListener positiveButtonClickListener,
                                                   @Nullable final AlertButtonClickListener negativeButtonClickListener) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context).setMessage(message)
                .setCancelable(isCancelable);

        if (!TextUtils.isEmpty(title)) {
            dialog.setTitle(title);
        }

        if (!TextUtils.isEmpty(positiveButtonText)) {
            dialog.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (positiveButtonClickListener != null) {
                        positiveButtonClickListener.onButtonClicked(dialog, which);
                    }
                }
            });
        }

        if (!TextUtils.isEmpty(negativeButtonText)) {
            dialog.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    if (negativeButtonClickListener != null) {
                        negativeButtonClickListener.onButtonClicked(dialog, which);
                    }
                }
            });
        }

        return dialog.create();
    }
}
