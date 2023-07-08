package PPS.scalopoly.utils

import PPS.scalopoly.model.space.purchasable.{BuildableSpace, PurchasableSpace}
import PPS.scalopoly.model.{Player, SpaceGroup}
import PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType

import java.util.Optional

/** Object that contains all the methods to show alerts to the user.
  */
object AlertUtils:

  private val MONEY_CURRENCY = "M"

  /** Shows an alert to the user, with the rent payment information.
    * @return
    *   The button pressed by the user.
    */
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
      s"${currentPlayer.nickname}(${currentPlayer.token}) ha pagato ${rent}M a" +
        s" ${owner.nickname}(${owner.token}) per l'affitto di ${purchasableSpace.name}"
    )

  /** Shows an alert to the user, with the player eliminated information.
    * @return
    *   The button pressed by the user.
    */
  def showPlayerEliminatedByRent(
      currentPlayer: Player,
      owner: Player
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.WARNING,
      "Attenzione",
      "Eliminazione dal gioco",
      s"${currentPlayer.nickname}(${currentPlayer.token}) non puo' pagare l'affito, ha perso!" +
        s" Il suo patrimonio e' diventato di ${owner.nickname}(${owner.token})"
    )

  /** Shows an alert to the user, with the player eliminated information.
    * @return
    *   The button pressed by the user.
    */
  def showPlayerEliminatedByTax(
      currentPlayer: Player,
      tax: Int
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.WARNING,
      "Attenzione",
      "Eliminazione dal gioco",
      s"${currentPlayer.nickname}(${currentPlayer.token}) non puo' pagare l'importo di ${tax}M, ha perso!"
    )

  /** Shows an alert to the user, asking if he wants to buy a purchasable space.
    * @return
    *   The button pressed by the user.
    */
  def showAskToBuyPurchasableSpace(
      currentPlayer: Player,
      purchasableSpace: PurchasableSpace
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.CONFIRMATION,
      "Attenzione",
      "Acquista proprieta'",
      s"${currentPlayer.nickname}(${currentPlayer.token}) vuoi acquistare la proprieta'" +
        s" ${purchasableSpace.name} libera, per ${purchasableSpace.sellingPrice}${MONEY_CURRENCY} ?"
    )

  /** Shows an alert to the user, if he doesn't have enough money to buy a purchasable space.
    * @return
    *   The button pressed by the user.
    */
  def showNotPurchasableSpace(
      currentPlayer: Player,
      purchasableSpace: PurchasableSpace
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "Attenzione",
      "Proprieta' non acquistabile",
      s"${currentPlayer.nickname}(${currentPlayer.token}) " +
        s"non ha abbastanza soldi per acquistare la proprieta' ${purchasableSpace.name}"
    )

  /** Shows an alert to the user, when player cannot buy houses.
    * @return
    *   The button pressed by the user.
    */
  def showPlayerCannotBuyHouses(
      currentPlayer: Player,
      buildableSpace: BuildableSpace
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "Attenzione",
      "Casa/Albergo non acquistabile",
      s"${currentPlayer.nickname}(${currentPlayer.token}) " +
        s"non ha abbastanza soldi per acquistare una nuova casa/albergo' sulla proprieta' " +
        s"${buildableSpace.name} (costo: ${buildableSpace.buildingCost}${MONEY_CURRENCY})"
    )

  /** Shows an alert to the user, when player doesn't own all the properties of the same group.
    * @return
    *   The button pressed by the user.
    */
  def showPlayerDonNotOwnAllPropertiesOfSameGroup(
      currentPlayer: Player,
      spaceGroup: SpaceGroup
  ): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "Attenzione",
      "Casa/Albergo non acquistabile",
      s"${currentPlayer.nickname}(${currentPlayer.token}) " +
        s"deve possedere tutte le proprieta' del gruppo $spaceGroup per poter costruirci sopra una casa/albergo"
    )

  /** Shows an alert to the user, with the action of a not purchasable space.
    * @return
    *   The button pressed by the user.
    */
  def showNotPurchasableSpaceAction(
      currentPlayer: Player,
      notPurchasableSpace: NotPurchasableSpace,
      moneyOperationResult: Int
  ): Optional[ButtonType] =
    val actionResultMessage = moneyOperationResult match
      case i if i > 0 => s" e ha guadagnato " + moneyOperationResult + MONEY_CURRENCY
      case i if i < 0 => s" e ha perso " + -1 * moneyOperationResult + MONEY_CURRENCY
      case _          => " e non ha subito nessuna conseguenza"
    FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "Scalopoly",
      "Casella speciale",
      s"${currentPlayer.nickname}(${currentPlayer.token}) e' finito sulla casella'" +
        s" ${notPurchasableSpace.name}${actionResultMessage}."
    )

  /** Shows an alert to the user, if he wins the game.
    * @return
    *   The button pressed by the user.
    */
  def showVictory(winner: Player): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "VITTORIA",
      "Vittoria",
      s"Complimenti ${winner.nickname}(${winner.token}) hai vinto!"
    )

  /** Shows an alert to the user, if there are not enough players to start the game.
    * @return
    *   The button pressed by the user.
    */
  def showNotEnoughPlayersWarning(): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.WARNING,
      "Scalopoly",
      "Non e' possibile avviare il gioco",
      "Aggiungere almeno due giocatori"
    )

  /** Shows an alert to the user, if the player name is empty.
    * @return
    *   The button pressed by the user.
    */
  def showEmptyPlayerNameWarning(): Optional[ButtonType] =
    FxmlUtils.showAlert(
      AlertType.WARNING,
      "Scalopoly",
      "Non e' possibile aggiungere il giocatore.",
      "Il nome del giocatore non puo' essere vuoto."
    )
