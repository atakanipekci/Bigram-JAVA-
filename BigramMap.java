import java.lang.*;
import java.util.*;
import java.io.*;

class BigramMap<T> implements Bigram<T> {

	private String filename;
	private Map<Integer, Object[]> pairs= new HashMap<Integer, Object[]>();
	private int dataType;
	/*Constructor that takes an integer to set the data type.
	  1 for int,2 for strings 3 for doubles*/
	BigramMap(int datatype){

	dataType=datatype;

	}

	/*Get and set functions for myBigrams*/	
	public int getDataT(){

	return dataType;

	}

	public int noOfPairs(){

	return pairs.size();

	}

	public Object[] getPair(int index){

	return pairs.get(index);
	}

	public void addPair(int i,Object x, Object y){

		Object []t = new Object[2];
		t[0]=x;
		t[1]=y;

		pairs.put(i,t);

	}
	/*Reads elements from file*/
	public void readFile(String name)throws Exception {

	int i =0,flag=0,flag2=0,count=0,count2=0;
	Object [] temp = new Object[2];
	Object temp1=0,temp2=0,temp3=0;
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
	/*Going back to the start of the file to actually read and store this time*/
	in.close();
	in = new Scanner(new File(name));
	

	while(in.hasNextLine()){

	Scanner line = new Scanner(in.nextLine());
	line.useLocale(Locale.US);/*Without this hasNext methods might not work correctly*/
		/*Reading data according to the dataType. This way we can check for exceptions*/		
		switch(getDataT()){

			case 2://for strings
				while(line.hasNext()){
				temp1=line.next();
		
					count2++;

					if(flag ==0)//if it is the first element read so far
					{
						flag=1;
						temp2=temp1;
					}		
					else
					{
						if(flag2 == 0)//if it is the second element read so far
						{
				
						addPair(i,temp2,temp1);
						temp3=temp1;
				
						flag2=1;
						i++;
						}
						else
						{
						addPair(i,temp3,temp1);
						temp2=temp3;
						temp3=temp1;
						i++;

						}
					}
					}

				break;


			case 1://for ints
				while(line.hasNextInt()){
				temp1=line.next();
		
					count2++;

					if(flag ==0)
					{
						flag=1;
						temp2=temp1;
					}		
					else
					{
						if(flag2 == 0)
						{
				
						addPair(i,temp2,temp1);
						temp3=temp1;
				
						flag2=1;
						i++;
						}
						else
						{
						addPair(i,temp3,temp1);
						temp2=temp3;
						temp3=temp1;
						i++;

						}
					}
					}

				break;


			case 3://for doubles
				while(line.hasNextDouble()){
				temp1=line.next();
		
					count2++;

					if(flag ==0)
					{
						flag=1;
						temp2=temp1;
					}		
					else
					{
						if(flag2 == 0)
						{
				
						addPair(i,temp2,temp1);
						temp3=temp1;
				
						flag2=1;
						i++;
						}
						else
						{
						addPair(i,temp3,temp1);
						temp2=temp3;
						temp3=temp1;
						i++;

						}
					}
					}

				break;

			

		}
		
		
	}
	
	  
/*If the amount we read to our Object type variable above is not the same as the one we
	read to our string variable then it means the file contains atleast 1 wrong
	type of data. Which is accepted as an exception*/


	if(count != count2)
		throw new myException("File contains wrong type of data");
	

	
	}
	/*Returns the total number of bigrams that are calculated*/
	public int numGrams (){
	
	return noOfPairs();

	}
	/*Returns the occurance of a bigram that is taken as an input*/
	public int numOfGrams(Object x, Object y){

	int i,count=0;

	Object []t = new Object[2];
	t[0]=x;
	t[1]=y;

	for(i=0; i<numGrams(); i++){

		if(Arrays.equals(getPair(i),t))
		count++;

	}

	return count;

	}

	/*prints all the bigrams and their occurences in decreasing occurency order.*/
	@Override
	public String toString(){

	int i,j,k,count,flag,temp;
	Object [] temp2= new Object[2];
	Object [][] sorted = new Object [noOfPairs()][2];
	Object [][] used = new Object [noOfPairs()][2];
	
/*copying our pairs to a 2D object array*/
		for(i=0; i<sorted.length; i++){
		
		sorted[i]=getPair(i);

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














