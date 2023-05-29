# Sprint backlog

<table>
    <thead>
        <tr>
            <th>Item</th>
            <th>Task</th>
            <th>Volunteer</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td rowspan=2>Organizzazione del progetto</td>
            <td>Discussione del processo di sviluppo</td>
            <td rowspan=2 style="text-align: center;">Team</td>
        </tr>
        <tr>
            <td>Identificazione dei requisiti dell'attuale Sprint</td>
        </tr>
        <tr>
            <td rowspan=6>Configurazione del progetto</td>
            <td>Creazione del repository</td>
            <td style="text-align: center;">Zammarchi</td>
        </tr>
        <tr>
            <td>Configurazione di Trello</td>
            <td style="text-align: center;">Negri</td>
        </tr>
        <tr>
            <td>Configurazione della build automation e della struttura del progetto</td>
            <td rowspan=3 style="text-align: center;">Zammarchi</td>
        </tr>
        <tr>
            <td>Configurazione della licenza</td>
        </tr>
        <tr>
            <td>Creazione workflow per la CI</td>
        </tr>
        <tr>
            <td>Configurazione e stesura iniziale della doc</td>
            <td style="text-align: center;">Negri, Zammarchi</td>
        </tr>
        <tr>
            <td rowspan=10>Gioco dell’oca CLI</td>
            <td>Progettazione dell'architettura e dei componenti base</td>
            <td style="text-align: center;">Team</td>
        </tr>
        <tr>
            <td>Sviluppo modello giocatore e pedine</td>
            <td rowspan=3 style="text-align: center;">Negri</td>
        </tr>
        <tr>
            <td>Sviluppo modello partita</td>
        </tr>
        <tr>
            <td>Sviluppo modello tabellone e spazi</td>
        </tr>
        <tr>
            <td>Sviluppo menù di avvio applicazione</td>
            <td rowspan=4 style="text-align: center;">Laddaga</td>
        </tr>
        <tr>
            <td>Sviluppo menù configurazione partita</td>
        </tr>
        <tr>
            <td>Sviluppo menù inizio turno</td>
        </tr>
        <tr>
            <td>Sviluppo menù fine turno</td>
        </tr>
        <tr>
            <td>Sviluppo gestione turni</td>
            <td rowspan=2 style="text-align: center;">Zammarchi</td>
        </tr>
        <tr>
            <td>Sviluppo lancio dadi e spostamento</td>
        </tr>
    </tbody>
</table>


## Sprint goal
I 2 obiettivi di questa Sprint sono:
- inizializzare il progetto (organizzazione e configurazione)
- implementare una prima versione semplificata di Monopoly simile al gioco dell'Oca:
  - senza GUI, solo CLI
  - le uniche azioni possibili per un giocatore sono tirare i dadi, avanzare nel tabellone e finire il turno

## Deadline
27/05/2023

# Sprint review
Lo Sprint goal è stato raggiunto con un paio di giorni di ritardo.

# Sprint retrospective
Essendo questo il primo Sprint, il Team ha impiegato un certo periodo di tempo per ottimizzare il proprio rendimento. Inoltre, all'avvicinarsi della deadline, il Team ha constatato di non aver preso inizialmente in considerazione 2 passaggi fondamentali per il completo setup del progetto (*configurazione del versioning/release* e *configurazione della QA*).
