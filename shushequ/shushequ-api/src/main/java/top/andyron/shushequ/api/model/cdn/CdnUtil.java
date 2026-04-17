package top.andyron.shushequ.api.model.cdn;

/**
 * @author andyron
 * @date 2026/4/17
 */
public class CdnUtil {

    private static final String TO_REPLACE_CDN = "https://cdn.tobebetterjavaer.com";
    private static final String TARGET_REPLACE_CDN = "https://cdn.paicoding.com";

    /**
     * 自动替换成新的CDN
     *
     * @param val
     * @return
     */
    public static String autoTransCdn(String val) {
//  备案完成，无需做域名替换
//        if (val.startsWith(TO_REPLACE_CDN)) {
//            return val.replace(TO_REPLACE_CDN, TARGET_REPLACE_CDN);
//        }
        return val;
    }

}
