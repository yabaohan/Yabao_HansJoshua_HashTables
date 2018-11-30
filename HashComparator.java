package Yabao_HansJoshua_HashTables;

public interface HashComparator<K> {

	public int hashIndex(K k);
	public Boolean keyEqual(K k1,  K k2);
	
}
