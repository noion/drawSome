package ru.noion;

import org.graalvm.collections.Pair;

public final class VecFunctions {

    static double length(Vec3 vec) {
        return Math.sqrt(vec.x() * vec.x() + vec.y() * vec.y() + vec.z() * vec.z());
    }

    static Vec3 normalize(Vec3 vec) {
        var length = length(vec);
        return new Vec3(vec.x() / length, vec.y() / length, vec.z() / length);
    }

    public static double dot(Vec3 rd, Vec3 rd1) {
        return rd.x() * rd1.x() + rd.y() * rd1.y() + rd.z() * rd1.z();
    }

    public static Vec2 sphereIntersection(Vec3 ro, Vec3 rd, double r) {
        var b = dot(ro, rd);
        var c = dot(ro, ro) - r * r;
        var h = b * b - c;
        if (h < 0) {
            return new Vec2(-1, -1);
        }
        h = Math.sqrt(h);
        return new Vec2(-b - h, -b + h);
    }

    public static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public static Pair<Vec2, Vec3> boxIntersection(Vec3 ro, Vec3 rd, Vec3 boxSize, Vec3 normal) {
        var m = new Vec3(1.0 / rd.x(), 1.0 / rd.y(), 1.0 / rd.z());
        var n = m.mul(ro);
        var k = m.abs().mul(boxSize);
        var t1 = n.minus().sub(k);
        var t2 = n.minus().add(k);
        var tN = Math.max(Math.max(t1.x(), t1.y()), t1.z());
        var tF = Math.min(Math.min(t2.x(), t2.y()), t2.z());
        if (tN > tF || tF < 0) {
            return Pair.create(new Vec2(-1, -1), normal);
        }
        var yzx = new Vec3(t1.y(), t1.z(), t1.x());
        var zxy = new Vec3(t1.z(), t1.x(), t1.y());
        normal = sign(rd).mul(step(yzx, t1).mul(step(zxy, t1)));
        return Pair.create(new Vec2(tN, tF), normal);
    }

    public static double plane(Vec3 ro, Vec3 rd, Vec3 p, double w) {
        return -(dot(ro, p) + w) / dot(rd, p);
    }

    public static double sign(double x) {
        return x < 0 ? -1 : 1;
    }

    public static Vec3 sign(Vec3 vec) {
        return new Vec3(sign(vec.x()), sign(vec.y()), sign(vec.z()));
    }

    public static Vec3 step(Vec3 edge, Vec3 x) {
        return new Vec3(x.x() < edge.x() ? 0 : 1, x.y() < edge.y() ? 0 : 1, x.z() < edge.z() ? 0 : 1);
    }

    public static Vec3 rotateY(Vec3 vec, double angle) {
        var c = Math.cos(angle);
        var s = Math.sin(angle);
        return new Vec3(vec.x() * c - vec.z() * s, vec.y(), vec.x() * s + vec.z() * c);
    }

    public static Vec3 rotateZ(Vec3 vec, double angle) {
        var c = Math.cos(angle);
        var s = Math.sin(angle);
        return new Vec3(vec.x() * c - vec.y() * s, vec.x() * s + vec.y() * c, vec.z());
    }

    public static Vec3 reflect(Vec3 rd, Vec3 normal) {
        return rd.sub(normal.mul(dot(rd, normal) * 2));
    }
}
