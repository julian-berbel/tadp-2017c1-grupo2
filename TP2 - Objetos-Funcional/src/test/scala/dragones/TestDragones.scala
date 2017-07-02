package dragones

import Item.Maza
import org.scalatest._
import participantes.Caracteristicas
import postas.requerimientos.BarbaridadMinima
import participantes.Vikingo._

class TestDragones extends FlatSpec {

  val nadder: Nadder = Nadder(10)
  val furiaNocturna: FuriaNocturna = FuriaNocturna(15,20)
  val gronckle: Gronckle = Gronckle(10, 30)
  val dragonConBarbaridadMinimaRequerida: FuriaNocturna = FuriaNocturna(150, 20, List(BarbaridadMinima(5)))

  "A Dragon" should "have a base speed of 60" in {
    assertResult(60)(nadder.velocidadBasica)
  }

  it should "not be able to be ridden by a rider who weighs more than 20% of the dragon's weight" in {
    assert(patan.montar(furiaNocturna).isEmpty)
  }

  it should "not be able to be ridden by a rider who weighs less than 20% of the dragon's weight" in {
    assert(hipo.montar(furiaNocturna).isDefined)
  }

  it should "have a net speed equal to its base speed minus its weight" in {
    assertResult(50)(nadder.velocidad)
  }

  "A Night Fury" should "have a base speed three times the regular" in {
    assertResult(180)(furiaNocturna.velocidadBasica)
  }

  it should "have a damage specific to itself" in {
    assertResult(20)(furiaNocturna.danio)
  }

  "A Nadder" should "not be able to be ridden by a rider with higher damage than it" in {
    val unVikingoFuerte: Vikingo = Vikingo(Caracteristicas(1,Maza(200),1,1,1))
    assert(unVikingoFuerte.montar(nadder).isEmpty)
  }

  it should "be able to be ridden by a rider with lower damage than it" in {
    assert(hipo.montar(nadder).isDefined)
  }

  it should "have a damage of 150" in {
    assertResult(150)(nadder.danio)
  }

  "A Gronckle" should "have a base speed half the regular" in {
    assertResult(30)(gronckle.velocidadBasica)
  }

  it should "have a damage equal to 5 times its weight" in {
    assertResult(50)(gronckle.danio)
  }

  it should "be able to be ridden by a rider with lower weight than its weight limit" in {
    assert(hipo.montar(gronckle).isDefined)
  }

  it should "not be able to be ridden by a rider with higher weight than its weight limit" in {
    assert(pataPez.montar(gronckle).isEmpty)
  }

  "A Dragon with a minimum barbarity requirement" should "not be able to be ridden by a rider with a barbarity lower than said minimum" in {
    assert(hipo.montar(dragonConBarbaridadMinimaRequerida).isEmpty)
  }

  it should "be able to be ridden by a rider with a barbarity higher than said minimum" in {
    assert(patan.montar(dragonConBarbaridadMinimaRequerida).isDefined)
  }

  "A Dragon with an item requirement" should "be able to be ridden by a rider with said item" in {
    assert(hipo.montar(chimuelo).isDefined)
  }

  it should "not able to be ridden by a rider without said item" in {
    assert(patan.montar(chimuelo).isEmpty)
  }
}