/**
 * 
 */
package com.renlg.string;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.renlg.exception.SysRunException;

/**
 * @author renlinggao
 *
 */
public class StringUtil {
    public final static int PARSE_RETN = -1;

    /** 空字符串。 */
    public static final String EMPTY_STRING = "";

    public static boolean isBlank(String str) {
        int strLen;
        if (null == str || (strLen = str.length()) == 0 || "null".equals(str)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(String str) {
    	return (str != null && str.length() > 0 ? true : false);
    }


    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }


    public static long parseLong(Object obj, boolean isNotNull) {
        if (obj == null) {
            if (isNotNull) {
                throw new SysRunException("转换Long型时目标对象为空！", true);
            }
            return PARSE_RETN;
        }

        try {
            if (obj instanceof Long) {
                return (Long)obj;
            }
            return Long.parseLong(obj.toString());
        } catch (NumberFormatException e) {
            if (isNotNull) {
                throw new SysRunException("转换Long型时出现异常！", true);
            }
            return PARSE_RETN;
        }
    }

    public static int parseInt(Object obj, boolean isNotNull) {
        if (obj == null) {
            if (isNotNull) {
                throw new SysRunException("转换int型时目标对象为空！", true);
            }
            return PARSE_RETN;
        }

        try {
            if (obj instanceof Integer) {
                return (Integer)obj;
            }
            return Integer.parseInt(obj.toString());
        } catch (NumberFormatException e) {
            if (isNotNull) {
                throw new SysRunException("转换int型时出现异常！", true);
            }
            return PARSE_RETN;
        }
    }

    /**
     * 将一个集合转换成String
     * @param list
     * @param conjunction
     * @return String
     */
    public static String join(List<Object> list, String conjunction) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Object item : list) {
            if(null == item)
                continue;
            if (first)
                first = false;
            else
                sb.append(conjunction);
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * 将一个数组转换成String
     * @param items
     * @param conjunction
     * @return String
     */
    public static String join(Object[] items, String conjunction) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Object item : items) {
            if(null == item)
                continue;
            if (first)
                first = false;
            else
                sb.append(conjunction + "");
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * 去除全角空格
     * －excel中的粘贴
     * @param str
     * @return String
     */
    public static String trim95(String str){
        byte[] bytes=str.getBytes();
        for (int i=0;i<bytes.length;i++) {
            if(bytes[i]==63){//全角
                bytes[i]=32;//半角
            }
        }
        return new String(bytes).trim();
    }

    /**
     * 补全字符串
     *
     * @param str
     *            需要补全的字符串
     * @param wadChar
     *            补全码
     * @param length
     *            补全长度
     * @return 补全字符串 如果字符串为<code>null</code>或者length&lt;0或者length&lt;str.length()，则返回
     *         <code>str</code>自身
     */
    public static String STAM(String str, String wadChar, int length) {
        if (str == null || length < 0 || length <= str.length())
            return str;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - str.length(); i++) {
            sb.append(wadChar);
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * 随机获取指定长度的字符串(由数字与字母组成)：
     * <p>	另：如果导入commons-lang.jar包，可用此类：
     * <br>		RandomStringUtils.randomAlphanumeric(length)
     * @param length			获取的长度
     * @return					长度小于或等于0时，则返回""
     */
    public static String getRandomStr(int length) {
        if (length <= 0) {
            return "";
        }
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        return sb.toString();
    }

    /**
     * 取得第一个出现的分隔子串之后的子串。
     *
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
     * 或未找到该子串，则返回原字符串。
     *
     * <pre>
     * StringUtil.substringAfter(null, *)      = null
     * StringUtil.substringAfter(&quot;&quot;, *)        = &quot;&quot;
     * StringUtil.substringAfter(*, null)      = &quot;&quot;
     * StringUtil.substringAfter(&quot;abc&quot;, &quot;a&quot;)   = &quot;bc&quot;
     * StringUtil.substringAfter(&quot;abcba&quot;, &quot;b&quot;) = &quot;cba&quot;
     * StringUtil.substringAfter(&quot;abc&quot;, &quot;c&quot;)   = &quot;&quot;
     * StringUtil.substringAfter(&quot;abc&quot;, &quot;d&quot;)   = &quot;&quot;
     * StringUtil.substringAfter(&quot;abc&quot;, &quot;&quot;)    = &quot;abc&quot;
     * </pre>
     *
     * </p>
     *
     * @param str
     *            字符串
     * @param separator
     *            要搜索的分隔子串
     *
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringAfter(String str, String separator) {
        if ((str == null) || (str.length() == 0)) {
            return str;
        }

        if (separator == null) {
            return EMPTY_STRING;
        }

        int pos = str.indexOf(separator);

        if (pos == -1) {
            return EMPTY_STRING;
        }

        return str.substring(pos + separator.length());
    }

    /**
     * 取得第一个出现的分隔子串之前的子串。
     *
     * <p>
     * 如果字符串为<code>null</code>，则返回<code>null</code>。 如果分隔子串为<code>null</code>
     * 或未找到该子串，则返回原字符串。
     *
     * <pre>
     * StringUtil.substringBefore(null, *)      = null
     * StringUtil.substringBefore(&quot;&quot;, *)        = &quot;&quot;
     * StringUtil.substringBefore(&quot;abc&quot;, &quot;a&quot;)   = &quot;&quot;
     * StringUtil.substringBefore(&quot;abcba&quot;, &quot;b&quot;) = &quot;a&quot;
     * StringUtil.substringBefore(&quot;abc&quot;, &quot;c&quot;)   = &quot;ab&quot;
     * StringUtil.substringBefore(&quot;abc&quot;, &quot;d&quot;)   = &quot;abc&quot;
     * StringUtil.substringBefore(&quot;abc&quot;, &quot;&quot;)    = &quot;&quot;
     * StringUtil.substringBefore(&quot;abc&quot;, null)  = &quot;abc&quot;
     * </pre>
     *
     * </p>
     *
     * @param str
     *            字符串
     * @param separator
     *            要搜索的分隔子串
     *
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substringBefore(String str, String separator) {
        if ((str == null) || (separator == null) || (str.length() == 0)) {
            return str;
        }

        if (separator.length() == 0) {
            return EMPTY_STRING;
        }

        int pos = str.indexOf(separator);

        if (pos == -1) {
            return str;
        }

        return str.substring(0, pos);
    }

    /**
     * 替换指定的子串，只替换第一个出现的子串。
     *
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
     * ，则返回原字符串。
     *
     * <pre>
     * StringUtil.replaceOnce(null, *, *)        = null
     * StringUtil.replaceOnce(&quot;&quot;, *, *)          = &quot;&quot;
     * StringUtil.replaceOnce(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtil.replaceOnce(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, null)  = &quot;aba&quot;
     * StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, &quot;&quot;)    = &quot;ba&quot;
     * StringUtil.replaceOnce(&quot;aba&quot;, &quot;a&quot;, &quot;z&quot;)   = &quot;zba&quot;
     * </pre>
     *
     * </p>
     *
     * @param text
     *            要扫描的字符串
     * @param repl
     *            要搜索的子串
     * @param with
     *            替换字符串
     *
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceOnce(String text, String repl, String with) {
        return replace(text, repl, with, 1);
    }

    /**
     * 替换指定的子串，替换指定的次数。
     *
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
     * ，则返回原字符串。
     *
     * <pre>
     * StringUtil.replace(null, *, *, *)         = null
     * StringUtil.replace(&quot;&quot;, *, *, *)           = &quot;&quot;
     * StringUtil.replace(&quot;abaa&quot;, null, null, 1) = &quot;abaa&quot;
     * StringUtil.replace(&quot;abaa&quot;, null, null, 1) = &quot;abaa&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, null, 1)  = &quot;abaa&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;&quot;, 1)    = &quot;baa&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 0)   = &quot;abaa&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 1)   = &quot;zbaa&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, 2)   = &quot;zbza&quot;
     * StringUtil.replace(&quot;abaa&quot;, &quot;a&quot;, &quot;z&quot;, -1)  = &quot;zbzz&quot;
     * </pre>
     *
     * </p>
     *
     * @param text
     *            要扫描的字符串
     * @param repl
     *            要搜索的子串
     * @param with
     *            替换字符串
     * @param max
     *            maximum number of values to replace, or <code>-1</code> if no
     *            maximum
     *
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with, int max) {
        if ((text == null) || (repl == null) || (with == null)
                || (repl.length() == 0) || (max == 0)) {
            return text;
        }

        StringBuffer buf = new StringBuffer(text.length());
        int start = 0;
        int end = 0;

        while ((end = text.indexOf(repl, start)) != -1) {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();

            if (--max == 0) {
                break;
            }
        }

        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * 从字符串尾部开始查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
     * <code>-1</code>。
     *
     * <pre>
     * StringUtil.lastIndexOf(null, *)         = -1
     * StringUtil.lastIndexOf(&quot;&quot;, *)           = -1
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a') = 7
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b') = 5
     * </pre>
     *
     * @param str
     *            要扫描的字符串
     * @param searchChar
     *            要查找的字符
     *
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOf(String str, char searchChar) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }

        return str.lastIndexOf(searchChar);
    }

    /**
     * 取指定字符串的子串。
     *
     * <p>
     * 负的索引代表从尾部开始计算。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.substring(null, *, *)    = null
     * StringUtil.substring(&quot;&quot;, * ,  *)    = &quot;&quot;;
     * StringUtil.substring(&quot;abc&quot;, 0, 2)   = &quot;ab&quot;
     * StringUtil.substring(&quot;abc&quot;, 2, 0)   = &quot;&quot;
     * StringUtil.substring(&quot;abc&quot;, 2, 4)   = &quot;c&quot;
     * StringUtil.substring(&quot;abc&quot;, 4, 6)   = &quot;&quot;
     * StringUtil.substring(&quot;abc&quot;, 2, 2)   = &quot;&quot;
     * StringUtil.substring(&quot;abc&quot;, -2, -1) = &quot;b&quot;
     * StringUtil.substring(&quot;abc&quot;, -4, 2)  = &quot;ab&quot;
     * </pre>
     *
     * </p>
     *
     * @param str
     *            字符串
     * @param start
     *            起始索引，如果为负数，表示从尾部计算
     * @param end
     *            结束索引（不含），如果为负数，表示从尾部计算
     *
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        if (end < 0) {
            end = str.length() + end;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return EMPTY_STRING;
        }

        if (start < 0) {
            start = 0;
        }

        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 在字符串中查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtil.indexOf(null, *, *)          = -1
     * StringUtil.indexOf(*, null, *)          = -1
     * StringUtil.indexOf(&quot;&quot;, &quot;&quot;, 0)           = 0
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;a&quot;, 0)  = 0
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 0)  = 2
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;ab&quot;, 0) = 1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 3)  = 5
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, 9)  = -1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;b&quot;, -1) = 2
     * StringUtil.indexOf(&quot;aabaabaa&quot;, &quot;&quot;, 2)   = 2
     * StringUtil.indexOf(&quot;abc&quot;, &quot;&quot;, 9)        = 3
     * </pre>
     *
     * @param str
     *            要扫描的字符串
     * @param searchStr
     *            要查找的字符串
     * @param startPos
     *            开始搜索的索引值，如果小于0，则看作0
     *
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOf(String str, String searchStr, int startPos) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }

        // JDK1.3及以下版本的bug：不能正确处理下面的情况
        if ((searchStr.length() == 0) && (startPos >= str.length())) {
            return str.length();
        }

        return str.indexOf(searchStr, startPos);
    }

    /**
     * 在字符串中查找指定字符，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>。
     *
     * <pre>
     * StringUtil.indexOf(null, *, *)          = -1
     * StringUtil.indexOf(&quot;&quot;, *, *)            = -1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 0)  = 2
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 3)  = 5
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', 9)  = -1
     * StringUtil.indexOf(&quot;aabaabaa&quot;, 'b', -1) = 2
     * </pre>
     *
     * @param str
     *            要扫描的字符串
     * @param searchChar
     *            要查找的字符
     * @param startPos
     *            开始搜索的索引值，如果小于0，则看作0
     *
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOf(String str, char searchChar, int startPos) {
        if ((str == null) || (str.length() == 0)) {
            return -1;
        }

        return str.indexOf(searchChar, startPos);
    }

    /**
     * 在字符串中查找指定字符串，并返回第num个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
     * <code>-1</code>。
     *
     * @param num
     *            出现的次数
     * @param str
     *            要扫描的字符串
     * @param searchStr
     *            要查找的字符串
     * @return 第num个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int indexOf(int num, String str, String searchStr) {
        int index = 0;
        for (int i = 0;i<num;i++){
            index = indexOf(str,searchStr,index+1);
        }
        return index;
    }

    /**
     * 替换指定的子串，替换所有出现的子串。
     *
     * <p>
     * 如果字符串为<code>null</code>则返回<code>null</code>，如果指定子串为<code>null</code>
     * ，则返回原字符串。
     *
     * <pre>
     * StringUtil.replace(null, *, *)        = null
     * StringUtil.replace(&quot;&quot;, *, *)          = &quot;&quot;
     * StringUtil.replace(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtil.replace(&quot;aba&quot;, null, null) = &quot;aba&quot;
     * StringUtil.replace(&quot;aba&quot;, &quot;a&quot;, null)  = &quot;aba&quot;
     * StringUtil.replace(&quot;aba&quot;, &quot;a&quot;, &quot;&quot;)    = &quot;b&quot;
     * StringUtil.replace(&quot;aba&quot;, &quot;a&quot;, &quot;z&quot;)   = &quot;zbz&quot;
     * </pre>
     *
     * </p>
     *
     * @param text
     *            要扫描的字符串
     * @param repl
     *            要搜索的子串
     * @param with
     *            替换字符串
     *
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replace(String text, String repl, String with) {
        return replace(text, repl, with, -1);
    }

    /**
     * 取指定字符串的子串。
     *
     * <p>
     * 负的索引代表从尾部开始计算。如果字符串为<code>null</code>，则返回<code>null</code>。
     *
     * <pre>
     * StringUtil.substring(null, *)   = null
     * StringUtil.substring(&quot;&quot;, *)     = &quot;&quot;
     * StringUtil.substring(&quot;abc&quot;, 0)  = &quot;abc&quot;
     * StringUtil.substring(&quot;abc&quot;, 2)  = &quot;c&quot;
     * StringUtil.substring(&quot;abc&quot;, 4)  = &quot;&quot;
     * StringUtil.substring(&quot;abc&quot;, -2) = &quot;bc&quot;
     * StringUtil.substring(&quot;abc&quot;, -4) = &quot;abc&quot;
     * </pre>
     *
     * </p>
     *
     * @param str
     *            字符串
     * @param start
     *            起始索引，如果为负数，表示从尾部查找
     *
     * @return 子串，如果原始串为<code>null</code>，则返回<code>null</code>
     */
    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }

        if (start > str.length()) {
            return EMPTY_STRING;
        }

        return str.substring(start);
    }

    /**
     * 替换指定的子串，只替换第一个出现的子串。
     *
     * @param startPos
     *            开始搜索的索引值，如果小于0，则看作0
     * @param text
     *            要扫描的字符串
     * @param repl
     *            要搜索的子串
     * @param with
     *            替换字符串
     * @return 被替换后的字符串，如果原始字符串为<code>null</code>，则返回<code>null</code>
     */
    public static String replaceOnce(int startPos,String text, String repl, String with) {
        String target = StringUtils.substring(text, startPos);
        String before = StringUtils.substring(text, 0,startPos);
        String after = StringUtils.replaceOnce(target, repl, with);
        return (before + after);
    }

    /**
     * 从字符串尾部开始查找指定字符串，并返回第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回
     * <code>-1</code>。
     *
     * <pre>
     * StringUtil.lastIndexOf(null, *)         = -1
     * StringUtil.lastIndexOf(&quot;&quot;, *)           = -1
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'a') = 7
     * StringUtil.lastIndexOf(&quot;aabaabaa&quot;, 'b') = 5
     * </pre>
     *
     * @param str
     *            要扫描的字符串
     * @param searchStr
     *            要查找的字符串
     *
     * @return 第一个匹配的索引值。如果字符串为<code>null</code>或未找到，则返回<code>-1</code>
     */
    public static int lastIndexOf(String str, String searchStr) {
        if ((str == null) || (searchStr == null)) {
            return -1;
        }

        return str.lastIndexOf(searchStr);
    }
    /**
     * 获取随机的32位UUID字符串：(已将其中的"-"替换掉)
     * <p>	另：还可使用RandomGUID
     * <br>		地址：http://www.javaexchange.com/aboutRandomGUID.html
     * @return
     */
    public static String getRandomUUIDStr() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    /**
     * 得到一个日期加随机数的字符串
     * @param length			随机数的个数，如不加随机数，则用0代替
     * @return					如果length=8，返回形式为：20110318103647015PVje2TJO
     */
    public static String getDateAndRandStr(int length) {
        return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())
                + getRandomStr(length);
    }

    /**
     * string split 方法重写 添加null判断
     * @param sourceStr sourceStr
     * @param limiter limiter
     * @param maxCount maxCount
     * @return String[]
     */
    public static final String[] split(final String sourceStr, final String limiter, final int maxCount) {
        if (StringUtil.isEmpty(sourceStr) || StringUtil.isEmpty(limiter)) {
            return new String[0];
        }
        return StringUtils.split(sourceStr, limiter, maxCount);
    }

    /**
     * 用户 split 重新添加null判断
     * @param sourceStr 原文件
     * @param limiter limiter 分割的字符串
     * @return String[]
     */
    public static final String[] split(final String sourceStr, final String limiter) {
        return split(sourceStr, limiter, 0);
    }
}
