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

#### NotPurchasable

#### Purchasable

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
