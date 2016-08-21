package de.endrullis.sta.exmaples

import de.endrullis.sta._

/**
 * This example animates the construction of a bar diagram.
 *
 * @author Stefan Endrullis &lt;stefan@endrullis.de&gt;
 */
object BarDiagramAni extends ScalaTikzAni {

	conf.frameRate = 2

	conf.boundingBox = Pos(-1,-2) -> Pos(16,5)

	// define the color for the bars
	conf.header = """\definecolor{barColor}{HTML}{92dcec}""".stripMargin


	val country2bip = List(
		"Deutschland"    -> 0.9,
		"Frankreich"     -> 1.2,
		"Großbritannien" -> 1.9,
		"Italien"        -> 0.1,
		"Spanien"        -> 3.5,
		"Polen"          -> 3.5,
		"Rumänien"       -> 4.1,
		"Niederlande"    -> 1.5,
		"Griechenland"   -> 3.7,
		"Portugal"       -> 0.5
	)

	add(new BarDiagram(country2bip.sortBy(-_._2)) with MutableCodeContainer {
		implicit val axisTm  = TimeMap.linear[AxisRising]
		implicit val labelTm = TimeMap.linear[LabelFadeIn]
		implicit val varTm   = TimeMap.decelerate[BarRising]

		val axesRiseDur = 5
		add(xAxis riseUpIn axesRiseDur)
		add(yAxis riseUpIn axesRiseDur)

		for (i <- xIndices) {
			add(xLabel(i) start (axesRiseDur+i*10.0/xIndices.size) fadeInIn 10)
		}

		for (y <- 1 to 4) {
			add(yHelperLine(y) start (axesRiseDur/5*y) riseUpIn 10)
		}
		for (y <- 0 to 4) {
			add(yLabel(y) start (axesRiseDur+y*10.0/5) fadeInIn 10)
		}

		for (i <- xIndices) {
			add(bar(i) start (30+3*i) riseUpIn 10)
		}
	})


	animateNotInLine("gen/diagram", SlideOptions(frameRate = 30))

}
