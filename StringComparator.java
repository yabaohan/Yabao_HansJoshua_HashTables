package Yabao_HansJoshua_HashTables;
//FSM Library Version 1.0
//Copyright (C) 2018 by Hans Joshua Yabao  
//Written by:Ijah Penn, Mostafa Sabri, Hans Joshua Yabao, 2017
public class StringComparator implements HashComparator<String> {

	
	int a = 11;
	public StringComparator() { }
	public int hashIndex(String k)
	{
	int slen = k.length();
	int i = -1;
	int code = 0;
	while (i < slen - 1)
	{
	i = i + 1;
	code = (int) k.charAt(i) + a * code;
	}
	return(Math.abs(code));
	}
	public Boolean keyEqual(String k1,  String k2)
	{ return(k1.equals(k2)); }

}
