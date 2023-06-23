package PPS.scalopoly.model

/** Represents the name of a space on the board.
  */
enum SpaceName(name: String):
  case VICOLO_CORTO extends SpaceName("Vicolo Corto")
  case VICOLO_STRETTO extends SpaceName("Vicolo Stretto")

  case BASTIONI_GRAN_SASSO extends SpaceName("Bastioni Gran Sasso")
  case VIALE_MONTEROSA extends SpaceName("Viale Monterosa")
  case VIALE_VESUVIO extends SpaceName("Viale Vesuvio")

  case VIA_ACCADEMIA extends SpaceName("Via Accademia")
  case CORSO_ATENEO extends SpaceName("Corso Ateneo")
  case PIAZZA_UNIVERSITA extends SpaceName("Piazza Universita'")

  case VIA_VERDI extends SpaceName("Piazza Universita'")
  case CORSO_RAFFAELLO extends SpaceName("Piazza Universita'")
  case PIAZZA_DANTE extends SpaceName("Piazza Universita'")

  case VIA_MARCO_POLO extends SpaceName("Via Marco Polo'")
  case CORSO_MAGELLANO extends SpaceName("Corso Magellano")
  case LARGO_COLOMBO extends SpaceName("Largo Colombo")

  case VIALE_COSTANTINO extends SpaceName("Viale Costantino")
  case VIALE_TRAIANO extends SpaceName("Viale Traiano")
  case PIAZZA_GIULIO_CESARE extends SpaceName("Piazza Giulio Cesare")

  case VIA_ROMA extends SpaceName("Via Roma")
  case CORSO_IMPERO extends SpaceName("Corso Impero")
  case LARGO_AUGUSTO extends SpaceName("Piazza Giulio Cesare")

  case VIALE_DEI_GIARDINI extends SpaceName("Viale dei Giardini")
  case PARCO_DELLA_VITTORIA extends SpaceName("Parco della Vittoria")

  case STAZIONE_SUD extends SpaceName("Stazione Sud")
  case STAZIONE_OVEST extends SpaceName("Stazione Ovest")
  case STAZIONE_NORD extends SpaceName("Stazione Nord")
  case STAZIONE_EST extends SpaceName("Stazione Est")

  case SOCIETA_ELETTRICA extends SpaceName("Societa' Elettrica")
  case SOCIETA_ACQUA_POTABILE extends SpaceName("Societa' Acqua Potabile")

  case PROBABILITA extends SpaceName("Probabilita'")
  case IMPREVISTI extends SpaceName("Imprevisti")

  case VIA extends SpaceName("VIA!")
  case PRIGIONE_TRANSITO extends SpaceName("Prigione/Transito")
  case PARCHEGGIO_GRATUITO extends SpaceName("Parcheggio gratuito")
  case IN_PRIGIONE extends SpaceName("In prigione!")

  case TASSA_PATRIMONIALE extends SpaceName("Tassa patrimoniale")
  case TASSA_DI_LUSSO extends SpaceName("Tassa di Lusso")

  /** Checks if two SpaceNames are equal.
    * @param obj
    *   the other SpaceName
    * @return
    *   true if the two SpaceNames are equal, false otherwise
    */
  override def equals(obj: Any): Boolean = super.equals(obj)
