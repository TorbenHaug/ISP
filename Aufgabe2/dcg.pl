% Autor:  Louisa Spahl, Torben Haug
% Datum: 08.05.2015

% s := Satz
  s(SemS, s(IP, VP, PP))    --> ip(IP,AGR), vp(SemVP, VP,AGR), pp(SemPP,PP,AGR),
                                {SemS =.. [SemVP, _, SemPP]}.
  s(SemS, s(V, PN, NP, PP)) --> v(V,AGR), pn(SemPN,PN,AGR), np(SemNP,NP,AGR), pp(SemPP, PP,AGR),
                                {SemS =.. [SemNP,SemPN,SemPP]}.
  
% vp := Verbphrase
  vp(SemNP, vp(V, NP),AGR)       --> v(V,AGR), np(SemNP, NP,AGR).
  
% np := Nominalphrase
  np(SemN, np(DET, N),AGR)      --> det(DET,AGR), n(SemN, N,AGR).
  np(SemN, np(N),AGR)           --> n(SemN, N,AGR).

  
% pp := Präpositionalphrase
  pp(SemPN,pp(PRAE, PN),AGR)    --> prae(PRAE,AGR), pn(SemPN, PN,_).

% v := verb
  v(v(V),AGR)             --> [X], {lex(X,V,v,AGR)}.

% n := Nomen
  n(N,n(N),AGR)             --> [X], {lex(X,N,n,AGR)}.

% prae := Präposition
  prae(prae(Prae),AGR)    --> [X], {lex(X,Prae,prae,AGR)}.
  
% pn := Eigenname
  pn(PN,pn(PN),AGR)          --> [X], {lex(X,PN,pn,AGR)}.
  
% ip : Iterogativpronomen
  ip(ip(IP),AGR)          --> [X], {lex(X,IP,ip,AGR)}.

% det := Artikel
  det(det(DET),AGR)       --> [X], {lex(X,DET,det,AGR)}.
  
  
% lex IP
  lex(wer,wer,ip, _).

% lex DET
  lex(der, der, det, m).
  lex(die, die, det, w).

% lex PRAE
  lex(von, von, prae, _).

% lex N
  lex(vater, vater, n, m).
  lex(mutter, mutter, n, w).
  lex(opa, opa, n, m).
  lex(oma, oma, n, w).
  lex(schwager, schwager, n, m).
  lex(schwaegerin, schwaegerin, n, w).
  lex(bruder, bruder, n, m).
  lex(schwester, schwester, n, w).
  lex(onkel, onkel, n, m).
  lex(tante, tante, n, w).
  lex(grossonkel, grossonkel, n, m).
  lex(grosstante, grosstante, n, w).
  lex(cousin, cousin, n, m).
  lex(cousine, cousine, n, w).
  lex(schwager, schwager, n, m).
  lex(schwaegerin, schwaegerin, n, w).
  lex(schwippschwager, schwippschwager, n, m).
  lex(schwippschwagerin, schwippschwagerin, n, w).

% lex V
  lex(ist,sein,v, _).

% lex PN
  lex(PN, PN, pn, m) :- mann(PN).
  lex(PN, PN, pn, w) :- frau(PN).

