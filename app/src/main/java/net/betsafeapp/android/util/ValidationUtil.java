package net.betsafeapp.android.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.Collection;

/**
 * Created by tyln on 28/01/2017.
 */

public final class ValidationUtil {
    private ValidationUtil() {
        // Private Emptry Constructor
    }

    public static boolean isNullOrEmpty(@Nullable String s) {
        return s == null || s.length() == 0 || s.trim().length() == 0;
    }

    public static boolean isInitialAmountValid(double initialAmount) {
        return initialAmount > 0;
    }

    public static boolean isNullOrEmpty(@Nullable CharSequence s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrEmpty(@Nullable Collection collection) {
        return collection == null || collection.size() == 0;
    }
}
