import java.io.File; 
import java.io.FileNotFoundException; 

public class Driver {
	public static void main(String [] args) {
		// test the empty parameter constuctor 
			Polynomial p = new Polynomial();
			System.out.println(p.evaluate(3));
		
		// test creating a polynomial with parameters (p1.length > p2.length)
			double [] c1 = {6,2, 5};
			int [] c11 = {0, 1, 3};
			Polynomial p1 = new Polynomial(c1, c11);
		
			// create another polynomial
			double [] c2 = {3, 4};
			int [] c22 = {1, 2};
			Polynomial p2 = new Polynomial(c2, c22);

			// test the add function
			Polynomial s1 = p1.add(p2);

			// test the evaluate function
			System.out.println("s1(0.1) = " + s1.evaluate(0.1));

			// test the hasRoot function
			if(s1.hasRoot(1))
				System.out.println("1 is a root of s");
			else
				System.out.println("1 is not a root of s");

		// test creating a polynomial with parameters (p2.length > p1.length)
			double [] a1 = {3, 4};
			int [] a11 = {1, 2};
			Polynomial m1 = new Polynomial(a1, a11);
		
			// create another polynomial
			double [] a2 = {-6,-2, -5};
			int [] a22 = {0, 1, 3};
			Polynomial m2 = new Polynomial(a2, a22);

			// test the add function
			Polynomial s2 = m1.add(m2);

			// test the evaluate function
			System.out.println("s2(0.1) = " + s2.evaluate(0.1));

		// test creating a polynomial with parameters (p2.length = p1.length)
			double [] b1 = {-3, -4};
			int [] b11 = {1, 2};
			Polynomial n1 = new Polynomial(b1, b11);
		
			// create another polynomial
			double [] b2 = {-6,-2};
			int [] b22 = {0, 1};
			Polynomial n2 = new Polynomial(b2, b22);

			// test the add function
			Polynomial s3 = n1.add(n2);

			// test the evaluate function
			System.out.println("s3(0.1) = " + s3.evaluate(0.1));

		// test the multiply function
			// positive * positive
			Polynomial t1 = p1.multiply(p2);
			System.out.println("t1(1) = " + t1.evaluate(1));

			// positive * negative
			Polynomial t2 = m1.multiply(m2);
			System.out.println("t2(1) = " + t2.evaluate(1));

			// negative * negative
			Polynomial t3 = n1.multiply(n2);
			System.out.println("t3(1) = " + t3.evaluate(1));			

		// test File constructor
			File myObj = new File("test.txt");
			Polynomial u = new Polynomial(myObj);
			System.out.println("u(0.1) = " + u.evaluate(0.1));

		// test saveToFile
			// starts with a positive
			s1.saveToFile("test1.txt");
			// starts with a negative
			s3.saveToFile("test2.txt");
		
	}
}