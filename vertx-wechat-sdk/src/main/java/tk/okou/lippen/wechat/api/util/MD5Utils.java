package tk.okou.lippen.wechat.api.util;

import java.security.MessageDigest;

public class MD5Utils {
    /**
     * 对数据，进行MD5算法的信息摘要计算
     *
     * @param data
     *            数据的字节数组
     * @return
     */
    public static String encryptMD5(byte[] data) {
        try {
            // 判断数据的合法性
            if (data == null) {
                throw new RuntimeException("数据不能为NULL");
            }
            // 获取MD5算法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 加入要获取摘要的数据
            md5.update(data);
            // 获取数据的信息摘要
            byte[] resultBytes = md5.digest();
            // 将字节数组转化为16进制
            String resultString = fromBytesToHex(resultBytes);
            return resultString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 对数据，进行MD5算法的信息摘要计算
     *
     * @param data
     *            要进行计算信息摘要的数据
     * @return
     */
    public static String encryptMD5(String data) {
        try {
            // 判断数据的合法性
            if (data == null) {
                throw new RuntimeException("数据不能为NULL");
            }
            // 获取MD5算法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 加入要获取摘要的数据
            md5.update(data.getBytes());
            // 获取数据的信息摘要
            byte[] resultBytes = md5.digest();
            // 将字节数组转化为16进制
            String resultString = fromBytesToHex(resultBytes);
            return resultString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 对数据，进行MD5算法的信息摘要计算，加入了salt
     *
     * @param data
     *            数据的字节数组
     * @param salt
     *            加入的盐
     * @return
     */
    public static String encryptMD5AndSalt(byte[] data, Object salt) {
        try {
            // 将data和盐拼接
            String datatemp = new String(data);
            String data_salt = mergeDataAndSalt(datatemp, salt);
            // 加入盐后，数据的信息摘要
            // 获取MD5算法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 加入要获取摘要的数据
            md5.update(data_salt.getBytes());
            // 获取数据的信息摘要
            byte[] resultBytes = md5.digest();
            // 将字节数组转化为16进制
            String resultString = fromBytesToHex(resultBytes);
            return resultString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 对数据，进行MD5算法的信息摘要计算，加入了salt
     *
     * @param data
     *            数据的字节数组
     * @param salt
     *            加入的盐
     * @return
     */
    public static String encryptMD5AndSalt(String data, Object salt) {
        try {
            // 完成数据和盐的拼接
            String data_salt = mergeDataAndSalt(data, salt);
            // 加入盐后，数据的信息摘要
            // 获取MD5算法
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            // 加入要获取摘要的数据
            md5.update(data_salt.getBytes());
            // 获取数据的信息摘要
            byte[] resultBytes = md5.digest();
            // 将字节数组转化为16进制
            String resultString = fromBytesToHex(resultBytes);
            return resultString;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 用于数据和salt的拼接
     *
     * @param data
     *            要计算数据摘要的数据
     * @param salt
     *            加入的盐
     * @return
     */
    private static String mergeDataAndSalt(String data, Object salt) {
        if (data == null) {
            data = "";
        }

        if ((salt == null) || "".equals(salt)) {
            return data;
        } else {
            return data + "{" + salt.toString() + "}";
        }

    }

    /**
     *
     * @param encPass
     *            加入盐后，计算的数据摘要
     * @param rawPass
     *            加盐前的数据
     * @param salt
     *            要加入的盐
     * @return
     */
    public static boolean isPasswordValid(String encPass, String rawPass,
                                          Object salt) {
        String data1 = encPass;
        String data2 = encryptMD5AndSalt(rawPass, salt);
        return data2.equals(data1);
    }

    /**
     * 将给定的字节数组，转化为16进制数据
     *
     * @param resultBytes
     * @return
     */
    private static String fromBytesToHex(byte[] resultBytes) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < resultBytes.length; i++) {
            if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {
                builder.append("0").append(
                        Integer.toHexString(0xFF & resultBytes[i]));
            } else {
                builder.append(Integer.toHexString(0xFF & resultBytes[i]));
            }
        }
        return builder.toString();
    }
}