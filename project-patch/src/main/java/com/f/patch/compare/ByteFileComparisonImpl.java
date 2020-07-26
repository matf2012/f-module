package com.f.patch.compare;

import com.f.patch.IComparison;

import java.io.File;
import java.io.FileInputStream;

public class ByteFileComparisonImpl extends AbsComparison implements IComparison {


	@Override
	public boolean comparison(File a, File b) {
		boolean ret = a != null && b != null && a.exists() && b.exists() && a.length() == b.length();
		if(ret){
			try {
				FileInputStream aFis = new FileInputStream(a);
				FileInputStream bFis = new FileInputStream(b);
				int size = new Long(a.length()).intValue();
				byte[] aByte = new byte[size];
				byte[] bByte = new byte[size];
				aFis.read(aByte);
				bFis.read(bByte);
				ret = comparison(aByte, bByte);
				aFis.close();
				bFis.close();
			} catch (Exception e) {
				e.printStackTrace();
				ret = false;
			}
		}
		return ret;
	}

}
