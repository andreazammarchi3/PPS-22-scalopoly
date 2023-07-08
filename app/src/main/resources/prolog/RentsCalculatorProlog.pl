rents(B, _, 0, [B]).
rents(B, R, N, [T|Rest]) :- N > 0, N1 is N - 1, rents(B, R, N1, Rest), T is R * N + B, !.
