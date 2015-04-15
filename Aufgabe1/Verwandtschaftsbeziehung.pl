% Autor:   Louisa Spahl / Torben Haug
% Datum: 15.04.2015




% vater(X,Y) -> X ist Vater von Y
vater(X,Y) :- mann(X), elternteil(X,Y).

% mutter(X,Y) -> X ist Mutter von Y
mutter(X,Y) :- frau(X), elternteil(X,Y).



% opa(X,Y) -> X ist Opa von Y
opa(X,Y) :- mann(X), vater(X,Z), elternteil(Z,Y).

% oma(X,Y) -> X ist Oma von Y.
oma(X,Y) :- frau(X), mutter(X,Z), elternteil(Z,Y).

% grosselternteil(X, Y) -> X ist Oma oder Opa von Y
grosselternteil(X, Y) :- oma(X,Y).
grosselternteil(X, Y) :- opa(X,Y).



% vorfahre(X,Y) -> X ist vorfahre von Y
vorfahre(X,Y) :-  elternteil(X,Y).
vorfahre(X,Y) :-  elternteil(X,Z), vorfahre(Z,Y).



% geschwister(X,Y) -> X und Y sind Geschwister
geschwister(X,Y) :- mutter(A,X), mutter(A,Y),
                    vater(B,X), vater(B,Y), X \= Y.
               
% bruder(X,Y) -> X ist Vollbruder von Y
bruder(X,Y) :- mann(X), geschwister(X,Y).
               
% schwester(X,Y) -> X ist Schwester von Y
schwester(X,Y) :- frau(X), geschwister(X,Y).




% onkel(X,Y) -> X ist Onkel von Y
onkel(X,Y) :- bruder(X,Z), elternteil(Z,Y).

% tante(X,Y) -> X ist Tante von Y
tante(X,Y) :- schwester(X,Z), elternteil(Z,Y).



% grossonkel(X,Y) -> X ist Großonkel von Y
grossonkel(X,Y) :-  bruder(X,Z), grosselternteil(Z,Y).

% grosstante(X,Y) -> X ist Großtante von Y
grosstante(X,Y) :- schwester(X,Z), grosselternteil(Z,Y).



% cousin(X,Y) -> X ist Cousin von Y.
cousin(X,Y) :- mann(X), onkel(Z,Y), elternteil(Z,X).
cousin(X,Y) :- mann(X), tante(Z,Y), elternteil(Z,X).

% cousine(X,Y) -> X ist Cousine von Y
cousine(X,Y) :- frau(X),  onkel(Z,Y), elternteil(Z,X).
cousine(X,Y) :- frau(X),  tante(Z,Y), elternteil(Z,X).



% schwager(X,Y) -> X ist Schwager von Y
schwager(X,Y) :- verheiratet(Y,Z), bruder(X,Z).

%schwaegerin(X,Y) -> X ist Schwägerin von Y
schwaegerin(X,Y) :- verheiratet(Y,Z), schwester(X,Z).