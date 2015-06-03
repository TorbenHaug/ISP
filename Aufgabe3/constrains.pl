% Autor:
% Datum: 03.06.2015
:- use_module(library(clpfd)).
:- use_module(library(apply)).
solve_const :-
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
              
              Green + 1 #= Ivory,
              abs(Chesterfield - Fox) #= 1,
              abs(Kools - Horse) #= 1,
              abs(Norway - Blue) #= 1,

              label(Color),
              label(Nationality),
              label(Pet),
              label(Drink),
              label(Cigarette),
              
              sortByIndex(Color,[red, green, ivory, blue, yellow], ColorSorted),
              sortByIndex(Nationality,[english,epanish,ukraine,norway,japan],NationalitySorted),
              sortByIndex(Pet,[dog, snake, zebra, fox, horse],PetSorted),
              sortByIndex(Drink,[tea, orangejuice, milk, water, coffee],DrinkSorted),
              sortByIndex(Cigarette,[oldgold, kools, chesterfield, luckystrike, parliament],CigaretteSorted),
              write_const(ColorSorted, NationalitySorted, PetSorted, DrinkSorted, CigaretteSorted,1).
              
sortByIndex(Index, Values, Goal) :-
              pairs_keys_values(KeyVal, Index, Values),
              sort(KeyVal,KeyValSorted),
              pairs_values(KeyValSorted,Goal).

              
structure([Color|ColorRest],
           [Nat|NationalityRest],
           [Pet|PetRest],
           [Drink|DrinkRest],
           [Cigarette|CigaretteRest], Out) :-
                append([Color, Nat, Pet, Drink, Cigarette], [Out], Out1),
                structure(ColorRest,
                            NationalityRest,
                            PetRest,
                            DrinkRest,
                            CigaretteRest,
                            Out1
                 ).
                            
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
                            Count1
                 ).


