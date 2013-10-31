package drj.euler.problems;

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
public class Problem102 {

	public static void main(String[] args) {
		Utility.Timer t = new Utility.Timer();
		t.start();

		String filePath = "external data/triangles.txt";
		
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

		System.out.println(containsOriginCount);
		System.out.println(t.toDecimalString());
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
