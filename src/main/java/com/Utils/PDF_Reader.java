package com.Utils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;

public class PDF_Reader extends Base {

	public static void PDF_Val() throws IOException, InterruptedException, NoSuchAlgorithmException, KeyManagementException {

		driver.findElement(By.linkText("PROCUREMENT")).click();
		driver.findElement(By.linkText("Purchase Order")).click();
		driver.findElement(By.id("orderId")).sendKeys("PO0700");
		driver.findElement(By.xpath("//*[@id='searchCriteria']/div/table/tbody/tr[17]/td/input")).click();
		driver.findElement(By.linkText("PO0700")).click();
		driver.findElement(By.linkText("Generate PDF")).click();

		Set<String> generatePDF = driver.getWindowHandles();
		Iterator<String> generatePDF1 = generatePDF.iterator();
		String MainWindowID1 = generatePDF1.next();
		String SubWindowID1 = generatePDF1.next();
		driver.switchTo().window(SubWindowID1);

		 // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                @Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                @Override
				public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                @Override
				public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };

        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        // Create all-trusting host name verifier
        HostnameVerifier allHostsValid = new HostnameVerifier() {
            @Override
			public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        FileOutputStream fos1 = new FileOutputStream("download.pdf");
     // Install the all-trusting host verifier
        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        String current = driver.getCurrentUrl();
		Thread.sleep(2000);
		URL url = new URL(current);
		int baLength;
		byte[] ba1 = new byte[1024];
		System.out.println(url.openConnection().getContentLength());

//		 File file = new File(current);
//		 PDFParser parser = new PDFParser((RandomAccessRead) new FileInputStream(file));

		InputStream is = url.openStream();

		while ((baLength = is.read(ba1)) != -1) {
            fos1.write(ba1, 0, baLength);
        }
        fos1.flush();
        fos1.close();
        is.close();

		BufferedInputStream fileParse = new BufferedInputStream(is);
		PDDocument document = null;

		document = PDDocument.load(fileParse);
		String pdfContent = new PDFTextStripper().getText(document);
		System.out.println(pdfContent);

		driver.close();
		driver.switchTo().window(MainWindowID1);


		Thread.sleep(2000);
	}

}
