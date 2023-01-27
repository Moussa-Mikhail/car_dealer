package library;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * @author Moussa
 */
public class DateAdapter extends XmlAdapter<String, Date> {
    private static final String CUSTOM_FORMAT_STRING = "yyyy-MM-dd";

    @Override
    public String marshal(Date v) {
        return new SimpleDateFormat(CUSTOM_FORMAT_STRING, Locale.US).format(v);
    }

    @Override
    public Date unmarshal(String v) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat(CUSTOM_FORMAT_STRING, Locale.US).parse(v);
        return new Date(utilDate.getTime());
    }
}