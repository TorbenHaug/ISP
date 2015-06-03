% Autor:
% Datum: 03.06.2015

:-begin_tests(generate).
  % testing the structure
  test(generate) :- assertion(generate([[_,_,_,_,_],
                                        [_,_,_,_,_],
                                        [_,_,_,_,_],
                                        [_,_,_,_,_],
                                        [_,_,_,_,_]])).

:-end_tests(generate).


:-begin_tests(test).
   % positive test
   test(test) :- assertion(einsteintest([[yellow, norway, fox, water, kools],
                                         [blue, ukraine, horse, tea, chesterfield],
                                         [red, english, snake, milk, oldgold],
                                         [ivory, spanish, dog, orangejuice, luckystrike],
                                         [green, japan, zebra, coffee, parliament]])).

   % negative test
   test(test) :- assertion(not(einsteintest([[blue, norway, fox, water, kools],
                                            [yellow, ukraine, horse, tea, chesterfield],
                                            [red, english, snake, milk, oldgold],
                                            [ivory, spanish, dog, orangejuice, luckystrike],
                                            [green, japan, zebra, coffee, parliament]]))).

einsteintest:-end_tests(test).