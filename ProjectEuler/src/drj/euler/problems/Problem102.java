package drj.euler.problems;

import drj.euler.Answer;
import drj.euler.Problem;
import drj.euler.Utility;

/**
 * Three distinct points are plotted at random on a Cartesian plane, for which
 * -1000 <= x, y <= 1000, such that a triangle is formed.
 * 
 * Consider the following two triangles:
 * 
 * 		A(-340,495), B(-153,-910), C(835,-947)
 * 		X(-175,41), Y(-421,-714), Z(574,-645)
 * 
 * It can be verified that triangle ABC contains the origin, whereas triangle
 * XYZ does not.
 * 
 * Using triangles.txt (right click and 'Save Link/Target As...'), a 27K text
 * file containing the co-ordinates of one thousand "random" triangles, find
 * the number of triangles for which the interior contains the origin.
 * 
 * NOTE: The first two examples in the file represent the triangles in the
 * example given above.
 */
@Answer("228")
public class Problem102 extends Problem {

	public static void main(String[] args) {
		Problem p = new Problem102();
		System.out.println(p);
	}

	@Override
	protected String onSolve() {
		String filePath = "data/triangles.txt";

		int containsOriginCount = 0;

		for (String line : Utility.getFileContents(filePath)) {
			String[] points = line.split(",");
			float[] xVertices = new float[points.length / 2];
			float[] yVertices = new float[points.length / 2];
			int verticeCount = 0;
			for (int i = 0; i < points.length; i++) {
				if ((i & 1) == 0) {
					xVertices[i / 2] = Float.parseFloat(points[i]);
					verticeCount++;
				} else {
					yVertices[i / 2] = Float.parseFloat(points[i]);
				}
			}
			if (pointInPoly(verticeCount, xVertices, yVertices, 0, 0))
				containsOriginCount++;
		}

		return String.valueOf(containsOriginCount);
	}

	private static boolean pointInPoly(int numVertices,
			float[] verticeXVals, float[] verticeYVals,
			float pointX, float pointY) {
		boolean inPoly = false;
		for (int i = 0, j = numVertices-1; i < numVertices; j = i++) {
			if (((verticeYVals[i]>pointY) != (verticeYVals[j]>pointY)) &&
					(pointX < (verticeXVals[j]-verticeXVals[i]) *
							(pointY-verticeYVals[i]) /
							(verticeYVals[j]-verticeYVals[i])
							+ verticeXVals[i]))
				inPoly = !inPoly;
		}
		return inPoly;
	}
}
