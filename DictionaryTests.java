package Yabao_HansJoshua_HashTables;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class DictionaryTests {
	@Test
	void test() {
		
		IDictionary<String, String> d = new Dictionary<String, String>();
		assertEquals(d.isEmpty(), true);
		d.insert("Marco", "Morazan");
		d.insert("James", "Kirk");
		d.insert("Kathryn", "Janeway");
		d.insert("B'Elanna", "Torres");
		assertEquals(d.isEmpty(), false);
		assertEquals(d.size().intValue(), 4);
		assertEquals(d.findElement("Marco"), "Morazan");
		assertEquals(d.findElement("B'Elanna"), "Torres");
		assertEquals(d.findElement("Josie"), null);
		d.delete("Marco");
		assertEquals(d.findElement("Marco"), null);
		assertEquals(d.size().intValue(), 3);
		
		d.insert("Marco", "Morazan");
		d.insert("James", "Kirk");
		d.insert("Kathryn", "Janeway");
		d.insert("B'Elanna", "Torres");
		d.delete("Marco");
		d.delete("James");
		d.delete("Kathryn");
		d.delete("B'Elanna");
		
		Iterator<String> klooper = d.keys();
		Iterator<String> elooper = d.elements();
		String res = "";
		while (klooper.hasNext())
		{
		res = res + " " + klooper.next() + " " + elooper.next();  }
		assertEquals(res, " James Kirk Kathryn Janeway B'Elanna Torres");
	}

}
