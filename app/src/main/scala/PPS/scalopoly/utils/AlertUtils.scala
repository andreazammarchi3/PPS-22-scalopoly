package PPS.scalopoly.utils

import PPS.scalopoly.model.space.purchasable.{BuildableSpace, PurchasableSpace}
import PPS.scalopoly.model.{Player, SpaceGroup}
import PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType

import java.util.Optional

object AlertUtils:

  def showRentPayment(
      currentPlayer: Player,
      rent: Int,
      owner: Player,
      purchasableSpace: PurchasableSpace
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "Attenzione",
      "Pagamento di un affito",
      s"${currentPlayer.nickname}(${currentPlayer.token}) ha pagato ${rent}M a ${owner.nickname}(${owner.token}) per l'affitto di ${purchasableSpace.name}"
    )

  def showPlayerEliminated(
      currentPlayer: Player,
      owner: Player
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.WARNING,
      "Attenzione",
      "Eliminazione dal gioco",
      s"${currentPlayer.nickname}(${currentPlayer.token}) non puo' pagare l'affito, ha perso! Il suo patrimonio e' diventato di ${owner.nickname}(${owner.token})"
    )

  def showAskToBuyPurchasableSpace(
      currentPlayer: Player,
      purchasableSpace: PurchasableSpace
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.CONFIRMATION,
      "Attenzione",
      "Acquista proprieta'",
      s"${currentPlayer.nickname}(${currentPlayer.token}) vuoi acquistare la proprieta' ${purchasableSpace.name} libera, per ${purchasableSpace.sellingPrice}M ?"
    )

  def showNotPurchasableSpace(
      currentPlayer: Player,
      purchasableSpace: PurchasableSpace
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "Attenzione",
      "Proprieta' non acquistabile",
      s"${currentPlayer.nickname}(${currentPlayer.token}) non ha abbastanza soldi per acquistare la proprieta' ${purchasableSpace.name}"
    )

  def showPlayerCannotBuyHouses(
      currentPlayer: Player,
      buildableSpace: BuildableSpace
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "Attenzione",
      "Casa/Albergo non acquistabile",
      s"${currentPlayer.nickname}(${currentPlayer.token}) non ha abbastanza soldi per acquistare una nuova casa/albergo' sulla proprieta' ${buildableSpace.name} (costo: ${buildableSpace.buildingCost}M)"
    )

  def showPlayerDonNotOwnAllPropertiesOfSameGroup(
      currentPlayer: Player,
      spaceGroup: SpaceGroup
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "Attenzione",
      "Casa/Albergo non acquistabile",
      s"${currentPlayer.nickname}(${currentPlayer.token}) deve possedere tutte le proprieta' del gruppo $spaceGroup per poter costruirci sopra una casa/albergo"
    )

  def showNotPurchasableSpaceAction(
      currentPlayer: Player,
      notPurchasableSpace: NotPurchasableSpace
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "Scalopoly",
      "Casella speciale",
      s"${currentPlayer.nickname}(${currentPlayer.token}) è finito sulla casella' ${notPurchasableSpace.name} e ne subisce le conseguenze"
    )
  def showVictory(winner: Player): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "VITTORIA",
      "Vittoria",
      s"Complimenti ${winner.nickname}(${winner.token}) hai vinto!"
    )

  def showNotEnoughPlayersWarning(): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.WARNING,
      "Scalopoly",
      "Non e' possibile avviare il gioco",
      "Aggiungere almeno due giocatori"
    )

  def showEmptyPlayerNameWarning(): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.WARNING,
      "Scalopoly",
      "Non e' possibile aggiungere il giocatore.",
      "Il nome del giocatore non puo' essere vuoto."
    )
