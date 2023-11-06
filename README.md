**Semestrálna práca z predmetu Algoritmy a údajové štruktúry 2**

_Ide o jednoduchý informačný systém využívajúci stromovú dátovú štruktútru **k-d strom**._


**Popis systému:**

_Cieľom tejto semestrálnej práce bolo implementovať dátovú štruktúru k-d strom s funkcionalitami (vkladanie, mazanie, vyhľadávanie, intervalové vyhľadávanie), určiť výpočtovú zložitosť týchto operácií a vytvoriť jednoduchý informačný systém používajúci túto dátovú štruktúru._

_Informačný systém pracuje s objektami typu Nehnuteľnosť a Parcela._

_Nehnuteľnosti sa ukladajú do samostatného k-d stromu, a parcely sa taktiež ukladajú do samostatného k-d stromu. Konkrétne ide o 2-dimenzionálne stromy.
Jednotlivé vrcholy stromu obsahujú pole kľúčov, ktoré sú typu double a dáta, ktoré sú typu Nehnuteľnosť alebo Parcela. Každá nehnuteľnosť má v sebe atribút zoznam parciel (ArrayList), v ktorom si uchováva referencie na parcely, ktoré majú rovnaké GPS súradnice ako daná nehnuteľnosť. Každá parcela má v sebe atribút zoznam nehnuteľností (ArrayList), v ktorom si uchováva referencie na nehnuteľnosti, ktoré majú rovnaké GPS súradnice ako daná parcela._

---------------------------------
**Výpočtová zložitosť operácií**

**m** – _počet prvkov v zozname nehnuteľností_

**n** – _počet prvkov v zozname parciel_

**a** – _počet prvkov v zozname parciel, ktoré majú rovnakú GPS pozíciu ako nehnuteľnosť, v ktorej sa tento zoznam nachádza_

**b** – _počet prvkov v zozname nehnuteľností, ktoré majú rovnakú GPS pozíciu ako parcela, v ktorej sa tento zoznam nachádza_

_Vyhľadanie nehnuteľností:_ **priemerná zložitosť: O(log2m)        najhoršia zložitosť: O(m)**

_Vyhľadanie parciel:_ **priemerná zložitosť: O(log2n)        najhoršia zložitosť: O(n)**

_Vyhľadanie všetkých objektov:_ **priemerná zložitosť: O(log2m) + O(log2n)        najhoršia zložitosť: O(m) + O(n)**

_Pridanie nehnuteľnosti:_ **priemerná zložitosť: O(log2m)        najhoršia zložitosť: O(m)**

_Pridanie parcely:_ **priemerná zložitosť: O(log2n)        najhoršia zložitosť: O(n)**

_Editácia nehnuteľnosti:_ **priemerná zložitosť: O(log2m)        najhoršia zložitosť: O(m)**

_Editácia parcely:_ **priemerná zložitosť: O(log2n)        najhoršia zložitosť: O(n)**

_Vyradenie nehnuteľnosti:_ **priemerná zložitosť: O(log2m) + O(a)        najhoršia zložitosť: O(m) + O(a)**

_Vyradenie parcely:_ **priemerná zložitosť: O(log2n) + O(b)        najhoršia zložitosť: O(n) + O(b)**


**AKÉKOĽVEK KOPÍROVANIE ČASTÍ ALEBO CELÉHO ZDROJOVÉHO KÓDU JE ZAKÁZANÉ!** 
