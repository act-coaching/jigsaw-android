package act.coaching.jigsawandroid.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by actmember on 2018. 1. 24..
 */

public class JigsawPreference {
    private static final String JIG_SAW_PREF = "JIG_SAW_PREF";

    public static final String TOKEN = "TOKEN";

    private SharedPreferences pref;

    private static JigsawPreference jigsawPreference;

    private JigsawPreference(Context context) {
        pref = context.getSharedPreferences(JIG_SAW_PREF, Context.MODE_PRIVATE);
    }

    public static JigsawPreference getInstance(Context mContext) {
        if(jigsawPreference == null) {
            jigsawPreference = new JigsawPreference(mContext);
        }
        return jigsawPreference;
    }

    public void putString(String key, String defVal) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, defVal);
        editor.apply();
    }

    public String getString(String key) {
        return pref.getString(key, "");
    }
}
