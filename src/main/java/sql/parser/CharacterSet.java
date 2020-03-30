package sql.parser;

import java.lang.reflect.Field;
import java.util.*;

/**
 * A character set is a set of symbols and encodings.
 *
 * <p>A collation is a set of rules for comparing characters in a character set.</p>
 * <p>To use these features effectively,
 * you must know what character sets and collations are available,
 * how to change the defaults, and how they affect the behavior of string operators and functions.</p>
 *
 * <p></p>
 * <p>
 * MySQL can do these things for you:
 * <ul>
 *     <li>Store strings using a variety of character sets.</li>
 *     <li>Compare strings using a variety of collations.</li>
 *     <li>Mix strings with different character sets or collations in the same server, the same database, or even the
 *     same table.</li>
 *     <li>Enable specification of character set and collation at any level.</li>
 * </ul>
 *
 * @author liwenhe
 */
public class CharacterSet {

    /**
     * Parsing all subclass of CharacterSet and Their static fields to a map.
     * The map key is character set name, and value is the collations of the character set
     *
     * <p>
     * The type of Subclass static field must be String.
     * If it wasn't or parse failed, then throw a {@link CharacterSetException}
     * </p>
     */
    private final static Map<String, Set<String>> CHARACTER_SET;

    /**
     * Parsing all subclass of CharacterSet static field to {@link CharacterSet#COLLATIONS}
     *
     * <p>
     *     The type of Subclass static field must be String.
     *     If it wasn't or parse failed, then throw a {@link CharacterSetException}
     * </p>
     */
    private final static Set<String> COLLATIONS;

    static {
        Class[] clzs = CharacterSet.class.getClasses();
        CHARACTER_SET = new HashMap<>(clzs.length);
        COLLATIONS = new HashSet<>(clzs.length);
        Set<String> collationSet;

        for (Class clz : clzs) {
            Field[] fields = clz.getDeclaredFields();
            collationSet = new HashSet<>(fields.length);
            for (Field field : fields) {
                field.setAccessible(true);
                if (!field.getType().isAssignableFrom(String.class)) {
                    throw new CharacterSetException(
                        String.format("Field[=%s] type[=%s] must be String in [%s]."
                            , field.getName()
                            , field.getType()
                            , clz));
                }
                try {
                    Object filedTypeInstance = field.getType().newInstance();
                    collationSet.add((String) field.get(filedTypeInstance));
                } catch (Exception e) {
                    throw new CharacterSetException(e.getMessage(), e);
                }
            }
            COLLATIONS.addAll(collationSet);
            CHARACTER_SET.put(clz.getSimpleName(), collationSet);
        }
    }

    /**
     * Check collation if exists in {@link CharacterSet#COLLATIONS}
     *
     * @param collation input a collation
     * @return if exists a collation, then return true, or return false
     */
    public static Boolean exists(String collation) {
        return COLLATIONS.contains(collation.toUpperCase());
    }

    /**
     * Check charsetName exists in keys of map {@link CharacterSet#CHARACTER_SET},
     * and the collation in the collations set {@link CharacterSet#CHARACTER_SET} of given charset
     *
     * @param charsetName charset name
     * @param collation   input a collation
     * @return If and only if charsetName exists and collation in the collations of the charset return true, other
     * return false.
     */
    public static Boolean exists(String charsetName, String collation) {
        Set<String> collations = CHARACTER_SET.get(charsetName.toUpperCase());
        if (collations == null) {
            return false;
        }

        return collations.contains(collation.toUpperCase());
    }

    public static class BIG5 extends CharacterSet {
        public static String BIG5_CHINESE_CI = "BIG5_CHINESE_CI";
        public static String BIG5_BIN = "BIG5_BIN";
    }

    public static class DEC8 extends CharacterSet {
        public static String DEC8_SWEDISH_CI = "DEC8_SWEDISH_CI";
        public static String DEC8_BIN = "DEC8_BIN";
    }

    public static class CP850 extends CharacterSet {
        public static String CP850_GENERAL_CI = "CP850_GENERAL_CI";
        public static String CP850_BIN = "CP850_BIN";
    }

    public static class HP8 extends CharacterSet {
        public static String HP8_ENGLISH_CI = "HP8_ENGLISH_CI";
        public static String HP8_BIN = "HP8_BIN";
    }

    public static class KOI8R extends CharacterSet {
        public static String KOI8R_GENERAL_CI = "KOI8R_GENERAL_CI";
        public static String KOI8R_BIN = "KOI8R_BIN";
    }

    public static class LATIN1 extends CharacterSet {
        public static String LATIN1_GERMAN1_CI = "LATIN1_GERMAN1_CI";
        public static String LATIN1_SWEDISH_CI = "LATIN1_SWEDISH_CI";
        public static String LATIN1_DANISH_CI = "LATIN1_DANISH_CI";
        public static String LATIN1_GERMAN2_CI = "LATIN1_GERMAN2_CI";
        public static String LATIN1_BIN = "LATIN1_BIN";
        public static String LATIN1_GENERAL_CI = "LATIN1_GENERAL_CI";
        public static String LATIN1_GENERAL_CS = "LATIN1_GENERAL_CS";
        public static String LATIN1_SPANISH_CI = "LATIN1_SPANISH_CI";
    }

    public static class LATIN2 extends CharacterSet {
        public static String LATIN2_CZECH_CS = "LATIN2_CZECH_CS";
        public static String LATIN2_GENERAL_CI = "LATIN2_GENERAL_CI";
        public static String LATIN2_HUNGARIAN_CI = "LATIN2_HUNGARIAN_CI";
        public static String LATIN2_CROATIAN_CI = "LATIN2_CROATIAN_CI";
        public static String LATIN2_BIN = "LATIN2_BIN";
    }

    public static class SWE7 extends CharacterSet {
        public static String SWE7_SWEDISH_CI = "SWE7_SWEDISH_CI";
        public static String SWE7_BIN = "SWE7_BIN";
    }

    public static class ASCII extends CharacterSet {
        public static String ASCII_GENERAL_CI = "ASCII_GENERAL_CI";
        public static String ASCII_BIN = "ASCII_BIN";
    }

    public static class UJIS extends CharacterSet {
        public static String UJIS_JAPANESE_CI = "UJIS_JAPANESE_CI";
        public static String UJIS_BIN = "UJIS_BIN";
    }

    public static class SJIS extends CharacterSet {
        public static String SJIS_JAPANESE_CI = "SJIS_JAPANESE_CI";
        public static String SJIS_BIN = "SJIS_BIN";
    }

    public static class HEBREW extends CharacterSet {
        public static String HEBREW_GENERAL_CI = "HEBREW_GENERAL_CI";
        public static String HEBREW_BIN = "HEBREW_BIN";
    }

    public static class TIS620 extends CharacterSet {
        public static String TIS620_THAI_CI = "TIS620_THAI_CI";
        public static String TIS620_BIN = "TIS620_BIN";
    }

    public static class EUCKR extends CharacterSet {
        public static String EUCKR_KOREAN_CI = "EUCKR_KOREAN_CI";
        public static String EUCKR_BIN = "EUCKR_BIN";
    }

    public static class KOI8U extends CharacterSet {
        public static String KOI8U_GENERAL_CI = "KOI8U_GENERAL_CI";
        public static String KOI8U_BIN = "KOI8U_BIN";
    }

    public static class GB2312 extends CharacterSet {
        public static String GB2312_CHINESE_CI = "GB2312_CHINESE_CI";
        public static String GB2312_BIN = "GB2312_BIN";
    }

    public static class GREEK extends CharacterSet {
        public static String GREEK_GENERAL_CI = "GREEK_GENERAL_CI";
        public static String GREEK_BIN = "GREEK_BIN";
    }

    public static class CP1250 extends CharacterSet {
        public static String CP1250_GENERAL_CI = "CP1250_GENERAL_CI";
        public static String CP1250_CZECH_CS = "CP1250_CZECH_CS";
        public static String CP1250_CROATIAN_CI = "CP1250_CROATIAN_CI";
        public static String CP1250_BIN = "CP1250_BIN";
        public static String CP1250_POLISH_CI = "CP1250_POLISH_CI";
    }

    public static class GBK extends CharacterSet {
        public static String GBK_CHINESE_CI = "GBK_CHINESE_CI";
        public static String GBK_BIN = "GBK_BIN";
    }

    public static class LATIN5 extends CharacterSet {
        public static String LATIN5_TURKISH_CI = "LATIN5_TURKISH_CI";
        public static String LATIN5_BIN = "LATIN5_BIN";
    }

    public static class ARMSCII8 extends CharacterSet {
        public static String ARMSCII8_GENERAL_CI = "ARMSCII8_GENERAL_CI";
        public static String ARMSCII8_BIN = "ARMSCII8_BIN";
    }

    public static class UTF8 extends CharacterSet {
        public static String UTF8_GENERAL_CI = "UTF8_GENERAL_CI";
        public static String UTF8_BIN = "UTF8_BIN";
        public static String UTF8_UNICODE_CI = "UTF8_UNICODE_CI";
        public static String UTF8_ICELANDIC_CI = "UTF8_ICELANDIC_CI";
        public static String UTF8_LATVIAN_CI = "UTF8_LATVIAN_CI";
        public static String UTF8_ROMANIAN_CI = "UTF8_ROMANIAN_CI";
        public static String UTF8_SLOVENIAN_CI = "UTF8_SLOVENIAN_CI";
        public static String UTF8_POLISH_CI = "UTF8_POLISH_CI";
        public static String UTF8_ESTONIAN_CI = "UTF8_ESTONIAN_CI";
        public static String UTF8_SPANISH_CI = "UTF8_SPANISH_CI";
        public static String UTF8_SWEDISH_CI = "UTF8_SWEDISH_CI";
        public static String UTF8_TURKISH_CI = "UTF8_TURKISH_CI";
        public static String UTF8_CZECH_CI = "UTF8_CZECH_CI";
        public static String UTF8_DANISH_CI = "UTF8_DANISH_CI";
        public static String UTF8_LITHUANIAN_CI = "UTF8_LITHUANIAN_CI";
        public static String UTF8_SLOVAK_CI = "UTF8_SLOVAK_CI";
        public static String UTF8_SPANISH2_CI = "UTF8_SPANISH2_CI";
        public static String UTF8_ROMAN_CI = "UTF8_ROMAN_CI";
        public static String UTF8_PERSIAN_CI = "UTF8_PERSIAN_CI";
        public static String UTF8_ESPERANTO_CI = "UTF8_ESPERANTO_CI";
        public static String UTF8_HUNGARIAN_CI = "UTF8_HUNGARIAN_CI";
        public static String UTF8_SINHALA_CI = "UTF8_SINHALA_CI";
        public static String UTF8_GERMAN2_CI = "UTF8_GERMAN2_CI";
        public static String UTF8_CROATIAN_CI = "UTF8_CROATIAN_CI";
        public static String UTF8_UNICODE_520_CI = "UTF8_UNICODE_520_CI";
        public static String UTF8_VIETNAMESE_CI = "UTF8_VIETNAMESE_CI";
        public static String UTF8_GENERAL_MYSQL500_CI = "UTF8_GENERAL_MYSQL500_CI";
    }

    public static class UCS2 extends CharacterSet {
        public static String UCS2_GENERAL_CI = "UCS2_GENERAL_CI";
        public static String UCS2_BIN = "UCS2_BIN";
        public static String UCS2_UNICODE_CI = "UCS2_UNICODE_CI";
        public static String UCS2_ICELANDIC_CI = "UCS2_ICELANDIC_CI";
        public static String UCS2_LATVIAN_CI = "UCS2_LATVIAN_CI";
        public static String UCS2_ROMANIAN_CI = "UCS2_ROMANIAN_CI";
        public static String UCS2_SLOVENIAN_CI = "UCS2_SLOVENIAN_CI";
        public static String UCS2_POLISH_CI = "UCS2_POLISH_CI";
        public static String UCS2_ESTONIAN_CI = "UCS2_ESTONIAN_CI";
        public static String UCS2_SPANISH_CI = "UCS2_SPANISH_CI";
        public static String UCS2_SWEDISH_CI = "UCS2_SWEDISH_CI";
        public static String UCS2_TURKISH_CI = "UCS2_TURKISH_CI";
        public static String UCS2_CZECH_CI = "UCS2_CZECH_CI";
        public static String UCS2_DANISH_CI = "UCS2_DANISH_CI";
        public static String UCS2_LITHUANIAN_CI = "UCS2_LITHUANIAN_CI";
        public static String UCS2_SLOVAK_CI = "UCS2_SLOVAK_CI";
        public static String UCS2_SPANISH2_CI = "UCS2_SPANISH2_CI";
        public static String UCS2_ROMAN_CI = "UCS2_ROMAN_CI";
        public static String UCS2_PERSIAN_CI = "UCS2_PERSIAN_CI";
        public static String UCS2_ESPERANTO_CI = "UCS2_ESPERANTO_CI";
        public static String UCS2_HUNGARIAN_CI = "UCS2_HUNGARIAN_CI";
        public static String UCS2_SINHALA_CI = "UCS2_SINHALA_CI";
        public static String UCS2_GERMAN2_CI = "UCS2_GERMAN2_CI";
        public static String UCS2_CROATIAN_CI = "UCS2_CROATIAN_CI";
        public static String UCS2_UNICODE_520_CI = "UCS2_UNICODE_520_CI";
        public static String UCS2_VIETNAMESE_CI = "UCS2_VIETNAMESE_CI";
        public static String UCS2_GENERAL_MYSQL500_CI = "UCS2_GENERAL_MYSQL500_CI";
    }

    public static class CP866 extends CharacterSet {
        public static String CP866_GENERAL_CI = "CP866_GENERAL_CI";
        public static String CP866_BIN = "CP866_BIN";
    }

    public static class KEYBCS2 extends CharacterSet {
        public static String KEYBCS2_GENERAL_CI = "KEYBCS2_GENERAL_CI";
        public static String KEYBCS2_BIN = "KEYBCS2_BIN";
    }

    public static class MACCE extends CharacterSet {
        public static String MACCE_GENERAL_CI = "MACCE_GENERAL_CI";
        public static String MACCE_BIN = "MACCE_BIN";
    }

    public static class MACROMAN extends CharacterSet {
        public static String MACROMAN_GENERAL_CI = "MACROMAN_GENERAL_CI";
        public static String MACROMAN_BIN = "MACROMAN_BIN";
    }

    public static class CP852 extends CharacterSet {
        public static String CP852_GENERAL_CI = "CP852_GENERAL_CI";
        public static String CP852_BIN = "CP852_BIN";
    }

    public static class LATIN7 extends CharacterSet {
        public static String LATIN7_ESTONIAN_CS = "LATIN7_ESTONIAN_CS";
        public static String LATIN7_GENERAL_CI = "LATIN7_GENERAL_CI";
        public static String LATIN7_GENERAL_CS = "LATIN7_GENERAL_CS";
        public static String LATIN7_BIN = "LATIN7_BIN";
    }

    public static class UTF8MB4 extends CharacterSet {
        public static String UTF8MB4_GENERAL_CI = "UTF8MB4_GENERAL_CI";
        public static String UTF8MB4_BIN = "UTF8MB4_BIN";
        public static String UTF8MB4_UNICODE_CI = "UTF8MB4_UNICODE_CI";
        public static String UTF8MB4_ICELANDIC_CI = "UTF8MB4_ICELANDIC_CI";
        public static String UTF8MB4_LATVIAN_CI = "UTF8MB4_LATVIAN_CI";
        public static String UTF8MB4_ROMANIAN_CI = "UTF8MB4_ROMANIAN_CI";
        public static String UTF8MB4_SLOVENIAN_CI = "UTF8MB4_SLOVENIAN_CI";
        public static String UTF8MB4_POLISH_CI = "UTF8MB4_POLISH_CI";
        public static String UTF8MB4_ESTONIAN_CI = "UTF8MB4_ESTONIAN_CI";
        public static String UTF8MB4_SPANISH_CI = "UTF8MB4_SPANISH_CI";
        public static String UTF8MB4_SWEDISH_CI = "UTF8MB4_SWEDISH_CI";
        public static String UTF8MB4_TURKISH_CI = "UTF8MB4_TURKISH_CI";
        public static String UTF8MB4_CZECH_CI = "UTF8MB4_CZECH_CI";
        public static String UTF8MB4_DANISH_CI = "UTF8MB4_DANISH_CI";
        public static String UTF8MB4_LITHUANIAN_CI = "UTF8MB4_LITHUANIAN_CI";
        public static String UTF8MB4_SLOVAK_CI = "UTF8MB4_SLOVAK_CI";
        public static String UTF8MB4_SPANISH2_CI = "UTF8MB4_SPANISH2_CI";
        public static String UTF8MB4_ROMAN_CI = "UTF8MB4_ROMAN_CI";
        public static String UTF8MB4_PERSIAN_CI = "UTF8MB4_PERSIAN_CI";
        public static String UTF8MB4_ESPERANTO_CI = "UTF8MB4_ESPERANTO_CI";
        public static String UTF8MB4_HUNGARIAN_CI = "UTF8MB4_HUNGARIAN_CI";
        public static String UTF8MB4_SINHALA_CI = "UTF8MB4_SINHALA_CI";
        public static String UTF8MB4_GERMAN2_CI = "UTF8MB4_GERMAN2_CI";
        public static String UTF8MB4_CROATIAN_CI = "UTF8MB4_CROATIAN_CI";
        public static String UTF8MB4_UNICODE_520_CI = "UTF8MB4_UNICODE_520_CI";
        public static String UTF8MB4_VIETNAMESE_CI = "UTF8MB4_VIETNAMESE_CI";
    }

    public static class CP1251 extends CharacterSet {
        public static String CP1251_BULGARIAN_CI = "CP1251_BULGARIAN_CI";
        public static String CP1251_UKRAINIAN_CI = "CP1251_UKRAINIAN_CI";
        public static String CP1251_BIN = "CP1251_BIN";
        public static String CP1251_GENERAL_CI = "CP1251_GENERAL_CI";
        public static String CP1251_GENERAL_CS = "CP1251_GENERAL_CS";
    }

    public static class UTF16 extends CharacterSet {
        public static String UTF16_GENERAL_CI = "UTF16_GENERAL_CI";
        public static String UTF16_BIN = "UTF16_BIN";
        public static String UTF16_UNICODE_CI = "UTF16_UNICODE_CI";
        public static String UTF16_ICELANDIC_CI = "UTF16_ICELANDIC_CI";
        public static String UTF16_LATVIAN_CI = "UTF16_LATVIAN_CI";
        public static String UTF16_ROMANIAN_CI = "UTF16_ROMANIAN_CI";
        public static String UTF16_SLOVENIAN_CI = "UTF16_SLOVENIAN_CI";
        public static String UTF16_POLISH_CI = "UTF16_POLISH_CI";
        public static String UTF16_ESTONIAN_CI = "UTF16_ESTONIAN_CI";
        public static String UTF16_SPANISH_CI = "UTF16_SPANISH_CI";
        public static String UTF16_SWEDISH_CI = "UTF16_SWEDISH_CI";
        public static String UTF16_TURKISH_CI = "UTF16_TURKISH_CI";
        public static String UTF16_CZECH_CI = "UTF16_CZECH_CI";
        public static String UTF16_DANISH_CI = "UTF16_DANISH_CI";
        public static String UTF16_LITHUANIAN_CI = "UTF16_LITHUANIAN_CI";
        public static String UTF16_SLOVAK_CI = "UTF16_SLOVAK_CI";
        public static String UTF16_SPANISH2_CI = "UTF16_SPANISH2_CI";
        public static String UTF16_ROMAN_CI = "UTF16_ROMAN_CI";
        public static String UTF16_PERSIAN_CI = "UTF16_PERSIAN_CI";
        public static String UTF16_ESPERANTO_CI = "UTF16_ESPERANTO_CI";
        public static String UTF16_HUNGARIAN_CI = "UTF16_HUNGARIAN_CI";
        public static String UTF16_SINHALA_CI = "UTF16_SINHALA_CI";
        public static String UTF16_GERMAN2_CI = "UTF16_GERMAN2_CI";
        public static String UTF16_CROATIAN_CI = "UTF16_CROATIAN_CI";
        public static String UTF16_UNICODE_520_CI = "UTF16_UNICODE_520_CI";
        public static String UTF16_VIETNAMESE_CI = "UTF16_VIETNAMESE_CI";
    }

    public static class UTF16LE extends CharacterSet {
        public static String UTF16LE_GENERAL_CI = "UTF16LE_GENERAL_CI";
        public static String UTF16LE_BIN = "UTF16LE_BIN";
    }

    public static class CP1256 extends CharacterSet {
        public static String CP1256_GENERAL_CI = "CP1256_GENERAL_CI";
        public static String CP1256_BIN = "CP1256_BIN";
    }

    public static class CP1257 extends CharacterSet {
        public static String CP1257_LITHUANIAN_CI = "CP1257_LITHUANIAN_CI";
        public static String CP1257_BIN = "CP1257_BIN";
        public static String CP1257_GENERAL_CI = "CP1257_GENERAL_CI";
    }

    public static class UTF32 extends CharacterSet {
        public static String UTF32_GENERAL_CI = "UTF32_GENERAL_CI";
        public static String UTF32_BIN = "UTF32_BIN";
        public static String UTF32_UNICODE_CI = "UTF32_UNICODE_CI";
        public static String UTF32_ICELANDIC_CI = "UTF32_ICELANDIC_CI";
        public static String UTF32_LATVIAN_CI = "UTF32_LATVIAN_CI";
        public static String UTF32_ROMANIAN_CI = "UTF32_ROMANIAN_CI";
        public static String UTF32_SLOVENIAN_CI = "UTF32_SLOVENIAN_CI";
        public static String UTF32_POLISH_CI = "UTF32_POLISH_CI";
        public static String UTF32_ESTONIAN_CI = "UTF32_ESTONIAN_CI";
        public static String UTF32_SPANISH_CI = "UTF32_SPANISH_CI";
        public static String UTF32_SWEDISH_CI = "UTF32_SWEDISH_CI";
        public static String UTF32_TURKISH_CI = "UTF32_TURKISH_CI";
        public static String UTF32_CZECH_CI = "UTF32_CZECH_CI";
        public static String UTF32_DANISH_CI = "UTF32_DANISH_CI";
        public static String UTF32_LITHUANIAN_CI = "UTF32_LITHUANIAN_CI";
        public static String UTF32_SLOVAK_CI = "UTF32_SLOVAK_CI";
        public static String UTF32_SPANISH2_CI = "UTF32_SPANISH2_CI";
        public static String UTF32_ROMAN_CI = "UTF32_ROMAN_CI";
        public static String UTF32_PERSIAN_CI = "UTF32_PERSIAN_CI";
        public static String UTF32_ESPERANTO_CI = "UTF32_ESPERANTO_CI";
        public static String UTF32_HUNGARIAN_CI = "UTF32_HUNGARIAN_CI";
        public static String UTF32_SINHALA_CI = "UTF32_SINHALA_CI";
        public static String UTF32_GERMAN2_CI = "UTF32_GERMAN2_CI";
        public static String UTF32_CROATIAN_CI = "UTF32_CROATIAN_CI";
        public static String UTF32_UNICODE_520_CI = "UTF32_UNICODE_520_CI";
        public static String UTF32_VIETNAMESE_CI = "UTF32_VIETNAMESE_CI";
    }

    public static class BINARY extends CharacterSet {
        public static String BINARY = "BINARY";
    }

    public static class GEOSTD8 extends CharacterSet {
        public static String GEOSTD8_GENERAL_CI = "GEOSTD8_GENERAL_CI";
        public static String GEOSTD8_BIN = "GEOSTD8_BIN";
    }

    public static class CP932 extends CharacterSet {
        public static String CP932_JAPANESE_CI = "CP932_JAPANESE_CI";
        public static String CP932_BIN = "CP932_BIN";
    }

    public static class EUCJPMS extends CharacterSet {
        public static String EUCJPMS_JAPANESE_CI = "EUCJPMS_JAPANESE_CI";
        public static String EUCJPMS_BIN = "EUCJPMS_BIN";
    }

    public static class GB18030 extends CharacterSet {
        public static String GB18030_CHINESE_CI = "GB18030_CHINESE_CI";
        public static String GB18030_BIN = "GB18030_BIN";
        public static String GB18030_UNICODE_520_CI = "GB18030_UNICODE_520_CI";
    }


}
