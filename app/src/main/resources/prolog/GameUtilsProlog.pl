% Return the coordinates of the N-th cell in a grid of X rows and Y columns.
% N = Nth cell, Y = number of columns, X = number of rows
% SolY = column, Solx = row
getNthCellInGrid(N,Y,X,SolY,SolX) :- R is (N mod Y), R = 0 -> SolY is Y - 1, SolX is N//Y - 1;
SolY is ((N mod Y)-1), SolX is N//Y.

%Return the coordinates of a grid cell given the position of a player
%P = Position, CIS = Cells in Side (default 10)
%SolY = column of N cell, SolX = row of N cell
getCoordinateFromPosition(P,CIS,SolY,SolX) :- P < CIS -> SolY is CIS - P, SolX is CIS;
P < CIS * 2 -> SolY is 0, SolX is (CIS * 2) - P;
P < CIS * 3 -> SolY is P - (CIS * 2), SolX is 0;
SolY is CIS, SolX is P - (CIS * 3).