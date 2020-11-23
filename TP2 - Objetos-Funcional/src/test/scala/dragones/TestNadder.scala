package dragones

import items.Maza
import org.scalatest.flatspec.AnyFlatSpec
import participantes.Caracteristicas
import participantes.Vikingo._

class TestNadder extends AnyFlatSpec {

  val nadder: Nadder = Nadder(10)

  "A Nadder" should "not be able to be ridden by a rider with higher damage than it" in {
    val unVikingoFuerte: Vikingo = Vikingo("", Caracteristicas(1,Maza(200),1,1,1))
    assert(unVikingoFuerte.montar(nadder).isEmpty)
  }

  it should "be able to be ridden by a rider with lower damage than it" in {
    assert(hipo.montar(nadder).isDefined)
  }

  it should "have a damage of 150" in {
    assertResult(150)(nadder.danio)
  }

}
