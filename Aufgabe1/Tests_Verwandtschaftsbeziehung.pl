% Autor:
% Datum: 15.04.2015

:-begin_tests(vater).
  test(vater) :- vater(luca, emma).
  test(vater) :- vater(liam, julian).
  test(vater) :- not(vater(emma, luca)).
  test(vater, [fail]) :- vater(alexander, david).

:-end_tests(vater).

%Tests verden mit run_tests(<testname>). ausgeführt

