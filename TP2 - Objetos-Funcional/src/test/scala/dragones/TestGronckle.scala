package dragones

import org.scalatest._
import participantes.Vikingo._

class TestGronckle extends FlatSpec {

  val gronckle: Gronckle = Gronckle(10, 30)

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

}