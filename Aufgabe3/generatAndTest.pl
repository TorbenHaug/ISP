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
drink(butter).
drink(coffee).

cigarette(oldgold).
cigarette(kools).
cigarette(chesterfield).
cigarette(luckystrike).
cigarette(parliament).


solve :- test(Houses), generate(Houses), writehouses(Houses).

%house(Used,Output)
house(UsedInput,UsedOutput,[Color,Nationality,Pet,Drink,Cigarette]) :-
                      Color = color(_),Nationality = nationality(_), Pet = pet(_),Drink = drink(_),Cigarette = cigarette(_),
                      Color,Nationality,Pet,Drink,Cigarette,
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


test(Houses) :- Houses = [_,_,[_,_,_,drink(milk),_],_,_],                 %9
                Houses = [[_,nationality(norway),_,_,_],_,_,_,_],         %10
                member([color(red),nationality(english),_,_,_], Houses),  %2
                member([_,nationality(spanish),pet(dog),_,_], Houses),    %3
                member([color(green),_,_,drink(coffee),_], Houses),       %4
                member([_,nationality(ukraine),_,drink(tea),_], Houses),  %5
                member([_,_,pet(snake),_,cigarette(oldgold)], Houses),    %7
                member([color(yellow),_,_,_,cigarette(kools)], Houses),   %8
                member([_,_,_,drink(orangejuice),cigarette(luckystrike)], Houses), %13
                member([_,nationality(japan),_,_,cigarette(parliament)], Houses),  %14
                neighbour([color(ivory),_,_,_,_],[color(green),_,_,_,_], Houses),   %6
                simpleNeighbour([_,_,_,_,cigarette(chesterfield)],[_,_,pet(fox),_,_], Houses), %11
                simpleNeighbour([_,_,_,_,cigarette(kools)],[_,_,pet(horse),_,_], Houses), %12
                simpleNeighbour([color(blue),_,_,_,_],[_,nationality(norway),_,_,_], Houses). %15
                
simpleNeighbour(HouseLeft,HouseRight,Houses) :- neighbour(HouseLeft,HouseRight,Houses).
simpleNeighbour(HouseLeft,HouseRight,Houses) :- neighbour(HouseRight,HouseLeft,Houses).
neighbour(HouseLeft,HouseRight,[HouseLeft,HouseRight|_]).
neighbour(HouseLeft,HouseRight,[_|Rest]) :- neighbour(HouseLeft,HouseRight,Rest).

writehouses(Houses) :- write(Houses).




