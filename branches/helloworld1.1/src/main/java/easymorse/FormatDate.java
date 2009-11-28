package easymorse;

import java.text.MessageFormat;
import java.util.Date;

public class FormatDate {

	public String formatTheDate(Date date){
		return MessageFormat.format("{0,date,yyyy年MM月dd日}",date);
	}
}
