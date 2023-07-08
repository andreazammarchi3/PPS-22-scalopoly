# Implementazione

## Schermate di gioco
### Start Menu
Modellata dalla classe `StartMenuView`, rappresenta la pagina iniziale ed è controllata dal `StartMenuController`.
<p align="center">   <img src="../images/StartMenu.png" alt="Start Menu"/> </p>

### Configuration Menu
Modellata dalla classe `ConfigurationMenuView`, rappresenta la pagina di configurazione ed aggiunta dei giocatori ed è controllata dal `ConfigurationMenuController`.
<p align="center">   <img src="../images/ConfigurationMenu.png" alt="Configuration Menu"/> </p>

### Game View
Modellata dalla classe `GameView`, rappresenta il tabellone principale del gioco  ed è controllata dal `GameController`.
<p align="center">   <img src="../images/GameView.png" alt="Game View"/> </p>

### Victory View
È un semplice alert che viene mostrato quando un giocatore vince la partita. Altri alert simili sono stati implementati per notificare l'utente di diversi eventi durante la partita.
<p align="center">   <img src="../images/VictoryView.png" alt="Victory View"/> </p>

# Suddivisione del lavoro
Ogni componente fornirà una descrizione delle parti di cui è responsabile.

## Pair Programming (Negri, Zammarchi)  
Le classi a cui i due componenti hanno interamente lavorato insieme sono le seguenti, raggruppate per package:
- *Controller*:
  - `GameController`
- *Engine*`:
  - *Prolog*:
    - `PrologEngine`
  - `GameEngine`
  - `PlayerActionEngine`
- *Model*:
  - *Space*:
    - *Purchasable*:
        - `BuildableSpace`
        - `CompanySpace`
        - `PurchasableSpace`
        - `StationSpace`
    - `Space`
    - `SpaceImpl`
  - `Player`
  - `Token`
- *Utils*:
  - `FxmlUtils`
  - `GameUtils`
- *View*:
  - `GameView`

## Michele Laddaga
Le classi dove ho singolarmente lavorato maggiormente (interamente o in parte) sono le seguenti, raggruppate per package:
- *Controller*:
  - `ConfigurationMenuController`
  - `StartMenuController`
- *Engine*`:
  - *Prolog*:
    - `PrologEngine`
- *Model*:
  - *Space*:
    - *NotPurchasable*:
      - `NotPurchasableSpace`
      - `NotPurchasableSpaceBuilder`
      - `NotPurchasableSpaceType`
- *Utils*:
  - `GameUtils`
- *View*:
  - `ConfigurationMenuView`
  - `StartMenuView`
Tra i vari sviluppi effettuati riporto qualche punto di interesse che ha portato ad alcune riflessioni sull'utilizzo delle tecnologie scelte e sull'analisi iniziale effettuata.
Prima di tutto vorrei riportare come lo sviluppo Test Driven inizialmente abbia rallentato, almeno per quanto mi riguarda, il processo implementativo. Questo però è diventato essenziale nelle parti successive, soprattutto di refactor, per evitare di introdurre bug o problemi vari (come nell'esempio che riporto in seguito).
Come primo punto vorrei riportare come, a posteriori, la scelta della tecnologia JavaFX in un progetto in SCALA, mi abbia messo di fronte a molte lacunee nella documentazione online a riguardo. Lo stesso discorso l'ho riscontrato analizzando le soluzione messe a disposizione da SCALAFX il quale, per lo meno per gli esempi verificati, risulta anch'esso acerbo e non paragonabile ad alternative similari che si possono trovare ad esempio in altri linguaggi.
Come seconda analisi riporto un evento verificatosi durante il processo di sviluppo quando il refactoring di una parte del modello, nello specifico delle celle non acquistabili, è stato necessario una volta analizzato il codice che era stato prodotto: questo è successo poichè una mia analisi iniziale non precisa ha portato alla creazione di vari classi che ereditavano dal trait NotPurchasableSpace con la conseguenza di avere molto codice ripetuto.
Applicando quindi il principio DRY è stato unito tutto il codice ridondante andando a trasformare quello che inizialmente era un trait in una classe parametrica che ora va a prendere in ingresso l'azione che definirà poi a runtime il comportamento della stessa cella al passaggio degli utenti. Dopo di che è stato necessario creare un Builder che andasse a inizializzare e specializzare le varie celle in base all'azione di interesse.
Come anticipato all'inizio, in questa fase lo sviluppo Test Driven si è rivelato molto utile permettendo di procedere senza introdurre problematiche di nessun genere.
Come ultima annotazione riporto l'implementazione di un esempio in Prolog per il calcolo di un valore utilizzato per definire il comportamento delle celle di probabilità: tali celle, per semplicità nel progetto proposto, portano l'utente a pagare o a riscuotere una determinata cifra in base al valore restituito dalla regola definita in prolog. Tale implementazione di esempio potrà essere facilmente espansa andando a modificare le regole del file app/src/main/resources/prolog/ChanceCalculatorProlog.pl.

## Andrea Negri
Le classi dove ho singolarmente lavorato maggiormente, oltre a quelle già elencate nella parte di Pair Programming, sono le seguenti, raggruppate per package:
- *Engine*:
  - `EndgameLogicEngine`
  - `EngineUtils`
- *Utils*:
  - `AlertUtils`


## Andrea Zammarchi
Le classi dove ho singolarmente lavorato maggiormente, oltre a quelle già elencate nella parte di Pair Programming, sono le seguenti, raggruppate per package:

- *Deserialization*:
  - `BuildableSpaceJsonReader`
  - `CompanySpaceJsonReader`
  - `MyJsonReader`
  - `NotPurchasableSpaceJsonReader`
  - `SpacesJsonReader`
  - `StationSpaceJsonReader`
- *Engine*:
  - `BotEngine`
  - `Game`
- *Model*:
  - `DiceManager`
  - `GameBoard`
  - `SpaceGroup`
  - `SpaceStatus`


[Indietro](../5-detailed-design/README.md) | [Torna alla Home](../README.md) | [Vai a Conclusioni](../7-conclusion/README.md)
