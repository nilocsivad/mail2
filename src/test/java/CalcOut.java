
import java.util.regex.Pattern;

import com.iamVip.mail2.rs.util.RegexUtil;

/**
 * @author Colin
 */
public class CalcOut {

	/**
	 * 
	 */
	public CalcOut() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		{
			String fmt = "\"%d\", ";
			for (int i = 1; i <= 10; ++i) {
				System.out.format(fmt, i);
			}
		}

		{
			System.out.println(RegexUtil.isDouble("0"));
			System.out.println(RegexUtil.isDouble("0.234"));
			System.out.println(RegexUtil.isDouble("0.234.3"));
		}

		{
			String txt = "AB:35:23:B7:04:10";
			System.out.println(Pattern.matches("^[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}:[0-9a-fA-F]{2}$", txt));
		}

	}

}
