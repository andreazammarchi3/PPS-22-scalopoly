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
