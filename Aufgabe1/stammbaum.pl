% Autor:    Louisa Spahl / Torben Haug
% Datum: 31.03.2015

% elternteil(X,Y) -> X ist Elternteil von Y
  elternteil(franz_schwarzer,thomas_wilhelm_schwarzer).
  elternteil(ingrid_maria_radkovsky,thomas_wilhelm_schwarzer).
  elternteil(franz_josef_schwarzer,franz_schwarzer).
  elternteil(irma_marie_pattermann,franz_schwarzer).
  elternteil(willhelm_johann_radkovsky,ingrid_maria_radkovsky).
  elternteil(inge_heitmann,ingrid_maria_radkovsky).
  elternteil(johann_schwarzer_jr,franz_josef_schwarzer).
  elternteil(marie_lindenthal,franz_josef_schwarzer).
  elternteil(dominik_pattermann_jr,irma_marie_pattermann).
  elternteil(franziska_josefa_bergel,irma_marie_pattermann).
  elternteil(rudolf_ignaz_radkovsky,willhelm_johann_radkovsky).
  elternteil(berta_drexler,willhelm_johann_radkovsky).
  elternteil(josef_heitmann,inge_heitmann).
  elternteil(maria_anna_schneider,inge_heitmann).
  elternteil(johann_schwarzer,johann_schwarzer_jr).
  elternteil(karolina_winter,johann_schwarzer_jr).
  elternteil(franz_lindenthal,marie_lindenthal).
  elternteil(antonia_weis,marie_lindenthal).
  elternteil(dominik_pattermann,dominik_pattermann_jr).
  elternteil(josefa_pavlas,dominik_pattermann_jr).
  elternteil(leopold_bergel,franziska_josefa_bergel).
  elternteil(marianna_mach,franziska_josefa_bergel).
  elternteil(antonia_radkovski,rudolf_ignaz_radkovsky).
  elternteil(johann_drexler,berta_drexler).
  elternteil(anna_aloisia_mark,berta_drexler).
  elternteil(josepf_wilhelm_heitmann,josef_heitmann).
  elternteil(anna_aloisia_mark,josef_heitmann).
  elternteil(franz_schneider,maria_anna_schneider).
  elternteil(marie_sophie_zirps,maria_anna_schneider).


% Mann und Frau muss expliziet angegeben werden,
% denn auch wenn teoretisch gilt (nicht Mann) == Frau,
% gilt in Prolog, dass alle nicht in der Liste aufgeführten
% Personen (z.B. bei den Männern) automatisch keine Männer sind.
% mann(X) -> X ist ein Mann
  mann(thomas_wilhelm_schwarzer).
  mann(franz_schwarzer).
  mann(franz_josef_schwarzer).
  mann(willhelm_johann_radkovsky).
  mann(johann_schwarzer_jr).
  mann(dominik_pattermann_jr).
  mann(rudolf_ignaz_radkovsky).
  mann(josef_heitmann).
  mann(johann_schwarzer).
  mann(franz_lindenthal).
  mann(dominik_pattermann).
  mann(leopold_bergel).
  mann(johann_drexler).
  mann(josepf_wilhelm_heitmann).
  mann(franz_schneider).
  
  
% Mann und Frau muss expliziet angegeben werden,
% denn auch wenn teoretisch gilt (nicht Mann) == Frau,
% gilt in Prolog, dass alle nicht in der Liste aufgeführten
% Personen (z.B. bei den Frauen) automatisch keine Frauen sind.
% frau(X) -> X ist eine Frau
  frau(ingrid_maria_radkovsky).
  frau(irma_marie_pattermann).
  frau(inge_heitmann).
  frau(marie_lindenthal).
  frau(franziska_josefa_bergel).
  frau(berta_drexler).
  frau(maria_anna_schneider).
  frau(karolina_winter).
  frau(antonia_weis).
  frau(josefa_pavlas).
  frau(marianna_mach).
  frau(antonia_radkovski).
  frau(berta_roskosch).
  frau(anna_aloisia_mark).
  frau(marie_sophie_zirps).
  