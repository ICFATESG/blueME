package net.icfatesg.blueme.service;

import android.text.TextUtils;

/**
 * Created by harlock on 11/11/17.
 */

public  class Utilidade {

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
