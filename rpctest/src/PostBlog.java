import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * @author wangjun <a
 *         href="mailto:wangjun.0112@gmail.com">wangjun.0112@gmail.com</a> ��������
 */
public class PostBlog {
	public static void post(String title, String content) {
		try {
			// Set up XML-RPC connection to server
			String domain = "wangjun.easymorse.com";// ����վ������
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("http://" + domain + "/xmlrpc.php"));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);
			Map post = new HashMap();
			post.put("title", title);// ����
			post.put("mt_keywords", "java");// ��ǩ
			Object[] categories = new Object[] { "�����" };// ����
			post.put("categories", categories);
			post.put("description", content);// ����
			Object[] params = new Object[] { "1", "username", "password",post,true }; // 1������ʽ������0����ݸ�
			String ob = (String) client.execute("metaWeblog.newPost", params);
			System.out.println("Created with blogid " + ob);
		} catch (Exception e) {
			System.out.println(" UnCreated " + e.getMessage());
		}
	}
	public static void main(String[] args) {
		PostBlog.post("�������Բ���","���Գɹ�");
	}
}
