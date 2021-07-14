package com.Utils;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class FileRename extends Base {

	public static void test() {

		FileRename fr = new FileRename();
		File newfile = fr.getTheNewestFile("C:\\Users\\KailashR\\Downloads", "csv");
		newfile.renameTo(new File("C:\\Users\\KailashR\\Downloads\\report.csv"));
		String filename = newfile.getName();

		System.out.println("latest file is=" + filename);

		FileRename fr1 = new FileRename();
		File updated = fr1.getTheNewestFile("C:\\Users\\KailashR\\Downloads", "csv");
		System.out.println("Changed file name is =" + updated);
	}

	public File getTheNewestFile(String filePath, String ext) {
		File theNewestFile = null;
		File dir = new File(filePath);
		FileFilter fileFilter = new WildcardFileFilter("*." + ext);
		File[] files = dir.listFiles(fileFilter);

		if (files.length > 0) {
			/** The newest file comes first **/
			Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
			theNewestFile = files[0];
		}
		// System.out.println(theNewestFile.getPath());
		return theNewestFile;
	}
}
