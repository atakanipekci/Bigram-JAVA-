import java.lang.*;
import java.util.*;
import java.io.*;

class BigramDyn<T> implements Bigram<T> {

	private String filename;
	private Object [] elements;
	private int dataType;
	/*Constructor that takes an integer to set the data type.
	  1 for int,2 for strings 3 for doubles*/
	BigramDyn(int datatype) {

	dataType= datatype;	
	}
	/*Get and set functions for myBigrams*/	
	public int getDataT(){

	return dataType;

	}
	public int getNoOfPairs(){

	return elements.length-1;	

	}

	public Object getElement(int i){

	return elements[i];

	}

	public void createElements(int size){

	elements=new Object[size];

	}

	public void setElement(int index, Object value){

	elements[index]= value;

	}
	/*Reads elements from file*/
	public void readFile(String name)throws Exception {

	int i =0,flag=0,flag2=0,count=0,count2=0;
	T temp1,temp2,temp3;
	String temp4;

	Scanner in = new Scanner(new File(name));
	/*First taking all the elements to a string variable. This way we can read all
of them no matter the type. Stores the amount of elements in count*/
	while(in.hasNextLine()){

	Scanner line = new Scanner(in.nextLine());
		
		while(line.hasNext()){

		String word = line.next();
		count++;
		}
	}

		if(count <2)
		throw new myException("Not enough elements in file to create a bigram");

	createElements(count);
	/*Going back to the start of the file to actually read and store this time*/
	in.close();
	in = new Scanner(new File(name));
	
	

	while(in.hasNextLine()){

	Scanner line = new Scanner(in.nextLine());
	line.useLocale(Locale.US);/*Without this hasNext methods might not work correctly*/
/*Reading data according to the dataType. This way we can check for exceptions*/	
		switch (getDataT()){

			case 2://for strings
				while(line.hasNext()){
		
				setElement(i,line.next());
		
				i++;
				}
				break;

			case 1://for ints
				while(line.hasNextInt()){
		
				setElement(i,line.next());
		
				i++;
				}
				break;

			case 3://for doubles
				
				
			
				while(line.hasNextDouble()){
				
				setElement(i,line.next());
		
				i++;
				}
				break;
		

		}
	}
	
	/*If the amount we read to our Object type variable above is not the same as the one we
	read to our string variable then it means the file contains atleast 1 wrong
	type of data. Which is accepted as an exception*/

	if(count != i)
		throw new myException("File contains wrong type of data");

	
	}
/*Returns the total number of bigrams that are calculated*/
	public int numGrams (){

	return getNoOfPairs();

	}
	/*Returns the occurance of a bigram that is taken as an input*/
	public int numOfGrams(Object x, Object y){
	int count=0;
	
		for(int i = 0; i<getNoOfPairs(); i++){

			if(getElement(i).equals(x) && getElement(i+1).equals(y))
			{
				count++;
			}
		}


	return count;

	}
	/*prints all the bigrams and their occurences in decreasing occurency order.*/
	@Override
	public String toString(){
	
	int i,j,k,count,flag,temp;
	Object [] temp2= new Object[2];
	Object [][] sorted = new Object [getNoOfPairs()][2];
	Object [][] used = new Object [getNoOfPairs()][2];
	/*copying our pairs to a 2D object array*/
		for(i=0; i<sorted.length; i++){
		
		sorted[i][0]=getElement(i);
		sorted[i][1]=getElement(i+1);

		}

		/*sorting them.Bubble sort*/
		for(i=0; i<sorted.length; i++){
			
			for(j=0; j<sorted.length-1; j++){
				
				if(numOfGrams(sorted[j][0],sorted[j][1]) < numOfGrams(sorted[j+1][0],sorted[j+1][1])){

				temp2=sorted[j];
				sorted[j]=sorted[j+1];
				sorted[j+1]=temp2;

				}

			}

		}

/*String buffer is used to convert an array of objects to a single string with append methods below*/
	StringBuffer result = new StringBuffer();
		for (i = 0; i < sorted.length; i++) {
   		
		flag=0;
			/*The loop below is used to check for duplicates so that we don't print the same thing over and over again*/
			for(j=0; j<used.length; j++){

				if(sorted[i][0].equals(used[j][0]) && sorted[i][1].equals(used[j][1]))
				
				flag=1;		

			}

			if(flag != 1){
			result.append('(');
			result.append(sorted[i][0].toString());
			result.append(',');
			result.append(sorted[i][1].toString());
			result.append(')');
			result.append(" occured ");
			result.append(numOfGrams(sorted[i][0],sorted[i][1]));
			result.append(" times.");
			result.append('\n');
			used[i]=sorted[i];
			}
		}


	return result.toString();

	}
}
	



