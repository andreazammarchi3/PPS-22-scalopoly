package PPS.scalopoly.model

/** Represents the name of a space on the board.
  */
enum SpaceName(name: String, group:SpaceGroup):
  case VICOLO_CORTO extends SpaceName("Vicolo Corto", SpaceGroup.ROSA)
  case VICOLO_STRETTO extends SpaceName("Vicolo Stretto", SpaceGroup.ROSA)

  case BASTIONI_GRAN_SASSO extends SpaceName("Bastioni Gran Sasso", SpaceGroup.CELESTE)
  case VIALE_MONTEROSA extends SpaceName("Viale Monterosa", SpaceGroup.CELESTE)
  case VIALE_VESUVIO extends SpaceName("Viale Vesuvio", SpaceGroup.CELESTE)

  case VIA_ACCADEMIA extends SpaceName("Via Accademia", SpaceGroup.ARANCIONE)
  case CORSO_ATENEO extends SpaceName("Corso Ateneo", SpaceGroup.ARANCIONE)
  case PIAZZA_UNIVERSITA extends SpaceName("Piazza Universita'", SpaceGroup.ARANCIONE)

  case VIA_VERDI extends SpaceName("Piazza Universita'", SpaceGroup.MARRONE)
  case CORSO_RAFFAELLO extends SpaceName("Piazza Universita'", SpaceGroup.MARRONE)
  case PIAZZA_DANTE extends SpaceName("Piazza Universita'", SpaceGroup.MARRONE)

  case VIA_MARCO_POLO extends SpaceName("Via Marco Polo'", SpaceGroup.ROSSO)
  case CORSO_MAGELLANO extends SpaceName("Corso Magellano", SpaceGroup.ROSSO)
  case LARGO_COLOMBO extends SpaceName("Largo Colombo", SpaceGroup.ROSSO)

  case VIALE_COSTANTINO extends SpaceName("Viale Costantino", SpaceGroup.GIALLO)
  case VIALE_TRAIANO extends SpaceName("Viale Traiano", SpaceGroup.GIALLO)
  case PIAZZA_GIULIO_CESARE extends SpaceName("Piazza Giulio Cesare", SpaceGroup.GIALLO)

  case VIA_ROMA extends SpaceName("Via Roma", SpaceGroup.VERDE)
  case CORSO_IMPERO extends SpaceName("Corso Impero", SpaceGroup.VERDE)
  case LARGO_AUGUSTO extends SpaceName("Piazza Giulio Cesare", SpaceGroup.VERDE)

  case VIALE_DEI_GIARDINI extends SpaceName("Viale dei Giardini", SpaceGroup.VIOLA)
  case PARCO_DELLA_VITTORIA extends SpaceName("Parco della Vittoria", SpaceGroup.VIOLA)

  case STAZIONE_SUD extends SpaceName("Stazione Sud", SpaceGroup.FERROVIARIO)
  case STAZIONE_OVEST extends SpaceName("Stazione Ovest", SpaceGroup.FERROVIARIO)
  case STAZIONE_NORD extends SpaceName("Stazione Nord", SpaceGroup.FERROVIARIO)
  case STAZIONE_EST extends SpaceName("Stazione Est", SpaceGroup.FERROVIARIO)

  case SOCIETA_ELETTRICA extends SpaceName("Societa' Elettrica", SpaceGroup.COMPAGNIA_ELETTRICA)
  case SOCIETA_ACQUA_POTABILE extends SpaceName("Societa' Acqua Potabile", SpaceGroup.COMPAGNIA_ELETTRICA)

  case PROBABILITA extends SpaceName("Probabilita'", SpaceGroup.NO_GROUP)
  case IMPREVISTI extends SpaceName("Imprevisti", SpaceGroup.NO_GROUP)

  case VIA extends SpaceName("VIA!", SpaceGroup.NO_GROUP)
  case PRIGIONE_TRANSITO extends SpaceName("Prigione/Transito", SpaceGroup.NO_GROUP)
  case PARCHEGGIO_GRATUITO extends SpaceName("Parcheggio gratuito", SpaceGroup.NO_GROUP)
  case IN_PRIGIONE extends SpaceName("In prigione!", SpaceGroup.NO_GROUP)

  case TASSA_PATRIMONIALE extends SpaceName("Tassa patrimoniale", SpaceGroup.NO_GROUP)
  case TASSA_DI_LUSSO extends SpaceName("Tassa di Lusso", SpaceGroup.NO_GROUP)

  /** Checks if two SpaceNames are equal.
    * @param obj
    *   the other SpaceName
    * @return
    *   true if the two SpaceNames are equal, false otherwise
    */
  override def equals(obj: Any): Boolean = super.equals(obj)
