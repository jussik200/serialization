package com.serialization;

import static com.serialization.SerializationWriter.writeBytes;

public class Array {
	
	public static final byte CONTAINER_TYPE = ContainerType.ARRAY; // (field, array, object)
	public short 	nameLength;
	public byte[] 	name;
	public byte 	type;
	public int	 	count;
	
	public byte[] 		byteData;
	private short[]		shortData;
	private char[]		charData;
	private int[]		intData;
	private long[]		longData;
	private float[]		floatData;
	private double[]	doubleData;
	private boolean[]	booleanData;
	

	public void setName(String name) {
		assert(name.length() < Short.MAX_VALUE);
		this.nameLength = (short)name.length();
		this.name = name.getBytes();
	}

	public int getBytes(byte[] dest, int pointer){
		pointer = writeBytes(dest, pointer, CONTAINER_TYPE);
		pointer = writeBytes(dest, pointer, nameLength);
		pointer = writeBytes(dest, pointer, name);
		pointer = writeBytes(dest, pointer, count);
		pointer = writeBytes(dest, pointer, type);
		switch (type)
		{
		case Type.BYTE: pointer = writeBytes(dest, pointer, byteData); break;
		case Type.SHORT: pointer = writeBytes(dest, pointer, shortData); break;
		case Type.CHAR: pointer = writeBytes(dest, pointer, charData); break;
		case Type.INTEGER: pointer = writeBytes(dest, pointer, intData); break;
		case Type.LONG: pointer = writeBytes(dest, pointer, longData); break;
		case Type.FLOAT: pointer = writeBytes(dest, pointer, floatData); break;
		case Type.DOUBLE: pointer = writeBytes(dest, pointer, doubleData); break;
		case Type.BOOLEAN: pointer = writeBytes(dest, pointer, booleanData); break;
		}
		return pointer;
	}

	public int getSize(){
		return 1 + 2 + name.length + 1 + 4 + getDataSize();
	}
	
	public int getDataSize(){
		switch (type)
		{
		case Type.BYTE: 	return Type.getSize(Type.BYTE) 		* count;
		case Type.SHORT: 	return Type.getSize(Type.SHORT) 	* count;
		case Type.CHAR: 	return Type.getSize(Type.CHAR) 		* count;
		case Type.INTEGER: 	return Type.getSize(Type.INTEGER) 	* count;
		case Type.LONG:		return Type.getSize(Type.LONG) 		* count;
		case Type.FLOAT:	return Type.getSize(Type.FLOAT) 	* count;
		case Type.DOUBLE: 	return Type.getSize(Type.DOUBLE) 	* count;
		case Type.BOOLEAN:	return Type.getSize(Type.BOOLEAN) 	* count;
		}
		assert(false);
		return 0;
	}
	

	public static Array Byte(String name, byte[] data) {
		Array array = new Array();
		array.setName(name);
		array.type = Type.BYTE;
		array.count = data.length;
		array.byteData = data;
		return array;
	}
	public static Array Short(String name, short[] data) {
		Array array = new Array();
		array.setName(name);
		array.type = Type.INTEGER;
		array.count = data.length;
		array.shortData = data;
		return array;
	}
	public static Array Char(String name, char[] data) {
		Array array = new Array();
		array.setName(name);
		array.type = Type.INTEGER;
		array.count = data.length;
		array.charData = data;
		return array;
	}
	public static Array Integer(String name, int[] data) {
		Array array = new Array();
		array.setName(name);
		array.type = Type.INTEGER;
		array.count = data.length;
		array.intData = data;
		return array;
	}
	public static Array Long(String name, long[] data) {
		Array array = new Array();
		array.setName(name);
		array.type = Type.LONG;
		array.count = data.length;
		array.longData = data;
		return array;
	}
	public static Array Float(String name, float[] data) {
		Array array = new Array();
		array.setName(name);
		array.type = Type.FLOAT;
		array.count = data.length;
		array.floatData = data;
		return array;
	}
	public static Array Double(String name, double[] data) {
		Array array = new Array();
		array.setName(name);
		array.type = Type.DOUBLE;
		array.count = data.length;
		array.doubleData = data;
		return array;
	}
	public static Array Boolean(String name, boolean[] data) {
		Array array = new Array();
		array.setName(name);
		array.type = Type.BOOLEAN;
		array.count = data.length;
		array.booleanData = data;
		return array;
	}
	

}
