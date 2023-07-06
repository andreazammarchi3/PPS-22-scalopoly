getNthCellInGrid(N,Y,X,SolY,SolX) :- R is (N mod Y), R = 0 -> SolY is Y - 1, SolX is N//Y - 1; 
	SolY is ((N mod Y)-1), SolX is N//Y.