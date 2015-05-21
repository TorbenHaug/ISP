% Autor:
% Datum: 21.05.2015

% Tests

:-begin_tests(det).

  %weiblich
  test(detW) :- assertion(det(det(_),w,[die],[])).
  test(detW) :- assertion(not(det(det(_),w,[der],[]))).
  
  %männlich
  test(detM) :- assertion(det(det(_),m,[der],[])).
  test(detM) :- assertion(not(det(det(_),m,[die],[]))).

:-end_tests(det).


:-begin_tests(pn).

  %weiblich
  test(pnW) :- assertion((pn(lisa,pn(_),w,[lisa],[]))).
  test(pnW) :- assertion(not(pn(_,pn(_),w,[kevin],[]))).

  %männlich
  test(pntM) :- assertion((pn(noah,pn(_),m,[noah],[]))).
  test(pnM) :- assertion(not(pn(_,pn(_),m,[lea],[]))).
  
:-end_tests(pn).


:-begin_tests(v).

  test(v) :- assertion((v(v(V),_,[ist],[]), V = sein)).

:-end_tests(v).


:-begin_tests(pp).

  test(pp) :- assertion(pp(lisa, pp(prae(von), pn(lisa)), w, [von,lisa],[])).
  test(pp) :- assertion(pp(lisa, pp(prae(von), pn(lisa)), m, [von,lisa],[])).
:-end_tests(pp).


:-begin_tests(np).

  test(np) :- assertion(np(onkel, np(n(onkel)), m, [onkel],[])).
  test(np) :- assertion(np(onkel, np(det(der), n(onkel)), m, [der, onkel],[])).
  
  test(np) :- assertion(not(np(onkel, np(n(onkel)), w, [onkel],[]))).
  test(np) :- assertion(not(np(onkel, np(det(_), n(onkel)), m, [die, onkel],[]))).

:-end_tests(np).

:-begin_tests(vp).

  test(vp) :- assertion(vp(onkel, vp(v(sein), np(n(onkel)) ), m, [ist, onkel],[])).
  test(vp) :- assertion(vp(onkel, vp(v(sein), np(det(der), n(onkel))), m, [ist, der, onkel],[])).

  test(vp) :- assertion(not(vp(onkel, vp(v(sein), np(n(onkel))), w, [ist, onkel],[]))).
  test(vp) :- assertion(not(vp(onkel, vp(v(_),np(det(_), n(onkel))), m, [ist, die, onkel],[]))).

:-end_tests(vp).


:-begin_tests(s).

  test(s) :- assertion(s(onkel(_, kevin), s(ip(wer), vp(v(sein), np(n(onkel))), pp(prae(von), pn(kevin))), [wer, ist, onkel, von, kevin],[])).
  test(s) :- assertion(s(onkel(_, kevin), s(ip(wer), vp(v(sein), np(det(der),n(onkel))), pp(prae(von), pn(kevin))), [wer, ist, der, onkel, von, kevin],[])).
  test(s) :- assertion(s(onkel(jonas, kevin), s(v(sein), pn(jonas), np(det(der),n(onkel)), pp(prae(von), pn(kevin))), [ist, jonas, der, onkel, von, kevin],[])).
  
  test(s) :- assertion(not(s(onkel(_, kevin), s(v(sein), pn(jonas), np(det(_),n(onkel)), pp(prae(von), pn(kevin))), [ist, jonas, die, onkel, von, kevin],[]))).

:-end_tests(s).


:-begin_tests(a).

  test(a) :- assertion(a(onkel(_, kevin), a(pn(jonas), vp(v(sein), np(n(onkel))), pp(prae(von), pn(kevin))), [jonas, ist, onkel, von, kevin],[])).
  test(a) :- assertion(a(onkel(_, kevin), a(pn(jonas), vp(v(sein), np(det(der),n(onkel))), pp(prae(von), pn(kevin))), [jonas, ist, der, onkel, von, kevin],[])).
  test(a) :- assertion(a(onkel(jonas, kevin), a(pn(jonas), vp(v(sein), np(det(ein),n(onkel))), pp(prae(von), pn(kevin))), [ jonas, ist, ein, onkel, von, kevin],[])).

  test(a) :- assertion(not(a(onkel(_, kevin), a(pn(jonas), vp(v(sein), np(det(_),n(onkel))), pp(prae(von), pn(kevin))), [jonas, ist, die, onkel, von, kevin],[]))).

:-end_tests(a).
