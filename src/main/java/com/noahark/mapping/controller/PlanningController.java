package com.noahark.mapping.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.noahark.mapping.util.ReadXml;

@Controller
@RequestMapping("planning")
public class PlanningController extends BaseController {

	private Map<String, String> parameters = new HashMap<String, String>();

	private CloseableHttpClient httpclient = HttpClients.createDefault();

	// Create a local instance of cookie store
	private CookieStore cookieStore = new BasicCookieStore();

	// Create local HTTP context
	private HttpClientContext localContext = HttpClientContext.create();
	
	
	public PlanningController() {
		super();
		localContext.setCookieStore(cookieStore);
	}


	@RequestMapping(value = "/openForm", method = RequestMethod.GET)
	public ModelAndView openForm(){
		
		
		String url="http://10.0.5.242:19000/HyperionPlanning/EnterData.jsp?Form=test1&Application=PGMABG&";
		
		loginWksccc("admin","oracle");
		loginPlanning();
		
		System.out.println(parameters);
		url = "redirect:" + url + "JSESSIONID=" + parameters.get("JSESSIONID");
		ModelAndView mv = new ModelAndView(url);
		return mv;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "resource" })
	public void loginPlanning() {

		// http://192.168.136.133:19000/HyperionPlanning/servlet/HspLogOn
		CloseableHttpResponse response = null;
		HttpPost httppost = new HttpPost("http://10.0.5.242:19000/HyperionPlanning/servlet/HspLogOn");

		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("Application", "PGMABG"));
		formparams.add(new BasicNameValuePair("isContained", "true"));
		formparams.add(new BasicNameValuePair("isWorkspace", "true"));
		formparams.add(new BasicNameValuePair("LOCALE_LANGUAGE", "zh_CN"));
		formparams.add(new BasicNameValuePair("moduleid", "HyperionPlanning.planning.6"));
		formparams.add(new BasicNameValuePair("themeSelection", "BpmTadpole"));
		formparams.add(new BasicNameValuePair("upk_available", "false"));
		formparams.add(new BasicNameValuePair("sso_token", parameters.get("token")));

		UrlEncodedFormEntity uefEntity;

		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");

			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			response = httpclient.execute(httppost, localContext);

			// Header header = post.getResponseHeader("Location");

			HttpEntity entity = response.getEntity();

			String url = null;

			for (Iterator headers = response.headerIterator(); headers.hasNext();) {
				Header header = (Header) headers.next();

				if (header.getName().equals("Location")) {
					System.out.println(header.getValue());
					url = header.getValue();
				}

			}

			if (url != null) {
				HttpGet httpget = new HttpGet("http://10.0.5.242:19000" + url);
				response = httpclient.execute(httpget, localContext);

				entity = response.getEntity();

			}

			System.out.println();

			if (entity != null) {
				String rs = EntityUtils.toString(entity, "UTF-8");

				System.out.println("--------------------------------------");
				//System.out.println("Response content: " + rs);

				System.out.println("--------------------------------------");

				System.out.println(response.getStatusLine().getStatusCode());
				System.out.println("**************************************");
				System.out.println(response.getStatusLine());

				List<Cookie> cookies = cookieStore.getCookies();
				for (int i = 0; i < cookies.size(); i++) {
					System.out.println("Local cookie: " + cookies.get(i));
					if (cookies.get(i).getName().equals("JSESSIONID")
							&& cookies.get(i).getPath().equals("/HyperionPlanning")) {
						
						System.out.println(cookies.get(i).getValue());
						parameters.put("JSESSIONID", cookies.get(i).getValue());
					}

				}
				System.out.println("**************************************");

				//EntityUtils.consume(response.getEntity());
			}

			//response.close();

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
	}
	
	public void loginWksccc(String user, String pwd) {

		CloseableHttpResponse response = null;

		HttpPost httppost = new HttpPost("http://10.0.5.242:19000/workspace/logon");

		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("accessibilityMode", ""));
		formparams.add(new BasicNameValuePair("LOCALE_LANGUAGE", "zh_CN"));
		formparams.add(new BasicNameValuePair("rightToLeft", "false"));
		formparams.add(new BasicNameValuePair("themeSelection", ""));

		formparams.add(new BasicNameValuePair("sso_username", user));
		formparams.add(new BasicNameValuePair("sso_password", pwd));

		UrlEncodedFormEntity uefEntity;

		try {
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");

			httppost.setEntity(uefEntity);
			System.out.println("executing request " + httppost.getURI());
			response = httpclient.execute(httppost, localContext);

			HttpEntity entity = response.getEntity();
			System.out.println(response.getLastHeader("X-ORACLE-DMS-ECID").getValue());

			for (Iterator headers = response.headerIterator(); headers.hasNext();) {
				System.out.println(headers.next());

			}

			System.out.println();

			if (entity != null) {
				String rs = EntityUtils.toString(entity, "UTF-8");

				System.out.println("--------------------------------------");
				System.out.println("Response content: " + rs);

				ReadXml.readXml(rs, parameters);

				

				System.out.println("--------------------------------------");

				System.out.println(response.getStatusLine().getStatusCode());
				System.out.println("**************************************");
				System.out.println(response.getStatusLine());
				List<Cookie> cookies = cookieStore.getCookies();
				for (int i = 0; i < cookies.size(); i++) {
					System.out.println("Local cookie: " + cookies.get(i));
					parameters.put(cookies.get(i).getName(), cookies.get(i).getValue());

				}
				System.out.println("**************************************");

				EntityUtils.consume(response.getEntity());
			}

			// HttpPost post2 = new
			// HttpPost("http://10.0.5.136:19000/workspace/modules/com/oracle/workspace/mode/Adf.jsp");
			// "http://10.0.5.136:19000/workspace/modules/com/oracle/workspace/mode/Adf.jsp"

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);

		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
	
	public void loginWks(String user) {

		CloseableHttpResponse response = null;

		HttpGet httpget = new HttpGet("http://192.168.136.133:19000/workspace/logon?userId=" + user);


		try {

			response = httpclient.execute(httpget, localContext);

			HttpEntity entity = response.getEntity();
			//System.out.println(response.getLastHeader("X-ORACLE-DMS-ECID").getValue());

			/*for (Iterator headers = response.headerIterator(); headers.hasNext();) {
				System.out.println(headers.next());

			}*/

			System.out.println();

			if (entity != null) {
				String rs = EntityUtils.toString(entity, "UTF-8");

				//System.out.println("--------------------------------------");
				//System.out.println("Response content: " + rs);

				ReadXml.readXml(rs, parameters);

				/*Pattern p = Pattern.compile("sToken = \"(.*)\";\r\n");
				Matcher m = p.matcher(rs);
				while (m.find()) {
					System.out.println(m.group(1));

					parameters.put("sso_token", m.group(1));
				}*/

				System.out.println("--------------------------------------");

				System.out.println(response.getStatusLine().getStatusCode());
				System.out.println("**************************************");
				System.out.println(response.getStatusLine());
				List<Cookie> cookies = cookieStore.getCookies();
				/*for (int i = 0; i < cookies.size(); i++) {
					System.out.println("Local cookie: " + cookies.get(i));
					//parameters.put(cookies.get(i).getName(), cookies.get(i).getValue());

				}*/
				System.out.println("**************************************");

				//EntityUtils.consume(response.getEntity());
			}


		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		} catch (ClientProtocolException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);

		} finally {
			if (response != null)
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
}
