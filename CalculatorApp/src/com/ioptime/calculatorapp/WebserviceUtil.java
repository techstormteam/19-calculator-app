package com.ioptime.calculatorapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyStore;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;

public class WebserviceUtil {

	private static final String WEBSERVICE_CURRENCY_CONVERTER = "http://www.freecurrencyconverterapi.com/api/v3/convert?"
			+ "q=%s"
			+ "&compact=ultra";
	
	public static HttpClient getNewHttpClient() {
	    try {
	        KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
	        trustStore.load(null, null);

	        MySSLSocketFactory sf = new MySSLSocketFactory(trustStore);
	        sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

	        HttpParams params = new BasicHttpParams();
	        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

	        SchemeRegistry registry = new SchemeRegistry();
	        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
	        registry.register(new Scheme("https", sf, 443));

	        ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

	        return new DefaultHttpClient(ccm, params);
	    } catch (Exception e) {
	        return new DefaultHttpClient();
	    }
	}
	
	public static String api(String URL, String... params) throws ClientProtocolException, IOException {
		String createdURL = String.format(URL, params);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		HttpClient httpclient = getNewHttpClient();
	    HttpResponse response = httpclient.execute(new HttpGet(createdURL));
	    StatusLine statusLine = response.getStatusLine();
	    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        response.getEntity().writeTo(out);
	        String responseString = out.toString();
	        out.close();
	        return responseString;
	    } else{
	        //Closes the connection.
	        response.getEntity().getContent().close();
	        throw new IOException(statusLine.getReasonPhrase());
	    }
	}
	
	public static String apiCurrencyConverter(String from, String to) {
		String toReturn = "";
		String key = from + "_" + to;
		try {
			String response = api(WEBSERVICE_CURRENCY_CONVERTER, key);
			JSONObject obj = new JSONObject(response);
			toReturn = obj.getString(key);
			
		} catch (ClientProtocolException e) {
			toReturn = "No Data Recieved";
		} catch (IOException e) {
			toReturn = "No Data Recieved";
		} catch (JSONException e) {
			toReturn = "No Data Recieved";
		}
		
		return toReturn;

	}
	
}
