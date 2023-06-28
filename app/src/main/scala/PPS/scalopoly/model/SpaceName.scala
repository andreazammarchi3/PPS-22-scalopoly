package PPS.scalopoly.model

/** Represents the name of a space on the board.
  */
enum SpaceName(name: String, _sellingPrice: Int, _group: SpaceGroup):
  case VICOLO_CORTO extends SpaceName("Vicolo Corto", 150, SpaceGroup.ROSA)
  case VICOLO_STRETTO extends SpaceName("Vicolo Stretto", 150, SpaceGroup.ROSA)

  case BASTIONI_GRAN_SASSO
      extends SpaceName("Bastioni Gran Sasso", 250, SpaceGroup.CELESTE)
  case VIALE_MONTEROSA
      extends SpaceName("Viale Monterosa", 250, SpaceGroup.CELESTE)
  case VIALE_VESUVIO extends SpaceName("Viale Vesuvio", 300, SpaceGroup.CELESTE)

  case VIA_ACCADEMIA
      extends SpaceName("Via Accademia", 350, SpaceGroup.ARANCIONE)
  case CORSO_ATENEO extends SpaceName("Corso Ateneo", 350, SpaceGroup.ARANCIONE)
  case PIAZZA_UNIVERSITA
      extends SpaceName("Piazza Universita'", 400, SpaceGroup.ARANCIONE)

  case VIA_VERDI
      extends SpaceName("Piazza Universita'", 450, SpaceGroup.MARRONE)
  case CORSO_RAFFAELLO
      extends SpaceName("Piazza Universita'", 450, SpaceGroup.MARRONE)
  case PIAZZA_DANTE
      extends SpaceName("Piazza Universita'", 500, SpaceGroup.MARRONE)

  case VIA_MARCO_POLO
      extends SpaceName("Via Marco Polo'", 550, SpaceGroup.ROSSO)
  case CORSO_MAGELLANO
      extends SpaceName("Corso Magellano", 550, SpaceGroup.ROSSO)
  case LARGO_COLOMBO extends SpaceName("Largo Colombo", 600, SpaceGroup.ROSSO)

  case VIALE_COSTANTINO
      extends SpaceName("Viale Costantino", 650, SpaceGroup.GIALLO)
  case VIALE_TRAIANO extends SpaceName("Viale Traiano", 650, SpaceGroup.GIALLO)
  case PIAZZA_GIULIO_CESARE
      extends SpaceName("Piazza Giulio Cesare", 700, SpaceGroup.GIALLO)

  case VIA_ROMA extends SpaceName("Via Roma", 750, SpaceGroup.VERDE)
  case CORSO_IMPERO extends SpaceName("Corso Impero", 750, SpaceGroup.VERDE)
  case LARGO_AUGUSTO
      extends SpaceName("Piazza Giulio Cesare", 800, SpaceGroup.VERDE)

  case VIALE_DEI_GIARDINI
      extends SpaceName("Viale dei Giardini", 900, SpaceGroup.VIOLA)
  case PARCO_DELLA_VITTORIA
      extends SpaceName("Parco della Vittoria", 1000, SpaceGroup.VIOLA)

  case STAZIONE_SUD
      extends SpaceName("Stazione Sud", 480, SpaceGroup.FERROVIARIO)
  case STAZIONE_OVEST
      extends SpaceName("Stazione Ovest", 480, SpaceGroup.FERROVIARIO)
  case STAZIONE_NORD
      extends SpaceName("Stazione Nord", 480, SpaceGroup.FERROVIARIO)
  case STAZIONE_EST
      extends SpaceName("Stazione Est", 480, SpaceGroup.FERROVIARIO)

  case SOCIETA_ELETTRICA
      extends SpaceName(
        "Societa' Elettrica",
        375,
        SpaceGroup.COMPAGNIA_ELETTRICA
      )
  case SOCIETA_ACQUA_POTABILE
      extends SpaceName(
        "Societa' Acqua Potabile",
        375,
        SpaceGroup.COMPAGNIA_ELETTRICA
      )

  case PROBABILITA extends SpaceName("Probabilita'", 0, SpaceGroup.NO_GROUP)
  case IMPREVISTI extends SpaceName("Imprevisti", 0, SpaceGroup.NO_GROUP)

  case VIA extends SpaceName("VIA!", 0, SpaceGroup.NO_GROUP)
  case PRIGIONE_TRANSITO
      extends SpaceName("Prigione/Transito", 0, SpaceGroup.NO_GROUP)
  case PARCHEGGIO_GRATUITO
      extends SpaceName("Parcheggio gratuito", 0, SpaceGroup.NO_GROUP)
  case IN_PRIGIONE extends SpaceName("In prigione!", 0, SpaceGroup.NO_GROUP)

  case TASSA_PATRIMONIALE
      extends SpaceName("Tassa patrimoniale", 0, SpaceGroup.NO_GROUP)
  case TASSA_DI_LUSSO
      extends SpaceName("Tassa di Lusso", 0, SpaceGroup.NO_GROUP)

  /** Checks if two SpaceNames are equal.
    * @param obj
    *   the other SpaceName
    * @return
    *   true if the two SpaceNames are equal, false otherwise
    */
  override def equals(obj: Any): Boolean = super.equals(obj)

  /** SpaceName selling price.
    * @return
    *   SpaceName selling price
    */
  def sellingPrice: Int = _sellingPrice

  /** SpaceName selling price.
    *
    * @return
    *   SpaceName selling price
    */
  def group: SpaceGroup = _group
