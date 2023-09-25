public class Polynomial
{
	double[] coefficients;
	public Polynomial()
	{
		coefficients = new double[1];
		coefficients[0] = 0;
	}
	public Polynomial(double[] array)
	{
		coefficients = new double[array.length];
		for(int i = 0; i < array.length; i++)
		{
			coefficients[i] = array[i];
		}
	}
	public Polynomial add(Polynomial poly)
	{
		if (poly.coefficients.length > coefficients.length)
		{
			for(int i = 0; i < coefficients.length; i++)
			{
			poly.coefficients[i] += coefficients[i];
			}

			return poly;
		}

		for(int i = 0; i < poly.coefficients.length; i++)
		{

			coefficients[i] += poly.coefficients[i];
			
		}
		return this;
		 
	}
	public double evaluate(double x)
	{
		double eval = 0;
		for(int i = 0; i < coefficients.length; i++)
		{
			eval += coefficients[i] * Math.pow(x, i);
		}
		return eval; 
	}
	public boolean hasRoot(double x)
	{
		return evaluate(x) == 0;
	}
}