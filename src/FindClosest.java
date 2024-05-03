import java.awt.geom.Point2D;

public class FindClosest {

    private PointPair closestPointPair;
    private final QuickSort quicksort = new QuickSort();

    /** Constructor
     *
     * @param points --> point array
     */
    public FindClosest(Point2D.Double[] points)
    {
        //Sort points by X coordinate
        quicksort.sort(points, 0, points.length - 1, "compareX");
        this.closestPointPair = calculateClosestPointPair(points, 0, points.length - 1);
        //*********************************do nothing***************************************//
    }

    /** Get closest Point Pair
     *
     * @return closestPointPair
     */
    public PointPair getClosestPointPair()
    {
        return this.closestPointPair;
    }

    /** Main method for calculate and return closest point pair
     *
     * @param p --> point array
     * @param startIndex --> First index of p[]
     * @param lastIndex --> last index of p[]
     * @return
     */
    private PointPair calculateClosestPointPair(Point2D.Double[] p, int startIndex, int lastIndex)
    {
        if (lastIndex - startIndex == 1) {
            return new PointPair(p[startIndex], p[lastIndex]);
        } else if (lastIndex - startIndex == 2) {
            return getClosestPointPair(p[startIndex], p[startIndex + 1], p[lastIndex]);
        }

        int center = (startIndex + lastIndex) / 2;
        Point2D.Double centerPoint = p[center];

        PointPair leftPair = calculateClosestPointPair(p, startIndex, center);
        PointPair rightPair = calculateClosestPointPair(p, center + 1, lastIndex);

        PointPair minPair = (leftPair.getDistance() < rightPair.getDistance()) ? leftPair : rightPair;

        Point2D.Double[] strip = new Point2D.Double[lastIndex - startIndex + 1];
        int j = 0;

        for (int i = startIndex; i <= lastIndex; i++) {
            if (Math.abs(p[i].getX() - centerPoint.getX()) < minPair.getDistance()) {
                strip[j++] = p[i];
            }
        }

        quicksort.sort(strip, 0, j - 1, "compareY");

        return stripClosest(strip, j, minPair);
    }

    /** calculate and return closest point pair from 3 points
     *
     * @param p1 --> point 1
     * @param p2 --> point 2
     * @param p3 --> point 3
     * @return
     */
    private PointPair getClosestPointPair(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        PointPair pair1 = new PointPair(p1, p2);
        PointPair pair2 = new PointPair(p1, p3);
        PointPair pair3 = new PointPair(p2, p3);

        return getClosestPointPair(pair1, getClosestPointPair(pair2, pair3));
    }

    private PointPair getClosestPointPair(PointPair p1, PointPair p2){
        double dist1 = p1.getDistance();
        double dist2 = p2.getDistance();

        return (dist1 < dist2) ? p1 : p2;
    }

    /**
     * A utility function to find the distance between the closest points of
     * strip of given size. All points in strip[] are sorted according to
     * y coordinate. They all have an upper bound on minimum distance as d.
     * Note that this method seems to be a O(n^2) method, but it's a O(n)
     * method as the inner loop runs at most 6 times
     *
     * @param strip --> point array
     * @param size --> strip array element count
     * @param shortestLine --> shortest line calculated so far
     * @return --> new shortest line
     */
    private PointPair stripClosest(Point2D.Double strip[], int size, PointPair shortestLine) {
        double minDistance = shortestLine.getDistance();

        for(int i = 0; i < size; ++i) {
            for (int j = i + 1; j <size && (strip[j].getY() - strip[i].getY()) < minDistance; ++j) {
                if (strip[i].distance(strip[j]) < minDistance) {
                    minDistance = strip[i].distance(strip[j]);
                    shortestLine = new PointPair(strip[i], strip[j]);
                }
            }
        }
        return shortestLine;
    }

}