package dragones

import org.scalatest._
import postas.requerimientos.BarbaridadMinima
import participantes.Vikingo._

class TestDragon extends FlatSpec {

  val nadder: Nadder = Nadder(10)
  val furiaNocturna: FuriaNocturna = FuriaNocturna(15,20)
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