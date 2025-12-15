# Registraties

## Context

We voorzien dat een arts kan inloggen in eLys en daar een overzicht ziet van zijn registraties. Hiervoor hebben we
nieuwe endpoints nodig. Omdat die endpoints momenteel enkel(/vooral) via de GUI gebruikt worden, levert het
OSLO-compliant maken van dit endpoint weinig tot geen meerwaarde.

## Beslissing

De endpoints `/vaststelling/v1/registraties` en `/vaststelling/v1/vaststelling/{dossiernummer}` zijn niet
olso-compliant.