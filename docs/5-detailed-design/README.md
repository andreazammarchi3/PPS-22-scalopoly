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
Nel modulo space sono state implementate le logiche di cella della GameBoard definite dal trait Space poi specializzato da SpaceImpl, NotPurchasableSpace e da PurchasableSpace.

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
In figura viene mostrato il modulo View.

<p align="center">
  <img src="../images/View.png" alt="Diagramma del package View"/>
</p>

In questo modulo sono state implementate le viste rispettando il pattern MVC per permettere quindi un'interazione tra il giocatore e il controller che effettuerà poi le varie azioni.
Questo modulo è composto dalle 3 viste che è possibile incontrare nel gioco ed ereditano tutte dall'interfaccia javafx.fxml.Initializable:
* StartMenuView: rappresenta la vista di avvio in cui ci sono i tasti per avviare il gioco (playGameBtnClick) o per terminarlo (exitGameBtnClick).
* ConfigurationMenuView: rappresenta la vista la vista di configurazione per impostare i giocatori e sono presenti i comandi per avviare il gioco (playGameBtnClick), terminare il gioco (exitGameBtnClick), verificare e validare l'inserimento di un nuovo giocatore (checkAndAddPlayerToTableView) ed infine per rimuovere un giocatore dall'elenco di quelli già aggiunti (removePlayerFromTableView). 
* GameView: rappresenta la vista di gioco dove sono presenti i comandi per permettere ad un giocatore di abbandonare il gioco (quitBtnClick), lanciare i dadi (throwDiceBtnClick), di aggiornare la vista a seguito del lancio dei dadi (diceThrown), per permettere al giocatore di terminare il turno (endTurnBtnClick), di costruire una casa (buildBtnClick), aggiornare la vista mostrando il nome del giocatore di turno (updateTurnLabel).

## Controller
In figura viene mostrato il modulo Controller.

<p align="center">
  <img src="../images/Controller.png" alt="Diagramma del package Controller"/>
</p>

In questo modulo Controller sono state implementate le logiche di interazione tra Vista e Modello rispettando il pattern MVC attraverso le seguenti classi:
* StartMenuController: si occupa di comunicare con la vista iniziale StartMenuView esponendo i comandi playGame per passare alla schermata di configurazione ConfigurationMenuView, e exitGame per terminare il gioco.
* ConfigurationMenuController: si occupa di comunicare con la vista di configurazione ConfigurationMenuView attra verso i metodi addPlayer (per aggiungere un nuovo giocatore), removePlayer (per rimuoverne uno), availableToken (che restituisce la lista dei token ancora utilizzabili per i nuovi gioccatori), cadAddPlayer e canStartPlay che verificano se è possibile aggiungere ancora giocatori e se è possibile avviare la partita (questi utilizzati per pilotare la visibilità di alcuni campi nella vista) ed infine playGame (per avviare il gioco e spostarsi nella vista GameView) e exitGame (per terminare il gioco.
* GameController: si occupa di comunicare con la vista GameView di cui mantiene un riferimenti nella variabile view. È composto dai metodi currentPlayerQuit (che viene invocato quando un giocatore decide di abbandonare), throwDice (invocato quando un giocatore lancia i dadi), endTurn (chiamato quando un giocatore termina il proprio turno), checkPlayerActions (per verificare quali azioni può fare li giocatore) ed infine playerBuildHouse (che prende in ingresso il BuildableSpace dove il giocatore vuole costruire e restituisce un booleano che indica se l'operazione è stata completata con successo o meno). 

## Engine
In figura viene mostrato il modulo Engine che contiene le varie parti che gestiscono le logiche di gioco.

<p align="center">
  <img src="../images/Engine.png" alt="Diagramma del package Engine"/>
</p>



## Utils
In figura viene mostrato il modulo di strumenti utilizzati nell'applicativo.

<p align="center">
  <img src="../images/Utils.png" alt="Diagramma del package Utils"/>
</p>

## Deserialization
In figura viene mostrato il modulo di deserializzazione dei dati applicativi.

<p align="center">
  <img src="../images/Deserialization.png" alt="Diagramma del package Deserialization"/>
</p>

Il trait MyJsonReader definisce un generico JsonReader con il rispettivo comando read che prende in ingresso il JsonReader da analizzare.
Tale trai è implementato dagli altri 5 Object come si vede nella figura:
* SpaceJsonReader: si occupa di deserializzare oggetti Space
* StationSpaceJsonReader: si occupa di deserializzare oggetti StationSpace
* NotPurchasableSpaceJsonReader: si occupa di deserializzare oggetti NotPurchasableSpace
* CompanySpaceJsonReader: si occupa di deserializzare oggetti CompanySpace
* BuildableSpaceJsonReader: si occupa di deserializzare oggetti BuildableSpace

I vari Json sono nella cartella di progetto  app/src/resources/json.

[Indietro](../4-architectural-design/README.md) | [Torna alla Home](../README.md) | [Vai a Implementazione](../6-implementation/README.md)
