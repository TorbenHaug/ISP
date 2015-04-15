% Autor:
% Datum: 15.04.2015

:-begin_tests(vater).
  test(vater) :- vater(luca, emma).
  test(vater) :- vater(liam, julian).
  test(vater) :- not(vater(emma, luca)).
  test(vater) :- not(vater(alexander, david)).

:-end_tests(vater).
:-begin_tests(mutter).
  test(mutter) :- mutter(anna, emma).
  test(mutter) :- mutter(vanessa, julian).
  test(mutter) :- not(mutter(emma, luca)).
  test(mutter) :- not(mutter(alexander, david)).

:-end_tests(vater).

%Tests verden mit run_tests(<testname>). ausgef�hrt





% onkel(X,Y) -> X ist Onkel von Y

% tante(X,Y) -> X ist Tante von Y



% grossonkel(X,Y) -> X ist Gro�onkel von Y

% grosstante(X,Y) -> X ist Gro�tante von Y



% cousin(X,Y) -> X ist Cousin von Y.

% cousine(X,Y) -> X ist Cousine von Y



% schwager(X,Y) -> X ist Schwager von Y

%schwaegerin(X,Y) -> X ist Schw�gerin von Y

