package com.the.harbor.commons.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class MyX509TrustManager implements X509TrustManager{

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
	//检查客户端证书
	public void checkClientTrursted(X509Certificate[] chain,String authType) throws CertificateException{
		
	}
	
	
	//检查服务器端证书
	public void checkServerTrusted(X509Certificate[] chain,String authType) throws CertificateException{
		
	}

	@Override
	public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}
	
}
