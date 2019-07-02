package tk.okou.sdk.util;

import java.text.MessageFormat;

public class MessageFormatUtils {

    public static String format(MessageFormat formatter, Object... args){
        return formatter.format(args);
    }
}
