
<h1> Overview </h1>
<p> this program is a java project to benchmark material machines and evaluate the different algorithms solving the linear problem `Ax=B` will allow you </p>
<h4>how to run?</h4>
  

		  cd project 
		  mvn clean compile
      mvn exec:java

  to increase Heap space you can adjust execution command like this 
  

    mvn exec:java -Dexec.args="-Xmx<size here>"

<h4>how it works?</h4>

This program will allow you to 
	1- TEST ALGORITHMS USING RANDOM DATA BY GENERATING MANY MATRIX TYPES (triangle , Diagonal dominant , Normal and Valid matrix ) 
	2-SOLVE A PROBLEM FROM A FILE OR USING THE SYSTEM INPUT

<h4>Algorithms to test</h4>
	Partial Gauss <br>
	LU Elimination <br>
	Choelsky<br>
	Jacobi <br>
	and Gauss-Seidel <br>

You can also perform some transformation on  matrix like : 
tringulisation , transposation ...
	
				
