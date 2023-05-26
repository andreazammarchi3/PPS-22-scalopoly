package PPS.scalopoly.model

import PPS.scalopoly.model.SpaceName.*

class GameBoard:
  private val _gameBoardMap: Map[Int, SpaceName] = Map(
    0 -> VIA,
    1 -> VICOLO_CORTO,
    2 -> PROBABILITA,
    3 -> VICOLO_STRETTO,
    4 -> TASSA_PATRIMONIALE,
    5 -> STAZIONE_SUD,
    6 -> BASTIONI_GRAN_SASSO,
    7 -> IMPREVISTI,
    8 -> VIALE_MONTEROSA,
    9 -> VIALE_VESUVIO,
    10 -> PRIGIONE_TRANSITO,
    11 -> VIA_ACCADEMIA,
    12 -> SOCIETA_ELETTRICA,
    13 -> CORSO_ATENEO,
    14 -> PIAZZA_UNIVERSITA,
    15 -> STAZIONE_OVEST,
    16 -> VIA_VERDI,
    17 -> PROBABILITA,
    18 -> CORSO_RAFFAELLO,
    19 -> PIAZZA_DANTE,
    20 -> PARCHEGGIO_GRATUITO,
    21 -> VIA_MARCO_POLO,
    22 -> IMPREVISTI,
    23 -> CORSO_MAGELLANO,
    24 -> LARGO_COLOMBO,
    25 -> STAZIONE_NORD,
    26 -> VIALE_COSTANTINO,
    27 -> VIALE_TRAIANO,
    28 -> SOCIETA_ACQUA_POSTABILE,
    29 -> PIAZZA_GIULIO_CESARE,
    30 -> IN_PRIGIONE,
    31 -> VIA_ROMA,
    32 -> CORSO_IMPERO,
    33 -> PROBABILITA,
    34 -> LARGO_AUGUSTO,
    35 -> STAZIONE_EST,
    36 -> IMPREVISTI,
    37 -> VIALE_DEI_GIARDINI,
    38 -> TASSA_DI_LUSSO,
    39 -> PARCO_DELLA_VITTORIA
  )

  def gameBoardMap: Map[Int, SpaceName] = _gameBoardMap
  
  def size: Int = _gameBoardMap.size

