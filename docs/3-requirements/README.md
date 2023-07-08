# Requisiti di sistema
Di seguito una formulazione dei requisiti del sistema individuati durante l'analisi del problema per un'applicazione che permetta di giocare a Monopoly.

## Requisiti di business
- Creare un sistema che permetta di giocare a Monopoly con alcune regole del gioco originale.
- Il sistema sarà in grado di supportare partite interattive tra 2 e 6 giocatori, in modalità "hotseat" (dove i giocatori si alternano sullo stesso dispositivo).

## Requisiti utente
- Gli utenti dovranno interagire con il sistema tramite un'interfaccia grafica (GUI).
- Gli utenti possono visualizzare le proprie informazioni inerenti la partita (nome, pedina, soldi, proprietà).
- Gli utenti possono interagire col sistema effettuando diverse azioni:
  - Durante la fase di preparazione della partita potranno:
    - aggiungere/rimuovere giocatori;
    - settare alcuni giocatori come Bot;
    - scegliere un nome e una pedina;
    - avviare una nuova partita;
  - Durante lo svolgimento di una partita potranno:
    - lanciare i dadi;
    - comprare proprietà;
    - costruire case/alberghi;
    - terminare il proprio turno;
    - abbandonare la partita.

## Requisiti funzionali
- Una partita può essere avviata solo se il numero di giocatori è compreso tra 2 e 6.
- Il gioco si svolge in maniera interattiva, mantenendo il corretto ordine dei turni.
- Il campo da gioco consiste nel tabellone quadrato di Monopoly, composto da 40 caselle.
- Ogni giocatore è rappresentato sul tabellone dalla sua pedina scelta.
- Le pedine devono potersi spostare lungo le caselle del tabellone.
- Un giocatore si muove lanciando due dadi, ottenendo un numero compreso tra 2 e 12 che indica di quante caselle avanzare. Può ripetere il turno se ottiene due numeri uguali.
- Esistono diversi tipi di caselle:
  - proprietà acquistabili dove si possono costruire case;
  - proprietà acquistabili dove non si possono costruire case (società e stazioni);
  - caselle speciali (via, probabilità, imprevisti, tasse, parcheggio gratuito, transito/prigione).
- Un giocatore può acquistare solo le proprietà acquistabili, nel momento in cui termina il proprio movimento sopra una di esse e se non sono già state acquistate da un altro giocatore.
- Se un giocatore finisce sopra una proprietà posseduta da un avversario gli pagherà l'affitto.
- Un giocatore perde quando non può permettersi di pagare un affitto, cioè quando i suoi soldi vanno in negativo.
- Quando un giocatore perde, la sua eredità (soldi + proprietà) viene data al giocatore che dovrebbe incassare l'affitto (se perde per affitto).
- Vince il giocatore che rimane per ultimo in gioco.

## Requisiti non funzionali
- **Usabilità**: Il sistema deve essere intuitivo e facile da usare, con un'interfaccia utente chiara e ben organizzata. I giocatori devono poter interagire con il gioco senza difficoltà e comprendere le azioni disponibili.

- **Prestazioni**: Il sistema deve essere responsivo e in grado di gestire il carico di lavoro necessario per la gestione di una partita a Monopoly. Le azioni dei giocatori, le transazioni immobiliari e il calcolo delle risorse devono avvenire in modo tempestivo, senza ritardi significativi.

- **Affidabilità**: Il sistema deve essere stabile e affidabile, evitando crash o errori critici.

- **Documentazione e supporto**: Il sistema deve essere accompagnato da una documentazione chiara e completa, che spieghi le regole del gioco e le funzionalità del software.

## Requisiti di implementazione
Utilizzo di:
- Scala 3.x
- JDK 17+
- TuProlog x

## Requisiti opzionali
- Notifiche dei vari eventi ai giocatori durante il gioco
- Un giocatore può eventualmente costruire case quando possiede tutte le proprietà di uno stesso gruppo di colore.
- Le conseguenze delle caselle speciali variano a seconda del tipo:
  - se un giocatore passa dal Via guadagna una determinata cifra fissa,
  - se finisce sopra una Tassa (Patrimoniale o Di Lusso) o gli Imprevisti paga una determinata cifra fissa,
  - se capita sulle Probabilità può casualmente incassare o pagare una determinata cifra fissa,
  - se capita sopra Parcheggio Gratuito, Transito o Prigione non succede nulla.
- Se un giocatore finisce sopra una proprietà posseduta da un avversario gli pagherà non un affitto fisso ma cambierà a seconda del tipo di proprietà:
  - proprietà acquistabili dove si possono costruire case:
    - se sono presenti case o alberghi l'affitto varia in base al loro numero;
    - se non sono presenti case o alberghi, si verifica se il proprietario possiede tutte le proprietà dello stesso colore di quella in cui è finito il giocatore: in caso negativo è previsto un affito base, in caso affermativo l'affitto è uguale al doppio di quello base;
  - proprietà acquistabili dove non si possono costruire case (società e stazioni);
    - nel caso delle società si verifica se il proprietario le possiede entrambe oppure no: a seconda del caso si moltiplica l'affitto base per un diverso moltiplicatore;
    - nel caso delle stazioni si verifica quante stazioni possiedere il proprietario e l'affitto varia in base a questo numero;
- Possibilità di etichettare un giocatore come Bot, cioè un giocatore controllato dal computer che esegue azioni e decisioni di base. In particolare:
  - lancia i dadi;
  - se finisce su una proprietà acquistabile e libera la compra se può permettersela;
  - se non può fare altro termina il turno.

[Indietro](../2-developments-process/README.md) | [Torna alla Home](../README.md) | [Vai a Design architetturale](../4-architectural-design/README.md)
