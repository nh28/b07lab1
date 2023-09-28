import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Polynomial
{
	double[] coefficients;
	int[] exponents;
	public Polynomial()
	{
		coefficients = new double[1];
		coefficients[0] = 0;

		exponents = new int[1];
		exponents[0] = 0;
	}
	public Polynomial(double[] array1, int[] array2)
	{
		coefficients = new double[array1.length];
		exponents = new int[array2.length];
		for(int i = 0; i < array1.length; i++)
		{
			coefficients[i] = array1[i];
			exponents[i] = array2[i];
		}
	}

	public Polynomial(File file)
	{
		try{
		BufferedReader input = new BufferedReader(new FileReader(file));
		String poly = input.readLine();
		String[] splitPoly = poly.split("[+-]", 0);
		int counter = 0;
		
		coefficients = new double[splitPoly.length];
		exponents = new int[splitPoly.length];
		
		int i = 0;
		int j = 1;
		if (splitPoly[0] != "")
		{
			String[] first = splitPoly[0].split("x", 0);
			coefficients[i] = Double.parseDouble(first[0]);
			if (first.length > 1)
			{
				exponents[i] = Integer.parseInt(first[1]);
				counter+=2;
			}
			else
			{
				exponents[i] = 0;
			}
			counter++;
			i++;
				
		}
		while(j < splitPoly.length)
		{
			String[] elements = splitPoly[j].split("x", 0);
			if (poly.substring(counter, counter + 1).equals("-"))
			{
				coefficients[i] = Double.parseDouble("-" + elements[0]);
			}
			else
			{
				coefficients[i] = Double.parseDouble(elements[0]);
			}
			counter+=2;
			if(elements.length == 1)
			{
				exponents[i] = 0;
				counter--;
			}
			else
			{
				exponents[i] = Integer.parseInt(elements[1]);
				counter ++;
			}
			counter++;
			i++;
			j++;
			
		}
		}
		catch(FileNotFoundException ex){}
		catch(IOException e){}
		
		
	}

	public Polynomial add(Polynomial poly)
	{
		int maxExpo = 0;
		int minExpo = 0;
		
		for(int a = 0; a < exponents.length; a++)
		{
			if (maxExpo < exponents[a])
			{
				maxExpo = exponents[a];
			}
			if (minExpo > exponents[a])
			{
				minExpo = exponents[a];
			}
		}
		for(int b = 0; b < poly.exponents.length; b++)
		{
			if (maxExpo < poly.exponents[b])
			{
				maxExpo = poly.exponents[b];
			}
			if (minExpo > poly.exponents[b])
			{
				minExpo = poly.exponents[b];
			}
		}
		

		double[] coeffNew = new double[maxExpo - minExpo + 1];
		int[] exponentNew = new int[maxExpo - minExpo + 1];
		for(int i = 0; i < exponentNew.length; i++)
		{
			exponentNew[i] = i;
		}
		
		for(int j = 0; j < coefficients.length; j++)
		{
			coeffNew[exponents[j]] += coefficients[j];
		}

		for(int k = 0; k < poly.coefficients.length; k++)
		{
			coeffNew[poly.exponents[k]] += poly.coefficients[k];
		}

		Polynomial added = new Polynomial(coeffNew, exponentNew);
		return added;
		 
	}
	public double evaluate(double x)
	{
		double eval = 0;
		for(int i = 0; i < coefficients.length; i++)
		{
			eval += coefficients[i] * Math.pow(x, exponents[i]);
		}
		return eval; 
	}
	public boolean hasRoot(double x)
	{
		return evaluate(x) == 0;
	}

	public Polynomial multiply(Polynomial poly)
	{
		Polynomial newPoly = new Polynomial();
		
		for(int j = 0; j < coefficients.length; j++)
		{
			int size = poly.coefficients.length;
			double[] thisCoeff = new double[size];
			int[] thisExpo = new int[size];

			for(int k = 0; k < size; k++)
			{
				thisCoeff[k] = coefficients[j] * poly.coefficients[k];
				thisExpo[k] = poly.exponents[k] + exponents[j];
			}
			Polynomial temp = new Polynomial(thisCoeff, thisExpo);
			newPoly = newPoly.add(temp);
		}

		return newPoly;
	}

	public void saveToFile(String fileName)
	{
		try{
		String polynomial = "";
		for(int i = 0; i < coefficients.length; i++)
		{
			double coeff = coefficients[i];
			int expo = exponents[i];
			if (coeff > 0)
			{
				polynomial += "+" + String.valueOf(coefficients[i]);
			}
			else
			{
				polynomial += String.valueOf(coefficients[i]);
			}
			if (expo > 0)
			{
				polynomial += "x" + String.valueOf(expo);
			}
			
		} 
		PrintStream output = new PrintStream(fileName);
		if(polynomial.substring(0, 1).equals("+"))
			output.println(polynomial.substring(1));
		else		
			output.println(polynomial.substring(1));
		output.close();
		}
		catch(FileNotFoundException ex){}
	}
}