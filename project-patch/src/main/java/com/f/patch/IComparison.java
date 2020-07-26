package com.f.patch;

import java.io.File;

/**
 * �ֽڱȽ�
 * @author matf
 *
 */
public interface IComparison {

	boolean comparison(byte[] a, byte[] b);

	boolean comparison(File a, File b);
}
