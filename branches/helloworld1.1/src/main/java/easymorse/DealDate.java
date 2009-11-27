package easymorse;

import java.text.MessageFormat;
import java.util.Date;
/**
 * 
 * @author 邓彦辉 <a href="mailto:jiessiedyh@gmail.com">jiessiedyh@gmail.com</a>
 */
public class DealDate {

	public String formateDate(Date date){
		return MessageFormat.format("{0,date,yyyy年MM月dd日}", date);
	}
}

