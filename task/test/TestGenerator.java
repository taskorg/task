import org.junit.Test;

import com.abc.task.lang.SerialNumberGenerator;
import com.abc.task.rulefilter.RuleFilter;
import com.abc.task.rulefilter.IpFilter;


public class TestGenerator {
	@Test
	public void test() {
		System.out.println(SerialNumberGenerator.generate());
	}
}
