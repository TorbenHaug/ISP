% Autor:    Louisa Spahl / Torben Haug
% Datum: 31.03.2015

% elternteil(X,Y) -> X ist Elternteil von Y
  elternteil(alexander, levin).
  elternteil(alexander, vanessa).
  elternteil(emilia, levin).
  elternteil(emilia, vanessa).
  elternteil(levin, jonas).
  elternteil(levin, lena).
  elternteil(levin, david).
  elternteil(lisa, jonas).
  elternteil(lisa, lena).
  elternteil(lisa, david).
  elternteil(vanessa, julian).
  elternteil(liam, julian).
  elternteil(julian, noah).
  elternteil(julian, marcel).
  elternteil(anna, samira).
  elternteil(anna, emma).
  elternteil(luca, samira).
  elternteil(luca, emma).
  elternteil(samira, noah).
  elternteil(samira, marcel).
  elternteil(emma, kevin).
  elternteil(emma, lina).
  elternteil(emma, mila).
  elternteil(emma, lea).
  elternteil(david, kevin).
  elternteil(david, lina).
  elternteil(david, mila).
  elternteil(david, lea).


% verheiratet(X,Y) -> mann(X) ist mit frau(Y) verheiratet
  verheitatet(alexander, emilia).
  verheitatet(levin, lisa).
  verheitatet(liam, vanessa).
  verheitatet(david, emma).
  verheitatet(julian, samira).
  verheitatet(luca, anna).


% Mann und Frau muss expliziet angegeben werden,
% denn auch wenn theoretisch gilt (nicht Mann) == Frau,
% gilt in Prolog, dass alle nicht in der Liste aufgeführten
% Personen (z.B. bei den Männern) automatisch keine Männer sind.
% mann(X) -> X ist ein Mann
  mann(maksim).
  mann(michael).
  mann(elias).
  mann(kevin).
  mann(luca).
  mann(alexander).
  mann(marcel).
  mann(liam).
  mann(noah).
  mann(jonas).
  mann(david).
  mann(levin).
  mann(daniel).
  mann(linus).
  mann(julian).
  mann(marvin).
  mann(milan).
  mann(thomas).
  mann(samuel).
  mann(levi).
  mann(jan).
  

  
  
% Mann und Frau muss expliziet angegeben werden,
% denn auch wenn teoretisch gilt (nicht Mann) == Frau,
% gilt in Prolog, dass alle nicht in der Liste aufgeführten
% Personen (z.B. bei den Frauen) automatisch keine Frauen sind.
% frau(X) -> X ist eine Frau
  frau(laura).
  frau(emma).
  frau(julia).
  frau(samira).
  frau(anna).
  frau(lena).
  frau(lisa).
  frau(lea).
  frau(vanessa).
  frau(mila).
  frau(lina).
  frau(sarah).
  frau(emilia).
  frau(hannah).
  frau(alina).
  frau(melanie).
  frau(lara).
  frau(sandra).
  frau(elena).
  frau(sophie).
  frau(nina).
  
  
