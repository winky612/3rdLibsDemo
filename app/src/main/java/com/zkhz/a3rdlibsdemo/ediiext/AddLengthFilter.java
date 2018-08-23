package com.zkhz.a3rdlibsdemo.ediiext;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 长度监听过滤
 *
 * @version v1.0
 *
 * @author
 */
public class AddLengthFilter implements InputFilter {

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        return null;
    }

    //长度监听
    public static void lengthFilter(final Context context, final EditText editText, final int max_length,
                                    final String err_msg) {
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new LengthFilter(max_length) {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                //获取字符个数(一个中文算2个字符)
                int destLen = getCharacterNum(dest.toString());
                int sourceLen = getCharacterNum(source.toString());

                if (destLen + sourceLen > max_length) {
                    Toast.makeText(context, err_msg, Toast.LENGTH_SHORT).show();
                    return "";
                }
                return source;
            }
        };
        editText.setFilters(filters);
    }

    /**
     * @description 获取一段字符串的字符个数（包含中英文，一个中文算2个字符）
     * @param content
     * @return
     */
    public static int getCharacterNum(final String content) {
        if (null == content || "".equals(content)) {
            return 0;
        } else {
            return (content.length() + getChineseNum(content));
        }
    }

    /**
     * @description 返回字符串里中文字或者全角字符的个数
     * @param s
     * @return
     */
    public static int getChineseNum(String s) {
        int num = 0;
        char[] myChar = s.toCharArray();
        for (int i = 0; i < myChar.length; i++) {
            if ((char) (byte) myChar[i] != myChar[i]) {
                num++;
            }
        }
        return num;
    }

}
