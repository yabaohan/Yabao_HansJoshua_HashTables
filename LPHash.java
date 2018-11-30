package Yabao_HansJoshua_HashTables;

import java.util.ArrayList;
import java.util.Iterator;

public class LPHash<K,E> implements IDictionary<K,E>{

	private Item<K, E> AVAILABLE = new Item<K, E>(null, null); // deleted cell item
	private int n; // number of elements in the hash table
	private int N; //size of the array
	private ArrayList<Item<K, E>> A;
	private HashComparator<K> h;  // provides hashIndex and Equals

	public LPHash(int s, HashComparator<K> hc)
	{
		n = 0; h = hc; N = s; int i = 0;
		A = new ArrayList<Item<K, E>>(s);
		/* Processing vector interval [0..s-1]
		 * INV: 0 <= i <= s-1 && A[0..i] == null */
		while (i <= s - 1)
		{
			A.add(i, null);
			i = i + 1;
		}
	}
	//-----------------------------------------------------------------------------------
	//auxiliary methods
	private boolean available(int i)
	{ return(A.get(i) == AVAILABLE); }
	//-----------------------------------------------------------------------------------

	private boolean empty(int i)
	{ return(A.get(i) == null);}
	//-----------------------------------------------------------------------------------

	private K key(int i)
	{return(A.get(i).getKey());}
	//-----------------------------------------------------------------------------------

	//auxiliary methods
	private E elem(int i)
	{return(A.get(i).getElem());}
	//-----------------------------------------------------------------------------------

	private int find(K k) // returns the index of the given key or -1 if there is no such key
	{ int i = (h.hashIndex(k) % N); // division method compression map
	int j = i; 
	int res = -1;  
	boolean done = false; // indicates that the search is done

	/*
	 * Processing vector interval [0..n-1]
	 * INV: 
	 * done ==> 
	(empty(j) && res == -1) || 
	 * 
	(available(j) && j == i && res == -1) || 
	 * 
	(key(j) == k && res == j) ||
	 * 
	( j == i && res = -1)
	 * && !done ==> res == -1
	 * && j in [0..n-1]
	 * && [i%N..(j-1)%N] is processed
	 * && -1 <= res <= n-1
	 */

	while (!done)
	{
		if (empty(j))  {done = true;}
		else if (available(j))
		{
			j = (j + 1) % N;
			if (j == i)   { done = true; }  }
		else if (h.keyEqual(key(j), k))
		{
			res = j;
			done = true;  }
		else
		{ 
			j = (j + 1) % N;
			if (j == i)
			{ done = true; }  }  }
	return(res);   }
	//-----------------------------------------------------------------------------------

	// Dictionary methods
	public Integer size() {return(n);}
	//-----------------------------------------------------------------------------------

	public Boolean isEmpty() {return(n == 0);}
	//-----------------------------------------------------------------------------------

	public Iterator<E> elements()
	{ Iterator<Item<K, E>> htlooper = A.iterator();
	ArrayList<E> elems = new ArrayList<E>();
	Item<K, E> k;
	while (htlooper.hasNext())
	{
		k = htlooper.next();
		if ((k != null) && (k != AVAILABLE))
		{ elems.add(k.getElem()); }
	}
	return(elems.iterator());
	}
	//-----------------------------------------------------------------------------------
	public Iterator<K> keys()
	{
		Iterator<Item<K, E>> htlooper = A.iterator();
		ArrayList<K> keys = new ArrayList<K>();
		Item<K, E> k;
		while (htlooper.hasNext())
		{
			k = htlooper.next();
			if ((k != null) && (k != AVAILABLE))
			{ keys.add(k.getKey()); }
		}
		return(keys.iterator());
	}
	//-----------------------------------------------------------------------------------

	public E findElement(K k)
	{
		int i = find(k);
		if (i < 0) { return(null); }
		else { return(elem(i)); }
	}
	//-----------------------------------------------------------------------------------

	public void insert(K k, E e)
	{// Assumes this HT is not full
		int i = h.hashIndex(k) % N;
		int j = i;
		boolean done = false;
		while (!done)
		{
			if (empty(j) || available(j))
			{
				A.set(j, new Item<K, E>(k, e));
				n = n + 1;
				done = true;}
			j = (j + 1) % N;
		} }
	//-----------------------------------------------------------------------------------
	public void delete(K k)
	{
		int i = find(k);
		if (i > -1)
		{
			A.set(i, AVAILABLE);
			n = n - 1;
		}	}
}

