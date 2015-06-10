% Autor:
% Datum: 03.06.2015
:- use_module(library(clpfd)).
solve_const :- solve_const(Out), !, writehouses(Out,1).
solve_const(Out) :-
              Color = [Red, Green, Ivory, Blue, Yellow],
              Nationality = [English,Spanish,Ukraine,Norway,Japan],
              Pet = [Dog, Snake, Zebra, Fox, Horse],
              Drink = [Tea, Orangejuice, Milk, Water, Coffee],
              Cigarette = [Oldgold, Kools, Chesterfield, Luckystrike, Parliament],
              
              Color ins 1..5,
              Nationality ins 1..5,
              Pet ins 1..5,
              Drink ins 1..5,
              Cigarette ins 1..5,

              all_different(Color),
              all_different(Nationality),
              all_different(Pet),
              all_different(Drink),
              all_different(Cigarette),

              Milk = 3,
              Norway = 1,
              
              Red = English,
              Spanish = Dog,
              Coffee = Green,
              Ukraine = Tea,
              Oldgold = Snake,
              Kools = Yellow,
              Luckystrike = Orangejuice,
              Japan = Parliament,

              (Green - Ivory) #= 1,   % Green + 1 #= Ivory,
              abs(Chesterfield - Fox) #= 1,
              abs(Kools - Horse) #= 1,
              abs(Norway - Blue) #= 1,

              label(Color),
              label(Nationality),
              label(Pet),
              label(Drink),
              label(Cigarette),
              
              sortByIndex(Color,[red, green, ivory, blue, yellow], ColorSorted),
              sortByIndex(Nationality,[english, spanish, ukraine, norway, japan], NationalitySorted),
              sortByIndex(Pet, [dog, snake, zebra, fox, horse], PetSorted),
              sortByIndex(Drink, [tea, orangejuice, milk, water, coffee], DrinkSorted),
              sortByIndex(Cigarette, [oldgold, kools, chesterfield, luckystrike, parliament], CigaretteSorted),
              
              % chnages the structure to the structure like in file generateAndTest
              structure(ColorSorted, NationalitySorted, PetSorted, DrinkSorted, CigaretteSorted, _, _, Out1),
              reverse(Out1, Out).
              
sortByIndex(Index, Values, Goal) :-
              pairs_keys_values(KeyVal, Index, Values),
              sort(KeyVal,KeyValSorted),
              pairs_values(KeyValSorted,Goal).

              
structure(ColorSorted, NationalitySorted, PetSorted, DrinkSorted, CigaretteSorted, _, Accu, Out) :-
   structure_(ColorSorted, NationalitySorted, PetSorted, DrinkSorted, CigaretteSorted, [], Accu, Out).

structure_([], [], [], [], [], Out, _, Out).

structure_([Color|ColorRest],
           [Nat|NationalityRest],
           [Pet|PetRest],
           [Drink|DrinkRest],
           [Cigarette|CigaretteRest], OldList, NewList, Out) :-
                List = [Color, Nat, Pet, Drink, Cigarette],
                NewList = [List|OldList],
                structure_(ColorRest,
                            NationalityRest,
                            PetRest,
                            DrinkRest,
                            CigaretteRest,
                            NewList,
                            _, Out).

%UNUSED
write_const([Color|ColorRest],
           [Nat|NationalityRest],
           [Pet|PetRest],
           [Drink|DrinkRest],
           [Cigarette|CigaretteRest],Count) :-
                 write('House'),
                 write(Count),
                 write(': '),
                 write(Color), write(' '),
                 write(Nat), write(' '),
                 write(Pet), write(' '),
                 write(Drink), write(' '),
                 write(Cigarette),
                 write('\n'),
                 Count1 is Count + 1,
                 writeConst(ColorRest,
                            NationalityRest,
                            PetRest,
                            DrinkRest,
                            CigaretteRest,
                            Count1).


