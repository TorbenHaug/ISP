% Autor:
% Datum: 03.06.2015

color(red).
color(green).
color(ivory).
color(blue).
color(yellow).

nationality(english).
nationality(spanish).
nationality(ukraine).
nationality(norway).
nationality(japan).

pet(dog).
pet(snake).
pet(zebra).
pet(fox).
pet(horse).

drink(tea).
drink(orangejuice).
drink(milk).
drink(water).
drink(coffee).

cigarette(oldgold).
cigarette(kools).
cigarette(chesterfield).
cigarette(luckystrike).
cigarette(parliament).


solve :- einsteintest(Houses), generate(Houses), !, writehouses(Houses,1).

%house(Used,Output)
house(UsedInput,UsedOutput,[Color,Nationality,Pet,Drink,Cigarette]) :-
                      color(Color), nationality(Nationality), pet(Pet), drink(Drink), cigarette(Cigarette),
                      not(member(Color,UsedInput)),
                      not(member(Nationality,UsedInput)),
                      not(member(Pet,UsedInput)),
                      not(member(Drink,UsedInput)),
                      not(member(Cigarette,UsedInput)),
                      append(UsedInput,[Color,Nationality,Pet,Drink,Cigarette],UsedOutput).

generate([House1,House2,House3,House4,House5]) :-
                      house([],House1Used,House1),
                      house(House1Used,House2Used,House2),
                      house(House2Used,House3Used,House3),
                      house(House3Used,House4Used,House4),
                      house(House4Used,_,House5).


einsteintest(Houses) :- Houses = [_,_,[_,_,_,milk,_],_,_],                 %9
                Houses = [[_,norway,_,_,_],_,_,_,_],         %10
                member([red,english,_,_,_], Houses),  %2
                member([_,spanish,dog,_,_], Houses),    %3
                member([green,_,_,coffee,_], Houses),       %4
                member([_,ukraine,_,tea,_], Houses),  %5
                member([_,_,snake,_,oldgold], Houses),    %7
                member([yellow,_,_,_,kools], Houses),   %8
                member([_,_,_,orangejuice,luckystrike], Houses), %13
                member([_,japan,_,_,parliament], Houses),  %14
                neighbour([ivory,_,_,_,_],[green,_,_,_,_], Houses),   %6
                simpleNeighbour([_,_,_,_,chesterfield],[_,_,fox,_,_], Houses), %11
                simpleNeighbour([_,_,_,_,kools],[_,_,horse,_,_], Houses), %12
                simpleNeighbour([blue,_,_,_,_],[_,norway,_,_,_], Houses). %15
                
simpleNeighbour(HouseLeft,HouseRight,Houses) :- neighbour(HouseLeft,HouseRight,Houses).
simpleNeighbour(HouseLeft,HouseRight,Houses) :- neighbour(HouseRight,HouseLeft,Houses).
neighbour(HouseLeft,HouseRight,[HouseLeft,HouseRight|_]).
neighbour(HouseLeft,HouseRight,[_|Rest]) :- neighbour(HouseLeft,HouseRight,Rest).

writehouses([First|Rest],Count) :- write('House'), write(Count), write(': '), writeOneHouse(First), write('\n'), Count1 is Count + 1, writehouses(Rest,Count1).
writeOneHouse([Color,Nat,Pet,Drink,Cigarette]) :- write(Color), write(' '),
                                                  write(Nat), write(' '),
                                                  write(Pet), write(' '),
                                                  write(Drink), write(' '),
                                                  write(Cigarette).





