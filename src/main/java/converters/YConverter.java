package converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.math.BigDecimal;
import java.text.NumberFormat;

@FacesConverter("convertY")
public class YConverter implements Converter {
    public Object getAsObject( FacesContext fc, UIComponent uic, String value ) {
        if(value != null) {
            try {
                return new BigDecimal( value.replace(',','.') );
            } catch (NumberFormatException ex) {
                FacesMessage msg = new FacesMessage("", "Y должен быть числом, лежащим в промежутке (-3; 3)!");
                msg.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ConverterException(msg);
            }
        }
        return null;
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object ) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits( Integer.parseInt((String)uic.getAttributes().get("maxFractionDigits")) );
        nf.setMinimumFractionDigits( Integer.parseInt((String)uic.getAttributes().get("minFractionDigits")) );
        return nf.format(object);
    }


}
