package com.f.patch.compare;

import com.f.patch.IComparison;

public abstract class AbsComparison implements IComparison {

	@Override
	public boolean comparison(byte[] a, byte[] b) {
		boolean ret = a != null && b != null && a.length == b.length;
		if(ret){
			for(int i=0;i<a.length;i++){
				if(a[i] != b[i]){
					ret = false;
					break;
				}
			}
		}
		return ret;
	}

}
