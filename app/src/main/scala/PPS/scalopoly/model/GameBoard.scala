package PPS.scalopoly.model

import PPS.scalopoly.model.SpaceName.*

/** The game board is a map of the game spaces represented by a list of
  * [[SpaceName]].
  */
object GameBoard:

  private val _gameBoardList: List[SpaceName] = List(
    VIA,
    VICOLO_CORTO,
    PROBABILITA,
    VICOLO_STRETTO,
    TASSA_PATRIMONIALE,
    STAZIONE_SUD,
    BASTIONI_GRAN_SASSO,
    IMPREVISTI,
    VIALE_MONTEROSA,
    VIALE_VESUVIO,
    PRIGIONE_TRANSITO,
    VIA_ACCADEMIA,
    SOCIETA_ELETTRICA,
    CORSO_ATENEO,
    PIAZZA_UNIVERSITA,
    STAZIONE_OVEST,
    VIA_VERDI,
    PROBABILITA,
    CORSO_RAFFAELLO,
    PIAZZA_DANTE,
    PARCHEGGIO_GRATUITO,
    VIA_MARCO_POLO,
    IMPREVISTI,
    CORSO_MAGELLANO,
    LARGO_COLOMBO,
    STAZIONE_NORD,
    VIALE_COSTANTINO,
    VIALE_TRAIANO,
    SOCIETA_ACQUA_POTABILE,
    PIAZZA_GIULIO_CESARE,
    IN_PRIGIONE,
    VIA_ROMA,
    CORSO_IMPERO,
    PROBABILITA,
    LARGO_AUGUSTO,
    STAZIONE_EST,
    IMPREVISTI,
    VIALE_DEI_GIARDINI,
    TASSA_DI_LUSSO,
    PARCO_DELLA_VITTORIA
  )

  /** Returns the game board map.
    * @return
    *   the game board map.
    */
  def gameBoardList: List[SpaceName] = _gameBoardList

  /** Returns the size of the game board.
    * @return
    *   the size of the game board.
    */
  def size: Int = _gameBoardList.length
