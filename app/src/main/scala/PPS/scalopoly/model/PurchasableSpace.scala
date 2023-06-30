package PPS.scalopoly.model

enum PurchasableSpace(
    _spaceName: SpaceName,
    _sellingPrice: Int,
    _spaceGroup: SpaceGroup
):
  case VICOLO_CORTO
      extends PurchasableSpace(SpaceName.VICOLO_CORTO, 150, SpaceGroup.ROSA)
  case VICOLO_STRETTO
      extends PurchasableSpace(SpaceName.VICOLO_STRETTO, 150, SpaceGroup.ROSA)

  case BASTIONI_GRAN_SASSO
      extends PurchasableSpace(
        SpaceName.BASTIONI_GRAN_SASSO,
        250,
        SpaceGroup.CELESTE
      )
  case VIALE_MONTEROSA
      extends PurchasableSpace(
        SpaceName.VIALE_MONTEROSA,
        250,
        SpaceGroup.CELESTE
      )
  case VIALE_VESUVIO
      extends PurchasableSpace(SpaceName.VIALE_VESUVIO, 300, SpaceGroup.CELESTE)

  case VIA_ACCADEMIA
      extends PurchasableSpace(
        SpaceName.VIA_ACCADEMIA,
        350,
        SpaceGroup.ARANCIONE
      )
  case CORSO_ATENEO
      extends PurchasableSpace(
        SpaceName.CORSO_ATENEO,
        350,
        SpaceGroup.ARANCIONE
      )
  case PIAZZA_UNIVERSITA
      extends PurchasableSpace(
        SpaceName.PIAZZA_UNIVERSITA,
        400,
        SpaceGroup.ARANCIONE
      )

  case VIA_VERDI
      extends PurchasableSpace(SpaceName.VIA_VERDI, 450, SpaceGroup.MARRONE)
  case CORSO_RAFFAELLO
      extends PurchasableSpace(
        SpaceName.CORSO_RAFFAELLO,
        450,
        SpaceGroup.MARRONE
      )
  case PIAZZA_DANTE
      extends PurchasableSpace(SpaceName.PIAZZA_DANTE, 500, SpaceGroup.MARRONE)

  case VIA_MARCO_POLO
      extends PurchasableSpace(SpaceName.VIA_MARCO_POLO, 550, SpaceGroup.ROSSO)
  case CORSO_MAGELLANO
      extends PurchasableSpace(SpaceName.CORSO_MAGELLANO, 550, SpaceGroup.ROSSO)
  case LARGO_COLOMBO
      extends PurchasableSpace(SpaceName.LARGO_COLOMBO, 600, SpaceGroup.ROSSO)

  case VIALE_COSTANTINO
      extends PurchasableSpace(
        SpaceName.VIALE_COSTANTINO,
        650,
        SpaceGroup.GIALLO
      )
  case VIALE_TRAIANO
      extends PurchasableSpace(SpaceName.VIALE_TRAIANO, 650, SpaceGroup.GIALLO)
  case PIAZZA_GIULIO_CESARE
      extends PurchasableSpace(
        SpaceName.PIAZZA_GIULIO_CESARE,
        700,
        SpaceGroup.GIALLO
      )

  case VIA_ROMA
      extends PurchasableSpace(SpaceName.VIA_ROMA, 750, SpaceGroup.VERDE)
  case CORSO_IMPERO
      extends PurchasableSpace(SpaceName.CORSO_IMPERO, 750, SpaceGroup.VERDE)
  case LARGO_AUGUSTO
      extends PurchasableSpace(SpaceName.LARGO_AUGUSTO, 800, SpaceGroup.VERDE)

  case VIALE_DEI_GIARDINI
      extends PurchasableSpace(
        SpaceName.VIALE_DEI_GIARDINI,
        900,
        SpaceGroup.VIOLA
      )
  case PARCO_DELLA_VITTORIA
      extends PurchasableSpace(
        SpaceName.PARCO_DELLA_VITTORIA,
        1000,
        SpaceGroup.VIOLA
      )

  case STAZIONE_SUD
      extends PurchasableSpace(
        SpaceName.STAZIONE_SUD,
        480,
        SpaceGroup.FERROVIARIO
      )
  case STAZIONE_OVEST
      extends PurchasableSpace(
        SpaceName.STAZIONE_OVEST,
        480,
        SpaceGroup.FERROVIARIO
      )
  case STAZIONE_NORD
      extends PurchasableSpace(
        SpaceName.STAZIONE_NORD,
        480,
        SpaceGroup.FERROVIARIO
      )
  case STAZIONE_EST
      extends PurchasableSpace(
        SpaceName.STAZIONE_EST,
        480,
        SpaceGroup.FERROVIARIO
      )

  case SOCIETA_ELETTRICA
      extends PurchasableSpace(
        SpaceName.SOCIETA_ELETTRICA,
        375,
        SpaceGroup.SOCIETA
      )
  case SOCIETA_ACQUA_POTABILE
      extends PurchasableSpace(
        SpaceName.SOCIETA_ACQUA_POTABILE,
        375,
        SpaceGroup.SOCIETA
      )

  private val RENT_SELLING_PRICE_PERC = 10

  def calculateRent(): Int =
    sellingPrice / RENT_SELLING_PRICE_PERC

  def spaceName: SpaceName = _spaceName

  def sellingPrice: Int = _sellingPrice

  def spaceGroup: SpaceGroup = _spaceGroup
