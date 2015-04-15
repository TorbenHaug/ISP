% Autor:
% Datum: 15.04.2015
% Tests verden mit run_tests(<testname>). ausgeführt

:-begin_tests(vater).
  test(vater) :- vater(luca, emma).
  test(vater) :- vater(liam, julian).
  test(vater) :- not(vater(emma, luca)).
  test(vater) :- not(vater(alexander, david)).

:-end_tests(vater).

:-begin_tests(mutter).
  test(mutter) :- assertion(mutter(anna, emma)).
  test(mutter) :- assertion(mutter(vanessa, julian)).
  test(mutter) :- assertion(not(mutter(emma, luca))).
  test(mutter) :- assertion(not(mutter(alexander, levin))).
:-end_tests(mutter).

:-begin_tests(opa).
  test(opa) :- assertion(opa(luca, marcel)).
  test(opa) :- assertion(opa(liam, noah)).
  test(opa) :- assertion(not(opa(noah, liam))).
  test(opa) :- assertion(not(opa(vanessa, noah))).
:-end_tests(opa).

:-begin_tests(oma).
  test(oma) :- assertion(oma(anna, marcel)).
  test(oma) :- assertion(oma(vanessa, noah)).
  test(oma) :- assertion(not(oma(noah, vannessa))).
  test(oma) :- assertion(not(oma(liam, noah))).
:-end_tests(oma).

:-begin_tests(grosselternteil).
  test(grosselternteil) :- assertion(grosselternteil(anna, marcel)).
  test(grosselternteil) :- assertion(grosselternteil(luca, marcel)).
  test(grosselternteil) :- assertion(not(grosselternteil(samira, marcel))).
  test(grosselternteil) :- assertion(not(grosselternteil(emilia, marcel))).
:-end_tests(grosselternteil).

:-begin_tests(vorfahre).
  test(vorfahre) :- assertion(vorfahre(alexander, kevin)).
  test(vorfahre) :- assertion(vorfahre(david, kevin)).
  test(vorfahre) :- assertion(not(vorfahre(alexander, samira))).
  test(vorfahre) :- assertion(not(vorfahre(david, david))).
  test(vorfahre) :- assertion(not(vorfahre(david, lisa))).
:-end_tests(vorfahre).

:-begin_tests(geschwister).
  test(geschwister) :- assertion(geschwister(lina, kevin)).
  test(geschwister) :- assertion(geschwister(kevin, lina)).
  test(geschwister) :- assertion(not(geschwister(david, kevin))).
  test(geschwister) :- assertion(not(geschwister(kevin, david))).
  test(geschwister) :- assertion(not(geschwister(lina, lina))).
  test(geschwister) :- assertion(not(geschwister(kevin, kevin))).
:-end_tests(geschwister).

:-begin_tests(bruder).
  test(bruder) :- assertion(not(bruder(lina, kevin))).
  test(bruder) :- assertion(bruder(kevin, lina)).
  test(bruder) :- assertion(not(bruder(david, kevin))).
:-end_tests(bruder).

:-begin_tests(schwester).
  test(schwester) :- assertion(schwester(lina, kevin)).
  test(schwester) :- assertion(not(schwester(kevin, lina))).
  test(schwester) :- assertion(not(schwester(lena, lisa))).
:-end_tests(schwester).

:-begin_tests(onkel).
  test(onkel) :- assertion(onkel(levin, julian)).
  test(onkel) :- assertion(onkel(jonas, kevin)).
  test(onkel) :- assertion(onkel(jonas, lina)).
  test(onkel) :- assertion(not(onkel(liam, david))).
  test(onkel) :- assertion(not(onkel(lena, kevin))).
:-end_tests(onkel).

:-begin_tests(tante).
  test(tante) :- assertion(tante(samira, lea)).
  test(tante) :- assertion(tante(samira, kevin)).
  test(tante) :- assertion(not(tante(lisa, julian))).
  test(tante) :- assertion(not(tante(levin, julian))).
:-end_tests(tante).

:-begin_tests(grossonkel).
  test(grossonkel) :- assertion(grossonkel(levin, noah)).
  test(grossonkel) :- assertion(not(grossonkel(liam, kevin))).
  test(grossonkel) :- assertion(not(grossonkel(vanessa, lina))).
:-end_tests(grossonkel).

:-begin_tests(grosstante).
  test(grosstante) :- assertion(grosstante(vanessa, lina)).
  test(grosstante) :- assertion(not(grosstante(lisa, noah))).
  test(grosstante) :- assertion(not(grosstante(anna, mila))).
:-end_tests(grosstante).

:-begin_tests(cousin).
  test(cousin) :- assertion(cousin(marcel, kevin)).
  test(cousin) :- assertion(cousin(kevin, marcel)).
  test(cousin) :- assertion(cousin(julian, lena)).
  test(cousin) :- assertion(not(cousin(lisa, noah))).
  test(cousin) :- assertion(not(cousin(lena, julian))).
:-end_tests(cousin).

:-begin_tests(cousine).
  test(cousine) :- assertion(cousine(lena, julian)).
  test(cousine) :- assertion(not(cousine(julian, lena))).
:-end_tests(cousine).

:-begin_tests(schwager).
  test(schwager) :- assertion(schwager(liam, levin)).
  test(schwager) :- assertion(schwager(levin, liam)).
  test(schwager) :- assertion(not(schwager(lisa, vanessa))).
  test(schwager) :- assertion(not(schwager(vanessa, lisa))).
  test(schwager) :- assertion(not(schwager(emma, julian))).
  test(schwager) :- assertion(schwager(julian, emma)).
:-end_tests(schwager).

:-begin_tests(schwaegerin).
  test(schwaegerin) :- assertion(not(schwaegerin(liam, levin))).
  test(schwaegerin) :- assertion(not(schwaegerin(levin, liam))).
  test(schwaegerin) :- assertion(schwaegerin(lisa, vanessa)).
  test(schwaegerin) :- assertion(schwaegerin(vanessa, lisa)).
  test(schwaegerin) :- assertion(schwaegerin(emma, julian)).
  test(schwaegerin) :- assertion(not(schwaegerin(julian, emma))).
:-end_tests(schwaegerin).