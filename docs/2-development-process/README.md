# Processo di sviluppo

## Agile
Lo sviluppo del progetto è avvenuto seguendo i prinicipi della metodologia Agile, adottando alcuni aspetti del framework **Scrum**. In particolare è stato utilizzato un processo incrementale e iterativo: lo sviluppo è stato diviso in diverse iterazioni (Sprint), ognuna delle quali ha aggiunto nuove funzionalità a quanto già implementato o ha raffinato quanto già presente.

## Product Backlog
Il Product Backlog rappresenta un elenco delle funzionalità essenziali da sviluppare per la realizzazione dell'applicativo e viene costantemente aggiornato durante ogni Sprint Planning, tenendo conto delle esigenze che emergono durante l'avanzamento del progetto. Per ciascuna funzionalità, vengono identificati i task necessari per completarla.

## Sprint
Durante ogni fase di Sprint Planning, viene stabilito un obiettivo specifico che consiste nella produzione di un prototipo funzionante entro la scadenza dello Sprint, seguendo un approccio di sviluppo incrementale. Sulla base di tale obiettivo, vengono selezionati i task da completare durante lo Sprint, utilizzando il Product Backlog come riferimento. Ogni task viene assegnato a un membro del team, in modo che ogni sviluppatore si dedichi a diversi aspetti del progetto.

Inoltre, durante ogni Sprint Planning, vengono affrontati gli aspetti legati alla modellazione del dominio, al fine di garantire una comprensione chiara a tutti i membri del gruppo e prevenire possibili fraintendimenti.

## Flow
All'interno del repository su GitHub si è deciso di adottare il flusso di lavoro noto come *Git Flow*. Questo flusso prevede che i commit per il rilascio di una versione vengano effettuati sul branch principale denominato "main". Il branch principale per lo sviluppo delle nuove funzionalità, invece, è denominato "develop".

A partire dal branch "develop", ogni sviluppatore crea il proprio branch personale, il cui nome segue un pattern definito come "feature/*nome_feature*". All'interno di questo branch, gli sviluppatori lavorano sul codice per completare i task assegnati a loro.

In questo modo, il flusso di lavoro Git Flow permette di gestire in modo strutturato lo sviluppo delle funzionalità, mantenendo un branch principale per il rilascio stabile e separando le modifiche in branch dedicati per ciascuna feature.

## Build Automation
Per automatizzare i processi di compilazione, testing ed esecuzione, si è scelto di utilizzare **Gradle** come strumento di *Build Automation*.

La scelta di preferire *Gradle* rispetto a *Sbt* si è basata su diversi fattori. Innanzitutto, Gradle è considerato una tecnologia più matura e consolidata nel campo dell'automazione della build. Ha una vasta base di utenti e una comunità attiva, il che significa che è possibile trovare un ampio supporto, risorse e plugin per esigenze specifiche.

Inoltre, gli sviluppatori del nostro team hanno più esperienza con Gradle rispetto a Sbt. Conoscono la sintassi e le funzionalità di Gradle e si sentono più a loro agio nell'utilizzarlo per gestire i processi di compilazione, testing ed esecuzione. Scegliere uno strumento con cui gli sviluppatori sono già familiari e che offre un ecosistema solido può contribuire a ridurre il tempo di apprendimento, migliorare l'efficienza e facilitare la risoluzione dei problemi durante lo sviluppo del progetto.

## Test Driven Development
Durante lo sviluppo del sistema, abbiamo scelto di applicare il **Test-Driven Development* (TDD) il più possibile. L'obiettivo principale del TDD è quello di anticipare la fase di testing il più presto possibile, al fine di ridurre i costi di manutenzione e il rischio di incorrere in bug o failures.

Il processo seguito nel TDD è un ciclo iterativo noto come **Red-Green-Refactor** (RGR), che comprende le seguenti fasi in ogni iterazione:
1. *Red*: si scrive un test che fallisce per una determinata funzionalità che deve essere implementata;
2. *Green*: si scrive il codice di produzione necessario per soddisfare il test definito in precedenza, facendo in modo che il test superi con successo;
3. *Refactor*: si esegue una ristrutturazione del codice, sia del codice di testing che del codice di produzione, al fine di migliorarne la qualità e leggibilità senza alterarne il comportamento funzionale, aggiungendo anche della doc.

Per supportare efficacemente questo processo, abbiamo scelto di adottare i seguenti strumenti:
- *JUnit 5*: Un framework che permette di definire i test di unità per il linguaggio di programmazione Scala. Tramite gli *assert* di JUnit, è possibile scrivere test chiari e ben strutturati per verificare il corretto funzionamento delle varie implementazioni.
- *JaCoCo*: Uno strumento che  consente di valutare la qualità dei test analizzando la percentuale di codice di produzione coperto dai test stessi. JaCoCo fornisce anche un report utile per identificare eventuali aree di codice non testate adeguatamente.
L'uso di questi strumenti supporta il processo TDD, permettendo di sviluppare il software in maniera più sicura e affidabile, grazie a una maggiore copertura dei test e a un'attenta valutazione della qualità del codice di produzione.

## Quality Assurance
Per garantire il controllo della qualità del sistema, sono stati scelti diversi strumenti specifici per Scala:
- *ScalaFmt*: per controllare lo stile del codice sorgente in Scala. Esso applica automaticamente le convenzioni di formattazione predefinite, garantendo uniformità e coerenza nel codice sorgente.
- *WartRemover*: per individuare possibili difetti o problemi nel codice Scala. Esso analizza il codice alla ricerca di costrutti o pattern noti che potrebbero causare errori o produrre comportamenti indesiderati.

## Continuous Integration
Per garantire la continua integrità e correttezza del codice durante lo sviluppo, si è scelto di adottare un workflow basato su **GitHub Actions**. GitHub Actions è un servizio di *Continuous Integration* (CI) offerto da GitHub. Consente di automatizzare le attività di build, test e rilascio del software direttamente nel repository GitHub. Utilizzando Actions, possiamo definire e configurare flussi di lavoro personalizzati per eseguire una serie di azioni o comandi in risposta a determinati eventi, come il push di nuovi commit o la creazione di pull request.

Nel nostro caso, abbiamo impostato il flusso di lavoro in modo che, ogni volta che viene effettuato un aggiornamento nel progetto (come un *push* o una *pull request*), i test del progetto vengano eseguiti automaticamente su diverse configurazioni di sistemi operativi (Windows, Ubuntu, MacOS). Questo ci consente di verificare che il codice funzioni correttamente su diverse piattaforme e di individuare eventuali problemi o incompatibilità in modo tempestivo. Vengono inoltre eseguiti anche i task di verifica della *coverage* e della *quality assurance*.

## Versioning
Per il *versioning* del sistema, è stato scelto di adottare lo standard *Semantic Versioning*. Secondo questo standard, ogni versione del sistema è caratterizzata da tre numeri identificativi: 
1. *Major*: incrementato quando vengono apportati cambiamenti non retrocompatibili nel sistema. Questo significa che le modifiche effettuate potrebbero richiedere aggiornamenti significativi e potrebbero non essere compatibili con le versioni precedenti del sistema.
2. *Minor*: incrementato quando vengono aggiunte nuove funzionalità o apportate modifiche retrocompatibili al sistema. Ciò indica che il sistema è stato migliorato o ampliato, ma le modifiche non interferiscono con il funzionamento delle funzionalità esistenti.
3. *Patch*: incrementato quando vengono effettuate correzioni di difetti o bug nel sistema, senza apportare modifiche significative alle funzionalità esistenti.

## Licensing
La licenza del progetto adottata è **MIT**. In generale, la scelta della licenza MIT riflette un approccio aperto e collaborativo per il progetto, consentendo agli sviluppatori e agli utenti di beneficiare delle funzionalità del software in modo flessibile e senza restrizioni, promuovendo la condivisione della conoscenza e il contributo comunitario.

[Torna alla Home](../README.md) | [Vai a Requisiti di sistema](../3-requirements/README.md)
