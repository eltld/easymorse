import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

/**
 * @author wangjun <a
 *         href="mailto:wangjun.0112@gmail.com">wangjun.0112@gmail.com</a>
 * �õ����ͱ�ǩ
 */
public class GetBlogCategories {
	public static void getBlogCategories()  {
		try{
            String domain = "wangjun.easymorse.com";//����վ������
			XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
			config.setServerURL(new URL("http://"+domain+"/xmlrpc.php"));
			XmlRpcClient client = new XmlRpcClient();
			client.setConfig(config);
			Object[] params = new Object[] {"��������", "username", "password" };
			// �õ������б�
			Object[] ob=(Object[])client.execute("metaWeblog.getCategories", params);
			System.out.println("�����б�"+ob.length+","+ob[1]);
		}catch (Exception e) {
                       System.out.println(" UnCreated "+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		GetBlogCategories.getBlogCategories();
	}
}
