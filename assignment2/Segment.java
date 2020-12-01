package com.akamenev.assignment2;

public class Segment implements Comparable<Segment>{
    Point point1 = null;
    Point point2 = null;


    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        point1.s = this;
        point2.s = this;
    }

    @Override
    public int compareTo(Segment o) {
        int xNot = IntersectAny.getXnot();
        Segment b = o;
        Segment a = this;
        Point aS = this.point1;
        Point aF = this.point2;
        Point bS = o.point1;
        Point bF = o.point2;
        if(o.point2.x >= this.point2.x){
            b = this;
            a = o;
            bS = this.point1;
            bF = this.point2;
            aS = o.point1;
            aF = o.point2;
        }
        //direction(bS, bF, aS)
        int crossProduct = Intersect.direction(bS, bF, aS);
        boolean doTheyCross = Intersect.segmentIntersect(aS, aF, bS, bF);
        if (doTheyCross){
            //crossWhere = true - before / = false - after
            boolean crossWhere = (((aF.x - aS.x)*(bF.x - bS.x)*(bS.y - aS.y) -
                    (aF.x - aS.x)*(bF.y - bS.y)*bS.x + (bF.x - bS.x)*(aF.y - aS.y)*aS.x)
                    < ((bF.x - bS.x)*(aF.y - aS.y)*xNot - (aF.x - aS.x)*(bF.y - bS.y)*xNot));
            if(crossWhere){
                if (crossProduct > 0){
                    if(a == this){
                        return 1;
                    } else {
                        return -1;
                    }
                }
                else {
                    if(a == this)
                        return -1;
                    else
                        return 1;
                }
            }
        }
        if (crossProduct > 0){
            if(a == this){
                return -1;
            } else {
                return 1;
            }
        }
        else {
            if(a == this)
                return 1;
            else
                return -1;
        }
    }

    @Override
    public String toString() {
        return "This Segment hold points {" +
                " Point 1 = " + point1.toString() +
                ", Point 2 = " + point2.toString() +
                " }\n";
    }
}
