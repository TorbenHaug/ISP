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
   test(test1) :- assertion(einsteintest([[yellow, norway, fox, water, kools],
                                         [blue, ukraine, horse, tea, chesterfield],
                                         [red, english, snake, milk, oldgold],
                                         [ivory, spanish, dog, orangejuice, luckystrike],
                                         [green, japan, zebra, coffee, parliament]])).

   % negative test
   test(test2) :- assertion(not(einsteintest([[blue, norway, fox, water, kools],
                                            [yellow, ukraine, horse, tea, chesterfield],
                                            [red, english, snake, milk, oldgold],
                                            [ivory, spanish, dog, orangejuice, luckystrike],
                                            [green, japan, zebra, coffee, parliament]]))).
                                            

:-end_tests(test).


      
:-begin_tests(constrainTest).

  %test(constrainTest1) :- solve_const(OUT),  assertion(member([yellow, norway, fox, water, kools], OUT)).
  %test(constrainTest2) :- solve_const(OUT1), assertion(member([blue,ukraine,horse,tea,chesterfield], OUT1)).
  %test(constrainTest3) :- solve_const(OUT2), assertion(member([red,english,snake,milk,oldgold], OUT2)).
  %test(constrainTest4) :- solve_const(OUT3), assertion(member([green,japan,zebra,coffee,parliament], OUT3)).
  %test(constrainTest5) :- solve_const(OUT4), assertion(member([ivory,epanish,dog,orangejuice,luckystrike], OUT4)).
  test(constrainTest6) :- assertion(solve_const([[yellow,norway,fox,water,kools],
                                                 [blue,ukraine,horse,tea,chesterfield],
                                                 [red,english,snake,milk,oldgold],
                                                 [ivory,spanish,dog,orangejuice,luckystrike],
                                                 [green,japan,zebra,coffee,parliament]])).
                                         

:-end_tests(constrainTest).



:-begin_tests(compareBothFuncions).

 % compare generateAndTest function (generateAndTest.pl) with constrains funcrion (constrains.pl)
  test(const) :- assertion((solve_const(O), einsteintest(O), generate(O))).

:-end_tests(compareBothFuncions).