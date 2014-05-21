import static org.junit.Assert.assertEquals;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.abc.task.coder.DESCoder;
import com.abc.task.vo.Member;

public class DESCoderTest {

	@Test
	public void test() throws Exception {
		ObjectMapper o = new ObjectMapper();
		Member m = new Member();
		m.setEmail("zhangsan@task.com");
		String inputStr = o.writeValueAsString(m);
		byte[] key = DESCoder.initKey();
		System.err.println("原文:\t" + inputStr);

		System.err.println("密钥:\t" + new String(key,"utf-8"));

		byte[] inputData = inputStr.getBytes("utf-8");
		inputData = DESCoder.encrypt(inputData, key);

		System.err.println("加密后:\t" + new String(DESCoder.encryptBASE64(inputData),"utf-8"));

		byte[] outputData = DESCoder.decrypt(inputData, key);
		String outputStr = new String(outputData);

		System.err.println("解密后:\t" + outputStr);

		assertEquals(inputStr, outputStr);
	}
}
