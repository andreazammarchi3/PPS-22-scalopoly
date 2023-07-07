# Design di dettaglio

Come detto nel capitolo precedente le componenti del sistema sono state suddivise in vari moduli; oltre a quelli già citati (alcuni dei quali sono stati a loro volta divisi in sotto-moduli) sono presenti 2 ulteriori package, uno per la deserializzazione di file json contenenti informazioni delle varie caselle del tabellone di gioco, e uno contenente vari oggetti di utility. 

Nella figura sottostante viene mostrata la gerarchia con cui sono stati organizzati i vari moduli del progetto.

<p align="center">
  <img src="../images/Package_Diagram.png" alt="Moduli del progetto"/>
</p>

## Diagramma delle Classi
Nella figura sottostante è riportato il diagramma delle classi. I rettangoli blu indicano i vari moduli e sotto-moduli.

<p align="center">
  <img src="../images/Class_Diagram.png" alt="Diagramma delle classi"/>
</p>

Viene ora analizzato ogni singolo modulo.

## Model
In figura viene mostrato il modulo `Model` con i relativi sotto-moduli, `Space`, `NotPurchasable` e `Purchasable`.

<p align="center">
  <img src="../images/Model.png" alt="Diagramma del package Model"/>
</p>



### Space
Il trait Space rappresenta genericamente una cella della GameBoard poi specializzato da SpaceImpl, NotPurchasableSpace e da PurchasableSpace.

#### NotPurchasable
In questo sotto modulo sono state implementate le caselle non acquistabili nel gioco.
Queste celle sono poi differenziante in base al tipo NotPurcasableSpaceType nel seguente modo:
* BLANK: cella non definita
* CHANCE: Possibilità
* COMMUNITY_CHEST: Imprevisti
* INCOME_TAX: Tassa patrimoniale
* LUXURY_TAX: Tassa di lusso
  
La classe NotPurchasableSpace è uno Space che rappresenta una casella non acquistabile dai giocatori ed composta da:
* un parametro spaceValue che rappresenta il valore utilizzato nell'azione della casella
* un parametro spaceType di tipo NotPurcasableSpaceType
* un parametro action che rappresenta un metodo che dato un Player esegue un'azione su di esso

La classe NotPurchasableSpaceBuilder si occupa infine di instanziare questi tipi di classe in base al name e allo spaceType il quale va a pilotare anche la action definita.

#### Purchasable
In questo sotto modulo sono state implementate le caselle acquistabili nel gioco.
Il trait PurchableSpace è uno Space che rappresenta una casella acquistabile dai giocatori ed è compsta da:
* un parametro sellingPrice che indica il prezzo di vendita della casella
* un parametro rents che inidica gli affitti che i giocatorri pagheranno al passaggio della casella in base al numero di case presenti in essa, questo se la cella appartiene ad un altro giocatore
* un parametro spaceGroup che indica il gruppo di appartenenza della cella

Il trait PurchableSpace è implementato dalle classi:
* CompanySpace e StationSpace in cui è definita una specifica logica di calcolo del pedaggio (implementata nel calculateRent)
* BuildableSpace che rappresenta il restto delle caselle acquistabili dove è possibile costruire case (definite da numHouse) e alberghi pagando il buildingCost.



## View

<p align="center">
  <img src="../images/View.png" alt="Diagramma del package View"/>
</p>

## Controller

<p align="center">
  <img src="../images/Controller.png" alt="Diagramma del package Controller"/>
</p>

## Engine

<p align="center">
  <img src="../images/Engine.png" alt="Diagramma del package Engine"/>
</p>

## Utils

<p align="center">
  <img src="../images/Utils.png" alt="Diagramma del package Utils"/>
</p>

## Deserialization

<p align="center">
  <img src="../images/Deserialization.png" alt="Diagramma del package Deserialization"/>
</p>

[Indietro](../4-architectural-design/README.md) | [Torna alla Home](../README.md) | [Vai a Implementazione](../6-implementation/README.md)
