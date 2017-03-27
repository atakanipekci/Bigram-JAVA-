/*Created by Taha Atakan İpekçi on 6.1.2017*/
/*an interface for bigrams*/
import java.lang.*;
import java.util.*;
import java.io.*;

interface Bigram<T>{

	public void readFile(String name)throws Exception;
	public int numGrams ();
	public int numOfGrams(T x, T y);
	@Override 
	public String toString();

}
