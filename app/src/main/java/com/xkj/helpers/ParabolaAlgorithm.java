package com.xkj.helpers;

/**
 * Created by fuqiang on 16/1/8.
 */
public class ParabolaAlgorithm {
    /**
     * a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2)) / (x1 * x1 * (x2 -
     * x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2))
     * b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
     * c = y1 - (x1 * x1) * a - x1 * b;
     */
    public static ParabolaParam calculate(float[][] points) {
        float x1 = points[0][0];
        float y1 = points[0][1];
        float x2 = points[1][0];
        float y2 = points[1][1];
        float x3 = points[2][0];
        float y3 = points[2][1];

        final float a = (y1 * (x2 - x3) + y2 * (x3 - x1) + y3 * (x1 - x2))
                / (x1 * x1 * (x2 - x3) + x2 * x2 * (x3 - x1) + x3 * x3 * (x1 - x2));
        final float b = (y1 - y2) / (x1 - x2) - a * (x1 + x2);
        final float c = y1 - (x1 * x1) * a - x1 * b;

        //Systems.out.println("-a->" + a + " b->" +b + " c->" +c);

        return new ParabolaParam(a, b, c);
    }

    /**
     * 根据两点计算抛物线方程
     *
     * @param topPointX
     * @param topPointY
     * @param startPointX
     * @param startPointY
     * @return
     */
    public static ParabolaParam caculate(float topPointX, float topPointY, float startPointX, float startPointY) {
        //根据顶点坐标计算另一个点坐标
        return ParabolaAlgorithm.calculate(new float[][]{{startPointX, startPointY},
                {topPointX, topPointY},
                {(2 * topPointX - startPointX), startPointY}});
    }

    /**
     * 抛物线参数
     */
    public static class ParabolaParam {
        private float a;
        private float b;
        private float c;

        public ParabolaParam(float a, float b, float c) {
            setA(a);
            setB(b);
            setC(c);
        }

        public float getB() {
            return b;
        }

        public void setB(float b) {
            this.b = b;
        }

        public float getC() {
            return c;
        }

        public void setC(float c) {
            this.c = c;
        }

        public float getA() {
            return a;
        }

        public void setA(float a) {
            this.a = a;
        }
    }
}
